package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private Util util = new Util();


    @Override
    public void createUsersTable() throws SQLException {
        Statement statement = util.getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (id MEDIUMINT not null auto_increment," +
                " name VARCHAR(40) not null," +
                " lastName VARCHAR(40) not null," +
                " age MEDIUMINT not null," +
                "  PRIMARY KEY(id));");

    }

    public void dropUsersTable() throws SQLException {
        Statement statement = util.getConnection().createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS Users");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement statement = util.getConnection().prepareStatement("INSERT INTO Users(name,lastname,age)" +
                " VALUES(?, ?,?);");
        statement.setString(1,name);
        statement.setString(2,lastName);
        statement.setString(3, String.valueOf(age));
        statement.executeUpdate();
        System.out.printf("User с именем – %s добавлен в базу данных \n",name);



    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement statement = util.getConnection().prepareStatement("DELETE FROM Users WHERE id = ?");
        statement.setString(1,Long.toString(id));

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Statement statement = util.getConnection().createStatement();
        String query = "select * from Users";
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            User user = new User();
            user.setId((long) resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setAge((byte) resultSet.getInt(4));
            users.add(user);
        }

        for(User user:users){
            System.out.println(user);
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        Statement statement = util.getConnection().createStatement();
        try {
            statement.executeUpdate("TRUNCATE TABLE Users");
        }catch (SQLException e){}

    }
}
