package com.cts.sme.dao;


import java.util.List;
import java.util.Scanner;

import com.cts.sme.exceptions.InvalidDateTimeFormatException;
import com.cts.sme.exceptions.MovieNotFoundException;
import com.cts.sme.exceptions.ShowTimeNotFoundException;
import com.cts.sme.model.Showtime;

public interface ShowTimeDAO {
	//add new Showtime
    public void addShowtime(Showtime showtime);
    
    //get ShowTime by showTimeId
    public Showtime getShowtimeById(int showtimeId);
    
    //get Showtime details by movieId for booking
    public Showtime getShowtimeByMovieId(int movieId);
    
    
    //get List of Available Showtimes
    public List<Showtime> getAllShowtimes() throws ShowTimeNotFoundException;
    
    //update Showtime
    public void updateShowtime(Showtime showtime);
    
    //delete Showtime
    public void deleteShowtime(int showtimeId);
    
    //Get new Showtime for adding
    public Showtime getNewShowtime(Scanner input) throws InvalidDateTimeFormatException, MovieNotFoundException;
    
    //for updating Showtime by Id
    public void updateShowTimeById(Scanner input) throws InvalidDateTimeFormatException;
}
