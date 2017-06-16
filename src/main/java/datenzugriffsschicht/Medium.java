package datenzugriffsschicht;

import javax.persistence.MappedSuperclass;

/**
 * Represents a Medium in the service.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
@MappedSuperclass
public abstract class Medium {

    private String title;
    
    /**
     * Constructs a medium with a title.
     * @param title of the medium
     */
    public Medium(String title) {
        this.title = title;
    }
    
    /**
     * @return title of the medium
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets the title.
     * @param title of a medium
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Medium other = (Medium) obj;
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        return true;
    }
    
    /**
     * @return a string representation of a medium
     */
    public String toString() {
        return "Medium: " + title;
    }

}
