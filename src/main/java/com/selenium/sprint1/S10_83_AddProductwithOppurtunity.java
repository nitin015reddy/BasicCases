package com.selenium.sprint1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class S10_83_AddProductwithOppurtunity {

	@Test 
	  public void AddProdtOppty() throws InterruptedException {
			
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
	        Thread.sleep(2000);	  
	        driver.findElement(By.id("Login")).click();
	        System.out.println("Click login button");
	        
	        System.out.println("User logged in successfully");  
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)) ;
	        System.out.println(driver.getTitle());
	        
	        driver.findElement(By.xpath("//div[starts-with(@class,'slds-icon-waffle')]")).click();
	        System.out.println("Clicked on toggle menu button from the left corner");
	        Thread.sleep(5000);
	        
	        driver.findElement(By.xpath("//button[contains(@aria-label,'View All Applications')]")).click();
	        System.out.println("Click view All ");
	        Thread.sleep(8000);
	        
	        driver.findElement(By.xpath("//p[(text()='Content')]")).click();
	        System.out.println("Click content ");
	        Thread.sleep(5000);
	        
	        driver.findElement(By.xpath("//span[(text()='View All Key Deals')]")).click();
	        System.out.println("Click view all key deals ");
	        Thread.sleep(5000);
	   //     click dropdown and select all oppurtunities      
	        driver.findElement(By.xpath("//lightning-icon[@icon-name=\"utility:chevrondown\"]")).click();  
	        driver.findElement(By.xpath("//span([text()='All Opportunities')]")).click();
	        Thread.sleep(5000);
	        
	        WebElement inputField = driver.findElement(By.xpath("//input[contains(@placeholder,'Search this list...')]"));
	          inputField.sendKeys("Nitin");	  
	          inputField.sendKeys(Keys.TAB);
	          Thread.sleep(2000);
	          WebElement av=driver.findElement(By.xpath("//table/tbody/tr/td[3]/span/a"));
	    	  String Actualvalue= av.getText();
	    	  System.out.println(Actualvalue);
	    	  av.click();
	    	  Thread.sleep(2000);
	    //	  Click on  dropdown of Products under Related and select Add Products
	    	  
	    //	  10. Click on List Price to sort the result and select the highest priced product
	    	  
	    //	  11. Click Next and give product Quantity as 560, click Save
	    	  
	    //	  12. Verrify the Sales Price and Product Name                  
	}
}
