package geschaeftslogik;

import javax.ws.rs.core.Response.Status;

public enum MediaServiceResult {
    FEHLER;
    public int getCode(){
        return 200;
    }
    public Status getStatus(){
        return Status.OK;
    }
    
    
};
