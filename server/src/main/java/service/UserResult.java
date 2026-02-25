package service;

import java.util.Objects;

public class UserResult {
    private final int status;
    private final String message;
    private final String username;
    private final String authToken;

    public UserResult(int status, String message, String username, String authToken) {
        this.status = status;
        this.message = message;
        this.username = username;
        this.authToken = authToken;
    }

    @Override
    public String toString() {
        return "UserResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", authToken='" + authToken + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserResult that = (UserResult) o;
        return status == that.status && Objects.equals(message, that.message) && Objects.equals(username, that.username) && Objects.equals(authToken, that.authToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, username, authToken);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthToken() {
        return authToken;
    }
}
