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
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import datenzugriffsschicht.Book;
import datenzugriffsschicht.Disc;

/**
 * The class MediaResource hands off JSON Objects to the service.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
@Path("/media")
public class MediaResource {

    private MediaService mediaService;
    
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
        return Response.status(Response.Status.OK)
                .entity(objToJson(mediaService.addBook(b)))
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
    public Response getBook(@PathParam("isbn")String isbn) {
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
        return Response.status(Status.OK)
                .entity(objToJson(mediaService.updateBook(isbn, b)))
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
        return Response.status(Response.Status.OK)
                .entity(objToJson(mediaService.addDisc(d)))
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
        return Response.status(Status.OK)
                .entity(objToJson(mediaService.updateDisc(barcode, d)))
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
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
