package databaseAccess;

import exceptions.NoSuchUserNameException;
import exceptions.PasswordException;
import model.User;

public interface UserDAO {
    public User authenticate(String userName, String password) throws NoSuchUserNameException, PasswordException;
    public void registerUser(String userName, String password);
}
