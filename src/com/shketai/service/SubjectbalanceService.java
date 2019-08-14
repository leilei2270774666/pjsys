package com.shketai.service;

import java.util.List;

import com.shketai.dao.SubjectbalanceDao;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Info;
import com.shketai.entity.PageBean;

public class SubjectbalanceService {
	private SubjectbalanceDao subjectbalanceDao;

	public SubjectbalanceDao getSubjectbalanceDao() {
		return subjectbalanceDao;
	}

	public void setSubjectbalanceDao(SubjectbalanceDao subjectbalanceDao) {
		this.subjectbalanceDao = subjectbalanceDao;
	}
	/**
	 * 查询科目明细
	 */
	public List<Object[]> getSubjectbalance(){
		return subjectbalanceDao.getSubjectbalance();
		
	}
	/**
	 * 查询科目明细分页
	 * @param page
	 * @return
	 */
	public PageBean<Object[]> find(int page,String subject){
		PageBean<Object[]> pb = new PageBean<Object[]>();
		List<Object[]> list = subjectbalanceDao.find(page,subject);
		pb.setDatas(list);
		int total = subjectbalanceDao.findCount(subject);
		pb.setTotal(total);
		return pb;
	}

}
