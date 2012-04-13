package databaseAccess;


import model.Load;
import database.Loads;

import java.util.ArrayList;
import java.util.HashMap;

public  class LoadDAOimpl implements LoadDAO{

    public Load insertLoad(String content, String harbor,String destination){
        return Loads.getInstance().insertLoad(content, harbor,destination);
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

    @Override
    public ArrayList<Load> getAllLoads() {
        Loads tempLoads = Loads.getInstance();
        ArrayList<Load> loadsMatching = new ArrayList<Load>();
        for(Load load : tempLoads.getLoads().values())
                loadsMatching.add(load);
        return loadsMatching;
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
}
