package dataaccess.database;

import dataaccess.DatabaseManager;
import dataaccess.dataaccessobject.AuthDAO;
import dataaccess.serverexception.BadRequestException;
import dataaccess.serverexception.UserUnauthorizedException;

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

    @Override
    public String getAuthUsername(String authToken) throws Exception {
        DatabaseManager.connect(createStatements);
        String statement = "SELECT username FROM auth WHERE authToken = ?";
        try(Connection conn = DatabaseManager.connectToDB()){
            try(var preparedStatement = conn.prepareStatement(statement)){
                preparedStatement.setString(1,authToken);
                try(var qRes = preparedStatement.executeQuery()){
                    if (!qRes.next()) {
                        throw new UserUnauthorizedException("Error: unauthorized");
                    }
                    return qRes.getString(1);
                }

            }
        }
    }

    @Override
    public String createAuthToken(String username) throws Exception {
        DatabaseManager.connect(createStatements);
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
        DatabaseManager.connect(createStatements);
        String statement = "DELETE FROM auth WHERE authToken = ?";
        try(Connection conn = DatabaseManager.connectToDB()){
            try(var authStatement = conn.prepareStatement(
                    "SELECT authToken FROM auth WHERE authToken = ?")){
                authStatement.setString(1,authToken);
                try(var qRes = authStatement.executeQuery()){
                    if(!qRes.next()){
                        throw new UserUnauthorizedException("Error: unauthorized");
                    }
                }
            }

            try(var preparedStatement = conn.prepareStatement(statement)){
                preparedStatement.setString(1,authToken);
                preparedStatement.execute();
            }
        }
    }

    @Override
    public void flush() throws Exception {
        try {
            DatabaseManager.connect(createStatements);
            String statement = "DROP TABLE auth";

            try (Connection conn = DatabaseManager.connectToDB();) {
                conn.prepareStatement(statement).execute();
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
