package dataaccess.dataaccessobject;

import model.UserData;

public interface UserDAO {
    public UserData getUserData(String username) throws Exception;

    public void createUser(UserData userData) throws Exception;

    public void flush()throws Exception;
}
