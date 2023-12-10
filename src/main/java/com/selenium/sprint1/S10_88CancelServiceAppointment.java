package com.selenium.sprint1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class S10_88CancelServiceAppointment {

@Test
public void CancelServiceAppointment() throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
        String url = "https://login.salesforce.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;

        driver.findElement(By.id("username")).sendKeys("nitink@testleaf.com");;  
        System.out.println("Enter the username");
        driver.findElement(By.id("password")).sendKeys("Chicago@1518");
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
        
        //enter Appointments in search bar
        driver.findElement(By.xpath("//input[contains(@placeholder,'Search apps or items...')]")).sendKeys("Service Appointments");
        Thread.sleep(1000);
        //click Appointments link    
        WebElement Appointments= driver.findElement(By.xpath("//p/mark[(text()='Service Appointments')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;		
        js.executeScript("arguments[0].click();", Appointments);
        Thread.sleep(4000);
        System.out.println("Service Appointments link clicked");
        
        
       //check on this one ,data in hears for appointment number
        List<WebElement> list1 =driver.findElements(By.xpath("//table/tbody/tr/th"));
        
        for(WebElement element:list1 ){
            if(element.getText().equals("SA-0001"))
            {
            	driver.findElement(By.xpath("//table/tbody/tr/td[6]/span/div")).click();
            	Thread.sleep(5000);
            	
            	driver.findElement(By.xpath("//a[contains(@title,'Cancel Service Appointment')]")).click();
    	        Thread.sleep(3000);
    	        
    	        System.out.println("Cancel clicked");
    	 //for go back in pop code     //button[contains(text(),'Go Back')]
    	  //      driver.findElement(By.xpath("//button[contains(text(),'Confirm Cancellation')]")).click();
    	        driver.findElement(By.xpath("//button[contains(text(),'Go Back')]")).click();
    	        Thread.sleep(5000);
            }
        }
        
        
     //   Pending code for this cancel service appointment     
          WebElement inputField = driver.findElement(By.xpath("//input[contains(@placeholder,'Search this list...')]"));
          inputField.sendKeys("SA-0001");	  
          inputField.sendKeys(Keys.TAB);
          Thread.sleep(2000);
    	  String Actualvalue= driver.findElement(By.xpath("//table/tbody/tr/td[3]")).getText();
    	  System.out.println(Actualvalue);	    			  	
    	  String Expectedvalue="Cancelled";
          Assert.assertEquals(Actualvalue,Expectedvalue);
          System.out.println("Case is Edited Successfully  and Status is working");
        
}
}
