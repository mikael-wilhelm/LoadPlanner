package databaseAccess;

import model.Load;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface LoadDAO {
    public Load insertLoad(String content, String harbor,String destination) throws Exception;
    public void clearAllEntries();
    public Load updateLoad(Load load);
    public Load getLoad(int loadID) throws LoadNotFoundException, SQLException, URISyntaxException;
    public ArrayList<Load> getReservedLoads();
    public ArrayList<Load> getNotReservedLoadsFilteredByHarbor(String s) throws SQLException, URISyntaxException;
    public ArrayList<Load> getAllLoads() throws Exception;

}
