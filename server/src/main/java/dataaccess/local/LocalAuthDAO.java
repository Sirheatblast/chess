package dataaccess.local;

import dataaccess.dataAccessObject.AuthDAO;
import dataaccess.serverException.BadRequestException;
import model.AuthData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LocalAuthDAO implements AuthDAO {
    private static final Map<String, String> internalAuthTokenData = new HashMap<>();

    @Override
    public String GetAuthKey(String username) throws Exception {
        if (username.isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }
        return internalAuthTokenData.get(username);
    }

    @Override
    public String CreateAuthToken(String username) throws Exception {
        if (username.isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }

        String nAuthToken = UUID.randomUUID().toString();
        internalAuthTokenData.put(username, nAuthToken);
        return nAuthToken;
    }

    @Override
    public void DeleteAuthToken(AuthData authData) throws Exception {
        if (authData.getUsername().isEmpty()) {
            throw new BadRequestException("Error: bad request");
        }

        internalAuthTokenData.remove(authData.getUsername());
    }
}
