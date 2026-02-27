package dataaccess.dataAccessObject;

import dataaccess.serverException.DataAccessException;
import model.AuthData;

public interface AuthDAO {
    public String GetAuthKey(String username) throws Exception;

    public String CreateAuthToken(String username) throws Exception;

    public void DeleteAuthToken(AuthData authData) throws Exception;
}
