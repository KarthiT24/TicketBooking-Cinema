package com.cts.sme.users;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.cts.sme.exceptions.BookingNotFoundException;
import com.cts.sme.exceptions.InvalidCredentialException;
import com.cts.sme.exceptions.MovieNotFoundException;
import com.cts.sme.exceptions.ShowTimeNotFoundException;
import com.cts.sme.model.Booking;
import com.cts.sme.model.Movie;
import com.cts.sme.model.Showtime;
import com.cts.sme.service.implementation.BookingServiceImplementation;
import com.cts.sme.service.implementation.MovieServiceImplementation;
import com.cts.sme.service.implementation.ShowtimeServiceImplementation;
import com.cts.sme.util.JdbcConnection;

public class User {
	String userName;
	int userId;
	String password;
	String email;
	String phone_number;
	MovieServiceImplementation movieServiceImplementation = new MovieServiceImplementation();
	ShowtimeServiceImplementation showTime = new ShowtimeServiceImplementation();
	BookingServiceImplementation bookingServiceImplementation = new BookingServiceImplementation();
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	 @Override
	public String toString() {
		return "UserName=" + userName + "\nUserId=" + userId + "\npassword=" + password + "\nemail=" + email
				+ "\nphone_number=" + phone_number + "\n";
	}
	public User login(String username, String password) throws InvalidCredentialException{
			User user=null;
	        try (Connection conn = JdbcConnection.getConnection()) {
	            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, username);
	            stmt.setString(2, password);
	            ResultSet rs = stmt.executeQuery();
	            user=new User();
	            if(rs.next()) {
	            user.setUserId(rs.getInt(1));
	            user.setUserName(rs.getString(2));
	            user.setPassword(rs.getString(3));
	            user.setEmail(rs.getString(4));
	            user.setPhone_number(rs.getString(5));
	            }
	            // if no user found
	            else
	            	throw new InvalidCredentialException("No user Found"); 
	            
	            
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return user;
	   }
	
	public void main(Scanner input) throws InvalidCredentialException, BookingNotFoundException, ShowTimeNotFoundException, MovieNotFoundException {
		 
		 System.out.println("Enter Login Credentials");
		 System.out.print("User_name: ");
		 String name=input.nextLine();
		 System.out.print("Password :");
		 String password=input.nextLine();
		 User current_user=login(name,password);
		 if(current_user!=null) {
			 System.out.println("Welcome "+name);
			 operations(current_user,input);
		 }
		 else {
			 System.out.println("Invalid Credentials");
			 return;
		 }
	 }
	 
	public void signup(Scanner input) {
		 try (Connection conn = JdbcConnection.getConnection()) {
			 	System.out.println("Enter SignUp Credentials");
			 	System.out.print("User_name: ");
			 	String name=input.nextLine();
			 	System.out.print("Password :");
			 	String password=input.nextLine();
			 	System.out.println("Email :");
			 	String mail=input.nextLine();
			 	System.out.println("Phone No: ");
			 	String phone_number=input.next();
			 	
			 	
	            String query = "INSERT INTO user (username, password,email,phone) VALUES (?,?,?,?)";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, name);
	            stmt.setString(2, password);
	            stmt.setString(3, mail);
	            stmt.setString(4, phone_number);
	            stmt.executeUpdate();
	            System.out.println("Signup completed successfully");
	            
	        }
		 	catch (SQLException e) {
	            e.printStackTrace();
	        }
		 
		
	}
	
	public void operations(User current_user,Scanner input) throws BookingNotFoundException, ShowTimeNotFoundException, MovieNotFoundException {

		List<Movie> movies;

		 List<Booking> bookings;
		 int user_id=current_user.getUserId();
		 while(true) {
			System.out.println("-------Menu-------\n"
					+ "1.View All Movies\n"
					+ "2.Get Movie Details by Id\n"
					+ "3.Book Tickets\n"
					+ "4.Cancel Booking\n"
					+ "5.Get All Bookings\n"
					+ "6.Exit\n"
					+ "Enter your choice: ");
			int choice=input.nextInt();
			input.nextLine();
			switch(choice) {
			case 1:
				movies=movieServiceImplementation.getAllMovies();
				printMovies(movies);
				break;
				
			case 2: //movie details by id for showtime availability
				movies=movieServiceImplementation.getAllMovies();
				printMovies(movies);
				System.out.println("Enter MovieId: ");
				int movie_id=input.nextInt();
				input.nextLine();
				Showtime movieAvail=showTime.getShowtimeById(movie_id);
				if(movieAvail==null) 
					throw new ShowTimeNotFoundException("No ShowTimes Available for the Movie..");
				else
				System.out.println(movieAvail.toString());
				break;
				
			case 3://booking
				movies=movieServiceImplementation.getAllMovies();
				printMovies(movies);
				
				Booking booking=GetBooking(input,user_id);
				if(booking!=null)
				bookingServiceImplementation.addBooking(booking);
				break;
				
			case 4://Cancel Booking by bookingId
				System.out.println("Enter Booking Id");
				int booked_id=input.nextInt();
				bookingServiceImplementation.cancelBooking(booked_id);
				break;
				
			case 5://get all bookings
				bookings=bookingServiceImplementation.getAllBookings();
				printBookings(bookings);
				break;
				
			case 6://exit to main menu
				return;
			
			default:
				System.out.println("Invalid Choice..");
				break;
				
			}
		 }
	 }
	
	private void printShowtimes(List<Showtime> showTimes) {
		System.out.println("\n----------List of ShowTimes-----------");
		Showtime header=new Showtime();
		System.out.println(header.getHeaders());
		for(Showtime sh:showTimes)
			System.out.println(sh.toString());
		System.out.println("----------End of List-----------\n");
		
		
	}
	private Booking GetBooking(Scanner input, int user_id) throws ShowTimeNotFoundException {
		Booking booking = new Booking();
		booking.setUserId(user_id);
		List<Showtime> showtimes=showTime.getAllShowtimes();
		printShowtimes(showtimes);
		System.out.println("Enter ShowtimeId :");
		int shId=input.nextInt();
		input.nextLine();
		booking.setShowtimeId(shId);
		Showtime showtime=showTime.getShowtimeById(shId);
		if(showtime==null) {
			throw new ShowTimeNotFoundException("Invalid ShowTime Id..");
		}
		
		System.out.println("Enter NoOfTickets: ");
		int noOfTickets=input.nextInt();
		input.nextLine();
		booking.setTotalTickets(noOfTickets);
		
		long millis=System.currentTimeMillis();  //for current bookingDate
        Date date=new Date(millis);
        booking.setBookingDate(date);
        
		double total_price=bookingServiceImplementation.calculateTotalPrice(shId, noOfTickets);
		booking.setTotalPrice(total_price);
		
		return booking;
	}
	private void printMovies(List<Movie> movies) {
		System.out.println("\n--------List Of Movies------------");
		Movie header=new Movie();
		System.out.println(header.getHeaders());
		for(Movie m:movies)
			System.out.println(m.toString());
		System.out.println("---------End Of List------------\n");
		
	}
	private void printBookings(List<Booking> bookings) {
		
		System.out.println("\n---------List Of Bookings------------");
		Booking header=new Booking();
		System.out.println(header.getHeaders());
		for(Booking bk:bookings)
			System.out.println(bk.toString());
		System.out.println("-----------End Of List----------\n");
		
	}
	
}
