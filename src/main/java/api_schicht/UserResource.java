package api_schicht;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import datenzugriffsschicht.Token;
import geschaeftslogik.TokenResult;
import geschaeftslogik.UserService;
import geschaeftslogik.UserServiceImpl;

/**
 * The class UserResource hands off JSON Objects to the service for authentification.
 * @author Altvatter Robert, Gro�beck Thomas
 *
 */
@Path("/users")
public class UserResource {

    private static final int OK = 200;
    
    private final UserService userService;
    
    /**
     * constructs a user resource.
     */
    public UserResource() {
        userService = new UserServiceImpl();
    }
    
    /**
     * constructs a user resource.
     * @param us user resource
     */
    public UserResource(UserService us) {
        this.userService = us;
    }
    
    /**
     * Creates a token for the user.
     * @param user who wants a token
     * @param pwd for mthe user
     * @return service response
     */
    @GET
    @Path("/authenticate/{user}/{pwd}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createToken(@PathParam("user")String user, @PathParam("pwd")String pwd) {
        TokenResult res = userService.createToken(user, pwd);
        Token token = new Token();
        if (res.getCode() == OK) {
            token = userService.getToken(user, pwd);
        }
        JSONObject jo = new JSONObject();
        jo.put("detail", res.getStatus());
        jo.put("code", res.getCode());
        jo.put("token", token.getToken());
        return Response
                .status(res.getCode())
                .entity(jo.toString())
                .build();
    }
   
    
    /**
     * checks if a token is validated.
     * @param token to check
     * @return service response
     */
    @GET
    @Path("/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateToken(@PathParam("token") String token) {
        TokenResult res = userService.validateToken(token);
        JSONObject jo = new JSONObject();
        jo.put("detail", res.getStatus());
        jo.put("code", res.getCode());
        return Response
                .status(res.getCode())
                .entity(jo.toString())
                .build();
    } 
}
