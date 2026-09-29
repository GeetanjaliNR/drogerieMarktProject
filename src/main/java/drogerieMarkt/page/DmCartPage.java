package drogerieMarkt.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DmCartPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//div[@data-dmid=\'cart-link']")
	WebElement cartButton;
	
	@FindBy(xpath = "//span[@data-dmid='cart-summary-items']")
	WebElement cartSummary;
	
	@FindBy(xpath = "//h3[@data-dmid='dm-heading']")
	WebElement emptyCartMessage;
	
	@FindBy(xpath = "//span[@data-dmid='cart-total-price']")
	WebElement totalPrice;
	
	@FindBy(xpath = "//div[@data-dmid='availability-summary-banner']")
	WebElement availabilitySummary;
	
	@FindBy(xpath = "//button[@data-dmid='cart-checkoutnext-button']")
	WebElement cartCheckoutButton;
	
	@FindBy(xpath = "//div[@data-dmid='cart-entries-container']//li")
	List<WebElement> cartProductList;
	
	public DmCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));		
	}

	public void addProductToCart(WebElement product, int expectedCount) {
		WebElement addToCartQuickButton = product.findElement(By.id("add-direct-to-cart-button"));
		wait.until(ExpectedConditions.elementToBeClickable(addToCartQuickButton));
		addToCartQuickButton.click();
		
		wait.until(ExpectedConditions.textToBePresentInElement(cartSummary, String.valueOf(expectedCount)));
	}

	public String getCartSummaryItems() {
//		wait.until(ExpectedConditions.visibilityOf(cartSummary));
		return cartSummary.getText();
		
	}

	public void clickCartButton() {
		cartButton.click();
		wait.until(ExpectedConditions.urlContains("/cart"));	
	}

	public String getEmptyCartMessage() {
		wait.until(ExpectedConditions.visibilityOf(emptyCartMessage));
		return emptyCartMessage.getText();
	}

	public boolean isTotolPriceDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(totalPrice));
		
		return totalPrice.isDisplayed();
	}

	public boolean isAvailabilitySummaryDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(availabilitySummary));
		return availabilitySummary.isDisplayed();
	}

	public boolean isCartCheckoutButtonEnabled() {
		wait.until(ExpectedConditions.visibilityOf(cartCheckoutButton));
		return cartCheckoutButton.isDisplayed();
		
	}

	public List<WebElement> getListOfItemsInCart() {
		wait.until(ExpectedConditions.visibilityOfAllElements(cartProductList));
		return cartProductList;
	}	

}
