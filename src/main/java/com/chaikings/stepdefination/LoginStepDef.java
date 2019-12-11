package com.chaikings.stepdefination;


import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;


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
		Assert.assertEquals(EnvironmentsData.chaikingsTitle, currentTitle);
		System.out.println("Title is verfiyed , title is "+"'"+currentTitle+"'");
	}

	@Given("^enter username and password$")
	public void enter_username_and_password() throws Throwable {
		
		Map<String ,String> testdatainmpa= ExcelTestDataHandler.getTestDataInMap(EnvironmentsData.testdata_sheet, EnvironmentsData.FistSheet,EnvironmentsData.testcase);
		testdata.setTestDataInMap(testdatainmpa);//setter
		
		Map<String,String> data =testdata.getTestDataInMap();
		System.out.println(data.get("Data_1"));
	   driver.findElement(By.xpath(prop.getProperty("Login_username_xpath"))).sendKeys(testdatainmpa.get("Data_2"));
	   driver.findElement(By.xpath(prop.getProperty("Login_password_xpath"))).sendKeys(testdatainmpa.get("Data_4"));
	   driver.findElement(By.xpath(prop.getProperty("Sign_up_xpath"))).click();
	   
	   
	   String dd= driver.getCurrentUrl();
	   if(dd.equals(EnvironmentsData.URL))
	   {
		   String  forcelogintext = driver.findElement(By.xpath(prop.getProperty("force_login_xpath"))).getText();
		   System.out.println(forcelogintext);

			   String clicktext=driver.findElement(By.xpath(prop.getProperty("force_login_linkTextclick_xpath"))).getText();
			   System.out.println(clicktext);
			   
	   }else {
		   System.out.println("Login succesfully.....");
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
		System.out.println("test2");
	}

	@Then("^Verify the title of login page should chaikings$")
	public void verify_the_title_of_login_page_should_chaikings() throws Throwable {
	   
	}

	@Given("^enter invalid username and password$")
	public void enter_invalid_username_and_password() throws Throwable {
	 
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
	
	
	
}
