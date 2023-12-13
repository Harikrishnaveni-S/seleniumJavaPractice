package frameworkDesign.stepDefinitions;

import frameworkDesign.testComponents.BaseTest;

import java.io.IOException;

import org.testng.Assert;

import frameworkDesign.pageObjects.CartPage;
import frameworkDesign.pageObjects.CheckoutPage;
import frameworkDesign.pageObjects.GetProducts;
import frameworkDesign.pageObjects.LandingPage;
import frameworkDesign.pageObjects.OrderConfirmationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefImp extends BaseTest {
	
	public LandingPage landingPage;
	public 	GetProducts getProducts;
	public CartPage cartPage;
	public 	CheckoutPage checkoutPage ;
	
	@Given("^I landed on the ecom page$")
	public void land_on_ecom_page() throws IOException
	{
		 landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+) with (.+)$")
	public void login_To_Web(String username, String password, String loginSuccessMessage)
	
	{
		landingPage.loginToTheApplication(username, password);
		getProducts = landingPage.verifySuccessfulLogin(loginSuccessMessage);

	}
	
	
	@When("^I add the product (.+) to the cart with message (.+)$")
	public void add_To_cart(String productName, String successMessage)
	{
		getProducts.addToCart(productName);
		getProducts.verifySuccessMessageOfProductAddedToCart(successMessage);
	}
	
	@And("^checkout the product (.+) and submit the order for country (.+)$")
	public void placeOrder(String productName, String countryName)
	{
		cartPage = getProducts.goToCartPage();
		Boolean checkAddedProduct = cartPage.verifyProductAddedDisplayedInCartPage(productName);
		Assert.assertTrue(checkAddedProduct);
		checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.searchCountry(countryName);
		checkoutPage.getMatchedCountry();
	}
	
	@Then("{string} message will be displayed on confirmation page")
	public void verifyConfMessage(String string)
	{
		OrderConfirmationPage orderConfirmationPage = checkoutPage.placeOrder();
		String orderConfirmationMessage = orderConfirmationPage.verifyOrderConfirmationMessage();
		Assert.assertEquals(orderConfirmationMessage, string);
		driver.quit();
	}
}
