package handler;

import io.javalin.http.Context;
import model.UserData;
import service.UserResult;
import service.UserService;

public class UserHandler {
    public void RegisterUser(Context cxt) {
        UserData uData = cxt.bodyAsClass(UserData.class);
        UserService uService = new UserService();
        UserResult result = uService.RegisterUser(uData);

    }

    public void LoginUser(Context cxt) {
        UserData userData = cxt.bodyAsClass(UserData.class);
    }

    public void LogOutUser(Context cxt) {
        UserData userData = cxt.bodyAsClass(UserData.class);
    }
}
