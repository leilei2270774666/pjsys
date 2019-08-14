package com.shketai.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.Info;
import com.shketai.entity.Interview_info;
import com.shketai.entity.PageBean;
import com.shketai.service.Interview_infoService;
import com.shketai.service.LogService;

public class Interview_infoAction extends ActionSupport implements ModelDriven<Interview_info>{

	private static final long serialVersionUID = 1L;

	private Interview_infoService interview_infoService;
	
	private int results;
	
	private List <Interview_info> interview_info;
	
	private PageBean<Interview_info> pb;
	
	private int stu_id;
	
	private int interview_timeslot_id;
	
	private int interview_id;
	
	private int user_id;
	
	private int page;
	
	private String stuName;
	
	private Interview_info interview_infos = new Interview_info();
	
	private Integer flag;
	
	private LogService logService;
	
	/**
	 * 面试通过
	 * @return
	 */
	public String pass(){
		logService.addLog("面试审核", "通过");
		interview_infoService.pass(stu_id,interview_timeslot_id);
		 results = 1;
		return SUCCESS;
	}
	
	/**
	 * 查看预约面试列表
	 * @return
	 */
	public String findAll(){
	    interview_infoService.findAll();
		return SUCCESS;
	}
	
	/**
	 * 学生查看预约面试列表
	 * @return
	 */
	public String findInterviewList(){
		interview_info = interview_infoService.find(user_id);
		//result = 1;
		return SUCCESS;
	}
	/**
	 * 面试不通过
	 * @return
	 */
	public String unpass(){
		logService.addLog("面试审核", "不通过");
		interview_infoService.unpass(stu_id,interview_timeslot_id);
	     results = 2;
		return SUCCESS;
	}

	/**
	 * 查看面试状态
	 * @return
	 */
	public String findInterview(){
		interview_info = interview_infoService.findInterview(stu_id);
		return SUCCESS;
	}

	
	/**
	 * 查询所有面试审核通过的学生
	 * @return
	 */
	public String findResult1(){
		pb=interview_infoService.findResult(page);
		return SUCCESS;
		
	}
	
	public String findByInterview(){
		pb = interview_infoService.findByInterview(interview_id, page);
		return SUCCESS;
	}
	/**
	 * 根据姓名查询学生信息
	 * @return
	 */
	public String show1()  {
		logService.addLog("面试审核", "按姓名查找");
		//username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
		interview_info = interview_infoService.queryByUsername(stuName);
		return SUCCESS;
	}
	
	/**
	 * 清空面试审核
	 */
	public String interview_infoInclear_flag() {
		
//		把所有interview_info 里的  clear_flag 赋值1 
		interview_infoService.interview_infoInclear_flag();
		
		flag=1;
		
		return SUCCESS;
	}
	

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public List<Interview_info> getInterview_info() {
		return interview_info;
	}

	public void setInterview_info(List<Interview_info> interview_info) {
		this.interview_info = interview_info;
	}


	public void setInterview_infoService(Interview_infoService interview_infoService) {
		this.interview_infoService = interview_infoService;
	}

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public PageBean<Interview_info> getPb() {
		return pb;
	}

	public void setPb(PageBean<Interview_info> pb) {
		this.pb = pb;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public int getInterview_timeslot_id() {
		return interview_timeslot_id;
	}

	public void setInterview_timeslot_id(int interview_timeslot_id) {
		this.interview_timeslot_id = interview_timeslot_id;
	}

	public Interview_info getInterview_infos() {
		return interview_infos;
	}

	public void setInterview_infos(Interview_info interview_infos) {
		this.interview_infos = interview_infos;
	}

	public int getInterview_id() {
		return interview_id;
	}

	public void setInterview_id(int interview_id) {
		this.interview_id = interview_id;
	}

	@Override
	public Interview_info getModel() {
		// TODO Auto-generated method stub
		return interview_infos;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	
	
	
	
}
