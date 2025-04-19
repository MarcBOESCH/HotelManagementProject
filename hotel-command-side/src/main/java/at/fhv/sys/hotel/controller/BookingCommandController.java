package at.fhv.sys.hotel.controller;

import at.fhv.sys.hotel.commands.BookRoomCommand;
import at.fhv.sys.hotel.commands.BookingAggregate;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingCommandController {

    private final BookingAggregate bookingAggregate;

    public BookingCommandController(BookingAggregate bookingAggregate) {
        this.bookingAggregate = bookingAggregate;
    }

    @POST
    @Path("/bookRoom")
    public Response bookRoom(@Valid BookRoomCommand command) {
        String bookingId = bookingAggregate.handleBookRoom(command);
        return Response.status(Response.Status.CREATED).entity(Map.of("bookingId", bookingId)).build();
    }
}
