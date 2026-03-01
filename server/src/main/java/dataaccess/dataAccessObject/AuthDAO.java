package dataaccess.dataAccessObject;

import dataaccess.serverException.DataAccessException;
import model.AuthData;

public interface AuthDAO {
    public String GetAuthUsername(String authToken) throws Exception;

    public String CreateAuthToken(String username) throws Exception;

    public void DeleteAuthToken(String authToken) throws Exception;
}
