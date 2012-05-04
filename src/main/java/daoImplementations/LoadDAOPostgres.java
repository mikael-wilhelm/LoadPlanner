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

    @Override
    public Load insertLoad(String content, String harbor, String destination) throws Exception {
        Load tempLoad = new Load(getValidId(), content, harbor, destination);
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("INSERT INTO loads (id, content, harbor, destination) VALUES (?,?,?,?)");
            stmt.setInt(1, tempLoad.getId());
            stmt.setString(2, tempLoad.getContent());
            stmt.setString(3, tempLoad.getHarbor());
            stmt.setString(4, tempLoad.getDestination());
            stmt.execute();
        } catch (SQLException sql){

        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {throw new ServerException();};
            try { if (conn != null) conn.close(); } catch (SQLException e) {throw new ServerException();};
        }
        return tempLoad;
    }

    private int getValidId() throws ServerException {
        int actualId;
        Random rnd = new Random();
        actualId = rnd.nextInt(10000);
        while (!testIfIdAvailable(actualId))
            actualId = rnd.nextInt(10000);
        return actualId;
    }

    private boolean testIfIdAvailable(int id) throws ServerException {
        boolean isOk = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT id FROM loads WHERE id=?");
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            isOk = !rs.next();
        } catch (SQLException sql){

        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {throw new ServerException();};
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {throw new ServerException();};
            try { if (conn != null) conn.close(); } catch (SQLException e) {throw new ServerException();};
        }
        return isOk;
    }

    @Override
    public Load updateLoad(Load load) throws ServerException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("UPDATE loads SET content=?, harbor=?, destination=?, reserved=?,reservedBy=? WHERE id=?;");
            ps.setString(1, load.getContent());
            ps.setString(2, load.getHarbor());
            ps.setString(3, load.getDestination());
            ps.setBoolean(4, load.getReserved());
            ps.setString(5, load.getReservedBy().toString());
            ps.setInt(6, load.getId());
            ps.execute();
        } catch (SQLException sql){

        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) {throw new ServerException();};
            try { if (conn != null) conn.close(); } catch (SQLException e) {throw new ServerException();};
        }
        return load;
    }

    @Override
    public Load getLoad(int loadID) throws LoadNotFoundException,ServerException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Load tempLoad = null;
        try {
            Connection connection = getConnection();
            ps = connection.prepareStatement("SELECT * FROM loads WHERE id = ?;");
            ps.setInt(1,loadID);
            rs = ps.executeQuery();
            if(rs.next())
                tempLoad = new Load(Integer.parseInt(rs.getString("id")), rs.getString("content"), rs.getString("harbor"), rs.getString("destination"));
            else
                throw new LoadNotFoundException();
        }
        catch (SQLException sql){

        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {throw new ServerException();};
            try { if (ps != null) ps.close(); } catch (SQLException e) {throw new ServerException();};
            try { if (conn != null) conn.close(); } catch (SQLException e) {throw new ServerException();};
        }
        return tempLoad;
    }

    @Override
    public List<Load> getReservedLoads(User user) throws LoadNotFoundException, ServerException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Load> tempLoads = new ArrayList<Load>();
        try {
            Connection connection = getConnection();
            ps = connection.prepareStatement("SELECT id,reservedBy,userName FROM loads,loadUsers WHERE loads.reservedBy=loadUsers.userName AND reserved=TRUE AND reservedBy = ?;");
            ps.setString(1, user.getUserName());
            rs = ps.executeQuery();
            while (rs.next())
                    tempLoads.add(getLoad(Integer.parseInt(rs.getString("id"))));
        }
        catch (SQLException sql){

        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {throw new ServerException();};
            try { if (ps != null) ps.close(); } catch (SQLException e) {throw new ServerException();};
            try { if (conn != null) conn.close(); } catch (SQLException e) {throw new ServerException();};
        }
        return tempLoads;
    }

    @Override
    public List<Load> getNotReservedLoadsFilteredByHarbor(String s) throws ServerException, LoadNotFoundException{

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Load> tempLoads = new ArrayList<Load>();
        try {
            Connection connection = getConnection();
            ps = connection.prepareStatement("SELECT id FROM loads WHERE reserved=FALSE;");
            rs = ps.executeQuery();
            while (rs.next())
                    tempLoads.add(getLoad(Integer.parseInt(rs.getString("id"))));
        }
        catch (SQLException sql){
            throw new ServerException();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {throw new ServerException();};
            try { if (ps != null) ps.close(); } catch (SQLException e) {throw new ServerException();};
            try { if (conn != null) conn.close(); } catch (SQLException e) {throw new ServerException();};
        }
        return tempLoads;
    }

    @Override
    public List<Load> getAllLoads() throws Exception {
        return null;
    }

    private static Connection getConnection() throws ServerException {
        URI dbUri = null;
        try {
            dbUri = new URI(System.getenv("SHARED_DATABASE_URL"));
        } catch (URISyntaxException e) {
            throw new ServerException();
        }
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            return connection;
        } catch (SQLException e) {
            throw new ServerException();
        }

    }
}
