package dataaccess.local;

import chess.ChessGame;
import dataaccess.dataaccessobject.GameDAO;
import handler.GameRequest;
import model.GameData;
import service.result.GameMetaData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalGameDAO implements GameDAO {
    private final static Map<Integer, GameData> gameDatamap = new HashMap<>();
    private static int nextIndex = 1;

    @Override
    public int createGame(GameRequest gameReq) throws Exception {
        int gameID = nextIndex;
        gameDatamap.put(gameID, new GameData(gameID, null, null, new ChessGame(), gameReq.getGameName()));
        nextIndex=nextIndex+1;
        return gameID;
    }

    @Override
    public GameData getGame(int gameID) throws Exception {
        return gameDatamap.get(gameID);
    }

    @Override
    public List<GameMetaData> listGames() throws Exception {
        List<GameMetaData> games = new ArrayList<>();
        for (GameData gameData : gameDatamap.values()) {
            games.add(new GameMetaData(gameData.getGameID(), gameData.getName(),
                    gameData.getWhiteUsername(), gameData.getBlackUsername()));
        }
        return games;
    }

    @Override
    public void updateGame(int gameID, GameRequest gameReq) throws Exception {

    }

    @Override
    public void flush() throws Exception {
        gameDatamap.clear();
    }
}
