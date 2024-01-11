/*Main file of the project in which the object of both DriverSetup and writeExcelData are called
 * The project is based on searching "Mobile Smartphones under 30000" on Amazon.in
 * After searching the search is sorted to Newest Arrivals options and it is checked whether the button is clicked or not
 * Then some values such as No. of pages, No. of items and No. of sorting options are displayed in console and stored in excel file
 * */
package com.MiniProject;
import com.javaUtil.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class miniProject {
	static WebDriver driver;	
	static int size;
	static String text;
	static List<WebElement> sortOptions = new ArrayList<WebElement>(); // To store the options
	public WebDriver createDriver(String bName) {
		//Driver is created
	    driver = driverSetup.getWebDriver(bName);
	    driver.manage().window().maximize(); // To maximize the window
		driver.get("http://www.amazon.in/"); // To naviagate to the desired website
		return driver;
	}
	
	public void Search(String string) {
		//String is passed to search bar and search button is clicked
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(string);
		driver.findElement(By.id("nav-search-submit-button")).click();
	}
	
	public void clickdrp() {
		//Sorting is changed from Featured to Newest Arrivals
		driver.findElement(By.className("a-dropdown-label")).click();
		driver.findElement(By.id("s-result-sort-select_4")).click();
	}
	
	public int options() {
		//No. of sorting options are stored 
		sortOptions = driver.findElements(By.className("a-dropdown-link"));
		int n = sortOptions.size();
		return n;
	}
	
	public String printLine() {
		//A string like 1-24 of over 1,000 results for "mobile smartphones under 30000" is displayed
		text = driver.findElement(By.className("sg-col-inner")).getText();
		//A new string is created to find number of items 
		String line[] = text.split(" ");
		return line[3];
	}
	
	public String check() {
		// A value is stored in txt string which stores "Newest Arrivals" and checked whether the button is clicked or not
		String txt = driver.findElement(By.className("a-dropdown-prompt")).getText();
		if(txt.equals("Newest Arrivals")) {
			return "true";
		}
		else
		return "false";
	}
	
	public String printPages() {
		
		//A value is stored in elementText which consists number of pages 
		String elementText = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/span[1]/div[1]/div[20]/div/div/span/span[4]")).getText();																												
		return elementText;
	}
	
	public void printOptions() {
		
		// All the Sorting options are displayed
		int i;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String[] mc = new String[5];
		for(i=0;i<size;i++) {
			mc[i] = "//*[@id=\'s-result-sort-select_"+i+"']";
		String elementText = (String) js.executeScript("return arguments[0].innerText;", driver.findElement(By.xpath(mc[i])));
		System.out.println(elementText);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		miniProject mp = new miniProject();		//Object is created
		Scanner sc = new Scanner(System.in); 	// Scanner class is used to get input
		System.out.println("Enter 1 for Chrome\nEnter 2 for Edge");
		int a = sc.nextInt();
		switch(a) {
		case 1:
			mp.createDriver("Chrome");
		break;
		case 2:
			mp.createDriver("Edge");
		break;
		default:
			System.out.println("Enter the correct value"); 		//If user enters incorrect number then code will not work
			return;
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		mp.Search("mobile smartphones under 30000");
		//Different functions are called to  
		writeExcelData.writeExcel();
		writeExcelData.createRow1();
		mp.clickdrp();	
		size = mp.options();
		writeExcelData.showOptions(size);
		String line = mp.printLine();
		writeExcelData.showItems(line);
		System.out.println(text);
		String check = mp.check();
		writeExcelData.Check(check);
		Thread.sleep(1000);
		String pages = mp.printPages();
		writeExcelData.showPages(pages);
		System.out.println("No. of pages: "+mp.printPages());
		System.out.println("No. of options: "+size);
		mp.printOptions();
		writeExcelData.Close();
		driver.quit();
	}
}