package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;



@Epic("EPIC - 101: Desgin of the Accounts page for open cart app")
@Story("US - 201: implement Accounts page features for open cart app")
public class AccountsPageTest extends BaseTest {
	//In order to reach AccountsPage we need to login first
	//the advantage of page chaining is now we can call all the methods with reference of AccountsPage class
	
	
	@BeforeClass
	public void accPageSetup() {
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));  //Inheriting login page reference from base test
		      //doLogin method will return the the object of next landing class 
		      //so we store in next landing class ref variable
		      //so we maintain the ref variable in base test and inherit in this class
	}
	@Test
	public void accPageTitleTest() {
		//how to call methods from AccountPage class---we have to create object of Accounts page using page chaining model
		String actAccPagetitle=accPage.getAccPageTitle();
		Assert.assertEquals(actAccPagetitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.IslogoutLinkExist());
	}
	
	@Test
	public void accPageHeadersCountTest() {
		int actAccPageHeadersCount=accPage.getAccountsPageHeaderCount();
		System.out.println("Actual account page headers count :" + actAccPageHeadersCount);
		Assert.assertEquals(actAccPageHeadersCount, AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	
	@Test
	public void accPageHeadersListTest() {
		List<String> actAccPageHeadersList=accPage.getAccountsPageHeader();
		Assert.assertEquals(actAccPageHeadersList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	//to provide data for search we use dataprovider --to provide multilpe search with diff data
	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] {  //We are adding searchKey and product count after searching the dataa
				{"Samsung",2},
				{"imac",1},
				{"macbook",3},
		};
				
	}
	
	
	//mapping data provider to test
	@Test(dataProvider="getSearchKey")
	public void searchTest(String searchKey,int expProductCount  ) { //We need to pass the holding parameters
		searchResPage=accPage.doSearch(searchKey);
		int actResultsCount=searchResPage.getSearchResultsCount();
		Assert.assertEquals(actResultsCount,expProductCount);
	}
	
	//we will think and write test first and then do development and classes......

	
	
	
	
	
}
