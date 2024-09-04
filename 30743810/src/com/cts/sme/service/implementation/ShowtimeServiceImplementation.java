package com.cts.sme.service.implementation;

import java.util.List;
import java.util.Scanner;

import com.cts.sme.dao.ShowTimeDAO;
import com.cts.sme.dao.implementation.ShowTimeDAOImplementation;
import com.cts.sme.exceptions.InvalidDateTimeFormatException;
import com.cts.sme.exceptions.ShowTimeNotFoundException;
import com.cts.sme.model.Showtime;
import com.cts.sme.service.ShowtimeService;

public class ShowtimeServiceImplementation implements ShowtimeService {
    private ShowTimeDAO showtimeDAO;

    public ShowtimeServiceImplementation() {
        this.showtimeDAO = new ShowTimeDAOImplementation();
    }

    public void addShowtime(Showtime showtime) {
        showtimeDAO.addShowtime(showtime);
    }

    
    public Showtime getShowtimeById(int showtimeId) {
        return showtimeDAO.getShowtimeById(showtimeId);
    }

    public List<Showtime> getAllShowtimes() throws ShowTimeNotFoundException {
        return showtimeDAO.getAllShowtimes();
    }

    public void updateShowtime(Showtime showtime) {
        showtimeDAO.updateShowtime(showtime);
    }

    public void deleteShowtime(int showtimeId) {
        showtimeDAO.deleteShowtime(showtimeId);
    }
    public Showtime getNewShowtime(Scanner input) throws InvalidDateTimeFormatException {
    	return showtimeDAO.getNewShowtime(input);
    }
    public void updateShowTimeById(Scanner input) throws InvalidDateTimeFormatException {
    	showtimeDAO.updateShowTimeById(input);
    }

	public Showtime getShowtimeByMovieId(int movie_id) {
		
		return showtimeDAO.getShowtimeByMovieId(movie_id);
	}
}