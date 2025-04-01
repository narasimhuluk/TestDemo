package NKTech.TESTRahul;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JdbcConnections {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String host = "localhost";
		String port = "3306";

		Connection conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/demo", "root", "root");
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("select * from credentials where scenario='zerobalancecard';");

		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		while (rs.next()) {
			driver.findElement(By.id("userEmail")).sendKeys(rs.getString("username"));
			driver.findElement(By.id("userPassword")).sendKeys(rs.getString("passwords"));
		}
		driver.findElement(By.id("login")).click();
	}

}
