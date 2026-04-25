package tutorialninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.CommonUtils;

public class TC_RF_014 {
	// Program for checking whether the password field follow the complexity standards or not
	
	// WebDriver instance declaration
	WebDriver driver;
	@Test(dataProvider="passwordSupplier")
	public void verifyPasswordComplexityStandards(String passwordText) {
		// Launching Browser
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		// Opening a URL
		driver.get("https://tutorialsninja.com/demo/");
		
		// Locating Web Element
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("Yash");
		driver.findElement(By.id("input-lastname")).sendKeys("Saini");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateEmailWithTimestamp());
		driver.findElement(By.id("input-telephone")).sendKeys("987459320");
		driver.findElement(By.id("input-password")).sendKeys(passwordText);
		driver.findElement(By.id("input-confirm")).sendKeys(passwordText);
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		// Validating the Password standard
		String ExpectedWarningMessage = "Password entered is not matching the complexity standards";
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), ExpectedWarningMessage);
		Assert.assertFalse(driver.findElement(By.xpath("//ul[@class=''breadcrumb]//a[text()='Success']")).isDisplayed());
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@DataProvider(name="passwordSupplier")
	public Object supplyPassword() {
		Object[][] data = {{"12345"},{"abcdefghi"},{"abcd123$"},{"abcd1234"},{"ABCD456#"}};
		return data;
	}

}
