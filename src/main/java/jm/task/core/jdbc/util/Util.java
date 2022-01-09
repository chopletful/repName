package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private String url = "jdbc:mysql://localhost:3306/new_schema";
    private String user = "root";
    private String pass = "98ofehom";
    private Connection conn;
//test
    public Util() {
        try {
            conn = DriverManager.getConnection(url, user, pass);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection(){
        return conn;
    }
}
