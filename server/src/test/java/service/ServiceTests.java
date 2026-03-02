package service;

import dataaccess.dataaccessobject.AuthDAO;
import dataaccess.dataaccessobject.GameDAO;
import dataaccess.dataaccessobject.UserDAO;
import dataaccess.local.LocalAuthDAO;
import dataaccess.local.LocalGameDAO;
import dataaccess.local.LocalUserDAO;

import dataaccess.serverexception.BadRequestException;
import dataaccess.serverexception.UserUnauthorizedException;
import model.UserData;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

import service.result.UserResult;

import java.util.Objects;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceTests {
    static GameDAO gameDAO = new LocalGameDAO();
    static UserDAO userDAO = new LocalUserDAO();
    static AuthDAO authDAO = new LocalAuthDAO();

    @BeforeAll
    public static void initTest() throws Exception {
        authDAO.flush();
        gameDAO.flush();
        userDAO.flush();
    }

    @Test
    @Order(0)
    @DisplayName("AddUserSuccess")
    public void AddUserSuccess() throws Exception {
        UserService userService = new UserService();
        UserData userData = new UserData("cool name", "cool password", "my email dude");
        UserResult result = userService.registerUser(userData);

        assert (result.getStatus() == 200);
        assert (userDAO.getUserData(result.getUsername()) != null);
    }

    @Test
    @Order(1)
    @DisplayName("AddUserFail")
    public void AddUserFail() throws Exception {

        assertThrows(BadRequestException.class, () -> {
            UserService userService = new UserService();
            UserData userData = new UserData(null, "cool password", "my email dude");
            UserResult result = userService.registerUser(userData);
        });
    }

    @Test
    @Order(2)
    @DisplayName("LoginUserSuccess")
    public void LoginUserSuccess() throws Exception {
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
    public void LoginUserFail() throws Exception {

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
    public void LogoutUserSuccess() throws Exception {
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
    public void LogoutUserFail() throws Exception {

        assertThrows(UserUnauthorizedException.class, () -> {
            UserService userService = new UserService();
            UserResult result = userService.logoutUser(null);
        });
    }
}
