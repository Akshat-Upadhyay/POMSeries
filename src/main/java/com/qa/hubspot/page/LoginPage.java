package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.basepage.BasePage;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.javaScriptUtil;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By emailID = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signup = By.linkText("Sign up");
	javaScriptUtil jsUtil;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		elementUtil= new ElementUtil(driver);
		jsUtil = new javaScriptUtil(driver);
	}
	
	public String getPageTitle()
	{
		return elementUtil.doGetPageTitle();
	}
	
	public String getPageTitleByJS()
	{
		return jsUtil.getTitleByJS();
	}
	
	public boolean checkSignUpLink()
	{
		return elementUtil.doIsDisplayed(signup);
	}
	
	public HomePage doLogin(String username, String pwd)
	{

		
		elementUtil.doSendKeys(emailID, username);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		return new HomePage(driver);
	}
}


//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>NOTES>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//1. first thing to do is to create locator by using By.

//2. Second thing is to create page action 