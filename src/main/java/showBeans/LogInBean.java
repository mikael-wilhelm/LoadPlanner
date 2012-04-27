package showBeans;

import controller.Controller;
import database.NoSuchUserNameException;
import database.WrongPasswordException;
import model.User;
import sessionBeans.UserSessionBean;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class LogInBean {
    private String userName;
    private String password;
    Controller controller = new Controller();
    @ManagedProperty(value="#{userSessionBean}")
    private UserSessionBean loggedInUser;

    public String logIn(){
        try {
            User user = controller.authenticate(userName,password);
            loggedInUser.setLoggedInUser(user);
            return "showLoads.xhtml";
        } catch (NoSuchUserNameException e) {
            return "logIn.xhtml";
        } catch (WrongPasswordException e) {
            return "logIn.xhtml";
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setLoggedInUser(UserSessionBean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}