package geschaeftslogik;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import datenzugriffsschicht.User;
import datenzugriffsschicht.UserGroup;
import datenzugriffsschicht.Token;

/**
 * Implementation UserService.
 * @author Altvatter Robert, Großbeck Thomas
 *
 */
public class UserServiceImpl implements UserService {
    private ArrayList<User> users;
    private static HashMap<Token, User> tokenUserMap = new HashMap<>();;
    private static int nextToken = 0;
    
    /**
     * Constructs an user service with some default users.
     */
    public UserServiceImpl() {
        users = new ArrayList<>();
        users.add(new User("admin", "admin", 1, true, "a@b.com", UserGroup.ADMIN));
        users.add(new User("sepp", "sepp", 2, true, "a@b.com", UserGroup.NORMAL));
        //CHECKSTYLE:OFF
        users.add(new User("hans", "hans", 3, true, "a@b.com", UserGroup.ADMIN));
        //CHECKSTYLE:ON
    }
    
    /**
     * Authenticates an user.
     * @param user user to authenticate
     * @param pwd from the user
     * @return the user if the token is validated else returns null.
     */
    public User authenticateUser(String user, String pwd) {
        for (User u:users) {
            if (u.getName().equals(user) && u.getPassword().equals(pwd)) {
                return u;
            }
        }
        return null;
    }
    
    @Override
    public TokenResult createToken(String user, String pwd) {
        System.out.println("createToken - userService");
        User u = authenticateUser(user, pwd);
        if (u == null) {
            return TokenResult.INVALID;
        }
        if (tokenUserMap.containsValue(u)) {
            for (Entry<Token, User> e:tokenUserMap.entrySet()) {
                if (e.getValue().equals(u)) {
                    return TokenResult.OK;
                }
            }
            return TokenResult.INVALID;
        } else {
            Token help = new Token(nextToken);
            nextToken++;
            tokenUserMap.put(help, u);
            System.out.println(help.getToken());
            return TokenResult.OK;
        }
    }
    
    @Override
    public TokenResult validateToken(String token) {   
        System.out.println("validateToken - userService");
        Date now = new Date();
        for (Entry<Token, User> e: tokenUserMap.entrySet()) {
            if (e.getKey().getToken().equals(token) && now.before(e.getKey().getDate())) {
                return TokenResult.OK;
            } else if (e.getKey().getToken().equals(token) && !now.before(e.getKey().getDate())) {
                return TokenResult.CONFLICT;
            }
        }
        return TokenResult.INVALID;       
    }
    
    @Override
    public Token getToken(String user, String pwd) {
        User u = authenticateUser(user, pwd);
        for (Entry<Token, User> e: tokenUserMap.entrySet()) {
            if (e.getValue().equals(u)) {
                //todo: Prüfung auf gültigkeit (nicht abgelaufen)
                return e.getKey();
            }
        }
        return null;
    }

}
