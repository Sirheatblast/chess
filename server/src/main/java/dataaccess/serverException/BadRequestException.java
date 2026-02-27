package dataaccess.serverException;

public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
