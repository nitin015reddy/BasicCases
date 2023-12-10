package com.selenium.sprint1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class S10_106MandatoryFieldsCreatePayment {

@Test	
public void MandatoryFieldForCreatePayment() throws InterruptedException {
		
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
        
        //enter Payments in search bar
        driver.findElement(By.xpath("//input[contains(@placeholder,'Search apps or items...')]")).sendKeys("Payments");
        Thread.sleep(1000);
        //click onPayments link    
       WebElement payment= driver.findElement(By.xpath("//ul/li[1]/one-app-launcher-tab-item/a/span/lightning-formatted-rich-text/span/p/mark[(text()='Payments')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;		
        js.executeScript("arguments[0].click();", payment);
        Thread.sleep(4000);
        System.out.println("Payments link clicked");
        
        
        //click on new button
        driver.findElement(By.xpath("//div[(text()='New')]")).click();
        Thread.sleep(4000);
        System.out.println("Click new button");
        
        
        //click on save 
        driver.findElement(By.xpath("//div/button[3][contains(@title,'Save')]")).click();      
         Thread.sleep(5000);        
         System.out.println("Saved new payment");
       //verify error for mandatory fields
         String Actualmessage=   driver.findElement(By.xpath("//li[contains(text(),'Complete')]")).getText();
         System.out.println(Actualmessage);
         String Expectedmessage="Complete this field.";
         
         Assert.assertEquals(Actualmessage, Expectedmessage);  
         driver.close();
         
}
}