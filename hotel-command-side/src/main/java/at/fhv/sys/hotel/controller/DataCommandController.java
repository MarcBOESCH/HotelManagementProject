package at.fhv.sys.hotel.controller;

import at.fhv.sys.hotel.commands.BookingAggregate;
import at.fhv.sys.hotel.commands.CustomerAggregate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.io.Reader;

@Path("/api")
@ApplicationScoped
public class DataCommandController {

    @Inject
    BookingAggregate bookingAggregate;

    @Inject
    CustomerAggregate customerAggregate;

    @DELETE
    @Path("/command-models")
    public Response deleteCommandModels() {
        bookingAggregate.clear();
        customerAggregate.clear();
        return Response.noContent().build();
    }
}
