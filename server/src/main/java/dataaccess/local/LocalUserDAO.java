package dataaccess.local;

import dataaccess.serverexception.BadRequestException;
import dataaccess.dataaccessobject.UserDAO;
import model.UserData;

import java.util.HashMap;
import java.util.Map;

public class LocalUserDAO implements UserDAO {
    private static final Map<String, UserData> INTERNAL_USER_DATA = new HashMap<>();

    @Override
    public UserData getUserData(String username) throws Exception {
        if (username==null||username.isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }
        return INTERNAL_USER_DATA.get(username);
    }

    @Override
    public void createUser(UserData userData) throws Exception {
        if (userData.getUsername().isEmpty() || userData.getPassword().isEmpty() || userData.getEmail().isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }
        INTERNAL_USER_DATA.put(userData.getUsername(), userData);
    }

    @Override
    public void flush() throws Exception {
        INTERNAL_USER_DATA.clear();
    }
}
