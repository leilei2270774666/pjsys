package com.shketai.service;

import java.util.List;

import com.shketai.dao.SubjectrefundDao;
import com.shketai.entity.Info;
import com.shketai.entity.PageBean;

public class SubjectrefundService {
	private SubjectrefundDao subjectrefundDao;

	public SubjectrefundDao getSubjectrefundDao() {
		return subjectrefundDao;
	}

	public void setSubjectrefundDao(SubjectrefundDao subjectrefundDao) {
		this.subjectrefundDao = subjectrefundDao;
	}
	/**
	 * 科目收支明细
	 */
	public List<Object[]> getSubjectrefund(){
		return subjectrefundDao.getSubjectrefund();
		
	}
	/**
	 * 查询科目退款明细分页
	 * @param page
	 * @return
	 */
	public PageBean<Object[]> find(int page){
		PageBean<Object[]> pb = new PageBean<Object[]>();
		List<Object[]> list = subjectrefundDao.find(page);
		pb.setDatas(list);
		int total = subjectrefundDao.findCount();
		pb.setTotal(total);
		return pb;
	}
}
