package com.shketai.service;

import com.shketai.dao.CountsDao;
import com.shketai.entity.Classinfo;

public class CountsService {

	private CountsDao countsDao;

	public CountsDao getCountsDao() {
		return countsDao;
	}

	public void setCountsDao(CountsDao countsDao) {
		this.countsDao = countsDao;
	}
	
	public Classinfo findById(int id) {
		Classinfo classinfo = countsDao.findById(id);
		int result = countsDao.CountStudentByClassInfoId(classinfo.getId());
	
        classinfo.setCount(result);
		return classinfo;
	}
}
	
