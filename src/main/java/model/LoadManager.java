package model;


import java.util.ArrayList;

public  class LoadManager {
    private static LoadManager loadManager = new LoadManager();
    private Loads loads = new Loads();

    private LoadManager(){

    }

    public void registerLoad( String content, String harbor){
        loads.registerLoad(content, harbor);
    }

    public ArrayList<Load> getLoads(String s){
        if(!s.equals("")){
        ArrayList<Load> loadsMatching = new ArrayList<Load>();
        for(Load load: loads.getLoads())
            if(s.equals(load.getHarbor()))
                loadsMatching.add(load);

            return loadsMatching;
        }
        else
            return loads.getLoads();
    }

    public static LoadManager getInstance(){
        return loadManager;
    }


}
