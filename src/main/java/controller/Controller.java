package controller;

import daoImplementations.LoadDAOPostgres;
import daoImplementations.UserDAOPostgres;
import database.NoSuchUserNameException;
import database.WrongPasswordException;
import databaseAccess.LoadDAO;
import databaseAccess.LoadNotFoundException;
import databaseAccess.UserDAO;
import model.Load;
import model.User;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    private LoadDAO loadDAO = new LoadDAOPostgres();
    private UserDAO userDAO = new UserDAOPostgres();

    public Load insertLoad(String content, String harbor, String destination) throws Exception {
        return loadDAO.insertLoad(content, harbor, destination);
    }

    public ArrayList<Load> getNotReservedLoadsFilteredByHarbor(String s) throws SQLException, URISyntaxException {
        return loadDAO.getNotReservedLoadsFilteredByHarbor(s);
    }

    public ArrayList<Load> getReservedLoads(User user){
        ArrayList<Load> tempLoads = new ArrayList<Load>();
        try {
            tempLoads = loadDAO.getReservedLoads(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return tempLoads;
    }

    public void reserveLoad(int loadID, User user) throws LoadNotFoundException, SQLException, URISyntaxException {
        Load load = loadDAO.getLoad(loadID);
        reserveLoad(load,user);
    }

    public void reserveLoad(Load load, User user){
        load.setReserved(true);
        load.setReservedBy(user);
        try {
            loadDAO.updateLoad(load);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Load> getAllLoads() throws Exception {
        return loadDAO.getAllLoads();
    }

    public void setDAO (LoadDAO dao){
        loadDAO = dao;
    }

    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User authenticate(String userName, String password) throws NoSuchUserNameException, WrongPasswordException {
        return userDAO.authenticate(userName,password);
    }

    public void registerUser(String userName, String password) {
        userDAO.registerUser(userName,password);
    }
}
