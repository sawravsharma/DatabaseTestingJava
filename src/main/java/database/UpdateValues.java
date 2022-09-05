package database;

import org.testng.annotations.Test;

import java.sql.*;

public class UpdateValues {
    @Test
    public void testDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        Statement smt;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/forpractise", "root", "Welcome@123");
        System.out.println("Connected to MySQL");

        smt = con.createStatement();

        //Updating the values
        String sql = "insert into info values (?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, "Jacob");
        preparedStatement.setString(2, "Jacob@gmail.com");
        int n = preparedStatement.executeUpdate();
        if (n == 1) {
            System.out.println("Record Inserted");
        } else {
            System.out.println("Record not Inserted");
        }
    }
}
