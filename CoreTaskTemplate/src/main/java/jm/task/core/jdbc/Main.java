package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Ivan","Ivanov",(byte)27);
        user.saveUser("Fedor","Fedorov",(byte)27);
        //user.removeUserById(1);
        user.getAllUsers();
        //user.dropUsersTable();
        //user.dropUsersTable();
        //user.cleanUsersTable();
    }
}
