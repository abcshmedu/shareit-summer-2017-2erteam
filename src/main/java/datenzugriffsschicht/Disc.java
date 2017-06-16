package datenzugriffsschicht;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Represents a disc as a medium.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Disc extends Medium {
    @Id
    private String barcode;
    private String director;
    private int fsk;
    
    /**
     * Constructs a disc.
     */
    public Disc() {
        super("");
        barcode = "";
        director = "";
        fsk = 0;
    }
    
    /**
     * Constructs a disc with title, barcode, fsk and director.
     * @param title of the disc
     * @param barcode of the disc
     * @param fsk of the disc
     * @param director of the disc
     */
    public Disc(String title, String barcode, int fsk, String director) {
        super(title);
        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }

    @Override
    public String toString() {
        return "Disc [barcode=" + barcode + ", director=" + director + ", fsk=" + fsk + "]";
    }
    /**
     * @return the barcode
     */
    public String getBarcode() {
        return barcode;
    }
    /**
     * @return the director
     */
    public String getDirector() {
        return director;
    }
    /**
     * @return the fsk
     */
    public int getFsk() {
        return fsk;
    }
    
    /**
     * Sets the director.
     * @param director of the disc
     */
    public void setDirector(String director) {
        this.director = director;
    }
    
    /**
     * Sets the fsk.
     * @param fsk of the disc
     */
    public void setFsk(int fsk) {
        this.fsk = fsk;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
        result = prime * result + ((director == null) ? 0 : director.hashCode());
        result = prime * result + fsk;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Disc other = (Disc) obj;
        if (barcode == null) {
            if (other.barcode != null) {
                return false;
            }
        } else if (!barcode.equals(other.barcode)) {
            return false;
        }
        if (director == null) {
            if (other.director != null) {
                return false;
            }
        } else if (!director.equals(other.director)) {
            return false;
        }
        if (fsk != other.fsk) {
            return false;
        }
        return true;
    }

    /**
     * @param barcode the barcode to set
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
}
