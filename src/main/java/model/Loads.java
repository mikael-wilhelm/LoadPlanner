package model;


import java.util.ArrayList;

public class Loads {
    private ArrayList<Load> loads = new ArrayList<Load>();

    public void registerLoad(String content, String harbor){
        loads.add(new Load(content, harbor));
    }

    public ArrayList<Load> getLoads(){
        return loads;
    }
}
