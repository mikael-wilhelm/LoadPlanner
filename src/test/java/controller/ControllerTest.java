package controller;

import com.sun.jmx.snmp.UserAcl;
import database.Loads;
import databaseAccess.LoadNotFoundException;
import model.Load;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ControllerTest {

    private Controller controller;
    @Before
    public void initTests(){
    controller = new Controller();
    }

    @Test
    public void insertLoadsTest(){
        //SUT
        controller.insertLoad("TestContent1", "TestHarbor1","TestDest1");
        controller.insertLoad("TestContent2", "TestHarbor2","TestDest2");

        ArrayList<Load> allLoads = controller.getAllLoads();

        int result = allLoads.size();
        int expected = 2;

        assertThat(result, is(expected));

        Loads.getInstance().clearAllEntries();
    }


    @Test
    public void reserveLoadByIDTest()throws Exception{

        Load expectedNotReservedLoad = controller.insertLoad("LoadNotResContent", "LoadNotResHarbor","LoadNotResDest");
        Load expectedReservedLoad = controller.insertLoad("LoadResContent", "LoadResHarbor","loadResDest");
        int loadToReserveId = expectedReservedLoad.getId();

        // SUT
        controller.reserveLoad(loadToReserveId);

        assertThatOneLoadIsReserved(expectedNotReservedLoad, expectedReservedLoad);
    }

    private void assertThatOneLoadIsReserved(Load expectedNotReservedLoad, Load expectedReservedLoad) {
        ArrayList<Load> reservedLoads = controller.getReservedLoads();
        ArrayList<Load> notReservedLoads = controller.getNotReservedLoadsFilteredByHarbor("");

        int reservedLoadsSize = reservedLoads.size();
        int notReservedLoadsSize = notReservedLoads.size();
        int expectedReservedSize = 1;
        int expectedNotReservedSize = 1;

        int firstIndex = 0;
        Load reservedLoadResult = reservedLoads.get(firstIndex);
        Load notReservedLoadResult = notReservedLoads.get(firstIndex);

        assertThat(reservedLoadsSize, is(expectedReservedSize));
        assertThat(notReservedLoadsSize, is(expectedNotReservedSize));
        assertThat(notReservedLoadResult,is(expectedNotReservedLoad));
        assertThat(reservedLoadResult,is(expectedReservedLoad));
    }

    @Test
    public void filterResultsTest(){
        controller.insertLoad("TestContent", "a","TestDest");
        controller.insertLoad("TestContent", "a","TestDest");
        controller.insertLoad("TestContent", "b","TestDest");

        ArrayList<Load> filteredLoadList = controller.getNotReservedLoadsFilteredByHarbor("a");

        int resultFilteredLoadsSize = filteredLoadList.size();
        int expectedSize = 2;

        assertThat(resultFilteredLoadsSize, is(expectedSize));
    }

    @After
    public void tearDown() {
        Loads.getInstance().clearAllEntries();
    }
}
