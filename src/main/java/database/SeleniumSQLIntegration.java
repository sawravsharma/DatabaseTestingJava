package database;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

public class SeleniumSQLIntegration {

    @Test(dataProvider = "DP")
    public void validateLogin(String uname, String pword){
        System.out.println("DB values : " + uname);
        System.out.println("DB values : " + pword);
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(uname);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pword);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    @DataProvider(name = "DP")
    public String[][] feedDP() throws ClassNotFoundException, SQLException{
            String data[][] = getDBValues();
        return data;
    }
    public String[][] getDBValues() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        Statement stmt;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/forpractise", "root", "Welcome@123");
        System.out.println("Connected to MySQL");

        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("select * from login");
        rs.last();

        int rows = rs.getRow();
        System.out.println("Number of rows : " + rows);

        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        System.out.println("Number of cols : " + cols);

        String[][] data = new String[rows][cols];

        int i = 0;
        rs.beforeFirst();
        while (rs.next()){
            for(int j=0;j<cols;j++){
                data[i][j] = rs.getString(j+1);
                System.out.println(data[i][j]);
            }
            i++;
        }
        return data;
    }
}
