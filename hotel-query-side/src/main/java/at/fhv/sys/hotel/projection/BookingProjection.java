package at.fhv.sys.hotel.projection;

import at.fhv.sys.hotel.commands.shared.events.BookingCanceledEvent;
import at.fhv.sys.hotel.commands.shared.events.RoomBookedEvent;
import at.fhv.sys.hotel.models.BookingQueryPanacheModel;
import at.fhv.sys.hotel.service.BookingServicePanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logmanager.Logger;

@ApplicationScoped
public class BookingProjection {

    @Inject
    BookingServicePanache bookingServicePanache;

    public void processRoomBookedEvent(RoomBookedEvent roomBookedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + roomBookedEvent);

        BookingQueryPanacheModel booking = new BookingQueryPanacheModel();
        booking.bookingId = roomBookedEvent.getBookingId();
        booking.customerId = roomBookedEvent.getCustomerId();
        booking.roomNumber = roomBookedEvent.getRoomNumber();
        booking.fromDate = roomBookedEvent.getFromDate();
        booking.toDate = roomBookedEvent.getToDate();
        booking.status = "BOOKED";

        bookingServicePanache.bookRoom(booking);
    }

    @Transactional
    public void processBookingCanceledEvent(BookingCanceledEvent bookingCanceledEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + bookingCanceledEvent);

        BookingQueryPanacheModel booking = BookingQueryPanacheModel
                .find("bookingId", bookingCanceledEvent.getBookingId())
                .firstResult();

        if (booking != null) {
            booking.status = "CANCELED";
        }

    }
}
