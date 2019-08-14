package com.shketai.service;

import java.util.List;

import com.shketai.dao.InterviewDao;
import com.shketai.dao.Interview_timeslotDao;
import com.shketai.dao.SubjectDao;
import com.shketai.entity.Interview;
import com.shketai.entity.Interview_timeslot;
import com.shketai.entity.Subject;

public class SubjectService {

	private SubjectDao subjectDao;
	
	private InterviewDao interviewDao;
	
	private Interview_timeslotDao interview_timeslotDao;
	

	
	/**
	 * set
	 */
	public void setInterview_timeslotDao(Interview_timeslotDao interview_timeslotDao) {
		this.interview_timeslotDao = interview_timeslotDao;
	}

	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	public void setInterviewDao(InterviewDao interviewDao) {
		this.interviewDao = interviewDao;
	}



	/**
	 *  业务
	 */
	
	
	public int add(Subject subject,int major_id){
		
		//添加科目
		subjectDao.add(subject, major_id);
		
		//同时添加 面试科目
		int subject_id = subject.getId();
		String interviewSubject = subject.getSubject();
		
		interviewDao.add(subject_id, interviewSubject);
		
		return 1;
	}
	
	/**
	 * @param subject
	 * @return
	 * 
	 *  修改subject对象同时 更新 interview对象 
	 * 	如果subject里的interview 等于0（不要面试=0） 就把 interview_timeslot 的startTime,endtime ,num 清空
	 *  
	 */
	public int update(Subject subject , int major_id){
		int result = subjectDao.myUpdateSql( subject ,  major_id);
		
		int subject_id = subject.getId();
		
		Interview interview = interviewDao.findSubject_id(subject_id);
		
		if(interview != null) {
			interview.setInterviewSubject( subject.getSubject() );
			
			interviewDao.sqlUpdate(interview);
		}
					
		
//		如果subject里的interview 等于0（不要面试=0） 就把 interview_timeslot 的startTime,endtime ,num 清空
//		if(subject.getInterview() == 0) {
//			if(interview != null) {
//				int interview_id =  interview.getId();
//				interview_timeslotDao.clearInterview_timeslot(interview_id);
//			}
//		}
		
		return result;
	}
	
	public int delete(Subject subject){
		int result = subjectDao.delete(subject);
		return result;		
	}
	
	public List<Subject> findAll(){
		List<Subject> subjectList = subjectDao.findAll();
		
		for (int i = 0; i < subjectList.size(); i++) {
			
			Subject subject = subjectList.get(i);
			
			int subject_id = subject.getId();
			
			Interview_timeslot interview_timeslot = interview_timeslotDao.findSubject_id(subject_id);
			
			subject.setInterview_timeslot(interview_timeslot);
			
		
			Interview interview = interviewDao.findSubject_id(subject_id);
			if(interview != null) {
				subject.setInterview_id( interview.getId() );
			}
			
			
		}
		
		
		
		
		return subjectList;
	}
	
	public List<Subject> findAll1(int major_id){
		List list = subjectDao.findAll1(major_id);
		return list;
	}
	
	
}
