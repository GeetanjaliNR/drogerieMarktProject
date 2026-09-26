package drogerieMarkt.test;

import java.util.List;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import drogerieMarkt.base.DmBaseClass;

public class DmSearchTest extends DmBaseClass {

	
	@Test(enabled=false)
	public void verifyIfCategoryFilterIsApplied() {
		productPage.searchProduct("Shampoo");
		productPage.getProductsLoaded();
		String filteredProductCount = searchPage.setCategoryFilterAndGetProductCount();
		String totalProductCount= searchPage.getTotalProductCount();
		
		Assert.assertEquals(totalProductCount, filteredProductCount);		
	}

	@Test(enabled=false)
	public void verifySortByAscending() {
		productPage.searchProduct("Shampoo");
		productPage.getProductsLoaded();
		Select sortBy =  searchPage.getSortBySelect();
		sortBy.selectByValue("price_asc");
		List<Double> productPriceListAsc = searchPage.getProductPriceList();
		
		for(int i =0; i<productPriceListAsc.size()-1; i++) {
			Assert.assertTrue(productPriceListAsc.get(i) <= productPriceListAsc.get(i + 1));
		}
	}
	
	
	@Test(enabled=false)
	public void verifySortByDescending() {
		productPage.searchProduct("Shampoo");
		productPage.getProductsLoaded();
		Select sortBy =  searchPage.getSortBySelect();
		sortBy.selectByValue("price_desc");
		List<Double> productPriceListDesc = searchPage.getProductPriceList();
		
		for(int i =0; i<productPriceListDesc.size()-1; i++) {
			Assert.assertTrue(productPriceListDesc.get(i) >= productPriceListDesc.get(i + 1));
		}
	}

	@Test(enabled=true)
	public void verifyPriceFilterFunctionality(){
		productPage.searchProduct("Shampoo");
		productPage.getProductsLoaded();
		searchPage.setPriceFilter("12", "15");
		List<Double> productPrice = searchPage.getProductPriceList(); 
		for(int i =0; i<productPrice.size(); i++) {
			Assert.assertTrue(productPrice.get(i) >= 12 && productPrice.get(i) <= 15);
		}
	}
	
}
