package Controllers;

import java.io.IOException;

public class NullSpeakerException extends IOException {
    /**
     * Raises an error if the Speaker cannot be found
     */
    public NullSpeakerException() {
        super();
    }
}
