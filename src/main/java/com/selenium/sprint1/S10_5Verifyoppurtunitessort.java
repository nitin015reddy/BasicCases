package com.selenium.sprint1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class S10_5Verifyoppurtunitessort {

	
	@Test 
  public void VerifyOpportunities() throws InterruptedException {
		
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)) ;
        
        driver.findElement(By.xpath("//button[contains(@aria-label,'View All Applications')]")).click();
        System.out.println("Click view All ");
        Thread.sleep(8000);
        
        driver.findElement(By.xpath("//p[(text()='Sales')]")).click();
        System.out.println("Click sales ");
        Thread.sleep(5000);
		
        
        System.out.println("Click on Opportunity tab");
        WebElement opportunity = driver.findElement(By.xpath("//span[(text()='Opportunities')][1]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;		
        js.executeScript("arguments[0].click();", opportunity);
        Thread.sleep(5000);
        
        System.out.println("Click on Table view");
        driver.findElement(By.xpath("//button[contains(@title,'Select list display')]")).click();
         driver.findElement(By.xpath("//a/span[text()='Table']")).click();
        Thread.sleep(2000);
        
     
        //store table values of close date before sort
        ArrayList<String> obtainedList1 = new ArrayList<String>(); 
        List<WebElement> list1= driver.findElements(By.xpath("//table/tbody/tr/td[6]//span/span[contains(@class,'uiOutputDate')]"));
        for(WebElement we:list1){
           obtainedList1.add(we.getText());
           System.out.println("success1");
        }
        ArrayList<String> sortedList = new ArrayList<String>();   
        for(String s:obtainedList1){
        sortedList.add(s);
        }
        Collections.sort(sortedList);

      //table/tbody/tr/td[6]//span/span[contains(@class,'uiOutputDate')]
        
        WebElement sort = driver.findElement(By.xpath("//span[contains(@title,'Close Date')]"));		
        js.executeScript("arguments[0].click();", sort);       
        Thread.sleep(5000); 
        js.executeScript("arguments[0].click();", sort);
        System.out.println("Sort the Opportunities by Close Date in ascending order");
        Thread.sleep(5000);
        
        
        Actions builder = new Actions(driver);
        Action click = builder.doubleClick().build();
        click.perform();
        Thread.sleep(3000);
        //store table values of close date after sort
        ArrayList<String> obtainedList2 = new ArrayList<String>(); 
        List<WebElement> list2= driver.findElements(By.xpath("//table/tbody/tr/td[6]//span/span[contains(@class,'uiOutputDate')]"));
        for(WebElement we:list2){
           obtainedList2.add(we.getText());
           System.out.println("success2");
        }
        
        
        //compare two lists
        Assert.assertTrue(obtainedList2.equals(sortedList));
            
        
        System.out.println("Verify the Opportunities displayed in ascending order by Close date");
          driver.close();
        
	}
	
}
