package dataaccess.serverexception;

public class UserUnauthorizedException extends Exception {
    public UserUnauthorizedException(String message) {
        super(message);
    }
}
