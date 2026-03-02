package service;

import dataaccess.dataAccessObject.AuthDAO;
import dataaccess.dataAccessObject.GameDAO;
import dataaccess.dataAccessObject.UserDAO;
import dataaccess.local.LocalAuthDAO;
import dataaccess.local.LocalGameDAO;
import dataaccess.local.LocalUserDAO;
import model.UserData;

public class DBService {
    AuthDAO aDAO = new LocalAuthDAO();
    GameDAO gDAO = new LocalGameDAO();
    UserDAO uDAO = new LocalUserDAO();
    public void ClearDB() throws Exception {
        aDAO.Flush();
        gDAO.Flush();
        uDAO.Flush();
    }

}
