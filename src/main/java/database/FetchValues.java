package database;

import org.testng.annotations.Test;

import java.sql.*;

public class FetchValues {
    @Test
    public void testDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        Statement smt;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/forpractise", "root", "Welcome@123");
        System.out.println("Connected to MySQL");

        smt = con.createStatement();

        //Fetching the values
        ResultSet rs = smt.executeQuery("select * from info");
        while (rs.next()) {
            String firstName = rs.getString("Name");
            System.out.println("Database record is " + firstName);

            String emailAddress = rs.getString("Email");
            System.out.println("Database record is " + emailAddress);
        }
    }
}
