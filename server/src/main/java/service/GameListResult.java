package service;

import model.GameData;
import java.util.List;

public class GameListResult {
    private final int status;
    private final String message;
    private final List<GameData> games;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<GameData> getGames() {
        return games;
    }

    public GameListResult(int status, String message, List<GameData> games) {
        this.status = status;
        this.message = message;
        this.games = games;
    }
}
