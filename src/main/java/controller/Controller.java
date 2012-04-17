package controller;

import databaseAccess.LoadDAO;
import databaseAccess.LoadDAOimpl;
import databaseAccess.LoadNotFoundException;
import model.Load;

import java.util.ArrayList;

public class Controller {
    private LoadDAO loadDAO = new LoadDAOimpl();


    public Load insertLoad(String content, String harbor, String destination){
        return loadDAO.insertLoad(content, harbor, destination);
    }

    public ArrayList<Load> getNotReservedLoadsFilteredByHarbor(String s){
        return loadDAO.getNotReservedLoadsFilteredByHarbor(s);
    }

    public ArrayList<Load> getReservedLoads(){
        return loadDAO.getReservedLoads();
    }


    public void reserveLoad(int loadID)throws LoadNotFoundException{
            Load load = loadDAO.getLoad(loadID);
            reserveLoad(load);
    }

    public void reserveLoad(Load load){
        load.setReserved(true);
        loadDAO.updateLoad(load);
    }

    public ArrayList<Load> getAllLoads(){
        return loadDAO.getAllLoads();
    }
}
