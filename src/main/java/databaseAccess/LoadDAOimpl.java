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
    public ArrayList<Load> getNotReservedLoadsFilteredByHarbor(String matcher) {
        Loads tempLoads = Loads.getInstance();
        ArrayList<Load> loadsMatching = new ArrayList<Load>();
        for(Load load : tempLoads.getLoads().values())
            if(isLoadMatching(load,matcher))
                loadsMatching.add(load);
        return loadsMatching;
    }

    private boolean isLoadMatching(Load load,String matcher){
        return !load.getReserved() && (matcher.equals(load.getHarbor())||matcher.equals(""));
    }


    public ArrayList<Load> getAllLoads() {
        Loads tempLoads = Loads.getInstance();
        ArrayList<Load> loadsMatching = new ArrayList<Load>();
        for(Load load : tempLoads.getLoads().values())
            loadsMatching.add(load);
        return loadsMatching;
    }


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


    public Load updateLoad(Load load) {
        Loads.getInstance().updateLoad(load);
        return load;
    }


    public Load getLoad(int loadID) throws LoadNotFoundException{
        Load load = Loads.getInstance().getLoad(loadID);
        if(load != null)
            return load;
        else
            throw new LoadNotFoundException();
    }
}
