package showBeans;

import controller.Controller;
import databaseAccess.LoadDAOimpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RegisterLoadsManageBean {

    private Controller controller = new Controller();

    private String tempContent = "Enter Content";



    private String tempHarbor = "Enter Harbor";


    public void registerLoad(){
        controller.insertLoad(tempContent, tempHarbor);
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
