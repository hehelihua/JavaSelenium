package com.example.springBootTest.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SeleniumAPI {
	
	WebDriver driver;
	
	@BeforeClass
	public void test0() {
		System.setProperty("webdriver.gecko.driver", "D:\\tools\\geckodriver-v0.21.0-win64\\geckodriver.exe");
		driver=new FirefoxDriver();
	}
	
	@FindBy(id="loginName")
	private WebElement loginName;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="login_btn")
	public WebElement loginBtn;
	
	public void login(WebDriver wd,String loginName,String password) {
		driver.get("http://www.rongjinqiao.com/user/toLogin");
		
	}
	
	
	//判断元素是否存在
	public boolean test2(WebDriver driver,By selector) {
		try {
			driver.findElement(selector);
			return true;
		} catch (NoSuchElementException  e) {
			return false;
		}
	}	
	
	@Test
	public void test1() {
		//截屏
		TakesScreenshot take=(TakesScreenshot) driver;
		File file=take.getScreenshotAs(OutputType.FILE);
		OutputStream out=null;
		try {
			out=new FileOutputStream("D:/test.jpg");
			FileUtils.copyFile(file, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//执行双击操作
		
	}

	public static void main(String[] args) {
//		MobileElement el3 = (MobileElement) driver.findElementById("com.android.calculator2:id/digit_7");
//		el3.click();
//		MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("plus");
//		el4.click();
//		MobileElement el5 = (MobileElement) driver.findElementById("com.android.calculator2:id/digit_5");
//		el5.click();
//		MobileElement el6 = (MobileElement) driver.findElementByAccessibilityId("equals");
//		el6.click();

	}
}
