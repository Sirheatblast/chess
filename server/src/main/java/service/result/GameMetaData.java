package service.result;

import java.util.Objects;

public class GameMetaData {
    private final int gameID;
    private final String gameName;
    private final String whiteUsername;

    @Override
    public String toString() {
        return "GameMetaData{" +
                "gameID=" + gameID +
                ", gameName='" + gameName + '\'' +
                ", whiteUsername='" + whiteUsername + '\'' +
                ", blackUsername='" + blackUsername + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameMetaData that = (GameMetaData) o;
        return gameID == that.gameID && Objects.equals(gameName, that.gameName) && Objects.equals(whiteUsername, that.whiteUsername) && Objects.equals(blackUsername, that.blackUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID, gameName, whiteUsername, blackUsername);
    }

    public int getGameID() {
        return gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public String getWhiteUsername() {
        return whiteUsername;
    }

    public String getBlackUsername() {
        return blackUsername;
    }

    public GameMetaData(int gameID, String gameName, String whiteUsername, String blackUsername) {
        this.gameID = gameID;
        this.gameName = gameName;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
    }

    private final String blackUsername;
}
