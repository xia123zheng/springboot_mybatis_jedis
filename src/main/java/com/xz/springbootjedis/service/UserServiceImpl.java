package com.xz.springbootjedis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xz.springbootjedis.dao.UserDao;
import com.xz.springbootjedis.entity.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

}
