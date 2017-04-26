package datenzugriffsschichtTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

import datenzugriffsschicht.Disc;

/**
 * JUnit Test for the class Disc.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class DiscTest {
    //CHECKSTYLE:OFF
    private Disc d;
    @Before
    public void setUp() {
        d = new Disc("TestDisc", "1234", 12, "Director");
    }
    
    @Test
    public void testToString() {
        assertEquals("Disc [barcode=" + d.getBarcode() + ", director=" + d.getDirector() + ", fsk=" + d.getFsk() + "]", d.toString());
    }
    
    @Test
    public void testEquals() {
        assertEquals(new Disc("TestDisc", "1234", 12, "Director"), d);
        assertNotSame(new Disc(), d);
    }
    
    @Test
    public void testHashCode() {
        assertEquals(1144452700, d.hashCode());
    }
    
    @Test
    public void testGetTitle() {
        assertEquals("TestDisc", d.getTitle());
    }
}
