package frameworkDesign.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import frameworkDesign.abstractComponents.AbstractComponent;

public class GetProducts extends AbstractComponent {
	
	WebDriver driver;

	public GetProducts(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css=".card")
	List<WebElement> products;
	
	@FindBy(css="[id=\"toast-container\"] div div")
	WebElement toastPopupText;
	
	
	
	By productsBy = By.cssSelector(".mb-3");
	By productTextBy = By.cssSelector("b");
	By addToCartButton = By.cssSelector(".card button:last-of-type");
	By toastPopup = By.id("toast-container");
	By loader = By.id(".ng-animating");

	public List<WebElement> getProductsList()
	{
		waitUntilAnElementToGetAppearedInThePage(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{

		WebElement requiredProduct = getProductsList().stream()
				.filter(product -> product.findElement(productTextBy).getText().equals(productName)).findFirst()
				.orElse(null);
		return requiredProduct;
	}
	
	public void addToCart(String productName)
	{
		getProductByName(productName).findElement(addToCartButton).click();

	}
	
	public void verifySuccessMessageOfProductAddedToCart(String Message)
	{
		waitUntilAnElementToGetAppearedInThePage(toastPopup);
		checkAssertTextEquals(toastPopupText,Message);
		waitUntilAnElementToGetDisappeared(loader);
	}
	
	
}
