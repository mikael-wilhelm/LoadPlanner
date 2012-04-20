package showBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;

@ManagedBean
@RequestScoped
public class MainManageBean {

    private String actualPage = "showLoads.xhtml";


    public String getActualPage() {
        return actualPage;
    }

    public void setActualPage(String actualPage) {
        this.actualPage = actualPage;
    }
}
