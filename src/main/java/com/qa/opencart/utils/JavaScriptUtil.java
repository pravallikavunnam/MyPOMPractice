package com.qa.opencart.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	private JavascriptExecutor js;
	
	//creating one public constructor
	//have to create object of Javatil class
	//construtor name will be same as class name
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver=driver;
		js=(JavascriptExecutor)this.driver;//we use js to execute scripts in various methods
	}
	
	//if driver.getTitle is not giving title then use this method
	public String getTitlebyJS() {
		return js.executeScript("return document.title").toString();

	}
	
	public void goBackWithJS() {
		js.executeScript("history.go(-1);");
	}
	
	public void goForwardWithJS() {
		js.executeScript("history.go(1);");
	}
	
	public void pageRefreshWithJS() {
		js.executeScript("history.go(0);");
	}
		
	
	public void generateJSAlert(String mesg) throws InterruptedException {
		js.executeScript("alert('"+mesg+"')");
		Thread.sleep(500);
		driver.switchTo().alert().accept();
	}
	
	public void generateJSConfirmAlert(String mesg) {
		js.executeScript("confirm('"+mesg+"')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}
	
	public void generateJSPromptAlert(String mesg,String value) {
		js.executeScript("prompt('"+mesg+"')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alert=driver.switchTo().alert();
		alert.sendKeys("value");
		alert.accept();
	}
	
	//if we want to get text from the page where text is available in the form of paragraph
	//In Selenium we don't have any method which gives entire page text
	//In JS We have one method to get entire page text ----document.documentElement.innerText
	
	
	public String getPageInnerText() {
		return js.executeScript("return document.documentElement.innerText;").toString();
	}
	
	public void scrollPageUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	
	public void scrollPageDown() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void scrollPageDown(String height) {
		js.executeScript("window.scrollTo(0,'"+height+"')");
	}
	
	public void scrollPageDownMiddlePage(String height) {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight/2)");
	}
	
	public void scrollToView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true)" ,element);
	}
	
	//Usecase of zoom :we can use Zoom In/Zoom Out for responsive testing--Accessibility testing 
	
	/**
	 * example: "document.body.style.zoom = '400.0%'"
	 * @param zoomPercentage
	 */
	
	
	public void zoomChromeEdgeSafari(String zoomPercentage) {
		String zoom="document.body.style.zoom='"+ zoomPercentage +" %'" ;
		js.executeScript(zoom);
	}
	
	
	/**
	 * example: "document.body.style.MozTransform = 'scale(0.5)'; ";
	 * @param zoomPercentage
	 */
	public void zoomFirefox(String zoomPercentage) {
		String zoom = "document.body.style.MozTransform = 'scale("+zoomPercentage+")'";
		js.executeScript(zoom);
	}
	
	public void drawBorder(WebElement element) {
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor"); //give the rgb color combination of that color
		for (int i = 0; i < 10; i++) {
			changeColor("rgb(178,42,151)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}
	
	private void changeColor(String color, WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		//G->P->G->P

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
		
		
		

	

