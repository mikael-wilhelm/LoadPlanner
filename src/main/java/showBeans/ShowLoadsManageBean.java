package showBeans;

import controller.Controller;
import databaseAccess.LoadDAOimpl;
import model.Load;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ManagedBean
@RequestScoped
public class ShowLoadsManageBean {
    private HashMap<Integer,Load> loads = new HashMap<Integer,Load>();
    private Controller controller = new Controller();
    private String filter="";

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public HashMap<Integer,Load> getLoads() {


        return loads;
    }

    public List<Integer> getKeyAsList(){
        loads = controller.getLoadsFilteredByHarbor(filter);
        ArrayList<Integer> keys = new ArrayList<Integer>(loads.keySet());
        return  keys;
    }

    public void setLoads(HashMap<Integer,Load> loads) {
        this.loads = loads;
    }
}
