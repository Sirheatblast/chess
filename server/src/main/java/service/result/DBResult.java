package service.result;

import java.util.Objects;

public class DBResult {
    private final int status;
    private final String message;

    @Override
    public String toString() {
        return "DBResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DBResult dbResult = (DBResult) o;
        return status == dbResult.status && Objects.equals(message, dbResult.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public DBResult(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
