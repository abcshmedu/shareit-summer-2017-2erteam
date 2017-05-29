package api_schichtTest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import api_schicht.MediaResource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import datenzugriffsschicht.Book;
import datenzugriffsschicht.Disc;
import geschaeftslogik.MediaService;
import geschaeftslogik.MediaServiceResult;


/**
 * JUnit Test for the class MediaResource.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class MediaResourceTest extends JerseyTest {
//CHECKSTYLE:OFF

    @Mock
    private MediaService serviceMock;
    
    private Book[] books = {
            new Book("a", "a", "978-1-4028-9462-6"),
            new Book("b", "b", "978-3-16-148410-0")
    };
    
    private Disc[] discs = {
            new Disc("TestTitel", "9781402894626", 0, "TestDirector"),
            new Disc("TestTitel2", "9783161484100", 0, "TestDirector")            
    };

    @Override
    protected Application configure() {
        MockitoAnnotations.initMocks(this);
        return new ResourceConfig().register(new MediaResource(serviceMock));
    }
    
    @Test
    public void testCreateBook() {
        when(serviceMock.addBook(any(Book.class))).thenReturn(MediaServiceResult.OK);
        Entity<Book> entity = Entity.entity(books[0], MediaType.APPLICATION_JSON);
        Response response = target("media/books/0").request().post(entity);
        assertEquals(200, response.getStatus());
        assertEquals("{\"code\":200,\"detail\":\"successful\"}", response.readEntity(String.class));
    }
    
    @Test
    public void testCreateBookFail() {
        when(serviceMock.addBook(any(Book.class))).thenReturn(MediaServiceResult.OK);
        Entity<Book> entity = Entity.entity(books[0], MediaType.APPLICATION_JSON);
        Response response = target("media/books/1").request().post(entity);
        assertEquals(401, response.getStatus());
        assertEquals("{\"code\":200,\"detail\":\"Invalid Token\"}", response.readEntity(String.class));
    }
    
    @Test
    public void testGetBook() {
        when(serviceMock.getBook("978-1-4028-9462-6")).thenReturn(books[0]);
        Response response = target("media/books/978-1-4028-9462-6/0").request().get();
        assertEquals(200, response.getStatus());
        assertEquals("{\"title\":\"a\",\"author\":\"a\",\"isbn\":\"978-1-4028-9462-6\"}", response.readEntity(String.class));
    }
    
    @Test
    public void testGetBookFail() {
        when(serviceMock.getBook("978-1-4028-9462-6")).thenReturn(books[0]);
        Response response = target("media/books/978-1-4028-9462-6/1").request().get();
        assertEquals(401, response.getStatus());
        assertEquals("{\"code\":200,\"detail\":\"Invalid Token\"}", response.readEntity(String.class));
    }
    
    @Test
    public void testGetBooks() {
        when(serviceMock.getBooks()).thenReturn(books);
        Response response = target("media/books/0").request().get();
        assertEquals(200, response.getStatus());
        assertEquals("[{\"title\":\"a\",\"author\":\"a\",\"isbn\":\"978-1-4028-9462-6\"},{\"title\":\"b\",\"author\":\"b\",\"isbn\":\"978-3-16-148410-0\"}]", response.readEntity(String.class));
    }
    
    @Test
    public void testGetBooksFail() {
        when(serviceMock.getBooks()).thenReturn(books);
        Response response = target("media/books/1").request().get();
        assertEquals(401, response.getStatus());
        assertEquals("{\"code\":200,\"detail\":\"Invalid Token\"}", response.readEntity(String.class));
    }
    
    @Test
    public void testUpdateBook() {
        when(serviceMock.updateBook(eq("978-1-4028-9462-6"), any(Book.class))).thenReturn(MediaServiceResult.OK);
        Entity<Book> entity = Entity.entity(books[0], MediaType.APPLICATION_JSON);
        Response response = target("media/books/978-1-4028-9462-6/0").request().put(entity);
        assertEquals(200, response.getStatus());
        assertEquals("{\"code\":200,\"detail\":\"successful\"}", response.readEntity(String.class));
    }
    
    @Test
    public void testUpdateBookFail() {
        when(serviceMock.updateBook(eq("978-1-4028-9462-6"), any(Book.class))).thenReturn(MediaServiceResult.OK);
        Entity<Book> entity = Entity.entity(books[0], MediaType.APPLICATION_JSON);
        Response response = target("media/books/978-1-4028-9462-6/1").request().put(entity);
        assertEquals(401, response.getStatus());
        assertEquals("{\"code\":200,\"detail\":\"Invalid Token\"}", response.readEntity(String.class));
    }
    
    @Test
    public void testCreateDisc() {
        when(serviceMock.addDisc(any(Disc.class))).thenReturn(MediaServiceResult.OK);
        Entity<Disc> entity = Entity.entity(discs[0], MediaType.APPLICATION_JSON);
        Response response = target("media/discs/0").request().post(entity);
        assertEquals(200, response.getStatus());
        assertEquals("{\"code\":200,\"detail\":\"successful\"}", response.readEntity(String.class));
    }
    
    @Test
    public void testCreateDiscFail() {
        when(serviceMock.addDisc(any(Disc.class))).thenReturn(MediaServiceResult.OK);
        Entity<Disc> entity = Entity.entity(discs[0], MediaType.APPLICATION_JSON);
        Response response = target("media/discs/1").request().post(entity);
        assertEquals(401, response.getStatus());
        assertEquals("{\"code\":200,\"detail\":\"Invalid Token\"}", response.readEntity(String.class));
    }
    
    @Test
    public void testGetDisc() {
        when(serviceMock.getDisc("9781402894626")).thenReturn(discs[0]);
        Response response = target("media/discs/9781402894626/0").request().get();
        assertEquals(200, response.getStatus());
        assertEquals("{\"title\":\"TestTitel\",\"barcode\":\"9781402894626\",\"director\":\"TestDirector\",\"fsk\":0}", response.readEntity(String.class));
    }
    
    @Test
    public void testGetDiscFail() {
        when(serviceMock.getDisc("9781402894626")).thenReturn(discs[0]);
        Response response = target("media/discs/9781402894626/1").request().get();
        assertEquals(401, response.getStatus());
        assertEquals("{\"code\":200,\"detail\":\"Invalid Token\"}", response.readEntity(String.class));
    }
    
    @Test
    public void testGetDiscs() {
        when(serviceMock.getDiscs()).thenReturn(discs);
        Response response = target("media/discs/0").request().get();
        assertEquals(200, response.getStatus());
        assertEquals("[{\"title\":\"TestTitel\",\"barcode\":\"9781402894626\",\"director\":\"TestDirector\",\"fsk\":0},{\"title\":\"TestTitel2\",\"barcode\":\"9783161484100\",\"director\":\"TestDirector\",\"fsk\":0}]", response.readEntity(String.class));
    }
    
    @Test
    public void testGetDiscsFail() {
        when(serviceMock.getDiscs()).thenReturn(discs);
        Response response = target("media/discs/1").request().get();
        assertEquals(401, response.getStatus());
        assertEquals("{\"code\":200,\"detail\":\"Invalid Token\"}", response.readEntity(String.class));
    }
    
    @Test
    public void testUpdateDisc() {
        when(serviceMock.updateDisc(eq("9781402894626"), any(Disc.class))).thenReturn(MediaServiceResult.OK);
        Entity<Disc> entity = Entity.entity(discs[0], MediaType.APPLICATION_JSON);
        Response response = target("media/discs/9781402894626/0").request().put(entity);
        assertEquals(200, response.getStatus());
        assertEquals("{\"code\":200,\"detail\":\"successful\"}", response.readEntity(String.class));
    } 
    
    @Test
    public void testUpdateDiscFail() {
        when(serviceMock.updateDisc(eq("9781402894626"), any(Disc.class))).thenReturn(MediaServiceResult.OK);
        Entity<Disc> entity = Entity.entity(discs[0], MediaType.APPLICATION_JSON);
        Response response = target("media/discs/9781402894626/1").request().put(entity);
        assertEquals(401, response.getStatus());
        assertEquals("{\"code\":200,\"detail\":\"Invalid Token\"}", response.readEntity(String.class));
    } 
}
