package handler;

import com.google.gson.Gson;
import dataaccess.serverException.BadRequestException;
import dataaccess.serverException.DataAccessException;
import dataaccess.serverException.UserAlreadyExistsException;
import dataaccess.serverException.UserUnauthorizedException;
import io.javalin.http.Context;
import model.AuthData;
import model.UserData;
import service.UserResult;
import service.UserService;

public class UserHandler {
    public void RegisterUser(Context cxt) {
        UserResult result;
        Gson gson = new Gson();

        try{
            UserData uData = gson.fromJson(cxt.body(),UserData.class);
            UserService uService = new UserService();
            result = uService.RegisterUser(uData);
        }
        catch (UserAlreadyExistsException e){
            result = new UserResult(403,e.getMessage(),"","");
        }
        catch (BadRequestException e){
            result = new UserResult(400,e.getMessage(),"","");
        }
        catch (Exception e){
            result = new UserResult(500,e.getMessage(),"","");
        }

        String resultJson = gson.toJson(result);
        cxt.result(resultJson);
    }

    public void LoginUser(Context cxt) {
        UserResult result;
        Gson gson = new Gson();

        try{
            UserData uData = gson.fromJson(cxt.body(),UserData.class);
            UserService uService = new UserService();
            result = uService.LoginUser(uData);
        }
        catch (UserUnauthorizedException e){
            result = new UserResult(401,e.getMessage(),"","");
        }
        catch (BadRequestException e){
            result = new UserResult(400,e.getMessage(),"","");
        }
        catch (Exception e){
            result = new UserResult(500,e.getMessage(),"","");
        }

        String resultJson = gson.toJson(result);
        cxt.result(resultJson);
    }

    public void LogOutUser(Context cxt) {
        UserResult result;
        Gson gson = new Gson();

        try{
            String authToken = cxt.header("authorization");
            if(authToken==null){
                throw new UserUnauthorizedException("Error: unauthorized");
            }

            UserService uService = new UserService();
            result = uService.LogoutUser(authToken);
        }
        catch (UserUnauthorizedException e){
            result = new UserResult(401,e.getMessage(),"","");
        }
        catch (Exception e){
            result = new UserResult(500,e.getMessage(),"","");
        }

        String resultJson = gson.toJson(result);
        cxt.result(resultJson);
    }
}
