package databaseAccess;


import model.Load;
import database.Loads;

import java.util.ArrayList;
import java.util.HashMap;

public  class LoadDAOimpl implements LoadDAO{


    public LoadDAOimpl(){

    }

    public void insertLoad(String content, String harbor){
        Loads.getInstance().insertLoad(content, harbor);
    }

    public HashMap<Integer,Load> getLoads(){
            return Loads.getInstance().getLoads();
    }

    public HashMap<Integer,Load> getLoadsFilteredByHarbor(String s){
        Loads tempLoads = Loads.getInstance();
        if(!s.equals("")){
            HashMap<Integer,Load> loadsMatching = new HashMap<Integer,Load>();
            for(int i =0; i < tempLoads.getLoads().size();i++)
                if(s.equals(tempLoads.getLoads().get(i).getHarbor()))
                    loadsMatching.put(loadsMatching.size(),tempLoads.getLoads().get(i));
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
