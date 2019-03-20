package com.example.springBootTest.mvc;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.springBootTest.model.TestUser;
import com.example.springBootTest.service.UserService;

public class LoginTest extends BaseTest {

	WebDriver driver;
	
	@Autowired
	private UserService userService;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.gecko.driver", "D:\\tools\\geckodriver-v0.21.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void loginTest() {
		PageFactory.initElements(driver, LoginPage.class);
		LoginUser.login(driver, "18955556666", "");
		assertEquals(0, 1, "登录失败");
	}
	
	@Test
	public void queryUserList() {
		List<TestUser> userList=userService.queryUserList();
		for (TestUser testUser : userList) {
			System.out.println(testUser.getTestNo()+","+testUser.getTestName());
		}
		
	}
}
