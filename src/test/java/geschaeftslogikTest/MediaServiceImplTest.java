package geschaeftslogikTest;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datenzugriffsschicht.Book;
import datenzugriffsschicht.Disc;
import geschaeftslogik.MediaService;
import geschaeftslogik.MediaServiceImpl;
import geschaeftslogik.MediaServiceResult;

/**
 * JUnit Test for the class MediaServiceImpl.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class MediaServiceImplTest {
//CHECKSTYLE:OFF

    private MediaService media;
    
    @Before
    public void setUp() {
        media = new MediaServiceImpl();
    }
    
    @After
    public void shutDown() {
        
    }

//Discs
//******************************************************************************************
    /*
    @Test
    public void testAddDiscOk() {
        MediaServiceResult res = media.addDisc(new Disc("TestTitel", "9783826690211", 0, "TestDirector"));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
    }
    
    @Test 
    public void testAddDiscConflict() {
        MediaServiceResult res = media.addDisc(new Disc("TestTitel", "9783826690211", 0, "TestDirector"));
        MediaServiceResult res2 = media.addDisc(new Disc("TestTitel", "9783826690211", 0, "TestDirector"));
        assertEquals(res2.getCode(), Status.CONFLICT.getStatusCode());
    }
    
    @Test
    public void testAddDiscBadRequest() {
        MediaServiceResult res = media.addDisc(new Disc("", "9783826690211", 0, ""));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
    }
    
    @Test
    public void testGetDiscs() {
        int start = media.getDiscs().length;
        Disc disc1 = new Disc("TestTitel1", "9781402894626", 0, "TestDirector");
        Disc disc2 = new Disc("TestTitel2", "9783161484100", 0, "TestDirector");
        media.addDisc(disc1);
        media.addDisc(disc2);
        assertEquals(media.getDiscs().length, start+2);
    }
    
    @Test
    public void testGetDisc() {
        Disc d = new Disc("Test", "Test", 0, "9783551797841");
        media.addDisc(d);
        assertEquals(media.getDisc("9783551797841"), d);
    }
    
    @Test
    public void testUpdateDiscOk() {
        MediaServiceResult res = media.updateDisc("9781402894626", new Disc("NewTitle", "9781402894626", 0, "NewDirector"));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
    }
    
    @Test
    public void testUpdateDiscNotFound() {
        MediaServiceResult res = media.updateDisc("9783806875119", new Disc("NewTitle", "9781402894626", 0, "NewDirector"));
        assertEquals(res.getCode(), Status.NOT_FOUND.getStatusCode());
    }
    
    @Test
    public void testUpdateDiscBadRequest() {
        MediaServiceResult res = media.updateDisc("9781402894626", new Disc("", "9781402894625", 0, ""));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
    }
//Books
//**************************************************************************************
    @Test
    public void testAddBookOk() {
        MediaServiceResult res = media.addBook(new Book("TestTitel", "TestAutor", "978-3-8068-7511-9"));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
    }
    
    //@Test */
    /*public void testAddBookConflict() {
        MediaServiceResult res = media.addBook(new Book("TestTitel", "TestAutor", "978-3-8266-9021-1"));
        MediaServiceResult res2 = media.addBook(new Book("TestTitel", "TestAutor", "978-3-8266-9021-1"));
        assertEquals(res2.getCode(), Status.CONFLICT.getStatusCode());
    }*/
    /*
    @Test
    public void testAddBookBadRequest() {
        MediaServiceResult res = media.addBook(new Book("", "", "978-3-8266-9021-1"));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
    }
    
    @Test
    public void testGetBooks() {
        int start = media.getBooks().length;
        Book book1 = new Book("a", "a", "978-1-4028-9462-6");
        Book book2 = new Book("b", "b", "978-3-16-148410-0");
        media.addBook(book1);
        media.addBook(book2);
        assertEquals(media.getBooks().length, start+2);
    }
    */
    @Test
    public void testGetBook() {
        Book b = new Book("Test", "Test", "9783551714442");
        media.addBook(b);
        assertEquals(media.getBook("9783551714442"), b);
    }
    /*
    @Test
    public void testUpdateBookOk() {
        Book b = new Book("Test", "Test", "978-1-4028-9462-6");
        media.addBook(b);
        MediaServiceResult res = media.updateBook("978-1-4028-9462-6", new Book("NewTitle", "NewAutor", "978-1-4028-9462-6"));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
    }
    
    @Test
    public void testUpdateBookNotFound() {
        MediaServiceResult res = media.updateBook("78-3-8266-9021-1", new Book("NewTitle", "NewAutor", "78-3-8266-9021-1"));
        assertEquals(res.getCode(), Status.NOT_FOUND.getStatusCode());
    }
    
    @Test
    public void testUpdateBookBadRequest() {
        Book b = new Book("Test", "Test", "978-1-4028-9462-6");
        media.addBook(b);
        MediaServiceResult res = media.updateBook("978-1-4028-9462-6", new Book("", "", "978-1-4028-9462-6"));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
    }*/
}
