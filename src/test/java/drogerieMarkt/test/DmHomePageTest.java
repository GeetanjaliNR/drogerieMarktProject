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
		//verify home page title is as expected
		String actualTitle = homePage.getPageTitle();
		
		String expectedTitle = "Bei dm-drogerie markt online einkaufen | dm";
		
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(enabled = true)
	public void homePageLinkValidation() {
		//verify home page main menu links are functional 
		List<String> brokenLinksList = homePage.validateCategoryLinks();
		Assert.assertTrue(brokenLinksList.isEmpty(),  "Broken links found: " + brokenLinksList);
	}
	
	
	@Test(enabled = true)
	public void verifyPresenceOfLogo() {
		//verify company logo is present
		Assert.assertTrue(homePage.isLogoDisplayed());
	}
	
	
	@Test(enabled = true)
	public void verifyIfCarouselIsDisplayed() {
		//verify image Carousel is displayed
		List<WebElement> carouselList = homePage.getCarouselImages();
		for (WebElement carousel : carouselList) {
			Assert.assertTrue(carousel.isDisplayed());
		}
		
	}
	
	@Test(enabled = true)
	public void verifyCarouselNextButton() throws Exception {
		//verify carousel image changes when next button is pressed
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
		//verify products can be searched using search bar
		String productText = homePage.searchProduct("Shampoo");
		System.out.println(productText);
		Assert.assertTrue(productText.contains("Produkte"));
	}
	
}
