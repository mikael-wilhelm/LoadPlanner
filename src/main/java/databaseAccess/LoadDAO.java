package databaseAccess;


import model.Load;
import database.Loads;

import java.util.ArrayList;

public  class LoadDAO {


    public LoadDAO(){

    }

    public void insertLoad(String content, String harbor){
        Loads.getInstance().insertLoad(content, harbor);
    }

    public ArrayList<Load> getLoads(){
            return Loads.getInstance().getLoads();
    }

    public ArrayList<Load> getLoadsFilteredByHarbor(String s){
        Loads tempLoads = Loads.getInstance();
        if(!s.equals("")){
            ArrayList<Load> loadsMatching = new ArrayList<Load>();
            for(Load load: tempLoads.getLoads())
                if(s.equals(load.getHarbor()))
                    loadsMatching.add(load);
            return loadsMatching;
        }
        else
            return tempLoads.getLoads();

    }

    public int getNumberOfLoads(){
        return Loads.getInstance().size();
    }

    public void clearAllEntries(){
        Loads.getInstance().clearAllEntries();
    }

}
