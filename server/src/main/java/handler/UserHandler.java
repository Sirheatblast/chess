package handler;

import dataaccess.DataAccessException;
import dataaccess.UserAlreadyExistsException;
import io.javalin.http.Context;
import model.UserData;
import service.UserResult;
import service.UserService;

public class UserHandler {
    public void RegisterUser(Context cxt) {
        UserResult result;
        try{
            UserData uData = cxt.bodyAsClass(UserData.class);
            UserService uService = new UserService();
            result = uService.RegisterUser(uData);
        }
        catch (UserAlreadyExistsException e){
            result = new UserResult(403,e.getMessage(),"","");
        }
        catch (DataAccessException e){
            result = new UserResult(cxt.statusCode(),e.getMessage(),"","");
        }



    }

    public void LoginUser(Context cxt) {
        UserData userData = cxt.bodyAsClass(UserData.class);
    }

    public void LogOutUser(Context cxt) {
        UserData userData = cxt.bodyAsClass(UserData.class);
    }
}
