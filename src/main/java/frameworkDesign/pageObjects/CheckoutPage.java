package frameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkDesign.abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[placeholder=\"Select Country\"]")
	WebElement countrySearchBar;
	
	@FindBy(css=".ta-results button")
	List<WebElement> matchedCountriesList;
	
	@FindBy(css="[class=\"btnn action__submit ng-star-inserted\"]")
	WebElement placeOrderButton;
	
	By matchedCountries = By.cssSelector(".ta-results button");
	
	public void searchCountry(String countryName)
	{
		Actions action = new Actions(driver);
		action.sendKeys(countrySearchBar, countryName).build()
				.perform();
		waitUntilVisibilityOfAllElements(matchedCountries);
	}
	
	public void getMatchedCountry()
	{

		WebElement countrySearched = matchedCountriesList.stream().filter(country -> country.getText().equals("India"))
				.findFirst().orElse(null);

		countrySearched.click();
	}
	
	public OrderConfirmationPage placeOrder()
	{
		placeOrderButton.click();
		OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
		return orderConfirmationPage;

	}

}
