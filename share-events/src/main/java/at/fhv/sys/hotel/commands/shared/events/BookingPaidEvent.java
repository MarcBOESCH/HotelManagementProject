package at.fhv.sys.hotel.commands.shared.events;

import java.time.LocalDateTime;
import java.util.Objects;

public class BookingPaidEvent {

    private String paymentId;
    private String bookingId;
    private LocalDateTime paidAt;
    private String paymentMethod;
    private String amount;

    public BookingPaidEvent() {}

    public BookingPaidEvent(String paymentId, String bookingId, LocalDateTime paidAt, String paymentMethod, String amount) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.paidAt = paidAt;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BookingPaidEvent{" +
                "paymentId='" + paymentId + '\'' +
                "bookingId='" + bookingId + '\'' +
                ", paidAt=" + paidAt +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
