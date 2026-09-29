package drogerieMarkt.test;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import drogerieMarkt.base.DmBaseClass;


public class DmHomePageTest extends DmBaseClass{
	
	@Test(enabled = true)
	public void verifyHomePageTitle() {
		String actualTitle = homePage.getPageTitle();
		
		String expectedTitle = "Bei dm-drogerie markt online einkaufen | dm";
		
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(enabled = true)
	public void homePageLinkValidation() {
		List<String> brokenLinksList = homePage.validateCategoryLinks();
		Assert.assertTrue(brokenLinksList.isEmpty(),  "Broken links found: " + brokenLinksList);
	}
	
	
	@Test(enabled = true)
	public void verifyPresenceOfLogo() {
	Assert.assertTrue(homePage.isLogoDisplayed());
	}
	
	
	@Test(enabled = true)
	public void verifyIfCaroselIsDisplayed() {
		List<WebElement> caroselList = homePage.getCaroselImages();
		for (WebElement carosel : caroselList) {
			Assert.assertTrue(carosel.isDisplayed());
		}
		
	}
	
	@Test(enabled = true)
	public void verifyCarouselNextButton() throws Exception {
		long positionBefore = homePage.getCarouselScrollPosition();
		System.out.println("positionBefore = " + positionBefore);
		
		homePage.clickNextButton();
		Thread.sleep(2000);
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
	
	@Test(enabled = true)
	public void verifyProductSearch() throws Exception {
		String productText = homePage.searchProduct("Shampoo");
		System.out.println(productText);
		Assert.assertTrue(productText.contains("Produkte"));
	}
	
}
