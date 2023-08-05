package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//Page class responsibility is to return something in each method so that test class can validate
//No Assertions in page classes

@Epic("EPIC - 100: Desgin of the login page for open cart app")
@Story("US - 200: implement login page features for open cart app")
public class LoginPageTest extends BaseTest{
	
	//It is a TestNG class
	//Login page @Test responsibility is to write Test Cases according to the page class that we have written
	//@Test class should not have any driver.methods ,no selenium code inside the test class
	@Description("login page title test......")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actTitle=loginpage.getLoginPageTitle(); 
		//In test class we have to get the page class reference which is not pointing to null 
		//and starts calling those page class methods
		//those page class methods will give the actual things
		Assert.assertEquals(actTitle,AppConstants.LOGIN_PAGE_TITLE); //here we need to validate actual and expected 
	}
	@Description("login page url test......")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageURLTest() {
		String actURL=loginpage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	
	}
	@Description("check forgot pwd link exist on login page......")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void isForgotPwdLinkExistTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}

	@Description("check user is able to login to open cart with valid credentials......")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=4)
	public void LoginTest() {
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password")); 
		//TestNG need to supply username and password 
		Assert.assertEquals(accPage.IslogoutLinkExist(),true);
	}
	
	

}
