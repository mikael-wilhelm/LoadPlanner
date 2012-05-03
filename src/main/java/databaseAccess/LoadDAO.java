package databaseAccess;

import exceptions.LoadNotFoundException;
import exceptions.ServerException;
import model.Load;
import model.User;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface LoadDAO {
    public Load insertLoad(String content, String harbor,String destination) throws Exception;
    public Load updateLoad(Load load) throws SQLException, URISyntaxException;
    public Load getLoad(int loadID) throws LoadNotFoundException, SQLException, URISyntaxException;
    public List<Load> getReservedLoads(User user) throws SQLException, URISyntaxException, LoadNotFoundException;
    public List<Load> getNotReservedLoadsFilteredByHarbor(String s) throws ServerException, LoadNotFoundException;
    public List<Load> getAllLoads() throws Exception;

}
