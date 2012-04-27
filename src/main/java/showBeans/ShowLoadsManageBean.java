package showBeans;

import controller.Controller;
import exceptions.LoadAlreadyReservedException;
import exceptions.LoadNotFoundException;
import model.Load;
import sessionBeans.UserSessionBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

@ManagedBean
@RequestScoped
public class ShowLoadsManageBean {

    private ArrayList<Load> notReservedLoads = new ArrayList<Load>();
    private Controller controller = new Controller();
    private String filter="";
    private String tempID ="test";
    @ManagedProperty(value="#{userSessionBean}")
    private UserSessionBean loggedInUser;
    private HtmlDataTable test = new HtmlDataTable();

    public void reserveLoad(){
        FacesMessage doneMessage = null;
        try{
            controller.reserveLoad(((Load)test.getRowData()).getId(),loggedInUser.getLoggedInUser());
            doneMessage = new FacesMessage("Load reserved");
        }
        catch(LoadNotFoundException ignored){
        } catch (SQLException e) {
            doneMessage = new FacesMessage("SQL error");
        } catch (URISyntaxException e) {
            doneMessage = new FacesMessage("SQL error");
        } catch (LoadAlreadyReservedException e) {
            doneMessage = new FacesMessage("The Load is already reserved");
        }
        FacesContext.getCurrentInstance().addMessage(null,doneMessage);
    }

    public String getTempID() {
        return tempID;
    }

    public void setTempID(String tempID) {
        this.tempID = tempID;
    }

    public String getFilter() {
        return filter;
    }

    public ArrayList<Load> getNotReservedLoads() {
        try {
            notReservedLoads = controller.getNotReservedLoadsFilteredByHarbor(filter);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return notReservedLoads;
    }

    public void setNotReservedLoads(ArrayList<Load> notReservedLoads) {
        this.notReservedLoads = notReservedLoads;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void setLoggedInUser(UserSessionBean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public HtmlDataTable getTest() {
        return test;
    }

    public void setTest(HtmlDataTable test) {
        this.test = test;
    }
    public void test(){
        System.out.println(test.getRowIndex());
    }




}
