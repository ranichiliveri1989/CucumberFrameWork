package com.jan23.commontests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.io.FileHandler;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.jan23.utility.Constants;
import com.jan23.utility.ExtentReportsUtility;
import com.jan23.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest2 {
	
	public static WebDriver driver;
	
	public static void scrolldown() throws InterruptedException  {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		Thread.sleep(3000);
	}
	
	
   
	public static void getScreenshots() {
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
		String fileName = timeStamp + ".png";
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcImage = ts.getScreenshotAs(OutputType.FILE);
		File tarImage = new File(Constants.SCREENSHOTS_DIR_PATH+fileName);
		try {
			FileHandler.copy(srcImage, tarImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getScreenshots(WebDriver driver)  {
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
		String fileName = timeStamp + ".png";
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcImage = ts.getScreenshotAs(OutputType.FILE);
		
		File tarImage = new File(Constants.SCREENSHOTS_DIR_PATH+fileName);
		try {
			FileHandler.copy(srcImage, tarImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tarImage.getAbsolutePath();
	}



	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver;
	}
	
}


