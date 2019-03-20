package com.example.springBootTest.mvc;

import org.openqa.selenium.WebDriver;

public class LoginUser {
	
	public static void login(WebDriver driver,String loginName,String password) {
		driver.get("http://www.rongjinqiao.com/user/toLogin");
		LoginPage.loginName.sendKeys(loginName);
		LoginPage.password.sendKeys(password);
		LoginPage.loginBtn.click();
	}
}
