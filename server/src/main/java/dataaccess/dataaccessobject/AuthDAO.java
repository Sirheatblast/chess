package dataaccess.dataaccessobject;

public interface AuthDAO {
    public String getAuthUsername(String authToken) throws Exception;

    public String createAuthToken(String username) throws Exception;

    public void deleteAuthToken(String authToken) throws Exception;

    public void flush() throws Exception;
}
