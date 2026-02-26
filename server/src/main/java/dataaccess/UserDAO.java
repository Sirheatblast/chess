package dataaccess;

import model.AuthData;
import model.UserData;

public interface UserDAO {
    public UserData GetUserData(String username) throws DataAccessException;

    public void CreateUser(UserData userData) throws DataAccessException;

    public String CreateAuthToken(AuthData authData) throws DataAccessException;

    public void DeleteAuthToken(AuthData authData) throws DataAccessException;
}
