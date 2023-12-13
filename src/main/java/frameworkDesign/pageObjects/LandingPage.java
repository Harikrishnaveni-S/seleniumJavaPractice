package frameworkDesign.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkDesign.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void loadApplicationUrl()
	{
		driver.get("https://rahulshettyacademy.com/client");

	}
	
	//Page factory
	@FindBy(id="userEmail")
	WebElement emailInputField;
	
	@FindBy(id="userPassword")
	WebElement passwordInputField;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[id=\"toast-container\"] div div")
	WebElement toastText;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	By toastPopup = By.id("toast-container");
	
	
	
	public void loginToTheApplication(String emailIdValue, String passwordValue)
	{
		emailInputField.sendKeys(emailIdValue);
		passwordInputField.sendKeys(passwordValue);
		loginButton.click();
		
	}
	
	public GetProducts verifySuccessfulLogin(String Message)
	{
		waitUntilAnElementToGetAppearedInThePage(toastPopup);
		checkAssertTextEquals(toastText,Message);
		waitUntilAnElementToGetDisappeared(toastPopup);
		GetProducts getProducts =  new GetProducts(driver);
		return getProducts;

	}
	
	public String getErrorMessage()
	{
		waitUntilAnWebElementToGetAppearedInThePage(errorMessage);
		return errorMessage.getText();
	}

}
