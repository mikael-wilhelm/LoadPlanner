package daoImplementations;

import databaseAccess.LoadDAO;
import exceptions.LoadNotFoundException;
import exceptions.ServerException;
import model.Load;
import model.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LoadDAOPostgres implements LoadDAO {

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("SHARED_DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        return DriverManager.getConnection(dbUrl, username, password);
    }

    @Override
    public Load insertLoad(String content, String harbor, String destination) throws Exception{
        Load tempLoad = new Load(getValidId(), content, harbor, destination);
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO loads (id, content, harbor, destination) VALUES (?,?,?,?)");
        stmt.setInt(1,tempLoad.getId());
        stmt.setString(2,tempLoad.getContent());
        stmt.setString(3,tempLoad.getHarbor());
        stmt.setString(4,tempLoad.getDestination());

        stmt.execute();

        connection.close();
        return tempLoad;
    }

    private int getValidId() throws SQLException, URISyntaxException {
        int actualId;
        Random rnd = new Random();
        actualId = rnd.nextInt(10000);
        while(!testIfIdAvailable(actualId))
            actualId = rnd.nextInt(10000);
        return actualId;
    }

    private boolean testIfIdAvailable(int id) throws SQLException, URISyntaxException{
        boolean isValid;
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id FROM loads WHERE id="+id+";");

        stmt.close();
        connection.close();
        isValid = !rs.next();
        rs.close();
        return isValid;
    }

    @Override
    public Load updateLoad(Load load) throws SQLException, URISyntaxException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE loads SET content=?, harbor=?, destination=?, reserved=?,reservedBy=? WHERE id=?;");
        ps.setString(1, load.getContent());
        ps.setString(2,load.getHarbor());
        ps.setString(3,load.getDestination());
        ps.setBoolean(4,load.getReserved());
        ps.setString(5,load.getReservedBy().toString());
        ps.setInt(6,load.getId());

        ps.execute();
        connection.close();
        return load;
    }

    @Override
    public Load getLoad(int loadID) throws LoadNotFoundException, SQLException, URISyntaxException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        ResultSet loadValues = stmt.executeQuery("SELECT * FROM loads WHERE id = "+loadID+";");
        loadValues.next();
        Load tempLoad = new Load(Integer.parseInt(loadValues.getString("id")),loadValues.getString("content"),loadValues.getString("harbor"),loadValues.getString("destination"));
        loadValues.close();
        stmt.close();
        connection.close();
        return tempLoad;
    }

    @Override
    public List<Load> getReservedLoads(User user) throws SQLException, URISyntaxException, LoadNotFoundException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT id,reservedBy,userName FROM loads,loadUsers WHERE loads.reservedBy=loadUsers.userName AND reserved=TRUE AND reservedBy = ?;");
        ps.setString(1,user.getUserName());
        ResultSet rs = ps.executeQuery();
        List<Load> tempLoads = new ArrayList<Load>();
        while(rs.next())
                tempLoads.add(getLoad(Integer.parseInt(rs.getString("id"))));
        rs.close();
        ps.close();
        connection.close();
        return tempLoads;
    }

    @Override
    public List<Load> getNotReservedLoadsFilteredByHarbor(String s) throws ServerException, LoadNotFoundException {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM loads WHERE reserved=FALSE;");
            List<Load> tempLoads = new ArrayList<Load>();
            while(rs.next())
                    tempLoads.add(getLoad(Integer.parseInt(rs.getString("id"))));
            connection.close();
            return tempLoads;
        } catch (URISyntaxException e) {
            throw new ServerException();
        } catch (SQLException e) {
            throw new ServerException();
        }
    }

    @Override
    public List<Load> getAllLoads() throws Exception{
        return null;
    }
}
