package showBeans;

import controller.Controller;
import model.Load;
import sessionBeans.UserSessionBean;

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
    @ManagedProperty(value="#{userSessionBean}")
    private UserSessionBean loggedInUser;

    public void registerLoad(){
        try {
            controller.insertLoad(tempContent, tempHarbor,tempDestination);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Load> getLoads() {
        loads = controller.getReservedLoads(loggedInUser.getLoggedInUser());
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

    public void setLoggedInUser(UserSessionBean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
