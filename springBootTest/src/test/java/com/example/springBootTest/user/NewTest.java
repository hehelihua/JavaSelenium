package com.example.springBootTest.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.example.springBootTest.unit.ImportExcelUtil;
import com.example.springBootTest.vo.User;

public class NewTest {

	@Test
	public void getUserList() {
		ImportExcelUtil<User> util = new ImportExcelUtil<>();
		User user = new User();
		File file = new File("D:\\2222.xlsx");
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			List<User> userList = util.readExcel(inputStream, file.getName(), user, "user");
			for (User u : userList) {
				System.out.println(u.getCellphone()+","+u.getPassward());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
