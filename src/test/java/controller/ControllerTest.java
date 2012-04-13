package controller;

import model.Load;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ControllerTest {

     private Controller controller = new Controller();

    @Test
    public void reserveLoadByIDTest(){
        Load expectedNotReservedLoad = controller.insertLoad("LoadNotResContent", "LoadNotResHarbor","LoadNotResDest");
        Load expectedReservedLoad = controller.insertLoad("LoadResContent", "LoadResHarbor","loadResDest");

        controller.reserveLoad(expectedReservedLoad.getId());

        ArrayList<Load> reservedLoads = controller.getReservedLoads();
        ArrayList<Load> notReservedLoads = controller.getNotReservedLoadsFilteredByHarbor("");

        int reservedLoadsSize = reservedLoads.size();
        int notReservedLoadsSize = notReservedLoads.size();
        int expectedReservedSize = 1;
        int expectedNotReservedSize = 1;

        Load reservedLoadResult = reservedLoads.get(0);
        Load notReservedLoadResult = notReservedLoads.get(0);

        assertThat(reservedLoadsSize, is(expectedReservedSize));
        assertThat(notReservedLoadsSize, is(expectedNotReservedSize));

        assertThat(notReservedLoadResult,is(expectedNotReservedLoad));
        assertThat(reservedLoadResult,is(expectedReservedLoad));

        controller.clearAllLoads();
    }

    @Test
    public void clearAllTest(){
        controller.insertLoad("TestContent", "TestHarbor","TestDest");
        controller.insertLoad("TestContent", "TestHarbor","TestDest");

        ArrayList<Load> allLoads = controller.getAllLoads();

        int resultBefore = allLoads.size();

        controller.clearAllLoads();

        allLoads = controller.getAllLoads();

        int resultAfter = allLoads.size();

        int expectedBefore = 2;
        int expectedAfter = 0;

        assertThat(resultBefore, is(expectedBefore));
        assertThat(resultAfter, is(expectedAfter));
    }
}
