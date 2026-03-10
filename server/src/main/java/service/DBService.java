package service;

import dataaccess.dataaccessobject.AuthDAO;
import dataaccess.dataaccessobject.GameDAO;
import dataaccess.dataaccessobject.UserDAO;
import dataaccess.database.DBAuthDAO;
import dataaccess.database.DBGameDAO;
import dataaccess.database.DBUserDAO;
import dataaccess.local.LocalAuthDAO;
import dataaccess.local.LocalGameDAO;
import dataaccess.local.LocalUserDAO;

public class DBService {
    AuthDAO aDAO = new DBAuthDAO();
    GameDAO gDAO = new DBGameDAO();
    UserDAO uDAO = new DBUserDAO();
    public void clearDB() throws Exception {
        try{
            aDAO.flush();
            gDAO.flush();
            uDAO.flush();
        }
        catch (Exception e){
            throw new Exception("Error: Failed to connect to Server");
        }

    }

}
