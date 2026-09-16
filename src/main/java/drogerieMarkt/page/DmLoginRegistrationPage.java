package drogerieMarkt.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DmLoginRegistrationPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//*[@id=\"dm-view\"]/div/div/nav/ul/li[3]/div/button")
	WebElement accountButton;
	
	@FindBy(xpath="//*[@id=\"login-button\"]")
	WebElement loginButton;
	
	@FindBy(xpath="//button[@data-dmid='account-widget-button' and @aria-label='Mein Konto']")
	WebElement myAccountButton;
	
	@FindBy(id="registration-button")
	WebElement registrationButton;
	
	@FindBy(xpath = "//input[@type=\"radio\"]")
	List<WebElement> genderRadioButtons;
	
	@FindBy(id = "birthDate-date-input-year")
	WebElement DOBYear;
	
	@FindBy(id="birthDate-date-input-month")
	WebElement DOBMonth;
	
	@FindBy(id="birthDate-date-input-day")
	WebElement DOBDay;
	
	@FindBy(id="birthDate-error")
	WebElement errorMessage;
	
	public DmLoginRegistrationPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); 
		wait= new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	
	public void clickLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
	}
	
	
	public void clickAccountButton() {
		accountButton.click();	
	}
	

	public String getCurrentPageURL() {
		wait.until(ExpectedConditions.urlContains("web-login"));
		return driver.getCurrentUrl();
	}

	public void clickRegistrationButton() {
		wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
		registrationButton.click();
		
	}

	public List<WebElement> getRadioButtons() {
		wait.until(ExpectedConditions.visibilityOfAllElements(genderRadioButtons));
		return genderRadioButtons;
		
	}


	public void setDOBValues(String day, String month, String year) {
		DOBDay.sendKeys(day);
		DOBDay.sendKeys(Keys.ENTER);
		DOBMonth.sendKeys(month);
		DOBMonth.sendKeys(Keys.ENTER);
		DOBYear.sendKeys(year);
		DOBYear.sendKeys(Keys.ENTER);
	}
	
	public boolean isErrorMessageDisplayed() {
		wait.until(ExpectedConditions.visibilityOfAllElements(errorMessage));
		return errorMessage.isDisplayed();
	}
	

}
