package database;


import model.Load;

import java.util.ArrayList;
import java.util.HashMap;

public class Loads {
    private static Loads loads = new Loads();
    private HashMap<Integer,Load> loadList = new HashMap<Integer,Load>();
    private Loads(){

    }
    public Load insertLoad(String content, String harbor, String destination){
        int newKey =  loadList.size();
        Load load = new Load( newKey,content, harbor, destination) ;
        loadList.put(newKey,load);
        return load;

    }

    public Load insertLoad(Load load){
        int newKey = loadList.size();
        loadList.put(newKey,load);
        return load;
    }

    public HashMap<Integer,Load> getLoads(){
        return loadList;
    }

    public Load updateLoad(Load load){
        int loadID = load.getId();
        deleteLoad(load.getId());
        loadList.put(loadID,load);
        return load;
    }

    public Load getLoad(int loadID){
        return loadList.get(loadID);
    }

    public void deleteLoad(int loadID){
        loadList.remove(loadID);
    }

    public static Loads getInstance(){
        return loads;
    }

    public void clearAllEntries(){
        loadList.clear();
    }

    public int size(){
        return loadList.size();
    }
}
