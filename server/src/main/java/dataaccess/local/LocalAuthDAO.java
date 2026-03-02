package dataaccess.local;

import dataaccess.dataaccessobject.AuthDAO;
import dataaccess.serverexception.BadRequestException;
import dataaccess.serverexception.UserUnauthorizedException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LocalAuthDAO implements AuthDAO {
    private static final Map<String, String> internalAuthTokenData = new HashMap<>();

    @Override
    public String GetAuthUsername(String authToken) throws Exception {
        if (internalAuthTokenData.get(authToken)==null) {
            throw new UserUnauthorizedException("Error: unauthorized");
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
        if(!internalAuthTokenData.containsKey(authToken)){
            throw new UserUnauthorizedException("Error: unauthorized");
        }
        internalAuthTokenData.remove(authToken);
    }

    @Override
    public void Flush() throws Exception {
        internalAuthTokenData.clear();
    }
}
