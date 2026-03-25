package service;

import dataaccess.dataaccessobject.AuthDAO;
import dataaccess.dataaccessobject.GameDAO;
import dataaccess.dataaccessobject.UserDAO;
import dataaccess.database.DBAuthDAO;
import dataaccess.database.DBGameDAO;
import dataaccess.database.DBUserDAO;
import dataaccess.local.LocalAuthDAO;
import dataaccess.local.LocalGameDAO;
import dataaccess.local.LocalUserDAO;

import dataaccess.serverexception.BadRequestException;
import dataaccess.serverexception.UserAlreadyExistsException;
import dataaccess.serverexception.UserUnauthorizedException;
import handler.GameRequest;
import model.UserData;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

import model.result.GameListResult;
import model.result.GameResult;
import model.result.UserResult;

import java.util.Objects;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceTests {
    static GameDAO gameDAO = new DBGameDAO();
    static UserDAO userDAO = new DBUserDAO();
    static AuthDAO authDAO = new DBAuthDAO();

    @BeforeAll
    public static void initTest() throws Exception {
        authDAO.flush();
        gameDAO.flush();
        userDAO.flush();
    }

    @Test
    @Order(0)
    @DisplayName("AddUserSuccess")
    public void addUserSuccess() throws Exception {
        UserService userService = new UserService();
        UserData userData = new UserData("cool name", "cool password", "my email dude");
        UserResult result = userService.registerUser(userData);

        assert (result.getStatus() == 200);
        assert (userDAO.getUserData(result.getUsername()) != null);
    }

    @Test
    @Order(1)
    @DisplayName("AddUserFail")
    public void addUserFail() throws Exception {

        assertThrows(BadRequestException.class, () -> {
            UserService userService = new UserService();
            UserData userData = new UserData(null, "cool password", "my email dude");
            UserResult result = userService.registerUser(userData);
        });
    }

    @Test
    @Order(2)
    @DisplayName("LoginUserSuccess")
    public void loginUserSuccess() throws Exception {
        UserData userData = new UserData("cool name", "cool password", "my email dude");
        userDAO.createUser(userData);
        String authToken = authDAO.createAuthToken(userData.getUsername());
        UserService userService = new UserService();

        UserResult result = userService.loginUser(userData);
        assert (Objects.equals(userData.getUsername(), authDAO.getAuthUsername(authToken)));
        assert (result.getStatus() == 200);
    }

    @Test
    @Order(2)
    @DisplayName("LoginUserFail")
    public void loginUserFail() throws Exception {

        assertThrows(UserUnauthorizedException.class, () -> {
            UserService userService = new UserService();
            UserData userData = new UserData("cool name", "cool password", "my email dude");
            UserData loginData = new UserData("name", "cool password", "my email dude");

            UserResult result = userService.loginUser(loginData);
        });
    }

    @Test
    @Order(3)
    @DisplayName("LogoutUserSuccess")
    public void logoutUserSuccess() throws Exception {
        UserData userData = new UserData("cool name", "cool password", "my email dude");
        userDAO.createUser(userData);
        String authToken = authDAO.createAuthToken(userData.getUsername());
        UserService userService = new UserService();
        UserResult result = userService.logoutUser(authToken);
        assert (result.getStatus() == 200);
    }

    @Test
    @Order(4)
    @DisplayName("LogoutUserFail")
    public void logoutUserFail() throws Exception {

        assertThrows(UserUnauthorizedException.class, () -> {
            UserService userService = new UserService();
            UserResult result = userService.logoutUser(null);
        });
    }

    @Test
    @Order(5)
    @DisplayName("GetGameListSuccess")
    public void getGameListSuccess() throws Exception {
        GameService gameService = new GameService();
        String authToken = authDAO.createAuthToken("User1");
        gameService.createGame(authToken, new GameRequest("Game1", null, 1));

        GameListResult result = gameService.getGameList(authToken);
        assert (result.getGames().size() == 1);
        assert (result.getStatus() == 200);
    }

    @Test
    @Order(6)
    @DisplayName("GetGameListFail")
    public void getGameListFail() throws Exception {

        assertThrows(UserUnauthorizedException.class, () -> {
            GameService gameService = new GameService();
            String authToken = "BadToken";
            gameService.createGame(authToken, new GameRequest("Game1", null, 1));

            GameListResult result = gameService.getGameList(authToken);
        });
    }

    @Test
    @Order(7)
    @DisplayName("CreateGameSuccess")
    public void createGameSuccess() throws Exception {
        GameService gameService = new GameService();
        String authToken = authDAO.createAuthToken("User1");
        GameResult result = gameService.createGame(authToken, new GameRequest("Game1",
                null, 1));
        assert (result.getStatus() == 200);
    }

    @Test
    @Order(8)
    @DisplayName("CreateGameFail")
    public void createGameFail() throws Exception {

        assertThrows(UserUnauthorizedException.class, () -> {
            GameService gameService = new GameService();
            String authToken = "BadToken";
            GameResult result = gameService.createGame(authToken, new GameRequest("Game1",
                    null, 1));
        });
    }

    @Test
    @Order(7)
    @DisplayName("JoinGameSuccess")
    public void joinGameSuccess() throws Exception {
        GameService gameService = new GameService();
        String authToken = authDAO.createAuthToken("User1");
        gameService.createGame(authToken,
                new GameRequest("Game1", null, 1));
        GameResult result = gameService.joinGame(authToken,
                new GameRequest("Game1", "WHITE", 1));
        assert (result.getStatus() == 200);
    }

    @Test
    @Order(8)
    @DisplayName("JoinGameFail")
    public void joinGameFail() throws Exception {

        assertThrows(UserAlreadyExistsException.class, () -> {
            GameService gameService = new GameService();
            String authToken = authDAO.createAuthToken("User1");
            gameService.createGame(authToken,
                    new GameRequest("Game1", null, 1));
            gameService.joinGame(authToken, new GameRequest("Game1", "WHITE", 1));
            GameResult result = gameService.joinGame(authToken,
                    new GameRequest("Game1", "WHITE", 1));
        });
    }
}
