package com.selenium.sprint1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class S10_52servicesortorder {
	@Test 
	  public void ServicesSort() throws InterruptedException {
			
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
	        
	      // Click on the sliding icon until ""See System Status"" is displayed
	        String originalWindow = driver.getWindowHandle(); 
	        List<WebElement> ele = driver.findElements(By.xpath("//article[@class='onesetupHelpTile']//span[@class='text uiOutputText']"));
	    	int size = ele.size();
	    	System.out.println(size);
	    	WebElement clickEle = driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral rightArrowButton uiButton']"));
	    	Thread.sleep(3000);
	    	String text;
	    			for (int i = 0; i < size; i++) {
	    				Thread.sleep(3000);
	    				text = ele.get(i).getText();
	    				System.out.println(text);
	    				if(i==2) {
	    					clickEle.click();
	    				}else if (i==5) {
	    					clickEle.click();
	    				}else if(text.equals("See System Status")) {
	    					Thread.sleep(3000);	    			 
	    					driver.findElement(By.xpath("(//button[@title='Get Started'])[last()]")).click();
	    				}	    				
	    			}
	          //  Clicked on Get Started and switch to new window
	    			//Store the ID of the original window    		         	   	    
	    		  //Loop through until we find a new window handle
	    		    for (String windowHandle : driver.getWindowHandles()) {
	    		        if(!originalWindow.contentEquals(windowHandle)) {
	    		            driver.switchTo().window(windowHandle);
	    		            System.out.println(driver.getTitle());
	    		            break;
	    		        }
	    		    }
	   	      //   Click confirm on the redirecting page and navigate to SalesForce Trust new Window.
	    	 	    driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
	    		    Thread.sleep(5000);
	      
             // Select Trust Compliance from the dropdown
	    		    driver.findElement(By.xpath("//a[text()='Compliance']")).click();
	    		    Thread.sleep(3000);	    		  
	      
               //Click and navigate to Services tab
	    		    driver.findElement(By.xpath("//a[text()='Services']")).click();
	    		    Thread.sleep(5000);
	           	      
              // Verify the Services are displayed in alphabetical order" 	    		    
	   		    List<WebElement> ele1 = driver.findElements(By.xpath("//div/div/span[contains(@style,'box-shadow:')]/span"));
	    	   	int size1 = ele1.size();
	    	   	System.out.println(size1);
	  /*  	   	String text1;
	    	   	for (int j = 0; j < size1; j++) {
    				Thread.sleep(1000);
    				text1 = ele1.get(j).getText();
    				System.out.println(text1);
                }  */
	}
}
