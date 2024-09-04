package main;
import java.util.Scanner;

import com.cts.sme.exceptions.BookingNotFoundException;
import com.cts.sme.exceptions.InvalidCredentialException;
import com.cts.sme.exceptions.MovieNotFoundException;
import com.cts.sme.exceptions.ShowTimeNotFoundException;
import com.cts.sme.users.Admin;
import com.cts.sme.users.User;


public class TicketBookingSystem {

	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        User user = new User();
        while (true) {
            System.out.println("\nTicket Booking System\n"
            		+ "-----MAIN MENU----\n"
                    + "1.Admin Login\n"
                    + "2.User-Login\n"
                    + "3.User-SignUp\n"
                    + "4.Exit\n"
                    + "-------------------\n"
                    + "Enter Choice :");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    try {
                        Admin admin = new Admin();
                        admin.main(input);
                    } catch (InvalidCredentialException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        user.main(input);
                    } catch (InvalidCredentialException | BookingNotFoundException | ShowTimeNotFoundException | MovieNotFoundException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        user.signup(input);
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println("Invalid Choice");
            }
        }
		
	}

}
