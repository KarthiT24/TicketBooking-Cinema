package com.cts.sme.service.implementation;

import java.util.List;
import java.util.Scanner;

import com.cts.sme.dao.MovieDAO;
import com.cts.sme.dao.implementation.MovieDAOImplementation;
import com.cts.sme.exceptions.InvalidDateTimeFormatException;
import com.cts.sme.exceptions.MovieNotFoundException;
import com.cts.sme.model.Movie;
import com.cts.sme.service.MovieService;

public class MovieServiceImplementation implements MovieService{
	
	    private MovieDAO movieDAO;

	    public MovieServiceImplementation() {
	        this.movieDAO = new MovieDAOImplementation();
	    }

	    
	    public void addMovie(Movie movie) {
	        movieDAO.addMovie(movie);
	    }

	    public Movie getMovieById(int movieId) {
	        return movieDAO.getMovieById(movieId);
	    }

	    public List<Movie> getAllMovies() {
	        return movieDAO.getAllMovies();
	    }

	   
	    public void updateMovie(Movie movie) {
	        movieDAO.updateMovie(movie);
	    }

	    public Movie getNewMovie(Scanner input) throws InvalidDateTimeFormatException {
	    	return movieDAO.getNewMovie(input);
	    }
	  
	    public void deleteMovie(int movieId) {
	        movieDAO.deleteMovie(movieId);
	    }
	    public void updateMovieById(Scanner input) throws MovieNotFoundException, InvalidDateTimeFormatException {
	    	movieDAO.updateMovieById(input);
	    }
}


