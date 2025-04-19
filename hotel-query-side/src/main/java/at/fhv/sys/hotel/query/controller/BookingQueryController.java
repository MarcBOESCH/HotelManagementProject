package at.fhv.sys.hotel.query.controller;

import at.fhv.sys.hotel.commands.shared.events.RoomBookedEvent;
import at.fhv.sys.hotel.models.BookingQueryPanacheModel;
import at.fhv.sys.hotel.projection.BookingProjection;
import at.fhv.sys.hotel.service.BookingServicePanache;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingQueryController {

    @Inject
    BookingProjection bookingProjection;

    @Inject
    BookingServicePanache bookingService;

    public BookingQueryController() {
    }

    @POST
    @Path("/roomBooked")
    public Response roomBooked(RoomBookedEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        bookingProjection.processRoomBookedEvent(event);
        return Response.ok(event).build();
    }

    @GET
    @Path("/getBookingsByDate")
    public List<BookingQueryPanacheModel> getBookingsByDate(@QueryParam("from") String fromStr, @QueryParam("to") String toStr) {
        LocalDate from = LocalDate.parse(fromStr);
        LocalDate to = LocalDate.parse(toStr);
        return bookingService.getBookingsByDate(from, to);
    }

}
