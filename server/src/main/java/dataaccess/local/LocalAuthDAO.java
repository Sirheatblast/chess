package dataaccess.local;

import dataaccess.dataaccessobject.AuthDAO;
import dataaccess.serverexception.BadRequestException;
import dataaccess.serverexception.UserUnauthorizedException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LocalAuthDAO implements AuthDAO {
    private static final Map<String, String> INTERNAL_AUTH_TOKEN_DATA = new HashMap<>();

    @Override
    public String getAuthUsername(String authToken) throws Exception {
        if (INTERNAL_AUTH_TOKEN_DATA.get(authToken)==null) {
            throw new UserUnauthorizedException("Error: unauthorized");
        }
        return INTERNAL_AUTH_TOKEN_DATA.get(authToken);
    }

    @Override
    public String createAuthToken(String username) throws Exception {
        if (username.isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }

        String nAuthToken = UUID.randomUUID().toString();
        INTERNAL_AUTH_TOKEN_DATA.put(nAuthToken,username);
        return nAuthToken;
    }

    @Override
    public void deleteAuthToken(String authToken) throws Exception {
        if(!INTERNAL_AUTH_TOKEN_DATA.containsKey(authToken)){
            throw new UserUnauthorizedException("Error: unauthorized");
        }
        INTERNAL_AUTH_TOKEN_DATA.remove(authToken);
    }

    @Override
    public void flush() throws Exception {
        INTERNAL_AUTH_TOKEN_DATA.clear();
    }
}
