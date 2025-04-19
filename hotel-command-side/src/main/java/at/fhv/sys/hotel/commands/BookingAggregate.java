package at.fhv.sys.hotel.commands;

import at.fhv.sys.hotel.client.EventBusClient;
import at.fhv.sys.hotel.commands.shared.events.BookingCanceledEvent;
import at.fhv.sys.hotel.commands.shared.events.RoomBookedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logmanager.Logger;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookingAggregate {

    @Inject
    @RestClient
    EventBusClient eventClient;

    // Stores all bookings
    private final List<RoomBookedEvent> bookings = new ArrayList<>();

    public String handleBookRoom(BookRoomCommand command) {
        if (command.fromDate().isAfter(command.toDate())) {
            throw new IllegalArgumentException("fromDate needs to be before toDate");
        }

        boolean unavailable = bookings.stream().anyMatch(event ->
                event.getRoomNumber().equals(command.roomNumber()) &&
                !(event.getToDate().isBefore(command.fromDate()) || event.getFromDate().isAfter(command.toDate())));

        if (unavailable) {
            throw new IllegalArgumentException("Room " + command.roomNumber() + " is not available in the selected time period");
        }

        RoomBookedEvent event = new RoomBookedEvent(
                command.bookingId(),
                command.customerId(),
                command.roomNumber(),
                command.fromDate(),
                command.toDate()
        );

        bookings.add(event);

        Logger.getAnonymousLogger().info(eventClient.processRoomBookedEvent(event).toString());

        return command.bookingId();
    }

    public String handleCancelBooking(CancelBookingCommand command) {
        String bookingId = command.bookingId();

        boolean exists = bookings.removeIf(event -> event.getBookingId().equals(bookingId));
        if (!exists) {
            throw new IllegalStateException("No booking found for bookingId: " + bookingId);
        }

        BookingCanceledEvent event = new BookingCanceledEvent(command.bookingId());

        Logger.getAnonymousLogger().info(eventClient.processBookingCanceledEvent(event).toString());

        return command.bookingId();
    }
}
