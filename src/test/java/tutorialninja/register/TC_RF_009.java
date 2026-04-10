package tutorialninja.register;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class TC_RF_009 {
  // Peogram to verify Register Account Page Using Invalid Email Address
	@Test
	public void verifyRegisterAccountPageUsingInvalidEmail() throws InterruptedException, IOException {
		// Launching BRowser
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
		driver.findElement(By.id("input-email")).sendKeys("yash345");
		driver.findElement(By.id("input-telephone")).sendKeys("123456");
		driver.findElement(By.id("input-password")).sendKeys("Yashwant123");
		driver.findElement(By.id("input-confirm")).sendKeys("Yashwant");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		Thread.sleep(3000);
		// Capture the screen shot on warning and save it to the folder
		File screenshot1 =driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshot1, new File(System.getProperty("user.dir")+"\\screenshots\\ActualSC.png"));
		
		// Comparing both the image to validate the diffrence
		BufferedImage actualBImg = ImageIO.read(new File(System.getProperty("user.dir")+"\\screenshots\\ActualSC.png"));
		BufferedImage expectedBImg = ImageIO.read(new File(System.getProperty("user.dir")+"\\screenshots\\sc1.png"));
		
		ImageDiffer imgdiffer = new ImageDiffer();
		ImageDiff imgdifference = imgdiffer.makeDiff(expectedBImg, actualBImg);
		
		Assert.assertFalse(imgdifference.hasDiff());
		
		// Closing the browser
		driver.close();
	}
}
