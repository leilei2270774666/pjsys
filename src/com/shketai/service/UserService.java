 package com.shketai.service;

import java.util.List;

import com.shketai.dao.UserDao;
import com.shketai.entity.User;

public class UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void updateResult(int id){
		userDao.updateResult(id);
	}
	
	public User login(String mobilephone,String password){
		User user = userDao.login(mobilephone, password);
		return user;
	}
	
	public int register(User user){
		int result = userDao.register(user);
		return result;
	}
	
	public int  reset(User user){
		 int result = userDao.reset(user);
		 return result;
	}
	
	public String findyzm(String yzm){
		User user = userDao.findyzm(yzm);
		if(user != null){
			return user.getYzm();
		}else{
			return "";
		}
	}
	
	public int findUsername(String mobilephone){
		int result = userDao.findUsername(mobilephone);
		return result;
	}
	
}
