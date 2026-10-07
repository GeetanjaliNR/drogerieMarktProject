package drogerieMarkt.page;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DmSearchPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//div[@title='Nach Kategorien filtern']")
	WebElement categoryFilter;
	
	@FindBy(xpath = "//div[@title='Nach Preis filtern']")
	WebElement preisFilter;
	
	@FindBy(xpath = "//input[@aria-label='Gib einen Wert für den Mindestpreis ein.']")
	WebElement minPrice;
	
	@FindBy(xpath = "//form[@data-dmid=\"container-filter-Preis\"]/div/button")
	WebElement showProductByPrice;
	
	@FindBy(xpath = "//input[@aria-label='Gib einen Wert für den Maximalpreis ein.']")
	WebElement maxPrice;
	
	@FindBy(xpath = "//div[@data-dmid='dropdown-with-layer-Kategorien-layer']//div[@title='Shampoo']")
	WebElement shampooCategory;
	
	@FindBy(xpath = "//div[@title='Produkte zeigen']/button")
	WebElement showProductButton;
	
	@FindBy(xpath = "//div[@data-dmid='dropdown-with-layer-Kategorien-layer']//div[@title='Shampoo']//span[2]")
	WebElement filteredProductCount;
	
	@FindBy(xpath = "//span[@data-dmid='total-count']")
	WebElement totalPoductCount;
	
	@FindBy(xpath = "//select[@id=\"sort-control-select\"]")
	WebElement sortBySelect;
	
	@FindBy(xpath="//div[@id=\"product-tiles\"]//div[@data-dmid=\"product-tile-container\"]//span[@data-dmid=\"price-localized\"]")
	List<WebElement> priceList;
	
	public DmSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	public String setCategoryFilterAndGetProductCount() {
		wait.until(ExpectedConditions.elementToBeClickable(categoryFilter)).click();
		wait.until(ExpectedConditions.visibilityOf(shampooCategory)).click();
		String productCount = filteredProductCount.getText();
		showProductButton.click();
		return productCount;
	}
	
	public String getTotalProductCount() {
		String totalProductText = wait.until(ExpectedConditions.visibilityOf(totalPoductCount)).getText();
		return totalProductText.replaceAll("[^0-9]", "");
	}

	public Select getSortBySelect() {
		wait.until(ExpectedConditions.visibilityOf(sortBySelect));
		Select sortBy = new Select(sortBySelect);
		return sortBy;		
	}

	public List<Double> getProductPriceList() {
		wait.until(ExpectedConditions.visibilityOfAllElements(priceList));
		List<Double> prices = new ArrayList<Double>();
		for(WebElement price :priceList ) {
			String priceText = price.getText().replace("€", "").trim().replace(".","").replace(",", ".");
			prices.add(Double.parseDouble(priceText));
		}
		return prices;
	}

	
	public void setPriceFilter(String startPrice, String endPrice) {
		wait.until(ExpectedConditions.elementToBeClickable(preisFilter)).click();
		
		wait.until(ExpectedConditions.visibilityOf(minPrice));
		((JavascriptExecutor) driver).executeScript(
		        "arguments[0].focus();", minPrice
		    );
		minPrice.sendKeys(Keys.COMMAND, "a");
		minPrice.sendKeys(startPrice);
		
		wait.until(ExpectedConditions.visibilityOf(maxPrice));
		((JavascriptExecutor) driver).executeScript(
		        "arguments[0].focus();", maxPrice
		    );
		maxPrice.sendKeys(Keys.COMMAND, "a");
		maxPrice.sendKeys(endPrice);
		wait.until(ExpectedConditions.elementToBeClickable(showProductByPrice)).click();	
	}
}

