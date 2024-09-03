package com.cts.sme.dao.implementation;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cts.sme.dao.MovieDAO;
import com.cts.sme.exceptions.InvalidDateTimeFormatException;
import com.cts.sme.exceptions.MovieNotFoundException;
import com.cts.sme.model.Movie;
import com.cts.sme.util.JdbcConnection;

public class MovieDAOImplementation implements MovieDAO{


    @Override
    public void addMovie(Movie movie) {
        String query = "INSERT INTO Movie (title, genre, duration, release_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = JdbcConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setInt(3, movie.getDuration());
            stmt.setDate(4, movie.getReleaseDate());
            stmt.executeUpdate();
            System.out.println("Movie Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Movie getMovieById(int movieId) {
        String query = "SELECT * FROM Movie WHERE movie_id = ?";
        try (Connection conn = JdbcConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, movieId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Movie(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getInt("duration"),
                    rs.getDate("release_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM Movie";
        try (Connection conn = JdbcConnection.getConnection(); 
        		PreparedStatement stmt = conn.prepareStatement(query); 
        		ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                movies.add(new Movie(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getInt("duration"),
                    rs.getDate("release_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public void updateMovie(Movie movie) {
        String query = "UPDATE Movie SET title = ?, genre = ?, duration = ?, release_date = ? WHERE movie_id = ?";
        try (Connection conn = JdbcConnection.getConnection(); 
        		PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setInt(3, movie.getDuration());
            stmt.setDate(4, new java.sql.Date(movie.getReleaseDate().getTime()));
            stmt.setInt(5, movie.getMovieId());
            stmt.executeUpdate();
            System.out.println("Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMovie(int movieId) {
    	String query1 = "DELETE FROM Movie WHERE movie_id = ?;";
        String query2= "DELETE FROM Showtime WHERE movie_id = ?;";
        
        
        try (Connection conn = JdbcConnection.getConnection();
        		PreparedStatement stmt1 = conn.prepareStatement(query1);
        		PreparedStatement stmt2=conn.prepareStatement(query2);
        		) {
            stmt1.setInt(1, movieId);
            stmt2.setInt(1,movieId);
            stmt2.executeUpdate();
            stmt1.executeUpdate();
            System.out.println("Deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public void updateMovieById(Scanner input) throws MovieNotFoundException ,InvalidDateTimeFormatException{
		 MovieDAO movieDAO=new MovieDAOImplementation();
		 
		 System.out.println("Enter Movie ID to Update: ");
		 int id1=input.nextInt();input.nextLine();
		 
		 Movie movie=movieDAO.getMovieById(id1);
		 if(movie==null) {
			 throw new MovieNotFoundException("Movie Not Found...");
		 }
		 System.out.println("Enter Updated/Original Title :");
		 String title=input.nextLine();
		 movie.setTitle(title);
		 
		 System.out.println("Enter Updated/Original Genre :");
		 String genre=input.nextLine();
		 movie.setGenre(genre);
		 
		 System.out.println("Enter Updated/Orginal Duration");
		 int duration=input.nextInt(); input.nextLine();
		 movie.setDuration(duration);
		 
		 System.out.println("Enter Updated/Original release_date(YYYY-MM-DD)");
		 Date date=Date.valueOf(input.nextLine());
		 movie.setReleaseDate(date);
		 
		 movieDAO.updateMovie(movie);
	}
    
    public Movie getNewMovie(Scanner input) throws InvalidDateTimeFormatException {
    	 Movie movie=new Movie(); 
		 
		 System.out.println("Enter title: ");
		 String title=input.nextLine();
		 movie.setTitle(title);
		 
		 System.out.println("Enter Genre: ");
		 String genre=input.nextLine();
		 movie.setGenre(genre);
		 
		 System.out.println("Enter Duration (in minutes)");
		 int duration=input.nextInt();
		 movie.setDuration(duration);
		 input.nextLine();
		 
		 System.out.println("Enter release Date (YYYY-MM-DD)");
		 String date=input.nextLine();
		 movie.setReleaseDate(Date.valueOf(date));
		 
		 return movie;
	 }
}