package dataaccess.database;

import chess.ChessGame;
import com.google.gson.Gson;
import dataaccess.DatabaseManager;
import dataaccess.dataaccessobject.GameDAO;
import dataaccess.serverexception.BadRequestException;
import dataaccess.serverexception.DataAccessException;
import model.result.GameRequest;
import model.GameData;
import model.result.GameMetaData;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class DBGameDAO implements GameDAO {
    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS  game (
              `gameID` int NOT NULL AUTO_INCREMENT,
              `whiteUsername` varchar(256) NULL,
              `blackUsername` varchar(256) NULL,
              `game` TEXT DEFAULT NULL,
              `gameName` varchar(256) NOT NULL,
              PRIMARY KEY (`gameID`),
              INDEX(whiteUsername),
              INDEX(blackUsername),
              INDEX(gameName)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
            """
    };

    @Override
    public int createGame(GameRequest gameReq) throws Exception {
        if(gameReq.getGameName().isEmpty()){
            throw new BadRequestException("Error: Bad Request");
        }

        DatabaseManager.connect(createStatements);
        String statement = "INSERT INTO game (whiteUsername,blackUsername, game,gameName) VALUES (?, ?, ?, ?)";
        try(Connection conn = DatabaseManager.connectToDB()) {
            try (var preparedStatement = conn.prepareStatement(statement,RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1,null);
                    preparedStatement.setString(2,null);
                    preparedStatement.setString(3,new Gson().toJson(new ChessGame()));
                    preparedStatement.setString(4,gameReq.getGameName());
                    preparedStatement.executeUpdate();

                    try(var qKey = preparedStatement.getGeneratedKeys()){
                        if(qKey.next()){
                            return qKey.getInt(1);
                        }
                    }
            }
        }
        return 0;
    }

    @Override
    public GameData getGame(int gameID) throws Exception {
        DatabaseManager.connect(createStatements);
        String statement = "SELECT whiteUsername,blackUsername,game,gameName FROM game WHERE gameID = ?";
        try(Connection conn = DatabaseManager.connectToDB()) {
            try(var preparedStatement = conn.prepareStatement(statement)){
                preparedStatement.setInt(1,gameID);
                try(var qRes = preparedStatement.executeQuery()){
                    if(qRes.next()){
                        String wName = qRes.getString(1);
                        String bName = qRes.getString(2);
                        ChessGame game = new Gson().fromJson(qRes.getString(3), ChessGame.class);
                        String gameName = qRes.getString(4);
                        return new GameData(gameID,wName,bName,game,gameName);
                    }
                    return null;
                }

            }
        }
    }

    @Override
    public List<GameMetaData> listGames() throws Exception {
        DatabaseManager.connect(createStatements);
        List<GameMetaData> games = new ArrayList<>();
        String statement = "SELECT gameID,whiteUsername,blackUsername,gameName FROM game";
        try(Connection conn = DatabaseManager.connectToDB();) {
            try (var preparedStatement = conn.prepareStatement(statement)) {
                try(var qRes = preparedStatement.executeQuery()){
                    while(qRes.next()){
                        int gameID = qRes.getInt(1);
                        String wName = qRes.getString(2);
                        String bName =qRes.getString(3);
                        String gName = qRes.getString(4);
                        games.add(new GameMetaData(gameID,gName,wName,bName));
                    }
                }
            }
        }
        return games;
    }

    @Override
    public void update(GameData game) throws Exception {
        DatabaseManager.connect(createStatements);
        String statement = "UPDATE game SET whiteUsername = ?, blackUsername = ?, game = ? WHERE gameID = ?";
        try(Connection conn = DatabaseManager.connectToDB();){
            try(var preparedStatement = conn.prepareStatement(statement)){
                preparedStatement.setString(1,game.getWhiteUsername());
                preparedStatement.setString(2,game.getBlackUsername());
                preparedStatement.setString(3,new Gson().toJson(game.getGame()));
                preparedStatement.setInt(4,game.getGameID());
                preparedStatement.execute();
            }
        }
    }

    @Override
    public void flush() throws Exception {
        try{
            DatabaseManager.connect(createStatements);
            String statement = "DROP TABLE game";

            try(Connection conn = DatabaseManager.connectToDB();){
                conn.prepareStatement(statement).execute();
            }
        }
        catch (Exception e){
            throw  new Exception("Error: "+e.getMessage());
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
            throw new DataAccessException(String.format("Error: Unable to configure database: %s", e.getMessage()));
        }
    }
}
