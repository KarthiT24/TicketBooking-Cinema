package com.cts.sme.service.implementation;

import java.util.List;

import com.cts.sme.dao.BookingDAO;
import com.cts.sme.dao.implementation.BookingDAOImplementation;
import com.cts.sme.dao.implementation.ShowTimeDAOImplementation;
import com.cts.sme.exceptions.BookingNotFoundException;
import com.cts.sme.model.Booking;
import com.cts.sme.model.Showtime;
import com.cts.sme.service.BookingService;

public class BookingServiceImplementation implements BookingService{
	private BookingDAO bookingDAO;

    public BookingServiceImplementation() {
        this.bookingDAO = new BookingDAOImplementation();
    }

    public void addBooking(Booking booking) {
        bookingDAO.addBooking(booking);
    }


    public Booking getBookingById(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }

 
    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }


   
    public void deleteBooking(int bookingId) {
        bookingDAO.deleteBooking(bookingId);
    }
   

    public double calculateTotalPrice(int showtimeId, int numberOfTickets) {
        final double ticketPrice = 150.0;

        Showtime showtime = new ShowTimeDAOImplementation().getShowtimeById(showtimeId);
        if (showtime != null && numberOfTickets <= showtime.getAvailableSeats()) {
            return ticketPrice * numberOfTickets;
        } else {
            throw new IllegalArgumentException("Invalid showtime or number of tickets exceeds available seats.");
        }
    }
    
    public List<Showtime> getShowTimings(int movieId){
    	return bookingDAO.getShowTimings(movieId);
    }
    
    public void cancelBooking(int bookingId) throws BookingNotFoundException {
    	bookingDAO.cancelBooking(bookingId);
    }
}
