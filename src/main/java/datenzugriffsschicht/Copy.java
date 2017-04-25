package datenzugriffsschicht;

/**
 * Represents a copy of a medium.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class Copy {
    private Medium medium;
    private String owner;
    
    /**
     * Constructs a copy of a medium.
     * @param owner of the copy
     * @param toCopy the medium to copy
     */
    Copy(String owner, Medium toCopy) {
        medium = toCopy;
        this.owner = owner;
    }
    
    /**
     * @return the medium
     */
    public Medium getMedium() {
        return medium;
    }
    
    /**
     * @return the username
     */
    public String getUserName() {
        return owner;
    }
}
