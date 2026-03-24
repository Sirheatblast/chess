package client;

import com.google.gson.Gson;
import model.UserData;

public class ServerFacade {
    private static final Gson gson = new Gson();

    public UserData createUser(String username,String password, String email){
        UserData desiredUser = new UserData(username,password,email);
        String asJson = gson.toJson(desiredUser);
        return null;
    }
}
