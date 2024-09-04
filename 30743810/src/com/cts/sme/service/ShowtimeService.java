package com.cts.sme.service;

import java.util.List;
import java.util.Scanner;

import com.cts.sme.exceptions.InvalidDateTimeFormatException;
import com.cts.sme.exceptions.MovieNotFoundException;
import com.cts.sme.exceptions.ShowTimeNotFoundException;
import com.cts.sme.model.Showtime;

public interface ShowtimeService {
	//add showtime
	public void addShowtime(Showtime showtime) ;
	
	//get showTime by id
	public Showtime getShowtimeById(int showtimeId);
	
	//get list Of Showtimes
	public List<Showtime> getAllShowtimes() throws ShowTimeNotFoundException;
	
	//update Showtime
	public void updateShowtime(Showtime showtime);
	
	//delete showtime
	public void deleteShowtime(int showtimeId);
	
	public Showtime getNewShowtime(Scanner input) throws InvalidDateTimeFormatException, MovieNotFoundException;
	public void updateShowTimeById(Scanner input) throws InvalidDateTimeFormatException;
}
