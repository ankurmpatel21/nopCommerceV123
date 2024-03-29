package com.nopcommerce.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass
{
    public WebDriver driver;  
    public Properties configPropObj;
	public Logger logger;
    
    @BeforeClass
    @Parameters("browser")
	public void setup(String br) throws IOException
	{
		//Load config.properties
		
		configPropObj = new Properties();
		FileInputStream configfile = new FileInputStream(System.getProperty("user.dir")+"\\Configuration\\config.properties");
		configPropObj.load(configfile);
		
		//Log4J code
		logger = Logger.getLogger("nopCommerceV123");
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\Configuration\\log4j.properties");
		
		
		if(br.equals("chrome"))
		{			
			System.setProperty("webdriver.chrome.driver", configPropObj.getProperty("chromepath"));
			driver=new ChromeDriver();
		}
		else if (br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", configPropObj.getProperty("firefoxpath"));
			driver=new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", configPropObj.getProperty("iepath"));
			driver=new InternetExplorerDriver();
		}	
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"\\Screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");	
		
	}
	
	public String randomestring()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomnumber()
	{
		String generatedstring = RandomStringUtils.randomNumeric(5);
		return generatedstring;
	}
}
