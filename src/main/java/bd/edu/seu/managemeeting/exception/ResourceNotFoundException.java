package bd.edu.seu.managemeeting.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String resource) {
        super(resource + " not found!");
    }
}