package a.nikolskii.archive.exception;


public class DocumentNotFoundException extends RuntimeException{
    public DocumentNotFoundException(String message) {
        super(message);
    }
}
