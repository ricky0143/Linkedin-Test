package com.funtionaltesting;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Invitation extends Base{

	public String filePath = "C:\\Users\\ricky\\eclipse-workspace\\funtionaltesting\\src\\main\\resources\\LinkedinTestdata.xlsx";
	public String sheetName = "Invitation";
	public static Object[][] data;

	@DataProvider(name = "excelData")
	public Object[][] readExcel() throws InvalidFormatException, IOException, InterruptedException 
	{
		
		data = Base.readExcel(filePath, sheetName);
		return data;
	}

	@Test(dataProvider = "excelData", priority = 4)	
	public void loginlogout(String Email, String Password) throws InterruptedException 
	{
		ChromeDriver driver = new ChromeDriver();
		//try 
		//{
			System.out.println("Test Case two with Thread Id:- " + Thread.currentThread().getId());
			driver.manage().deleteAllCookies();	

			//login section starts here
			driver.get("https://www.linkedin.com");
			driver.findElement(By.id("login-email")).sendKeys(Email);
			driver.findElement(By.id("login-password")).sendKeys(Password);
			driver.findElement(By.id("login-submit")).submit();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//login section ends here
		    Thread.sleep(2000);
	       
	        driver.findElement(By.xpath("//*[@id=\"mynetwork-nav-item\"]")).click();
	        Thread.sleep(1000);
	        driver.findElement(By.className("mn-invitations-preview__manage-all")).click();
	        Thread.sleep(5000);
	        
	        //driver.findElement(By.partialLinkText("Dese")).click();
	        driver.findElement(By.id("contact-select-checkbox")).click();
	       // driver.findElement(By.xpath("contact-select-checkbox")).click();
	         //driver.findElement(By.className("ember-checkbox")).click();
	       
	         System.out.println("checkbox clicked");
		//}
		//catch (Exception e) 
		//{
			//System.out.print(e);
			//System.out.println("Invalid login credentials, so couldn't continue with invitation");
			//driver.quit();
		}
	}
//}
	

