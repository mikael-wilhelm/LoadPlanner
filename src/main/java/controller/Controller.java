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

    public void reserveLoad(Load load){
        load.setReserved(true);
        loadDAO.updateLoad(load);
    }

    public ArrayList<Load> getNotReservedLoads(String s){
        return loadDAO.getNotReservedLoadsFilteredByHarbor(s);
    }

    public ArrayList<Load> getReservedLoads(){
        return loadDAO.getReservedLoads();
    }

    public void clearAllLoads(){
        loadDAO.clearAllEntries();
    }

    public void reserveLoad(int loadID){
        Load load = loadDAO.getLoad(loadID);
        load.setReserved(true);
        loadDAO.updateLoad(load);
    }
}
