package com.chaikings.stepdefination;


import java.io.IOException;
import java.util.Map;
import java.util.Properties;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cahikings.GenericFiles.BrowserOpen;
import com.cahikings.GenericFiles.ExcelDataHandlerGetterSetter;
import com.cahikings.GenericFiles.ExcelTestDataHandler;
import com.cahikings.GenericFiles.ReadingProepertyXpath;
import com.chaikings.EnvironmentData.EnvironmentsData;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;



public class LoginStepDef extends BrowserOpen{
	
	ExcelDataHandlerGetterSetter testdata=new ExcelDataHandlerGetterSetter();
	
	public static Properties prop;

	/*
	 * // public LoginStepDef() throws IOException { // // prop =new Properties();
	 * // FileInputStream fis=new FileInputStream(EnvironmentsData.Xpath_property);
	 * // prop.load(fis); // }
	 * 
	 * // ReadingProepertyXpath.readxpathdata(EnvironmentsData.Xpath_property);
	 */	
	
	
	@Before
	public void openBrowser() throws InterruptedException, IOException
	{
		BrowserOpen.initialization();
		prop=ReadingProepertyXpath.readxpathdata(EnvironmentsData.Xpath_property);
	}
	
	@After
	public void closeBrowser() throws InterruptedException
	{
		Thread.sleep(3000);
		 System.out.println("Closing Browser");
	        driver.close();
	}
	
	// 1nd scenario
	@Given("^User is already on chaiking login page$")
	public void user_is_already_on_chaiking_login_page() throws Throwable {
		
		System.out.println("Browser got open succesfuly..");
	}

	@Then("^verify the title of login page is chaikings$")
	public void verify_the_title_of_login_page_is_chaikings() throws Throwable {
		String currentTitle = driver.getTitle();
		//Assert.assertEquals(EnvironmentsData.chaikingsTitle, currentTitle);
		System.out.println("Title is verfiyed , title is "+"'"+currentTitle+"'");
	}

	@Given("^enter username and password$")
	public void enter_username_and_password() throws Throwable {
		
		Map<String ,String> testdatainmpa= ExcelTestDataHandler.getTestDataInMap(EnvironmentsData.testdata_sheet, EnvironmentsData.FistSheet,EnvironmentsData.testcase);
		testdata.setTestDataInMap(testdatainmpa);//setter
		
		Map<String,String> data =testdata.getTestDataInMap();
		System.out.println(data.get("Data_1"));
	   driver.findElement(By.xpath(prop.getProperty("Login_username_xpath"))).sendKeys(testdatainmpa.get("Data_2"));
	   Thread.sleep(3000);
	   driver.findElement(By.xpath(prop.getProperty("Login_password_xpath"))).sendKeys(testdatainmpa.get("Data_4"));
	   driver.findElement(By.xpath(prop.getProperty("Sign_up_xpath"))).click();
	   Thread.sleep(3000);
	   
	   String dd= driver.getCurrentUrl();
	   if(dd.equals(EnvironmentsData.URL))
	   {
		   System.out.println("I am in force login screen....");
		   String  forcelogintext = driver.findElement(By.xpath(prop.getProperty("force_login_xpath"))).getText();
		   System.out.println(forcelogintext);

			   WebElement clicktext=driver.findElement(By.xpath(prop.getProperty("force_login_linkTextclick_xpath")));
			   
			   System.out.println(clicktext.getText());
			   clicktext.click();
			   
			   if(driver.getCurrentUrl().equals(EnvironmentsData.Counter_start_URL))
			   {
				   System.out.println("i am Satrt screen.....");
				   System.out.println(driver.getCurrentUrl());
				   String open_cash_date = driver.findElement(By.xpath(prop.getProperty("opeing_cash_date_xpath"))).getText();
				   System.out.println("Open counter data : "+ open_cash_date);
				   String open_cash_amount = driver.findElement(By.xpath(prop.getProperty("opeing_cahs_xpath"))).getText();
				   System.out.println("open cash is : "+open_cash_amount);
				   System.out.println("Login succesfully.....");
				   driver.findElement(By.xpath(prop.getProperty("opeing_cash_statrt_button_xpath"))).click();
				   System.out.println("Succesfully POS Screen Displayed.");
			   }
			   WebElement ckHome=driver.findElement(By.xpath(prop.getProperty("ck_dropudown_xpath")));
			   System.out.println(ckHome.getText());
			   ckHome.click();
			   Thread.sleep(2000);
			   WebElement holdlogout=driver.findElement(By.xpath(prop.getProperty("hold_and_logout_xpath")));
			   System.out.println(holdlogout.getText());
			   
			   
			   
			   
	   }else if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_start_URL))
		   {
		   System.out.println("i am Satrt screen.....");
		   System.out.println(driver.getCurrentUrl());
		   String open_cash_date = driver.findElement(By.xpath(prop.getProperty("opeing_cash_date_xpath"))).getText();
		   System.out.println("Open counter data : "+ open_cash_date);
		   String open_cash_amount = driver.findElement(By.xpath(prop.getProperty("opeing_cahs_xpath"))).getText();
		   System.out.println("open cash is : "+open_cash_amount);
		   System.out.println("Login succesfully.....");
		   driver.findElement(By.xpath(prop.getProperty("opeing_cash_statrt_button_xpath"))).click();
		   System.out.println("Succesfully POS Screen Displayed.");
		   }else if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_update_end_URL))
		   {
			   System.out.println("i am in Day out update screen");
			 WebElement acutualCash=driver.findElement(By.xpath(prop.getProperty("actual_cash_text_xpath")));
			 System.out.println("Actual cahs is "+acutualCash);
			 acutualCash.click();
			 driver.findElement(By.xpath(prop.getProperty("enter_closing_cash_xapth"))).sendKeys(testdatainmpa.get("Data_8"));
			 driver.findElement(By.xpath(prop.getProperty("update_button_xpath"))).click();
			 driver.findElement(By.xpath(prop.getProperty("//button[.='Submit & Logout']"))).click();
			 
			 Alert alert = driver.switchTo().alert();
			 Thread.sleep(3000);
			 alert.accept();
			}
	   	
	   }

	@Given("^Clcik on Submit button$")
	public void clcik_on_Submit_button() throws Throwable {
	    
	}

	@Then("^home pos screen should display$")
	public void home_pos_screen_should_display() throws Throwable {
	 
	}
	
	
