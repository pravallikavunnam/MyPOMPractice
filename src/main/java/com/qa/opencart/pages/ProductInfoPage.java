package com.qa.opencart.pages;

import java.util.HashMap;
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
	
	private By productHeader=By.cssSelector("div#content h1");
	private By productImage=By.cssSelector("ul.thumbnails img");
	private By quantity=By.name("quantity");
	private By addToCartBtn=By.id("button-cart");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String,String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver=driver; 				  
		eleUtil=new ElementUtil(driver);
	}
	
	public String getProductHeaderValue() {
		return eleUtil.doElementGetText(productHeader);
	}
	//we cannot validate images with selenium /not in scope of selenium
	//we verify just image is available or not
	public int getProductImagesCount() {
		int actproductImagesCount=eleUtil.waitForElementsVisible(productImage, AppConstants.MEDIUM_TIME_OUT).size();
		System.out.println("total product images for:" + getProductHeaderValue() +"=====>" + actproductImagesCount );
		return actproductImagesCount;
	}
	//Brand: Apple
	//Product Code: Product 18
	//Reward Points: 800
	//Availability: In Stock
	private void getProductMetaData() {
		List<WebElement> metaList=eleUtil.waitForElementsVisible(productMetaData, AppConstants.MEDIUM_TIME_OUT);
		//parent Interface of HashMap is Map Interface where Map says give me generics for key and value both
	//	Map<String,String> metaMap=new HashMap<String,String>();
		for(WebElement e:metaList) {
			String metaText=e.getText();//now the text will be in key and value pair format with :
			//so now we split the string using split method
			String key=metaText.split(":")[0].trim(); //return type of split method is String[] String Array
			String value=metaText.split(":")[1].trim();
			//now we need a collection to store key and value ---we use HashMap Collection
			// HaspMap is a another collection which stores values on the basis of key and value pair format
			//In HashMap to add the values we use put method
			productMap.put(key, value);
			
		}
	//	return metaMap;
	}
	//$2,000.00
	//Ex Tax: $2,000.00
	private void getProductPriceData() {
		List<WebElement> priceList=eleUtil.waitForElementsVisible(productPriceData, AppConstants.MEDIUM_TIME_OUT);
//		Map<String,String> priceMap=new HashMap<String,String>();
		String actPrice=priceList.get(0).getText().trim();
		String exTaxKey=priceList.get(1).getText().split(":")[0].trim();
		String exTaxValue=priceList.get(1).getText().split(":")[1].trim();
		productMap.put("price",actPrice);//when there is no key available we give own own key
		productMap.put(exTaxKey, exTaxValue);
		
		
	//	return priceMap;
		}
	
	//Master method to give complete data of product
	
	public Map<String,String> getProductData() {
		productMap=new HashMap<String,String>(); //as productMap declared at global level we use can it anywhere
	//	productMap=new LinkedHashMap<String,String>(); //to display in order
	//	productMap=new TreeMap<String,String>(); //to display in sorting[alphabetical] order
		productMap.put("productheader", getProductHeaderValue());  //ProductHeaderData
		productMap.put("productimages", String.valueOf(getProductImagesCount())); //but this method returns int 
		//so we have two options 1. need to write a class which can hold any datatype
		//2. convert the integer returning method to string 
		getProductMetaData(); //ProductMetaData
		getProductPriceData(); //ProductPriceData
		
		return productMap;
		
	}
	
	
	
	
	
	
	
	

}
