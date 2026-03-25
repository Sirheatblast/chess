package client;

import com.google.gson.Gson;
import model.*;
import model.result.*;
import serverinterface.ServerInterface;

public class ServerFacade {
    private static final Gson GSON = new Gson();
    private static ServerInterface inter;

    public ServerFacade(String url) {
        inter = new ServerInterface(url);
    }

    public UserResult createUser(String username,String password, String email) throws Exception {
        UserData desiredUser = new UserData(username,password,email);
        String asJson = GSON.toJson(desiredUser);
        return GSON.fromJson(inter.createUser(asJson),UserResult.class);
    }

    public UserResult loginUser(String username, String password) throws Exception{
        UserData desiredUser = new UserData(username,password,null);
        String asJson = GSON.toJson(desiredUser);
        return GSON.fromJson(inter.loginUser(asJson),UserResult.class);
    }

    public UserResult logoutUser(AuthData authData) throws Exception{
        return GSON.fromJson(inter.logoutUser(authData),UserResult.class);
    }

    public GameListResult getGames(AuthData authData) throws Exception{
        return GSON.fromJson(inter.getGames(authData),GameListResult.class);
    }

    public GameResult createGame(AuthData authData, GameRequest gameReq)throws Exception{
        String toJson = GSON.toJson(gameReq);
        return GSON.fromJson(inter.createGame(authData,toJson),GameResult.class);
    }

    public GameResult joinGame(AuthData authData,int gameId,String dColor)throws Exception{
        GameRequest gameReq = new GameRequest(null,dColor,gameId);
        String toJson = GSON.toJson(gameReq);
        return GSON.fromJson(inter.joinGame(authData,toJson),GameResult.class);
    }

    public GameResult observeGame(AuthData authData,int gameId)throws Exception{
        GameRequest gameReq = new GameRequest(null,"OBSERVER",gameId);
        String toJson = GSON.toJson(gameReq);
        return GSON.fromJson(inter.joinGame(authData,toJson),GameResult.class);
    }
}
