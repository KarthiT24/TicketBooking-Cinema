# TicketBooking-Cinema

# Ticket Booking System

## Overview

The **Ticket Booking System** is a menu-based console application developed to demonstrate proficiency in Core Java, MySQL, and JDBC. The application simulates a ticket booking system for a cinema, allowing users (admins and regular users) to manage movies, showtimes, and bookings.

### Table of Contents

- [Project Structure](#project-structure)
- [Functionalities](#functionalities)
  - [1. Movie Management](#1-movie-management)
  - [2. Showtime Management](#2-showtime-management)
  - [3. Booking Management](#3-booking-management)
- [Database Schema](#database-schema)
- [Setup Instructions](#setup-instructions)
- [How to Run](#how-to-run)
- [Error Handling](#error-handling)
- [Additional Information](#additional-information)

## Project Structure

The project follows a modular structure with the following key components:

- **Main Class**: The entry point for the application (`Main.java`). It presents the main menu to users and navigates through the different options.
- **User Class**: Handles user-specific functionalities such as login, signup, and operations related to movie and booking management.
- **Admin Class**: Manages administrative functionalities like adding, updating, deleting movies and showtimes.
-                   For instance:- use admin as credentials as the management provides admin accounts in real time therefore no sign up is provided for admin.
- **Service Implementation Classes**: (`MovieServiceImplementation.java`, `ShowtimeServiceImplementation.java`, `BookingServiceImplementation.java`) These classes contain the business logic for handling movies, showtimes, and bookings.
- **Model Classes**: (`Movie.java`, `Showtime.java`, `Booking.java`) Represent the entities in the system.
- **Exception Classes**: Custom exceptions to handle specific error scenarios (e.g., `MovieNotFoundException.java`, `ShowTimeNotFoundException.java`, `BookingNotFoundException.java`, `InvalidCredentialException.java`).
- **Utility Class**: (`JdbcConnection.java`) Manages database connectivity.

## Functionalities

### 1. Movie Management

Admin can perform the following operations:

- **Add New Movie**: Admin can add new movies to the cinema.
- **View Movie Details**: Admin can view the list of all movies and details of a specific movie by its ID.
- **Update Movie Information**: Admin can update details of an existing movie.
- **Delete Movie**: Admin can remove a movie from the cinema.

### 2. Showtime Management

Admin can manage showtimes for movies:

- **Schedule Showtimes**: Admin can add showtimes for movies.
- **View Showtime Details**: Admin can view all showtimes or details of a specific showtime by its ID.
- **Update Showtime Information**: Admin can update showtime details.
- **Delete Showtimes**: Admin can delete a showtime by its ID.

### 3. Booking Management

Users can manage their bookings:

- **Book Tickets**: Users can book tickets for available movies and showtimes.
- **View Booking Details**: Users can view their booking details.
- **Update Booking Information**: Users can cancel their bookings.
- **Calculate Total Ticket Price**: The system calculates the total price for the booking based on the number of tickets.

## Database Schema

The database consists of the following tables:

### Movie Table
- **movie_id**: Primary Key
- **title**: Title of the movie
- **genre**: Genre of the movie
- **duration**: Duration of the movie
- **release_date**: Release date of the movie

### Showtime Table
- **showtime_id**: Primary Key
- **movie_id**: Foreign Key references `Movie Table`
- **start_time**: Start time of the showtime
- **end_time**: End time of the showtime
- **available_seats**: Available seats for the showtime
- **total_seats**: Total seats for the showtime

### Booking Table
- **booking_id**: Primary Key
- **showtime_id**: Foreign Key references `Showtime Table`
- **user_id**: Foreign Key references `User Table`
- **booking_date**: Date of booking
- **total_tickets**: Total number of tickets booked
- **total_price**: Total price for the booking

## Setup Instructions

1. **Clone the Repository**:
   ```
   git clone <repository_url>
   cd ticket-booking-system
   ```

2. **Configure the Database**:
   - Create a MySQL database.
   - Execute the SQL script provided in the `db` directory to create the necessary tables.
   - Update the database connection details in `JdbcConnection.java`.

3. **Build the Project**:
   - Compile the Java files using a Java IDE or the command line.

## How to Run

1. **Execute the Main Class**:
   - Run the `Main.java` class to start the application.

2. **Choose an Option**:
   - The application presents a menu with options for Admin and User operations. Enter the appropriate choice and follow the prompts to perform desired operations.

3. **Login/Signup**:
   - Admins must log in with valid credentials.
   - Users can either log in with existing credentials or sign up to create a new account.

## Error Handling

The application includes custom exception handling for various scenarios:

- **InvalidCredentialException**: Thrown when user/admin credentials are incorrect.
- **MovieNotFoundException**: Thrown when a movie ID does not exist.
- **ShowTimeNotFoundException**: Thrown when a showtime ID does not exist.
- **BookingNotFoundException**: Thrown when a booking ID does not exist.
- **InvalidDateTimeFormatException**: Thrown when date or time format is incorrect.

## Additional Information

- **Code Modularity**: The project is designed to be modular, with separate classes for different functionalities, making it easy to maintain and extend.
- **Scalability**: The system can be easily scaled by adding more features, such as seat selection, payment integration, and more.

Feel free to explore the code and customize the application as per your requirements.
