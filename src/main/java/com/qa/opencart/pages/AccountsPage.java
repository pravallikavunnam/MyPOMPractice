package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	//1. Create required locators as private
		private By logoutLink=By.linkText("Logout");
		private By accountHeaders=By.xpath("//div[@id='content']/h2");
		private By search=By.name("search");
		private By searchIcon=By.xpath("//div[@id='search']//button");
		
		private WebDriver driver;
		private ElementUtil eleUtil;
		
		//2. create its own constructor
		//every page class constructor expecting driver
		//page class has no its own driver so lets create private webDriver 
		public AccountsPage(WebDriver driver) {
			this.driver=driver; 				  //to supply parameter driver to private  driver 
			eleUtil=new ElementUtil(driver);	 //eleUtil also waiting for driver
			
		}
		
		//3. Create Page Actions/functionality of the page
		public String getAccPageTitle() {
			return eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		}
	
		public boolean IslogoutLinkExist() {
			return eleUtil.waitForElementPresence(logoutLink, AppConstants.SHORT_TIME_OUT).isDisplayed();
		}
		
		public List<String> getAccountsPageHeader() {
			List<WebElement> headersList=eleUtil.waitForElementsVisible(accountHeaders, AppConstants.MEDIUM_TIME_OUT);
			List<String> headersValueList=new ArrayList<String>();
			for(WebElement e:headersList) {
				String header=e.getText(); //i want to store this header in list create one blank array list with generics
				headersValueList.add(header);
				
			}
			System.out.println("Account page headers are====>"+ headersValueList);
			return headersValueList;
		}
		
		public int getAccountsPageHeaderCount() {
			return eleUtil.waitForElementsVisible(accountHeaders, AppConstants.MEDIUM_TIME_OUT).size();
		}

		public SearchResultsPage doSearch(String searchKey) {
			WebElement searchField=eleUtil.waitForElementVisible(search, AppConstants.MEDIUM_TIME_OUT);
			searchField.clear();
			searchField.sendKeys(searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver) ;          //when we click on search we get the results in the next page ,so here doSearch method responsibility to create object of next landing page
		}
		
		//As of now SearchResultsPage page class is not created so we follow approach- TDD Test Driven development Approach
		//TDD means ---you are doing development driven by your test 
		//TDD means --according to your test requirement you have to keep create your page classes or other utility classes
		//we will think and write test first and then do development and classes......

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
