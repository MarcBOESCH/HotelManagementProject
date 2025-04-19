package at.fhv.sys.hotel.client;

import at.fhv.sys.hotel.commands.shared.events.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey="hotel-eventbus-api-client")
@Path("/api")
public interface EventBusClient {

    @POST
    @Path("/customerCreated")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    CustomerCreatedEvent processCustomerCreatedEvent(CustomerCreatedEvent event);

    @POST
    @Path("/customerUpdated")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    CustomerUpdatedEvent processCustomerUpdatedEvent(CustomerUpdatedEvent event);

    @POST
    @Path("/customerDeleted")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    CustomerDeletedEvent processCustomerDeletedEvent(CustomerDeletedEvent event);

    @POST
    @Path("/roomBooked")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    RoomBookedEvent processRoomBookedEvent(RoomBookedEvent event);

    @POST
    @Path("/bookingCanceled")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    BookingCanceledEvent processBookingCanceledEvent(BookingCanceledEvent event);
}
