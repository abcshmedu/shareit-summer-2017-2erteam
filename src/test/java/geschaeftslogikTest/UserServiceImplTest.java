package geschaeftslogikTest;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import datenzugriffsschicht.Token;
import datenzugriffsschicht.User;
import datenzugriffsschicht.UserGroup;
import geschaeftslogik.TokenResult;
import geschaeftslogik.UserService;
import geschaeftslogik.UserServiceImpl;

/**
 * JUnit Test for the class UserServiceImpl.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class UserServiceImplTest {
//CHECKSTYLE:OFF
    private UserService userService;
    
    @Before
    public void setUp() {
        userService = new UserServiceImpl();
    }
    
    @Test
    public void testAuthenticateUser() {
        User u = userService.authenticateUser("admin", "admin");
        assertEquals(u, new User("admin", "admin", 1, true, "a@b.com", UserGroup.ADMIN));
    }
    
    @Test
    public void testCreateTokenOk() {
        TokenResult tr = userService.createToken("admin", "admin");
        assertEquals(tr.getCode(), Status.OK.getStatusCode());
    }
    
    @Test
    public void testCreateTokenInvalid() {
        TokenResult tr = userService.createToken("chris", "meier");
        assertEquals(tr.getCode(), Status.BAD_REQUEST.getStatusCode());
    }
    
    @Test
    public void testValidateTokenOk() {
        userService.createToken("admin", "admin");
        TokenResult tr = userService.validateToken("0");
        assertEquals(tr.getCode(), Status.OK.getStatusCode());
    }
    
    @Test
    public void testValidateTokenInvalid() {
        TokenResult tr = userService.validateToken("100");
        assertEquals(tr.getCode(), Status.BAD_REQUEST.getStatusCode());
    }
    
    @Test
    public void testValidateTokenConflict() {
        
    }
    
    @Test
    public void testGetToken() {
        userService.createToken("admin", "admin");
        Token token = userService.getToken("admin", "admin");
        System.out.println("Hier" +token.getToken());
        assertEquals(token, new Token(0));
    }
    
    @Test
    public void testGetTokenNull() {
        Token token = userService.getToken("chris", "meier");
        assertEquals(token, null);
    }
}
