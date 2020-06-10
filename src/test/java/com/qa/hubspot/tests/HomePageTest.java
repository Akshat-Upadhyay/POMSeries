package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.basepage.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;

public class HomePageTest {
	
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeTest
	public void setup() throws InterruptedException
	{
		basePage = new BasePage();
		prop = basePage.init_properties();
		
		String browserName = prop.getProperty("browser");
		driver=  basePage.init_Driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage= new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(5000);
		
	}	
	
	@Test
	public void verifyHomePagetitletest()
	{
		String title = homePage.getHomePageTitle();
		System.out.println("Home Page title is " +title);
		Assert.assertEquals(title,"Reports dashboard");
	}
	
	
	@Test
	public void getHomePageHeader()
	{
		String header = homePage.getHomePageHeader();
		System.out.println("Home Page header is "+header);
		Assert.assertEquals(header, "Sales Dashboard");
	}
	
	
	@Test(enabled = false)
	public void verifyLoggedInUserTest()
	{
		String accountName = homePage.getLoggedInUserAccountName();
		System.out.println("Logged in Account Name " +accountName);
		Assert.assertEquals(accountName, "Infosys");
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
