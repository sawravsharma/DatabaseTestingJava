package database;

import org.testng.annotations.Test;

import java.sql.*;

public class DeleteValues {
    @Test
    public void testDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        Statement smt;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/forpractise", "root", "Welcome@123");
        System.out.println("Connected to MySQL");

        smt = con.createStatement();

        //Deleting the values
        String del = "delete from info where Name = ?";
        PreparedStatement preparedStatements = con.prepareStatement(del);
        preparedStatements.setString(1,"Jacob");
        int d = preparedStatements.executeUpdate();
        if (d == 1){
            System.out.println("Record Deleted");
        }else{
            System.out.println("Record not Deleted");
        }
    }
}
