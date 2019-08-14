package com.shketai.action;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.Interview;
import com.shketai.service.InterviewService;
import com.shketai.service.Interview_timeslotService;
import com.shketai.service.LogService;


public class InterviewAction extends ActionSupport implements ModelDriven<Interview>{

	private static final long serialVersionUID = 1L;
	
	private InterviewService interviewService;
	
	private Interview_timeslotService interview_timeslotService;
	
	private LogService logService;
	
	private int id;
	
	private int interview_timeslot_id;
	
	private String InterviewingSubject; //面试科目
	
	private Date starttime;             //面试开始时间
	
	private Date end_time;              //面试结束时间
	
	private int num;                    //可以面试的总人数
	
	private int result;
	
	private List<Interview> interviews;
	
	private Interview interview = new Interview();

	public void setInterviewService(InterviewService interviewService) {
		this.interviewService = interviewService; 
	}

	/**
	 * 增加面试科目
	 * @param interview
	 * @return
	 */
	public String add(){
		
		logService.addLog("面试审核", "新增");
		
		result = interviewService.add(interview);
		return SUCCESS;
	}
	/**
	 * 修改面试科目
	 * @param interview
	 * @return
	 */
	public String update(){
		
		logService.addLog("科目管理-场次详情", "修改场次");
		
		result = interview_timeslotService.sqlUpdate(interview_timeslot_id, starttime, end_time, num);
		
//		result = interviewService.update(interview);
		return SUCCESS;
	} 
	
	public String findAllInterview(){
		
		logService.addLog("面试审核", "查询");
		
		interviews = interviewService.findAllInterview();
		//result = 1;
		return SUCCESS;
	}

	public Interview getModel() {
		return interview;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInterviewingSubject() {
		return InterviewingSubject;
	}

	public void setInterviewingSubject(String interviewingSubject) {
		InterviewingSubject = interviewingSubject;
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public List<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}

	public void setInterview_timeslotService(Interview_timeslotService interview_timeslotService) {
		this.interview_timeslotService = interview_timeslotService;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getInterview_timeslot_id() {
		return interview_timeslot_id;
	}

	public void setInterview_timeslot_id(int interview_timeslot_id) {
		this.interview_timeslot_id = interview_timeslot_id;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public InterviewService getInterviewService() {
		return interviewService;
	}

	public Interview_timeslotService getInterview_timeslotService() {
		return interview_timeslotService;
	}
	
	
	
}
