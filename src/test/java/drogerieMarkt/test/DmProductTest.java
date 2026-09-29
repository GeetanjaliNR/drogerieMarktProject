package drogerieMarkt.test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import drogerieMarkt.base.DmBaseClass;

public class DmProductTest extends DmBaseClass{
	
	
	@Test(enabled=true)
	public void verifyProductsAreDisplayed() throws Exception {
		boolean productVisibile = productPage.areSearchedProductVisible("book");
		Assert.assertTrue(productVisibile);
	}

	@Test(enabled=true)
	public void verifyProductPageNaviagation() throws Exception {
		productPage.loadProductPage("book");
		Assert.assertTrue(productPage.isProductHeadingVisible());
	}
	
	@Test(enabled=true)
	public void verifyProductPriceIsDisplayed() {
		productPage.loadProductPage("book");
		Assert.assertTrue(productPage.isProductDisplayed());
	}
	
	@Test(enabled=true)
	public void verifyAddToCartButtonEnabled() {
		productPage.loadProductPage("book");
		Assert.assertTrue(productPage.isButtonEnabled());
	}
	
	@Test(enabled=true)
	public void verifyProductQuatityDropdown() {
		productPage.loadProductPage("book");
		Select quantitySelect = productPage.getQuantitySelectElement();
		List<WebElement> quantityOptions =  quantitySelect.getOptions();
		
		//validate dropdown has 10 options from 1 to 10
		Assert.assertEquals(quantityOptions.size(), 10);
		
		for(int i =0; i< quantityOptions.size(); i++){
			Assert.assertEquals(quantityOptions.get(i).getText(), String.valueOf(i+1));
		}	
	}
	
	@DataProvider(name="quantites")
	public Object[][] quantites(){
		return new Object[][] {
			{1},
			{2},
			{3},
			{4}
		};
	}
	
	@Test(enabled = true, dataProvider = "quantites")
	public void verifyQuantityChange(int quantity) {
		productPage.loadProductPage("Shampoo");
		Select quantitySelect = productPage.getQuantitySelectElement();
		quantitySelect.selectByIndex(quantity);
		
		Assert.assertEquals(quantitySelect.getFirstSelectedOption().getText(), String.valueOf(quantity+1));
	}
	
	
	
	
	

	
	

}
