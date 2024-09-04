package com.cts.sme.service;

import java.util.List;
import java.util.Scanner;

import com.cts.sme.exceptions.InvalidDateTimeFormatException;
import com.cts.sme.exceptions.MovieNotFoundException;
import com.cts.sme.model.Movie;

public interface MovieService {

	public void addMovie(Movie movie);	
	public Movie getMovieById(int movieId) throws MovieNotFoundException;
	public List<Movie> getAllMovies();
	public void updateMovie(Movie movie);
	public Movie getNewMovie(Scanner input) throws InvalidDateTimeFormatException;
	public void deleteMovie(int movieId);
	public void updateMovieById(Scanner input) throws MovieNotFoundException, InvalidDateTimeFormatException;
}
