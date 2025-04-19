package at.fhv.sys.eventbus.client;

import at.fhv.sys.hotel.commands.shared.events.CustomerCreatedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerDeletedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerUpdatedEvent;
import at.fhv.sys.hotel.commands.shared.events.RoomBookedEvent;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey="hotel-query-api-client")
// Globaler Pfad für diesen Rest-Endpoint
@Path("/api")
public interface QueryClient {

    @POST
    @Path("/customerCreated")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardCustomerCreatedEvent(CustomerCreatedEvent event);

    @POST
    @Path("/customerUpdated")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardCustomerUpdatedEvent(CustomerUpdatedEvent event);

    @POST
    @Path("/customerDeleted")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardCustomerDeletedEvent(CustomerDeletedEvent event);

    @POST
    @Path("/roomBooked")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardRoomBookedEvent(RoomBookedEvent event);
}
