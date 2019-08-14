package com.shketai.service;

import java.util.List;

import com.shketai.dao.AdministratorDao;
import com.shketai.entity.Teacher;

public class AdministratorService {

	private AdministratorDao administratorDao;

	public void setAdministratorDao(AdministratorDao administratorDao) {
		this.administratorDao = administratorDao;
	}
	
	public int add(Teacher teacher){
		administratorDao.add(teacher);
		return 1;
	}
	
	public int update(Teacher teacher){
		int result = administratorDao.update(teacher);
		return result;
	}
	public int delete(Teacher teacher){
		administratorDao.delete(teacher);
		return 1;
	}
	
	public List<Teacher> findAll(){
		List list =  administratorDao.findAll();
		return list;
	}
}
