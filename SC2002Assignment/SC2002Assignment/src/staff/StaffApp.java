package staff;

import managers.StaffManager;
import managers.MovieManager;
import managers.SystemSettingsManager;
import java.util.Scanner;

/**
 * StaffApp for Staff
 */
public class StaffApp {
	// Attributes
	/**
	 * check whether StaffApp has only one instance or not
	 */
    private static StaffApp single_instance = null;
    
    private Scanner sc = new Scanner(System.in);

    private StaffApp(){}

	/**
	 * Creates an instance of Customer App and ensure that only one is running. If one exists, we use that.
	 * @return an instance of StaffApp.
	 */
    public static StaffApp getInstance()
    {
        if (single_instance == null)
            single_instance = new StaffApp();
        return single_instance;
    }

    
    // Public exposed methods to app

	/**
	 * Login Menu for Staffs,  username and password verification is done here.
	 */
    public void displayLoginMenu() {
    	boolean loggedIn = false;
    	boolean quit = false;
		int choice;
		
		System.out.println(	"==================== MOBLIMA STAFF APP ====================\n"+
                " 1. Login                                                \n"+
                " 0. Back to MOBLIMA APP                                  \n"+
                "===========================================================");
		System.out.println("Enter choice: ");

		while (!sc.hasNextInt()) {
			System.out.println("Invalid input type. Please enter an integer value.");
			sc.next(); // Remove newline character
		}

		choice = sc.nextInt();
		sc.nextLine();
		
		while (loggedIn == false && quit == false) {
		
			if (choice == 1) {
				System.out.println("Username: ");
                while (!sc.hasNext()) {
                	System.out.println("Invalid input type. Please try again!");
            		sc.next(); // Remove newline character
                }
                String username = sc.nextLine();
                System.out.println("Password: ");
                while (!sc.hasNext()) {
                	System.out.println("Invalid input type. Please try again!");
            		sc.next(); // Remove newline character
                }
                String password = sc.nextLine();
                
                boolean authenticate = StaffManager.getInstance().login(username, password);
                
                
                if (authenticate) {
                	loggedIn = true;
                	this.displayLoggedInMenu();
                	quit = true;
                } else {
                	System.out.println("Invalid Username or Password, please try again.");
                }
			}
			else if (choice == 0){
				System.out.println("Back to MOBLIMA APP......");
				quit = true;
			}
			}
		} 
    
    
    
    // Private methods

	/**
	 * Once logged in, the staff views this menu.
	 */
    private void displayLoggedInMenu() {
		int choice;
		
		System.out.println(	"==================== MOBLIMA STAFF APP ====================\n" +
                " 1. View Top 5 Movies                                     \n" +
                " 2. Configure System Settings                             \n" +
                " 3. Movie Database                                        \n" +
                " 0. Logout from StaffApp                                  \n"+
				"===========================================================");
		System.out.println("Enter choice: ");

		while (!sc.hasNextInt()) {
			System.out.println("Invalid input type. Please enter an integer value.");
			sc.next(); // Remove newline character
		}

		choice = sc.nextInt();
		
		while (choice != 0) {
	
			if (choice == 1) {
				MovieManager.getInstance().viewTop5("Staff");
			}
			else if (choice == 2) {
				SystemSettingsManager.getInstance().displayMenu();
			}
			else if (choice == 3) {
				MovieManager.getInstance().movieMenuStaff();
			}
			else if (choice == 0) {
				System.out.println("Logging out from StaffApp......");
			}
			else {
				System.out.println("Invalid choice. Please choose between 0-3.");
			}
				
			System.out.println(	"==================== MOBLIMA STAFF APP ====================\n" +
                    " 1. View Top 5 Movies                                     \n" +
                    " 2. Configure System Settings                             \n" +
                    " 3. Movie Database                                        \n" +
                    " 0. Logout from StaffApp                                  \n"+
					"===========================================================");
			System.out.println("Enter choice: ");

			while (!sc.hasNextInt()) {
				System.out.println("Invalid input type. Please enter an integer value.");
				sc.next(); // Remove newline character
			}

			choice = sc.nextInt();
			
		} 
    }
}
