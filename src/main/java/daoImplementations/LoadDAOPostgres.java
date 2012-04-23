package daoImplementations;

import databaseAccess.LoadDAO;
import databaseAccess.LoadNotFoundException;
import model.Load;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;


public class LoadDAOPostgres implements LoadDAO {

    private static Connection getConnection() throws URISyntaxException, SQLException {
    URI dbUri = new URI(System.getenv("DATABASE_URL"));

    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

    return DriverManager.getConnection(dbUrl, username, password);
    }

    @Override
    public Load insertLoad(String content, String harbor, String destination) throws Exception{
        return null;

    }

    @Override
    public void clearAllEntries() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Load updateLoad(Load load) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Load getLoad(int loadID) throws LoadNotFoundException, SQLException, URISyntaxException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        ResultSet loadValues = stmt.executeQuery("SELECT * FROM loads WHERE id = "+loadID+";");
        Load tempLoad = new Load(Integer.parseInt(loadValues.getString("id")),loadValues.getString("content"),loadValues.getString("harbor"),loadValues.getString("destination"));
        return tempLoad;
        }

    @Override
    public ArrayList<Load> getReservedLoads() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ArrayList<Load> getNotReservedLoadsFilteredByHarbor(String s) throws SQLException, URISyntaxException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id FROM loads");
        ArrayList<Load> tempLoads = new ArrayList<Load>();
        while(rs.next())
            try {
                tempLoads.add(getLoad(Integer.parseInt(rs.getString("id"))));
            } catch (LoadNotFoundException e) {

            }
        return tempLoads;

    }

    @Override
    public ArrayList<Load> getAllLoads() throws Exception{
              return null;
    }
}
