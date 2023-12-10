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

public class S10_34CreateTask {

@DataProvider(name="GetTestData")
	public String [][] getExcelData(){
		return ExcelUtility.readExcelValue("S10_34CreateTask");
	}
	
	
	@Test(dataProvider= "GetTestData") 
  public void CreateTask(String url,String uname,String pwd,String field,String sub) throws InterruptedException {
		
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
        
        //enter Tasks in search bar
        driver.findElement(By.xpath("//input[contains(@placeholder,'Search apps or items...')]")).sendKeys(field);
        Thread.sleep(1000);
        //click on Tasks link    
       WebElement task= driver.findElement(By.xpath("//a[contains(@data-label,'Tasks')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;		
        js.executeScript("arguments[0].click();", task);
        Thread.sleep(4000);
        System.out.println("Task link clicked");
        
        //click on drop down and click on new task
        driver.findElement(By.xpath("//a[contains(@title,'Show one more action')]")).click();
        driver.findElement(By.xpath("//a[contains(@title,'New Task')]")).click();
        Thread.sleep(3000);
        System.out.println("Click new task");
        
        //Enter subject as "Bootcamp" 
        driver.findElement(By.xpath("//input[contains(@class,'slds-combobox__input slds-input')]")).sendKeys(sub);
        
        
      //Select status as 'Waiting on someone else'
        driver.findElement(By.xpath("//div/a[contains(text(),'Not Started')]")).click();
        driver.findElement(By.xpath("//a[contains(@title,'Waiting on someone else')]")).click();
        
      //click on Save
        driver.findElement(By.xpath("//div/button[3][contains(@title,'Save')]")).click();
        
        Thread.sleep(5000);
        
        System.out.println("Saved new task");
        
       //verify task message created successfully
        String Actualmessage =driver.findElement(By.xpath("//div[contains(@title,'Bootcamp')]")).getText();
        
        String Expectedmessage="Bootcamp";
        
        Assert.assertEquals(Actualmessage, Expectedmessage);
        
        System.out.println("Task created successfully");
       
        driver.close();
	}
}
