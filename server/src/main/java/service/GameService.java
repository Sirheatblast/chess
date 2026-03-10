package service;

import dataaccess.dataaccessobject.AuthDAO;
import dataaccess.dataaccessobject.GameDAO;
import dataaccess.database.DBAuthDAO;
import dataaccess.database.DBGameDAO;
import dataaccess.local.LocalAuthDAO;
import dataaccess.local.LocalGameDAO;
import dataaccess.serverexception.BadRequestException;
import dataaccess.serverexception.UserAlreadyExistsException;
import handler.GameRequest;
import model.GameData;
import service.result.GameListResult;
import service.result.GameResult;


public class GameService {
    private final GameDAO gameDAO = new DBGameDAO();
    private final AuthDAO authDAO = new DBAuthDAO();

    public GameListResult getGameList(String authToken) throws Exception {
        authDAO.getAuthUsername(authToken);
        return new GameListResult(200, "", gameDAO.listGames());
    }

    public GameResult createGame(String authToken, GameRequest gameReq) throws Exception {
        authDAO.getAuthUsername(authToken);
        if(gameReq.getGameName()==null){
            throw new BadRequestException("Error: bad request");
        }

        return new GameResult(200, "", gameDAO.createGame(gameReq));
    }

    public GameResult joinGame(String authToken, GameRequest gameReq) throws Exception {
        String username = authDAO.getAuthUsername(authToken);
        GameData gameData = gameDAO.getGame(gameReq.getGameID());
        if (gameData == null || gameReq.getPlayerColor()==null ||
                !gameReq.getPlayerColor().equals("WHITE") && !gameReq.getPlayerColor().equals("BLACK")) {
            throw new BadRequestException("Error: bad request");
        }
        if (gameReq.getPlayerColor().equals("WHITE")) {
            if (gameData.getWhiteUsername()!=null) {
                throw new UserAlreadyExistsException("Error: already taken");
            }
            gameData.setWhiteUsername(username);
        } else {
            if (gameData.getBlackUsername() != null) {
                throw new UserAlreadyExistsException("Error: already taken");
            }
            gameData.setBlackUsername(username);
        }
        return new GameResult(200, "", null);
    }
}
