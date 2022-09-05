package database;

import org.testng.annotations.Test;

import java.sql.*;
import java.sql.Connection;

public class ConnectMySQL {

    @Test
    public void testDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        Statement smt;
        Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/forpractise", "root", "Welcome@123");
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

        //Updating the values
        String sql = "insert into info values (?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,"Jacob");
        preparedStatement.setString(2, "Jacob@gmail.com");
        int n = preparedStatement.executeUpdate();
        if (n == 1){
            System.out.println("Record Inserted");
        }else{
            System.out.println("Record not Inserted");
        }

        //Deleting the values
        /*String del = "delete from info where Name = ?";
        PreparedStatement preparedStatements = con.prepareStatement(del);
        preparedStatements.setString(1,"Jacob");
        int d = preparedStatements.executeUpdate();
        if (d == 1){
            System.out.println("Record Deleted");
        }else{
            System.out.println("Record not Deleted");
        }*/
    }
}
