package dataaccess.database;

import dataaccess.serverexception.BadRequestException;
import model.result.GameRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBGameDAOTest {
    DBGameDAO gameDAO = new DBGameDAO();

    @BeforeEach
    void setUp() throws Exception {
        gameDAO.flush();
    }

    @Test
    void testFlush() throws Exception {
        GameRequest gameReq = new GameRequest("Fun Game", null, 0);
        int firstID = gameDAO.createGame(gameReq);
        gameReq = new GameRequest("Normal Game", null, 0);
        int secondID = gameDAO.createGame(gameReq);
        gameDAO.flush();
        assertNull(gameDAO.getGame(firstID));
        assertNull(gameDAO.getGame(secondID));
    }


    @Test
    void createGameSuccess() throws Exception {
        GameRequest gameReq = new GameRequest("Fun Game", null, 0);
        int id = gameDAO.createGame(gameReq);
        assertNotNull(gameDAO.getGame(id));
    }

    @Test
    void createGameFailure() {
        assertThrows(BadRequestException.class,()->{
            GameRequest gameReq = new GameRequest("", null, 0);
            int id = gameDAO.createGame(gameReq);
        });
    }

    @Test
    void getGameSuccess() throws Exception {
        GameRequest gameReq = new GameRequest("Fun Game", null, 0);
        int id = gameDAO.createGame(gameReq);
        assertNotNull(gameDAO.getGame(id));
    }

    @Test
    void getGameFailure() throws Exception {
        GameRequest gameReq = new GameRequest("Fun Game", null, 0);
        int id = gameDAO.createGame(gameReq);
        assertNull(gameDAO.getGame(2));
    }

    @Test
    void listGamesSuccess() throws Exception {
        GameRequest gameReq = new GameRequest("Fun Game", null, 0);
        int firstID = gameDAO.createGame(gameReq);
        gameReq = new GameRequest("Normal Game", null, 0);
        int secondID = gameDAO.createGame(gameReq);

        assert(gameDAO.listGames().size()==2);
    }
}