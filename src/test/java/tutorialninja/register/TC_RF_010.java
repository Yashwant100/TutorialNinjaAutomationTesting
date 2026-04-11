package tutorialninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC_RF_010 {
	// Test case to verify register acoount by providing invalid number
	WebDriver driver;
	@Test
	public void verifyRegisterAccountProvidingInvalidPhone() {
		// Launching Browser
	    driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		// Opening URL
		driver.get("https://tutorialsninja.com/demo/");
		
		// Locating WebElement
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.id("input-firstname")).sendKeys("Yashwant");
		driver.findElement(By.id("input-lastname")).sendKeys("Saini");
		driver.findElement(By.id("input-email")).sendKeys(TC_RF_001.generateEmailWithTimestamp());
		driver.findElement(By.id("input-telephone")).sendKeys("ABCD");// Invalid Phone No.
		driver.findElement(By.id("input-password")).sendKeys("Yashwant123");
		driver.findElement(By.id("input-confirm")).sendKeys("Yashwant123");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		// VAlidating the script
		String expectedWarning = "Telephone number does not appear to be valid!";
		Assert.assertEquals(expectedWarning,driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText());
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
