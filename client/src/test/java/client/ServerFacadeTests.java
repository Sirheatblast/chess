package client;

import model.AuthData;
import model.result.GameListResult;
import model.result.UserResult;
import org.junit.jupiter.api.*;
import server.Server;
import static org.junit.jupiter.api.Assertions.*;

public class ServerFacadeTests {

    private static Server server;
    private static ServerFacade facade;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(8080);
        System.out.println("Started test HTTP server on " + port);
        facade = new ServerFacade();
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }

    @Test
    void createUserSuccess() throws Exception {
        UserResult result = facade.createUser("GoodName","StrongPassword","Email");
        assert(result.getStatus()==200);
    }

    @Test
    void createUserFail() throws Exception {
        UserResult result = facade.createUser(null,"StrongPassword","Email");
        assert(result.getStatus()!=200);
    }

    @Test
    void loginUserSuccess() throws Exception {
        UserResult result = facade.loginUser("GoodName","StrongPassword");
        assert(result.getStatus()==200);
    }

    @Test
    void loginUserFail() throws Exception {
        UserResult result = facade.loginUser(null,"StrongPassword");
        assert(result.getStatus()!=200);
    }

    @Test
    void logoutUserSuccess() {
        Assertions.assertDoesNotThrow(()->{
            UserResult result = facade.loginUser("GoodName","StrongPassword");
            AuthData authData = new AuthData(result.getAuthToken(),result.getUsername());
            facade.logoutUser(authData);
        });
    }

    @Test
    void logoutUserFail() throws Exception {
        AuthData authData = new AuthData("Bad Token","Jim");
        UserResult result = facade.logoutUser(authData);
        assert(result.getStatus()!=200);
    }

    @Test
    void getGamesSuccess() throws Exception {
        UserResult userResult = facade.loginUser("GoodName","StrongPassword");
        GameListResult result = facade.getGames(new AuthData(userResult.getAuthToken(), userResult.getUsername()));
        assert (result.getStatus()==200);
    }

    @Test
    void getGamesFailure() throws Exception {
        GameListResult result = facade.getGames(new AuthData("Bad TOken", "Bad Name"));
        assert (result.getStatus()!=200);
    }

    @Test
    void createGame() {
    }

    @Test
    void joinGame() {
    }

    @Test
    void observeGame() {
    }

}
