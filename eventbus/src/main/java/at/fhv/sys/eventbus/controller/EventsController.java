package at.fhv.sys.eventbus.controller;

import at.fhv.sys.eventbus.services.EventProcessingService;
import at.fhv.sys.eventbus.services.EventStoreService;
import at.fhv.sys.hotel.commands.shared.events.*;
import com.eventstore.dbclient.ResolvedEvent;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logmanager.Logger;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventsController {
    @Inject
    EventProcessingService eventProcessingService;

    @Inject
    EventStoreService eventStoreService;

    public EventsController() {
    }

    @GET
    @Path("/getAllEvents")
    public Response getAllEvents() {
        List<Object> domainEvents = eventStoreService.getAllEvents();
        return Response.ok(domainEvents).build();
    }

    @POST
    @Path("/replayAllEvents")
    public Response replayAllEvents() {
        new Thread(eventProcessingService::replayAllEvents).start();
        return Response.accepted().build();
    }

    @POST
    @Path("/roomCreated")
    public Response roomCreated(RoomCreatedEvent event) {
        eventProcessingService.processRoomCreatedEvent("room-" + event.getRoomNumber(), event);
        return Response.ok(event).build();
    }

    @POST
    @Path("/customerCreated")
    public Response customerCreated(CustomerCreatedEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventProcessingService.processCustomerCreatedEvent("customer-" + event.getUserId(), event);
        return Response.ok(event).build();
    }

    @POST
    @Path("/customerUpdated")
    public Response customerUpdated(CustomerUpdatedEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventProcessingService.processCustomerUpdatedEvent("customer-" + event.getUserId(), event);
        return Response.ok(event).build();
    }

    @POST
    @Path("/customerDeleted")
    public Response customerDeleted(CustomerDeletedEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventProcessingService.processCustomerDeletedEvent("customer-" + event.getCustomerId(), event);
        return Response.ok(event).build();
    }

    @POST
    @Path("/roomBooked")
    public Response roomBooked(RoomBookedEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventProcessingService.processRoomBookedEvent("booking-" + event.getBookingId(), event);
        return Response.ok(event).build();
    }

    @POST
    @Path("/bookingCanceled")
    public Response bookingCanceled(BookingCanceledEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventProcessingService.processBookingCanceledEvent("booking-" + event.getBookingId(), event);
        return Response.ok(event).build();
    }

    @POST
    @Path("/bookingPaid")
    public Response bookingPaid(BookingPaidEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventProcessingService.processBookingPaidEvent("booking-" + event.getBookingId(), event);
        return Response.ok(event).build();
    }

}