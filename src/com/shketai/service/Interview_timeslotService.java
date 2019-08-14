package com.shketai.service;

import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.shketai.dao.Interview_timeslotDao;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Interview;
import com.shketai.entity.Interview_timeslot;
import com.shketai.entity.Student;

public class Interview_timeslotService {

	private Interview_timeslotDao interview_timeslotDao;
	
	public void setInterview_timeslotDao(Interview_timeslotDao interview_timeslotDao) {
		this.interview_timeslotDao = interview_timeslotDao;
	}

	/**
	 * 新增面试场次（时间段和限制人数）
	 * @param interview_id
	 * @param starttime
	 * @param end_time
	 * @param num
	 */

	public int add(Interview_timeslot interview_timeslot,int interview_id){
		int result = interview_timeslotDao.add(interview_timeslot,interview_id);
		return result;
	}
	
	public int update(Interview_timeslot interview_timeslot) {
		int result = interview_timeslotDao.update(interview_timeslot);
		return result;
	}
	/**
	 * 查询相关的面试时间段  
	 * @param interviewId
	 * @return
	 */
	public List<Interview_timeslot> findBySubjectInterview(int interview_id){
		List list = interview_timeslotDao.findBySubjectInterview( interview_id);
		return list;   
	}
	/**
	 * 查询所有面试时间段 
	 * @return
	 */
	public List<Interview_timeslot> findAll(){
		List interview_timeslotList = interview_timeslotDao.findAll();
		return interview_timeslotList;
	}

	public void selectInterview(int stu_id,int Interview_timeslot_id){
		interview_timeslotDao.selectInterview(stu_id, Interview_timeslot_id);
	}
	
	
	/**
	 * @param interview_id
	 * @return
	 *  查询指定interview_id的面试科目记录
	 */
	public List<Interview_timeslot>  queryInterview_timeslotActionByinterview_id(int interview_id) {
		
		return interview_timeslotDao.queryInterview_timeslotActionByinterview_id(interview_id);
		
	}
	
	/**
	 * @param id
	 *   sql update
	 */
	public int sqlUpdate(int id , Date starttime, Date end_time , int num ) {
		return interview_timeslotDao.sqlUpdate(id, starttime, end_time, num);
	}
	
}
