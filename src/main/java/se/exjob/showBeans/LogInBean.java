package se.exjob.showBeans;

import se.exjob.controller.Controller;
import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.exceptions.PasswordException;
import se.exjob.exceptions.ServerException;
import se.exjob.model.User;
import se.exjob.sessionBeans.UserSessionBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LogInBean {
    private String userName;
    private String password;
    Controller controller = new Controller();
    @ManagedProperty(value="#{userSessionBean}")
    private UserSessionBean loggedInUser;

    public String logIn(){
        String returnPage;
        try {
            User user = controller.authenticate(userName,password);
            loggedInUser.setLoggedInUser(user);
            returnPage= "showLoads.xhtml";
        } catch (NoSuchUserNameException e) {
            returnPage = "logIn.xhtml";
        } catch (PasswordException e) {
            returnPage = "logIn.xhtml";
        } catch (ServerException e) {
            returnPage = "logIn.xhtml" ;
        }
        return returnPage;
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
