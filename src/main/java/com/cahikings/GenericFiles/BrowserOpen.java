package com.cahikings.GenericFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.chaikings.EnvironmentData.EnvironmentsData;

import cucumber.api.Scenario;

public class BrowserOpen {
	
	public static WebDriver driver;
	public static Properties prop;
	
	//protected static EnvironmentsData data =new EnvironmentsData(); //getting paths of drivers
	
	public BrowserOpen() throws IOException {
		prop=new Properties();
	FileInputStream fis=new FileInputStream(EnvironmentsData.Xpath_property);
	prop.load(fis);
	}
	
	
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
	driver.manage().window().setSize(new Dimension(1600, 880));
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}
	
//	public static void tearDown(Scenario scenario) throws IOException
//	{
//		if (scenario.isFailed()) {
//			try {
//				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//				File screenshot_with_scenario_name = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//				FileUtils.copyFile(screenshot_with_scenario_name, 
//						new File("./target/test-report/" + scenario.getName() + ".png"));
//				System.out.println(scenario.getName());
//				scenario.embed(screenshot, "image/png");				
// 			}catch (WebDriverException e) {
// 				System.out.println(e.getMessage()); 				
// 			}
//		}
//		driver.close();
//	}
}
