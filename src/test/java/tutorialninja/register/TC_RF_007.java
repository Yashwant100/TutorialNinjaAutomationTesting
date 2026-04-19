package tutorialninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.CommonUtils;

public class TC_RF_007 {
	// program to verify Register Account using mismatch Password
	
	@Test
	public void verifyRegisteringAccountUsingMismatchPassword() {
		// Launching Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		// Opening URl
		driver.get("https://tutorialsninja.com/demo/");
		
		// LOcating element
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.id("input-firstname")).sendKeys("Yashwant");
		driver.findElement(By.id("input-lastname")).sendKeys("Saini");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateEmailWithTimestamp());
		driver.findElement(By.id("input-telephone")).sendKeys("123456");
		driver.findElement(By.id("input-password")).sendKeys("Yashwant123");
		driver.findElement(By.id("input-confirm")).sendKeys("Yashwant");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		// Validating the Test CAse
		String expectedWarningMessage = "Password confirmation does not match password!";
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-confirm']/following-sibling::div")).getText(), expectedWarningMessage);
		
		// Exiting Browser
		driver.quit();

	}

}
