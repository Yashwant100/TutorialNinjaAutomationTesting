package tutorialninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_001 {
    @Test
	public void verifyRegisteringWithMandatoryFields() {
		// LAunching Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Opening URL
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		
		// LOcating element
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("Yashwant");
		driver.findElement(By.id("input-lastname")).sendKeys("Saini");
		driver.findElement(By.id("input-email")).sendKeys(generateEmailWithTimestamp());
		driver.findElement(By.id("input-telephone")).sendKeys("123456");
		driver.findElement(By.id("input-password")).sendKeys("Yashwant123");
		driver.findElement(By.id("input-confirm")).sendKeys("Yashwant123");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		// Validating if the account is created or not
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		
		String expectedHeading = "Your Account Has Been Created!";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success']//h1")).getText(),expectedHeading);
		
		driver.findElement(By.xpath("//a[text()='Continue']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	

	}
	
	public String generateEmailWithTimestamp() {
		return new Date().toString().replaceAll("\\s","").replaceAll("\\:","")+"@gmail.com";
	}

}
