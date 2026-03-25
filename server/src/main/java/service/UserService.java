package service;

import dataaccess.dataaccessobject.AuthDAO;
import dataaccess.dataaccessobject.UserDAO;
import dataaccess.database.DBAuthDAO;
import dataaccess.database.DBUserDAO;
import dataaccess.serverexception.BadRequestException;
import dataaccess.serverexception.UserAlreadyExistsException;
import dataaccess.serverexception.UserUnauthorizedException;
import model.UserData;
import org.mindrot.jbcrypt.BCrypt;
import model.result.UserResult;

public class UserService {
    private final UserDAO userDAO = new DBUserDAO();
    private final AuthDAO authDAO = new DBAuthDAO();


    public UserResult registerUser(UserData uData) throws Exception {
        if(uData.getUsername()==null||uData.getPassword()==null||uData.getEmail()==null){
            throw new BadRequestException("Error: bad request");
        }

        UserData userServerData = userDAO.getUserData(uData.getUsername());
        if (userServerData != null) {
            throw new UserAlreadyExistsException("Error: already taken");
        }
        userDAO.createUser(uData);
        String authToken = authDAO.createAuthToken(uData.getUsername());
        return new UserResult(200, "", uData.getUsername(), authToken);
    }

    public UserResult loginUser(UserData uData) throws Exception {
        if (uData.getUsername()==null||uData.getUsername().isEmpty()||
                uData.getPassword()==null||uData.getPassword().isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }
        UserData userServerData = userDAO.getUserData(uData.getUsername());

        if (userServerData == null || !BCrypt.checkpw(uData.getPassword(), userServerData.getPassword())) {
            throw new UserUnauthorizedException("Error: unauthorized");
        }

        String authToken = authDAO.createAuthToken(uData.getUsername());
        return new UserResult(200, "", uData.getUsername(), authToken);
    }

    public UserResult logoutUser(String authToken) throws Exception {
        if(authToken==null){
            throw new UserUnauthorizedException("Error: unauthorized");
        }
        String username = authDAO.getAuthUsername(authToken);
        authDAO.deleteAuthToken(authToken);
        return new UserResult(200, "", "", "");
    }


}
