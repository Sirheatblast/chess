package service;

import dataaccess.dataAccessObject.AuthDAO;
import dataaccess.dataAccessObject.GameDAO;
import dataaccess.local.LocalAuthDAO;
import dataaccess.local.LocalGameDAO;
import dataaccess.serverException.BadRequestException;
import dataaccess.serverException.UserAlreadyExistsException;
import handler.GameRequest;
import model.GameData;
import service.result.GameListResult;
import service.result.GameResult;


public class GameService {
    private final GameDAO gameDAO = new LocalGameDAO();
    private final AuthDAO authDAO = new LocalAuthDAO();

    public GameListResult GetGameList() throws Exception {
        return new GameListResult(200, "", gameDAO.ListGames());
    }

    public GameResult CreateGame(String authToken, GameRequest gameReq) throws Exception {
        authDAO.GetAuthUsername(authToken);
        return new GameResult(200, "", gameDAO.CreateGame(gameReq));
    }

    public GameResult JoinGame(String authToken, GameRequest gameReq) throws Exception {
        String username = authDAO.GetAuthUsername(authToken);
        GameData gameData = gameDAO.GetGame(gameReq.getGameID());
        if (gameData == null || gameReq.getPlayerColor().isEmpty() ||
                !gameReq.getPlayerColor().equals("WHITE") && !gameReq.getPlayerColor().equals("BLACK")) {
            throw new BadRequestException("Error: bad request");
        }
        if (gameReq.getPlayerColor().equals("WHITE")) {
            if (!gameData.getWhiteUsername().isEmpty()) {
                throw new UserAlreadyExistsException("Error: already taken");
            }
            gameData.setWhiteUsername(username);
        } else {
            if (!gameData.getBlackUsername().isEmpty()) {
                throw new UserAlreadyExistsException("Error: already taken");
            }
            gameData.setBlackUsername(username);
        }
        return new GameResult(200, "", gameReq.getGameID());
    }
}
