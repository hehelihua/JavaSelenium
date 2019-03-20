package com.example.springBootTest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBootTest.mapper.UserMapper;
import com.example.springBootTest.model.TestUser;
import com.example.springBootTest.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;

	@Override
	public List<TestUser> queryUserList() {
		return userMapper.queryUserList();
	}

}
