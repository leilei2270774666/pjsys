package com.shketai.service;

import java.util.List;

import com.shketai.dao.MajorsDao;
import com.shketai.entity.Majors;

public class MajorsService {

	private MajorsDao majorsDao;

	public void setMajorsDao(MajorsDao majorsDao) {
		this.majorsDao = majorsDao;
	}
	
	public List<Majors> findAll(){
		List list = majorsDao.findAll();
		return list;
	}
	
	public int add(Majors majors){
		int result = majorsDao.add(majors);
		return result;
	}

	public int update(Majors majors){
		int result = majorsDao.update(majors);
		return result;
	}
	
	public int delete(Majors majors){
		int result =majorsDao.delete(majors);
		return result;
	}
}
