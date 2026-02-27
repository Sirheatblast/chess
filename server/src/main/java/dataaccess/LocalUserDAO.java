package dataaccess;

import model.AuthData;
import model.UserData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LocalUserDAO implements UserDAO{
    private final Map<String,UserData> internalUserData = new HashMap<>();
    private final Map<String,String> internalAuthTokenData = new HashMap<>();

    @Override
    public UserData GetUserData(String username) throws DataAccessException{
        if(username.isEmpty()){
            throw new BadRequestException("Error: bad request");
        }
        return internalUserData.get(username);
    }

    @Override
    public void CreateUser(UserData userData) throws DataAccessException {
        if(userData.getUsername().isEmpty() ||userData.getPassword().isEmpty()||userData.getEmail().isEmpty()){
            throw new BadRequestException("Error: bad request");
        }
        internalUserData.put(userData.getUsername(),userData);
    }

    //Need to put these in LocalAuthDAO
    @Override
    public String CreateAuthToken(String username) throws DataAccessException {
        if(username.isEmpty()){
            throw new BadRequestException("Error: bad request");
        }

        String nAuthToken = UUID.randomUUID().toString();
        internalAuthTokenData.put(username,nAuthToken);
        return nAuthToken;
    }

    @Override
    public void DeleteAuthToken(AuthData authData) throws DataAccessException {
        if(authData.getUsername().isEmpty()){
            throw new BadRequestException("Error: bad request");
        }

        internalAuthTokenData.remove(authData.getUsername());
    }
}
