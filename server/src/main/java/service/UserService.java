package service;

import dataaccess.dataAccessObject.AuthDAO;
import dataaccess.dataAccessObject.UserDAO;
import dataaccess.local.LocalAuthDAO;
import dataaccess.local.LocalUserDAO;
import dataaccess.serverException.DataAccessException;
import dataaccess.serverException.UserAlreadyExistsException;
import model.UserData;

public class UserService {
    private final UserDAO userDAO = new LocalUserDAO();
    private final AuthDAO authDAO = new LocalAuthDAO();

    public UserResult RegisterUser(UserData uData) throws Exception {
        UserData userServerData = userDAO.GetUserData(uData.getUsername());
        if (userServerData != null) {
            throw new UserAlreadyExistsException("Error: already taken");
        }
        userDAO.CreateUser(uData);
        String authToken = authDAO.CreateAuthToken(uData.getUsername());
        return new UserResult(200,"",uData.getUsername(),authToken);
    }
}
