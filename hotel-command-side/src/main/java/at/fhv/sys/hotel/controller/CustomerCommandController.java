package at.fhv.sys.hotel.controller;

import at.fhv.sys.hotel.commands.CreateCustomerCommand;
import at.fhv.sys.hotel.commands.CustomerAggregate;
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
        String customerId = customerAggregate.handle(command);
        return Response.status(Response.Status.CREATED)
                .entity(Map.of("customerId", customerId))
                .build();
    }

    @POST
    @Path("/{customerId}/update")
    public String updateCustomer(@PathParam("customerId") String customerId, @QueryParam("email") String email) {
        // TBD: process customer
        return "Customer updated";
    }

    @POST
    @Path("/{customerId}/delete")
    public String deleteCustomer(@PathParam("customerId") String customerId) {
        // TBD: delete customer
        return "Customer deleted";
    }
}