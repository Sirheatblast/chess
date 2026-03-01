package service.result;

import java.util.List;

public class GameListResult {
    private final int status;
    private final String message;
    private final List<GameMetaData> games;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<GameMetaData> getGames() {
        return games;
    }

    public GameListResult(int status, String message, List<GameMetaData> games) {
        this.status = status;
        this.message = message;
        this.games = games;
    }
}
