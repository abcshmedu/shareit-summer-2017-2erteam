package geschaeftslogikTest;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import datenzugriffsschicht.Book;
import geschaeftslogik.MediaResource;

/**
 * JUnit Test for the class MediaServiceImpl.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class MediaServiceImplTest {
//CHECKSTYLE:OFF

    private MediaResource media;
    
    @Before
    public void setUp() {
        media = new MediaResource();
    }

    @Test
    public void testAddBookOk() {
        Response res = media.createBook(new Book("TestTitel", "TestAutor", "12345"));
        assertEquals(res.getStatus(), Status.OK.getStatusCode());
    }
    
    @Test 
    public void testAddBookConflict() {
        Response res = media.createBook(new Book("TestTitel", "TestAutor", "1234"));
        Response res2 = media.createBook(new Book("TestTitel", "TestAutor", "1234"));
        assertEquals(res2.getStatus(), Status.CONFLICT.getStatusCode());
    }
    
    @Test
    public void testAddBookBadRequest() {
        Response res = media.createBook(new Book("", "", "1234"));
        assertEquals(res.getStatus(), Status.BAD_REQUEST.getStatusCode());
    }
}
