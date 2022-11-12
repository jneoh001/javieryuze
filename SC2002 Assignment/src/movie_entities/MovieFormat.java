package movie_entities;

/**
 * MovieFormat of a showtime.
 */
public enum MovieFormat {
	TWOD ("TWOD"),
    THREED ("THREED"),
    IMAX ("IMAX");

    private final String name;

    /**
     * Constructor for the MovieFormat enum, taking in the string value of the enum and setting it as an attribute.
     * @param s of the enum.
     */
    private MovieFormat(String s) {
        name = s;
    }

    /**
     * For string comparison.
     * @param otherName String to be compared to.
     * @return boolean on whether the String value of MovieFormat is equals to otherName.
     */
    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return name.equals(otherName);
    }

    /**
     *
     * @return String value of MovieFormat for string comparison purposes.
     */
    public String toString() {
       return this.name;
    }
}
