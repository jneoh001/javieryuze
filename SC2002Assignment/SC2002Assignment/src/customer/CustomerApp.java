package customer;

import managers.MovieManager;
import managers.CustomerManager;
import java.util.Scanner;

/**
 * Customer App
 */
public class CustomerApp {
	// Attributes

	/**
	 * Checks whether CustomerApp has one instance or not.
	 */
    private static CustomerApp single_instance = null;
    
    private Scanner sc = new Scanner(System.in);

    private CustomerApp(){}

	/**
	 * Creates an instance of Customer App and ensure that only one is running. If one exists, we use that.
	 * @return an instance of CustomerApp.
	 */
    public static CustomerApp getInstance()
    {
        if (single_instance == null)
            single_instance = new CustomerApp();
        return single_instance;
    }

	/**
	 * Display Customer App and the functions
	 */
	public void displayCustomerMenu() {
		int choice;		
		
		System.out.println(	"============== MOBLIMA CUSTOMER APP ================\n" +
                " 1. View movie listings					        \n" +
                " 2. View top 5                                    \n" +
                " 3. Check booking history                         \n" +
                " 0. Back to MOBLIMA APP                           \n"+
			    "====================================================");

		System.out.println("Enter choice: ");

		while (!sc.hasNextInt()) {
			System.out.println("Invalid input type. Please enter an integer value.");
			sc.next(); // Remove newline character
		}

		choice = sc.nextInt();
		while (choice != 0) {
	
            
			if (choice == 1) {
				MovieManager.getInstance().viewMovies("Customer");
			}
			else if (choice == 2) {
				MovieManager.getInstance().viewTop5("Customer");
			}
			else if (choice == 3) {
				int subchoice;
				System.out.println(	"=========== SEARCH BOOKING HISTORY =============\n" +
	                    " 1. Check via email address					    		\n" +
	                    " 2. Check via mobile number 					    		\n" +
		                " 0. Back to CustomerApp......                             \n"+
									"================================================");
        		
                System.out.println("Enter choice: ");
                
                while (!sc.hasNextInt()) {
                	System.out.println("Invalid input type. Please enter an integer value.");
            		sc.next(); // Remove newline character
                }
                
                subchoice = sc.nextInt();
                
                while (subchoice != 0) {
                	if (subchoice == 1) {
                		CustomerManager.getInstance().printPastBookingByEmail();
                	}
                	else if (subchoice == 2) {
                		CustomerManager.getInstance().printPastBookingByMobile();
                	}
                	else if (subchoice == 0) {
                		System.out.println("Back to CustomerApp......");
                		break;
                	}
                	else {
                		System.out.println("Invalid choice. Please choose between 0-2.");
                	}
                	
                	System.out.println(	"=========== SEARCH BOOKING HISTORY =============\n" +
		                    " 1. Check via email address					    		\n" +
		                    " 2. Check via mobile number 					    		\n" +
			                " 0. Back to CustomerApp......                             \n"+
										"================================================");
            		
                    System.out.println("Enter choice: ");
                    
                   
                    
                    subchoice = sc.nextInt();
                	
                }
			}
			else if (choice == 0) {
				System.out.println("Back to MOBLIMA APP......");
			}
			else {
				System.out.println("Invalid choice. Please choose between 0-3.");
			}
			
           
			
			System.out.println(	"============== MOBLIMA CUSTOMER APP ================\n" +
	                " 1. View movie listings					        \n" +
	                " 2. View top 5                                    \n" +
	                " 3. Check booking history                         \n" +
	                " 0. Back to MOBLIMA APP                           \n"+
				    "====================================================");

			System.out.println("Enter choice: ");

			while (!sc.hasNextInt()) {
				System.out.println("Invalid input type. Please enter an integer value.");
				sc.next(); // Remove newline character
			}

			choice = sc.nextInt();
			
	        } 
		}
    }
	
