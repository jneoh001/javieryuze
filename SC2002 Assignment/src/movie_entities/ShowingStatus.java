package movie_entities;

/**
 * Showing status of a movie.
 */
public enum ShowingStatus {
    COMING_SOON ("COMING_SOON"),
    PREVIEW ("PREVIEW"),
    NOW_SHOWING ("NOW_SHOWING"),
    END_OF_SHOWING ("END_OF_SHOWING");

    private final String name;

    /**
     * Constructor for ShowingStatus enum, taking in the string value of the enum and setting it as an attribute.
     * @param name of the enum.
     */
    private ShowingStatus(String name) { this.name = name; }

    /**
     * For string comparison.
     * @param otherName String to be compared to.
     * @return boolean on whether the String value of ShowingStatus is equals to otherName.
     */
    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    /**
     *
     * @return String value of ShowingStatus for string comparison purposes.
     */
    public String toString() {
        return this.name;
    }
}
