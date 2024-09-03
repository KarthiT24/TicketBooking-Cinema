package com.cts.sme.dao;


import java.util.List;
import java.util.Scanner;

import com.cts.sme.exceptions.InvalidDateTimeFormatException;
import com.cts.sme.exceptions.ShowTimeNotFoundException;
import com.cts.sme.model.Showtime;

public interface ShowTimeDAO {
	
    public void addShowtime(Showtime showtime);
    
    public Showtime getShowtimeById(int showtimeId);
    
    public List<Showtime> getAllShowtimes() throws ShowTimeNotFoundException;
    
    public void updateShowtime(Showtime showtime);
    
    public void deleteShowtime(int showtimeId);
    
    public Showtime getNewShowtime(Scanner input) throws InvalidDateTimeFormatException;
    
    public void updateShowTimeById(Scanner input) throws InvalidDateTimeFormatException;
}
