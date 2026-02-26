package service;

import dataaccess.DataAccessException;
import dataaccess.UserAlreadyExistsException;
import dataaccess.UserDAO;
import model.UserData;

public class UserService {
    private UserDAO userDAO;

    public UserResult RegisterUser(UserData uData) {

        try {
            UserData uServerData = userDAO.GetUserData(uData.getUsername());
            if(uServerData!=null){
                throw new UserAlreadyExistsException("User Already Taken");
            }
            userDAO.CreateUser(uData);
            String authToken = userDAO.CreateAuthToken(uData.getUsername());
            return new UserResult(200, "", uData.getUsername(), authToken);
        } catch (UserAlreadyExistsException e) {
            return new UserResult(403, e.getMessage(), "", "");
        } catch (DataAccessException e) {
            return new UserResult(0, e.getMessage(), "", "");//temp
        }
    }
}
