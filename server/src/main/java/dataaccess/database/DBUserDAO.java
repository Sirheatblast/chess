package dataaccess.database;

import dataaccess.DatabaseManager;
import dataaccess.dataaccessobject.UserDAO;
import dataaccess.serverexception.BadRequestException;
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

    @Override
    public UserData getUserData(String username) throws Exception {
        DatabaseManager.connect(createStatements);
        if (username==null||username.isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }
        String statement = "SELECT password,email FROM user WHERE username = ?";
        try(Connection conn = DatabaseManager.connectToDB()) {
            try(var preparedStatement = conn.prepareStatement(statement)){
                preparedStatement.setString(1,username);
                try(var qRes = preparedStatement.executeQuery()){
                    if(qRes.next()){
                        String password =qRes.getString(1);
                        String email = qRes.getString(2);
                        return new UserData(username,password,email);
                    }
                    return null;
                }

            }
        }
    }

    @Override
    public void createUser(UserData userData) throws Exception {
        DatabaseManager.connect(createStatements);
        if (userData.getUsername().isEmpty() || userData.getPassword().isEmpty() || userData.getEmail().isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }
        String statement = "INSERT INTO user (username,password, email) VALUES (?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(userData.getPassword(), BCrypt.gensalt());

        try(Connection conn = DatabaseManager.connectToDB()){
            try(var preparedStatement = conn.prepareStatement(statement)){
                preparedStatement.setString(1, userData.getUsername());
                preparedStatement.setString(2,hashedPassword);
                preparedStatement.setString(3, userData.getEmail());
                preparedStatement.execute();
            }
        }
        catch (SQLException e){
            throw new DataAccessException(
                    String.format("Unable to add User to database: %s", e.getMessage()));
        }
    }

    @Override
    public void flush() throws Exception {
        try{
            DatabaseManager.connect(createStatements);
            String statement = "DROP TABLE user";

            try(Connection conn = DatabaseManager.connectToDB();){
                conn.prepareStatement(statement).execute();
            }
        }
        catch (Exception e){
            throw  new Exception("Error: "+e.getMessage());
        }

    }


}
