package Controllers;

public class NullSpeakerException extends RuntimeException{
    public NullSpeakerException(String errorMessage){
        super(errorMessage);
    }
}
