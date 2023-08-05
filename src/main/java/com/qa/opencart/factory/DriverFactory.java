package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
/**
 * 
 * @author Pravallika
 *
 */
public class DriverFactory {

	WebDriver driver;
	Properties pop;
	OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	//Creating instance of ThreadLocal---threadlocal says you have to ge me generics
	//we have to create threadLocal for the WebDriver
	//TL has two methods 1. Set---to initiliaze TL on specific resource
	//2. get method--to fetch local copy of thread

	/**
	 * This is used to initialize the driver
	 * @param browserName
	 * @return it returns driver
	 */
	public WebDriver initDriver(Properties prop) { // how we initliaze browser--on basis of by giving browsername
		String browserName=prop.getProperty("browser"); //passing key from config.properties in double quotes
		System.out.println("Browser name is:" + browserName);
		highlight=prop.getProperty("highlight");
		optionsManager=new OptionsManager(prop);

		switch (browserName.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pravallika\\Downloads\\chromedriver114\\chromedriver.exe");
		//	driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions())); //it will set the local copy of new chromeDriver
			break;
		case "firefox":
		//	driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));//it will create a thread copy of chromeDriver
			break;
		case "edge":
		//	driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		default:
			System.out.println("Please pass the right browser : " + browserName);
			break;
		}
		getTlDriver().manage().deleteAllCookies();
		getTlDriver().manage().window().maximize();
		getTlDriver().get(prop.getProperty("url"));
		return getTlDriver();
	}
	
	public static WebDriver getTlDriver() { //use tlDriver
		return tlDriver.get() ;      //it will give the local copy of driver
		
	}
	
						//we create method which will read config properties file --here java will read not TestNG or selenium
	/**
	 * This method is used to initialize the properties
	 * @return prop reference 
	 * This method should be called by Base Test
	 */
	public Properties initProp() { 			//in order to initialze prop method we have to create object of properties class
														//so we maintian the Properties pro at global level
		FileInputStream fip=null;
		pop=new Properties(); 							//properties class is coming from Java
		//we need to create object of FileInputStream class 
		//and pass the config.properties file path as parameter
		
		//maven clean install -Denv="qa"
		// -D is option with maven command
		//env is variable name which can be anything
		//qa is value
		
		//In java whenever want to read env variables use--System class ie..,System.getProperty() we use
		String envName=System.getProperty("env");
		System.out.println("Env name is ::" + envName);
		try {
		if(envName==null) {
			System.out.println("No env is given------hence running it on QA env " );
			fip=new FileInputStream("./src\\test\\resources\\config\\qa.config.properties");
		}
		else
		{
			switch (envName.toLowerCase().trim()) {
			case "qa":
				fip=new FileInputStream("./src\\test\\resources\\config\\qa.config.properties");
				break;
			case "dev":
				fip=new FileInputStream("./src\\test\\resources\\config\\dev.config.properties");
				break;
			case "stage":
				fip=new FileInputStream("./src\\test\\resources\\config\\stage.config.properties");
				break;
			case "uat":
				fip=new FileInputStream("./src\\test\\resources\\config\\uat.config.properties");
				break;
			case "prod":
				fip=new FileInputStream("./src\\test\\resources\\config\\config.properties");
				break;
			
			default:
				System.out.println("please pass the right envname :" + envName);
				break;
			}
		}
		
		}	
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		try {
		pop.load(fip);
			} 
		catch (IOException e) {
				e.printStackTrace();
			}
		
		 return pop; 	//once loading part is done return the properties object reference
		 				//this prop is having all the properties of config.properties 
		 				//so instead void we write properties
	}
	
	
	
	/**
	 * take screenshot
	 */
	//we have one interface takesScreenshot which is implemented by RemoteWebdriver class(Child class of takesScreenshot parent Interface)
	//interface takesScreenshot has one method getScreenshotAs() method which has no method body as it is interface class
	//getScreenshotAs() method is implemented by RemoteWebDriver class and takes screenshot 
	//getScreenshotAs() method gets override in RemoteWebDriver class from parent takesScreenshot Interface
	
	public static String getScreenshot(String methodName) {
		//calling getTlDriver method as it returns driver and 
		//1.we need to convert to TakesScreenshot Interface and wrap it with bracket
		//getScreenshotAs() method asks which kind of screenhsot to take
		//return of getScreenshotAs method is  i will take screenshot and return the file object
		//File is coming from Java.io pac ---and OutputType.File gives file type object
		//this line will give the screenshot 
		
		File srcFile=((TakesScreenshot)getTlDriver()).getScreenshotAs(OutputType.FILE);
		//and we need to store it  so we create own custom path
		String path =System.getProperty("user.dir")+"/screenshot/"+ methodName+"_"+System.currentTimeMillis()+".png"; //with the help of System.getProperty one env var we can pass which is already defined
		//user.dir means user directory is already defined in laptop which gives path of current project
		//so go to project directory and after project directory you  create one screenshot folder if it is not there
		//and after that you have to give filename with current time and file extension as .png
		
		//so now Screenshot will be in srcFile
		//To move screenshot from scrFile to Path ---we can move but we cannot move location form file object to String Object
		//File to String transfer we cannot do but we can do File to File Transfer
		//create one destination file and associate with path 
		File destination=new File(path); //destination file
	//	FileUtils.copy(srcFile,destination); not working now in selenium 4.0

		try {
			FileHandler.copy(srcFile, destination);//copy from one location to other location
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
