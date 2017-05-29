package geschaeftslogik;

import datenzugriffsschicht.Token;
import datenzugriffsschicht.User;

/**
 * 
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public interface UserService {
    
    /**
     * Authenticates an user.
     * @param user to authenticate
     * @param pwd from the user
     * @return the authenticated user
     */
    User authenticateUser(String user, String pwd);
    
    /**
     * Creates a token.
     * @param user who needs a token
     * @param pwd from the user
     * @return a new token
     */
    TokenResult createToken(String user, String pwd);
    
    /**
     * Checks if a token is validated.
     * @param token to check
     * @return the user who owns the token else null
     */
    TokenResult validateToken(String token);
    
    /**
     * Returns the current token belonging to a user.
     * @param user u
     * @param password p
     * @return token
     */
    Token getToken(String user, String password);
}
