package dataaccess.dataaccessobject;

import handler.GameRequest;
import model.GameData;
import service.result.GameMetaData;

import java.util.List;

public interface GameDAO {
    int createGame(GameRequest gameReq) throws Exception;

    GameData getGame(int gameID) throws Exception;

    List<GameMetaData> listGames() throws Exception;
    public void update(GameData game) throws Exception;
    public void flush() throws Exception;
}