package com.qa.basepage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	
	WebDriver driver;
	Properties prop;
	public boolean highlightElement;
	
	public WebDriver init_Driver(String browserName)
	{
		highlightElement= prop.getProperty("highlight").equals("Yes") ? true : false;
		
		System.out.println("Browser name is " +browserName);
		
		if(browserName.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			
			if(prop.getProperty("headless").equals("yes"))
			{
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--headless");
			driver= new ChromeDriver(co);
			}
			else
			{
				driver= new ChromeDriver();
			}
		}
		else if(browserName.equals("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			
			if(prop.getProperty("headless").equals("yes"))
			{
			FirefoxOptions co = new FirefoxOptions();
			co.addArguments("--headless");
			driver= new FirefoxDriver(co);
			}
			else
			{
				driver= new FirefoxDriver();
			}
		}
		
		else if(browserName.equals("Safari"))
		{
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver= new SafariDriver();
		}
		else
		{
			System.out.println("Browser Name" +browserName+ "is not found. Please pass the correct Browser");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		
		
		return driver;
	}
	
	public Properties init_properties()
	{
		prop = new Properties();
		String path = "./src/main/java/com/qa/hubspot/config/config.properties";
		try 
		{
			FileInputStream ip = new FileInputStream(path);// make a connection with config.properties
			prop.load(ip); //one object will be created and all the properties will be stored in that prop.
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("some issue with config properties...Please correct your config");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return prop;
		
	}

}
