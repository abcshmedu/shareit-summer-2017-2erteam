package geschaeftslogik;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import datenzugriffsschicht.Book;

@Path("/media")
public class MediaResource {
	
	private MediaService mediaService;
	
    public MediaResource(){
    	mediaService = new MediaServiceImpl();
    }
    
    public Response createBook(Book b){
        return null;
    }
    
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(){
    	return Response.status(Response.Status.OK)
    			.entity(stringToJson(mediaService.getBooks()))
    			.build();
    }
    
    public Response getBook(String isbn){
        return null;
    }
    
    public Response updateBook(Book b){
        return null;
    }
    
    private String stringToJson(Object o) {
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
