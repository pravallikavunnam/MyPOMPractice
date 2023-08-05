package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

//import com.qa.opencart.frameworkexceptions.FrameworkException;

public class ElementUtil {

	private Actions act;
	
	private JavaScriptUtil jsUtil;
	
	//this methods will be stored in CMA if we use as static then only one copy will be created
	//so in this case driver also should be made as static --driver also will go for CMA--Where we cannot do any parallel execution
	//then how we supply driver to this class--we create constructor and use OOP concepts
	
	private WebDriver driver; //declaring driver  --default value will be null
	//we are declaring it as private  ---if not tomrw anyone can a create object and start acessing it is made public
	//Separate webdriver for the class
	//with the help of constructor we initialize this variable --so here we are using oop concept Encapsulation
	//In Encapsulation --private variables can be accessed by public layers --like public constructor or public getter or setter
	
	//create public constructor --public constructor name will be same as className
	//why create constructor :because i have to use driver 
	
	//we need to check who is supplying driver to ElmentUtil because default value of declared driver will be null..
	//so we have to initialize declared driver with  driver in the constructor 
	//this constructor says you have to give me driver 
	//Element Util is not responsible to initialize driver --where browser util is repsonsible to initialize driver
	
	 public ElementUtil(WebDriver driver) { 
		 												//,and wants to supply same driver to declared driver
		 this.driver=driver;
		 act = new Actions(driver);
		 jsUtil=new JavaScriptUtil(driver);
	 }
	 //why we created ElementUtil Constructor ----we no need to initialzie -->Webdriver driver=new ChromeDriver();
	 
	 
	 //how to call this methods--as these methods are non-static ,so we have to create object of ElementUtil class
	 
	 //getElementAttribute
	 
	 
	/*------------------------doSendKeys--------------------------*/
	 
	 public void doSendKeys(By locator,String value) {
	//	 if(value==null) {
		//	 System.out.println("value cannot be null while using sendkeys method");
		//	 throw new FrameworkException("VALUECANNOTBENULL");
		// }
		 getElement(locator).sendKeys(value);       
	}
	 
	 /*------------------------getElement Method--------------------------*/
	 
	public WebElement getElement(By locator)  { 
//		WebElement element=null;  //creating an WebElement with element as reference = null  
//	try {
//		element=driver.findElement(locator); //if i pass wrong locator exception is thrown here so put it in try catch block 
//	}
//	catch (NoSuchElementException e) {
//		System.out.println("getting element exception...plz check your locator again");
//		e.printStackTrace();
//		try {
//				Thread.sleep(2000);//used to provide some wait ie..2000 milli seconds is 2 seconds then try to create element one more time
//			} catch (InterruptedException e1) 
//			{
//				
//				e1.printStackTrace();
//			} 
//			element=driver.findElement(locator);
//	}
//		return element;
//	}
		
		WebElement element=driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		return element;
	}	
	
