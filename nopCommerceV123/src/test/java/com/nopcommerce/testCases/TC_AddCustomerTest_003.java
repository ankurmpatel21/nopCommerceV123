package com.nopcommerce.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddCustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;

import junit.framework.Assert;

public class TC_AddCustomerTest_003 extends BaseClass 
{
	@Test
	public void AddNewCustomer() throws InterruptedException, IOException
	{
		//Login
		logger.info("********* TC_AddCustomerTest_003 started *************");
		logger.info("********* Opening URL *************");
		driver.get(configPropObj.getProperty("baseURL"));
		driver.manage().window().maximize();
		LoginPage lp = new  LoginPage(driver);
		
		logger.info("********* Providing Login Details *************");
		lp.SetUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		
		//Add New Customer :
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomers();
		addcust.clickOnAddnew();
		
		logger.info("..........Providing Customer Details...........");
		
		
		String email = randomestring()+"@gmail.com";
		addcust.setEmail(email);
		
		addcust.setPassword("test123");
		addcust.setFname("Akshay");
		addcust.setLname("Patel");
		addcust.setGender("Male");
		addcust.setDob("7/05/1980");
		addcust.setCompanyName("Infosys Limited");
		addcust.clickTax();
		addcust.clicknewsletter();
		addcust.setCustomerRole("Guest");
		addcust.setManagerOfVendor("Vendor 1");
		addcust.clickactive();
		addcust.setAdminComment("This is for Testing");
		addcust.clickSave();
		
		//Validation
		
		String msg = driver.findElement(By.tagName("body")).getText();
		
		if(msg.contains("The new customer has been added successfully."))
		{
			logger.info(".........Customer Added Succesfully...........");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("...........Adding Customer Failed..........");
			captureScreen(driver,"AddNewCustomer");
			Assert.assertTrue(false);
		}
		
		
		logger.info("............TC_AddCustomerTest_003 Finished.........");
		
	}
}
