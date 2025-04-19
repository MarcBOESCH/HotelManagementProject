package at.fhv.sys.eventbus.controller;

import at.fhv.sys.eventbus.services.EventProcessingService;
import at.fhv.sys.hotel.commands.shared.events.*;
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

    @POST
    @Path("/roomBooked")
    public Response roomBooked(RoomBookedEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processRoomBookedEvent("booking-" + event.getBookingId(), event);
        return Response.ok(event).build();
    }

    @POST
    @Path("/bookingCanceled")
    public Response bookingCanceled(BookingCanceledEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processBookingCanceledEvent("booking-" + event.getBookingId(), event);
        return Response.ok(event).build();
    }

    @POST
    @Path("/bookingPaid")
    public Response bookingPaid(BookingPaidEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processBookingPaidEvent("booking-" + event.getBookingId(), event);
        return Response.ok(event).build();
    }

}