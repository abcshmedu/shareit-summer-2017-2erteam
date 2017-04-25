package geschaeftslogik;

import javax.ws.rs.core.Response.Status;

/**
 * enum with status codes.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public enum MediaServiceResult {
    FEHLER;
    
    /**
     * @return code
     */
    public int getCode() {
        return 200;
    }
    
    /**
     * @return status
     */
    public Status getStatus() {
        return Status.OK;
    }
    
    
};
