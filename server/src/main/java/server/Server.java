package server;

import handler.GameHandler;
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

        javalin.get("/game", cxt -> {
            GameHandler gameHdlr = new GameHandler();
        });

        javalin.post("/game", cxt -> {
            GameHandler gameHdlr = new GameHandler();
        });

        javalin.put("/game", cxt -> {
            GameHandler gameHdlr = new GameHandler();
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
