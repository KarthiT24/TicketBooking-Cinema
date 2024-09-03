package com.cts.sme.model;

import java.sql.Time;


public class Showtime {
    private int showtimeId;
    private int movieId;
    private Time startTime;
    private Time endTime;
    private int availableSeats;
    private int totalSeats;

    // Parameterized constructor
    public Showtime(int showtimeId, int movieId, Time startTime, Time endTime, int availableSeats, int totalSeats) {
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSeats = availableSeats;
        this.totalSeats = totalSeats;
    }

    public Showtime() {
		
	}

	// Getters and Setters
    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    @Override
    public String toString() {
        return "|"+showtimeId + "|"+
                 + movieId +
                "|" + startTime +
                "|" + endTime +
                "|" + availableSeats +
                "|" + totalSeats +"|";
    }
    public String getHeaders() {
        return "| showtimeId | movieId | startTime | endTime | availableSeats | totalSeats |";
    }
}
