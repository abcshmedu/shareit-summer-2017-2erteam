package geschaeftslogikTest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Injector;

import api_schicht.MediaResource;
import datenzugriffsschicht.Book;
import datenzugriffsschicht.Disc;
import edu.hm.TestInjector;
import geschaeftslogik.MediaService;
import geschaeftslogik.MediaServiceImpl;
import geschaeftslogik.MediaServiceResult;
import persistence.Persistence;

/**
 * JUnit Test for the class MediaServiceImpl.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class MediaServiceImplTest extends JerseyTest{
//CHECKSTYLE:OFF

    private Persistence persistMock;
    private Injector injector;
    private MediaServiceImpl media;
    private ArrayList<Book> books = new ArrayList<>(Arrays.asList(new Book("a", "a", "9781402894626"),new Book("b", "b", "9783161484100")));
    private ArrayList<Disc> discs = new ArrayList<>(Arrays.asList(new Disc("TestTitel", "9781402894626", 10, "TestDirector"), new Disc("TestTitel2", "9783161484100", 10, "TestDirector")));
    @Override
    protected Application configure(){
        injector = TestInjector.getInjectorInstance();
        persistMock = injector.getInstance(Persistence.class);
        return new ResourceConfig().register(MediaService.class)
                .register(TestInjector.class);
    }
    @Before
    public void setUp() {
        media = new MediaServiceImpl(persistMock);
    }
    
    @After
    public void shutDown() {
        
    }

//Discs
//******************************************************************************************
    
    @Test
    public void testAddDiscOk() {
        when(persistMock.getAll(Disc.class)).thenReturn(new ArrayList<Disc>());
        MediaServiceResult res = media.addDisc(discs.get(0));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
    }
    
    @Test 
    public void testAddDiscConflict() {
        when(persistMock.getAll(Disc.class)).thenReturn(discs);
        MediaServiceResult res2 = media.addDisc(discs.get(1));
        assertEquals(res2.getCode(), Status.CONFLICT.getStatusCode());
    }
    
    @Test
    public void testAddDiscBadRequest() {
        MediaServiceResult res = media.addDisc(new Disc("", "9783826690211", 0, ""));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
        
        res = media.addDisc(new Disc("Name", null, 0, "Director"));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
        
        res = media.addDisc(new Disc("Name", "9783826690211", 0, ""));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
        
        res = media.addDisc(new Disc("", "9783826690211", 0, "Director"));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
    }
    
    @Test
    public void testGetDiscs() {
        when(persistMock.getAll(Disc.class)).thenReturn(discs);
        assertEquals(media.getDiscs().length,2);
        assertEquals(media.getDiscs(),discs.toArray());
    }
    
    @Test
    public void testGetDiscOk() {
        Disc d = discs.get(0);
        when(persistMock.getAll(Disc.class)).thenReturn(discs);
        assertEquals(media.getDisc(d.getBarcode()), d);
    }
    @Test
    public void testGetDiscFail() {
        Disc d = new Disc("TestDisc", "9783551359261", 0, "TestA");
        when(persistMock.getAll(Disc.class)).thenReturn(discs);
        assertEquals(media.getDisc("9783551359261"), null);
    }
    
    @Test
    public void testUpdateDiscOk() {
        MediaServiceResult res = media.updateDisc(discs.get(0).getBarcode(), new Disc("NewTitle", discs.get(0).getBarcode(), 0, "NewDirector"));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
        
        res = media.updateDisc(discs.get(0).getBarcode(), new Disc("", discs.get(0).getBarcode(), 0, "NewDirector"));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
        
        res = media.updateDisc(discs.get(0).getBarcode(), new Disc("NewTitle", discs.get(0).getBarcode(), 0, ""));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
        
        res = media.updateDisc(discs.get(0).getBarcode(), new Disc("NewTitle", discs.get(0).getBarcode(), -1, "NewDirector"));
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
        
        res = media.updateDisc(discs.get(0).getBarcode(), new Disc("", discs.get(0).getBarcode(), -1, ""));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
    }
//Books
//**************************************************************************************
    @Test
    public void testAddBookOk() {
        MediaServiceResult res = media.addBook(new Book("TestTitel", "TestAutor", "978-3-8068-7511-9"));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
    }
    @Test 
    public void testAddBookConflict() {
        when(persistMock.getAll(Book.class)).thenReturn(books);
        MediaServiceResult res2 = media.addBook(books.get(0));
        assertEquals(res2.getCode(), Status.CONFLICT.getStatusCode());
    }
    
    @Test
    public void testAddBookBadRequest() {
        MediaServiceResult res = media.addBook(new Book("", "", "978-3-8266-9021-1"));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
        
        res = media.addBook(new Book("", "", "1"));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
        
        res = media.addBook(new Book("asd", "", "978-3-8266-9021-1"));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
        
        res = media.addBook(new Book("", "asd", "978-3-8266-9021-1"));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
        
        res = media.addBook(new Book("asd", "asd", null));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
    }
    
    @Test
    public void testGetBooks() {
        when(persistMock.getAll(Book.class)).thenReturn(books);
        assertEquals(media.getBooks().length,2);
        assertEquals(media.getBooks(),books.toArray());
    }
    @Test
    public void testGetBookOk() {
        Book b = books.get(0);
        when(persistMock.getAll(Book.class)).thenReturn(books);
        assertEquals(media.getBook(b.getIsbn()), b);
    }
    @Test
    public void testGetBookFail() {
        Book b = new Book("TestDisc", "TestA", "9783551359261");
        when(persistMock.getAll(Book.class)).thenReturn(books);
        assertEquals(media.getBook(b.getIsbn()), null);
    }
    
    @Test
    public void testUpdateBookOk() {
        when(persistMock.exist(Book.class, books.get(0).getIsbn())).thenReturn(true);
        MediaServiceResult res = media.updateBook(books.get(0).getIsbn(), new Book("NewTitle", "NewAutor", books.get(0).getIsbn()));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
        
        res = media.updateBook(books.get(0).getIsbn(), new Book("NewTitle", "", books.get(0).getIsbn()));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
        
        res = media.updateBook(books.get(0).getIsbn(), new Book("", "NewAutor", books.get(0).getIsbn()));
        assertEquals(res.getCode(), Status.OK.getStatusCode());
    }
    
    @Test
    public void testUpdateBookNotFound() {
        MediaServiceResult res = media.updateBook("78-3-8266-9021-1", new Book("NewTitle", "NewAutor", "78-3-8266-9021-1"));
        assertEquals(res.getCode(), Status.NOT_FOUND.getStatusCode());
    }
    
    @Test
    public void testUpdateBookBadRequest() {
        when(persistMock.exist(Book.class, books.get(0).getIsbn())).thenReturn(true);
        
        MediaServiceResult res = media.updateBook(books.get(0).getIsbn(), new Book("", "", books.get(0).getIsbn()));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
        
        res = media.updateBook(books.get(0).getIsbn(), new Book("", "", "1"));
        assertEquals(res.getCode(), Status.BAD_REQUEST.getStatusCode());
    }
}
