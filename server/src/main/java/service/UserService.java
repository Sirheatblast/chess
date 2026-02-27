package service;

import dataaccess.*;
import model.UserData;

public class UserService {
    private final UserDAO userDAO = new LocalUserDAO();

    public UserResult RegisterUser(UserData uData) throws DataAccessException {
        UserData userServerData = userDAO.GetUserData(uData.getUsername());
        if (userServerData != null) {
            throw new UserAlreadyExistsException("Error: already taken");
        }
        userDAO.CreateUser(uData);
        String authToken = userDAO.CreateAuthToken(uData.getUsername());
        return new UserResult(200,"",uData.getUsername(),authToken);
    }
}
