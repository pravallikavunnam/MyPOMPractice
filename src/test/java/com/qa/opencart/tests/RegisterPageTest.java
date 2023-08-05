package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void registarctionPageSetup() {
		regPage=loginpage.navigateToRegistrationPage(); 
		
	}
	
	//in order to pass unique email ID we create a generic method
	public String getRandomEmailID() {
		return "openauto"+System.currentTimeMillis()+"@open.com";
		//System.currentTimeMillis()---it will give the current time 
	}
	
	@DataProvider()
	public Object[][] getUserRegData() {
		return new Object[][] {
			{"chinnu","pandu","9423456789","test@134","Yes"},
			{"munnu","pras","9345634222","test@214","No"},
			{"junnu","pk","9425632123","test@125","Yes"}
			
		};
	}
	//fetching data from excel
	@DataProvider()
	public Object[][] getUserRegSheetData() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider="getUserRegData")
	public void userRegisterTest(String firstName,String lastName,String telephone,String password,String subscribe) {
		Assert.assertTrue(regPage.registerUser( firstName, lastName, getRandomEmailID(),telephone, password, subscribe));
		
	}
//	
//	@Test(dataProvider="getUserRegSheetData")
//	public void userRegisterTest(String firstName,String lastName,String telephone,String password,String subscribe) {
//		Assert.assertTrue(regPage.registerUser( firstName, lastName, getRandomEmailID(),telephone, password, subscribe));
//		
//	}


}
