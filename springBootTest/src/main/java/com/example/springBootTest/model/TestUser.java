package com.example.springBootTest.model;

import java.io.Serializable;

public class TestUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String testId;
	private String testNo;
	private String testName;
	private String testResult;

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestNo() {
		return testNo;
	}

	public void setTestNo(String testNo) {
		this.testNo = testNo;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

}
