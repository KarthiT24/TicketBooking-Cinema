package com.cts.sme.dao.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cts.sme.dao.MovieDAO;
import com.cts.sme.dao.ShowTimeDAO;
import com.cts.sme.exceptions.InvalidDateTimeFormatException;
import com.cts.sme.exceptions.MovieNotFoundException;
import com.cts.sme.exceptions.ShowTimeNotFoundException;
import com.cts.sme.model.Showtime;
import com.cts.sme.util.JdbcConnection;

public class ShowTimeDAOImplementation implements ShowTimeDAO {

	@Override
	public void addShowtime(Showtime showtime) {
		String query = "INSERT INTO Showtime (movie_id, start_time, end_time, available_seats, total_seats)"
				+ " VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JdbcConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, showtime.getMovieId());
            stmt.setTime(2, showtime.getStartTime());
            stmt.setTime(3,showtime.getEndTime());
            stmt.setInt(4, showtime.getAvailableSeats());
            stmt.setInt(5, showtime.getTotalSeats());
            stmt.executeUpdate();
            System.out.println("ShowTime added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public Showtime getShowtimeById(int showtimeId) {
		String query = "SELECT * FROM Showtime WHERE showtime_id = ?";
        try (Connection conn = JdbcConnection.getConnection();
        		PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, showtimeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Showtime(
                    rs.getInt("showtime_id"),
                    rs.getInt("movie_id"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time"),
                    rs.getInt("available_seats"),
                    rs.getInt("total_seats")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public List<Showtime> getAllShowtimes() throws ShowTimeNotFoundException {
		 List<Showtime> showtimes = new ArrayList<>();
	        String query = "SELECT * FROM Showtime";
	        try (Connection conn = JdbcConnection.getConnection();
	        		PreparedStatement stmt = conn.prepareStatement(query); 
	        		ResultSet rs = stmt.executeQuery()) {
	        	
	            while (rs.next()) {
	                showtimes.add(new Showtime(
	                    rs.getInt("showtime_id"),
	                    rs.getInt("movie_id"),
	                    rs.getTime("start_time"),
	                    rs.getTime("end_time"),
	                    rs.getInt("available_seats"),
	                    rs.getInt("total_seats")
	                ));
	           
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return showtimes;
	}

	@Override
	public void updateShowtime(Showtime showtime) {
		String query = "UPDATE Showtime SET movie_id = ?, start_time = ?, end_time = ?, "
				+ "available_seats = ?, total_seats = ? WHERE showtime_id = ?";
        try (Connection conn = JdbcConnection.getConnection();
        		PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, showtime.getMovieId());
            stmt.setTime(2, showtime.getStartTime());
            stmt.setTime(3,showtime.getEndTime());
            stmt.setInt(4, showtime.getAvailableSeats());
            stmt.setInt(5, showtime.getTotalSeats());
            stmt.setInt(6, showtime.getShowtimeId());
            stmt.executeUpdate();
            System.out.println("Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteShowtime(int showtimeId) {

		 String query = "DELETE FROM Showtime WHERE showtime_id = ?";
	        try (Connection conn = JdbcConnection.getConnection(); 
	        		PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setInt(1, showtimeId);
	            stmt.executeUpdate();
	            System.out.println("Deleted Successfully..");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	}
	
	public Showtime getNewShowtime(Scanner input) throws InvalidDateTimeFormatException {
		Showtime showtime=new Showtime();
		MovieDAO movieDAO=new MovieDAOImplementation();
		System.out.println("Enter Valid MovieId: ");
		int movieId=input.nextInt();
		input.nextLine();
		try {
			if(movieDAO.getMovieById(movieId) == null)
			{
				throw new MovieNotFoundException("Invalid MovieId...");
				
			}
		} catch (MovieNotFoundException e) {
			e.printStackTrace();
		}
		showtime.setMovieId(movieId);
		try {
		System.out.println("Enter Start Time(HH:MM:SS)");
		String startTime=input.nextLine();
		showtime.setStartTime(Time.valueOf(startTime));
		
		
		System.out.println("Enter End Time(HH:MM:SS)");
		String endTime=input.nextLine();
		showtime.setEndTime(Time.valueOf(endTime));
		}
		catch(IllegalArgumentException e) {
			throw new InvalidDateTimeFormatException("Invalid Time Format");
		}
		System.out.println("Enter Available Seats");
		int seats=input.nextInt();
		showtime.setAvailableSeats(seats);
		
		//Initially AvailableSeats and TotalSeats will be same,So
		showtime.setTotalSeats(seats);
	
		
		return showtime;
}

	
	@Override
	public void updateShowTimeById(Scanner input) throws InvalidDateTimeFormatException {
		ShowTimeDAO showtime=new ShowTimeDAOImplementation();
		System.out.println("Enter Valid ShowTime id to Update: ");
		int shId=input.nextInt(); input.nextLine();
		Showtime show=showtime.getShowtimeById(shId);
		if(show==null) {
			System.out.println("Invalid Show Time id");
			return;
		}
		try {
		System.out.println("Enter Updated/Original Start time(HH:MM:SS)");
		String S_time=input.nextLine();
		Time start_time=Time.valueOf(S_time);
		show.setStartTime(start_time);
		
		System.out.println("Enter Updated/Original End time(HH:MM:SS)");
		String E_time=input.nextLine();
		Time end_time=Time.valueOf(E_time);
		show.setEndTime(end_time);
		}
		catch(IllegalArgumentException e) {
			throw new InvalidDateTimeFormatException("Invalid Time Format...");
		}
		System.out.println("Enter Updated/Original Available Seats");
		int available_seats=input.nextInt();
		input.nextLine();
		show.setAvailableSeats(available_seats);
		
		System.out.println("Enter Updated/Original Total Seats");
		int total_seats=input.nextInt();
		input.nextLine();
		show.setTotalSeats(total_seats);
		
		updateShowtime(show);
		
		
	}

	@Override
	public Showtime getShowtimeByMovieId(int movieId) {
		String query = "SELECT * FROM Showtime WHERE movie_id = ?";
        try (Connection conn = JdbcConnection.getConnection();
        		PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, movieId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Showtime(
                    rs.getInt("showtime_id"),
                    rs.getInt("movie_id"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time"),
                    rs.getInt("available_seats"),
                    rs.getInt("total_seats")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

}