package movie_entities;

/**
 * Ways of sorting.
 */
public enum SortType {
    REVIEW_SCORE ("REVIEW_SCORE"),
    TICKETS_SOLD ("TICKETS_SOLD"),
    GROSS_PROFIT ("GROSS_PROFIT");

    private final String name;

    /**
     * Constructor for the SortType enum, taking in the string value of the enum and setting it as an attribute.
     * @param name of the enum.
     */
    private SortType(String name) { this.name = name; }

    /**
     * For string comparison.
     * @param otherName String to be compared to.
     * @return boolean on whether the String value of SortType is equals to otherName.
     */
    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    /**
     *
     * @return String value of SortType for string comparison purposes.
     */
    public String toString() {
        return this.name;
    }
}
