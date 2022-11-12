package movie_entities;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * Review for Movie.
 */
public class Review implements Serializable {
    //Attributes
    /**
     * Review ID of review.
     */
    private String reviewID;
    /**
     * Name of reviewer.
     */
    private String reviewerName;
    /**
     * Title of review.
     */
    private String reviewTitle;
    /**
     * Main body of review.
     */
    private String reviewBody;
    /**
     * Movie ID of movie review was made for.
     */
    private String movieID;
    /**
     * Score given by reviewer.
     */
    private double score;
    /**
     * Date and time of review.
     */
    private LocalDateTime reviewDateTime;


    //Getters
    public String getReviewID() {
        return reviewID;
    }
    public String getReviewerName() { return reviewerName; }
    public String getReviewTitle() {
        return reviewTitle;
    }
    public String getReviewBody() {
        return reviewBody;
    }
    public String getMovieID() {
    	return movieID;
    }
    public double getScore() {
        return score;
    }
    public LocalDateTime getreviewDateTime() {
        return reviewDateTime;
    }
    
    
    //Setters
    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }
    public void setReviewerName(String reviewName) { this.reviewerName = reviewName; }
    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }
    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }
    public void setMovieID(String movieID) {
    	this.movieID = movieID;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public void setReviewDateTime(LocalDateTime reviewDateTime) {
        this.reviewDateTime = reviewDateTime;
    }
}
