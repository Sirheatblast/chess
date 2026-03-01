package handler;

import java.util.Objects;

public class GameRequest {
    private final String gameName;
    private final String playerColor;
    private final int gameID;

    @Override
    public String toString() {
        return "GameRequest{" +
                "gameName='" + gameName + '\'' +
                ", playerColor='" + playerColor + '\'' +
                ", gameID=" + gameID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameRequest that = (GameRequest) o;
        return gameID == that.gameID && Objects.equals(gameName, that.gameName) && Objects.equals(playerColor, that.playerColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameName, playerColor, gameID);
    }

    public String getGameName() {
        return gameName;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public int getGameID() {
        return gameID;
    }

    public GameRequest(String gameName, String playerColor, int gameID) {
        this.gameName = gameName;
        this.playerColor = playerColor;
        this.gameID = gameID;
    }
}
