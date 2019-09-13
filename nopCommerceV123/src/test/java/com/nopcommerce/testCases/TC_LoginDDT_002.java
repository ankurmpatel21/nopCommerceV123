package com.nopcommerce.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;
import com.nopcommerce.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{
	
	
	@Test(dataProvider = "LoginData")
	public void loginTest(String user, String pwd) throws IOException, InterruptedException
	{
		logger.info("********* starting TC_LoginTest_002 *************");
		logger.info("********* Opening URL *************");
		driver.get(configPropObj.getProperty("baseURL"));
		driver.manage().window().maximize();
		LoginPage lp = new  LoginPage(driver);
		
		logger.info("********* Providing Login Details *************");
		lp.SetUserName(user);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		Thread.sleep(5000);
		
		logger.info("********* Validating login with valid Credential *************");
		String exp_title = "Dashboard / nopCommerce administration";
		String act_title = driver.getTitle();
		
		if(exp_title.equals(act_title))
		{
			logger.info("********* Login Test Is Passed *************");
			lp.clickLogout();
			Assert.assertTrue(true);
		}
		else
		{
			captureScreen(driver,"loginTest");
			logger.info("********* Login Test Is Failed *************");
			Assert.assertTrue(false);
		}
		
		logger.info("********* Login Test is Finished *************");
		logger.info("********* Finish TC_LoginTest_002 *************");
	}
	
	@DataProvider (name="LoginData")
	public String [][] getdata() throws IOException
	{

		String path=System.getProperty("user.dir")+"/TestData/LoginData.xlsx";
		
		int rowcount=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rowcount][colcount];
		
		for(int i=1;i<=rowcount;i++)
		{		
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]= XLUtils.getCellData(path, "Sheet1",i, j);  //1,0
			}
		}
		
		return logindata;

	}
	
	
	

}
