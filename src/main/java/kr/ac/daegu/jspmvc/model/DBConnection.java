package kr.ac.daegu.jspmvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL  = "jdbc:mariadb://localhost:3306/dgd";
    private static final String DB_USER = "root";
    private static final String DB_PW   = "0000";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Connection, PreparedStatement, ResultSet은 interface 객체이다.
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
    }
}