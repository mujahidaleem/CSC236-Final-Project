package Controllers;

public class NullSpeakerException extends RuntimeException {
    /**
     * Raises an error if the Speaker cannot be found
     *
     * @param errorMessage the message that will appear next to the error
     */
    public NullSpeakerException(String errorMessage) {
        super(errorMessage);
    }
}
