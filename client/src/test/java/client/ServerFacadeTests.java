package client;

import model.AuthData;
import model.result.GameListResult;
import model.result.GameRequest;
import model.result.GameResult;
import model.result.UserResult;
import org.junit.jupiter.api.*;
import server.Server;

import java.util.UUID;

public class ServerFacadeTests {

    private static Server server;
    private static ServerFacade facade;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        facade = new ServerFacade("http://localhost:"+port+"/");
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }

    @BeforeEach
    void doBefore() throws Exception{
        try{
            facade.createUser("GoodName","StrongPassword","email");
        } catch (Exception e) {
            return;
        }
    }

    @Test
    void createUserSuccess() throws Exception {
        String randomUsername = UUID.randomUUID().toString();
        UserResult result = facade.createUser(randomUsername,"StrongPassword","Email");
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
    void createGameSuccess() throws Exception {
        UserResult userResult = facade.loginUser("GoodName","StrongPassword");
        GameRequest gameReq = new GameRequest("GameNameGoesHere",null,0);
        GameResult result = facade.createGame(new AuthData(userResult.getAuthToken(), userResult.getUsername()),gameReq);
        assert (result.getStatus()==200);
    }

    @Test
    void createGameFailure() throws Exception {
        AuthData authToken = new AuthData("BadTOken","Hello");
        GameRequest gameReq = new GameRequest("GameNameGoesHere",null,0);
        GameResult result = facade.createGame(authToken,gameReq);
        assert (result.getStatus()!=200);
    }

    @Test
    void joinGameSuccess() throws Exception {
        UserResult userResult = facade.loginUser("GoodName","StrongPassword");
        AuthData authToken =  new AuthData(userResult.getAuthToken(), userResult.getUsername());
        GameRequest gameReq = new GameRequest("GameNameGoesHere",null,0);
        GameResult gameResult = facade.createGame(authToken,gameReq);
        GameResult result = facade.joinGame(new AuthData(userResult.getAuthToken(), userResult.getUsername()), gameResult.getGameID(), "WHITE");
        assert (result.getStatus()==200);
    }

    @Test
    void joinGameFailure() throws Exception {
        UserResult userResult = facade.loginUser("GoodName","StrongPassword");
        AuthData authToken =  new AuthData(userResult.getAuthToken(), userResult.getUsername());
        GameResult result = facade.joinGame(new AuthData(userResult.getAuthToken(), userResult.getUsername()), 1, "WHITE");
        assert (result.getStatus()!=200);
    }

    @Test
    void observeGameSuccess() throws Exception {
        UserResult userResult = facade.loginUser("GoodName","StrongPassword");
        AuthData authToken =  new AuthData(userResult.getAuthToken(), userResult.getUsername());
        GameResult result = facade.observeGame(authToken, 1);
        assert (result.getStatus()==200);
    }

    @Test
    void observeGameFailure() throws Exception{
        UserResult userResult = facade.loginUser("GoodName","StrongPassword");
        AuthData authToken =  new AuthData("Bad Token", userResult.getUsername());
        GameResult result = facade.observeGame(authToken, 1);
        assert (result.getStatus()!=200);
    }

}
