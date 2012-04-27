package daoImplementations;

import exceptions.NoSuchUserNameException;
import database.Users;
import exceptions.PasswordException;
import databaseAccess.UserDAO;
import model.User;

public class UserDAOHashTable implements UserDAO{

    @Override
    public User authenticate(String userName, String password) throws NoSuchUserNameException, PasswordException {
        Users users = Users.getInstance();
        return users.authenticate(userName,password);
    }

    @Override
    public void registerUser(String userName, String password) {
        Users.getInstance().registerUser(userName,password);
    }
}
