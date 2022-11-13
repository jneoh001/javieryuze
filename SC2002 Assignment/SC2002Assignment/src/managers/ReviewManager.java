package managers;

import utils.IDHelper;
import utils.ProjectRootPathFinder;
import utils.SerializerHelper;
import movie_entities.Review;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This is the ReviewManager. It will handle all review related issues like adding new reviews or viewing reviews
 *
 */
public class ReviewManager {
	// Attributes
    private Scanner sc = new Scanner(System.in);
    
	/**
	 * Holds the reviews object, that has a hashmap of all the available reviews in the system
	 * You can search out a specific review by its reviewID 
	 */
    private Map<String, Review> reviews;

	/**
     * single_instance tracks whether ReviewManager has been instantiated before.
     */
    private static ReviewManager single_instance = null;

    
	/**
     * Instantiates the ReviewManager singleton. If no previous instance has been created,
     * one is created. Otherwise, the previous instance created is used.
     * @return an instance of ReviewManager.
     */
    public static ReviewManager getInstance() {
        if (single_instance == null)
            single_instance = new ReviewManager();
        return single_instance;
    }
    
    
    // Constructor
	/**
	 * Constructor of the ReviewManager. It will load in all the available reviews from the data that we have and turn it into a hashmap
	 * based on the reviewID as a key  
	 */
    private ReviewManager() {
    	this.reviews = new HashMap<String, Review>();
    	this.reviews = this.load();
    }
    
    
	// Public exposed methods to app
    /**
     * This will print out all the review details for a specific movie that we have. 
     * @param reviewIDs This is the ArrayList of the reviewIDs that are held by a movie, which tells us which reviews belong to the movie
     */
    public void printReviews(List<String> reviewIDs) {
    	int i=0;
    	
    	for (String reviewID : reviewIDs) {
    		i++;
    		Review review = this.reviews.get(reviewID);
    		
    		System.out.println("================ REVIEW " + i + " ================");
            System.out.println("Name: " + review.getReviewerName());
            System.out.println("Title: " + review.getReviewTitle());
            System.out.println("Score: " + review.getScore() + "/5");
            System.out.println("Review body: " + review.getReviewBody());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh.mma");
            System.out.println("Review date: "+review.getreviewDateTime().format(formatter));
            System.out.println("==================================================");
            System.out.println("");
    	}
    	
    	if (i==0) {
    		System.out.println("No reviews found");
    	}
    }
    
  
    /**
     * This will allow the customer to add a new review for a specific movie. This can only by accessed by viewing details for a specific movie
     * No validation or login is required. Review will be added upon confirmation
     * @param movieID This is the unique ID that each movie has as its identifier
     */
    public void addReview(String movieID) {
    	Review review = new Review();
    	
    	////////////// INPUT VALIDATION NEEDED
 	
        System.out.println("Enter your name: ");
        review.setReviewerName(sc.nextLine());
        
        System.out.println("Enter title of review: ");
        review.setReviewTitle(sc.nextLine());
        
        System.out.println("Enter review: ");
        review.setReviewBody(sc.nextLine());
        
        System.out.println("Enter a movie score between 0-5: ");

        while (!sc.hasNextDouble()) {
        	System.out.println("Invalid input type. Please enter a numeric value.");
    		sc.next(); // Remove newline character

        }
        review.setScore(sc.nextDouble());
        
        review.setReviewDateTime(LocalDateTime.now());
        
        int choice;
        
        System.out.println(	"========================= ADD REVIEW ====================\n" +
                " 1. Submit review	   						    	 	 \n" +
                " 2. Edit review	   						    	 	 \n" +
                " 0. Discard review, back to Movie Choices              \n"+
                "=========================================================");

        System.out.println("Your current review: ");
        System.out.println("Name: " + review.getReviewerName());
        System.out.println("Title: " + review.getReviewTitle());
        System.out.println("Review body: " + review.getReviewBody());
        System.out.println("Score: " + review.getScore());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh.mma");
        System.out.println("DateTime: " + review.getreviewDateTime().format(formatter));
        System.out.println("");
        System.out.println("Enter choice: ");

        while (!sc.hasNextInt()) {
        	System.out.println("Invalid input type. Please enter an integer value.");
        	sc.next(); // Remove newline character
        }

        choice = sc.nextInt();
        sc.nextLine();
        
        while (choice != 0) {
        
       	
        	if (choice == 1) {
        		String reviewID = IDHelper.getLatestID("review");
            	review.setReviewID(reviewID);
            	this.save(review);
            	MovieManager.getInstance().updateReview(movieID, reviewID, review.getScore(), "add");
            	
            	this.reviews.put(review.getReviewID(), review);
            	
            	System.out.println("Review created! Back to ReviewPortal......");
            	choice = 0;
        	}
        	else if (choice == 2) {
        		this.editReview(review);
        		
        		String reviewID = IDHelper.getLatestID("review");
            	review.setReviewID(reviewID);
            	this.save(review);
            	MovieManager.getInstance().updateReview(movieID, reviewID, review.getScore(), "add");
            	
            	this.reviews.put(review.getReviewID(), review);
            	
            	System.out.println("Review created! Back to ReviewPortal......");
        		
        		choice = 0;
        	}
        	else if (choice == 0) {
        		System.out.println("Review discarded. Back to MovieChoices......");
        	}
        	else {
        		System.out.println("Invalid choice. Please enter a number between 0-2");
        	}
        } 
    }
    
    
    /**
     * Method to delete reviews 
     * @param reviewIDs This is the ArrayList of the reviewIDs that are held by a movie, which tells us which reviews belong to the movie
     */
    public void deleteReview(List<String> reviewIDs) {
    	this.printReviews(reviewIDs);
    	System.out.println("");
    	
    	int choice;
    	
    	System.out.println("Which review would you like to delete? Input 0 to go back to MovieChoices");
    	
    	while (!sc.hasNextInt()) {
        	System.out.println("Invalid input type. Please enter an integer value.");
    		sc.next(); // Remove newline character
        }
    	
    	choice = sc.nextInt();
    	
    	while (choice != 0) {
    
        	
        	if (choice == 0) {
        		System.out.println("Back to MovieChoices......");
        		return;
        	} else if (choice <= reviewIDs.size()) {
            	String reviewID = reviewIDs.get(choice-1);       		
        		MovieManager.getInstance().updateReview(this.reviews.get(reviewID).getMovieID(), reviewID, this.reviews.get(reviewID).getScore(), "remove");

        		String root = ProjectRootPathFinder.findProjectRootPath();
        		File file = new File(root + "/data/reviews/review_" + reviewID + ".dat");
        		file.delete();
        		this.reviews = this.load();        		
        		choice = 0;
        	} else {
        		System.out.println("Invalid input. Please give a number between 0-" + reviewIDs.size());
        	}
        	
        	System.out.println("Which review would you like to delete? Input 0 to go back to MovieChoices");
        	
        	while (!sc.hasNextInt()) {
            	System.out.println("Invalid input type. Please enter an integer value.");
        		sc.next(); // Remove newline character
            }
        	
        	choice = sc.nextInt();
        	
    	} //while (choice != 0);
    }
    
    
    /**
     * This is available to customers who might wish to edit their review before finalising their review. 
     * However reviews cannot be edited once they are finalized and submitted
     * @param review This is a single review object, which is the current review that is being written but not yet saved
     */
	// Private CRUD methods  
    private void editReview(Review review) {
    	int choice;
    	
    	System.out.println(	"======================== EDIT REVIEW ====================\n" +
                " 1. Edit Name		   						    	 	 \n" +
                " 2. Edit Title	   						    	 	 \n" +
                " 3. Edit Review Body	   						    	\n" +
                " 4. Edit Score	   						    	 	 \n" +
                " 0. Finish Editing Review 				            \n"+
                "=========================================================");

    	System.out.println("Enter choice: ");

    	while (!sc.hasNextInt()) {
    		System.out.println("Invalid input type. Please enter an integer value.");
    		sc.next(); // Remove newline character
    	}

    	choice = sc.nextInt();
    	sc.nextLine();
    	
    	while (choice != 0) {
    	
            
    		if (choice == 1) {
    			System.out.println("Enter your name: ");
                review.setReviewerName(sc.nextLine());
    		}
    		else if (choice == 2) {
    			System.out.println("Enter title of review: ");
                review.setReviewTitle(sc.nextLine());
    		}
    		else if (choice == 3) {
    			 System.out.println("Enter review: ");
                 review.setReviewBody(sc.nextLine());
    		}
    		else if (choice == 4) {
    			System.out.println("Enter a movie score between 0-5: ");

                while (!sc.hasNextDouble()) {
                	System.out.println("Invalid input type. Please enter a numeric value.");
            		sc.next(); // Remove newline character

                }
                review.setScore(sc.nextDouble());   
    		}
    		else if (choice == 0) {
    			System.out.println("Review discarded. Back to AddReview......");
    		}
    		else {
    			System.out.println("Invalid choice. Please enter a number between 0-4");
    		}
    		
           
            
            System.out.println(	"======================== EDIT REVIEW ====================\n" +
	                " 1. Edit Name		   						    	 	 \n" +
	                " 2. Edit Title	   						    	 	 \n" +
	                " 3. Edit Review Body	   						    	\n" +
	                " 4. Edit Score	   						    	 	 \n" +
	                " 0. Finish Editing Review 				            \n"+
                    "=========================================================");

            System.out.println("Enter choice: ");

            while (!sc.hasNextInt()) {
            	System.out.println("Invalid input type. Please enter an integer value.");
            	sc.next(); // Remove newline character
            }

            choice = sc.nextInt();
            sc.nextLine();
            
    	} //while (choice != 0);       
    }
    
    

