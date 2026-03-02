package dataaccess.dataaccessobject;

import handler.GameRequest;
import model.GameData;
import service.result.GameMetaData;

import java.util.List;

public interface GameDAO {
    int CreateGame(GameRequest gameReq) throws Exception;

    GameData GetGame(int gameID) throws Exception;

    List<GameMetaData> ListGames() throws Exception;

    void UpdateGame(int gameID, GameRequest gameReq) throws Exception;

    public void Flush() throws Exception;
}