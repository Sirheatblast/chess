package service;

import java.util.Objects;

public class GameResult {
    private final int status;
    private final String message;
    private final int gameID;

    @Override
    public String
    toString() {
        return "GameResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", gameID=" + gameID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameResult that = (GameResult) o;
        return status == that.status && gameID == that.gameID && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, gameID);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getGameID() {
        return gameID;
    }

    public GameResult(int status, String message, int gameID) {
        this.status = status;
        this.message = message;
        this.gameID = gameID;
    }
}
