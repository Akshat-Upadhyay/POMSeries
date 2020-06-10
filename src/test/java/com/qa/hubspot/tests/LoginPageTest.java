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
import com.qa.hubspot.util.AppConstant;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	
	@BeforeTest
	public void setup()
	{
		basePage = new BasePage();
		prop = basePage.init_properties();
		
		String browserName = prop.getProperty("browser");
		driver=  basePage.init_Driver(browserName);
		driver.get(prop.getProperty("url"));
		
		loginPage= new LoginPage(driver);
		
	}
	
	@Test(priority = 1)
	public void verfiyLoginPageTitleTest() throws InterruptedException
	{
		Thread.sleep(8000);
		String title = loginPage.getPageTitle();
		System.out.println("Login page title is " +title);
		Assert.assertEquals(title, AppConstant.Login_Page_Header);
		
	}
	
	@Test(priority = 3)
	public void loginTest()
	{
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		HomePage homepage = loginPage.doLogin(username, password);
		String title = homepage.getHomePageTitle();
		Assert.assertEquals(title,"HubSpot Login");
		//Assert.assertEquals("Facebook-login", expected);
	}
	
	
	@Test(priority = 2)
	public void verfiySignUpLinkTest()
	{
		Assert.assertTrue(loginPage.checkSignUpLink());
	}
	
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
