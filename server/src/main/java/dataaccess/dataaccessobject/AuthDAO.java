package dataaccess.dataaccessobject;

public interface AuthDAO {
    public String GetAuthUsername(String authToken) throws Exception;

    public String CreateAuthToken(String username) throws Exception;

    public void DeleteAuthToken(String authToken) throws Exception;

    public void Flush()throws Exception;
}
