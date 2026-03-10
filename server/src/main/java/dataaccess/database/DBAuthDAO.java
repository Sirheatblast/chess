package dataaccess.database;

import dataaccess.DatabaseManager;
import dataaccess.dataaccessobject.AuthDAO;
import dataaccess.serverexception.BadRequestException;
import dataaccess.serverexception.DataAccessException;

import java.sql.Connection;
import java.util.UUID;

public class DBAuthDAO implements AuthDAO {
    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS  auth (
              `id` int NOT NULL AUTO_INCREMENT,
              `authToken` varchar(256) NOT NULL,
              `username` varchar(256) NOT NULL,
              PRIMARY KEY (`id`),
              INDEX(authToken),
              INDEX(username)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
            """
    };

    public DBAuthDAO() {
        try {
            configureDatabase();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAuthUsername(String authToken) throws Exception {
        String statement = "SELECT password,email FROM user WHERE username = ?";

        return "";
    }

    @Override
    public String createAuthToken(String username) throws Exception {
        if (username.isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }

        String nAuthToken = UUID.randomUUID().toString();
        String statement = "INSERT INTO auth (authToken,username) VALUES (?, ?)";
        try(Connection conn = DatabaseManager.connectToDB()){
            try(var preparedStatement = conn.prepareStatement(statement)){
                preparedStatement.setString(1,nAuthToken);
                preparedStatement.setString(2, username);
                preparedStatement.execute();
            }
        }

        return nAuthToken;
    }

    @Override
    public void deleteAuthToken(String authToken) throws Exception {
        String statement = "DELETE FROM auth WHERE authToken = ?";
        try(Connection conn = DatabaseManager.connectToDB()){
            try(var preparedStatement = conn.prepareStatement(statement)){
                preparedStatement.setString(1,authToken);
                preparedStatement.execute();
            }
        }
    }

    @Override
    public void flush() throws Exception {
        String statement = "DROP TABLE auth";

        try(Connection conn = DatabaseManager.connectToDB();){
            conn.prepareStatement(statement).execute();
        }
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
