package showBeans;

import controller.Controller;
import databaseAccess.LoadDAOimpl;
import model.Load;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;

@ManagedBean
@RequestScoped
public class RegisterLoadsManageBean {

    private Controller controller = new Controller();
    private String tempContent = "Enter Content";
    private String tempHarbor = "Enter Harbor";
    private ArrayList<Load> loads = new ArrayList<Load>();

    public void registerLoad(){
        controller.insertLoad(tempContent, tempHarbor);
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

}
