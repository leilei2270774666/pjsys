package com.shketai.service;

import java.math.BigInteger;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.shketai.dao.TeacherDao;
import com.shketai.entity.Teacher;

public class TeacherService {
	
	private TeacherDao teacherDao;
	

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}



	public Teacher login(String username,String password,int role) {
		Teacher teacher =  teacherDao.login(username,password,role);
		return teacher;
	}

	
	/**
	 *  查询指定id 返回总数
	 */
	public int queryIdReturnCount(int id){
		return teacherDao.queryIdReturnCount(id);
	}
}
