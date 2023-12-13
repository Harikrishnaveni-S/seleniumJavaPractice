@tag
Feature: Purchase the order from Ecommerce Web

	Background:
	Given I landed on the ecom page
	
	@tag2
	Scenario Outline: Positive Test of submitting the order
	
		Given Logged in with username <username> and password <password> with <loginSuccessMessage>
		When I add the product <productName> to the cart with message <successMessage>
		And checkout the product <productName> and submit the order for country <countryName>
		Then "THANKYOU FOR THE ORDER." message will be displayed on confirmation page
		
		Examples:
		
		|username               | password     | loginSuccessMessage  | productName   | successMessage          | countryName |
		| qatestprac@gmail.com  | Qa@rahul0    | Login Successfully   |  ZARA COAT 3  | Product Added To Cart   | India       |
	