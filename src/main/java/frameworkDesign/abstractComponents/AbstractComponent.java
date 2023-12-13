package frameworkDesign.abstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import frameworkDesign.pageObjects.CartPage;
import frameworkDesign.pageObjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css="[routerlink=\"/dashboard/cart\"]")
	WebElement cartMenu;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderMenu;
	
	
	public void waitUntilAnElementToGetAppearedInThePage(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitUntilAnWebElementToGetAppearedInThePage(WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public void checkAssertTextEquals(WebElement ele, String Message) {
		
		Assert.assertEquals((ele).getText(), Message);
		
	}

	public void waitUntilAnElementToGetDisappeared(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));

	}
	
	public CartPage goToCartPage()
	{
		cartMenu.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;


	}
	
	public OrderPage goToOrdersPage()
	{
		orderMenu.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;


	}
	
	public void waitUntilVisibilityOfAllElements(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));

	}
}
