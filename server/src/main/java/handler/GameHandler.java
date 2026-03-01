package handler;

import com.google.gson.Gson;
import dataaccess.serverException.BadRequestException;
import dataaccess.serverException.UserAlreadyExistsException;
import dataaccess.serverException.UserUnauthorizedException;
import io.javalin.http.Context;
import service.GameService;
import service.result.GameListResult;
import service.result.GameResult;

public class GameHandler {
    public void GetGameList(Context cxt) {
        GameListResult result;
        Gson gson = new Gson();
        try {
            String authToken = cxt.header("authorization");
            if (authToken == null) {
                throw new UserUnauthorizedException("Error: unauthorized");
            }
            GameService gameService = new GameService();
            result = gameService.GetGameList();

        } catch (UserUnauthorizedException e) {
            result = new GameListResult(401, e.getMessage(), null);
        } catch (Exception e) {
            result = new GameListResult(500, e.getMessage(), null);
        }

        String resultJson = gson.toJson(result);
        cxt.result(resultJson);
    }

    public void CreateGame(Context cxt) {
        GameResult result;
        Gson gson = new Gson();
        try {
            String authToken = cxt.header("authorization");
            if (authToken == null) {
                throw new UserUnauthorizedException("Error: unauthorized");
            }
            GameRequest gameReq = gson.fromJson(cxt.body(), GameRequest.class);

            GameService gameService = new GameService();
            result = gameService.CreateGame(authToken, gameReq);

        } catch (BadRequestException e) {
            result = new GameResult(400, e.getMessage(), null);
        } catch (UserUnauthorizedException e) {
            result = new GameResult(401, e.getMessage(), null);
        } catch (Exception e) {
            result = new GameResult(500, e.getMessage(), null);
        }

        String resultJson = gson.toJson(result);
        cxt.result(resultJson);
    }

    public void JoinGame(Context cxt) {
        GameResult result;
        Gson gson = new Gson();
        try {
            String authToken = cxt.header("authorization");
            if (authToken == null) {
                throw new UserUnauthorizedException("Error: unauthorized");
            }
            GameRequest gameReq = gson.fromJson(cxt.body(), GameRequest.class);

            GameService gameService = new GameService();
            result = gameService.JoinGame(authToken, gameReq);

        } catch (BadRequestException e) {
            result = new GameResult(400, e.getMessage(), null);
        } catch (UserUnauthorizedException e) {
            result = new GameResult(401, e.getMessage(), null);
        } catch (UserAlreadyExistsException e) {
            result = new GameResult(403, e.getMessage(), null);
        } catch (Exception e) {
            result = new GameResult(500, e.getMessage(), null);
        }

        String resultJson = gson.toJson(result);
        cxt.result(resultJson);
    }
}
