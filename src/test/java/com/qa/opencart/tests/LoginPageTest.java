package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


//@Listeners(ExtentReportListener.class)
public class LoginPageTest extends BaseTest {

	@Description("Login page title test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Description("Login page url test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		Assert.assertTrue(loginPage.getLoginPageUrl());
	}
	
	@Description("Forgot password link displayed or not")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void isForgotPasswordLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExists());
	}
	
	@Description("Login page login test with correct username and Password")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist()); 
	}
	
	
	
	
}
