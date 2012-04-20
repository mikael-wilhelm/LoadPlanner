package showBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@ManagedBean
@SessionScoped
public class MainManageBean {

    private String actualPage = "showLoads.xhtml";


    public String getActualPage() {

        return actualPage;
    }

    public void setActualPage(String actualPage) {

        this.actualPage = actualPage;
    }
}
