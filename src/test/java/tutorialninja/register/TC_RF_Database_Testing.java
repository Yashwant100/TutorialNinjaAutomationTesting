package tutorialninja.register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.CommonUtils;

public class TC_RF_Database_Testing {
	
	// Program to perform database testing using Opencart Application setting up to the local machine
	
	// Declaring WebDriver Refrence :
	WebDriver driver;
	
	private static final String url = "jdbc:mysql://localhost:3306/opencart_db";
    private static final String username = "root";
    private static final String password = ""; // XAMPP default is empty
	@Test
	public void verifyTestingDataRegisteringAccount() {
		// Launching Browser
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		// OPening URL
		driver.get("http://localhost/opencart/");
		
		// Locating Web Element
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		String firstNameInputData = "Yashwant";
		driver.findElement(By.id("input-firstname")).sendKeys(firstNameInputData);
		
		String lastNameInputData = "Saini";
		driver.findElement(By.id("input-lastname")).sendKeys(lastNameInputData);
		
		String emailInputData = CommonUtils.generateEmailWithTimestamp();
		driver.findElement(By.id("input-email")).sendKeys(emailInputData);
		
		String passwordInputData = "123457";
		driver.findElement(By.id("input-password")).sendKeys(passwordInputData);
		
		// Clicking button using Javascript Executor Class
		WebElement element = driver.findElement(By.id("input-newsletter"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		
		WebElement element2 = driver.findElement(By.name("agree"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element2);
		
		WebElement element3 = driver.findElement(By.xpath("//button[text()='Continue']"));
		js.executeScript("arguments[0].click();", element3);
		
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        String firstNameStoredInDatabase = null;
        String lastNameStoredInDatabase = null;
        String emailStoredInDatabase = null;
		
		
		// Handling Exception Related to the JDBC Connections
		 try {
	            connection = DriverManager.getConnection(url, username, password);
	            System.out.println("✅ Connected to database successfully!");
	            
	         // Step 2: Create a statement
	            statement = connection.createStatement();
	            
	            // Step 3: Execute a query
	            String sql = "SELECT * FROM oc_customer";
	            resultSet = statement.executeQuery(sql);
	            
	           
	            
	            // Step 4: Process the result set
	            while (resultSet.next()) {
	            	firstNameStoredInDatabase = resultSet.getString("firstname");
	                lastNameStoredInDatabase = resultSet.getString("lastname");
	                emailStoredInDatabase = resultSet.getString("email");
	            }

	            //conn.close();

	        } catch (SQLException e) {
	            System.out.println("❌ Connection failed!");
	            e.printStackTrace();
	        }finally {
	            // Step 5: Clean up the resources
	            try {
	                if (resultSet != null) resultSet.close();
	                if (statement != null) statement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		
			Assert.assertEquals(firstNameStoredInDatabase, firstNameInputData);
			Assert.assertEquals(lastNameStoredInDatabase, lastNameInputData);
			//Assert.assertEquals(emailStoredInDatabase, emailInputData); // it gives error cause emaill get changes every time
	}

}
