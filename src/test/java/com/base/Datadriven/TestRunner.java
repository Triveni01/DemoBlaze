package com.base.Datadriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.utility.ReadExcelData;

public class TestRunner {

	public static void main (String [] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.facebook.com");
		
	String userName = ReadExcelData.readCellData(3, 2);
	driver.findElement(By.id("email")).sendKeys(userName);
	
	String password = ReadExcelData.readCellData(2, 0);
	driver.findElement(By.id("pass")).sendKeys(password);
		
		
	}
	
}
