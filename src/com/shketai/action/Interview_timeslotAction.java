package com.shketai.action;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.Interview;
import com.shketai.entity.Interview_timeslot;
import com.shketai.service.Interview_timeslotService;
import com.shketai.service.LogService;

public class Interview_timeslotAction extends ActionSupport implements ModelDriven<Interview_timeslot>{

	private static final long serialVersionUID = 1L;

	private int id;
	
	private Date starttime;             //面试开始时间
	
	private Date end_time;              //面试结束时间
	
	private int num;                    //可以面试的总人数
	
	private int result;
	
	private int stu_id;
	
	private int interview_timeslot_id;
	
	private int interview_id;
	
	private Interview interview;
	
	private Interview_timeslot interview_timeslot = new Interview_timeslot();
	
	private List<Interview_timeslot> interview_timeslot1;

	private Interview_timeslotService interview_timeslotService;
	
	private LogService logService;
	
	
	public void setInterview_timeslotService(
			Interview_timeslotService interview_timeslotService) {
		this.interview_timeslotService = interview_timeslotService;
	}

	/**
	 * 新增面试场次时间、人数
	 * @param interview_timeslotService
	 */
	public String add(){
		logService.addLog("科目管理", "新增面试场次");
		result = interview_timeslotService.add(interview_timeslot,interview_id);
		return SUCCESS;
	}
	
	/**
	 * 修改面试场次
	 * @return
	 */
	public String update(){
		result = interview_timeslotService.update(interview_timeslot);
		return SUCCESS;
	}
	/**
	 * 查询相关的面试时间段
	 * @return
	 */
	public String findBySubjectInterview(){
		interview_timeslot1 = interview_timeslotService.findBySubjectInterview(interview_id);
		
		return SUCCESS;
	}
	/**
	 * 查询所有面试时间段
	 * @return
	 */
	public String findAll(){
		interview_timeslot1 = interview_timeslotService.findAll();
		return SUCCESS;
	}
	
	/**
	 * 预约面试
	 * @return
	 */
	public String selectInterview(){
		interview_timeslotService.selectInterview(stu_id, interview_timeslot_id);
		result = 1;
		return SUCCESS;
	}
	
	/**
	 * @return
	 *  查询指定interview_id的面试科目记录
	 */
	public String queryInterviewtimeslotActionByinterviewid() {
		logService.addLog("科目管理", "场次详情");
		interview_timeslot1 = interview_timeslotService.queryInterview_timeslotActionByinterview_id(interview_id);
		
		return SUCCESS;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	public Interview_timeslot getInterview_timeslot() {
		return interview_timeslot;
	}

	public void setInterview_timeslot(Interview_timeslot interview_timeslot) {
		this.interview_timeslot = interview_timeslot;
	}

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public int getInterview_timeslot_id() {
		return interview_timeslot_id;
	}

	public void setInterview_timeslot_id(int interview_timeslot_id) {
		this.interview_timeslot_id = interview_timeslot_id;
	}

	public int getInterview_id() {
		return interview_id;
	}

	public void setInterview_id(int interview_id) {
		this.interview_id = interview_id;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public List<Interview_timeslot> getInterview_timeslot1() {
		return interview_timeslot1;
	}

	public void setInterview_timeslot1(List<Interview_timeslot> interview_timeslot1) {
		this.interview_timeslot1 = interview_timeslot1;
	}


	public Interview_timeslot getModel() {
		return interview_timeslot;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
}
