package databaseAccess;

import database.NoSuchUserNameException;
import database.WrongPasswordException;
import model.User;

public interface UserDAO {
    public User authenticate(String userName, String password) throws NoSuchUserNameException, WrongPasswordException;
    public void registerUser(String userName, String password);
}
