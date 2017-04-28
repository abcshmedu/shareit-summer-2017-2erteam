package geschaeftslogikTest;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import datenzugriffsschicht.Book;
import geschaeftslogik.MediaResource;
import geschaeftslogik.MediaService;


/**
 * JUnit Test for the class MediaResource.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class MediaResourceTest extends JerseyTest {
//CHECKSTYLE:OFF

    @Mock
    private MediaService serviceMock;
    
    private Book book = new Book("a", "a", "978-1-4028-9462-6");

    @Override
    protected Application configure() {
        MockitoAnnotations.initMocks(this);
        return new ResourceConfig().register(new MediaResource(serviceMock));
    }
    
    @Test
    public void testCreateBook() {
        
    }
    
    @Test
    public void testGetBook() {
        when(serviceMock.getBook("978-1-4028-9462-6")).thenReturn(book);
        Response response = target("media/books/978-1-4028-9462-6").request().get();
        assertEquals(200, response.getStatus());
        assertEquals("{\"title\":\"a\",\"author\":\"a\",\"isbn\":\"978-1-4028-9462-6\"}", response.readEntity(String.class));
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
