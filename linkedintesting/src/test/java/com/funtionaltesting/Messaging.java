package com.funtionaltesting;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Messaging extends Base
{
	public String filePath = "C:\\Users\\ricky\\eclipse-workspace\\funtionaltesting\\src\\main\\resources\\LinkedinTestdata.xlsx";
	public String sheetName = "Messaging";
	public static Object[][] data;

	@DataProvider(name = "excelData")
	public Object[][] readExcel() throws InvalidFormatException, IOException, InterruptedException 
	{
		data = Base.readExcel(filePath, sheetName);
		return data;
	}
	
	@Test(dataProvider = "excelData", priority = 3)	
	public void loginlogout(String Email, String Password, String Message) throws InterruptedException 
	{
		ChromeDriver driver = new ChromeDriver();
		try 
		{

			System.out.println("Test Case two with Thread Id:- " + Thread.currentThread().getId());
			
			driver.manage().deleteAllCookies();
			
			//login section starts here
			driver.get("https://www.linkedin.com");
			driver.findElement(By.id("login-email")).sendKeys(Email);
			driver.findElement(By.id("login-password")).sendKeys(Password);
			driver.findElement(By.id("login-submit")).submit();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//login section ends here
			
			//messaging section starts here
			driver.get("https://www.linkedin.com/in/kulveersingh/");
		    driver.findElement(By.className("pv-s-profile-actions")).click();       
		    Thread.sleep(5000);
		          
		    driver.findElement(By.className("msg-form__contenteditable")).sendKeys(Message);
		    driver.findElement(By.className("msg-form__send-button")).click();
		    //messaging section ends here
		    
			driver.findElement(By.id("nav-settings__dropdown")).click();
			Thread.sleep(3000);
			driver.findElement(By.className("block")).click();
			driver.close();
		} 
		catch (Exception e) 
		{
			System.out.println("Invalid login credentials, so couldn't continue with messaging");
			driver.quit();
		}
	}
}

