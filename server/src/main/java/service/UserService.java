package service;

import dataaccess.DataAccessException;
import dataaccess.UserAlreadyExistsException;
import dataaccess.UserDAO;
import model.UserData;

public class UserService {
    private UserDAO userDAO;

    public UserResult RegisterUser(UserData uData) {

        try {
            userDAO.GetUserData(uData.getUsername());
            userDAO.CreateUser(uData);
            String authToken = userDAO.CreateAuthToken(uData.getUsername());
            return new UserResult(0, "", uData.getUsername(), authToken);
        } catch (UserAlreadyExistsException e) {
            return new UserResult(403, "User Already Taken", "", "");
        } catch (DataAccessException e) {
            return new UserResult(0, "", "", "");//temp
        }
    }
}
