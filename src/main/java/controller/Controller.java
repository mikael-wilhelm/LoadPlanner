package controller;

import daoImplementations.LoadDAOHashTable;
import daoImplementations.LoadDAOPostgres;
import databaseAccess.LoadDAO;
import databaseAccess.LoadNotFoundException;
import model.Load;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    private LoadDAO loadDAO = new LoadDAOPostgres();


    public Load insertLoad(String content, String harbor, String destination) throws Exception {
        return loadDAO.insertLoad(content, harbor, destination);
    }

    public ArrayList<Load> getNotReservedLoadsFilteredByHarbor(String s) throws SQLException, URISyntaxException {
        return loadDAO.getNotReservedLoadsFilteredByHarbor(s);
    }

    public ArrayList<Load> getReservedLoads(){
        return loadDAO.getReservedLoads();
    }


    public void reserveLoad(int loadID) throws LoadNotFoundException, SQLException, URISyntaxException {
        Load load = loadDAO.getLoad(loadID);
        reserveLoad(load);
    }

    public void reserveLoad(Load load){
        load.setReserved(true);
        loadDAO.updateLoad(load);
    }

    public ArrayList<Load> getAllLoads() throws Exception {
        return loadDAO.getAllLoads();
    }
}
