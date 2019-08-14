package com.shketai.service;

import java.util.List;

import com.shketai.dao.InterviewDao;
import com.shketai.entity.Interview;

public class InterviewService {

	private InterviewDao interviewDao;

	public void setInterviewDao(InterviewDao interviewDao) {
		this.interviewDao = interviewDao;
	}
	
	public int add(Interview interview){
		int result = interviewDao.add(interview);
		return result;
	}
	
	public int update(Interview interview){
		int result = interviewDao.update(interview);
		return result;
	}
	
	
	
	/**
	 * 查询不需要面试科目
	 * //是否需要面试 1：需要 0：不需要
	 * @return
	 */
	public List<Interview> findAllInterview(){
		List interviewList = interviewDao.findAllInterview();
		
		return interviewList;
	}
}
