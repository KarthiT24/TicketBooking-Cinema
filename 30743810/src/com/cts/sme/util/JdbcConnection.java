package com.cts.sme.util;
import java.sql.*;
public class JdbcConnection {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/TicketBookingSystem";
    private static final String USER = "User1";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER,PASSWORD);
    }
}
