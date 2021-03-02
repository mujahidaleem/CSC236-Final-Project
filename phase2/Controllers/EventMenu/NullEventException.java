package Controllers.EventMenu;

import java.io.IOException;

public class NullEventException extends IOException {

    /**
     * Raises an error if the event cannot be found
     */
    public NullEventException(){
        super();
    }
}
