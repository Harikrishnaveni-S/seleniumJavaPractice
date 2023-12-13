package frameworkDesign.tests;

import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import frameworkDesign.pageObjects.CartPage;
import frameworkDesign.pageObjects.CheckoutPage;
import frameworkDesign.pageObjects.GetProducts;
import frameworkDesign.pageObjects.OrderConfirmationPage;
import frameworkDesign.testComponents.BaseTest;

public class PlaceOrderTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void placeOrder(HashMap<String, String> testData) throws IOException {

		String successMessage = "Product Added To Cart";
		String countryName = "India";
		String confirmationMessage = "THANKYOU FOR THE ORDER.";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		landingPage.loginToTheApplication(testData.get("emailId"), testData.get("password"));
		GetProducts getProducts = landingPage.verifySuccessfulLogin(testData.get("loginSuccessMessage"));
		getProducts.addToCart(testData.get("productName"));
		getProducts.verifySuccessMessageOfProductAddedToCart(successMessage);
		CartPage cartPage = getProducts.goToCartPage();
		Boolean checkAddedProduct = cartPage.verifyProductAddedDisplayedInCartPage(testData.get("productName"));
		Assert.assertTrue(checkAddedProduct);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.searchCountry(countryName);
		checkoutPage.getMatchedCountry();
		OrderConfirmationPage orderConfirmationPage = checkoutPage.placeOrder();
		String orderConfirmationMessage = orderConfirmationPage.verifyOrderConfirmationMessage();
		Assert.assertEquals(orderConfirmationMessage, confirmationMessage);

	}



	@DataProvider
	public Object[][] getData() throws IOException

	{

		List<HashMap<String, String>> testData = getMapFromJsonData(
				System.getProperty("user.dir") + "//src//test//java//frameworkDesign//testData//purchaseOrder.json");

		return new Object[][] { { testData.get(0) }, { testData.get(1) } };

	}
}











/*
 * @Test(dependsOnMethods= {"placeOrder"},dataProvider= "getData") public void
 * orderDetails(String emailId , String password, String productName, String
 * loginSuccessMessage) { landingPage.loginToTheApplication(emailId,password);
 * GetProducts getProducts =
 * landingPage.verifySuccessfulLogin(loginSuccessMessage); OrderPage ordersPage
 * = getProducts.goToOrdersPage();
 * Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
 * 
 * }
 */












/*
 * HashMap<String,String> map= new HashMap<String,String>(); map.put("emailId",
 * "qatestprac@gmail.com"); map.put("password", "Qa@rahul0");
 * map.put("productName", "ZARA COAT 3"); map.put("loginSuccessMessage",
 * "Login Successfully");
 */

// return new Object [] [] {{map}};

// return new Object [] [] {{"qatestprac@gmail.com","Qa@rahul0","ZARA COAT
// 3","Login Successfully"},{"practest@gmail.com","Qa@Test0","ADIDAS
// ORIGINAL","Login Successfully"}};
