package com.funtionaltesting;
import java.io.IOException;
//import java.util.concurrent.TimeUnit;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.openqa.selenium.By;
//import org.openqa.selenium.chrome.ChromeDriver;

public class Base {

	/*
	public static void main(String[] args) throws InvalidFormatException, IOException, InterruptedException 
	{
		ReadXL obj = new ReadXL();
		String filePath = "C:\\Users\\ricky\\eclipse-workspace\\funtionaltesting\\src\\main\\resources\\LinkedinTestdata.xlsx";
		String sheetName = "LoginTest";
		
		Object[][] data = obj.readExcel(filePath, sheetName);
				
		int i, count;
		count= data.length;
		
		for (i=0; i<count;i++)
		{
			ChromeDriver driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.get("https://www.linkedin.com");
			driver.findElement(By.id("login-email")).sendKeys(data[i][0].toString());
	        driver.findElement(By.id("login-password")).sendKeys(data[i][1].toString());
	        driver.findElement(By.id("login-submit")).submit();
	      
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      
	        driver.findElement(By.id("nav-settings__dropdown")).click();
	        Thread.sleep(3000);
	    
	        driver.findElement(By.className("block")).click();
	        driver.close(); 

		}
	}
	*/
	public static Object[][] readExcel(String filePath, String sheetName) throws InvalidFormatException, IOException, InterruptedException
	{
		Object[][] data = ReadXL.readExcel(filePath, sheetName);
		return data;
	}

}
