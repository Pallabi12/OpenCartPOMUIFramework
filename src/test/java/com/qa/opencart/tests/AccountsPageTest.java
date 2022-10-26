package com.qa.opencart.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(priority = 1)
	public void accPageTitleTest() {
		String actAccPageTitle 	 = accPage.getAccountPageTitle();
		Assert.assertEquals(actAccPageTitle, AppConstants.ACC_PAGE_TITLE);
	}

	@Test(priority = 2	)
	public void accPageUrlTest() {
		Assert.assertTrue(accPage.getAccountPageUrl());
	}
	
	
	@Test(priority = 3)
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	
	@Test(priority = 4)
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test(priority = 5)
	public void accPageHeadersTest() {
		ArrayList<String> actHeaderList = accPage.getAccSecHeaderList();
		System.out.println("Actual Acc Page header is : " + actHeaderList);
		Assert.assertEquals(actHeaderList, AppConstants.ACC_PAGE_SECTIONS_HEADERS);
	}
	
	@DataProvider
	public Object[][] getProductKey(){
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"}
		};
	}
	
	@Test(priority = 6, dataProvider = "getProductKey")
	public void searchCheckTest(String productKey) {
		searchResultsPage = accPage.performSearch(productKey);
		Assert.assertTrue(searchResultsPage.isSearchSuccessful());
	}
	
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"Macbook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung", "Samsung Galaxy Tab 10.1"},
		};
	}
	
	
	@Test(priority = 7, dataProvider = "getProductData")
	public void searchTest(String searchKey, String mainProductName) {
		searchResultsPage = accPage.performSearch(searchKey);
		if(searchResultsPage.isSearchSuccessful()) {
			productInfoPage = searchResultsPage.selectProduct(mainProductName);
			String actualProductHeader = productInfoPage.getProductHeader(mainProductName);
			Assert.assertEquals(actualProductHeader, mainProductName);
		}
	}
	
}
