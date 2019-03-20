package com.example.springBootTest.mvc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	@FindBy(id="loginName")
	public static WebElement loginName;
	
	@FindBy(id="password")
	public static WebElement password;
	
	@FindBy(id="login_btn")
	public static WebElement loginBtn;
	
}
