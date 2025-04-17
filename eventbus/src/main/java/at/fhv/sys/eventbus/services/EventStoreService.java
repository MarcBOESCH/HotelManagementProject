package at.fhv.sys.eventbus.services;

import com.eventstore.dbclient.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class EventStoreService {
    private final EventStoreDBClient eventStoreDBClient;
    private final ObjectMapper objectMapper;

    public EventStoreService(@ConfigProperty(name= "eventstoredb.uri") String connectionUri) {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        System.out.println(connectionUri);
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
}
