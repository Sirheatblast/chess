package service;

import dataaccess.DataAccessException;
import dataaccess.LocalUserDAO;
import dataaccess.UserAlreadyExistsException;
import dataaccess.UserDAO;
import model.UserData;

public class UserService {
    private UserDAO userDAO = new LocalUserDAO();

    public UserResult RegisterUser(UserData uData) throws DataAccessException {
        UserData userServerData = userDAO.GetUserData(uData.getUsername());
        if (userServerData != null) {
            throw new UserAlreadyExistsException("User Already Taken");
        }
        userDAO.CreateUser(uData);
        String authToken = userDAO.CreateAuthToken(uData.getUsername());
        return new UserResult(200,"",uData.getUsername(),authToken);
    }
}
