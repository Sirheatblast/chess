package client;

import com.google.gson.Gson;
import model.*;
import model.result.GameListResult;

import java.util.Collection;

public class ServerFacade {
    private static final Gson gson = new Gson();

    public UserData createUser(String username,String password, String email){
        UserData desiredUser = new UserData(username,password,email);
        String asJson = gson.toJson(desiredUser);
        return null;
    }

    public UserData loginUser(String username,String password){
        UserData desiredUser = new UserData(username,password,null);
        String asJson = gson.toJson(desiredUser);
        return null;
    }

    public GameListResult getGames(){

        return null;
    }

    public void joinGame(){

    }
}
