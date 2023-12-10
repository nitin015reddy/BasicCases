package com.data.datamanagement;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PassDatainParameters {

	
	@BeforeTest
	@Parameters({"url"})
	public void launch(String url){
		System.out.println(url);		
	}
	
	@Test
	@Parameters({"username","password"})
	public void testparameters(String uname,String pwd){
		System.out.println(uname+""+ pwd);		
	}
}
