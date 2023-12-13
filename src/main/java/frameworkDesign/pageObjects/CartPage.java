package frameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkDesign.abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[class=\"cartSection\"] h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath = "//button[text()=\"Checkout\"]")
	WebElement checkoutButton;
	
	By cartItems = By.cssSelector("[class=\"cartSection\"] h3");


	public List<WebElement> getCartProducts() {
		waitUntilVisibilityOfAllElements(cartItems);
		return cartProducts;
	}

	public boolean verifyProductAddedDisplayedInCartPage(String productName) {
		boolean checkAddedProduct = getCartProducts().stream()
				.anyMatch(product -> product.getText().equals(productName));
		return checkAddedProduct;

	}
	
	public CheckoutPage goToCheckoutPage()
	{
		checkoutButton.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;

	}

}
