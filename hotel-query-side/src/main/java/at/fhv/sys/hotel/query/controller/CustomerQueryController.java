package at.fhv.sys.hotel.query.controller;

import at.fhv.sys.hotel.commands.shared.events.CustomerCreatedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerUpdatedEvent;
import at.fhv.sys.hotel.models.CustomerQueryModel;
import at.fhv.sys.hotel.projection.CustomerProjection;
import at.fhv.sys.hotel.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logmanager.Logger;

import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerQueryController {

    @Inject
    CustomerProjection customerProjection;

    @Inject
    CustomerService customerService;

    public CustomerQueryController() {
    }

    @POST
    @Path("/customerCreated")
    public Response customerCreated(CustomerCreatedEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        customerProjection.processCustomerCreatedEvent(event);
        return Response.ok(event).build();
    }

    @POST
    @Path("/customerUpdated")
    public Response customerUpdated(CustomerUpdatedEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        customerProjection.processCustomerUpdatedEvent(event);
        return Response.ok(event).build();
    }

    @GET
    @Path("/customers")
    public List<CustomerQueryModel> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}