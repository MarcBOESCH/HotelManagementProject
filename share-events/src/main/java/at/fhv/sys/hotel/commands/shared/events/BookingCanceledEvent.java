package at.fhv.sys.hotel.commands.shared.events;

public class BookingCanceledEvent {

    private String bookingId;

    public BookingCanceledEvent() {}

    public BookingCanceledEvent(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    // TODO Override equals() and hashCode()

    @Override
    public String toString() {
        return "BookingCanceledEvent{bookingId= '" + bookingId + "'}";
    }
}
