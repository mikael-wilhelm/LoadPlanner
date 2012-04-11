package controller;

import databaseAccess.LoadDAOimpl;
import model.Load;

import java.util.ArrayList;

public class Controller {
    private LoadDAOimpl loadDAO = new LoadDAOimpl();
    public ArrayList<Load> getLoadsFilteredByHarbor(String s){
        return loadDAO.getLoadsFilteredByHarbor(s);
    }

    public ArrayList<Load> getLoads(){
        return loadDAO.getLoads();
    }

    public Load insertLoad(String content, String harbor){
        return loadDAO.insertLoad(content, harbor);
    }
}
