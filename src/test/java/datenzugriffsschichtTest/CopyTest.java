package datenzugriffsschichtTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datenzugriffsschicht.Book;
import datenzugriffsschicht.Copy;

/**
 * JUnit Test for the class Copy.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class CopyTest {
//CHECKSTYLE:OFF
    private Copy c;
    
    @Before
    public void setUp() {
        c = new Copy("Owner", new Book("TestBuch", "Testautor", "1234"));
    }
    
    @Test
    public void testGetMedium() {
        assertEquals(new Book("TestBuch", "Testautor", "1234"), c.getMedium());
    }
    
    @Test
    public void testGetUserName() {
        assertEquals("Owner", c.getUserName());
    }
    
}
