package controller;

import daoImplementations.LoadDAOPostgres;
import daoImplementations.UserDAOPostgres;
import exceptions.*;
import databaseAccess.LoadDAO;
import databaseAccess.UserDAO;
import model.Load;
import model.User;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private LoadDAO loadDAO = new LoadDAOPostgres();
    private UserDAO userDAO = new UserDAOPostgres();

    public Load insertLoad(String content, String harbor, String destination) throws Exception {
        return loadDAO.insertLoad(content, harbor, destination);
    }

    public List<Load> getNotReservedLoadsFilteredByHarbor(String s) throws ServerException, LoadNotFoundException {
        return loadDAO.getNotReservedLoadsFilteredByHarbor(s);
    }

    public List<Load> getReservedLoads(User user) throws LoadNotFoundException {
        List<Load> tempLoads = new ArrayList<Load>();
        try {
            tempLoads = loadDAO.getReservedLoads(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return tempLoads;
    }

    public void reserveLoad(int loadID, User user) throws ServerException,LoadAlreadyReservedException, LoadNotFoundException {
        Load load = null;
        try {
            load = loadDAO.getLoad(loadID);
        } catch (SQLException e) {
            throw new ServerException();
        } catch (URISyntaxException e) {
            throw new ServerException();
        }
        if(load.getReserved())
            throw new LoadAlreadyReservedException();
        else
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

    public List<Load> getAllLoads() throws Exception {
        return loadDAO.getAllLoads();
    }

    public void setDAO (LoadDAO dao){
        loadDAO = dao;
    }

    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User authenticate(String userName, String password) throws NoSuchUserNameException, PasswordException {
        return userDAO.authenticate(userName,password);
    }

    public void registerUser(String userName, String password) {
        userDAO.registerUser(userName,password);
    }
}
