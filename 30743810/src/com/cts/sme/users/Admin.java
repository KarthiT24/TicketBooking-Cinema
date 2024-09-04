package com.cts.sme.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

import com.cts.sme.exceptions.InvalidCredentialException;
import com.cts.sme.exceptions.InvalidDateTimeFormatException;
import com.cts.sme.exceptions.MovieNotFoundException;
import com.cts.sme.exceptions.ShowTimeNotFoundException;
import com.cts.sme.model.Movie;
import com.cts.sme.model.Showtime;
import com.cts.sme.service.implementation.MovieServiceImplementation;
import com.cts.sme.service.implementation.ShowtimeServiceImplementation;
import com.cts.sme.util.JdbcConnection;

public class Admin {
    private String admin;
    private String password;
    private MovieServiceImplementation movieService = new MovieServiceImplementation();
    private ShowtimeServiceImplementation showtimeService = new ShowtimeServiceImplementation();

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login(String admin, String password) {
        String query = "SELECT * FROM admin WHERE name = ? AND password = ?";
        try (Connection conn = JdbcConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, admin);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void main(Scanner input) throws InvalidCredentialException {
        System.out.print("Administrator name: ");
        String name = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();

        if (login(name, password)) {
            System.out.println("WELCOME " + name);
            performOperations(input);
        } else {
            throw new InvalidCredentialException("Invalid Admin credentials");
        }
    }

    private void performOperations(Scanner input) {
        while (true) {
            try {
                displayMenu();
                int choice = getChoice(input);
                input.nextLine();
                switch (choice) {
                    case 1:
                        addMovie(input);
                        break;
                    case 2:
                        viewAllMovies();
                        break;
                    case 3:
                        getMovieById(input);
                        break;
                    case 4:
                        updateMovieById(input);
                        break;
                    case 5:
                        deleteMovieById(input);
                        break;
                    case 6:
                        addShowtime(input);
                        break;
                    case 7:
                        viewAllShowtimes();
                        break;
                    case 8:
                        getShowtimeById(input);
                        break;
                    case 9:
                        updateShowtimeById(input);
                        break;
                    case 10:
                        deleteShowtimeById(input);
                        break;
                    case 11:
                        return;
                    default:
                        System.out.println("Invalid Choice");
                }
            } catch (MovieNotFoundException | ShowTimeNotFoundException | InvalidDateTimeFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n-------------------------\n"
                + "1. Add new Movie\n"
                + "2. View All Movies\n"
                + "3. Get Movie by Id\n"
                + "4. Update Movie by Id\n"
                + "5. Delete Movie by Id\n"
                + "6. Add ShowTime for movie\n"
                + "7. View All ShowTimes\n"
                + "8. View ShowTime By Id\n"
                + "9. Update ShowTime\n"
                + "10. Delete ShowTime by Id\n"
                + "11. Exit\n"
                + "---------------------------\n"
                + "Enter your choice: ");
    }

    private int getChoice(Scanner input) {
        return input.nextInt();
    }

    private void addMovie(Scanner input) throws InvalidDateTimeFormatException {
        Movie movie = movieService.getNewMovie(input);
        movieService.addMovie(movie);
    }

    private void viewAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        printMovies(movies);
    }

    private void getMovieById(Scanner input) throws MovieNotFoundException {
        System.out.print("Enter Movie ID: ");
        int id = input.nextInt();
        input.nextLine();
        Movie movie = movieService.getMovieById(id);

        if (movie == null) {
            throw new MovieNotFoundException("Movie Doesn't Exist");
        } else {
        	System.out.println(movie.getHeaders());
            System.out.println(movie);
        }
    }

    private void updateMovieById(Scanner input) throws MovieNotFoundException, InvalidDateTimeFormatException {
        viewAllMovies(); //for reference
        movieService.updateMovieById(input);
    }

    private void deleteMovieById(Scanner input) throws MovieNotFoundException {
        System.out.print("Enter Movie ID: ");
        int movieId = input.nextInt();//movieId to be deleted
        input.nextLine();
        Movie movie = movieService.getMovieById(movieId);

        if (movie == null) {
            throw new MovieNotFoundException("Movie Doesn't Exist!!");
        } else {
            movieService.deleteMovie(movieId);
        }
    }

    private void addShowtime(Scanner input) throws InvalidDateTimeFormatException {
        viewAllMovies(); // Display movies to choose a valid movie ID
        Showtime showtime = showtimeService.getNewShowtime(input);
        showtimeService.addShowtime(showtime);
    }

    private void viewAllShowtimes() throws ShowTimeNotFoundException {
        List<Showtime> showtimes = showtimeService.getAllShowtimes();

        if (showtimes == null || showtimes.isEmpty()) {
            throw new ShowTimeNotFoundException("No ShowTime Available");
        } else {
            printShowtimes(showtimes);
        }
    }

    private void getShowtimeById(Scanner input) throws ShowTimeNotFoundException {
        System.out.print("Enter a valid ShowTime Id: ");
        int showTimeId = input.nextInt();
        input.nextLine();
        Showtime showtime = showtimeService.getShowtimeById(showTimeId);

        if (showtime == null) {
        	throw new ShowTimeNotFoundException("No ShowTime Available");
        } else {
            System.out.println(showtime);
        }
    }

    private void updateShowtimeById(Scanner input) throws ShowTimeNotFoundException, InvalidDateTimeFormatException {
        List<Showtime> showtimes = showtimeService.getAllShowtimes();

        if (showtimes == null || showtimes.isEmpty()) {
            throw new ShowTimeNotFoundException("No ShowTimes Available to Update..");
        } else {
            showtimeService.updateShowTimeById(input);
        }
    }

    private void deleteShowtimeById(Scanner input) throws ShowTimeNotFoundException {
        List<Showtime> showtimes = showtimeService.getAllShowtimes();

        if (showtimes == null || showtimes.isEmpty()) {
            throw new ShowTimeNotFoundException("No ShowTime available to Delete");
        }

        System.out.print("Enter a valid ShowTime Id: ");
        int deleteId = input.nextInt();
        input.nextLine();
        Showtime showtime = showtimeService.getShowtimeById(deleteId);

        if (showtime == null) {
            throw new ShowTimeNotFoundException("No ShowTimes Available..");
        } else {
            showtimeService.deleteShowtime(deleteId);
        }
    }

    private void printShowtimes(List<Showtime> showtimes) {
        System.out.println("---------List Of ShowTimes------------");
        Showtime show=new Showtime();
        System.out.println(show.getHeaders());
        for (Showtime showtime : showtimes) {
            System.out.println(showtime.toString());
        }
        System.out.println("---------End Of List------------");
    }

    private void printMovies(List<Movie> movies) {
        System.out.println("---------List Of Movies------------");
        Movie header=new Movie();
        System.out.println(header.getHeaders());
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
        System.out.println("---------End Of List------------");
    }
}

