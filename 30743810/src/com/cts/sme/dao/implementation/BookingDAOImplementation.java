package com.cts.sme.dao.implementation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cts.sme.dao.BookingDAO;
import com.cts.sme.exceptions.BookingNotFoundException;
import com.cts.sme.model.Booking;
import com.cts.sme.model.Showtime;
import com.cts.sme.util.JdbcConnection;
public class BookingDAOImplementation implements BookingDAO {

	@Override
	public void addBooking(Booking booking) {
		String query = "INSERT INTO Booking (showtime_id, user_id, booking_date, total_tickets, total_price) "
				+ "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JdbcConnection.getConnection();
        		PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, booking.getShowtimeId());
            stmt.setInt(2, booking.getUserId());
            stmt.setDate(3, booking.getBookingDate());
            stmt.setInt(4, booking.getTotalTickets());
            stmt.setDouble(5, booking.getTotalPrice());
            stmt.executeUpdate();
            System.out.println("Booking Completed!\n"+booking.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public Booking getBookingById(int bookingId) {
		String query = "SELECT * FROM Booking WHERE booking_id = ?";
        try (Connection conn = JdbcConnection.getConnection(); 
        		PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("showtime_id"),
                    rs.getInt("user_id"),
                    rs.getDate("booking_date"),
                    rs.getInt("total_tickets"),
                    rs.getDouble("total_price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	

	@Override
	public List<Booking> getAllBookings() {
		 List<Booking> bookings = new ArrayList<>();
	        String query = "SELECT * FROM Booking";
	        try (Connection conn = JdbcConnection.getConnection(); 
	        		PreparedStatement stmt = conn.prepareStatement(query); 
	        		ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                bookings.add(new Booking(
	                    rs.getInt("booking_id"),
	                    rs.getInt("showtime_id"),
	                    rs.getInt("user_id"),
	                    rs.getDate("booking_date"),
	                    rs.getInt("total_tickets"),
	                    rs.getDouble("total_price")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return bookings;
	}



	@Override
	public void deleteBooking(int bookingId) {
		 String query = "DELETE FROM Booking WHERE booking_id = ?";
	        try (Connection conn = JdbcConnection.getConnection();
	        		PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setInt(1, bookingId);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public List<Showtime> getShowTimings(int movie_id) {
		String query="SELECT * FROM Showtime s where movie_id= ?";
		try(Connection conn=JdbcConnection.getConnection();
				PreparedStatement stmt=conn.prepareStatement(query)){
			stmt.setInt(1, movie_id);
			ResultSet rs=stmt.executeQuery();
			List<Showtime> list=new ArrayList<>();
			while(rs.next()) {
				Showtime showtime = new Showtime();
                       showtime.setShowtimeId(rs.getInt("showtime_id"));
                        showtime.setStartTime(rs.getTime("start_time"));
                        showtime.setStartTime(rs.getTime("end_time"));
                        showtime.setShowtimeId(rs.getInt("available_seats"));
                        showtime.setShowtimeId(rs.getInt("total_seats"));
                list.add(showtime);          
			}
			return list;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void cancelBooking(int bookingId) throws BookingNotFoundException {
		Booking booking = getBookingById(bookingId);
		
        if (booking != null) {
            refund(booking);
        	deleteBooking(bookingId);
            System.out.println("Booking canceled successfully.");
        } else {
           throw new BookingNotFoundException("Booking not found.");
        }
	}

	private void refund(Booking booking) {
		double refundAmount=booking.getTotalPrice();
		System.out.println("Refund Amount: $"+refundAmount+" will be tranferred to your Account");
		
	}

}
