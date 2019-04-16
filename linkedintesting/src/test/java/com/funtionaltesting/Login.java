package com.funtionaltesting;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login extends Base 
{
	//Intializing variabless
	public String filePath = "C:\\Users\\ricky\\eclipse-workspace\\funtionaltesting\\src\\main\\resources\\LinkedinTestdata.xlsx";
	public String sheetName = "LoginTest";
	public static Object[][] data;

	@DataProvider(name = "excelData")
	public Object[][] readExcel() throws InvalidFormatException, IOException, InterruptedException 
	{
		data = Base.readExcel(filePath, sheetName);
		return data;
	}

	@Test(dataProvider = "excelData", priority = 1)
	public void read(String Email, String Password) 
	{
		System.out.println(Email + " :" + Password);
		System.out.println("Test Case two with Thread Id:- " + Thread.currentThread().getId());
	}

	@Test(dataProvider = "excelData", priority = 2)//, expectedExceptions = org.openqa.selenium.NoSuchElementException.class)
	public void login(String Email, String Password) throws InterruptedException 
	{
		
		ChromeDriver driver = new ChromeDriver();
		try 
		{

			System.out.println("Test Case two with Thread Id:- " + Thread.currentThread().getId());
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
			
			System.out.println("Invalid login credentials");
			driver.quit();

		}

	}
}
