package se.exjob.daoImplementations;

import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.exceptions.PasswordException;
import se.exjob.databaseAccess.UserDAO;
import se.exjob.model.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class UserDAOPostgres implements UserDAO{
    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("SHARED_DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        return DriverManager.getConnection(dbUrl, username, password);
    }

    @Override
    public User authenticate(String userName, String password) throws NoSuchUserNameException, PasswordException {
        User tempUser = getUser(userName);
        try{
            if(tempUser.getPassword().equals(password))
                return tempUser;
            else
                return null;
        }catch(Exception e){
            return null;
        }
    }

    public User getUser(String userName){

        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT userName, password FROM loadUsers WHERE userName = ?;");
            pstmt.setString(1,userName);
            ResultSet rs = pstmt.executeQuery();
            connection.close();
            rs.next();
            return new User(rs.getString("username"),rs.getString("password"));
        } catch (URISyntaxException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }

    }

    @Override
    public void registerUser(String userName, String password) {
        if(getUser(userName) == null)
            try {
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO loadUsers VALUES(?,?)");
                pstmt.setString(1,userName);
                pstmt.setString(2,password);
                pstmt.execute();
                connection.close();
            } catch (URISyntaxException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
    }
}
