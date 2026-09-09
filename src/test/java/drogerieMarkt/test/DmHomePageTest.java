package drogerieMarkt.test;

import org.testng.annotations.Test;

import java.util.List;

import org.testng.Assert;


import drogerieMarkt.base.DmBaseClass;


public class DmHomePageTest extends DmBaseClass{
	
	@Test(enabled=true)
	public void verifyHomePageTitle()throws Exception {
		String actualTitle = homePage.getPageTitle();
		homePage.acceptCookies();
		String expectedTitle = "Bei dm-drogerie markt online einkaufen | dm";
		
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(enabled=true)
	public void homePageLinkValidation() {
		List<String> brokenLinksList = homePage.validateCategoryLinks();
		Assert.assertTrue(brokenLinksList.isEmpty(),  "Broken links found: " + brokenLinksList);
	}
	
	@Test(enabled=true)
	public void verifyProductSearch() throws Exception {
		String productText = homePage.searchProduct("Shampoo");
		System.out.println(productText);
		Assert.assertTrue(productText.contains("Shampoo"));
	}
	
	@Test
	public void verifyPresenceOfLogo() {
		
	}
	
}
