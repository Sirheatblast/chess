package dataaccess.database;

import dataaccess.DatabaseManager;
import dataaccess.dataaccessobject.GameDAO;
import dataaccess.serverexception.DataAccessException;
import handler.GameRequest;
import model.GameData;
import service.result.GameMetaData;

import java.sql.Connection;
import java.util.List;

public class DBGameDAO implements GameDAO {
    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS  game (
              `gameID` int NOT NULL AUTO_INCREMENT,
              `whiteUsername` varchar(256) NOT NULL,
              `blackUsername` varchar(256) NOT NULL,
              `game` TEXT DEFAULT NULL,
              PRIMARY KEY (`gameID`),
              INDEX(whiteUsername),
              INDEX(blackUsername)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
            """
    };

    public DBGameDAO() {
        try {
            configureDatabase();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int createGame(GameRequest gameReq) throws Exception {
        return 0;
    }

    @Override
    public GameData getGame(int gameID) throws Exception {
        return null;
    }

    @Override
    public List<GameMetaData> listGames() throws Exception {
        return List.of();
    }

    @Override
    public void flush() throws Exception {

    }

    private void configureDatabase() throws DataAccessException {
        DatabaseManager.createDatabase();
        try (Connection conn = DatabaseManager.connectToDB();) {
            for (String statement : createStatements) {
                try (var preparedStatement = conn.prepareStatement(statement)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            throw new DataAccessException(String.format("Unable to configure database: %s", e.getMessage()));
        }
    }
}
