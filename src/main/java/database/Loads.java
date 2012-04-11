package database;


import model.Load;

import java.util.ArrayList;
import java.util.HashMap;

public class Loads {
    private static Loads loads = new Loads();
    private HashMap<Integer,Load> loadList = new HashMap<Integer,Load>();
    private Loads(){

    }
    public void insertLoad(String content, String harbor){
        loadList.put(loadList.size(),new Load(content, harbor));
    }

    public HashMap<Integer,Load> getLoads(){
        return loadList;
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
