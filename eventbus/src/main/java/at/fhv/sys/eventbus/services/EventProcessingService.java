package at.fhv.sys.eventbus.services;

import at.fhv.sys.eventbus.client.QueryClient;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreatedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerDeletedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerUpdatedEvent;
import at.fhv.sys.hotel.commands.shared.events.RoomBookedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class EventProcessingService {

    @Inject
    @RestClient
    QueryClient queryClient;

    @Inject
    EventStoreService eventStoreService;


    public EventProcessingService() {
    }

    public void processCustomerCreatedEvent(String stream, Object eventObject) {
        queryClient.forwardCustomerCreatedEvent((CustomerCreatedEvent) eventObject);
        eventStoreService.saveEvent(stream, eventObject);
    }

    public void processCustomerUpdatedEvent(String stream, Object eventObject) {
        queryClient.forwardCustomerUpdatedEvent((CustomerUpdatedEvent) eventObject);
        eventStoreService.saveEvent(stream, eventObject);
    }

    public void processCustomerDeletedEvent(String stream, Object eventObject) {
        queryClient.forwardCustomerDeletedEvent((CustomerDeletedEvent) eventObject);
        eventStoreService.saveEvent(stream, eventObject);
    }

    public void processRoomBookedEvent(String stream, Object eventObject) {
        queryClient.forwardRoomBookedEvent((RoomBookedEvent) eventObject);
        eventStoreService.saveEvent(stream, eventObject);
    }
}