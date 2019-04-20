package com.funtionaltesting;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login extends Base 
{
	
	@BeforeTest
	public void setExtend() 
	{
		//System.out.println("extend Method");
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/extentReport.html", true);
		extent.addSystemInfo("Host Name", "Ricky HomeDesktop");
		extent.addSystemInfo("User Name", "Ricky Testingwork");
		extent.addSystemInfo("Enviornment", "QA");
		//System.out.println("extend Method end");
	}
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException 
	{
		System.out.println("screenshot Method start");
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		System.out.println("screenshot end");
		return destination;
	}
	
	// Intializing variabless
	public String filePath = "C:\\Users\\ricky\\eclipse-workspace\\funtionaltesting\\src\\main\\resources\\LinkedinTestdata.xlsx";
	public String sheetName = "LoginTest";
	public static Object[][] data;

	public WebDriver driver1;
	public ExtentReports extent;
	public ExtentTest extentTest;

	
	@DataProvider(name = "excelData")
	public Object[][] readExcel() throws InvalidFormatException, IOException, InterruptedException 
	{
		data = Base.readExcel(filePath, sheetName);
		return data;
	}

	@Test(dataProvider = "excelData", priority = 1)
	public void read(String Email, String Password) 
	{
		extentTest = extent.startTest("readMethod");
		System.out.println(Email + " :" + Password);
		//System.out.println("Test Case two with Thread Id:- " + Thread.currentThread().getId());
	}
	
	
	@Test(dataProvider = "excelData", priority = 3) // , expectedExceptions =// org.openqa.selenium.NoSuchElementException.class)
	public void login(String Email, String Password) throws InterruptedException 
	{
		extentTest = extent.startTest("loginMethod");
		//System.out.println("login Method start");
		ChromeDriver driver = new ChromeDriver();
		try 
		{
			//System.out.println("Test Case two with Thread Id:- " + Thread.currentThread().getId());
			driver.manage().deleteAllCookies();
			driver.get("https://www.linkedin.com");
			driver.findElement(By.id("login-email")).sendKeys(Email);
			driver.findElement(By.id("login-password")).sendKeys(Password);
			driver.findElement(By.id("login-submit")).submit();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("nav-settings__dropdown")).click();
			Thread.sleep(3000);
			driver.findElement(By.className("block")).click();
			driver.close();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			System.out.println("Invalid login credentials");
			driver.quit();
	
		}
	}
	
	@AfterTest
	public void endReport() 
	{
		//System.out.println("After test starts");
		extent.flush();
		extent.close();
		//System.out.println("After test ends");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		//System.out.println("After Method");
		if(result.getStatus()==ITestResult.FAILURE)
		{
			
			System.out.println("After Method if block");
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = Login.getScreenshot(driver1, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			//System.out.println("After Method else if 1 ");
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			String methodName, message; 
			try
			{
				//System.out.println("After Method else if 2 ");
				//System.out.println(LogStatus.PASS.toString());
				methodName = result.getName().toString();
				message = "Test Case PASSED IS " + methodName;
				extentTest.log(LogStatus.PASS, message);
				//System.out.println("After Method else if 2 exit");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		//System.out.println("After Method before logger");
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		//System.out.println("After Method end");
		//driver1.quit();
	}
}
