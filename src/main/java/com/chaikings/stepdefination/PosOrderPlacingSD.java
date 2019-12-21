package com.chaikings.stepdefination;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cahikings.GenericFiles.BrowserOpen;
import com.cahikings.GenericFiles.ExcelDataHandlerGetterSetter;
import com.chaikings.EnvironmentData.EnvironmentsData;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PosOrderPlacingSD extends BrowserOpen {
	
	public PosOrderPlacingSD() throws Exception {
		super();
	}
	
	static Map<String,String> data;
	
	


	
	@Given("^User should be on login page$")
	public void user_should_be_on_login_page() throws IOException {
		
		ExcelDataHandlerGetterSetter testdatad = new ExcelDataHandlerGetterSetter(); // to store in getters and setters
		testdatad.setTestDataInMap(LoginStepDef.testdatainmpa);
		data=testdatad.getTestDataInMap();
		
		System.out.println("1st step");
	   
	}

	@Then("^Enter the counter username and password$")
	public void enter_the_counter_username_and_password() throws InterruptedException {
		System.out.println("2nd step");
		driver.findElement(By.xpath(prop.getProperty("Login_username_xpath"))).sendKeys(data.get("Data_1"));
		driver.findElement(By.xpath(prop.getProperty("Login_password_xpath"))).sendKeys(data.get("Data_2"));
	}

	@Then("^Click on login Button$")
	public void click_on_login_Button() throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("Sign_up_xpath"))).click();
		Thread.sleep(3000);
	}

	@Then("^POS screen should Display$")
	public void pos_screen_should_Display() throws Exception {
		
		if(driver.getCurrentUrl().equals(EnvironmentsData.URL))
		{
			forceLogin();
			if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_update_end_URL))
			{
				counterUpdate();
				
			}else if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_start_URL))
			{
				counterStatrt();
				//orderPlaceing();
				//logout();
				
			}else if (driver.getCurrentUrl().equals(EnvironmentsData.POS_url))
			{
				//orderPlaceing();
				//logout();
			}
		}else if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_start_URL))
		{
			counterStatrt();
			//orderPlaceing();
			//logout();
			
		}else if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_update_end_URL))
		{
			counterUpdate();
		}else if (driver.getCurrentUrl().equals(EnvironmentsData.POS_url))
		{
			System.out.println("POS screen is Displayed");
			//orderPlaceing();
			//logout();
		}
	    
	}

	@Then("^counter user should able to navigate all screens$")
	public void counter_user_should_able_to_navigate_all_screens() {
	    driver.findElement(By.xpath(prop.getProperty("pos_delivery_xpath"))).click();
	    
	    driver.findElement(By.xpath(prop.getProperty("pos_user_xpath"))).click();
	    
	    driver.findElement(By.xpath(prop.getProperty("pos_takeAway_xpath"))).click();
	    
	    driver.findElement(By.xpath(prop.getProperty("pos_order_xpath"))).click();
	    
	    driver.findElement(By.xpath(prop.getProperty("order_takeWay_xpath"))).click();
	    driver.findElement(By.xpath(prop.getProperty("order_delivery_xpath"))).click();
	    driver.findElement(By.xpath(prop.getProperty("order_goBilling_xpath"))).click();
	    driver.findElement(By.xpath(prop.getProperty("pos_takeAway_xpath"))).click();
	}

	@Then("^add some items to cart$")
	public void add_some_items_to_cart() {
		
		 WebElement blackchai= driver.findElement(By.xpath(prop.getProperty("Black_chai_product_xpath")));
		 clickOn(driver,blackchai,5);
		 driver.findElement(By.xpath(prop.getProperty("honey_add_product_xpath"))).click();
		 WebElement categorysearch=driver.findElement(By.xpath(prop.getProperty("category_search_xpath")));
		 sendKeys(driver,categorysearch,5,data.get("Data_y"));
		 driver.findElement(By.xpath(prop.getProperty("maggi_product_xpath"))).click();
		 driver.findElement(By.xpath(prop.getProperty("maggi_add_xpath"))).click();
		 driver.findElement(By.xpath(prop.getProperty("quntity_increase_xpath"))).click();
		 
	}

	@Then("^check in cart added items should display$")
	public void check_in_cart_added_items_should_display() {
		List<WebElement> productNames= driver.findElements(By.xpath(prop.getProperty("cart_productName_xpath")));
		 for(int i=0; i<=productNames.size();i++)
		 {
			 System.out.println("Total products is "+productNames.size());
			 System.out.println(productNames.get(i).getText());
			 
		 }
	}

	@Then("^place order as cash order$")
	public void place_order_as_cash_order() {
	  
		driver.findElement(By.xpath(prop.getProperty("cash_order_xpath"))).click();
		 driver.findElement(By.xpath(prop.getProperty("order_confirm_xpath"))).click();		 
		 String amount =driver.findElement(By.xpath(prop.getProperty("cash_order_amount_xpath"))).getText();
		 driver.findElement(By.xpath(prop.getProperty("cash_amount_enter_xpath"))).sendKeys(amount);
		 driver.findElement(By.xpath(prop.getProperty("save_order_xpath"))).click();
		 
		 String ordernum = driver.findElement(By.xpath(prop.getProperty("order_number_xpath"))).getText();
		System.out.println(ordernum+" : order got punched.");
		 driver.findElement(By.xpath(prop.getProperty("print_later_xpath"))).click();
		 driver.findElement(By.xpath(prop.getProperty("order_view_xpath"))).click();
		 List<WebElement> listofOrder = driver.findElements(By.xpath(prop.getProperty("order_number_list_xpath")));
		 for (int i=0;i<listofOrder.size();i++)
		 {
			 listofOrder.get(i);
			 System.out.println(listofOrder.get(i).getText());
			 if(ordernum.equals(listofOrder.get(i).getText()))
			 {
				 System.out.println(" : Order got succsfully placed and Displaying in Order View page..");
			 }
		 }
	}

	@Then("^then go to billing screen$")
	public void then_go_to_billing_screen() {
	   
	}

	@Then("^place order should display$")
	public void place_order_should_display() {
	    
	}

	@Then("^clcik on bill and take screenshot$")
	public void clcik_on_bill_and_take_screenshot() {
	   
	}

	@Then("^logout from POS Screen$")
	public void logout_from_POS_Screen() {
		
	   
	}
	
	
	
	
	
	
	
	
	public static void login() throws InterruptedException 
	{	
		WebElement username=driver.findElement(By.xpath(prop.getProperty("Login_username_xpath")));
		sendKeys(driver,username,4,data.get("Data_1"));
		driver.findElement(By.xpath(prop.getProperty("Login_password_xpath"))).sendKeys(data.get("Data_2"));
		driver.findElement(By.xpath(prop.getProperty("Sign_up_xpath"))).click();
		
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
			//orderPlaceing();
			//logout();
			}
		}
	
	public static void counterStatrt() throws InterruptedException
	{
		if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_start_URL)) {
			System.out.println("Login succesfully.....");
			System.out.println("i am Satrt screen.....");
			System.out.println(driver.getCurrentUrl());
			String open_cash_date = driver.findElement(By.xpath(prop.getProperty("opeing_cash_date_xpath"))).getText();
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
			
			WebElement acutualCash = driver.findElement(By.xpath(prop.getProperty("actual_cash_text_xpath")));
			System.out.println("Actual cahs is " + acutualCash.getText());
			clickOn(driver,acutualCash,5);
			
			WebElement enterclosingcahs=driver.findElement(By.xpath(prop.getProperty("enter_closing_cash_xapth")));
			sendKeys(driver,enterclosingcahs,5,data.get("Data_3"));
			
			driver.findElement(By.xpath(prop.getProperty("update_button_xpath"))).click();
			
			System.out.println("Actual cash updated amount is " + acutualCash.getText());
			Thread.sleep(3000);
			WebElement submitLogout=driver.findElement(By.xpath(prop.getProperty("submit_logout_xpath")));
			clickOn(driver,submitLogout,5);
			Thread.sleep(3000);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(2000);
			alert.accept();
			Thread.sleep(2000);
			
			driver.navigate().refresh();
			
			driver.get(EnvironmentsData.URL);
			login();
			if (driver.getCurrentUrl().equals(EnvironmentsData.URL))
			{
				forceLogin();
				counterStatrt();
//				orderPlaceing();
//				logout();
			}else if (driver.getCurrentUrl().equals(EnvironmentsData.Counter_start_URL))
			{
				counterStatrt();
				Thread.sleep(3000);
//				orderPlaceing();
				//logout();
				
			}else if (driver.getCurrentUrl().equals(EnvironmentsData.POS_url))
			{
				System.out.println("displyed POS screen");
//				orderPlaceing();
				//logout();
			}
			
		}
		}
	
	
	
	public static void orderPlaceing() throws InterruptedException
	  {
				
		 WebElement blackchai= driver.findElement(By.xpath(prop.getProperty("Black_chai_product_xpath")));
		 clickOn(driver,blackchai,5);
		 driver.findElement(By.xpath(prop.getProperty("honey_add_product_xpath"))).click();
		 WebElement categorysearch=driver.findElement(By.xpath(prop.getProperty("category_search_xpath")));
		 sendKeys(driver,categorysearch,5,data.get("Data_y"));
		 driver.findElement(By.xpath(prop.getProperty("maggi_product_xpath"))).click();
		 driver.findElement(By.xpath(prop.getProperty("maggi_add_xpath"))).click();
		 driver.findElement(By.xpath(prop.getProperty("quntity_increase_xpath"))).click();
		 driver.findElement(By.xpath(prop.getProperty("cash_order_xpath"))).click();
		 driver.findElement(By.xpath(prop.getProperty("order_confirm_xpath"))).click();		 
		 String amount =driver.findElement(By.xpath(prop.getProperty("cash_order_amount_xpath"))).getText();
		 driver.findElement(By.xpath(prop.getProperty("cash_amount_enter_xpath"))).sendKeys(amount);
		 driver.findElement(By.xpath(prop.getProperty("save_order_xpath"))).click();
		 String ordernum = driver.findElement(By.xpath(prop.getProperty("order_number_xpath"))).getText();
		System.out.println(ordernum+" : order got punched.");
		 driver.findElement(By.xpath(prop.getProperty("print_later_xpath"))).click();
		 driver.findElement(By.xpath(prop.getProperty("order_view_xpath"))).click();
		 List<WebElement> listofOrder = driver.findElements(By.xpath(prop.getProperty("order_number_list_xpath")));
		 for (int i=0;i<listofOrder.size();i++)
		 {
			 listofOrder.get(i);
			 System.out.println(listofOrder.get(i).getText());
			 if(ordernum.equals(listofOrder.get(i).getText()))
			 {
				 System.out.println(" : Order got succsfully placed and Displaying in Order View page..");
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
	
	public static void sendKeys(WebDriver driver, WebElement element,int timeout ,String value)
	{
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}
	
	public static void clickOn(WebDriver driver,WebElement element,int timeout)
	{
		new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	

}
