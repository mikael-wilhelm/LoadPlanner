package controller;

import databaseAccess.LoadDAO;
import databaseAccess.LoadDAOimpl;
import model.Load;

import java.util.HashMap;

public class Controller {
    private LoadDAOimpl loadDAO = new LoadDAOimpl();
    public HashMap<Integer,Load> getLoadsFilteredByHarbor(String s){
        return loadDAO.getLoadsFilteredByHarbor(s);
    }

    public HashMap<Integer,Load> getLoads(){
        return loadDAO.getLoads();
    }

    public void insertLoad(String content, String harbor){
        loadDAO.insertLoad(content, harbor);
    }
}
