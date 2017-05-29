package api_schicht;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import datenzugriffsschicht.*;
import geschaeftslogik.MediaService;
import geschaeftslogik.MediaServiceImpl;
import geschaeftslogik.MediaServiceResult;

/**
 * The class MediaResource hands off JSON Objects to the service.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
@Path("/media")
public class MediaResource {

    private final MediaService mediaService;
    
    /**
     * Constructs a media service.
     */
    public MediaResource() {
        mediaService = new MediaServiceImpl();
    }
    
    /**
     * Constructors a media service.
     * @param service media service
     */
    public MediaResource(MediaService service) {
        mediaService = service;
    }
    
    /**
     * Creates a new Book in the service.
     * @param b new Book
     * @return response
     */
    @POST
    @Path("/books/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book b, @PathParam("token")String token) {
        // validate Token an eigenem O-Auth
        try{
            if(validateToken(token)){
                MediaServiceResult res = mediaService.addBook(b);
                // wird wohl automatisch von JSON zu nem Objekt umgewandelt...
                JSONObject jo = new JSONObject();
                jo.put("detail", res.getStatus());
                jo.put("code", res.getCode());
                return Response
                        .status(res.getCode())
                        .entity(jo.toString())
                        .build();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("detail","Invalid Token");
        jo.put("code", 200);
        return Response.status(Status.UNAUTHORIZED).entity(jo.toString()).build();
    }
    
    /**
     * Lists all books.
     * @return response
     */
    @GET
    @Path("/books/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(@PathParam("token")String token) {
        try{
            if(validateToken(token)){
                return Response.status(Response.Status.OK)
                        .entity(objToJson(mediaService.getBooks()))
                        .build();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("detail","Invalid Token");
        jo.put("code", 200);
        return Response.status(Status.UNAUTHORIZED).entity(jo.toString()).build();
        
    }
    
    /**
     * Lists one specific book.
     * @param isbn of the book
     * @return response
     */
    @GET
    @Path("/books/{isbn}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn")String isbn, @PathParam("token")String token) {
        // 1. ask Service for a specific Book (isbn) after de-serializing
        // 2. serialize the Book
        // 3. forward the serialized answer
        try{
            if(validateToken(token)){
                return Response.status(Response.Status.OK)
                        .entity(objToJson(mediaService.getBook(isbn)))
                        .build();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("detail","Invalid Token");
        jo.put("code", 200);
        return Response.status(Status.UNAUTHORIZED).entity(jo.toString()).build();
        
        
    }
    
    /**
     * Updates the attributes of a book.
     * @param isbn of the book
     * @param b book
     * @return reponse
     */
    @PUT
    @Path("/books/{isbn}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("isbn") String isbn, Book b, @PathParam("token")String token) {
        // 1. de-serialize the received JSON to make an Object
        // 2. call the Service1 (to update that Object)
        // 3. serialize Services answer
        // 4. forward serialized answer
        try{
            if(validateToken(token)){
                MediaServiceResult res = mediaService.updateBook(isbn, b);
                JSONObject jo = new JSONObject();
                jo.put("detail", res.getStatus());
                jo.put("code", res.getCode());
                return Response.status(res.getCode())
                        .entity(jo.toString())
                        .build();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("detail","Invalid Token");
        jo.put("code", 200);
        return Response.status(Status.UNAUTHORIZED).entity(jo.toString()).build();
    }
    
    /**
     * Creates a new disc.
     * @param d disc
     * @return response
     */
    @POST
    @Path("/discs/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc d, @PathParam("token")String token) {
        try{
            if(validateToken(token)){
                MediaServiceResult res = mediaService.addDisc(d);
                JSONObject jo = new JSONObject();
                jo.put("detail", res.getStatus());
                jo.put("code", res.getCode());
                return Response.status(res.getCode())
                        .entity(jo.toString())
                        .build();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("detail","Invalid Token");
        jo.put("code", 200);
        return Response.status(Status.UNAUTHORIZED).entity(jo.toString()).build();
    }
    
    /**
     * Lists all Discs.
     * @return response
     */
    @GET
    @Path("/discs/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(@PathParam("token")String token) {
        try{
            if(validateToken(token)){
                return Response.status(Response.Status.OK)
                        .entity(objToJson(mediaService.getDiscs()))
                        .build();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("detail","Invalid Token");
        jo.put("code", 200);
        return Response.status(Status.UNAUTHORIZED).entity(jo.toString()).build(); 
    }
    
    /**
     * Lists a specific Disc.
     * @param barcode of the disc
     * @return response
     */
    @GET
    @Path("/discs/{barcode}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode")String barcode, @PathParam("token")String token) {
        
        try{
            if(validateToken(token)){
                return Response.status(Response.Status.OK)
                        .entity(objToJson(mediaService.getDisc(barcode)))
                        .build();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("detail","Invalid Token");
        jo.put("code", 200);
        return Response.status(Status.UNAUTHORIZED).entity(jo.toString()).build();
    }
    
    /**
     * Updates the attributes of a disc.
     * @param barcode of the disc
     * @param d disc
     * @return response
     */
    @PUT
    @Path("/discs/{barcode}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(@PathParam("barcode") String barcode, Disc d, @PathParam("token")String token) {
        try{
            if(validateToken(token)){
                MediaServiceResult res = mediaService.updateDisc(barcode, d);
                JSONObject jo = new JSONObject();
                jo.put("detail", res.getStatus());
                jo.put("code", res.getCode());
                return Response.status(res.getCode())
                        .entity(jo.toString())
                        .build();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("detail","Invalid Token");
        jo.put("code", 200);
        return Response.status(Status.UNAUTHORIZED).entity(jo.toString()).build();
        
    }
    
    /**
     * Converts a java object to JSON.
     * @param o object to convert
     * @return string with the json representation
     */
    private String objToJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(o);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
    private boolean validateToken(String token){
        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target("http://localhost:8080/shareit/users/"+token);
        Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);
        Response response = request.get();
        if(response.getStatus()==200){
            return true;
        }else{
            return false;
        }
    }
    
}
