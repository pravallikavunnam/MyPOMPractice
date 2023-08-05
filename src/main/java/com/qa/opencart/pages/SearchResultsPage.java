package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productResults=By.cssSelector("div.product-layout");
	 
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver; 				  
		eleUtil=new ElementUtil(driver);
	}
	
	public int getSearchResultsCount() {
		return eleUtil.waitForElementsVisible(productResults, AppConstants.MEDIUM_TIME_OUT).size();
	}
	//locator we have to create now on the basis of given productName
	//so we cannot declare at class level as we won't get it class level
	//As we create By locators at the class level when locators are fixed
	
	public ProductInfoPage selectProduct(String productName) {
		eleUtil.clickElementWhenReady(By.linkText(productName), AppConstants.SHORT_TIME_OUT);
		//perform some click so return the object of next landing page by using TDD approach
		return new ProductInfoPage(driver);
	}
	//dynamic locators: on the basis of method parameter we create locator

	
}
