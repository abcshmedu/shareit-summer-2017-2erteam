package api_schichtTest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Injector;

import api_schicht.MediaResource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import datenzugriffsschicht.Book;
import datenzugriffsschicht.Disc;
import edu.hm.TestInjector;
import geschaeftslogik.MediaService;
import geschaeftslogik.MediaServiceResult;


/**
 * JUnit Test for the class MediaResource.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class MediaResourceTest extends JerseyTest {
//CHECKSTYLE:OFF

    private MediaService serviceMock;
    private Injector injector;
    
    private Book[] books = {
            new Book("a", "a", "9781402894626"),
            new Book("b", "b", "9783161484100")
    };
    
    private Disc[] discs = {
            new Disc("TestTitel", "9781402894626", 0, "TestDirector"),
            new Disc("TestTitel2", "9783161484100", 0, "TestDirector")            
    };

    @Override
    protected Application configure() {
        
        
        injector = TestInjector.getInjectorInstance();
        serviceMock = injector.getInstance(MediaService.class);
        return new ResourceConfig().register(MediaResource.class)
                .register(TestInjector.class);
    }
    
    @BeforeClass
    public static void setUp2() {
        //UserServiceImpl us = new UserServiceImpl();
        //us.createToken("admin", "admin");
        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target("http://frozen-coast-96197.herokuapp.com/shareit/users/authenticate/admin/admin");
        Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);
        Response response = request.get();
        if(response.getStatus() == 200)
            System.out.println("logged in with Token 0");
        else
            System.out.println("Failed with Code " + response.getStatus() + " maybe Server not running?");
    }
    
    @Test
    public void testCreateBook() {
        String help = "";
        when(serviceMock.addBook(any(Book.class))).thenReturn(MediaServiceResult.OK);
        Entity<Book> entity = Entity.entity(books[0], MediaType.APPLICATION_JSON);
        Response response = target("media/books/0").request().post(entity);
        help = response.readEntity(String.class);
        System.out.println(help);
        assertEquals(200, response.getStatus());
        assertEquals("{\"code\":200,\"detail\":\"successful\"}", help);
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
        assertEquals("{\"title\":\"a\",\"isbn\":\"9781402894626\",\"author\":\"a\"}", response.readEntity(String.class));
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
        assertEquals("[{\"title\":\"a\",\"isbn\":\"9781402894626\",\"author\":\"a\"},{\"title\":\"b\",\"isbn\":\"9783161484100\",\"author\":\"b\"}]", response.readEntity(String.class));
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
