package databaseAccess;


import databaseAccess.LoadDAO;
import model.Load;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LoadDAOTest {

    @Test
    public void clearAllTest(){
        LoadDAO loadDAO = new LoadDAO();
        loadDAO.insertLoad("testContent", "testHarbor");
        loadDAO.insertLoad("testContent2", "testHarbor2");

        int sizeResultBefore = loadDAO.getNumberOfLoads();

        loadDAO.clearAllEntries();

        int sizeResultAfter = loadDAO.getNumberOfLoads();

        int expectedSizeBefore = 2;
        int expectedSizeAfter = 0;

        assertThat(sizeResultBefore,is(expectedSizeBefore));
        assertThat(sizeResultAfter,is(expectedSizeAfter));


    }
    @Test
      public void addLoadTest(){
        LoadDAO loadDAO = new LoadDAO();
        loadDAO.insertLoad("testContent", "testHarbor");
        ArrayList<Load> registeredLoads = loadDAO.getLoads();
        Load load = registeredLoads.get(0);

        int sizeResult = registeredLoads.size();
        String harborResult = load.getHarbor();
        String contentResult = load.getContent();

        int expectedSize = 1;
        String expectedHarbor = "testHarbor";
        String expectedContent = "testContent";

        assertThat(sizeResult,is(expectedSize));
        assertThat(harborResult,is(expectedHarbor));
        assertThat(contentResult,is(expectedContent));

        loadDAO.clearAllEntries();
      }

    @Test
      public void filterResultsTest(){
         LoadDAO loadDAO = new LoadDAO();
        loadDAO.insertLoad("testContent", "testHarbor");
        loadDAO.insertLoad("testContent2", "testHarbor2");
        ArrayList<Load> registeredFilteredLoads = loadDAO.getLoadsFilteredByHarbor("testHarbor");
        Load load = registeredFilteredLoads.get(0);

        int sizeResult = registeredFilteredLoads.size();
        String harborResult = load.getHarbor();
        String contentResult = load.getContent();

        int expectedSize = 1;
        String expectedHarbor = "testHarbor";
        String expectedContent = "testContent";

        assertThat(sizeResult,is(expectedSize));
        assertThat(harborResult,is(expectedHarbor));
        assertThat(contentResult,is(expectedContent));

        loadDAO.clearAllEntries();
      }




}
