package geschaeftslogikTest;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import datenzugriffsschicht.Book;
import geschaeftslogik.MediaResource;

/**
 * JUnit Test for the class MediaResource.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class MediaResourceTest {
//CHECKSTYLE:OFF
    @Test
    public void testCreateBook() {
        MediaResource media = new MediaResource();
        Response res = media.createBook(new Book("TestTitel", "TestAutor", "1234"));
        assertEquals(res.getStatus(), Status.OK.getStatusCode());
        Response res2 = media.createBook(new Book("TestTitel", "TestAutor", "1234"));
        assertEquals(res2.getStatus(), Status.CONFLICT.getStatusCode());
        Response res3 = media.createBook(new Book("", "", "1234"));
        assertEquals(res3.getStatus(), Status.BAD_REQUEST.getStatusCode());
    }
    
    @Test
    public void testGetBook() {
        
    }
    
    @Test
    public void testGetBooks() {
        
    }
    
    @Test
    public void testUpdateBook() {
        
    }
    
    @Test
    public void testCreateDisc() {
        
    }
    
    @Test
    public void testGetDisc() {
        
    }
    
    @Test
    public void testGetDiscs() {
        
    }
    
    @Test
    public void testUpdateDisc() {
        
    }
    
}
