package dataaccess.dataAccessObject;

import handler.GameRequest;
import model.GameData;
import service.result.GameResult;

import java.util.List;

public interface GameDAO {
    int CreateGame(GameRequest gameReq) throws Exception;

    GameData GetGame(int gameID) throws Exception;

    List<GameData> ListGames() throws Exception;

    void UpdateGame(int gameID, GameRequest gameReq) throws Exception;
}