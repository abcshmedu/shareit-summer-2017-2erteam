package datenzugriffsschichtTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datenzugriffsschicht.Book;

/**
 * JUnit Test for the class Book.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class BookTest {
//CHECKSTYLE:OFF
    private Book b;
    @Before
    public void setUp() {
        b = new Book("TestBuch", "Testautor", "1234");
    }
    
    @Test
    public void testToString() {
        assertEquals("Book [author=" + b.getAuthor() + ", isbn=" + b.getIsbn() + "]", b.toString());
    }
    
    @Test
    public void testEquals() {
        assertEquals(new Book("TestBuch", "Testautor", "1234"), b);
        assertNotSame(new Book(), b);
    }
    
    @Test
    public void testHashCode() {
        assertEquals(-1230394150, b.hashCode());
    }
}
