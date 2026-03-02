package server;

import handler.DBHandler;
import handler.GameHandler;
import handler.UserHandler;
import io.javalin.*;

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
            gameHdlr.GetGameList(cxt);
        });

        javalin.post("/game", cxt -> {
            GameHandler gameHdlr = new GameHandler();
            gameHdlr.CreateGame(cxt);
        });

        javalin.put("/game", cxt -> {
            GameHandler gameHdlr = new GameHandler();
            gameHdlr.JoinGame(cxt);
        });

        javalin.delete("/db", cxt -> {
            DBHandler dbHdlr = new DBHandler();
            dbHdlr.ClearDB(cxt);
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
