package dataaccess;

import model.AuthData;
import model.UserData;

import java.util.HashMap;
import java.util.Map;

public class LocalUserDAO implements UserDAO{
    private Map<String,UserData> internalUserData = new HashMap<>();

    @Override
    public UserData GetUserData(String username) {
        return internalUserData.get(username);
    }

    @Override
    public void CreateUser(UserData userData) throws DataAccessException {

    }

    @Override
    public String CreateAuthToken(String username) throws DataAccessException {
        return "";
    }

    @Override
    public void DeleteAuthToken(AuthData authData) throws DataAccessException {

    }
}
