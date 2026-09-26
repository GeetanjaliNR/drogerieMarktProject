package drogerieMarkt.page;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DmProductPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//*[@id=\"input-search-composing-search-input-field\"]")
	WebElement searchBar;
	
	@FindBy(xpath = "//*[@id=\"product-tiles\"]")
	WebElement searchedProductResult;
	
	@FindBy(xpath = "//*[@id=\"product-tiles\"]/div[1]")
	WebElement firstSearchedProduct;
	
	@FindBy(xpath = "//h1[@data-dmid=\"detail-page-headline-product-title\"]")
	WebElement productHeading;
	
	@FindBy(xpath = "//span[@data-dmid=\"search-placeholder\"]")
	WebElement productPrice;
	
	@FindBy(id="add-to-cart-button")
	WebElement addToCartButton;
	
	@FindBy(id="dm-dropdown")
	WebElement quantityDropdown;
	
	public DmProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	public void searchProduct(String productName) {
		searchBar.clear();
		searchBar.sendKeys(productName, Keys.ENTER);
	}
	
	public void getProductsLoaded() {
		wait.until(ExpectedConditions.visibilityOf(searchedProductResult));
	}
	
	public boolean areSearchedProductVisible(String searchProductName){
		searchProduct(searchProductName);
		wait.until(ExpectedConditions.visibilityOf(searchedProductResult));
		return searchedProductResult.isDisplayed();
	}
	
	public WebElement getFirstSearchedProduct() {
		wait.until(ExpectedConditions.visibilityOf(firstSearchedProduct));
		return firstSearchedProduct;
	}

	public void goToProductPage() {
		wait.until(ExpectedConditions.visibilityOf(searchedProductResult));
		firstSearchedProduct.click();
	}
	
	public void loadProductPage(String product) {
		searchProduct(product);
		goToProductPage();
	}
	
	
	public boolean isProductHeadingVisible() {
		wait.until(ExpectedConditions.visibilityOf(productHeading));
		return productHeading.isDisplayed();
	}

	public boolean isProductDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(productPrice));
		return productPrice.isDisplayed();
		
	}
	
	public boolean isButtonEnabled() {
		wait.until(ExpectedConditions.visibilityOf(addToCartButton));
		return addToCartButton.isEnabled();
	}

	public Select getQuantitySelectElement() {
	
		wait.until(ExpectedConditions.elementToBeClickable(quantityDropdown));
		Select quantitySelect = new Select(quantityDropdown);
		return quantitySelect;
	}


}
