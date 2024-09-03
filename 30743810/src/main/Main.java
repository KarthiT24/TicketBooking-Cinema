package main;
import java.util.Scanner;

import com.cts.sme.exceptions.BookingNotFoundException;
import com.cts.sme.exceptions.InvalidCredentialException;
import com.cts.sme.exceptions.MovieNotFoundException;
import com.cts.sme.exceptions.ShowTimeNotFoundException;
import com.cts.sme.users.Admin;
import com.cts.sme.users.User;


public class Main {

	public static void main(String[] args) throws MovieNotFoundException, ShowTimeNotFoundException, InvalidCredentialException, BookingNotFoundException {
		Scanner input=new Scanner(System.in);
		User user=new User();
		while(true) {
		System.out.println("-----MAIN MENU----\n"
				+ "1.Admin Login\n"
				+ "2.User-Login\n"
				+ "3.User-SignUp\n"
				+ "4.Exit\n"
				+ "Enter Choice :");
		int choice=input.nextInt();input.nextLine();
		switch(choice) {
		case 1:
		{
			Admin admin=new Admin();
			admin.main(input);
			
			break;
		}
		case 2:
		{
			user.main(input);
			break;
		}
		case 3:
		{
			user.signup(input);
			break;
		}
		case 4:
		{
			System.exit(0);
		}
		default:
			System.out.println("Invalid Choice");
		}
		}
		
	}

}
