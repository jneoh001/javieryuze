package managers;


import utils.*;
import customer.CustomerAccount;
import movie_entities.Booking;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This is the CustomerManager. It will manage the customer side of things, the check their booking 
 * 
 *
 */
public class CustomerManager implements ResetSelf{
	// Attributes 
	/**
	 * This holds all the mobile numbers matching to the customer id (since we are not allowed a database)
	 */
	private Map<String, String> mobileHash;
	
	/** 
	 * This holds all the emails matching to the customer id (since we are not allowed a database)
	 */
	private Map<String, String> emailHash;
	
	/** 
	 * This holds all the customer ids matching to the customer object (since we are not allowed a database) 
	 */
	private Map<String, CustomerAccount> idHash;
	
	/**
	 * This is the current customer object
	 */
	private CustomerAccount cust;
	
	private Scanner sc= new Scanner(System.in);


	
	/**
     * Checks whether CustomerManager has been instantiated before.
     */
	private static CustomerManager single_instance = null;

	/**
     * Creates an instance of CustomerManager if none exists, if one exists we use that instance instead.
     * @return an instance of CustomerManager.
     */
	public static CustomerManager getInstance()
	{
		if (single_instance==null)
			single_instance= new CustomerManager();
		return single_instance;
	}
	
	
	// Constructor
    /**
     * Constructor of TransactionManager. Will intialize the lookup hashmaps that are required for searching out a customer
     */
	CustomerManager(){
		this.emailHash= new HashMap<String,String>();
		this.mobileHash= new HashMap<String,String>();
		this.idHash= new HashMap<String,CustomerAccount>();
		this.loadCustomers();
	}
	

	// Public exposed methods to app
	/**
	 * Searching for the customer by email and printing out their booking
	 */
	public void printPastBookingByEmail() {
		System.out.println("Enter your email address: ");
		String email= sc.next();
		//checks if email valid, then retrieves booking history of associated account
		if (validateEmail(email))
			printBookingHistory(emailToCustomer(email));
		}
	
	/**
	 * Searching for the customer by mobile number and printing out their booking
	 */
	public void printPastBookingByMobile() {
		System.out.println("Enter your mobile number:");
		String mobileNo= sc.next();
		//checks if mobileNo valid, then prints booking history of associated account
		if (validateMobileNo(mobileNo))
			printBookingHistory(mobileToCustomer(mobileNo));
	}
	
	//Create, Read, Update, Delete
	/**
	 * Update a customer's information
	 * @param name This is the customer's name
	 * @param email This is the customer's email
	 * @param mobileNo This is the customer's mobile number
	 */
	public void updateCustomer(String name, String email, String mobileNo) {
		// should be called during BookingManager.makeBooking
		CustomerAccount currCustomer;
		
		// if current customer exists, addBooking to customerAccount
		if (idHash.containsKey(mobileHash.get(mobileNo))) {
			currCustomer = mobileToCustomer(mobileNo);
		}
			
		// else, create newCustomer and addBooking
		else {
			currCustomer= new CustomerAccount(name,email,mobileNo);
			currCustomer.setCustomerID(IDHelper.getLatestID("customer"));
			emailHash.put(email, currCustomer.getCustomerID());
			mobileHash.put(mobileNo, currCustomer.getCustomerID());
		}
		setCust(currCustomer);
	}
	
	/**
	 * Store bookingID into Customer bookingID list
	 * @param bookingID This is the bookingID to be associated with the customer
	 */
	public void storeBooking(String bookingID) {
		getCust().addBookingID(bookingID);
		idHash.put(getCust().getCustomerID(), getCust());
		save();
		resetSelf();
	}
	
	/**
	 * Reset attributes using resetSelf
	 */
	public void resetSelf() {
		setCust(null);
	}
	
	// Getters 
	/**
	 * Gets customer object
	 * @return CustomerAccount 
	 */
	public CustomerAccount getCust() {
		return cust;
	}

	// Setters
	/**
	 * Sets customer object
	 * @param cust 
	 */
	public void setCust(CustomerAccount cust) {
		this.cust = cust;
	}
	
	
	
	
	
	
	
	// Private methods
	/**
	 * This is to match the email given to the customer
	 * @param email Email given to match customer
	 * @return CustomerAccount Matching customer account
	 */
	private CustomerAccount emailToCustomer(String email) {
		if (idHash.containsKey(emailHash.get(email)))
			return idHash.get(emailHash.get(email));
		else
			return null;
	}
	
