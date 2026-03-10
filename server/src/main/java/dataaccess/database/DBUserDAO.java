package dataaccess.database;

import dataaccess.DatabaseManager;
import dataaccess.dataaccessobject.UserDAO;
import dataaccess.serverexception.DataAccessException;
import model.UserData;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUserDAO implements UserDAO {
    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS  user (
              `id` int NOT NULL AUTO_INCREMENT,
              `username` varchar(256) NOT NULL,
              `password` varchar(256) NOT NULL,
              `email` varchar(256) NOT NULL,
              PRIMARY KEY (`id`),
              INDEX(username),
              INDEX(password),
              INDEX(email)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
            """
    };

    public DBUserDAO() {
        try {
            configureDatabase();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public UserData getUserData(String username) throws Exception {
        return null;
    }

    @Override
    public void createUser(UserData userData) throws Exception {
        String statement = "INSERT INTO user (username,password, email) VALUES (?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(userData.getPassword(), BCrypt.gensalt());

        try(Connection conn = DatabaseManager.connectToDB();){
            try(var preparedStatement = conn.prepareStatement(statement)){
                preparedStatement.setString(1, userData.getUsername());
                preparedStatement.setString(2,hashedPassword);
                preparedStatement.setString(3, userData.getEmail());
                preparedStatement.execute();
            }
        }
        catch (SQLException e){
            throw new DataAccessException(String.format("Unable to add User to database: %s", e.getMessage()));

        }
    }

    @Override
    public void flush() throws Exception {
        String statement = "DROP TABLE user";

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
