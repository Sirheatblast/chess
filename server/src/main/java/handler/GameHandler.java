package handler;

import com.google.gson.Gson;
import dataaccess.serverException.UserUnauthorizedException;
import io.javalin.http.Context;
import service.GameService;
import service.result.GameListResult;

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

    }

    public void JoinGame(Context cxt) {

    }
}
