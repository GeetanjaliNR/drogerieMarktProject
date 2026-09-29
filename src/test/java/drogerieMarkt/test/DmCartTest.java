package drogerieMarkt.test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import drogerieMarkt.base.DmBaseClass;

public class DmCartTest extends DmBaseClass {
	
	@Test(enabled=true)
	public void verifyCartShowsProductCount(){
		productPage.searchProduct("vitamin D");
		WebElement productOne = productPage.getFirstSearchedProduct();
		cartPage.addProductToCart(productOne, 1);
		
		productPage.searchProduct("book");
		WebElement productTwo = productPage.getFirstSearchedProduct();
		cartPage.addProductToCart(productTwo, 2);
		
		productPage.searchProduct("perfume");
		WebElement productThree = productPage.getFirstSearchedProduct();
		cartPage.addProductToCart(productThree, 3);

		String cartSummary = cartPage.getCartSummaryItems();
		Assert.assertEquals(cartSummary, String.valueOf(3));
	}
	
	@Test(enabled=true)
	public void verifyCartPageNavigation() {
		//verify that user is navigated to cart page when cart button is pressed
		cartPage.clickCartButton();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.dm.de/cart");
	}
	
	@Test(enabled=true)
	public void verifyEmptyCartMessage() {
		//verify cart message when no product are added to the cart
		cartPage.clickCartButton();
		String emptyCartMessage = cartPage.getEmptyCartMessage();
		System.out.println(emptyCartMessage);
		Assert.assertTrue(emptyCartMessage.contains("keine Artikel im Warenkorb"));
	}
	
	@Test(enabled=true)
	public void verifyCartElementsAreDisplayed() {
		
		productPage.searchProduct("shampoo");
		WebElement product = productPage.getFirstSearchedProduct();
		cartPage.addProductToCart(product, 1);
		
		productPage.searchProduct("book");
		WebElement productTwo = productPage.getFirstSearchedProduct();
		cartPage.addProductToCart(productTwo, 2);
		
		cartPage.clickCartButton();
		
		boolean isTotalPriceDisplayed = cartPage.isTotolPriceDisplayed();
		boolean isAvailabilitySummaryDisplayed = cartPage.isAvailabilitySummaryDisplayed();
		boolean isCartCheckoutButtonEnabled = cartPage.isCartCheckoutButtonEnabled();
		
		Assert.assertTrue(isTotalPriceDisplayed);
		Assert.assertTrue(isAvailabilitySummaryDisplayed);
		Assert.assertTrue(isCartCheckoutButtonEnabled);
	}
	
	@Test(enabled=true)
	public void verifyCartProductMatchesCartSummary() {
		productPage.searchProduct("shampoo");
		WebElement product = productPage.getFirstSearchedProduct();
		cartPage.addProductToCart(product, 1);
		
		productPage.searchProduct("book");
		WebElement productTwo = productPage.getFirstSearchedProduct();
		cartPage.addProductToCart(productTwo, 2);
		
		String cartSummary = cartPage.getCartSummaryItems();
		
		cartPage.clickCartButton();
		
		List<WebElement> productsInCart =  cartPage.getListOfItemsInCart();
		
		Assert.assertEquals(cartSummary, String.valueOf(productsInCart.size()));
		
	}
	
}
