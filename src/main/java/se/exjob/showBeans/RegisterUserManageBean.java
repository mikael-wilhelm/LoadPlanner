package se.exjob.showBeans;

import se.exjob.controller.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RegisterUserManageBean {
    private String userName;
    private String password;
    Controller controller = new Controller();

    public String register(){
        controller.registerUser(userName,password);
        return "logIn.xhtml";
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
}
