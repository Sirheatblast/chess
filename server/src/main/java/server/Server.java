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
            usrHdlr.registerUser(cxt);
        });

        javalin.post("/session", cxt -> {
            UserHandler usrHdlr = new UserHandler();
            usrHdlr.loginUser(cxt);
        });

        javalin.delete("/session", cxt -> {
            UserHandler usrHdlr = new UserHandler();
            usrHdlr.logoutUser(cxt);
        });

        javalin.get("/game", cxt -> {
            GameHandler gameHdlr = new GameHandler();
            gameHdlr.getGameList(cxt);
        });

        javalin.post("/game", cxt -> {
            GameHandler gameHdlr = new GameHandler();
            gameHdlr.createGame(cxt);
        });

        javalin.put("/game", cxt -> {
            GameHandler gameHdlr = new GameHandler();
            gameHdlr.joinGame(cxt);
        });

        javalin.delete("/db", cxt -> {
            DBHandler dbHdlr = new DBHandler();
            dbHdlr.clearDB(cxt);
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
