package server;

import handler.UserHandler;
import io.javalin.*;
import model.UserData;

public class Server {

    private final Javalin javalin;

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"));

        // Register your endpoints and exception handlers here.
        javalin.post("/user", cxt -> {
            UserHandler usrHdlr = new UserHandler();
            usrHdlr.RegisterUser(cxt);
        });

        javalin.post("/session", cxt -> {
            UserHandler usrHdlr = new UserHandler();
            usrHdlr.LoginUser(cxt);
        });

        javalin.delete("/session", cxt -> {
            UserHandler usrHdlr = new UserHandler();
            usrHdlr.LogOutUser(cxt);
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
