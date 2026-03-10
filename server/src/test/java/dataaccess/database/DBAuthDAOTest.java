package dataaccess.database;

import dataaccess.serverexception.BadRequestException;
import dataaccess.serverexception.UserUnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DBAuthDAOTest {
    DBAuthDAO authDAO = new DBAuthDAO();
    @BeforeEach
    void setUp() throws Exception {
        authDAO.flush();
    }

    @Test
    void testFlush() throws Exception {
        String john = authDAO.createAuthToken("John Doe");
        String jill = authDAO.createAuthToken("Jill Doe");
        String bill = authDAO.createAuthToken("Bill Boe");
        authDAO.flush();
        assertThrows(UserUnauthorizedException.class,()->{
            authDAO.getAuthUsername(john);
        });
        assertThrows(UserUnauthorizedException.class,()->{
            authDAO.getAuthUsername(jill);
        });
        assertThrows(UserUnauthorizedException.class,()->{
            authDAO.getAuthUsername(bill);
        });
    }


    @Test
    void getAuthUsernameSuccess() throws Exception {
        String token = authDAO.createAuthToken("John Doe");
        String username = authDAO.getAuthUsername(token);
        assert(username.equals("John Doe"));
    }

    @Test
    void getAuthUsernameFailure() {
        assertThrows(UserUnauthorizedException.class,()->{
            String username = authDAO.getAuthUsername("I don't exist");
        });
    }

    @Test
    void createAuthTokenSuccess() throws Exception {
        String token = authDAO.createAuthToken("John Doe");
        assert(authDAO.getAuthUsername(token).equals("John Doe"));
    }

    @Test
    void createAuthTokenFailure() {
        assertThrows(BadRequestException.class,()->{
           String token = authDAO.createAuthToken("");
        });
    }

    @Test
    void deleteAuthTokenSuccess() {
        assertThrows(UserUnauthorizedException.class,()->{
            String token = authDAO.createAuthToken("John Doe");
            authDAO.deleteAuthToken(token);
            authDAO.getAuthUsername(token);
        });
    }

    @Test
    void deleteAuthTokenFailure() {
        assertThrows(UserUnauthorizedException.class,()->{
            authDAO.deleteAuthToken("Bad Token doesnt exist");
        });
    }
}