package dataaccess;

import model.AuthData;
import model.UserData;

public interface UserDAO {
    public UserData GetUserData(String username);

    public void CreateUser(UserData userData) throws DataAccessException;

    public String CreateAuthToken(String username) throws DataAccessException;

    public void DeleteAuthToken(AuthData authData) throws DataAccessException;
}
