package com.shketai.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.Attence;
import com.shketai.entity.PageBean;
import com.shketai.entity.Student;
import com.shketai.service.AttenceService;
import com.shketai.service.LogService;

public class AttenceAction extends ActionSupport implements ModelDriven<Attence>{
	
	private static final long serialVersionUID = 1L;

	private int user_id;
	
	private int attence_status;   //出勤状态  1：正常 2：缺勤 3：迟到 4：早退
	
	private int page;    //页码
	
	private PageBean<Object[]> pb;
	
	private Student student; 
	
	private String stu_id;
	
	private int reason_id;
	
	private String reason_ids;
	
	private int result;
	
	private int classinfo_id;
	private Attence attence = new Attence();
	
	private String stuName;
	
	private List<Map<String,Object>> att1;
	private List<Attence> att;
	private List<Object[]> att2;
	private AttenceService attenceService;
	
	private LogService logService;
	
	private Date dateTime;     //时间
	

	public void setAttenceService(AttenceService attenceService) {
		this.attenceService = attenceService;
	}

	/**
	 * 查看个人考勤
	 * @return
	 */
	public String findAttenceById(){
		
		
		
		try {
			
			att1 = attenceService.findAttenceById(user_id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	/**
	 * 考勤
	 * @param id
	 * @return
	 */
	public String attence(){
		
		logService.addLog("学生考勤管理", "出勤");
		
		 System.out.println("========================================" + stu_id);
		 System.out.println("========================================" + classinfo_id);
		 System.out.println("========================================" + reason_id);
		 System.out.println("========================================" +  attence.getAttence_status());
		try{
			List<Integer> stu_id_list = new ArrayList<Integer>();
			String[] array = stu_id.split(",");
			List<String> list2 = Arrays.asList(array);
			for (String string : list2) {
				stu_id_list.add(Integer.parseInt(string));
			}
			
			List<Integer> reason_id_list = new ArrayList<Integer>();
			String[] reason_ids_array = reason_ids.split(",");
			for (int i = 0; i < reason_ids_array.length; i++) {
				reason_id_list.add( Integer.parseInt(reason_ids_array[i]) );
			}
			
			attenceService.add(stu_id_list, attence.getAttence_status(), classinfo_id,reason_id_list);
			 result = 1;
		}catch(Exception e){
			result = 0;
		}
		
		return SUCCESS;
	}
	/**
	 * 修改考勤状态
	 * @return
	 */
	public String updateFlag(){
		logService.addLog("学生考勤管理", "修改考勤");
		attenceService.updateFlag(attence.getId());
		
		result = 1;
		return SUCCESS;
	}
	
	/**
	 * 修改考勤状态
	 * @return
	 */
	public String attenceUpdateStatu(){
		logService.addLog("学生考勤管理", "修改考勤");
		attenceService.attenceUpdateStatu(attence.getId(),attence.getAttence_status() , reason_id );
		
		result = 1;
		return SUCCESS;
	}
	
	/**
	 * 统计考勤
	 * @return
	 */
	public String CountAttence(){
		att = attenceService.CountAttence();
		return SUCCESS;
	}
	
	/**
	 * 学生考勤汇总
	 * @return
	 */
	public String Attendancesummary(){
		
		logService.addLog("学生考勤数据统计", "查询");
		
		if(page==0){
			page = 1; //如果没有发送page参数，默认查询第一条
		}
		
		pb=attenceService.Attendancesummary(page, classinfo_id);
		return SUCCESS;
	}
	
	public String Attendancesummary1(){
		att2=attenceService.Attendancesummary1(classinfo_id);
		return SUCCESS;
	}
	
	public String UnAttence(){
		att = attenceService.UnAttence(user_id);
		System.out.println(user_id);
		return SUCCESS;
	}
	
	public String show1()  {
		logService.addLog("学生考勤管理-修改考勤", "查询");
		att = attenceService.QueryByName(stuName);
		return SUCCESS;
	}
	
	public String CountUnAttence() {
		result = attenceService.CountUnAttence(classinfo_id);
		return SUCCESS;
	}
	public String CountAttences() {
		result = attenceService.CountAttence(classinfo_id);
		return SUCCESS;
	}
	
	/**
	 * 传递 班级id 得到缺勤 的考勤记录
	 * @param classinfo_id
	 */
	public String unAttenceMessage() {
		
		logService.addLog("班级考勤数据统计", "查询");
		
		att = attenceService.querUnAttenceList(classinfo_id,dateTime);
		return SUCCESS;
	}
	
	
	
	public Attence getModel() {
		return attence;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public List<Attence> getAtt() {
		return att;
	}

	public void setAtt(List<Attence> att) {
		this.att = att;
	}

	public int getAttence_status() {
		return attence_status;
	}

	public void setAttence_status(int attence_status) {
		this.attence_status = attence_status;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Attence getAttence() {
		return attence;
	}

	public void setAttence(Attence attence) {
		this.attence = attence;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean<Object[]> getPb() {
		return pb;
	}

	public void setPb(PageBean<Object[]> pb) {
		this.pb = pb;
	}

	public AttenceService getAttenceService() {
		return attenceService;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getResult() {
		return result;
	}

	
	public List<Map<String,Object>> getAtt1() {
		return att1;
	}

	public void setAtt1(List<Map<String,Object>> att1) {
		this.att1 = att1;
	}

	public String getStu_id() {
		return stu_id;
	}

	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}

	public List<Object[]> getAtt2() {
		return att2;
	}

	public void setAtt2(List<Object[]> att2) {
		this.att2 = att2;
	}

	public int getClassinfo_id() {
		return classinfo_id;
	}

	public void setClassinfo_id(int classinfo_id) {
		this.classinfo_id = classinfo_id;
	}

	public int getReason_id() {
		return reason_id;
	}

	public void setReason_id(int reason_id) {
		this.reason_id = reason_id;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String getReason_ids() {
		return reason_ids;
	}

	public void setReason_ids(String reason_ids) {
		this.reason_ids = reason_ids;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}
