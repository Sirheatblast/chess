package handler;

import com.google.gson.Gson;
import dataaccess.serverexception.BadRequestException;
import dataaccess.serverexception.UserAlreadyExistsException;
import dataaccess.serverexception.UserUnauthorizedException;
import io.javalin.http.Context;
import model.UserData;
import service.result.UserResult;
import service.UserService;

public class UserHandler {
    public void registerUser(Context cxt) {
        UserResult result;
        Gson gson = new Gson();

        try{
            UserData uData = gson.fromJson(cxt.body(),UserData.class);
            UserService uService = new UserService();
            result = uService.registerUser(uData);
        }
        catch (UserAlreadyExistsException e){
            result = new UserResult(403,e.getMessage(),null,null);
        }
        catch (BadRequestException e){
            result = new UserResult(400,e.getMessage(),null,null);
        }
        catch (Exception e){
            result = new UserResult(500,e.getMessage(),null,null);
        }

        String resultJson = gson.toJson(result);
        cxt.result(resultJson);
        cxt.status(result.getStatus());
    }

    public void loginUser(Context cxt) {
        UserResult result;
        Gson gson = new Gson();

        try{
            UserData uData = gson.fromJson(cxt.body(),UserData.class);
            UserService uService = new UserService();
            result = uService.loginUser(uData);
        }
        catch (UserUnauthorizedException e){
            result = new UserResult(401,e.getMessage(),null,null);
        }
        catch (BadRequestException e){
            result = new UserResult(400,e.getMessage(),null,null);
        }
        catch (Exception e){
            result = new UserResult(500,e.getMessage(),null,null);
        }

        String resultJson = gson.toJson(result);
        cxt.result(resultJson);
        cxt.status(result.getStatus());
    }

    public void logoutUser(Context cxt) {
        UserResult result;
        Gson gson = new Gson();

        try{
            String authToken = cxt.header("authorization");
            if(authToken==null){
                throw new UserUnauthorizedException("Error: unauthorized");
            }

            UserService uService = new UserService();
            result = uService.logoutUser(authToken);
        }
        catch (UserUnauthorizedException e){
            result = new UserResult(401,e.getMessage(),null,null);
        }
        catch (Exception e){
            result = new UserResult(500,e.getMessage(),null,null);
        }

        String resultJson = gson.toJson(result);
        cxt.result(resultJson);
        cxt.status(result.getStatus());
    }
}
