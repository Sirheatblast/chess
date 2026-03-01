package service;

import dataaccess.dataAccessObject.AuthDAO;
import dataaccess.dataAccessObject.UserDAO;
import dataaccess.local.LocalAuthDAO;
import dataaccess.local.LocalUserDAO;
import dataaccess.serverException.BadRequestException;
import dataaccess.serverException.UserAlreadyExistsException;
import dataaccess.serverException.UserUnauthorizedException;
import model.UserData;
import service.result.UserResult;

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
        return new UserResult(200, "", uData.getUsername(), authToken);
    }

    public UserResult LoginUser(UserData uData) throws Exception {
        UserData userServerData = userDAO.GetUserData(uData.getUsername());
        if (userServerData == null) {
            throw new BadRequestException("Error: bad request");
        }
        if (!uData.getPassword().equals(userServerData.getPassword())) {
            throw new UserUnauthorizedException("Error: unauthorized");
        }

        String authToken = authDAO.CreateAuthToken(uData.getUsername());
        return new UserResult(200, "", uData.getUsername(), authToken);
    }

    public UserResult LogoutUser(String authToken) throws Exception {
        String username = authDAO.GetAuthUsername(authToken);
        authDAO.DeleteAuthToken(authToken);
        return new UserResult(200, "", "", "");
    }


}
