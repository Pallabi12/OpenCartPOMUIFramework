package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private static final Logger LOG = Logger.getLogger(LoginPage.class);
	
	//1. By Locator
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By logoImage = By.cssSelector("img[title='naveenopencart']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	//2. Page Constructor (call by reference)
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	//3. Page Action
	
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("Login Page Title is : " + title);
		LOG.info("Login Page Title is : " + title);
		return title;
	}
	
	public boolean getLoginPageUrl() {
		String url = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARAM);
		System.out.println("URL of the login page is : " + url);
		LOG.info("URL of the login page is : " + url);
		if(url.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isForgotPwdLinkExists() {
		return eleUtil.doEleIsDisplayed(forgotPwdLink);
	}
	
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("User credentials are : username: "+ username +" password: " + pwd);
		eleUtil.doSendKeysWithWait(emailId, AppConstants.DEFAULT_LARGE_TIME_OUT, username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		System.out.println("Navigation to registration page");
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}


	private Object registerPage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
