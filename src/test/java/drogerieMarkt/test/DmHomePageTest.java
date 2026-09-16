package drogerieMarkt.test;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import drogerieMarkt.base.DmBaseClass;


public class DmHomePageTest extends DmBaseClass{
	
	@Test(enabled=true, priority=1)
	public void verifyHomePageTitle()throws Exception {
		String actualTitle = homePage.getPageTitle();
		homePage.acceptCookies();
		
		String expectedTitle = "Bei dm-drogerie markt online einkaufen | dm";
		
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(enabled=true, priority=2)
	public void homePageLinkValidation() {
		List<String> brokenLinksList = homePage.validateCategoryLinks();
		Assert.assertTrue(brokenLinksList.isEmpty(),  "Broken links found: " + brokenLinksList);
	}
	
	
	@Test(enabled=true, priority=3)
	public void verifyPresenceOfLogo() {
	Assert.assertTrue(homePage.isLogoDisplayed());
	}
	
	
	@Test(enabled=true, priority=4)
	public void verifyIfCaroselIsDisplayed() {
		List<WebElement> caroselList = homePage.getCaroselImages();
		for (WebElement carosel : caroselList) {
			Assert.assertTrue(carosel.isDisplayed());
		}
		
	}
	
	@Test(enabled=true, priority=5)
	public void verifyCarouselNextButton() {
		long positionBefore = homePage.getCarouselScrollPosition();
		
		homePage.clickNextButton();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		  wait.until(driver ->
	        homePage.getCarouselScrollPosition() != positionBefore
	    );
		  
		  long positionAfter = homePage.getCarouselScrollPosition();
		  
		  System.out.println("Before: " + positionBefore);
		  System.out.println("After: " + positionAfter);
		  
		  Assert.assertNotEquals(
			        positionAfter,
			        positionBefore,
			        "Carousel did not move after clicking Next"
			    );
		
	}
	
	@Test(enabled=true, priority=6)
	public void verifyProductSearch() throws Exception {
		String productText = homePage.searchProduct("Shampoo");
		System.out.println(productText);
		Assert.assertTrue(productText.contains("Produkte"));
	}
	
}
