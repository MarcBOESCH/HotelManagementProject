package at.fhv.sys.hotel.projection;

import at.fhv.sys.hotel.commands.shared.events.BookingCanceledEvent;
import at.fhv.sys.hotel.commands.shared.events.BookingPaidEvent;
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
        if (booking.bookingId == null || booking.bookingId.trim().isEmpty()) {
            throw new IllegalArgumentException("BookingID is null");
        }
        booking.customerId = roomBookedEvent.getCustomerId();
        if (booking.customerId == null || booking.customerId.trim().isEmpty()){
            throw new IllegalArgumentException("CustomerID is null");
        }
        booking.roomNumber = roomBookedEvent.getRoomNumber();
        if (booking.roomNumber == null || booking.roomNumber.trim().isEmpty()){
            throw new IllegalArgumentException("Room Number is null");
        }
        booking.fromDate = roomBookedEvent.getFromDate();
        if (booking.fromDate == null){
            throw new IllegalArgumentException("From Date is null");
        }
        booking.toDate = roomBookedEvent.getToDate();
        if (booking.toDate == null){
            throw new IllegalArgumentException("To Date is null");
        }
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

    @Transactional
    public void processBookingPaidEvent(BookingPaidEvent bookingPaidEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + bookingPaidEvent);
        BookingQueryPanacheModel booking = BookingQueryPanacheModel
                .find("bookingId", bookingPaidEvent.getBookingId())
                .firstResult();

        if (booking != null) {
            booking.status = "PAID";
        }
    }
}
