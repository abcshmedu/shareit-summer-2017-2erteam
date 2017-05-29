package api_schichtTest;
//CHECKSTYLE:OFF
import static org.junit.Assert.*;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import api_schicht.UserResource;
import geschaeftslogik.TokenResult;
import geschaeftslogik.UserService;

/**
 * JUnit Test for the class UserResource.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class UserResourceTest extends JerseyTest {
    
    @Mock
    private UserService serviceMock;
    
    @Override
    protected Application configure() {
        MockitoAnnotations.initMocks(this);
        return new ResourceConfig().register(new UserResource(serviceMock));
    }
    /*
    @Test
    public void testCreateToken() {
        when(serviceMock.createToken(Mockito.anyString(), Mockito.anyString())).thenReturn(TokenResult.OK);    
        assertEquals(200, target("users/authenticate/admin/admin").request().get().getStatus());
    }
    
    @Test
    public void testValidateToken() {
        when(serviceMock.validateToken(Mockito.anyString())).thenReturn(TokenResult.OK);
        assertEquals(200, target("users/0").request().get().getStatus());
    }
    */

}
