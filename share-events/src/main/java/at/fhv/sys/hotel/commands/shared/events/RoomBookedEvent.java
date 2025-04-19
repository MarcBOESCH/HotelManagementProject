package at.fhv.sys.hotel.commands.shared.events;

import java.time.LocalDate;

public class RoomBookedEvent {
    private String bookingId;
    private String customerId;
    private String roomNumber;
    private LocalDate fromDate;
    private LocalDate toDate;

    public RoomBookedEvent() {
    }

    public RoomBookedEvent(String bookingId, String customerId, String roomNumber, LocalDate fromDate, LocalDate toDate) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.roomNumber = roomNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "RoomBookedEvent{" +
                "reservationId='" + bookingId + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", customerId='" + customerId + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}

