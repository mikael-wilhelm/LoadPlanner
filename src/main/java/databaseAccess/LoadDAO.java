package databaseAccess;

import model.Load;

import java.util.ArrayList;
import java.util.HashMap;

public interface LoadDAO {
    public Load insertLoad(String content, String harbor);
    public ArrayList<Load> getLoads();
    public ArrayList<Load> getLoadsFilteredByHarbor(String s);
    public int getNumberOfLoads();
    public void clearAllEntries();
    public Load updateLoad(Load load);
    public Load getLoad(int loadID);
    public ArrayList<Load> getReservedLoads();
    public ArrayList<Load> getNotReservedLoadsFilteredByHarbor(String s);

}
