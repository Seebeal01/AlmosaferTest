package almosaferProject;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FinallTest extends Parameters {

	@BeforeTest
	public void mytest() {
		GeneralSetup();
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();
	}

	@Test(priority = 1)
	public void testAlmosaferDefaultLanguageisEN() {

		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualLanguage, ExpectedDefaultLanage);

	}

	@Test(priority = 2)
	public void CheckdefaultCurrencyisSAR() {

		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3)
	public void CheckContactNumber() {

		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
	}

	@Test(priority = 4)
	public void CheckQitagLogo() {
		boolean ExpectedResultsForTheLogo = true;
		WebElement theFooter = driver.findElement(By.tagName("footer"));

		WebElement logo = theFooter.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"))
				.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));

		boolean ActualResultForThelogo = logo.isDisplayed();

		Assert.assertEquals(ActualResultForThelogo, ExpectedResultsForTheLogo);

	}

	@Test(priority = 5)
	public void checkHotel() {
		String ActualSelectAreaValue = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		String ExpectedSelectAreaValue = "false";
		Assert.assertEquals(ActualSelectAreaValue, ExpectedSelectAreaValue);

	}

	@Test(priority = 6)
	public void CheckDepatureDate() {

	

		List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("LiroG"));

		String ActualDepatureDate = depatureAndArrivalDates.get(0).getText();
		String ActualReturnDate = depatureAndArrivalDates.get(1).getText();

		int ActualDepatureDateAsInt = Integer.parseInt(ActualDepatureDate);
		int ActualreturnDateAsInt = Integer.parseInt(ActualReturnDate);

		Assert.assertEquals(ActualDepatureDateAsInt, Tomorrow);
		Assert.assertEquals(ActualreturnDateAsInt, ThedayAfterTomorrow);

	}

	@Test(priority = 7)
	public void test() throws InterruptedException {
		String[] URLs = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		int RandomIndex = rand.nextInt(URLs.length);
		driver.get(URLs[RandomIndex]);
	}

	@Test(priority = 8)

	public void FillHotelTab() {
		driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).click();

		WebElement SearchHotelInput = driver.findElement(By.className("sc-phbroq-2"));
		if (driver.getCurrentUrl().contains("en")) {
			String[] cityName = { "Dubai", "Jeddah", "Riyadh" };

			int randomIndexForThecity = rand.nextInt(cityName.length);

			String SearchHote = cityName[randomIndexForThecity];
			SearchHotelInput.sendKeys(SearchHote);
			WebElement firstResult = driver.findElement(By.className("sc-phbroq-5"));
			firstResult.click();

		} else {
			String[] cityName = { "دبي", "جدة", "الرياض" };

			int randomIndexForThecity = rand.nextInt(cityName.length);

			String SearchHote = cityName[randomIndexForThecity];
			SearchHotelInput.sendKeys(SearchHote);
			WebElement firstResult = driver.findElement(By.className("sc-phbroq-5"));
			firstResult.click();

		}

	}

	@Test(priority = 9)
	public void HotelSearchBox() throws InterruptedException {

		int option = rand.nextInt(2); // Generates 0 or 1

		if (option == 0) {
			// Select "1 room, 2 adults, 0 children"
			WebElement roomSelection = driver.findElement(By.xpath("//option[@value='A']"));
			roomSelection.click();

		} else {
			// Select "1 room, 1 adult, 0 children"
			WebElement roomSelection = driver.findElement(By.xpath("//option[@value='B']"));
			roomSelection.click();

		}

	}

	@Test(priority = 10)
	public void searchButton() {

		WebElement SearchButton = driver.findElement(By.className("sc-1vkdpp9-6"));
		SearchButton.click();

	}

	@Test(priority = 11)

	public void CheckThePageFullyLoaded() throws InterruptedException {

		boolean expectedResult = true;
		Thread.sleep(10000);
		String results = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']"))
				.getText();

		boolean finished = results.contains("وجدنا") || results.contains("found");

		Assert.assertEquals(finished, expectedResult);

	}

	@Test(priority = 12)

	public void SortItemsLowestToHighestPrice() {

		boolean expectedResults = true;
		WebElement LowestPriceSortButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));

		LowestPriceSortButton.click();

		WebElement PricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));

		List<WebElement> AllPrices = PricesContainer.findElements(By.className("Price__Value"));

		String LowestPrice = AllPrices.get(0).getText();
		String HighestPrice = AllPrices.get(AllPrices.size() - 1).getText();

		int LowestPriceAsInt = Integer.parseInt(LowestPrice);
		int HighestPriceAsInt = Integer.parseInt(HighestPrice);

		boolean ActualResults = LowestPriceAsInt < HighestPriceAsInt;

		Assert.assertEquals(ActualResults, expectedResults);

	}
}
