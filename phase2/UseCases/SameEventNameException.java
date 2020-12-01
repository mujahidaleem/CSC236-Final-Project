package UseCases;

import java.io.IOException;

public class SameEventNameException extends IOException {
    /**
     * Raises an error if the event shares a name with another existing event
     */
    public SameEventNameException() {
        super();
    }
}
