package service;

import dataaccess.dataAccessObject.AuthDAO;
import dataaccess.dataAccessObject.GameDAO;
import dataaccess.local.LocalAuthDAO;
import dataaccess.local.LocalGameDAO;
import model.GameData;
import service.result.GameListResult;

import java.util.ArrayList;

public class GameService {
    private final GameDAO gameDAO = new LocalGameDAO();
    private final AuthDAO authDAO = new LocalAuthDAO();

    public GameListResult GetGameList() {
        return new GameListResult(200,"",new ArrayList<GameData>());
    }
}
