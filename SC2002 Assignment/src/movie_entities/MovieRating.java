package movie_entities;

/**
 * Rating of a movie, e.g. G, PG13, etc.
 */
public enum MovieRating {
    G ("G"),
    PG ("PG"),
    PG13 ("PG13"),
    NC16 ("NC16"),
    M18 ("M18"),
    R21 ("R21");

    private final String name;

    /**
     * Constructor for MovieRating enum, taking in the string value of the enum and setting it as an attribute.
     * @param name of the enum.
     */
    private MovieRating(String name) { this.name = name; }

    /**
     * For string comparison.
     * @param otherName String to be compared to.
     * @return boolean on whether the String value of MovieRating is equals to otherName.
     */
    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    /**
     *
     * @return String value of MovieRating for string comparison purposes.
     */
    public String toString() {
        return this.name;
    }
}
