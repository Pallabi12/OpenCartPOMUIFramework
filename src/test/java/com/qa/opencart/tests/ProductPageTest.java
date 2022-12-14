package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductPageTest extends BaseTest{

	@BeforeClass
	public void prodInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));		
	}
	
	
	@DataProvider
	public Object[][] getProductHeader(){
	Object searchData[][] = ExcelUtil.getTestData(AppConstants.PRODUCT_SEARCH_SHEET_NAME);
	return searchData;
	
	}
	
	@Test(dataProvider = "getProductHeader")
	public void productHeaderTest(String searchKey, String productName) {
		searchResultsPage = accPage.performSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName); 
		String actProductHeader = productInfoPage.getProductHeader(productName);
		Assert.assertEquals(actProductHeader, productName);
	}
	
	@DataProvider
	public Object[][] getProductInfoData(){
		return new Object[][] {
			{"Macbook", "MacBook Pro", AppConstants.MACBOOK_PRO_IMAGES_COUNT},
			{"Macbook", "MacBook Air", AppConstants.MACBOOK_AIR_IMAGES_COUNT},
			{"iMac", "iMac", AppConstants.IMAC_IMAGES_COUNT},
		};
	}
	
	@Test(dataProvider = "getProductInfoData")
	public void productImagesCountTest(String searchKey, String mainProductName, int ImagesCount) {
		searchResultsPage = accPage.performSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(mainProductName); 
		int actProductImages = productInfoPage.getProductImagesCount();
		System.out.println("Count of Actual product images : " + actProductImages);
		Assert.assertEquals(actProductImages, ImagesCount);
	}
	
	@Test
	public void productMetaDataTest() throws InterruptedException {
		searchResultsPage = accPage.performSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actMetaDataMap = productInfoPage.getProductMetaData();
		Assert.assertEquals(actMetaDataMap.get("Brand"), "Apple");
		Assert.assertEquals(actMetaDataMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actMetaDataMap.get("Reward Points"), "800");
		Assert.assertEquals(actMetaDataMap.get("Availability"), "In Stock");		
	}
	
	@Test
	public void getProductPriceMetaDataTest() {
		searchResultsPage = accPage.performSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actPriceMetaDataMap = productInfoPage.getProductPriceMetaData();
		Assert.assertEquals(actPriceMetaDataMap.get("$2,000.00"), " ");
		Assert.assertEquals(actPriceMetaDataMap.get("Ex Tax"), "$2,000.00");
	}
	
	
}
