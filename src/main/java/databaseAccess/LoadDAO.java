package databaseAccess;

import model.Load;

import java.util.ArrayList;
import java.util.HashMap;

public interface LoadDAO {
    public void insertLoad(String content, String harbor);
    public HashMap<Integer,Load> getLoads();
    public HashMap<Integer,Load> getLoadsFilteredByHarbor(String s);
    public int getNumberOfLoads();
    public void clearAllEntries();
}
