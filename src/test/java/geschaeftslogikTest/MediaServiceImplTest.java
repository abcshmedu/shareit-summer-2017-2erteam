package geschaeftslogikTest;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response.Status;

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
    
    private Book[] books = {
            new Book("a", "a", "1"),
            new Book("b", "b", "2")
    };
    
    private Disc[] discs = {
       new Disc("TestTitel", "1234", 0, "TestDirector"),
       new Disc("TestTitel2", "5678", 0, "TestDirector")
    };
    
    @Before
    public void setUp() {
        media = new MediaServiceImpl();
    }

//Discs
//******************************************************************************************
    @Test
    public void testAddDiscOk() {
        MediaServiceResult res = media.addDisc(new Disc("TestTitel", "12345", 0, "TestDirector"));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
    }
    
    @Test 
    public void testAddDiscConflict() {
        MediaServiceResult res = media.addDisc(new Disc("TestTitel", "12345", 0, "TestDirector"));
        MediaServiceResult res2 = media.addDisc(new Disc("TestTitel", "12345", 0, "TestDirector"));
        assertEquals(res2.getCode(), Status.CONFLICT.getStatusCode());
    }
    
    @Test
    public void testAddDiscBadRequest() {
        MediaServiceResult res = media.addDisc(new Disc("", "1234", 0, ""));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
    }
    
    @Test
    public void testGetDiscs() {
        Disc disc1 = new Disc("TestTitel1", "1234", 0, "TestDirector");
        Disc disc2 = new Disc("TestTitel2", "5678", 0, "TestDirector");
        media.addDisc(disc1);
        media.addDisc(disc2);
        assertEquals(media.getDiscs(), discs);
    }
    
    @Test
    public void testGetDisc() {
        assertEquals(media.getDisc("1234"), discs[0]);
        assertEquals(media.getDisc("9012"), null);
    }
    
    @Test
    public void testUpdateDiscOk() {
        MediaServiceResult res = media.updateDisc("1234", new Disc("NewTitle", "1234", 0, "NewDirector"));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
    }
    
    @Test
    public void testUpdateDiscNotFound() {
        MediaServiceResult res = media.updateDisc("9012", new Disc("NewTitle", "1234", 0, "NewDirector"));
        assertEquals(res.getCode(), Status.NOT_FOUND.getStatusCode());
    }
    
    @Test
    public void testUpdateDiscBadRequest() {
        MediaServiceResult res = media.updateDisc("1234", new Disc("", "1234", 0, ""));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
    }
//Books
//**************************************************************************************
    @Test
    public void testAddBookOk() {
        MediaServiceResult res = media.addBook(new Book("TestTitel", "TestAutor", "12345"));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
    }
    
    @Test 
    public void testAddBookConflict() {
        MediaServiceResult res = media.addBook(new Book("TestTitel", "TestAutor", "1234"));
        MediaServiceResult res2 = media.addBook(new Book("TestTitel", "TestAutor", "1234"));
        assertEquals(res2.getCode(), Status.CONFLICT.getStatusCode());
    }
    
    @Test
    public void testAddBookBadRequest() {
        MediaServiceResult res = media.addBook(new Book("", "", "1234"));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
    }
    
    @Test
    public void testGetBooks() {
        Book book1 = new Book("a", "a", "1");
        Book book2 = new Book("b", "b", "2");
        media.addBook(book1);
        media.addBook(book2);
        assertEquals(media.getBooks(), books);
    }
    
    @Test
    public void testGetBook() {
        assertEquals(media.getBook("1"), books[0]);
        assertEquals(media.getBook("3"), null);
    }
    
    @Test
    public void testUpdateBookOk() {
        MediaServiceResult res = media.updateBook("1", new Book("NewTitle", "NewAutor", "1"));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
    }
    
    @Test
    public void testUpdateBookNotFound() {
        MediaServiceResult res = media.updateBook("3", new Book("NewTitle", "NewAutor", "3"));
        assertEquals(res.getCode(), Status.NOT_FOUND.getStatusCode());
    }
    
    @Test
    public void testUpdateBookBadRequest() {
        MediaServiceResult res = media.updateBook("1", new Book("", "", "1"));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
    }
}
