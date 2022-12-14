package managers;

import utils.ResetSelf;
import utils.ProjectRootPathFinder;
import utils.SerializerHelper;
import movie_entities.Ticket;
import movie_entities.TicketType;
import movie_entities.Transaction;
import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * 
 * Manager for Transcation.  Upon booking, it will settle transcation data.
 */
public class TransactionManager implements ResetSelf {
	/**
	 * Creation of transcation object.
	 */
	private Transaction transaction = null;
	
	/**
	 *  Name for the booker
	 */
	private String bookerName;
	
	/**
	 *  Mobile number for the booker
	 */
	private String bookerMobileNo;
	
	/**
	 * This is the email for the booker
	 */
	private String bookerEmail;
	
	/**
	 * Creditcard Number of the Booker
	 */
	private String bookerCreditCard;
	
	/**
	 * This is for loop control to exit the current transaction manager "window"
	 */
	public Boolean exit = false;
	
	private Scanner sc = new Scanner(System.in);
	
	
	
	/**
     *  This is to ensure that there is only a single instance of Transacation Manager created. 
     */
 	private static TransactionManager single_instance = null;
	
	/**
     * Creates an instance of TranscationManager if one has not been created before.
     * @return an instance of TransactionManager.
     */
	public static TransactionManager getInstance()
	{
	    if (single_instance == null) {
	    	 single_instance = new TransactionManager();
	    }	        
	    return single_instance;
	}

	
	
    /**
     * Constructor of TransactionManager
     */
 	private TransactionManager() {
 		
 	}
	
	// Public Methods
 	
	/**
	 *  Start a new transaction with selected seats and selected tickets
	 * @param ticketList List<Ticket> The list of tickets that are being purchased
	 * @param ticketPrices Map<TicketType, Double> The prices of tickets based off ticket types
	 * @param ticketCount Map<TicketType, Integer>  The number of tickets based off ticket types
	 */
	public void startTransaction(List<Ticket> ticketList, Map<TicketType, Double> ticketPrices, Map<TicketType, Integer> ticketCount) {
		
		exit = false;
		int choice;
		String input;
		
		// Create new transaction & update its total price and tickets
		setTransaction(new Transaction());
		updateTotalPrice(ticketPrices, ticketCount);
		getTransaction().setTicketList(ticketList);

		// Show individual prices
		displayPrices(ticketPrices, ticketCount);
		
		

		while(!exit) {
			System.out.println("================ PAYMENT CONFIRMATION ================\n"+
									" 1. Enter payment details                           \n"+
									" 0. Back to ticket selection                        \n"+
									"======================================================");
				System.out.println("Please select a choice:");	
				
			while (!sc.hasNextInt()) {
				System.out.println("Invalid input type. Please enter an integer value.");
				sc.next(); // Remove newline character
			}
	
			choice = sc.nextInt();
			sc.nextLine(); // Clears \n
	
			if( choice == 0){
				exit = true;
				resetSelf();
				break;
			}
	
			else if(choice == 1){
				System.out.println("Please enter your name:");
						
						while (!sc.hasNext()) { // Not a string
							sc.next(); // Remove newline character
							System.out.println("Invalid input! Please try again.");
						}
						
						input = sc.nextLine();
						
						// Validate name, error messages are auto generated by validation function
						if (validateName(input)) {
							setBookerName(input); // Update name
							
							// Same for email
							System.out.println("Please enter your email:");
							
							if(!sc.hasNext()) { // Not a string
								sc.next(); // Remove newline character
								System.out.println("Invalid input! Please try again.");
								continue; // Terminate
							}
							
							input = sc.next();
									
							if (validateEmail(input)) {
								setBookerEmail(input);
								
								// Same for mobileNo
								System.out.println("Please enter your mobile number (no country code):");
								
								while (!sc.hasNext()) { // Not a string
									sc.next(); // Remove newline character
									System.out.println("Invalid input! Please try again.");
								}
								
								input = sc.next();
								
								if (validateMobileNo(input)) {
									setBookerMobileNo(input);
									
									// Finally, get the credit card number
									System.out.println("Please enter your credit card number. Do not add dashes or spaces:");
									
									while (!sc.hasNext()) { // Not a string
										sc.next(); // Remove newline character
										System.out.println("Invalid input. Please try again.");
									}
									
									input = sc.next();
									
									if (validateCreditCard(input)) {
										setBookerCreditCard(input.replaceAll("[^0-9]+", "")); // Remove all non-numerics;
										
										// We assume all payment transactions go through, and therefore we are done.
										// Now we have to get BookingManager to finalize and raise the event.
										// ============ END OF TICKET BOOKING ============= //
										BookingManager.getInstance().makeBooking();
									}
								}
							}
			}
			}
	
			else{
				System.out.println("Invalid choice, please try again.");
			}
	
	
		} //while(!exit);
	}

		
	
	
	/**
	 * This method will display the pricing information
	 * @param ticketPrices Map<TicketType, Double> The prices of the tickets that are being bought
	 * @param ticketCount Map<TicketType, Integer> The number of tickets that are being bought
	 */
	public void displayPrices(Map<TicketType, Double> ticketPrices, Map<TicketType, Integer> ticketCount) {
		
		// Print out selected ticket prices inclusive of GST and total amount
		System.out.println("Please check your booking details below:");
		System.out.printf("%-40s%-20s%-20s%-20s\n", "Item", "Unit Price", "Quantity", "Net Price");
		for (Map.Entry<TicketType, Integer> item : ticketCount.entrySet()) {
			
			// Item and quantity
			System.out.printf("%-40s%-20.2fx%-19d", item.getKey().toString() + " TICKET", ticketPrices.get(item.getKey()), item.getValue());
			
			// Net price (item price multiplied by amount of items)
			System.out.printf("%-20.2f\n", ticketPrices.get(item.getKey()) * item.getValue());            			
		}
		
		// Booking fee
		System.out.printf("%-40s%-20.2fx%-19d%-20.2f\n", "BOOKING FEE", SystemSettingsManager.getInstance().getPrice("BOOKING"), 1, SystemSettingsManager.getInstance().getPrice("BOOKING"));
		
		// Print net total
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.printf("%-80s%-20.2f\n", "NET TOTAL (INCL. GST)", getTransaction().getTotalPrice());
		System.out.println("-----------------------------------------------------------------------------------------");
	}
	
	
 	
