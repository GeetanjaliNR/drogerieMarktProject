package drogerieMarkt.test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import drogerieMarkt.base.DmBaseClass;

public class DmLoginRegistrationPageTest extends DmBaseClass {

	
	@Test(enabled=false, priority=7)
	public void verifyLoginRedirection() throws Exception {
		homePage.acceptCookies();
		loginRegistrationPage.clickAccountButton();
		loginRegistrationPage.clickLoginButton();
		String currentURL =loginRegistrationPage.getCurrentPageURL();
		System.out.println("currentURL" +currentURL);
		Assert.assertTrue(currentURL.contains("web-login"));
	}
	
	@Test(enabled=false, priority=8)
	public void verifyRegistrationRedirection() throws Exception {
		homePage.acceptCookies();
		loginRegistrationPage.clickAccountButton();
		loginRegistrationPage.clickRegistrationButton();
		String currentURL =loginRegistrationPage.getCurrentPageURL();
		System.out.println("currentURL" +currentURL);
		Assert.assertTrue(currentURL.contains("registration"));
	}
	
	@Test(enabled=false, priority=8)
	public void verifyRegistrationFormRadioButtons() throws Exception {
		homePage.acceptCookies();
		loginRegistrationPage.clickAccountButton();
		loginRegistrationPage.clickRegistrationButton();
		
		List<WebElement> genderRadioButton = loginRegistrationPage.getRadioButtons();
		
		//verify only female radio button is clicked
		genderRadioButton.get(0).click();
		
		Assert.assertTrue(genderRadioButton.get(0).isSelected());
		Assert.assertFalse(genderRadioButton.get(1).isSelected());
		Assert.assertFalse(genderRadioButton.get(2).isSelected());
		
		//verify only male radio button is clicked
		genderRadioButton.get(1).click();
		
		Assert.assertFalse(genderRadioButton.get(0).isSelected());
		Assert.assertTrue(genderRadioButton.get(1).isSelected());
		Assert.assertFalse(genderRadioButton.get(2).isSelected());
		
		//verify only diverse radio button is clicked
		genderRadioButton.get(2).click();
		
		Assert.assertFalse(genderRadioButton.get(0).isSelected());
		Assert.assertFalse(genderRadioButton.get(1).isSelected());
		Assert.assertTrue(genderRadioButton.get(2).isSelected());
			
	}
	
	@Test(enabled=true, priority=9)
	public void dateOfBirthValidation() throws Exception {
		//ensure appropriate message is shown when year value is less than 1906 
		homePage.acceptCookies();
		loginRegistrationPage.clickAccountButton();
		loginRegistrationPage.clickRegistrationButton();
		
		loginRegistrationPage.setDOBValues("01","01","1905");
		
		Assert.assertTrue(loginRegistrationPage.isErrorMessageDisplayed());
		
	}
	
}
