package at.fhv.sys.eventbus.client;

import at.fhv.sys.hotel.commands.shared.events.*;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.print.attribute.standard.Media;

@RegisterRestClient(configKey="hotel-command-api-client")
@Path("/api")
public interface CommandClient {

    @POST
    @Path("/roomBooked")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardRoomBookedEvent(RoomBookedEvent roomBookedEvent);


    @POST
    @Path("/bookingCanceled")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardBookingCanceledEvent(BookingCanceledEvent bookingCanceledEvent);


    @POST
    @Path("/customerCreated")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardCustomerCreatedEvent(CustomerCreatedEvent customerCreatedEvent);

    @POST
    @Path("/customerUpdated")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardCustomerUpdatedEvent(CustomerUpdatedEvent customerUpdatedEvent);

    @POST
    @Path("/customerDeleted")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardCustomerDeletedEvent(CustomerDeletedEvent customerDeletedEvent);

}
