package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("EPIC - 103: Desgin of the productInfo page for open cart app")
@Story("US - 203: implement productInfo page features for open cart app")
public class ProductInfoSearchTest extends BaseTest{
	//every test class should extend BaseTest
	//It is not mandatory to have test class for each page class
	//but we should have each independent page class
	//@BeforeTest will be executed first then @BeforeClass  
	
	@BeforeClass
	public void ProductInfoSetup() {
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password")); 
		
	}
	
	@DataProvider
	public Object[][] productTestData() {
		return new Object[][] {  //We are adding searchKey and product name after searching the dataa
				{"Samsung","Samsung SyncMaster 941BW"},
				{"Samsung","Samsung Galaxy Tab 10.1"},
				{"imac","iMac"},
				{"macbook","MacBook Pro"},
				{"macbook","MacBook Air"},
		};
				
	}
	
	
	@Test(dataProvider="productTestData")
	public void productHeaderTest(String searchKey,String productName) {
		searchResPage=accPage.doSearch(searchKey);
		productInfoPage=searchResPage.selectProduct(productName);
		String actProductHeader=productInfoPage.getProductHeaderValue();
		Assert.assertEquals(actProductHeader, productName);
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] {  
				{"Samsung","Samsung SyncMaster 941BW",1},
				{"Samsung","Samsung Galaxy Tab 10.1",7},
				{"imac","iMac",3},
				{"macbook","MacBook Pro",4},
				{"macbook","MacBook Air",4},
		};
				
	}
	
	
	@Test(dataProvider="productData")
	public void productImagesCountTest(String searKey,String productName,int expProductImagesCount) {
		searchResPage=accPage.doSearch(searKey);
		productInfoPage=searchResPage.selectProduct(productName);
		int actProductImagesCount=productInfoPage.getProductImagesCount();
		Assert.assertEquals(actProductImagesCount,expProductImagesCount );
	}
	
	
	//HashMap is non-order based collection--it will give in a random order
	//So,to display in order we go for LinkedHashMap
	//to display in aplabhetical order we go for TreeMap
	@Test
	public void productInfoTest() {
		searchResPage=accPage.doSearch("macbook");
		productInfoPage=searchResPage.selectProduct("MacBook Pro");
		Map<String,String> productActualData=productInfoPage.getProductData();
		System.out.println(productActualData);
		softAssert.assertEquals(productActualData.get("Brand"), "Apple");
		softAssert.assertEquals(productActualData.get("Availability"), "In Stock");
		softAssert.assertEquals(productActualData.get("productheader"), "MacBook Pro");
		softAssert.assertEquals(productActualData.get("price"), "$2,000.00");
		softAssert.assertEquals(productActualData.get("Reward Points"), "800");
		softAssert.assertAll(); //it is mandatory because it gives overall how many assertions passed or failed
	}
	//In the above asserts As per AAA rule ---we can write Asser.AssertEquals or Assert.assertTrue only
	//but if the all the assertions represent same test case-we can write multiple assertions in same test cases
	//if one assert condition failed then it will stop executing the script --these assertions are called as Hard Assert
	//so we go for Soft Assert: which is coming from testNG
	//Soft Assert says you have to create object of SoftAssert class
	//Assertions available inside SoftAssert class are non-static so we create object of SoftAssert class 
	//when we have multiple assertions we go for Soft Assertion
	//when we have single assertion we go for Hard Assertion
}
