package geschaeftslogikTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import geschaeftslogik.UserService;
import geschaeftslogik.UserServiceImpl;

/**
 * JUnit Test for the class UserServiceImpl.
 * @author Altvatter Robert, Gro�beck Thomas
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
    public void testUpdateUser() {
        
    }
}
