package service;

import dataaccess.dataaccessobject.AuthDAO;
import dataaccess.dataaccessobject.GameDAO;
import dataaccess.dataaccessobject.UserDAO;
import dataaccess.local.LocalAuthDAO;
import dataaccess.local.LocalGameDAO;
import dataaccess.local.LocalUserDAO;

public class DBService {
    AuthDAO aDAO = new LocalAuthDAO();
    GameDAO gDAO = new LocalGameDAO();
    UserDAO uDAO = new LocalUserDAO();
    public void clearDB() throws Exception {
        aDAO.flush();
        gDAO.flush();
        uDAO.flush();
    }

}
