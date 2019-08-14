package com.shketai.service;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.shketai.dao.Interview_infoDao;
import com.shketai.entity.Info;
import com.shketai.entity.Interview_info;
import com.shketai.entity.PageBean;

public class Interview_infoService {

	private Interview_infoDao interview_infoDao;

	public void setInterview_infoDao(Interview_infoDao interview_infoDao) {
		this.interview_infoDao = interview_infoDao;
	}
	
	public void pass(int stu_id,int interview_timeslot_id){
		interview_infoDao.pass(stu_id, interview_timeslot_id);
	}
	
	public void unpass(int stu_id,int interview_timeslot_id){
		interview_infoDao.unpass(stu_id, interview_timeslot_id);
	}
	
	public List<Interview_info> findAll(){
		List list = interview_infoDao.findAll();
		return list;
	}
	
	public List<Interview_info> find(int user_id){
		List list = interview_infoDao.find(user_id);
		return list;
	}
	
	public PageBean<Interview_info> findByInterview(int interview_id,int page){
		PageBean<Interview_info> pb = new PageBean<Interview_info>();
		List<Interview_info> list = interview_infoDao.findByInterview(interview_id, page);
		pb.setDatas(list);
		int total = interview_infoDao.findCounts(interview_id);
		pb.setTotal(total);
		return pb;
	}
	public List<Interview_info> findInterview(int stu_id){
		return interview_infoDao.findInterview(stu_id);
		
	}
	
	/**
	 * 查询所有面试审核通过的学生
	 * @return
	 */
	public PageBean<Interview_info>findResult(int page){
		PageBean<Interview_info> pb = new PageBean<Interview_info>();
		List<Interview_info> list = interview_infoDao.findResult(page);
		pb.setDatas(list);
		int total = interview_infoDao.findCount();
		pb.setTotal(total);
		return pb;
		
	}
	/***
	 * 根据姓名查询学生信息
	 * @param stuName
	 * @return
	 */
	public List<Interview_info> queryByUsername(String stuName){
		return interview_infoDao.queryByUsername(stuName);
	}
	
	/**
	 * 把所有interview_info 里的  clear_flag 赋值1 
	 * @return
	 */
	public Integer interview_infoInclear_flag() {
		
		return interview_infoDao.interview_infoInclear_flag();
	}
}
