package dataaccess.database;

import dataaccess.dataaccessobject.UserDAO;
import model.UserData;

public class DBUserDAO implements UserDAO {
    @Override
    public UserData getUserData(String username) throws Exception {
        return null;
    }

    @Override
    public void createUser(UserData userData) throws Exception {

    }

    @Override
    public void flush() throws Exception {

    }
}
