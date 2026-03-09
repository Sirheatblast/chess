package dataaccess.database;

import dataaccess.dataaccessobject.GameDAO;
import handler.GameRequest;
import model.GameData;
import service.result.GameMetaData;

import java.util.List;

public class DBGameDAO implements GameDAO {
    @Override
    public int createGame(GameRequest gameReq) throws Exception {
        return 0;
    }

    @Override
    public GameData getGame(int gameID) throws Exception {
        return null;
    }

    @Override
    public List<GameMetaData> listGames() throws Exception {
        return List.of();
    }

    @Override
    public void flush() throws Exception {

    }
}
