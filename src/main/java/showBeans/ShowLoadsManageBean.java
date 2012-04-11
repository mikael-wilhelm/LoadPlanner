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
    private ArrayList<Load> loads = new ArrayList<Load>();
    private Controller controller = new Controller();
    private String filter="";

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public ArrayList<Load> getLoads() {

        loads = controller.getLoadsFilteredByHarbor(filter);
        return loads;
    }



    public void setLoads(ArrayList<Load> loads) {
        this.loads = loads;
    }
}
