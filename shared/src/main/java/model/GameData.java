package model;

import chess.ChessGame;

import java.util.Objects;

public class GameData {
    private final int gameID;
    private final String whiteUsername;
    private final String blackUsername;
    private final ChessGame game;

    @Override
    public String toString() {
        return "GameData{" +
                "gameID=" + gameID +
                ", whiteUsername='" + whiteUsername + '\'' +
                ", blackUsername='" + blackUsername + '\'' +
                ", game=" + game +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameData gameData = (GameData) o;
        return gameID == gameData.gameID && Objects.equals(whiteUsername, gameData.whiteUsername) && Objects.equals(blackUsername, gameData.blackUsername) && Objects.equals(game, gameData.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID, whiteUsername, blackUsername, game);
    }

    public int getGameID() {
        return gameID;
    }

    public String getWhiteUsername() {
        return whiteUsername;
    }

    public String getBlackUsername() {
        return blackUsername;
    }

    public ChessGame getGame() {
        return game;
    }

    public GameData(int gameID, String whiteUsername, String blackUsername, ChessGame game) {
        this.gameID = gameID;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.game = game;
    }
}
