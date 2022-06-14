package a.nikolskii.archive.exception;


public class DocumentAlreadyExistException extends RuntimeException{
    public DocumentAlreadyExistException(String message) {
        super(message);
    }
}
