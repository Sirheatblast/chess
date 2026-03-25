package client;

import model.result.UserResult;
import org.junit.jupiter.api.*;
import server.Server;

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
    void logoutUser() {
    }

    @Test
    void getGames() {
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
