package testngSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TestRepeatThis {

	@Test(invocationCount = 10)
	public void repeatThis() {
		System.out.println("repeatThis ");
	}

	@Test(invocationCount = 3)
	public void loadTestThisWebsite() {
		System.setProperty("webdriver.gecko.driver", "D:\\tools\\geckodriver-v0.21.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.yiibai.com");
		System.out.println("Page Title is " + driver.getTitle());
		AssertJUnit.assertEquals("Google", driver.getTitle());
		driver.quit();
	}

	@Test(invocationCount = 10, threadPoolSize = 3)
	public void testThreadPools() {
		System.out.printf("Thread Id : %s%n", Thread.currentThread().getId());
	}

	@Test(expectedExceptions = ArithmeticException.class)
	public void divisionWithException() {
		int i = 1 / 0;
		System.out.println("After division the value of i is :" + i);
	}

	@Test(timeOut = 5000) // time in mulliseconds
	public void testThisShouldPass() throws InterruptedException {
		Thread.sleep(4000);
	}

	@Test(timeOut = 1000)
	public void testThisShouldFail() {
		while (true) {
			// do nothing
		}
	}

}
