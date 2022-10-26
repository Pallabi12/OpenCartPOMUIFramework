package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productInfoMap;
	
	private By productImage = By.cssSelector("a.thumbnail img");
	private By productMetaData = By.xpath("(//div[@id = 'content']//ul[@class = 'list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id = 'content']//ul[@class = 'list-unstyled'])[position()=2]/li");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeader(String mainProductName) {
		String xpath = "//h1[text()='"+mainProductName+"']";
		String productHeader = eleUtil.doGetText(By.xpath(xpath));
		System.out.println("Product Header is : "+ productHeader);
		return productHeader; 	
	}
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsToBeVisible(productImage, AppConstants.DEFAULT_LARGE_TIME_OUT).size();	
	}

	public String getProductPageTitle(String productTitle) {
		return eleUtil.waitForTitleIs(AppConstants.DEFAULT_LARGE_TIME_OUT, productTitle);
	}

	public String getProductPageUrl(String searchKey) {
		return eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, searchKey);
	}
	
	public Map<String, String> getProductMetaData() {
		List<WebElement> metalist = eleUtil.getElements(productMetaData);
		productInfoMap = new LinkedHashMap<String, String>();
		for(WebElement e : metalist) {
			String metaText = e.getText();
			String meta[] = metaText.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		productInfoMap.forEach((k,v) -> System.out.println(k + ":" + v));
		return productInfoMap;
	}
	
	public Map<String, String> getProductPriceMetaData() {
		List<WebElement> priceMetaList = eleUtil.getElements(productPriceData);
		productInfoMap = new LinkedHashMap<String, String>();
		for(WebElement e: priceMetaList ) {
			String priceMetaText = e.getText();
			if(priceMetaText.contains(":")) {
				String meta[] = priceMetaText.split(":");
				String metaKey = meta[0].trim();
				String metaValue = meta[1].trim();
				productInfoMap.put(metaKey, metaValue);	
			}else {
				String metaKey = priceMetaText;
				String metaValue = " ";
				productInfoMap.put(metaKey, metaValue);
			}

		}
		productInfoMap.forEach((k,v) -> System.out.println(k +":"+ v));
		return productInfoMap;
	}
	
}