	/** 
	 * After transcation is approved, this will confirm it.
	 */
	public void confirmTransaction() {
		
		// Fill up booking details
		getTransaction().setTransactionID();
		getTransaction().setCreditCardNo(getBookerCreditCard());
		BookingManager.getInstance().getBooking().setTransactionID(getTransaction().getTransactionID());
		CustomerManager.getInstance().updateCustomer(getBookerName(), getBookerEmail(), getBookerMobileNo());
		
		// Update movie's total grossing
		String currMovieID = BookingManager.getInstance().getShowtime().getMovieID();
		MovieManager.getInstance().updateGrossProfit(currMovieID, getTransaction().getTotalPrice());
		
		// Serialize transaction
		String savePath = ProjectRootPathFinder.findProjectRootPath() + "/data/transactions/transaction_" + getTransaction().getTransactionID() + ".dat"; 
		SerializerHelper.serializeObject(getTransaction(), savePath);
	}
	
	
	/** 
	 * After everything has been completed, it will self reset to clear TranscationManager memory
	 */
	public void resetSelf() {
		setTransaction(null);
		setBookerName(null);
		setBookerMobileNo(null);
		setBookerEmail(null);
		exit = true;
	}
	
	//These are the Getters
	
	/**
	 * This returns the current transaction
	 * @return Transaction 
	 */
	public Transaction getTransaction() {return transaction;}
	/** 
	 * This returns the current customer's name
	 * @return String 
	 */
	public String getBookerName() {return bookerName;}
	/**
	 * This returns the current customer's mobile number 
	 * @return String
	 */
	public String getBookerMobileNo() {return bookerMobileNo;}
	/**
	 * This returns the current customer's email
	 * @return String
	 */
	public String getBookerEmail() {return bookerEmail;}
	/**
	 * This returns the current customer's credit card number (not encrypted, not the focus of this project)
	 * @return String credit card
	 */
	private String getBookerCreditCard() {return bookerCreditCard;}
	
	
	// These are the Setters
	/**
	 * This sets the current Transaction object
	 * @param transaction 
	 */
	public void setTransaction(Transaction transaction) {this.transaction = transaction;}
	/**
	 * This sets the current booker's name
	 * @param bookerName
	 */
	public void setBookerName(String bookerName) {this.bookerName = bookerName;}
	/**
	 * This sets the current booker's mobile number
	 * @param bookerMobileNo
	 */
	public void setBookerMobileNo(String bookerMobileNo) {this.bookerMobileNo = bookerMobileNo;}
	/**
	 * This sets the current booker's email
	 * @param bookerEmail
	 */
	public void setBookerEmail(String bookerEmail) {this.bookerEmail = bookerEmail;}
	/**
	 * This sets the current booker's credit card number (not encrypted, not the focus of this project)
	 * @param bookerCreditCard
	 */
	private void setBookerCreditCard(String bookerCreditCard) {this.bookerCreditCard = bookerCreditCard;}
	
	
	
	
	// Private methods 
	/**
	 * Validate user's name, check if all alphabets
	 * @param name This is the booker's name
	 * @return Boolean if successfully validated or not
	 */
	private Boolean validateName(String name) {
		String checkName = name.replaceAll("[^a-zA-Z0-9-_'\"]","");
		char[] chars = checkName.toCharArray();

		if (name.length() > 80) {
			System.out.println("Sorry, we are not able to store such a long name. Please input a shorter name.");
			return false;
		}
		
	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	        	System.out.println("Your name should be purely alphabetic. Please try again.");
	            return false;
	        }
	    }

	    return true;
	}
	
	/**
	 * This validates the mobile number. We assume this is a Singapore number
	 *  We check for    1. Starting with 8 or 9 .    2. Length is 8 .        3. Numeric
	 * @param mobileNo This is the booker's mobile number
	 * @return Boolean if successfully validated or not
	 */
	private Boolean validateMobileNo(String mobileNo) {	
		char[] chars = mobileNo.toCharArray();

		if (chars[0] != '8' && chars[0] != '9') {
			System.out.println("Please enter a valid Singaporean mobile number (starting digit 8 or 9).");
			return false;
		}
		
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
	 * Verify that the email address is valid
	 * @param email This is the booker's email address
	 * @return Boolean if successfully validated or not
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
	
	/**
	 * Checks if credit card is valid
	 * @param email This is the booker's credit card 
	 * @return Boolean if successfully validated or not
	 */
	private Boolean validateCreditCard(String creditCardNo) {
		
		String card = creditCardNo.replaceAll("[^0-9]+", ""); // Remove all non-numerics
        
		// Feel free to comment any of these checks away to facilitate testing
		
		// Check length
		if ((card == null) || (card.length() < 13) || (card.length() > 19)) {
			System.out.println("The credit card number you've entered is too short. Please try again.");
            return false;
        }

        
        return true;
	}
	
	
	/**
	 * Luhn's check for credit card validity. This is a more advanced check
	 * @param cardNumber This is the booker's credit card
	 * @return Boolean if successfully validated or not
	 */
	private Boolean luhnCheck(String cardNumber) {
		// Takes in a pure digit card number
        int digits = cardNumber.length();
        int oddOrEven = digits & 1;
        long sum = 0;
        for (int count = 0; count < digits; count++) {
            int digit = 0;
            try {
                digit = Integer.parseInt(cardNumber.charAt(count) + "");
            } catch(NumberFormatException e) {
                return false;
            }

            if (((count & 1) ^ oddOrEven) == 0) { // not
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }

        return (sum == 0) ? false : (sum % 10 == 0);
    }
	
	
	/** 
	 * Credit card company check 
	 * @param cardNumber
	 * @return
	 */
	private Boolean creditCardCompanyCheck(String cardNumber) {
		List<String> cardCompanies = new ArrayList<String>();
		cardCompanies.add("^4[0-9]{12}(?:[0-9]{3})?$"); // VISA
		cardCompanies.add("^5[1-5][0-9]{14}$"); // MASTER
		cardCompanies.add("^3[47][0-9]{13}$"); // AMEX
		cardCompanies.add("^3(?:0[0-5]|[68][0-9])[0-9]{11}$"); // DINERS
		cardCompanies.add("^6(?:011|5[0-9]{2})[0-9]{12}$"); // DISCOVER
		cardCompanies.add("^(?:2131|1800|35\\d{3})\\d{11}$"); // JCB
		
		// If card number matches any major company, return true, else the card is suspicious
		for (int i = 0; i < cardCompanies.size(); i++) {
			if (cardNumber.matches(cardCompanies.get(i))) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Update total price of booking
	 * @param ticketPrices Map<TicketType, Double> This is all the ticket price by ticket type
	 * @param ticketCount Map<TicketType, Integer> This is the number of ticket types 
	 */
	private void updateTotalPrice(Map<TicketType, Double> ticketPrices, Map<TicketType, Integer> ticketCount) {
	
		double totalPrice = 0;
		
		for (Map.Entry<TicketType, Integer> item : ticketCount.entrySet()) {
			
			// Net price (ticket price multiplied by amount of items)
			totalPrice += ticketPrices.get(item.getKey()) * item.getValue();            			
		}
		
		// Booking fee
		totalPrice += SystemSettingsManager.getInstance().getPrice("BOOKING");
		
		getTransaction().setTotalPrice(totalPrice);
	}
	

}
