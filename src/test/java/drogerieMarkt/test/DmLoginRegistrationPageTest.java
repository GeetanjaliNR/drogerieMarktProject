package drogerieMarkt.test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import drogerieMarkt.base.DmBaseClass;

public class DmLoginRegistrationPageTest extends DmBaseClass {

	
	@Test(enabled=true, priority=7)
	public void verifyLoginRedirection() throws Exception {
		loginRegistrationPage.clickAccountButton();
		loginRegistrationPage.clickLoginButton();
		String currentURL =loginRegistrationPage.getCurrentPageURL();
		System.out.println("currentURL" +currentURL);
		Assert.assertTrue(currentURL.contains("web-login"));
	}
	
	@Test(enabled=true, priority=8)
	public void verifyRegistrationRedirection() throws Exception {
		loginRegistrationPage.clickAccountButton();
		loginRegistrationPage.clickRegistrationButton();
		String currentURL =loginRegistrationPage.getCurrentPageURL();
		System.out.println("currentURL" +currentURL);
		Assert.assertTrue(currentURL.contains("registration"));
	}
	
	@Test(enabled=true, priority=8)
	public void verifyRegistrationFormRadioButtons() throws Exception {
		loginRegistrationPage.getRegistrationPage();
		
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
	
	@Test(enabled=false, priority=9)
	public void VerifyfirstName() throws Exception {
		loginRegistrationPage.getRegistrationPage();
		
		loginRegistrationPage.setFirstNameValue("");
		Assert.assertTrue(loginRegistrationPage.isFirstNameErrorMsgDisplayed());
	}
	
	@Test(enabled=false, priority=10)
	public void dateOfBirthValidation() throws Exception {
		//ensure appropriate error message is shown 
		loginRegistrationPage.getRegistrationPage();
		
		loginRegistrationPage.setDOBValues("01","01","1905");
		
		Assert.assertTrue(loginRegistrationPage.isErrorMessageDisplayed());
	}
}
