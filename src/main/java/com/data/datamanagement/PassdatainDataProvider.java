package com.data.datamanagement;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PassdatainDataProvider {
	
	@DataProvider(name="GetTestData")
	public String [][] getExcelData(){
		return ExcelUtility.readExcelValue("S10_34CreateTask");
	}

	@Test(dataProvider= "GetTestData")
	public void testExcelData(String url,String uname,String pwd){
		System.out.println(url+" "+uname+" "+ pwd);

		
	}
	
}
