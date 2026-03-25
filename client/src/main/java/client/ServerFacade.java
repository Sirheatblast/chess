package client;

import com.google.gson.Gson;
import model.*;
import model.result.*;
import serverInterface.ServerInterface;

public class ServerFacade {
    private static final Gson gson = new Gson();
    private static final ServerInterface inter = new ServerInterface();

    public UserResult createUser(String username,String password, String email) throws Exception {
        UserData desiredUser = new UserData(username,password,email);
        String asJson = gson.toJson(desiredUser);
        return gson.fromJson(inter.createUser(asJson),UserResult.class);
    }

    public UserResult loginUser(String username, String password) throws Exception{
        UserData desiredUser = new UserData(username,password,null);
        String asJson = gson.toJson(desiredUser);
        return gson.fromJson(inter.loginUser(asJson),UserResult.class);
    }

    public void logoutUser(AuthData authData) throws Exception{
        inter.logoutUser(authData);
    }

    public GameListResult getGames(AuthData authData) throws Exception{
        return gson.fromJson(inter.getGames(authData),GameListResult.class);
    }

    public GameResult createGame(AuthData authData, GameRequest gameReq)throws Exception{
        String toJson = gson.toJson(gameReq);
        return gson.fromJson(inter.createGame(authData,toJson),GameResult.class);
    }

    public GameResult joinGame(AuthData authData,int gameId,String dColor)throws Exception{
        GameRequest gameReq = new GameRequest(null,dColor,gameId);
        String toJson = gson.toJson(gameReq);
        return gson.fromJson(inter.observeGame(authData,toJson),GameResult.class);
    }

    public GameResult observeGame(AuthData authData,int gameId)throws Exception{
        GameRequest gameReq = new GameRequest(null,"OBSERVER",gameId);
        String toJson = gson.toJson(gameReq);
        return gson.fromJson(inter.observeGame(authData,toJson),GameResult.class);
    }
}
