package showBeans;

import controller.Controller;
import databaseAccess.LoadNotFoundException;
import model.Load;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

@ManagedBean
@RequestScoped
public class ShowLoadsManageBean {

    private ArrayList<Load> notReservedLoads = new ArrayList<Load>();
    private Controller controller = new Controller();
    private String filter="";
    private String tempID ="test";

    public void reserveLoad(){
         try{
            controller.reserveLoad(Integer.parseInt(tempID));
         }
         catch(LoadNotFoundException ignored){
         } catch (SQLException e) {
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         } catch (URISyntaxException e) {
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
    }

    public String getTempID() {
        return tempID;
    }

    public void setTempID(String tempID) {
        this.tempID = tempID;
    }

    public String getFilter() {
        return filter;
    }

    public ArrayList<Load> getNotReservedLoads() {
        try {
            notReservedLoads = controller.getNotReservedLoadsFilteredByHarbor(filter);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return notReservedLoads;
    }

    public void setNotReservedLoads(ArrayList<Load> notReservedLoads) {
        this.notReservedLoads = notReservedLoads;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
