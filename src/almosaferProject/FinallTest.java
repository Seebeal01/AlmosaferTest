package almosaferProject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FinallTest {
	WebDriver driver = new ChromeDriver();
	String AlmosaferURL = "https://global.almosafer.com/en";
	String ExpectedDefaultLanage = "en";

	@BeforeTest
	public void mytest() {

		driver.manage().window().maximize();
		driver.get(AlmosaferURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();

	}

	@Test
	public void testAlmosaferAssertions() {

	
		WebElement language = driver.findElement(By.cssSelector(".sc-gkFcWv.jJNggu"));
		String Actual = language.getText();
		String expected = "العربية";
		Assert.assertSame(Actual, expected);
		
		

	}
}
