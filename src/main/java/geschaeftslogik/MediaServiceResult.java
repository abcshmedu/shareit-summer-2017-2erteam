package geschaeftslogik;

import javax.ws.rs.core.Response;

/**
 * enum with status codes.
 * @author Altvatter Robert, Großbeck Thomas
 *
 */
public enum MediaServiceResult {
    OK(Response.Status.OK.getStatusCode(), "successful"),
    BAD_REQUEST(Response.Status.BAD_REQUEST.getStatusCode(), "Ungültige Angaben"),
    NOT_FOUND(Response.Status.NOT_FOUND.getStatusCode(), "Medium nicht gefunden"),
    CONFLICT(Response.Status.CONFLICT.getStatusCode(), "Medium bereits vorhanden");
    
    private String status;
    private int code;
    
    /**
     * Defaultconstructor.
     */
    MediaServiceResult() {
        code = 0;
        status = "";
    }
    
    /**
     * Constructs a MediaServiceResult.
     * @param code of the result
     * @param status of the result
     */
    MediaServiceResult(int code, String status) {
        this.code = code;
        this.status = status;
    }
    
    /**
     * @return code
     */
    public int getCode() {
        return code;
    }
    
    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }
    
    
};
