package movie_entities;

/**
 * Enums of Genres belonging to a Movie object. These can be set by staff as an attribute of movies. Also visible to customers.
 */
public enum Genre {
    BLOCKBUSTER ("BLOCKBUSTER"),
    ACTION ("ACTION"),
    ANIMATION ("ANIMATION"),
    COMEDY ("COMEDY"),
    CRIME ("CRIME"),
    DRAMA ("DRAMA"),
    EXPERIMENTAL ("EXPERIMENTAL"),
    FANTASY ("FANTASY"),
    HISTORICAL ("HISTORICAL"),
    HORROR ("HORROR"),
    ROMANCE ("ROMANCE"),
    SCIFI ("SCIFI"),
    THRILLER ("THRILLER"),
    WESTERN ("WESTERN"),
    ORIENTAL ("ORIENTAL");

    private final String name;

    /**
     * Constructor for Genre enum, taking in the string value of the enum and setting it as an attribute.
     * @param name of the enum.
     */
    private Genre(String name) { this.name = name; }

    /**
     * For string comparison.
     * @param otherName String to be compared to.
     * @return boolean on whether the String value of Genre is equals to otherName.
     */
    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    /**
     *
     * @return String value of Genre for string comparison purposes.
     */
    public String toString() {
        return this.name;
    }
}
