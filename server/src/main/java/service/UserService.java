package service;

import dataaccess.dataAccessObject.AuthDAO;
import dataaccess.dataAccessObject.UserDAO;
import dataaccess.local.LocalAuthDAO;
import dataaccess.local.LocalUserDAO;
import dataaccess.serverException.BadRequestException;
import dataaccess.serverException.UserAlreadyExistsException;
import dataaccess.serverException.UserUnauthorizedException;
import model.AuthData;
import model.UserData;
import org.eclipse.jetty.server.Authentication;

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

    public UserResult LogoutUser(AuthData aData) throws Exception {
        String serverAuth = authDAO.GetAuthKey(aData.getUsername());
        if (!aData.getAuthToken().equals(serverAuth)) {
            throw new UserUnauthorizedException("Error: unauthorized");
        }

        return new UserResult(200, "", "", "");
    }


}