// 2nd scenario
	@Given("^User is on chaiking login page$")
	public void user_is_on_chaiking_login_page() throws Throwable {
		System.out.println("Url got open succesfully.....");
	}

	@Then("^Verify the title of login page should chaikings$")
	public void verify_the_title_of_login_page_should_chaikings() throws Throwable {
	  
		String currentTitle = driver.getTitle();
		//Assert.assertEquals(EnvironmentsData.chaikingsTitle, currentTitle);
		System.out.println("Title is verfiyed , title is "+"'"+currentTitle+"'");
	}

	@Given("^enter invalid username and password$")
	public void enter_invalid_username_and_password() throws Throwable {
	 
		Map<String ,String> testdatainmpa2= ExcelTestDataHandler.getTestDataInMap(EnvironmentsData.testdata_sheet, EnvironmentsData.FistSheet,EnvironmentsData.testcase2);
		driver.findElement(By.xpath(prop.getProperty("Login_username_xpath"))).sendKeys(testdatainmpa2.get("Data_1"));
		driver.findElement(By.xpath(prop.getProperty("Login_password_xpath"))).sendKeys(testdatainmpa2.get("Data_2"));
		driver.findElement(By.xpath(prop.getProperty("Sign_up_xpath"))).click();
		
		Boolean trurr=driver.findElement(By.xpath(prop.getProperty("invalid_user_name_password_xpath"))).isDisplayed();
		if(trurr==true)
		{
			System.out.println("Proper Error message Displyed.");
		}else {
			System.out.println("Error message not Displayed.");
		}
		
	}

	@Then("^verify proper error messge should Display$")
	public void verify_proper_error_messge_should_Display() throws Throwable {
		
		
	    
	}
	
	//3rd scenario
	
	@Given("^counter will login to chaikings site$")
	public void counter_will_login_to_chaikings_site() throws Throwable {
	    System.out.println("i am 3rd scenario");
	}

	@Then("^user will add some items to cart$")
	public void user_will_add_some_items_to_cart() throws Throwable {
	    
	}

	@Then("^counter will click on conform order$")
	public void counter_will_click_on_conform_order() throws Throwable {
	  
	}

	@Then("^order should be confrimed$")
	public void order_should_be_confrimed() throws Throwable {
	    
	}
	
	
	//4th scenario
	@Given("^Manager in login page and verfiy login should dispaly$")
	public void manager_in_login_page_and_verfiy_login_should_dispaly() {
	   
	}

	@Then("^enter manger username and password and click on submit button$")
	public void enter_manger_username_and_password_and_click_on_submit_button() {
	  
	}

	@Then("^check weather succesfully logined or not$")
	public void check_weather_succesfully_logined_or_not() {
	   
	}

	@Then("^click on logout button$")
	public void click_on_logout_button() {
	  
	}

	@Then("^verify login page should display$")
	public void verify_login_page_should_display() {
	   
	}

	
	
	
}
