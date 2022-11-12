package customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer account holding customer details.
 */
public class CustomerAccount implements Serializable{
	// Attributes
	/**
	 * Name of customer.
	 */
	private String name=null;
	/**
	 * Customer ID of customer.
	 */
	private String customerID;
	/**
	 * Phone number of customer, allows checking of booking history.
	 */
    private String mobileNo;
	/**
	 * Email of customer, allows checking of booking history.
	 */
	private String email;
	/**
	 * Booking history of customer.
	 */
    private List<String> bookingHistory= new ArrayList<String>();


	/**
	 * Constructor of customer account.
	 * @param name Name of customer.
	 * @param email Email of customer.
	 * @param mobileNo Phone number of customer.
	 */
	public CustomerAccount(String name, String email, String mobileNo){
    	this.email= email;
    	this.mobileNo= mobileNo;
    	this.name= name;
    }

	/**
	 * Add booking ID to customer's booking history.
	 * @param bookingID Booking ID of booking.
	 */
	public void addBookingID(String bookingID) {
    	bookingHistory.add(bookingID);
    }
    

    //Getters
    public List<String> getBookingHistoryID() {
    	return bookingHistory;
    }
	public String getMobileNo() {
		return mobileNo;
	}
	public String getEmail() {
		return email;
	}
    public String getName() {
		return name;
	}

	//Setters
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

}