package com.cahikings.GenericFiles;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.chaikings.EnvironmentData.EnvironmentsData;

public class BrowserOpen {
	
	public static WebDriver driver;
	
	static EnvironmentsData data =new EnvironmentsData(); //getting paths of drivers
	
	public static void initialization() throws InterruptedException 
	{
	String Browsername=EnvironmentsData.browser;
	
	if(Browsername.equals("chrome"))
	{
		System.setProperty(EnvironmentsData.Chrome_key, EnvironmentsData.Chrome_value);
		Thread.sleep(3000);
		driver =new ChromeDriver();
	}else if (Browsername.equals("FF"))
	{
		System.setProperty(EnvironmentsData.FF_key, EnvironmentsData.FF_value);
		Thread.sleep(3000);
		driver = new FirefoxDriver();
	}
	
	driver.get(EnvironmentsData.URL);
	Thread.sleep(2000);
	//driver.manage().window().maximize();
	driver.manage().window().setSize(new Dimension(1600, 900));
	
	}
}
