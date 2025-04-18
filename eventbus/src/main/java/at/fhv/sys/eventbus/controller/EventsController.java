package at.fhv.sys.eventbus.controller;

import at.fhv.sys.eventbus.services.EventProcessingService;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreatedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerDeletedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerUpdatedEvent;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logmanager.Logger;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventsController {
    @Inject
    EventProcessingService eventStoreService;

    public EventsController() {
    }

    @POST
    @Path("/customerCreated")
    public Response customerCreated(CustomerCreatedEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processCustomerCreatedEvent("customer-" + event.getUserId(), event);
        return Response.ok(event).build();
    }

    @POST
    @Path("/customerUpdated")
    public Response customerUpdated(CustomerUpdatedEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processCustomerUpdatedEvent("customer-" + event.getUserId(), event);
        return Response.ok(event).build();
    }

    @POST
    @Path("/customerDeleted")
    public Response customerDeleted(CustomerDeletedEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processCustomerDeletedEvent("customer-" + event.getCustomerId(), event);
        return Response.ok(event).build();
    }
}