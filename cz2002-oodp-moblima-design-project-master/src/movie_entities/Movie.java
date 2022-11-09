package movie_entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Movie object
 */
public class Movie implements Serializable{
	// Attributes
    /**
     * MovieID of movie.
     */
	private String movieID;
    /**
     * Title of movie.
     */
    private String title;
    /**
     * List of genres for movie.
     */
    private List<Genre> genres;
    /**
     * Director of movie.
     */
    private String director;
    /**
     * List of cast members of movie.
     */
    private List<String> cast;
    /**
     * Synopsis of movie.
     */
    private String synopsis;
    /**
     * Movie rating (e.g PG,R21) of movie.
     */
    private MovieRating movieRating;
    /**
     * List of Movie Viewing Formats (e.g TWOD,THREED) of movie.
     */
    private List<MovieFormat> movieFormats;
    /**
     * Length of movie in minutes.
     */
    private int movieDuration;
    /**
     * List of reviews of movie.
     */
    private List<String> reviews;
    /**
     * Average review score of movie. Takes total review score over number of reviews.
     */
    private double averageReviewScore;
    /**
     * Number of reviews for movie.
     */
    private int totalReviewNo;
    /**
     * Total review score of all reviews for movie.
     */
    private double totalReviewScore;
    /**
     * Showing status of movie. (E.g COMING_SOON, NOW_SHOWING)
     */
    private ShowingStatus showingStatus;
    /**
     * Release date of movie.
     */
    private LocalDate releaseDate;
    /**
     * Number of tickets sold for movie.
     */
    private long ticketsSold = 0;
    /**
     * Total gross profit/sales of movie.
     */
    private double grossProfit = 0.0;
    /**
     * List of showtimeIDs of movie. Allows for accessing individual showtimes.
     */
    private List<String> showtimeIDs;


    /**
     * Initialises new arraylist for genres, cast, movie formats, reviews, showtime IDs when movie is created.
     * Sets average review score, total number of reviews and total review scores of movie to be 0 when created.
     */
    // Constructor
    public Movie(){
    	this.genres = new ArrayList<Genre>();
    	this.cast = new ArrayList<String>();
    	this.movieFormats = new ArrayList<MovieFormat>();
    	this.reviews = new ArrayList<String>();
    	this.showtimeIDs = new ArrayList<String>();
    	this.averageReviewScore = 0;
    	this.totalReviewNo = 0;
    	this.totalReviewScore = 0;
    }
    
    
    // Methods

