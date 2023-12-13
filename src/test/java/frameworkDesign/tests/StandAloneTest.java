package frameworkDesign.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";

		System.setProperty("webdriver.chrome.driver", "D:/driver/chromedriver_win32/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Invoking the url
		driver.get("https://rahulshettyacademy.com/client");

		// Login code
		driver.findElement(By.id("userEmail")).sendKeys("qatestprac@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Qa@rahul0");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));


		Assert.assertEquals(driver.findElement(By.cssSelector("[id=\"toast-container\"] div div")).getText(),
				"Login Successfully");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));



		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));

		//Thread.sleep(3000);

		// Getting products
		List<WebElement> products = driver.findElements(By.cssSelector(".card"));

		WebElement requiredProduct = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		requiredProduct.findElement(By.cssSelector(".card button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

		Assert.assertEquals(driver.findElement(By.cssSelector("[id=\"toast-container\"] div div")).getText(),
				"Product Added To Cart");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(".ng-animating")));


		driver.findElement(By.cssSelector("[routerlink=\"/dashboard/cart\"]")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class=\"cartSection\"] h3")));

		List<WebElement> cartProducts = driver.findElements(By.cssSelector("[class=\"cartSection\"] h3"));

		boolean checkAddedProduct = cartProducts.stream().anyMatch(product -> product.getText().equals(productName));
		Assert.assertTrue(checkAddedProduct);

		driver.findElement(By.xpath("//button[text()=\"Checkout\"]")).click();
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("[placeholder=\"Select Country\"]")), "India").build()
				.perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results button")));


		List<WebElement> matchedCountries = driver.findElements(By.cssSelector(".ta-results button"));

		WebElement countrySearched = matchedCountries.stream().filter(country -> country.getText().equals("India"))
				.findFirst().orElse(null);

		countrySearched.click();
		
		driver.findElement(By.cssSelector("[class=\"btnn action__submit ng-star-inserted\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"hero-primary\"]")));

		
		String orderConfirmationMessage = driver.findElement(By.cssSelector("[class=\"hero-primary\"]")).getText();
		Assert.assertEquals(orderConfirmationMessage, "THANKYOU FOR THE ORDER.");
		driver.quit();
	}

}
