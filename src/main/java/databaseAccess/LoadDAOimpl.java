package databaseAccess;


import model.Load;
import database.Loads;

import java.util.ArrayList;
import java.util.HashMap;

public  class LoadDAOimpl implements LoadDAO{


    public LoadDAOimpl(){

    }

    public Load insertLoad(String content, String harbor){
        return Loads.getInstance().insertLoad(content, harbor);
    }

    public void insertLoad(Load load){

    }

    public ArrayList<Load> getLoads(){
         ArrayList<Load>  allLoads = new ArrayList<Load>();
         for(Load load:Loads.getInstance().getLoads().values())
             allLoads.add(load);
        return allLoads;
    }

    public ArrayList<Load> getLoadsFilteredByHarbor(String s){
        Loads tempLoads = Loads.getInstance();
        if(!s.equals("")){
            ArrayList<Load> loadsMatching = new ArrayList<Load>();
            for(Load load : tempLoads.getLoads().values())
                if(s.equals(load.getHarbor()))
                    loadsMatching.add(load);
            return loadsMatching;
        }
        else
            return getLoads();

    }

    public int getNumberOfLoads(){
        return Loads.getInstance().size();
    }

    public void clearAllEntries(){
        Loads.getInstance().clearAllEntries();
    }

    @Override
    public Load updateLoad(Load load) {
        Loads.getInstance().updateLoad(load);
        return load;
    }

    @Override
    public Load getLoad(int loadID) {
         return Loads.getInstance().getLoad(loadID);
    }

    @Override
    public ArrayList<Load> getReservedLoads() {
        Loads tempLoads = Loads.getInstance();
            ArrayList<Load> loadsMatching = new ArrayList<Load>();
            for(Load load : tempLoads.getLoads().values())
                if(load.getReserved())
                    loadsMatching.add(load);
            return loadsMatching;

    }

    @Override
    public ArrayList<Load> getNotReservedLoadsFilteredByHarbor(String s) {
         Loads tempLoads = Loads.getInstance();
            ArrayList<Load> loadsMatching = new ArrayList<Load>();
            for(Load load : tempLoads.getLoads().values())
                if(!load.getReserved() && (s.equals(load.getHarbor())||s.equals("")))
                    loadsMatching.add(load);
            return loadsMatching;
    }

}
