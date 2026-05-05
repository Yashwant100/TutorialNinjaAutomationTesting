package tutorialninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC_RF_015 {
	// Program to verified the Register Account fields Height, Width and Alighnment on the page.
	// WebDriver instance declaration
    WebDriver driver;
    
    @Test
	public void verifyRegisteringAccountFields() {
		// Launching Browser
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		// Opening URL
		driver.get("https://tutorialsninja.com/demo/");
		
		// Locating Web Element
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//*[text()='Register']")).click();
		
		String expectedHeight = "34px";
		String expectedWidth = "701.25px";
		String actualHeight = driver.findElement(By.xpath("//input[@id='input-firstname']")).getCssValue("height");
		String actualWidth = driver.findElement(By.xpath("//input[@id='input-firstname']")).getCssValue("width");
		
		String actualLastNameHeight = driver.findElement(By.id("input-lastname")).getCssValue("height");
		String actualLastNameWidth = driver.findElement(By.id("input-lastname")).getCssValue("width");
		
		String actualEmailHeight = driver.findElement(By.id("input-email")).getCssValue("height");
		String actualEmailWidth = driver.findElement(By.id("input-email")).getCssValue("width");
		
		String actualTelephoneHeight = driver.findElement(By.id("input-telephone")).getCssValue("height");
		String actualTelephoneWidth = driver.findElement(By.id("input-telephone")).getCssValue("width");


		System.out.println(actualHeight +" "+ actualWidth);
		
		// Validating part
		Assert.assertEquals(expectedHeight, actualHeight); // FirstName Field
		Assert.assertEquals(expectedWidth, actualWidth);
		
		Assert.assertEquals(expectedHeight, actualLastNameHeight); // LastName Field
		Assert.assertEquals(expectedWidth, actualLastNameWidth);
		
		Assert.assertEquals(expectedHeight, actualEmailHeight); // Email Field
		Assert.assertEquals(expectedWidth, actualEmailWidth);
		
		Assert.assertEquals(expectedHeight, actualTelephoneHeight); // Telephone Field
		Assert.assertEquals(expectedWidth, actualTelephoneWidth);
	}
    @AfterMethod
    public void tearDown() {
    	driver.quit();
    }

}
