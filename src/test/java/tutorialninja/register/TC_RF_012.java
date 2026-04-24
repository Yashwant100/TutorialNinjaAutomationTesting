package tutorialninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC_RF_012 {
	// PRogram for verifying if the proper placeholder available or not to the element.
	WebDriver driver;
	@Test
	public void verifyingElementPlaceholder() {
		// Launching Browser
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		// Opening URL
		driver.get("https://tutorialsninja.com/demo/");
		
		// Locating Element
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		// Validating the test case
		String ExpectedFirstNamePlaceholderText = "First Name";
		Assert.assertEquals(driver.findElement(By.id("input-firstname")).getAttribute("placeholder"), ExpectedFirstNamePlaceholderText);
		
		String ExpectedEmailPlaceholderText = "E-Mail";
		Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("placeholder"), ExpectedEmailPlaceholderText);
		
		String ExpectedTelephonePlaceholderText = "Telephone";
		Assert.assertEquals(driver.findElement(By.id("input-telephone")).getAttribute("placeholder"), ExpectedTelephonePlaceholderText);
	}
	@AfterMethod
	public void tearDown() {
		// Closing the browser window
		driver.close();
	}

}
