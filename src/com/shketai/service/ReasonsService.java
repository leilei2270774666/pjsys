package com.shketai.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.shketai.dao.ReasonsDao;
import com.shketai.entity.Reasons;

public class ReasonsService {

	private ReasonsDao reasonsDao;

	public void setReasonsDao(ReasonsDao reasonsDao) {
		this.reasonsDao = reasonsDao;
	}
	public List<Reasons> findAll(){
		List list = reasonsDao.findAll();
		return list;
	}
	
	public Reasons queryId(int id) {
		return reasonsDao.queryId(id);
	}
	
}
