package dataaccess.local;

import dataaccess.dataAccessObject.AuthDAO;
import dataaccess.serverException.BadRequestException;
import dataaccess.serverException.UserUnauthorizedException;
import model.AuthData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LocalAuthDAO implements AuthDAO {
    private static final Map<String, String> internalAuthTokenData = new HashMap<>();

    @Override
    public String GetAuthUsername(String authToken) throws Exception {
        if (authToken.isEmpty()) {
            throw new UserUnauthorizedException("Error: unauthorized");
        }
        if(internalAuthTokenData.get(authToken)==null){
            throw new BadRequestException("Error: bad request");
        }
        return internalAuthTokenData.get(authToken);
    }

    @Override
    public String CreateAuthToken(String username) throws Exception {
        if (username.isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }

        String nAuthToken = UUID.randomUUID().toString();
        internalAuthTokenData.put(nAuthToken,username);
        return nAuthToken;
    }

    @Override
    public void DeleteAuthToken(String authToken) throws Exception {
        internalAuthTokenData.remove(authToken);
    }
}
