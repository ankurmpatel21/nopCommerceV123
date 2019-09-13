package com.nopcommerce.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_LoginTest_001 extends BaseClass
{
	
	
	@Test
	public void loginTest() throws IOException
	{
		logger.info("********* starting TC_LoginTest_001 *************");
		logger.info("********* Opening URL *************");
		driver.get(configPropObj.getProperty("baseURL"));
		driver.manage().window().maximize();
		LoginPage lp = new  LoginPage(driver);
		
		logger.info("********* Providing Login Details *************");
		lp.SetUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		
		logger.info("********* Validating login with valid Credential *************");
		String exp_title = "Dashboard / nopCommerce administration";
		String act_title = driver.getTitle();
		
		if(exp_title.equals(act_title))
		{
			logger.info("********* Login Test Is Passed *************");
			Assert.assertTrue(true);
		}
		else
		{
			captureScreen(driver,"loginTest");
			logger.info("********* Login Test Is Failed *************");
			Assert.assertTrue(false);
		}
		
		logger.info("********* Login Test is Finished *************");
		logger.info("********* Finish TC_LoginTest_001 *************");
	}
	
	

}
