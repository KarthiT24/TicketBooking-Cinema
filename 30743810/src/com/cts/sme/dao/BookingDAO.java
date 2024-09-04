package com.cts.sme.dao;

import java.util.List;

import com.cts.sme.exceptions.BookingNotFoundException;
import com.cts.sme.model.Booking;
import com.cts.sme.model.Showtime;

public interface BookingDAO {
    void addBooking(Booking booking);
    Booking getBookingById(int bookingId);
    List<Booking> getAllBookings();
    void deleteBooking(int bookingId);
    List<Showtime> getShowTimings(int movie_id);
    void cancelBooking(int bookingId) throws BookingNotFoundException;
}
