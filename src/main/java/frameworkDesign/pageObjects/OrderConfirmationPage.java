package frameworkDesign.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import frameworkDesign.abstractComponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent{

	
	WebDriver driver;
	public OrderConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	By confirmation = By.cssSelector("[class=\"hero-primary\"]");
	
	public String verifyOrderConfirmationMessage()
	{

		waitUntilAnElementToGetAppearedInThePage(confirmation);
		String orderConfirmationMessage = driver.findElement(confirmation).getText();
		return orderConfirmationMessage;
	}
}
