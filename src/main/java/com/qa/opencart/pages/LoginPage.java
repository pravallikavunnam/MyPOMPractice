package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
//This is called as Page Library/Page Class/Page Object
//Login page should class should interact with Element Util class methods
//As Element Util class is waiting for driver as it is not responsible for initializing the webdriver
//So we create object of element util

public class LoginPage { 
	
	private ElementUtil eleutil;
	
	
	//LoginPage is not responsible for initialize the driver=new chromdeDriver
				//LoginPage says whenever you create object of the class you have to give me the driver
	
	private WebDriver driver;  //creating own private webdriver at class level ,the value of driver now will be null 
	//-----becoz we don't have anything and we are not writing driver=new chromedriver();
	//we are saying why don't you give me the driver ----
	//because we are going to call page actions methods ?---by creating object of the class
	//when we create object of the class public constructor will be called and that time you give me and supply the driver
	
	//-------------------------------------------
	//locators are classic example of encapsulation which are private in nature --private By locators
	// 1.here first we have to maintain By locators also called as Page Elements/Page Locators
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@type='submit']");
	private By forgotPwdLink=By.linkText("Forgotten Password");
	private By registerLink=By.linkText("Register");
	private By panduLink=By.linkText("Pandu");
	
	
	
	//2.next we have to design the page constructor 
	//constructor will be public in nature ---so that we can create object of the class 
	//---and can access object of this class from outside class
	//-------------------------------------------
	public LoginPage(WebDriver driver) { //this is a local driver which is given to class driver by using this keyword
		this.driver=driver;
		eleutil=new ElementUtil(driver);//initializing the 
		
	}
	
	
	//If we want to perform some action on this By locators ---the imp thing we need here is 
	//we want to create the webelement and perform some action on it and by using this locators we need a driver
	//According to design loginPage is not responsible for driver ---which is done by driverfactory 
	//creating own private webdriver at class level
	
	//3. we have to define page actions means page behaviour  or page methods we had to define
	//page actions /methods will be public in nature --so that we can call it anywhere from TestNG Class
	//---Page action to fetch the title
	public String getLoginPageTitle() {
		String title=eleutil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.MEDIUM_TIME_OUT);
	//	String title=driver.getTitle();
		System.out.println("Login page title is : "+title);
		return title; //the responsibility of page class is to return something what is happening on the page
	}
	
	//---Page action to get loginpage URL
	
	public String getLoginPageURL() {
	String url=eleutil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION,AppConstants.SHORT_TIME_OUT);
	//String url=	driver.getCurrentUrl();
	System.out.println("Login page url is : "+url);
	return url;
		
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleutil.waitForElementVisible(forgotPwdLink, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
		//return driver.findElement(forgotPwdLink).isDisplayed();
	}
	
	public AccountsPage doLogin(String username,String pwd) {
		System.out.println("App credentials are :" + username +":"+ pwd);
		eleutil.waitForElementVisible(emailId, AppConstants.MEDIUM_TIME_OUT).sendKeys(username);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBtn);
	//	return eleutil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		
		//Applying Page chaining model: two pages are connected
		//After login page accounts page is displayed
		//so whenever landing on the next page after logged in 
		//so its login class method responsibility to create the object of that landing page
		
		return new AccountsPage(driver); //As we have no default constructor in Accounts Page so we supply the driver
		//As we are returning the landing class object so we return the landing class classname
		
		
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
//		return driver.getTitle();
	}
	//method name should be behaviour of the method not action of the method
	public RegisterPage navigateToRegistrationPage() {
		eleutil.waitForElementVisible(registerLink, AppConstants.SHORT_TIME_OUT).click();
		return new RegisterPage(driver);
	}
	
	
	
	
}