	/*------------------------getElements Method--------------------------*/ 
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	/*------------------------getElementsCount--------------------------*/
	
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}
	
	/*------------------------doClick--------------------------*/
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	/*------------------------doElementGetText--------------------------*/
	
	public String doElementGetText(By locator) {
		String eleText=getElement(locator).getText();
		System.out.println("element text is ----->" +eleText);
		return eleText;
	}
	
	/*-----------------------checkElementIsDisabled--------------------------*/
	
	public boolean checkElementIsDisabled(By locator) {
		String disabledValue=getElement(locator).getAttribute("disabled");
		if(disabledValue.equals("true"))
		{
			return true;
		}
		return false;
	}
	
	/*------------------------checkElementIsDisplayed--------------------------*/
	
	public boolean checkElementIsDisplayed(By locator) {
	
		return getElement(locator).isDisplayed();
	}
	
	/*------------------------getElementValue--------------------------*/
	
	public String getElementValue(By locator,String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	
	}
	/*------------------------clickonElement--------------------------*/
	
	public void clickonElement(By locator,String linkText) {
		List<WebElement> linksList=driver.findElements(locator);
		System.out.println("total number of links=" + linksList.size());
		for(WebElement e: linksList) {
			String text=e.getText();
			System.out.println(text);
			if(text.equals(linkText))
			{
				e.click();
				break;
			}
		}
	}
	
	/*------------------------doSearch--------------------------*/
	
	public  void doSearch(By searchLocator ,By searchSuggLocator,String searchKey,String suggName) throws InterruptedException {
		doSendKeys(searchLocator, searchKey);
		Thread.sleep(4000);
		List<WebElement> suggestionList=driver.findElements(searchSuggLocator);
		System.out.println(suggestionList.size());
		for(WebElement e: suggestionList) {
		String text=e.getText();
			System.out.println(text);
			if(text.contains(suggName)) {
				e.click();
				break;
			}
		}
		
	}
	
	/*------------------------getElementTextList--------------------------*/
	
	public List<String> getElementTextList(By locator) {
		List<WebElement> eleList=getElements(locator);
		List<String> eleTextList=new ArrayList<String>();
		System.out.println(eleList.size());
		for( WebElement e : eleList) {
			String text=e.getText();
			if(!text.isEmpty()) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}
	
	/*------------------------getElementAttribute--------------------------*/

	public String getElementAttribute(By locator,String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	
	}
	
	//***************************Drop Down Utils***************************//
	
	public void doSelectByIndex(By locator, int index) {

		if (index < 0) {
			System.out.println("Please pass the right (+)ve index");
			return; // only return means return nothing and void both are same
		}
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doSelectByVisibleText(By locator, String visibleText) {

		if(visibleText==null)
		{
			System.out.println("Please pass the right visible text and it cannot be null");
			return;
		}
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}

	public void doSelectByValue(By locator, String attributeValue) {

		if(attributeValue==null)
		{
			System.out.println("Please pass the right visible text and it cannot be null");
			return;
		}
		Select select = new Select(getElement(locator));
		select.selectByValue(attributeValue);
	}
	
	
	public List<String> getDropDownTextList(By locator) {
		Select select=new Select(getElement(locator));  //call getElementMethod
		List<WebElement> optionsList=select.getOptions();  //getOptions method will return list of webElements 
		List<String> optionsTextList=new ArrayList<String>();  //list of blank ArratList creation
		for(WebElement e:optionsList)
		{
			String optionName=e.getText();
			optionsTextList.add(optionName);
		}
		return optionsTextList;
	}
	
	
	public int getDropDownOptionsCount(By locator) {
		Select select=new Select(getElement(locator));
		return select.getOptions().size();
	}
	
	public void doSelectDropdownValue(By locator,String dropDownValue)
	{
		Select select=new Select(getElement(locator));
		List<WebElement> optionsList =select.getOptions();
		for(WebElement e:optionsList) {
			String list=e.getText();
			System.out.println(list);
			if(list.equals(dropDownValue)){
				e.click();
				break;
			}
		}
		
	}
	
	
	public void doSelectDropDownValueUsingLocator(By locator,String optionName) {
		
		List<WebElement> optionsList=driver.findElements(locator);
		int optionsCount=optionsList.size();
		for(WebElement e:optionsList) {
			String name=e.getText();
			if(name.equals(optionName)) {
				e.click();
				break;
			}
		}
	
}


//***************************Action Utils***************************//
	//Creating Action at Class level

	public void selectRightClickOption(By contextMenuLocator, String optionValue) {
	//Actions act = new Actions(driver);
	act.contextClick(getElement(contextMenuLocator)).perform();
	By optionLocator = By.xpath("//*[text()='" + optionValue + "']"); // span is hardcoded so we replace span with *
	doClick(optionLocator);

	}

	//if we have two level menu 
	public void doTwoLevelMenuHandling(By levelOneMenuLocator, By LevelTwoMenuLocator) throws InterruptedException {
	//Actions act = new Actions(driver);
	act.moveToElement(getElement(levelOneMenuLocator)).perform();
	Thread.sleep(1500);
	doClick(LevelTwoMenuLocator);

	//if we want to use builder patter
//	act.moveToElement(getElement(levelOneMenuLocator)).click(getElement(LevelTwoMenuLocator)).build().perform();
	}

	
	
	public WebElement getLinkNameEleByText(String linktext) {
		return driver.findElement(By.linkText(linktext));
		
	}
	//if we have four level menu
	
	public void multiLevelMenuHandling(By level1Locator,String level2,String level3,String level4) throws InterruptedException
	{
	//	Actions act = new Actions(driver);
		act.moveToElement(getElement(level1Locator)).perform();
		Thread.sleep(1500);
		act.moveToElement(getLinkNameEleByText(level2)).perform();
		Thread.sleep(1500);
		act.moveToElement(getLinkNameEleByText(level3)).perform();
		Thread.sleep(1500);
		getLinkNameEleByText(level4).click();
				
	}
			
			//method overloading example with same method name but different parameters
			//if we have three level menu
	public void multiLevelMenuHandling(By level1Locator,String level2,String level3) throws InterruptedException
	{
		//Actions act = new Actions(driver);
		act.moveToElement(getElement(level1Locator)).perform();
		Thread.sleep(1500);
		act.moveToElement(getLinkNameEleByText(level2)).perform();
		Thread.sleep(1500);
		getLinkNameEleByText(level3).click();

	}
	
	//***************************Using JavaScript executor for handling Pseudo Elements***************************//
	public void checkElementIsMandatory(String value) {
		JavascriptExecutor js=(JavascriptExecutor)driver  ;
		String script="return window.getComputedStyle(document.querySelector(\"label[for='input-"+value+"']\") , '::before').getPropertyValue('content')";
		String man_text=js.executeScript(script).toString(); //gives * which is JS String so convert to string 
		System.out.println(man_text);
		if(man_text.contains("*")) {
			System.out.println(value +" : "+"Element is mandatory");
		}
	}

	//***************************Using Waits***************************//
	
	/**
     * An expectation for checking that an element is present on the DOM of a page.
     *  This does not necessarily mean that the element is visible.
     * 
     * @param locator
     * @param timeOut
     * @return
     */
		
	public WebElement waitForElementPresence(By locator,int timeOut ) {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator)) ;
		
		}
	
	//Overloading method with adding polling time parameter
	public WebElement waitForElementPresence(By locator,int timeOut,int pollingTime ) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10),Duration.ofSeconds(pollingTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator)) ;
	
	}
	
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
		Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	* @param locator
	 * @param timeOut
	 * @return
	 */
	
	public WebElement waitForElementVisible(By locator,int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	//Overloading method with adding polling time parameter
	public WebElement waitForElementVisible(By locator,int timeOut,int pollingTime) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofSeconds(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	//***************************Using WebDriver Wait for getTitle and getCurrentURL***************************//
	
	//here we give titleFraction
	public String waitForTitleContains(String titleFraction,int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
//		return wait.until(ExpectedConditions.titleContains(titleFraction));
		try {
		if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		}
		else
		{
			System.out.println(titleFraction +" title value not present");
			return null;
		}
		}
		catch(Exception e) {
			System.out.println(titleFraction +" title value not present");
			e.printStackTrace();
			return null;
		}
		
	
	}
	
	
	//here we need to give full Title
	public String waitForTitleIs(String titleValue,int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
		if(wait.until(ExpectedConditions.titleContains(titleValue))) {
			return driver.getTitle();
		}
		else
		{
			System.out.println(titleValue +" title value not present");
			return null;
		}
		}
		catch(Exception e) {
			System.out.println(titleValue +" title value not present");
			e.printStackTrace();
			return null;
		}
		
	
	}
	
	//here we give urlFraction
	public String waitForURLContains(String urlFraction,int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
		if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
		else
		{
			System.out.println(urlFraction +" url value not present");
			return null;
		}
		}
		catch(Exception e) {
			System.out.println(urlFraction +" url value not present");
			e.printStackTrace();
			return null;
		}
		
	
	}

	//here we give urlValue
	
	public String waitForURLToBe(String urlValue,int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
		if(wait.until(ExpectedConditions.urlToBe(urlValue))) {
			return driver.getCurrentUrl();
		}
		else
		{
			System.out.println(urlValue +" url value not present");
			return null;
		}
		}
		catch(Exception e) {
			System.out.println(urlValue +" url value not present");
			e.printStackTrace();
			return null;
		}
		
	
	}
	

	//***************************Using WebDriver Wait for Alert***************************//
	
	public Alert waitForJSAlert(int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	//***************************Using WebDriver Wait for BrowserWindow***************************//
	
	
	
	
	public boolean WaitForNumberOfBrowserWindows(int timeOut,int numberOfWindowsToBe) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindowsToBe));
		
	}

	//***************************Using WebDriver Wait for Frames***************************//
	
	public void waitForFrameByLocator(By frameLocator,int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		
	}
	
	public void waitForFrameByIndex(int frameIndex,int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		
	}
	
	public void waitForFrameByFrameNameOrID(String frameNameorID,int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameorID));
		
	}
	
	public void waitForFrameByFrameElement(WebElement frameElement,int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		
	}
	
	//***************************Using WebDriver Wait for Elements***************************//
	
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
		public List<WebElement> waitForElementsPresence(By locator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}
	/**
	 * An expectation for checking that all elements present on the web page that match the locatorare visible. 
	 * Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
		
		public List<WebElement> waitForElementsVisible(By locator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
	
		//***************************Using WebDriver Wait for ClickonWebElemet***************************//
		
		/**
		 * 
		 * An expectation for checking an element is visible and enabled such that you can click it.
		 * @param locator
		 * @param timeOut
		 */
		
		public void clickElementWhenReady(By locator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
			
		}
		
		
		
		//***************************Fluent Wait Utils***************************//
		
		public WebElement waitForElementVisibleWithFluentWait(By locator,int timeOut,int pollingTime) {
			
			Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))   //wait time //method returns this keyword which is current class object
					.pollingEvery(Duration.ofSeconds(pollingTime))   //polling time
					.ignoring(NoSuchElementException.class)   //ignoring exp while doing polling mechanism with total timeout
					.ignoring(StaleElementReferenceException.class)
					.ignoring(StaleElementReferenceException.class)
					.withMessage("----timeout is done...element is not found" + locator);
			
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		public WebElement waitForElementPresenceWithFluentWait(By locator,int timeOut,int pollingTime) {
			
			Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))   //wait time //method returns this keyword which is current class object
					.pollingEvery(Duration.ofSeconds(pollingTime))   //polling time
					.ignoring(NoSuchElementException.class)   //ignoring exp while doing polling mechanism with total timeout
					.ignoring(StaleElementReferenceException.class)
					.ignoring(StaleElementReferenceException.class)
					.withMessage("----timeout is done...element is not found" + locator);
			
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		public Alert waitForJSAlertWithFluentWait(int timeOut,int pollingTime) {
			Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))   //wait time //method returns this keyword which is current class object
					.pollingEvery(Duration.ofSeconds(pollingTime))   //polling time
					.ignoring(NoAlertPresentException.class)   //ignoring exp while doing polling mechanism with total timeout
					.ignoring(StaleElementReferenceException.class)
					.withMessage("----timeout is done...element is not found" );
			return wait.until(ExpectedConditions.alertIsPresent());
			
		}
		public void waitForFrameWithFluentWait(String frameNameOrID,int timeOut,int pollingTime) {
			Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))   //wait time //method returns this keyword which is current class object
					.pollingEvery(Duration.ofSeconds(pollingTime))   //polling time
					.ignoring(NoSuchFrameException.class)   //ignoring exp while doing polling mechanism with total timeout
					.ignoring(StaleElementReferenceException.class)
					.withMessage("----timeout is done...element is not found" );
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrID));
			
		}
		
		
		
		//***************************Custom Waits***************************/

		public WebElement retryingElement(By locator,int timeOut) {
		
			WebElement element=null;
			int attempts=0;
			while(attempts<timeOut) {  
				try 
				{
				element=getElement(locator);
				System.out.println("Ele is found ...."+locator + "in attempt " +attempts);
				break;
				}
				catch(NoSuchElementException e) 
				{
					System.out.println("Ele is not found ...."+locator + "in attempt " +attempts);
					try {
						Thread.sleep(500);
					}
					catch(InterruptedException e1) {
						e1.printStackTrace();
					}
					
				}
				attempts++;
			}
			if(element==null) {
				
				System.out.println("Ele is not found ...tried for"+timeOut+"secs"+"with the interval of"+ 500 +" milli secs");
			}
			return element;
			
		}
		
		
		public  WebElement retryingElement(By locator,int timeOut,long pollingTime) {
			WebElement element=null;
			int attempts=0;
			while(attempts<timeOut) {  
				try 
				{
				element=getElement(locator);
				System.out.println("Ele is found ...."+locator + "in attempt " +attempts);
				break;
				}
				catch(NoSuchElementException e) 
				{
					System.out.println("Ele is not found ...."+locator + "in attempt " +attempts);
					try {
						Thread.sleep(pollingTime);
					}
					catch(InterruptedException e1) {
						e1.printStackTrace();
					}
					
				}
				attempts++;
			}
			if(element==null) {
				System.out.println("Ele is not found ...tried for"+timeOut+"secs"+"with the interval of"+ pollingTime+" milli secs");
			}
			return element;
			
		}
		
		//***************************Page LoadTime Out Concept***************************/		
		
		
		public boolean isPageLoaded(int timeOut) {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			String flag=wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete'")).toString();
			return Boolean.parseBoolean(flag);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	









//private WebDriver driver; // the idea is somehow i want to  use this driver -so that i can call driver.findElement method
// if no constructor then create object of ElementUtil and calling method getElement method 
//so when method called the value of driver will be null so we get NullPointerException ... 
//so to avoid this the driver should point to someone like chromedriver or firefoxdriver
//with the help of constructors--like if someone can give me the driver value problem will be solved
//In locator class we have driver=new ChromDriver(); here driver pointing to chromedriver with session ID 123
//we have to call methods in ElementUtil class,like we need to create object of the class--so when object created constructor will be called
//where constructor asked to pass driver as parameter
//after passing driver to constructor but how to use drive in methods it is local variable
//so we declare a global variable and assign local variable to global variable like thos.driver(global)=driver(local)
//
//public ElementUtil(WebDriver driver) { //this constructor says you have to give me driver ,and wants to supply same driver to declared driver
//	 this.driver=driver;
// }















