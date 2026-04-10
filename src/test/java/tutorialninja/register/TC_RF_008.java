package tutorialninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_008 {
	// Test Case to illustrate warning message on re-using already registered mail.
	@Test
	public void verifyRegisterAccountUsingExistingEmail() {
		// Launching Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		// Opening URL
		driver.get("https://tutorialsninja.com/demo/");
		
		// Locating Web Element
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.id("input-firstname")).sendKeys("Yashwant");
		driver.findElement(By.id("input-lastname")).sendKeys("Saini");
		driver.findElement(By.id("input-email")).sendKeys("yash345@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("123456");
		driver.findElement(By.id("input-password")).sendKeys("Yashwant123");
		driver.findElement(By.id("input-confirm")).sendKeys("Yashwant");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String warning = driver.findElement(By.xpath("//*[text()='Warning: E-Mail Address is already registered!']")).getText();
		// Validating the script if warning displays or not
		Assert.assertEquals(driver.findElement(By.xpath("//*[text()='Warning: E-Mail Address is already registered!']")).getText(), warning);
		
		// close the browser
		driver.close();
		
	}

}
