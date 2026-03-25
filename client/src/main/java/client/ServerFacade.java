package client;

import com.google.gson.Gson;
import model.*;
import model.result.GameListResult;
import model.result.GameMetaData;

import java.util.Collection;
import java.util.List;

public class ServerFacade {
    private static final Gson gson = new Gson();

    public AuthData createUser(String username,String password, String email){
        UserData desiredUser = new UserData(username,password,email);
        String asJson = gson.toJson(desiredUser);
        return null;
    }

    public AuthData loginUser(String username,String password){
        UserData desiredUser = new UserData(username,password,null);
        String asJson = gson.toJson(desiredUser);
        return null;
    }

    public void logoutUser(AuthData authData){

    }

    public List<GameMetaData> getGames(AuthData authData){

        return null;
    }

    public void createGame(AuthData authData,String gameName){

    }

    public GameData joinGame(AuthData authData,int gameId,String dColor,String username){

        return null;
    }

    public GameData observeGame(AuthData authData,int gameId){
        return null;
    }
}
