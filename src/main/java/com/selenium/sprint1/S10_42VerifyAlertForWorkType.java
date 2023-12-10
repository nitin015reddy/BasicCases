package com.selenium.sprint1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.data.datamanagement.ExcelUtility;

public class S10_42VerifyAlertForWorkType {

	@DataProvider(name="GetTestData")
	public String [][] getExcelData(){
		return ExcelUtility.readExcelValue("S10_42VerifyAlertForWorkType");
	}
	
	
	@Test(dataProvider= "GetTestData")
  public void MandatoryFieldForWorkType(String url,String uname,String pwd,String field,String sub) throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
       // String url = "https://login.salesforce.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;

        driver.findElement(By.id("username")).sendKeys(uname);;  
        System.out.println("Enter the username");
       driver.findElement(By.id("password")).sendKeys(pwd);
        System.out.println("enter the password");
        driver.findElement(By.id("Login")).click();
        System.out.println("Click login button");
        
        System.out.println("User logged in successfully");  
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
        System.out.println(driver.getTitle());
        
        driver.findElement(By.xpath("//div[starts-with(@class,'slds-icon-waffle')]")).click();
        System.out.println("Clicked on toggle menu button from the left corner");
       Thread.sleep(2000);
        
        driver.findElement(By.xpath("//button[contains(@aria-label,'View All Applications')]")).click();
        System.out.println("Click view All ");
        Thread.sleep(5000);
        
        //enter Work types in search bar
        driver.findElement(By.xpath("//input[contains(@placeholder,'Search apps or items...')]")).sendKeys(field);
        Thread.sleep(1000);
        //click on work types link    
       WebElement worktype= driver.findElement(By.xpath("//a[contains(@data-label,'Work Types')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;		
        js.executeScript("arguments[0].click();", worktype);
        Thread.sleep(4000);
        System.out.println("work types link clicked");
        
        
        //click on new button
        driver.findElement(By.xpath("//div[(text()='New')]")).click();
        Thread.sleep(4000);
        System.out.println("Click new button");
        
      //Enter subject as "Bootcamp" 
        driver.findElement(By.xpath("//input[contains(@maxlength,'255')]")).sendKeys(sub);
        
        
        //click on save 
       driver.findElement(By.xpath("//div/button[3][contains(@title,'Save')]")).click();      
        Thread.sleep(5000);        
        System.out.println("Saved new worktype");
        
        
        //verify error for mandatory fields
     String Actualmessage=   driver.findElement(By.xpath("//li[contains(text(),'Complete this field.')]")).getText();
     System.out.println(Actualmessage);
     String Expectedmessage="Complete this field.";
     
     Assert.assertEquals(Actualmessage, Expectedmessage);  
     driver.close();
}
}