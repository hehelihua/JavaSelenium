package com.example.springBootTest.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.springBootTest.unit.ImportExcelUtil;
import com.example.springBootTest.vo.User;

public class DemoTestng {
	
	WebDriver driver;
	
	@BeforeClass
    public void setup(){
        System.out.println("begin test");
		System.setProperty("webdriver.gecko.driver", "D:\\tools\\geckodriver-v0.21.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://user.lu.com/user/login");
    }
	
	@Test
	public void test() {
		List<User> userList=getUserList();
		for (User user : userList) {
			//driver.findElement(By.id("accountName")).sendKeys(user.getCellphone());
			//driver.findElement(By.id("password")).sendKeys(user.getCellphone());
			//WebElement login=driver.findElement(By.id("userLoginBtn"));
			//login.click();
			System.out.println(user.getCellphone());
		}
		
	}
    
    public List<User> getUserList() {
    	ImportExcelUtil<User> util=new ImportExcelUtil<>();
    	User user=new User();
    	File file = new File("D:\\2222.xlsx");
    	FileInputStream inputStream;
    	List<User> userList=new ArrayList<>();
		try {
			inputStream = new FileInputStream(file);
			userList=util.readExcel(inputStream, file.getName(), user, "user");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return userList;
    }
    
    
    @AfterClass
    public void teardown(){
    	System.out.println("Page title is: " + driver.getTitle());
    //	driver.quit();
        System.out.println("end test");
    }
}
