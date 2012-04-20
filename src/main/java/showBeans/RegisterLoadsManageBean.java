package showBeans;

import controller.Controller;
import databaseAccess.LoadDAOimpl;
import model.Load;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;

@ManagedBean
@SessionScoped
public class RegisterLoadsManageBean {

    private Controller controller = new Controller();
    private String tempContent = "Enter Content";
    private String tempHarbor = "Enter Harbor";



    private String tempDestination ="Enter destination";
    private ArrayList<Load> loads = new ArrayList<Load>();

    public void registerLoad(){
        controller.insertLoad(tempContent, tempHarbor,tempDestination);
    }

    public ArrayList<Load> getLoads() {
        loads = controller.getReservedLoads();
        return loads;
    }

    public String getTempHarbor() {
        return tempHarbor;
    }

    public void setTempHarbor(String tempHarbor) {
        this.tempHarbor = tempHarbor;
    }

    public String getTempContent() {
        return tempContent;
    }

    public void setTempContent(String tempContent) {
        this.tempContent = tempContent;
    }

    public String getTempDestination() {
        return tempDestination;
    }

    public void setTempDestination(String tempDestination) {
        this.tempDestination = tempDestination;
    }

}
