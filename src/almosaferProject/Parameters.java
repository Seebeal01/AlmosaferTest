package almosaferProject;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {
	WebDriver driver = new ChromeDriver();
	String AlmosaferURL = "https://global.almosafer.com/en";
	String ExpectedDefaultLanage = "en";
	String ExpectedCurrency = "SAR";
	String ExpectedContactNumber = "+966554400000";
	LocalDate todayDate = LocalDate.now();

	int Tomorrow = todayDate.plusDays(1).getDayOfMonth();
	int ThedayAfterTomorrow = todayDate.plusDays(2).getDayOfMonth();

	Random rand = new Random();
public void GeneralSetup() {
	driver.manage().window().maximize();
	driver.get(AlmosaferURL);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	
	
}
}
