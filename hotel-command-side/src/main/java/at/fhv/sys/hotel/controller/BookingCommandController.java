package at.fhv.sys.hotel.controller;

import at.fhv.sys.hotel.commands.BookRoomCommand;
import at.fhv.sys.hotel.commands.BookingAggregate;
import at.fhv.sys.hotel.commands.CancelBookingCommand;
import at.fhv.sys.hotel.commands.PayBookingCommand;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
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

    @POST
    @Path("/{bookingId}/cancel")
    public Response cancelBooking(@PathParam("bookingId") String bookingId) {
        bookingAggregate.handleCancelBooking(new CancelBookingCommand(bookingId));
        return Response.noContent().build();
    }

    @POST
    @Path("/{bookingId}/pay")
    public Response payBooking(@PathParam("bookingId") String bookingId, @Valid PayBookingCommand command) {
        bookingAggregate.handlePayBooking(bookingId, command);
        return Response.noContent().build();
    }
}
