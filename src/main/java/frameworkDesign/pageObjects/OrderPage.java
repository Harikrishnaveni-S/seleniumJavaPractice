package frameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkDesign.abstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalrow button")
	WebElement orderButton;

	@FindBy(css = "tr[class=\"ng-star-inserted\"] td:nth-child(3)")
	List<WebElement> productNames;

	public boolean verifyOrderDisplay(String productName) {
		boolean matchedOrder = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return matchedOrder;
	}

}
