package dataaccess.local;

import dataaccess.serverexception.BadRequestException;
import dataaccess.dataAccessObject.UserDAO;
import model.UserData;

import java.util.HashMap;
import java.util.Map;

public class LocalUserDAO implements UserDAO {
    private static final Map<String, UserData> internalUserData = new HashMap<>();

    @Override
    public UserData GetUserData(String username) throws Exception {
        if (username==null||username.isEmpty()) {
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

    @Override
    public void Flush() throws Exception {
        internalUserData.clear();
    }
}
