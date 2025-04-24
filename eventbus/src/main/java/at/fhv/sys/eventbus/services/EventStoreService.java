package at.fhv.sys.eventbus.services;

import at.fhv.sys.hotel.commands.shared.events.*;
import com.eventstore.dbclient.*;
import com.eventstore.dbclient.proto.streams.StreamsOuterClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class EventStoreService {
    private final EventStoreDBClient eventStoreDBClient;
    private final ObjectMapper objectMapper;

    public EventStoreService(@ConfigProperty(name= "eventstoredb.uri") String connectionUri) {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .registerModule(new Jdk8Module())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        EventStoreDBClientSettings settings = EventStoreDBConnectionString.parseOrThrow(connectionUri);
        this.eventStoreDBClient = EventStoreDBClient.create(settings);
        System.out.println("EventStoreDB client created");
    }



    public void saveEvent(String stream, Object eventObject) {
        try {
            UUID eventId = UUID.randomUUID();
            String eventType = eventObject.getClass().getSimpleName();
            byte[] json = objectMapper.writeValueAsBytes(eventObject);

            EventData eventData = EventData.builderAsJson(eventId, eventType, json).build();

            System.out.println("Writing event to stream: " + stream);
            eventStoreDBClient.appendToStream(stream, eventData).join();
            System.out.println("Event written");
        } catch (Exception e) {
            throw new RuntimeException("EventStore write failed", e);
        }
    }

    public List<Object> getAllEvents() {
        try {
            ReadAllOptions options = ReadAllOptions.get().forwards().fromStart();
            ReadResult result = eventStoreDBClient.readAll(options).get();

            List<ResolvedEvent> allEvents = new ArrayList<>(result.getEvents());

            return allEvents.stream()
                    // only domain events (customer-... or booking-...)
                    .filter(domainEvent -> {
                        String streamId = domainEvent.getEvent().getStreamId();
                        return streamId.startsWith("customer-") || streamId.startsWith("booking-");
                    })
                    .map(domainEvent -> {
                        String json = new String(domainEvent.getOriginalEvent().getEventData(), StandardCharsets.UTF_8);
                        String type = domainEvent.getEvent().getEventType();
                        try {
                            return switch (type) {
                                case "CustomerCreatedEvent" -> objectMapper.readValue(json, CustomerCreatedEvent.class);
                                case "CustomerUpdatedEvent" -> objectMapper.readValue(json, CustomerUpdatedEvent.class);
                                case "CustomerDeletedEvent" -> objectMapper.readValue(json, CustomerDeletedEvent.class);
                                case "RoomBookedEvent" -> objectMapper.readValue(json, RoomBookedEvent.class);
                                case "BookingCanceledEvent" -> objectMapper.readValue(json, BookingCanceledEvent.class);
                                case "BookingPaidEvent" -> objectMapper.readValue(json, BookingPaidEvent.class);
                                default -> Map.of("unknownEventType", type, "payload", objectMapper.readTree(json));
                            };
                        } catch (Exception e) {
                            return Map.of("error", e.getMessage(), "type", type, "raw", json);
                        }
                    })
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read all events from ESDB", e);
        }
    }
}
