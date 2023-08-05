package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver; 
	private ElementUtil eleutil;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By subscribeYes=By.xpath("//label[normalize-space()='Yes']");
	private By subscribeNo=By.xpath("//label[normalize-space()='No']");
	
	private By agreeCheckBox=By.name("agree");
	private By continueButton=By.xpath("//input[@type='submit']");
	
	private By successMessg=By.cssSelector("div#content h1");
	private By logoutLink=By.linkText("Logout");
	private By registerLink=By.linkText("Register");
	
	
	public boolean registerUser(String firstName,String lastName,String email,String telephone,String password,String subscribe) {
		eleutil.waitForElementVisible(this.firstName, AppConstants.MEDIUM_TIME_OUT).sendKeys(firstName);
		eleutil.doSendKeys(this.lastName, lastName);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(this.telephone, telephone);
		eleutil.doSendKeys(this.password, password);
		eleutil.doSendKeys(this.confirmPassword, password);
		if(subscribe.endsWith("Yes")) {
			eleutil.doClick(subscribeYes);
		}
		else {
			eleutil.doClick(subscribeNo);
		}
		eleutil.doClick(agreeCheckBox);
		eleutil.doClick(continueButton);
		String successMessage=eleutil.waitForElementVisible(successMessg, AppConstants.MEDIUM_TIME_OUT).getText();
		System.out.println(successMessage);
		if(successMessage.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG)) {
			eleutil.doClick(logoutLink);
			eleutil.doClick(registerLink);
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	

}
