package dataaccess.database;

import dataaccess.dataaccessobject.AuthDAO;

public class DBAuthDAO implements AuthDAO {
    @Override
    public String getAuthUsername(String authToken) throws Exception {
        return "";
    }

    @Override
    public String createAuthToken(String username) throws Exception {
        return "";
    }

    @Override
    public void deleteAuthToken(String authToken) throws Exception {

    }

    @Override
    public void flush() throws Exception {

    }
}
