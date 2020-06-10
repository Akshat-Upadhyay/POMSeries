package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.basepage.BasePage;

public class ElementUtil extends BasePage {
	
	WebDriver driver;
	javaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
		jsUtil = new javaScriptUtil(driver);
	}
	
	
	public String doGetPageTitle()
	{
		try
		{
		return driver.getTitle();
		}
		catch (Exception e)
		{
			System.out.println("Some error occurred while trying to fetch the title");
		}
		return null;
	}
	
	
	/** this method is used to create the webElement on the basis of by locator
	 * @param locator
	 * @return element
	 */
	public WebElement getElement(By locator)
	{
		WebElement element=null;
		
		try
		{
		element = driver.findElement(locator);
		
		if(highlightElement)
		{
			jsUtil.flash(element);
		}
		}
		catch (Exception e)
		{
			System.out.println("Some error occurred while creating the Web Element.....");
		}
		
		return element;
	}
	
	public void doClick(By locator)
	{
		try
		{
		getElement(locator).click();
		}
		catch(Exception e)
		{
			System.out.println("Some error occurred while clicking the Web Element...");
		}
	}
	
	
	public void doSendKeys(By locator, String value)
	{
		try
		{
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
		}
		catch (Exception e)
		{
			System.out.println("Some Error occured while entering values in a field...");
		}
	}
	
	
	public boolean doIsDisplayed(By locator)
	{
		try
		{
		return getElement(locator).isDisplayed();
		}
		catch (Exception e)
		{
			System.out.println("Some error occurred while checking the display....");
		}
		return false;
	}
	
	
	public String doGetText(By locator)
	{
		try
		{
		return getElement(locator).getText();
		}
		catch(Exception e)
		{
			System.out.println("Some error occurred while trying to fetch the text");
		}
		return null;
	}
	
}
