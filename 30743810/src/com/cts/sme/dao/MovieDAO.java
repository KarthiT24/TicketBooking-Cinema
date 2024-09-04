package com.cts.sme.dao;

import java.util.List;
import java.util.Scanner;

import com.cts.sme.exceptions.InvalidDateTimeFormatException;
import com.cts.sme.exceptions.MovieNotFoundException;
import com.cts.sme.model.Movie;

public interface MovieDAO {
	//Add a new Movie
    public void addMovie(Movie movie);
    
    //Get Movie by id
    public Movie getMovieById(int movieId) throws MovieNotFoundException;
    
    //Get List of Available Movies
    public List<Movie> getAllMovies();
    
    //Updating movie informations
    public void updateMovie(Movie movie);
    
    //Delete movie by Id
    public void deleteMovie(int movieId);
    
    //Update movie by specific Id
    public void updateMovieById(Scanner input) throws MovieNotFoundException, InvalidDateTimeFormatException;
    
    //Get new Movie for adding
    public Movie getNewMovie(Scanner input) throws InvalidDateTimeFormatException;
}
