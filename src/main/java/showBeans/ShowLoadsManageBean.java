package showBeans;

import model.Load;
import model.LoadManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;

@ManagedBean
@RequestScoped
public class ShowLoadsManageBean {
    private ArrayList<Load> loads = new ArrayList<Load>();

    private String filter="";

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public ArrayList<Load> getLoads() {
        loads = LoadManager.getInstance().getLoads(filter);
        return loads;
    }

    public void setLoads(ArrayList<Load> loads) {
        this.loads = loads;
    }
}
