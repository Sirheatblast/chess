package service;

import dataaccess.dataaccessobject.AuthDAO;
import dataaccess.dataaccessobject.UserDAO;
import dataaccess.local.LocalAuthDAO;
import dataaccess.local.LocalUserDAO;
import dataaccess.serverexception.BadRequestException;
import dataaccess.serverexception.UserAlreadyExistsException;
import dataaccess.serverexception.UserUnauthorizedException;
import model.UserData;
import service.result.UserResult;

public class UserService {
    private final UserDAO userDAO = new LocalUserDAO();
    private final AuthDAO authDAO = new LocalAuthDAO();

    public UserResult RegisterUser(UserData uData) throws Exception {
        if(uData.getUsername()==null||uData.getPassword()==null||uData.getEmail()==null){
            throw new BadRequestException("Error: bad request");
        }

        UserData userServerData = userDAO.GetUserData(uData.getUsername());
        if (userServerData != null) {
            throw new UserAlreadyExistsException("Error: already taken");
        }
        userDAO.CreateUser(uData);
        String authToken = authDAO.CreateAuthToken(uData.getUsername());
        return new UserResult(200, "", uData.getUsername(), authToken);
    }

    public UserResult LoginUser(UserData uData) throws Exception {
        if (uData.getUsername()==null||uData.getUsername().isEmpty()||
                uData.getPassword()==null||uData.getPassword().isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }
        UserData userServerData = userDAO.GetUserData(uData.getUsername());

        if (userServerData == null || !uData.getPassword().equals(userServerData.getPassword())) {
            throw new UserUnauthorizedException("Error: unauthorized");
        }

        String authToken = authDAO.CreateAuthToken(uData.getUsername());
        return new UserResult(200, "", uData.getUsername(), authToken);
    }

    public UserResult LogoutUser(String authToken) throws Exception {
        if(authToken==null){
            throw new UserUnauthorizedException("Error: unauthorized");
        }
        String username = authDAO.GetAuthUsername(authToken);
        authDAO.DeleteAuthToken(authToken);
        return new UserResult(200, "", "", "");
    }


}
