package com.cts.sme.service;

import java.util.List;

import com.cts.sme.exceptions.BookingNotFoundException;
import com.cts.sme.model.Booking;
import com.cts.sme.model.Showtime;

public interface BookingService {
	public void addBooking(Booking booking);
	public Booking getBookingById(int bookingId);
	public List<Booking> getAllBookings();
	public void deleteBooking(int bookingId);
	public double calculateTotalPrice(int showtimeId, int numberOfTickets);
	public List<Showtime> getShowTimings(int movieId);
	public void cancelBooking(int bookingId) throws BookingNotFoundException;
}
