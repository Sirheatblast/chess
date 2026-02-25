package server;

import handler.UserHandler;
import io.javalin.*;
import model.UserData;

public class Server {

    private final Javalin javalin;

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"));

        // Register your endpoints and exception handlers here.
        javalin.post("/user", hdr -> {
            UserData userData = hdr.bodyAsClass(UserData.class);
            UserHandler usrHdlr = new UserHandler();
            usrHdlr.RegisterUser(userData);
        });

        javalin.post("/session", hdr -> {
            UserData userData = hdr.bodyAsClass(UserData.class);
            UserHandler usrHdlr = new UserHandler();
            usrHdlr.LoginUser(userData);
        });

        javalin.delete("/session", hdr -> {
            UserData userData = hdr.bodyAsClass(UserData.class);
            UserHandler usrHdlr = new UserHandler();
            usrHdlr.LogOutUser(userData);
        });
    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }
}
