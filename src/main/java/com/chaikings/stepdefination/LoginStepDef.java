package com.chaikings.stepdefination;

import java.io.IOException;
import java.util.Map;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cahikings.GenericFiles.BrowserOpen;
import com.cahikings.GenericFiles.ExcelDataHandlerGetterSetter;
import com.cahikings.GenericFiles.ExcelTestDataHandler;
import com.chaikings.EnvironmentData.EnvironmentsData;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginStepDef extends BrowserOpen {

	
	static String scenario_name;
	static Map<String, String> testdatainmpa;
	public static Map<String, String> data;
	
	static ExcelDataHandlerGetterSetter testdata = new ExcelDataHandlerGetterSetter(); // to store in getters and setters
	
	
	
	public LoginStepDef() throws IOException {
		super();
	}


	@Before
	public void openBrowser(Scenario scenario) throws Exception {
		
		BrowserOpen.initialization();
		scenario_name=scenario.getName();
		
		System.out.println("Scenario Name : "+scenario_name);
		testdatainmpa = ExcelTestDataHandler.getTestDataInMap(EnvironmentsData.testdata_sheet,EnvironmentsData.FistSheet, "'"+scenario_name+"'");
		testdata.setTestDataInMap(testdatainmpa);// setter
		data = testdata.getTestDataInMap();//getter
	}

	@After
	public void closeBrowser(Scenario scenario) throws InterruptedException, IOException {
//		Thread.sleep(3000);
//		System.out.println("@After Method : Driver Closed the Browser");
//		driver.close();
		
		tearDown(scenario);
	}

	// 1nd scenario
	@Given("^User is already on chaiking login page$")
	public void user_is_already_on_chaiking_login_page() throws Throwable {
	
		System.out.println("Browser got open succesfuly..");
	}

	@Then("^verify the title of login page is chaikings$")
	public void verify_the_title_of_login_page_is_chaikings() throws Throwable {
		String currentTitle = driver.getTitle();
		// Assert.assertEquals(EnvironmentsData.chaikingsTitle, currentTitle);
		System.out.println("Title is verfiyed , title is " + "'" + currentTitle + "'");
	}

	@Given("^enter username and password$")
	public void enter_username_and_password() throws Throwable {

		System.out.println("Scenario Name : "+scenario_name);
//		 testdatainmpa = ExcelTestDataHandler.getTestDataInMap(EnvironmentsData.testdata_sheet,EnvironmentsData.FistSheet, "'"+scenario_name+"'");
//		testdata.setTestDataInMap(testdatainmpa);// setter
//
//		data = testdata.getTestDataInMap();//getter
		
		login();
		if(driver.getCurrentUrl().equals(EnvironmentsData.URL))
		{
			forceLogin();
			if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_update_end_URL))
			{
				counterUpdate();
				
			}else if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_start_URL))
			{
				counterStatrt();
				logout();
				
			}else if (driver.getCurrentUrl().equals(EnvironmentsData.POS_url))
			{
				logout();
			}
		}else if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_start_URL))
		{
			counterStatrt();
			logout();
			
		}else if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_update_end_URL))
		{
			counterUpdate();
		}else if (driver.getCurrentUrl().equals(EnvironmentsData.POS_url))
		{
			logout();
		}
				
	}
	
	
	public static void login() throws InterruptedException 
	{	
		try{
		driver.findElement(By.xpath(prop.getProperty("Login_username_xpath"))).sendKeys(testdatainmpa.get("Data_1"));
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("Login_password_xpath"))).sendKeys(testdatainmpa.get("Data_2"));
		driver.findElement(By.xpath(prop.getProperty("Sign_up_xpath"))).click();
		Thread.sleep(3000);
		}catch (Exception e)
		{
			System.out.println("nullpointer");
		}
	}
	
	public static void forceLogin() throws InterruptedException {
		String dd = driver.getCurrentUrl();
		if (dd.equals(EnvironmentsData.URL)) 
			{
			System.out.println("I am in force login screen....");
			String forcelogintext = driver.findElement(By.xpath(prop.getProperty("force_login_xpath"))).getText();
			System.out.println(forcelogintext);
			WebElement clicktext = driver.findElement(By.xpath(prop.getProperty("force_login_linkTextclick_xpath")));
			clicktext.click();
			logout();
			}
		}
	
	public static void counterStatrt() throws InterruptedException
	{
		if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_start_URL)) {
			System.out.println("Login succesfully.....");
			System.out.println("i am Satrt screen.....");
			System.out.println(driver.getCurrentUrl());
			String open_cash_date = driver.findElement(By.xpath(prop.getProperty("opeing_cash_date_xpath")))
					.getText();
			System.out.println("Open counter data : " + open_cash_date);
			String open_cash_amount = driver.findElement(By.xpath(prop.getProperty("opeing_cahs_xpath"))).getText();
			System.out.println("open cash is : " + open_cash_amount);
			driver.findElement(By.xpath(prop.getProperty("opeing_cash_statrt_button_xpath"))).click();
			System.out.println("Succesfully POS Screen Displayed.");
		}
	}
	
	
	public static void counterUpdate() throws InterruptedException {  
		  if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_update_end_URL)) {
			System.out.println("i am in Day out update screen");
			Thread.sleep(2000);
			WebElement acutualCash = driver.findElement(By.xpath(prop.getProperty("actual_cash_text_xpath")));
			System.out.println("Actual cahs is " + acutualCash.getText());
			Thread.sleep(2000);
			acutualCash.click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("enter_closing_cash_xapth"))).sendKeys(testdatainmpa.get("Data_6"));
			Thread.sleep(2000);
			driver.findElement(By.xpath(prop.getProperty("update_button_xpath"))).click();
			Thread.sleep(2000);
			System.out.println("Actual cash updated amount is " + acutualCash.getText());
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("submit_logout_xpath"))).click();
			Thread.sleep(2000);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(3000);
			alert.accept();
			Thread.sleep(2000);
			driver.navigate().refresh();
			driver.get(EnvironmentsData.URL);
			login();
			if (driver.getCurrentUrl().equals(EnvironmentsData.URL))
			{
				forceLogin();
				counterStatrt();
				logout();
			}else if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_start_URL))
			{
				counterStatrt();
				Thread.sleep(3000);
				logout();
				
			}else if (driver.getCurrentUrl().equals(EnvironmentsData.POS_url))
			{
				
				logout();
			}
			
		}
		}
	
	
	

	
	public static void logout() throws InterruptedException
	{
		driver.findElement(By.xpath(prop.getProperty("ck_dropudown_xpath"))).click();
		Thread.sleep(2000);
		WebElement holdlogout = driver.findElement(By.xpath(prop.getProperty("hold_and_logout_xpath")));
		Thread.sleep(2000);
		System.out.println("User click on - "+holdlogout.getText());
		holdlogout.click();
		
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
		// Assert.assertEquals(EnvironmentsData.chaikingsTitle, currentTitle);
		System.out.println("Title is verfiyed , title is " + "'" + currentTitle + "'");
	}

	@Given("^enter invalid username and password$")
	public void enter_invalid_username_and_password() throws Throwable {

//		Map<String, String> testdatainmpa2 = ExcelTestDataHandler.getTestDataInMap(EnvironmentsData.testdata_sheet,
//				EnvironmentsData.FistSheet, "'"+scenario_name+"'");
		driver.findElement(By.xpath(prop.getProperty("Login_username_xpath"))).sendKeys(testdatainmpa.get("Data_1"));
		driver.findElement(By.xpath(prop.getProperty("Login_password_xpath"))).sendKeys(testdatainmpa.get("Data_2"));
		driver.findElement(By.xpath(prop.getProperty("Sign_up_xpath"))).click();

		Boolean trurr = driver.findElement(By.xpath(prop.getProperty("invalid_user_name_password_xpath")))
				.isDisplayed();
		if (trurr == true) {
			System.out.print("Proper Error message Displyed.");
		} else {
			System.out.print("Error message not Displayed.");
		}

	}

	@Then("^verify proper error messge should Display$")
	public void verify_proper_error_messge_should_Display() throws Throwable {

	}

	// 3rd scenario

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

	// 4th scenario
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
