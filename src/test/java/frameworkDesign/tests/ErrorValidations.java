package frameworkDesign.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import frameworkDesign.testComponents.BaseTest;
import frameworkDesign.testComponents.RetryTest;

public class ErrorValidations extends BaseTest {
	
	@Test(retryAnalyzer=RetryTest.class)
	public void LoginErrorValidation () throws IOException {
		
		String emailId = "qatestprac@gmail.com";
		String password = "Qa@rahu";
		String expMesssage = "Incorrect email or password.";
		
		
		landingPage.loginToTheApplication(emailId,password);
		String errMessage = landingPage.getErrorMessage();
		Assert.assertEquals(errMessage, expMesssage);
	}
}
