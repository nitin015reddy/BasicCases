package com.selenium.sprint1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.data.datamanagement.ExcelUtility;

public class S10_31EditCase {
	

	@DataProvider(name="GetTestData")
	public String [][] getExcelData(){
		return ExcelUtility.readExcelValue("S10_31EditCase");
	}
	


	@Test(dataProvider= "GetTestData")
	  public void EditTask(String url,String uname,String pwd,String caseowner) throws InterruptedException {
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			ChromeDriver driver = new ChromeDriver(options);
	      //  String url = "https://login.salesforce.com/";
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
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)) ;
	        
	        driver.findElement(By.xpath("//button[contains(@aria-label,'View All Applications')]")).click();
	        System.out.println("Click view All ");
	        Thread.sleep(5000);
	        
	        driver.findElement(By.xpath("//p[(text()='Sales')]")).click();
	        System.out.println("Click sales ");
	        Thread.sleep(5000);
			
	        
	        System.out.println("Click on cases under more");
	        driver.findElement(By.xpath("//span[contains(text(),'More')]")).click();
	        
	        WebElement cases = driver.findElement(By.xpath("//a/span/span[contains(text(),'Cases')]"));
	        JavascriptExecutor js = (JavascriptExecutor)driver;		
            js.executeScript("arguments[0].click();", cases);
	        Thread.sleep(3000);
	        
	        System.out.println("view cases");
	        List<WebElement> list1 =driver.findElements(By.xpath("//table/tbody/tr/td[6]"));
	        
	        for(WebElement element:list1 ){
	            if(element.getText().equals(caseowner))
	            {
	            	driver.findElement(By.xpath("//div[contains(@class,'forceVirtualActionMarker forceVirtualAction')]")).click();
	            	Thread.sleep(5000);
	            	
	            	driver.findElement(By.xpath("//a[contains(@title,'Edit')]")).click();
	    	        Thread.sleep(3000);
	    	        
	    	        System.out.println("Edit fields in case");
	    	        
	    	
	    	        driver.findElement(By.xpath("//button[contains(@aria-label,'Status')]")).click();
	    	        driver.findElement(By.xpath("//span[contains(@title,'Working')]")).click();
	    	        System.out.println(" Update Status as Working");

	    	        driver.findElement(By.xpath("//button[contains(@aria-label,'Priority')]")).click();
	    	        driver.findElement(By.xpath("//span[contains(@title,'Low')]")).click();
	    	        System.out.println("Update Priority to low");

	    	        driver.findElement(By.xpath("//button[contains(@aria-label,'Case Origin')]")).click();
	    	        driver.findElement(By.xpath("//span[contains(@title,'Phone')]")).click();
	    	        System.out.println("Update Case Origin as Phone");
	    	        Thread.sleep(3000);
	    	        
	    	        WebElement elem = driver.findElement(By.xpath("//button[contains(@aria-label,'SLA Violation')]"));
	    	        js.executeScript("arguments[0].scrollIntoView(true);", elem);
	    	        
	    	      driver.findElement(By.xpath("//button[contains(@aria-label,'SLA Violation')]")).click();
	    	      driver.findElement(By.xpath("//span[(@title='No')]")).click();
	    	      System.out.println("Update SLA violation to No");
	    	      Thread.sleep(5000);
	 //   	        driver.findElement(By.xpath("//button[contains(@name,'SaveEdit')]")).click();	
	    	      driver.findElement(By.xpath("//button[contains(@name,'Cancel')]")).click();
	    	        Thread.sleep(5000);
	//    	        System.out.println("clicked Save");    	
	            }            
	        }
	        
	    
	        String sColValue = "NKarr";
           String Actualvalue=null;
	    	//First loop will find Nkarr value
	    	for (int i=1;i<10;i++){
	    		String sValue = null;
	    		sValue = driver.findElement(By.xpath("//table/tbody/tr/td["+i+"]")).getText();
	    		if(sValue.equals(sColValue)){
	    				 Actualvalue= driver.findElement(By.xpath("//table/tbody/tr/td["+(i-2)+"]")).getText();
	    				System.out.println(Actualvalue);	    			
	    		break;
	    	}
	    }
	    	
	    	
	    	String Expectedvalue="Working";
	      Assert.assertEquals(Actualvalue,Expectedvalue);
	        System.out.println("Case is Edited Successfully  and Status is working");
	        
	}
	        
	}


