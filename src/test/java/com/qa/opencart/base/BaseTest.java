package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	protected Properties prop; //making it as protected so we can inherit same in login page test class 
	WebDriver driver;
	protected LoginPage loginpage; //creating LoginPage reference so that we can Inherit loginpage in LoginPageTest
	//as now this is default so we cannot access outside the package in LoginPageTest
	//so will assign to protected
	
	protected AccountsPage accPage;
	protected SearchResultsPage searchResPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage regPage;
	DriverFactory df;
	
	protected SoftAssert softAssert; //only child class of base test class can access 
	
	@BeforeTest
	public void SetUp() {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pravallika\\Downloads\\chromedriver114\\chromedriver.exe");
//		driver =new ChromeDriver();
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		df=new DriverFactory();
		//calling by refernce prop
		prop=df.initProp();
		driver=df.initDriver(prop); //initDriver return WebDriver so store in driver so that it will passed to loginpage
		
		loginpage=new LoginPage(driver); //initializing the login page  
		                	//LoginPage constructor will be called which is waiting for driver so here in base class we have driver
							//so we pass the driver
		softAssert=new SoftAssert();
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
