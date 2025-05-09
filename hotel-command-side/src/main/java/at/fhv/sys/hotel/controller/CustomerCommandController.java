package at.fhv.sys.hotel.controller;

import at.fhv.sys.hotel.commands.CreateCustomerCommand;
import at.fhv.sys.hotel.commands.CustomerAggregate;
import at.fhv.sys.hotel.commands.DeleteCustomerCommand;
import at.fhv.sys.hotel.commands.UpdateCustomerCommand;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreatedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerDeletedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerUpdatedEvent;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerCommandController {

    private final CustomerAggregate customerAggregate;

    public CustomerCommandController(CustomerAggregate customerAggregate) {
        this.customerAggregate = customerAggregate;
    }

    @POST
    @Path("/createCustomer")
    public Response createCustomer(@Valid CreateCustomerCommand command) {
        String customerId = customerAggregate.handleCreateCustomer(command);
        return Response.status(Response.Status.CREATED)
                .entity(Map.of("customerId", customerId))
                .build();
    }

    @POST
    @Path("/{customerId}/update")
    public Response updateCustomer(@PathParam("customerId") String customerId, @Valid UpdateCustomerCommand command) {
        customerAggregate.handleUpdateCustomer(customerId, command);
        return Response.noContent().build();
    }

    @POST
    @Path("/{customerId}/delete")
    public Response deleteCustomer(@PathParam("customerId") String customerId) {
        customerAggregate.handleDeleteCustomer(new DeleteCustomerCommand(customerId));
        return Response.noContent().build();
    }

    //When replaying Events:

    @POST
    @Path("/customerCreated")
    public Response customerCreated(CustomerCreatedEvent customerCreatedEvent){
        customerAggregate.customerCreated(customerCreatedEvent);
        return Response.noContent().build();
    }

    @POST
    @Path("/customerUpdated")
    public Response customerUpdated(CustomerUpdatedEvent customerUpdatedEvent){
        customerAggregate.customerUpdated(customerUpdatedEvent);
        return Response.noContent().build();
    }

    @POST
    @Path("/customerDeleted")
    public Response customerDeleted(CustomerDeletedEvent customerDeletedEvent){
        customerAggregate.customerDeleted(customerDeletedEvent);
        return Response.noContent().build();
    }
}