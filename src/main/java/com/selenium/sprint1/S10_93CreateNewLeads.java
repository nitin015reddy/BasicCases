package com.selenium.sprint1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class S10_93CreateNewLeads {
	
	
	
	   @Test 
	  public void CreateNewLeads() throws InterruptedException {
			
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
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)) ;
	        System.out.println(driver.getTitle());
	        
	        driver.findElement(By.xpath("//div[starts-with(@class,'slds-icon-waffle')]")).click();
	        System.out.println("Clicked on toggle menu button from the left corner");
	        Thread.sleep(2000);
	        
	        driver.findElement(By.xpath("//button[contains(@aria-label,'View All Applications')]")).click();
	        System.out.println("Click view All ");
	        Thread.sleep(5000);
	        
	        driver.findElement(By.xpath("//p[(text()='Sales')]")).click();
	        System.out.println("Click sales ");
	        Thread.sleep(5000);
			
	        
	        System.out.println("Click on Leads tab");
	        WebElement leads = driver.findElement(By.xpath("//span[(text()='Leads')][1]"));
	        JavascriptExecutor js = (JavascriptExecutor)driver;		
	        js.executeScript("arguments[0].click();", leads);
	        Thread.sleep(5000);

	        driver.findElement(By.xpath("//button[contains(text(),'New')]")).click();
	        Thread.sleep(5000);
	        System.out.println("Click new leads");
	        
	        
	      //click on salutation dropdown and select value
	        driver.findElement(By.xpath("//button[contains(@aria-label,'Salutation')]")).click();
	        driver.findElement(By.xpath("//span[contains(@title,'Mr.')]")).click();
	        Thread.sleep(3000);
	        
	        //Enter first name
	        driver.findElement(By.xpath("//input[contains(@placeholder,'First Name')]")).sendKeys("Nitin");
	        //Enter last time 
	        driver.findElement(By.xpath("//input[contains(@placeholder,'Last Name')]")).sendKeys("Karri");
	       //Enter company 
	        driver.findElement(By.xpath("//input[contains(@name,'Company')]")).sendKeys("CTS");
	        
	        WebElement elem = driver.findElement(By.xpath("//button[contains(@aria-label,'Open - Not')]"));
	        js.executeScript("arguments[0].scrollIntoView(true);", elem);
	        //click on lead status dropdown and select value
	        elem.click();
	        driver.findElement(By.xpath("//span/span[contains(@title,'Working - Contacted')]")).click();
	        Thread.sleep(1000);
	     
	        
	        //click on Save
	        driver.findElement(By.xpath("//button[contains(@name,'SaveEdit')]")).click();
	        
	        Thread.sleep(5000);
	        
	        System.out.println("Saved new lead");
	        
	       //verify lead message created successfully
	        String Actualmessage =driver.findElement(By.xpath("//div[contains(@title,'Lead')]")).getText();	        
	        String Expectedmessage="Lead";	        
	        Assert.assertEquals(Actualmessage, Expectedmessage);	        
	        System.out.println("Lead created successfully");
	       
	        driver.close();
	                
}
}