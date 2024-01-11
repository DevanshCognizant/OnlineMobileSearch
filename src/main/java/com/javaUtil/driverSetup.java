package com.javaUtil;
//This file is used to setup webdriver and navigate to the desired link
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
public class driverSetup {
	
		public static WebDriver getWebDriver(String bName) {
		
		if(bName.equals("Chrome"))
		return new ChromeDriver();
		// All the functions to be performed in Chrome browser
		if(bName.equals("Edge"))
			return new EdgeDriver();
		// All the functions to be performed in Edge browser
		else
			return new ChromeDriver();
		//Automatically open Chrome browser
		}
}