	/**
	 * This is to match the mobile number given to the customer 
	 * @param mobileNo Mobile number given to match customer
	 * @return CustomerAccount Matching customer account
	 */
	private CustomerAccount mobileToCustomer(String mobileNo) {
		if (idHash.containsKey(mobileHash.get(mobileNo)))
			return idHash.get(mobileHash.get(mobileNo));
		else 
			return null;
	}
	

	/**
	 * Print out the customer's booking history
	 * @param custToPrint This is the customer we are querying
	 */
	private void printBookingHistory(CustomerAccount custToPrint) {//given customer account, print booking history
		Booking booking;
		if (custToPrint==null) //prints no records if customer doesnt exist
			System.out.println("No records found.\n");
		else {
			System.out.println("Previous Bookings:");
			//get list of booking IDs of customer and searches for each associated booking file
			for (int i=0;i<custToPrint.getBookingHistoryID().size();i++) {
				//search directory for booking file according to each ID
				booking= loadBooking(custToPrint.getBookingHistoryID().get(i));
				
				if (booking!=null)
					booking.displayBooking();
			}
		}
	}
	
	/** 
	 * Search directory for booking files
	 * @param bookingID This is the current booking we are searching for
	 * @return Booking This is the booking that we found
	 */
	private Booking loadBooking(String bookingID) {
		Booking booking=null;
		
		//looks in bookings folder for past bookings
		String folderPath = ProjectRootPathFinder.findProjectRootPath() + "/data/bookings";
		File[] files= getAllFiles(folderPath);
		if (files!=null) {
			for (int i=0; i<files.length;i++)
			{
				String filePath= files[i].getPath();
				if (filePath.contains(bookingID))
					booking= (Booking)SerializerHelper.deSerializeObject(filePath);
			}
			return booking;
			}
		else
			return null;
	}
	
	/**
	 * This is to verify the validity of the user's mobile number. We check whether it is 8 digits and it is numeric.  We assume this App is for usage in Singapore so country code is needed.
	 * @param mobileNo This is the mobile number that we are validating
	 * @return Boolean successfully validated or not
	 */
	private Boolean validateMobileNo(String mobileNo) {	
		char[] chars = mobileNo.toCharArray();

		// Check length
		if (mobileNo.length() != 8) {
			System.out.println("Please enter a valid 8 digit mobile number (no country code).");
			return false;
		}
		
	    for (char c : chars) {
	        if(!Character.isDigit(c)) {
	        	System.out.println("Your mobile number must be purely numeric. Please try again.");
	            return false;
	        }
	    }

	    return true;
	}
	
	
	/**
	 * Verify validity of email address
	 * @param email This is the email address that we are validating
	 * @return Boolean successfully validated or not
	 */
	private Boolean validateEmail(String email) {
		// Check length
		if (email.length() > 100) {
			System.out.println("Sorry, your email is too long. Please use a shorter email.");
			return false;
		}
		
		// Match email against pattern using regex
		String pattern = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
		if(!(email.matches(pattern))) {
			System.out.println("Sorry, that email is invalid. Please try again.");
			return false;
		}
		
		return true;
	}
	


	// Serialization and Deserialization
	/**
	 * This is to save the current customer's file into its own data file
	 */
	private void save() {
		String filePath = ProjectRootPathFinder.findProjectRootPath();
    	filePath = filePath + "/data/customers/customer_" + getCust().getCustomerID() + ".dat"; 
		SerializerHelper.serializeObject(getCust(), filePath);
	}
	
	/**
	 * This is to load all Customer Data into the CustomerManager for us to do lookup
	 */
	private void loadCustomers(){
		String folderPath = ProjectRootPathFinder.findProjectRootPath() + "/data/customers";
		File[] files= getAllFiles(folderPath);
		if (files!=null)
			for (int i=0; i<files.length;i++)
			{
				String filePath = files[i].getPath();
				CustomerAccount newCust = (CustomerAccount)(SerializerHelper.deSerializeObject(filePath));
				
				emailHash.put(newCust.getEmail(), newCust.getCustomerID());
				mobileHash.put(newCust.getMobileNo(), newCust.getCustomerID());
				idHash.put(newCust.getCustomerID(), newCust);
			}
	}
	
	
	/**
	 * Loads all text files in the specified folder and returns the list of files
	 * @param folderPath This is the path where the customer data files are stored
	 * @return File[] all the files we need
	 */
	private File[] getAllFiles(String folderPath) {
		try {
			// Finds folder and gets a list of all files in folder
			File directory = new File(folderPath);
			return(directory.listFiles());
		} catch (Exception e) {
			return null;
		}
	}	
}
