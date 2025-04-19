package at.fhv.sys.hotel.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class BookingQueryPanacheModel extends PanacheEntity {
    public String bookingId;
    public String customerId;
    public String roomNumber;
    public LocalDate fromDate;
    public LocalDate toDate;
    public String status;

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
