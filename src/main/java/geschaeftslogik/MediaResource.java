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

@Path("/media")
public class MediaResource {
	
	private MediaService mediaService;
	
    public MediaResource(){
    	mediaService = new MediaServiceImpl();
    }
    
    @POST
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book b){
        return Response.status(Response.Status.OK)
    			.entity(objToJson(mediaService.addBook(b)))
    			.build();
    }
    
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(){
    	return Response.status(Response.Status.OK)
    			.entity(objToJson(mediaService.getBooks()))
    			.build();
    }
    
    @GET
    @Path("/books/{isbn}")
    public Response getBook(@PathParam("isbn")String isbn){
        return Response.status(Response.Status.OK)
        		.entity(objToJson(mediaService.getBook(isbn)))
        		.build();
    }
    
    @PUT
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("isbn") String isbn, Book b){
        return Response.status(Status.OK)
        		.entity(objToJson(mediaService.updateBook(isbn, b)))
        		.build();
    }
    
    @POST
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc d){
        return Response.status(Response.Status.OK)
    			.entity(objToJson(mediaService.addDisc(d)))
    			.build();
    }
    
    @GET
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(){
    	return Response.status(Response.Status.OK)
    			.entity(objToJson(mediaService.getDiscs()))
    			.build();
    }
    
    @GET
    @Path("/discs/{barcode}")
    public Response getDisc(@PathParam("barcode")String barcode){
        return Response.status(Response.Status.OK)
        		.entity(objToJson(mediaService.getDisc(barcode)))
        		.build();
    }
    
    @PUT
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(@PathParam("barcode") String barcode, Disc d){
        return Response.status(Status.OK)
        		.entity(objToJson(mediaService.updateDisc(barcode, d)))
        		.build();
    }
    
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
