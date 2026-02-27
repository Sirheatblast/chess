package dataaccess.dataAccessObject;

import model.GameData;

import java.util.List;

public interface GameDAO {
    void CreateGame(GameData gameData);

    void GetGame(int gameID);

    List<GameData> ListGames();

    void UpdateGame(int gameID);
}