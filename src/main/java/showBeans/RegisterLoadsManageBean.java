package showBeans;

import controller.Controller;
import model.Load;

import javax.faces.bean.*;
import java.util.ArrayList;

@ManagedBean
@RequestScoped
public class RegisterLoadsManageBean {

    private Controller controller = new Controller();
    private String tempContent;
    private String tempHarbor;
    private String tempDestination;

    private ArrayList<Load> loads = new ArrayList<Load>();

    public String registerLoad(){
        try {
            controller.insertLoad(tempContent, tempHarbor,tempDestination);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "mainPage.xhtml";
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
