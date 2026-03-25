package dataaccess.dataaccessobject;

import model.result.GameRequest;
import model.GameData;
import model.result.GameMetaData;

import java.util.List;

public interface GameDAO {
    int createGame(GameRequest gameReq) throws Exception;

    GameData getGame(int gameID) throws Exception;

    List<GameMetaData> listGames() throws Exception;
    public void update(GameData game) throws Exception;
    public void flush() throws Exception;
}