package dataaccess.local;

import chess.ChessGame;
import dataaccess.dataAccessObject.GameDAO;
import handler.GameRequest;
import model.GameData;
import service.result.GameResult;

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
        gameDatamap.put(gameID,new GameData(gameID,"","",new ChessGame(), gameReq.getGameName()));
        nextIndex++;
        return gameID;
    }

    @Override
    public GameData GetGame(int gameID) throws Exception {
        return gameDatamap.get(gameID);
    }

    @Override
    public List<GameData> ListGames() throws Exception {
        return new ArrayList<GameData>(gameDatamap.values());
    }

    @Override
    public void UpdateGame(int gameID, GameRequest gameReq) throws Exception {

    }
}
