package dataaccess.dataAccessObject;

import dataaccess.serverException.DataAccessException;
import model.UserData;

public interface UserDAO {
    public UserData GetUserData(String username) throws Exception;

    public void CreateUser(UserData userData) throws Exception;
}
