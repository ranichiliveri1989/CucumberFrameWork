package com.steps;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import com.jan23.pages.forgotpasswordpage.ForgotPasswordpage;
import com.jan23.pages.home.HomePage;
import com.jan23.pages.login.LoginPage;
import com.jan23.utility.Constants;
import com.jan23.utility.ExtentReportsUtility;
import com.jan23.utility.PropertiesUtility;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinationForSaleforceLogin {

	public static WebDriver driver;
	public static Logger logger=null;
	public static ExtentReportsUtility extentreport=ExtentReportsUtility.getInstance();
	public static PropertiesUtility pUtility=null;
	LoginPage login;
	HomePage home;
	ForgotPasswordpage fPage;
	
	
    @Before
	public static void driverSetup() throws InterruptedException, IOException
	{
    	logger = LogManager.getLogger(StepDefinationForSaleforceLogin.class.getName());
    	String driverType = "chrome";
	logger.info("Creating driver");
    if (driverType.equals("chrome")) {
    	driver = setupChrome();
    }
    else if (driverType.equals("firefox")) {
    	driver = setupFirefox();
    }
    else {
    	logger.info("Invalid driver type");
    	}
	
	driver.manage().window().maximize();
	logger.info("driver created");
	Thread.sleep(4000);
	
	}
    public static WebDriver setupChrome() throws InterruptedException
	{
	logger.info("Creating Chrome");
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	return driver;
	}
	
	public static WebDriver setupFirefox() throws InterruptedException
	{
	logger.info("Creating FireFox");
	WebDriverManager.firefoxdriver().setup();
	driver=new FirefoxDriver();
	return driver;
	}
	
	@Given("Application is up and running and in loginpage")
	public void application_is_up_and_running_and_in_loginpage() throws IOException {
		driver.get(PropertiesUtility.readPropertyData("url"));
	}
	@When("user on {string}")
	public void user_on(String page) {
	    if(page.equalsIgnoreCase("loginpage"))
	    	login=new LoginPage(driver);
	    else if(page.equalsIgnoreCase("homepage"))
	    	home=new HomePage(driver);
	    else
	    	fPage=new ForgotPasswordpage(driver);
	    
	}

    @When("user enter a username {string}")
    public void user_enter_a_username(String data) throws IOException {
    	String username=PropertiesUtility.readPropertyData(data);
 	   login.enterUserName(username);
 	   System.out.println(username);
 	  logger.info("user name entered");
    }
    @When("user enter a password {string}")
    public void user_enter_a_password(String data) throws IOException {
    	String passWord=PropertiesUtility.readPropertyData(data);
	    login.enterPassword(passWord);
	    System.out.println(passWord);
    	logger.info("password entered");
     }

	@When("click on login button")
	public void click_on_login_button() throws InterruptedException {
		driver=login.clickLogin();
		Thread.sleep(3000);
		 
	}
	@Then("I should get the error message like {string}")
	public void i_should_get_the_error_message_like(String data) {
	   
		String actualErrorName=login.getText();
		
		Assert.assertEquals( actualErrorName,data);
		
		//getScreenshots();
		
	}
	@When("click on forgot password link")
	public void click_on_forgot_password_link() {
       
		login.clickforgotpassword();	
	}
	@When("user enter a username as in ForgotPasswordpage as {string}")
	public void user_enter_a_username_as_in_forgot_passwordpage_as(String data) throws IOException {
		String username=PropertiesUtility.readPropertyData(data);
		    fPage.senduserName(username);
	 	   System.out.println(username);
	 	  logger.info("user name entered");
	}
	@When("click on continue button")
	public void click_on_continue_button() {
         
		fPage.clickMycontinue();	
	}
	@Then("I should get the message like {string}")
	public void i_should_get_the_message_like(String data) {
		
		
		String actualMessage=fPage.getmessage();
		
		Assert.assertEquals(actualMessage,data);
		//getScreenshots(driver);
	}
	
	@Then("verify page user name {string}")
	public void verify_page_user_name(String data) {
		logger.info("<<<< driver 2" + driver);
    	logger.info("<<<< homepage 2" + home);
    	String actual_name=home.getTextFromHomeName();
    	Assert.assertEquals(actual_name, data);
    	//getScreenshots(driver);
	}

	@When("check the RememberMe checkbox")
	public void check_the_remember_me_checkbox() throws InterruptedException {
		
		login.clickRememberMe();
		Thread.sleep(3000);
	}
	
	@When("logout the homepage")
	public void logout_the_homepage() throws InterruptedException {
		
		home.clickMyProfile();
		home.clickLogout();
		Thread.sleep(3000);
	}
	@Then("I should get username field with my username as {string}")
	public void i_should_get_username_field_with_my_username_as(String data) throws IOException {
		String expectedusername=PropertiesUtility.readPropertyData(data);
		String actualUserName=login.getTextusernameverification();
		Assert.assertEquals(actualUserName,expectedusername);
		logger.info("valid user is logged in");
		
		//getScreenshots(driver);	
	}
    @Then("It should give the error message {string}")
    public void it_should_give_the_error_message(String data) 
    {
	String actualErrorMessage=login.getText();
	Assert.assertEquals(actualErrorMessage,data);
	//getScreenshots(driver);
	}
    public void tearDownTest()
    {
    	System.out.println("I am here tearDownTest ");
    	//getScreenshots(driver);
    	driver.close();
    }
    public static String getScreenshots(WebDriver driver)  {
    	
    	System.out.println("I am here getScreenshots");

        System.out.println("Taking Screenshot");
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
		String fileName =  timeStamp + ".png";
		TakesScreenshot ts = (TakesScreenshot)driver;
		System.out.println(ts);
		File srcImage = ts.getScreenshotAs(OutputType.FILE);
		System.out.println(srcImage);

		File tarImage = new File(Constants.SCREENSHOTS_DIR_PATH+fileName);
		try {
			FileHandler.copy(srcImage, tarImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tarImage.getAbsolutePath();
	}
    
    public static WebDriver getDriver() {
		return driver;
	}
    
}
