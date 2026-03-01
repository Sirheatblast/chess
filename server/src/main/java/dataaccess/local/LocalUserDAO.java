package dataaccess.local;

import dataaccess.serverException.BadRequestException;
import dataaccess.dataAccessObject.UserDAO;
import model.UserData;

import java.util.HashMap;
import java.util.Map;

public class LocalUserDAO implements UserDAO {
    private static final Map<String, UserData> internalUserData = new HashMap<>();

    @Override
    public UserData GetUserData(String username) throws Exception {
        if (username.isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }
        return internalUserData.get(username);
    }

    @Override
    public void CreateUser(UserData userData) throws Exception {
        if (userData.getUsername().isEmpty() || userData.getPassword().isEmpty() || userData.getEmail().isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }
        internalUserData.put(userData.getUsername(), userData);
    }
}
