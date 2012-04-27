package databaseAccess;

import database.NoSuchUserNameException;
import database.PasswordException;
import model.User;

public interface UserDAO {
    public User authenticate(String userName, String password) throws NoSuchUserNameException, PasswordException;
    public void registerUser(String userName, String password);
}
