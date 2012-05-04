package se.exjob.databaseAccess;

import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.exceptions.PasswordException;
import se.exjob.model.User;

public interface UserDAO {
    public User authenticate(String userName, String password) throws NoSuchUserNameException, PasswordException;
    public void registerUser(String userName, String password);
}
