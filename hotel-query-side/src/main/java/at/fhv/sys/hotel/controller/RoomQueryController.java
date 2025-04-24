package at.fhv.sys.hotel.controller;

import at.fhv.sys.hotel.commands.shared.events.RoomCreatedEvent;
import at.fhv.sys.hotel.models.BookingQueryPanacheModel;
import at.fhv.sys.hotel.models.RoomQueryPanacheModel;
import at.fhv.sys.hotel.projection.RoomProjection;
import at.fhv.sys.hotel.service.BookingServicePanache;
import at.fhv.sys.hotel.service.RoomServicePanache;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.Reader;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomQueryController {

    @Inject
    RoomProjection roomProjection;

    @Inject
    RoomServicePanache roomService;

    @Inject
    BookingServicePanache bookingService;

    @POST
    @Path("/roomCreated")
    public Response roomCreated(RoomCreatedEvent event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        roomProjection.processRoomCreatedEvent(event);
        return Response.ok(event).build();
    }

    @GET
    @Path("/getAllRooms")
    public List<RoomQueryPanacheModel> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GET
    @Path("/getFreeRooms")
    public List<RoomQueryPanacheModel> getFreeRooms(
            @QueryParam("from") String fromStr,
            @QueryParam("to") String toStr,
            @QueryParam("guests") int guests
    ) {
        LocalDate from = LocalDate.parse(fromStr);
        LocalDate to = LocalDate.parse(toStr);

        // Get all bookings in given time period
        List<BookingQueryPanacheModel> bookings = bookingService.getBookingsByDate(from, to);

        // Filter, which room numbers are occupied in the given time period
        Set<String> occupiedRoomNumbers = bookings.stream().map(b -> b.roomNumber).collect(Collectors.toSet());

        // Get all rooms and filter
        return roomService.getAllRooms().stream().filter(room -> room.capacity >= guests && !occupiedRoomNumbers.contains(room.roomNumber)).collect(Collectors.toList());
    }
}
