package drogerieMarkt.page;

import java.net.HttpURLConnection;
import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DmHomePage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(tagName="a")
	List<WebElement> allAnchorElements;
	
	@FindBy(xpath = "/html/body/div[1]/div/header/div[4]/div/nav/ul/li/a")
	List<WebElement> allKategories;
	
	@FindBy(xpath = "//*[@id=\"input-search-composing-search-input-field\"]")
	WebElement searchBar;
	
	@FindBy(xpath = "//*[@id=\"product-tiles\"]/div[1]/div[2]/div[2]/a")
	WebElement searchedProductResult;
	
	
	
	public DmHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}

	public List<String> validateCategoryLinks() {
		wait.until(ExpectedConditions.visibilityOfAllElements(allKategories));
		
		List<String> brokenLinks = new ArrayList<>();
		
		for(WebElement a : allKategories) {

			String link = a.getAttribute("href");
			int responseCode = verifyLinks(link);
			
			if(responseCode < 200 || responseCode >= 400) {
				brokenLinks.add(link);
			}
		}
		return brokenLinks;
		
	}

	private int verifyLinks(String link) {
		
		try {
			 URI linkObject= new URI(link);
			 HttpURLConnection connection = (HttpURLConnection)linkObject.toURL().openConnection();
			 
			 return connection.getResponseCode();
		}catch(Exception e){
			return -1;
		}
		
	}

	public String searchProduct(String searchProductName) throws InterruptedException {
		searchBar.sendKeys(searchProductName, Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(searchedProductResult));
		return searchedProductResult.getText();
	}
	
	public void acceptCookies() throws Exception {
		
		  WebElement usercentricsRoot = wait.until(
			        ExpectedConditions.presenceOfElementLocated(
			            By.id("usercentrics-root")
			        )
			    );
		  
		SearchContext shadowRoot = usercentricsRoot.getShadowRoot();

		    WebElement acceptBtn = wait.until(driver -> {
		        try {
		            return shadowRoot.findElement(
		                By.cssSelector("[data-testid='uc-accept-all-button']")
		            );
		        } catch (NoSuchElementException e) {
		            return null;
		        }
		    });

		    wait.until(driver ->
		        acceptBtn.isDisplayed() && acceptBtn.isEnabled()
		    );

		    acceptBtn.click();
		}
	
}
