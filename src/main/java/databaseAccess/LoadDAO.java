package databaseAccess;

import model.Load;

import java.util.ArrayList;
import java.util.HashMap;

public interface LoadDAO {
    public Load insertLoad(String content, String harbor,String destination);
    public void clearAllEntries();
    public Load updateLoad(Load load);
    public Load getLoad(int loadID) throws LoadNotFoundException;
    public ArrayList<Load> getReservedLoads();
    public ArrayList<Load> getNotReservedLoadsFilteredByHarbor(String s);
    public ArrayList<Load> getAllLoads();

}
