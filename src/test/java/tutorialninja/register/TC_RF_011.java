package tutorialninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.CommonUtils;

public class TC_RF_011 {

	// Verifying the registering an account using keyboard keys.
	@Test
	public void verifyRegisteringAccountUsingKeyboardKeys(){
		// Launching a Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		// Opening a URL
		driver.get("https://tutorialsninja.com/demo/");
		
		// Locating the Elements
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		// PErforming multiple press using Actions Class
		
		Actions act = new Actions(driver);
		for(int i=1;i<=23;i++) {
			act.sendKeys(Keys.TAB).perform();
		}
		
		// sending text to the empty field using Actions class
		act.sendKeys("Yashwant").pause(Duration.ofSeconds(1))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys("Saini")
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(CommonUtils.generateEmailWithTimestamp())
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys("123455432")
		.pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
		.sendKeys("12345").pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
		.pause(Duration.ofSeconds(1)).sendKeys("12345").pause(Duration.ofSeconds(1))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.LEFT)
		.pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
		.pause(Duration.ofSeconds(1)).sendKeys(Keys.SPACE).pause(Duration.ofSeconds(1))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER).build().perform();
		
		// Validating if acoount got created or not
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='column-right']//a[text()='Logout']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());
		
		// Exit the BRowser
		driver.quit();
	}
}
