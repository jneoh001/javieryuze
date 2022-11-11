package boundaries;

import customer.CustomerApp;
import staff.StaffApp;

import java.util.Scanner;

/**
 * Main app to run to choose Customer or Staff app
 */
public class DriverApp {
	public static void main(String[] args) {
		int choice;
		Scanner sc = new Scanner(System.in); 
		
		System.out.println("======================= MOBLIMA APP =======================\n"+
                " 1. Customer App                                          \n"+
                " 2. Staff App                                             \n"+
                " 0. Quit App                                              \n"+
						   "===========================================================");
	
        System.out.println("Enter choice: ");
        
        while (!sc.hasNextInt()) {
        	System.out.println("Invalid input type. Please enter an integer value.");
    		sc.next(); // Remove newline character
        }
        
        choice = sc.nextInt();
		
        while (choice != 0) {
        
		//do {
	        /*System.out.println("======================= MOBLIMA APP =======================\n"+
	                " 1. Customer App                                          \n"+
	                " 2. Staff App                                             \n"+
	                " 0. Quit App                                              \n"+
							   "===========================================================");
		
	        System.out.println("Enter choice: ");
	        
	        while (!sc.hasNextInt()) {
            	System.out.println("Invalid input type. Please enter an integer value.");
        		sc.next(); // Remove newline character
            }
	        
	        choice = sc.nextInt();*/
	        
        	if (choice == 1) {
        		CustomerApp.getInstance().displayCustomerMenu();
        	}
        	else if (choice == 2) {
        		StaffApp.getInstance().displayLoginMenu();
        	}
        	else if (choice == 0) {
        		System.out.println("Thank you for using our MOBLIMA APP");
        	}
        	else {
        		System.out.println("Please enter an option between 0-2");
        	}
        	
	        /*switch (choice) {
		        case 1:
		        	 CustomerApp.getInstance().displayCustomerMenu();
		        	break;
		        case 2:
		        	StaffApp.getInstance().displayLoginMenu();
		        	break;
		        case 0:
		        	System.out.println("Thank you for using our MOBLIMA APP");
		        	break;
	        	default:
	        		System.out.println("Please enter an option between 0-2");
	        		break;
	        }*/
        	
        	System.out.println("======================= MOBLIMA APP =======================\n"+
                    " 1. Customer App                                          \n"+
                    " 2. Staff App                                             \n"+
                    " 0. Quit App                                              \n"+
    						   "===========================================================");
    	
            System.out.println("Enter choice: ");
            
            while (!sc.hasNextInt()) {
            	System.out.println("Invalid input type. Please enter an integer value.");
        		sc.next(); // Remove newline character
            }
            
            choice = sc.nextInt();
        	
		} //while (choice != 0);
		System.out.println("Thank you for using our app!");
		sc.close();
	}
}
