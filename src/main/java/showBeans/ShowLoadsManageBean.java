package showBeans;

import model.Load;
import databaseAccess.LoadDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;

@ManagedBean
@RequestScoped
public class ShowLoadsManageBean {
    private ArrayList<Load> loads = new ArrayList<Load>();
    private LoadDAO loadDAO = new LoadDAO();
    private String filter="";

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public ArrayList<Load> getLoads() {
        loads = loadDAO.getLoadsFilteredByHarbor(filter);
        return loads;
    }

    public void setLoads(ArrayList<Load> loads) {
        this.loads = loads;
    }
}
