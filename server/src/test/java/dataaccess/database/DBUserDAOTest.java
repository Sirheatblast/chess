package dataaccess.database;

import dataaccess.serverexception.BadRequestException;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DBUserDAOTest {
    private final DBUserDAO userDAO = new DBUserDAO();

    @BeforeEach
    void setUp() throws Exception {
        userDAO.flush();
    }

    @Test
    void testFlush() throws Exception{
        UserData uData = new UserData("John Doe", "Does not exist", "@ghost");
        userDAO.createUser(uData);
        uData = new UserData("Jill Doe", "Does not exist", "@ghost2");
        userDAO.createUser(uData);
        uData = new UserData("Bill Boe", "Does exist", "Nota@ghost5");
        userDAO.createUser(uData);
        userDAO.flush();
        assertNull(userDAO.getUserData("John Doe"));
        assertNull(userDAO.getUserData("Jill Doe"));
        assertNull(userDAO.getUserData("Bill Boe"));
    }

    @Test
    void getUserDataSuccess() throws Exception {
        UserData uData = new UserData("John Doe", "Does not exist", "@ghost");
        userDAO.createUser(uData);
        uData = new UserData("Jill Doe", "Does not exist", "@ghost2");
        userDAO.createUser(uData);
        UserData serverUData = userDAO.getUserData(uData.getUsername());
        assert (uData.getUsername().equals(serverUData.getUsername()));
    }

    @Test
    void getUserDataFail() {
        assertThrows(BadRequestException.class,()->{
            UserData uData = new UserData("John Doe", "Does not exist", "@ghost");
            userDAO.createUser(uData);
            uData = new UserData("Jill Doe", "Does not exist", "@ghost2");
            userDAO.createUser(uData);
            UserData serverUData = userDAO.getUserData(null);
        });
    }

    @Test
    void createUserSuccess() throws Exception {
        UserData uData = new UserData("John Doe", "Does not exist", "@ghost");
        userDAO.createUser(uData);
        UserData serverUData = userDAO.getUserData(uData.getUsername());
        assert (uData.getUsername().equals(serverUData.getUsername()));
    }

    @Test
    void createUserFail() throws Exception {
        assertThrows(BadRequestException.class, () -> {
            UserData uData = new UserData("John Doe", "", "@ghost");
            userDAO.createUser(uData);
        });
    }
}