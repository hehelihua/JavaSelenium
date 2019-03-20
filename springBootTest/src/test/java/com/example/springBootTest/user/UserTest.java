package com.example.springBootTest.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserTest {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\tools\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.baidu.com");
		Thread.sleep(2000);
		WebElement kw = driver.findElement(By.id("kw"));
		kw.sendKeys("呵呵");
		Thread.sleep(2000);
		kw.submit();
	}
}