    /**
     * Displays movie details of movie.
     * If number of reviews for movie is less than 1, N/A is printed instead.
     */
    public void displayMovieDetails() {
    	// Title
    	System.out.printf("Movie Title: %s\n", getTitle());
    	//Average Review Score
        if(getTotalReviewNo()<=1){
            System.out.println("Average Review Score: N/A");
        }
        else{
            System.out.println("Average Review Score: " +getAverageReviewScore());
        }
    	// Genres
        System.out.printf("Genres: ");
        for (int i = 0; i < getGenres().size(); i++) {
        	System.out.print(getGenres().get(i).toString());
        	if (i+1 < getGenres().size()) {
        		System.out.print(", ");
        	}
        }
        System.out.println();
        	
        // Rating
        System.out.printf("Rating: %s\n", getMovieRating().toString());
        
        // Duration
        System.out.printf("Duration: %dm\n", getMovieDuration());
        
        // Movie formats
        System.out.print("Movie Formats: ");
        for (int j = 0; j < getMovieFormats().size(); j++) {
        	System.out.print(getMovieFormats().get(j).toString());      	
        	if (j+1 < getMovieFormats().size()) {
        		System.out.print(", ");
        	}
        }
        System.out.println();
        	
        // Showing status
        System.out.printf("Showing Status: %s\n", getShowingStatus().toString());

        // Synopsis
        System.out.printf("Synopsis: %s\n", getSynopsis());

        // Director
        System.out.printf("Director: %s\n", getDirector());

        // Cast
        System.out.print("Cast: ");
        for (int k = 0; k < getCast().size(); k++) {
        	System.out.print(getCast().get(k));      	
        	if (k+1 < getCast().size()) {
        		System.out.print(", ");
        	}
        }
        System.out.println();
    }

    
    // Getters
    public String getMovieID() {return this.movieID;}    
    public String getTitle() {return this.title;}
    public List<Genre> getGenres() {return this.genres;}
    public String getDirector() {return this.director;}
    public List<String> getCast() {return this.cast;}
    public String getSynopsis() {return this.synopsis;}
    public MovieRating getMovieRating() {return this.movieRating;}
    public List<MovieFormat> getMovieFormats() {return this.movieFormats;}
    public int getMovieDuration() {return this.movieDuration;}
    public List<String> getReviews() {return this.reviews;}
    public double getAverageReviewScore() {return this.averageReviewScore;}
    public int getTotalReviewNo() {return this.totalReviewNo;}
    public double getTotalReviewScore() {return this.totalReviewScore;}
    public ShowingStatus getShowingStatus() {return this.showingStatus;}
    public LocalDate getReleaseDate() {return this.releaseDate;}
    public long getTicketsSold() {return this.ticketsSold;}
    public double getGrossProfit() {return this.grossProfit;}
    public List<String> getShowtimeIDs() {return showtimeIDs;}

    // Setters
    public void setMovieID(String movieID) {this.movieID = movieID;}
    public void setTitle(String title) {this.title = title;}
    public void setGenres(ArrayList<Genre> genres) {this.genres = genres;}
    public void setDirector(String director) {this.director = director;}
    public void setCast(ArrayList<String> cast) {this.cast = cast;}
    public void setSynopsis(String synopsis) {this.synopsis = synopsis;}
    public void setMovieRating(MovieRating movieRating) {this.movieRating = movieRating;}
    public void setMovieFormats(ArrayList<MovieFormat> movieFormats) {this.movieFormats = movieFormats;}
    public void setMovieDuration(int movieDuration) {this.movieDuration = movieDuration;}
    public void setReviews(ArrayList<String> reviews) {this.reviews = reviews;}
    public void setAverageReviewScore(double averageReviewScore) {this.averageReviewScore = averageReviewScore;}
    public void setTotalReviewNo(int totalReviewNo) {this.totalReviewNo = totalReviewNo;}
    public void setTotalReviewScore(double totalReviewScore) {this.totalReviewScore = totalReviewScore;}
    public void setShowingStatus(ShowingStatus showingStatus) {this.showingStatus = showingStatus;}
    public void setReleaseDate(LocalDate releaseDate) {this.releaseDate = releaseDate;}
    public void setTicketsSold(long ticketsSold) {this.ticketsSold = ticketsSold;}
    public void setGrossProfit(double grossProfit) {this.grossProfit = grossProfit;}
    public void setShowtimeIDs(List<String> showtimeIDs) {this.showtimeIDs = showtimeIDs;}


    // Adders

    /**
     * Adds a new review into review array list.
     * @param reviewID Review ID of review to be added.
     */
    public void addMovieReview(String reviewID) {
        this.reviews.add(reviewID);
    }

    /**
     * Removes a review from review array list.
     * @param reviewID Review ID of review to be removed.
     */
    public void removeMovieReview(String reviewID) {
    	int i;
    	
    	for (i=0;i<this.getReviews().size(); i++)
    		if (this.getReviews().get(i).equals(reviewID)) {
    			this.reviews.remove(i);
    		}
    }

    /**
     * Adds a new showtime ID into list of showtime IDs.
     * @param showtimeID Showtime ID of showtime to be added.
     */
    public void addShowtimeID(String showtimeID) {
    	this.showtimeIDs.add(showtimeID);
    }

}