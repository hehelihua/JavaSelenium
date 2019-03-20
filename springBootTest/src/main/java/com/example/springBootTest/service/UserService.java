package com.example.springBootTest.service;

import java.util.List;

import com.example.springBootTest.model.TestUser;

public interface UserService {
	
	public List<TestUser> queryUserList();

}
