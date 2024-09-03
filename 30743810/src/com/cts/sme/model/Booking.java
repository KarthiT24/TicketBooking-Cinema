package com.cts.sme.model;

import java.sql.Date;

public class Booking {
    private int bookingId;
    private int showtimeId;
    private int userId;
    private Date bookingDate;
    private int totalTickets;
    private double totalPrice;

    // Parameterized constructor
    public Booking(int bookingId, int showtimeId, int userId, Date bookingDate, int totalTickets, double totalPrice) {
        this.bookingId = bookingId;
        this.showtimeId = showtimeId;
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.totalTickets = totalTickets;
        this.totalPrice = totalPrice;
    }

    public Booking() {
		
	}

	// Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return 
                bookingId +
                "|" + showtimeId +
                "|" + userId +
                "|" + bookingDate +
                "|" + totalTickets +
                "|" + totalPrice ;
    }
    
    public String getHeaders() {
    	return  "|bookingId | showtimeId | userId | bookingDate | totalTickets | totalPrice |";
    }
}
