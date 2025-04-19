package at.fhv.sys.hotel.service;

import at.fhv.sys.hotel.models.BookingQueryPanacheModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class BookingServicePanache {

    public List<BookingQueryPanacheModel> getBookingsByDate(LocalDate from, LocalDate to) {
        return BookingQueryPanacheModel.find("fromDate >= ?1 and toDate <= ?2", from, to).list();
    }

    public BookingQueryPanacheModel getBookingById(String bookingId) {
        return BookingQueryPanacheModel.<BookingQueryPanacheModel>find("bookingId", bookingId).firstResult();
    }

    @Transactional
    public void bookRoom(BookingQueryPanacheModel booking) {
        booking.persist();
    }
}
