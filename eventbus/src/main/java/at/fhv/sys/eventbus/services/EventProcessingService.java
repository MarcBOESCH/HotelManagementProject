package at.fhv.sys.eventbus.services;

import at.fhv.sys.eventbus.client.QueryClient;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreatedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerUpdatedEvent;
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
        eventStoreService.saveEvent(stream, eventObject);
        queryClient.forwardCustomerCreatedEvent((CustomerCreatedEvent) eventObject);
    }

    public void processCustomerUpdatedEvent(String stream, Object eventObject) {
        eventStoreService.saveEvent(stream, eventObject);
        queryClient.forwardCustomerUpdatedEvent((CustomerUpdatedEvent) eventObject);
    }
}