package geschaeftslogik;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

//import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import datenzugriffsschicht.*;

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
     * Creates a new Book in the service.
     * @param b new Book
     * @return response
     */
    @POST
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book b) {
        System.out.println("got called");
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
    
    /**
     * Lists all books.
     * @return response
     */
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        return Response.status(Response.Status.OK)
                .entity(objToJson(mediaService.getBooks()))
                .build();
    }
    
    /**
     * Lists one specific book.
     * @param isbn of the book
     * @return response
     */
    @GET
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn")String isbn) {
        // 1. ask Service for a specific Book (isbn) after de-serializing
        // 2. serialize the Book
        // 3. forward the serialized answer
        return Response.status(Response.Status.OK)
                .entity(objToJson(mediaService.getBook(isbn)))
                .build();
    }
    
    /**
     * Updates the attributes of a book.
     * @param isbn of the book
     * @param b book
     * @return reponse
     */
    @PUT
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("isbn") String isbn, Book b) {
        // 1. de-serialize the received JSON to make an Object
        // 2. call the Service1 (to update that Object)
        // 3. serialize Services answer
        // 4. forward serialized answer
        MediaServiceResult res = mediaService.updateBook(isbn, b);
        JSONObject jo = new JSONObject();
        jo.put("detail", res.getStatus());
        jo.put("code", res.getCode());
        return Response.status(res.getCode())
                .entity(jo.toString())
                .build();
    }
    
    /**
     * Creates a new disc.
     * @param d disc
     * @return response
     */
    @POST
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc d) {
        MediaServiceResult res = mediaService.addDisc(d);
        JSONObject jo = new JSONObject();
        jo.put("detail", res.getStatus());
        jo.put("code", res.getCode());
        return Response.status(res.getCode())
                .entity(jo.toString())
                .build();
    }
    
    /**
     * Lists all Discs.
     * @return response
     */
    @GET
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() {
        return Response.status(Response.Status.OK)
                .entity(objToJson(mediaService.getDiscs()))
                .build();
    }
    
    /**
     * Lists a specific Disc.
     * @param barcode of the disc
     * @return response
     */
    @GET
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode")String barcode) {
        return Response.status(Response.Status.OK)
                .entity(objToJson(mediaService.getDisc(barcode)))
                .build();
    }
    
    /**
     * Updates the attributes of a disc.
     * @param barcode of the disc
     * @param d disc
     * @return response
     */
    @PUT
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(@PathParam("barcode") String barcode, Disc d) {
        MediaServiceResult res = mediaService.updateDisc(barcode, d);
        JSONObject jo = new JSONObject();
        jo.put("detail", res.getStatus());
        jo.put("code", res.getCode());
        return Response.status(res.getCode())
                .entity(jo.toString())
                .build();
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
}
