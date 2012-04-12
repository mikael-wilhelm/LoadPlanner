package controller;

import model.Load;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ControllerTest {

     private Controller controller = new Controller();

    @Test
    public void reserveLoadsTest(){
        Load loadNotReserved1 = controller.insertLoad("Load1Not", "Load1NotCont");
        Load loadNotReserved2 = controller.insertLoad("Load2Not", "Load2NotCont");
        Load loadNotReserved3 = controller.insertLoad("Load3Not", "Load3NotCont");
        Load loadReserved1 = controller.insertLoad("Load1Res", "Load1ResCont");
        Load loadReserved2 = controller.insertLoad("Load2Res", "Load2ResCont");

        controller.reserveLoad(loadReserved1);
        controller.reserveLoad(loadReserved2);

        ArrayList<Load> reservedLoads = controller.getReservedLoads();
        ArrayList<Load> notReservedLoads = controller.getNotReservedLoads("");

        int numberOfReservedLoads = reservedLoads.size();
        int expectedSizeReserved = 2;

        int numberOfNotReservedLoads = notReservedLoads.size();
        int expectedSizeNotReserved = 3;



        assertThat(numberOfReservedLoads, is(expectedSizeReserved));
        assertThat(numberOfNotReservedLoads, is(expectedSizeNotReserved));
        controller.clearAllLoads();
    }

    @Test
    public void reserveLoadByIDTest(){
        controller.insertLoad("Load1Not", "Load1NotCont");
        controller.insertLoad("Load2Not", "Load2NotCont");
        controller.insertLoad("Load1Res", "Load1ResCont");
        controller.insertLoad("Load2Res", "Load2ResCont");

        controller.reserveLoad(2);
        controller.reserveLoad(3);

        ArrayList<Load> reservedLoads = controller.getReservedLoads();
        int numberOfReservedLoads = reservedLoads.size();

        int expectedSize = 2;

        assertThat(numberOfReservedLoads, is(expectedSize));

        controller.clearAllLoads();

    }



}
