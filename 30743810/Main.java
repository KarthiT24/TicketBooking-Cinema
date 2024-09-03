
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		User user=new User();
		while(true) {
		System.out.println("1.Admin Login\n"
				+ "2.User-Login\n"
				+ "3.User-SignUp\n"
				+ "4.Exit");
		int choice=input.nextInt();input.nextLine();
		switch(choice) {
		case 1:
		{
			Admin admin=new Admin();
			admin.main(input);
			return;
		}
		case 2:
		{
			user.main(input);
			return;
		}
		case 3:
		{
			user.signup(input);
			return;
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
