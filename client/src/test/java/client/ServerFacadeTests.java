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
    private static final ServerFacade FACADE = new ServerFacade();

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(8080);
        System.out.println("Started test HTTP server on " + port);
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }

    @BeforeEach
    void doBefore() throws Exception{
        try{
            FACADE.createUser("GoodName","StrongPassword","email");
        } catch (Exception _) {
        }
    }

    @Test
    void createUserSuccess() throws Exception {
        String randomUsername = UUID.randomUUID().toString();
        UserResult result = FACADE.createUser(randomUsername,"StrongPassword","Email");
        assert(result.getStatus()==200);
    }

    @Test
    void createUserFail() throws Exception {
        UserResult result = FACADE.createUser(null,"StrongPassword","Email");
        assert(result.getStatus()!=200);
    }

    @Test
    void loginUserSuccess() throws Exception {
        UserResult result = FACADE.loginUser("GoodName","StrongPassword");
        assert(result.getStatus()==200);
    }

    @Test
    void loginUserFail() throws Exception {
        UserResult result = FACADE.loginUser(null,"StrongPassword");
        assert(result.getStatus()!=200);
    }

    @Test
    void logoutUserSuccess() {
        Assertions.assertDoesNotThrow(()->{
            UserResult result = FACADE.loginUser("GoodName","StrongPassword");
            AuthData authData = new AuthData(result.getAuthToken(),result.getUsername());
            FACADE.logoutUser(authData);
        });
    }

    @Test
    void logoutUserFail() throws Exception {
        AuthData authData = new AuthData("Bad Token","Jim");
        UserResult result = FACADE.logoutUser(authData);
        assert(result.getStatus()!=200);
    }

    @Test
    void getGamesSuccess() throws Exception {
        UserResult userResult = FACADE.loginUser("GoodName","StrongPassword");
        GameListResult result = FACADE.getGames(new AuthData(userResult.getAuthToken(), userResult.getUsername()));
        assert (result.getStatus()==200);
    }

    @Test
    void getGamesFailure() throws Exception {
        GameListResult result = FACADE.getGames(new AuthData("Bad TOken", "Bad Name"));
        assert (result.getStatus()!=200);
    }

    @Test
    void createGameSuccess() throws Exception {
        UserResult userResult = FACADE.loginUser("GoodName","StrongPassword");
        GameRequest gameReq = new GameRequest("GameNameGoesHere",null,0);
        GameResult result = FACADE.createGame(new AuthData(userResult.getAuthToken(), userResult.getUsername()),gameReq);
        assert (result.getStatus()==200);
    }

    @Test
    void createGameFailure() throws Exception {
        AuthData authToken = new AuthData("BadTOken","Hello");
        GameRequest gameReq = new GameRequest("GameNameGoesHere",null,0);
        GameResult result = FACADE.createGame(authToken,gameReq);
        assert (result.getStatus()!=200);
    }

    @Test
    void joinGameSuccess() throws Exception {
        UserResult userResult = FACADE.loginUser("GoodName","StrongPassword");
        AuthData authToken =  new AuthData(userResult.getAuthToken(), userResult.getUsername());
        GameRequest gameReq = new GameRequest("GameNameGoesHere",null,0);
        GameResult gameResult = FACADE.createGame(authToken,gameReq);
        GameResult result = FACADE.joinGame(new AuthData(userResult.getAuthToken(), userResult.getUsername()), gameResult.getGameID(), "WHITE");
        assert (result.getStatus()==200);
    }

    @Test
    void joinGameFailure() throws Exception {
        UserResult userResult = FACADE.loginUser("GoodName","StrongPassword");
        AuthData authToken =  new AuthData(userResult.getAuthToken(), userResult.getUsername());
        GameResult result = FACADE.joinGame(new AuthData(userResult.getAuthToken(), userResult.getUsername()), 1, "WHITE");
        assert (result.getStatus()!=200);
    }

    @Test
    void observeGameSuccess() throws Exception {
        UserResult userResult = FACADE.loginUser("GoodName","StrongPassword");
        AuthData authToken =  new AuthData(userResult.getAuthToken(), userResult.getUsername());
        GameResult result = FACADE.observeGame(authToken, 1);
        assert (result.getStatus()==200);
    }

    @Test
    void observeGameFailure() throws Exception{
        UserResult userResult = FACADE.loginUser("GoodName","StrongPassword");
        AuthData authToken =  new AuthData("Bad Token", userResult.getUsername());
        GameResult result = FACADE.observeGame(authToken, 1);
        assert (result.getStatus()!=200);
    }

}
