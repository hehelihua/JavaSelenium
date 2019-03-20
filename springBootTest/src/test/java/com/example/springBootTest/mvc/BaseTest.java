package com.example.springBootTest.mvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.example.springBootTest.SpringBootTestApplicationTests;

@SpringBootTest(classes = {SpringBootTestApplicationTests.class})
public class BaseTest extends AbstractTestNGSpringContextTests{

}
