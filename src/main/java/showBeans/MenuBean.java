package showBeans;

import sessionBeans.UserSessionBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@ManagedBean
@RequestScoped
public class MenuBean {
    @ManagedProperty(value="#{userSessionBean}")
    private UserSessionBean loggedInUser;


    public UserSessionBean getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(UserSessionBean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
