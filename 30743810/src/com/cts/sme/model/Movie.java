package com.cts.sme.model;

import java.sql.Date;

public class Movie {
	    private int movieId;
	    private String title;
	    private String genre;
	    private int duration; // in minutes
	    private Date releaseDate;
	    
	    public Movie(){
	    	
	    }
	    
	    // Parameterized constructor
	    public Movie(int movieId, String title, String genre, int duration, Date releaseDate) {
	        this.movieId = movieId;
	        this.title = title;
	        this.genre = genre;
	        this.duration = duration;
	        this.releaseDate = releaseDate;
	    }

	    // Getters and Setters
	    public int getMovieId() {
	        return movieId;
	    }

	    public void setMovieId(int movieId) {
	        this.movieId = movieId;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getGenre() {
	        return genre;
	    }

	    public void setGenre(String genre) {
	        this.genre = genre;
	    }

	    public int getDuration() {
	        return duration;
	    }

	    public void setDuration(int duration) {
	        this.duration = duration;
	    }

	    public Date getReleaseDate() {
	        return releaseDate;
	    }

	    public void setReleaseDate(Date releaseDate) {
	        this.releaseDate = releaseDate;
	    }
	    
	    @Override
	    public String toString() {
	        return String.format("| %-8s | %-20s | %-10s | %-8s | %-12s |", 
	                             movieId, title, genre, duration, releaseDate);
	    }

	    public String getHeaders() {
	        return String.format("| %-8s | %-20s | %-10s | %-8s | %-12s |", 
	                             "movieId", "title", "genre", "duration", "releaseDate");
	    }

	}


