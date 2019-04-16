package com.funtionaltesting;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PostImageMsg extends Base {

	public String filePath = "C:\\Users\\ricky\\eclipse-workspace\\funtionaltesting\\src\\main\\resources\\LinkedinTestdata.xlsx";
	public String sheetName = "PostImageMsg";
	public static Object[][] data;

	@DataProvider(name = "excelData")
	public Object[][] readExcel() throws InvalidFormatException, IOException, InterruptedException 
	{
		
		data = Base.readExcel(filePath, sheetName);
		return data;
	}

	@Test(dataProvider = "excelData", priority = 4)	
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
			
			
			driver.findElement(By.className("share-box__open")).click();
		    
	        driver.findElement(By.className("mentions-texteditor__contenteditable")).sendKeys(Message);
	       
	        //driver.findElement(By.className("share-media-button__label")).click();
	     
	        /*
	        Robot robot = new Robot();
	        StringSelection stringselection = new StringSelection("C:\\Users\\ricky\\OneDrive\\Desktop\\dubai.jpg"); 
	     
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, null);	          
	        robot.setAutoDelay(3000);	
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.setAutoDelay(2000);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.setAutoDelay(3000);
*/
	        driver.findElement(By.className("share-actions__primary-action")).click();
	        //span[@text()="Next"]
	        //driver.findElement(By.className("artdeco-button__text")).click();
	        //driver.findElement(By.xpath("span[@text='Next']")).click();
	       // System.out.println("next button");
	   	        
	       // driver.findElement(By.id("share.post34")).click();
	       // System.out.println("post button");
		}
		catch (Exception e) 
		{
			System.out.print(e);
			System.out.println("Invalid login credentials, so couldn't continue with Post");
			driver.quit();
		}
	}
}