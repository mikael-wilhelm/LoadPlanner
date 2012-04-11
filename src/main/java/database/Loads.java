package database;


import model.Load;

import java.util.ArrayList;

public class Loads {
    private static Loads loads = new Loads();
    private ArrayList<Load> loadList = new ArrayList<Load>();
    private Loads(){

    }
    public void insertLoad(String content, String harbor){
        loadList.add(new Load(content, harbor));
    }

    public ArrayList<Load> getLoads(){
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
