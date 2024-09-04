package com.cts.sme.dao;

import java.util.List;

import com.cts.sme.exceptions.BookingNotFoundException;
import com.cts.sme.model.Booking;
import com.cts.sme.model.Showtime;

public interface BookingDAO {
	//add a new Booking 
    void addBooking(Booking booking);
    
    //get booking by id
    Booking getBookingById(int bookingId);
    
    //get List Of Bookings if available
    List<Booking> getAllBookings();
    
    //delete booking by id
    void deleteBooking(int bookingId);
    
    //get List Of ShowTimings if available
    List<Showtime> getShowTimings(int movie_id);
    
    //Cancel booking
    void cancelBooking(int bookingId) throws BookingNotFoundException;
}
