package showBeans;

import model.LoadManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RegisterLoadsManageBean {




    private String tempContent = "Enter Content";



    private String tempHarbor = "Enter Harbor";


    public void registerLoad(){
        LoadManager.getInstance().registerLoad(tempContent, tempHarbor);
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
