package dataaccess.local;

import chess.ChessGame;
import dataaccess.dataAccessObject.GameDAO;
import handler.GameRequest;
import model.GameData;
import service.result.GameMetaData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalGameDAO implements GameDAO {
    private final static Map<Integer, GameData> gameDatamap = new HashMap<>();
    int nextIndex = 0;

    @Override
    public int CreateGame(GameRequest gameReq) throws Exception {
        int gameID = nextIndex;
        gameDatamap.put(gameID, new GameData(gameID, "", "", new ChessGame(), gameReq.getGameName()));
        nextIndex++;
        return gameID;
    }

    @Override
    public GameData GetGame(int gameID) throws Exception {
        return gameDatamap.get(gameID);
    }

    @Override
    public List<GameMetaData> ListGames() throws Exception {
        List<GameMetaData> games = new ArrayList<>();
        for (GameData gameData : gameDatamap.values()) {
            games.add(new GameMetaData(gameData.getGameID(), gameData.getName(),
                    gameData.getWhiteUsername(), gameData.getBlackUsername()));
        }
        return games;
    }

    @Override
    public void UpdateGame(int gameID, GameRequest gameReq) throws Exception {

    }
}
