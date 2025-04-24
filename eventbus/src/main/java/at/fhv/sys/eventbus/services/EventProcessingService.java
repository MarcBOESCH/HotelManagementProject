package at.fhv.sys.eventbus.services;

import at.fhv.sys.eventbus.client.QueryClient;
import at.fhv.sys.hotel.commands.shared.events.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class EventProcessingService {

    @Inject
    @RestClient
    QueryClient queryClient;

    @Inject
    EventStoreService eventStoreService;


    public EventProcessingService() {
    }

    public void processRoomCreatedEvent(String stream, Object eventObject) {
        queryClient.forwardRoomCreatedEvent((RoomCreatedEvent) eventObject);
        eventStoreService.saveEvent(stream, eventObject);
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

    public void processBookingCanceledEvent(String stream, Object eventObject) {
        queryClient.forwardBookingCanceledEvent((BookingCanceledEvent) eventObject);
        eventStoreService.saveEvent(stream, eventObject);
    }

    public void processBookingPaidEvent(String stream, Object eventObject) {
        queryClient.forwardBookingPaidEvent((BookingPaidEvent) eventObject);
        eventStoreService.saveEvent(stream, eventObject);
    }

    public void replayAllEvents() {
        List<Object> events = eventStoreService.getAllEvents();

        for (Object evt: events) {
            if (evt instanceof CustomerCreatedEvent customerCreatedEvent) {
                queryClient.forwardCustomerCreatedEvent(customerCreatedEvent);
            } else if (evt instanceof CustomerUpdatedEvent customerUpdatedEvent) {
                queryClient.forwardCustomerUpdatedEvent(customerUpdatedEvent);
            } else if (evt instanceof CustomerDeletedEvent customerDeletedEvent) {
                queryClient.forwardCustomerDeletedEvent(customerDeletedEvent);
            } else if (evt instanceof RoomBookedEvent roomBookedEvent) {
                queryClient.forwardRoomBookedEvent(roomBookedEvent);
            } else if (evt instanceof BookingCanceledEvent bookingCanceledEvent) {
                queryClient.forwardBookingCanceledEvent(bookingCanceledEvent);
            } else if (evt instanceof BookingPaidEvent bookingPaidEvent) {
                queryClient.forwardBookingPaidEvent(bookingPaidEvent);
            } else {
                Logger.getAnonymousLogger().info("Unknown event type" + evt);
            }
        }
    }
}