	// Private Serialization and Deserialization
	/**
	 * This is used to save a particular review that has just been added by the customer. It will create a new review data file in the "reviews" folder
	 * @param review This is the current review that is being written and to be saved
	 */
    private void save(Review review) {
		String filePath = ProjectRootPathFinder.findProjectRootPath() + "/data/reviews/review_" + review.getReviewID() + ".dat";
		SerializerHelper.serializeObject(review, filePath);
	}
    
    /**
     * This returns all of the reviews that are currently in the "reviews" folder. 
     * @return This returns a hashmap of all the available reviews in our data files 
     */
    public HashMap<String, Review> load() {
        HashMap<String, Review> loadedReviews = new HashMap<String, Review>();
        File folder = new File(ProjectRootPathFinder.findProjectRootPath() + "/data/reviews");

        File[] listOfFiles = folder.listFiles();
        
        if(listOfFiles != null){
          for(int i=0;i<listOfFiles.length;i++){
            String filepath = listOfFiles[i].getPath(); // Returns full path incl file name and type
            Review newReview = (Review) SerializerHelper.deSerializeObject(filepath);
            String fileID = listOfFiles[i].getName().split("\\.(?=[^\\.]+$)")[0].split("_")[1];
                loadedReviews.put(fileID, newReview);
            }
        }
        return loadedReviews;
    }    
}

