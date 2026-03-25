package client;

import com.google.gson.Gson;
import model.*;
import model.result.GameListResult;
import model.result.GameMetaData;
import model.result.UserResult;
import serverInterface.ServerInterface;

import java.util.Collection;
import java.util.List;

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

    public List<GameMetaData> getGames(AuthData authData) throws Exception{

        return null;
    }

    public void createGame(AuthData authData,String gameName)throws Exception{

    }

    public GameData joinGame(AuthData authData,int gameId,String dColor,String username)throws Exception{

        return null;
    }

    public GameData observeGame(AuthData authData,int gameId)throws Exception{
        return null;
    }
}
