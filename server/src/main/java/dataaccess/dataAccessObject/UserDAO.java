package dataaccess.dataAccessObject;

import model.UserData;

public interface UserDAO {
    public UserData GetUserData(String username) throws Exception;

    public void CreateUser(UserData userData) throws Exception;

    public void Flush()throws Exception;
}
