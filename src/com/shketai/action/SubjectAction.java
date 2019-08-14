package com.shketai.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.Subject;
import com.shketai.service.LogService;
import com.shketai.service.SubjectService;

public class SubjectAction extends ActionSupport implements ModelDriven<Subject>{

	private static final long serialVersionUID = 1L;
	
	private int result;
	
	private int id;
	
	private String subject;
	
	private Subject subject1 = new Subject();
	
	private List<Subject> subjects;
	
	private SubjectService subjectService;
	
	private LogService logService;
	
	private int major_id;
	
	/**
	 * 新增科目
	 * @param subject
	 * @return
	 */
	public String add(){
		logService.addLog("科目管理", "添加");
		
		result = subjectService.add(subject1, major_id);
		return SUCCESS;
	}
	
	/**
	 * 修改科目信息
	 * @param subject
	 * @return
	 * 修改subject对象同时 更新 interview对象
	 */
	public String update(){
		logService.addLog("科目管理", "修改");
		
		result = subjectService.update(subject1,major_id);
		return SUCCESS;
	}
	
	/**
	 * 删除科目
	 * @param subject
	 * @return
	 */
	public String delete(){
		logService.addLog("科目管理", "删除");
		
		subjectService.delete(subject1);
		result = 1;
		return SUCCESS;
	}
	public String findAll(){
		logService.addLog("科目管理", "查询");
		
		subjects = subjectService.findAll();
		return SUCCESS;
	}

	public String findAll1(){
		subjects = subjectService.findAll1(major_id);
		return SUCCESS;
	}
	public Subject getModel() {
		return subject1;  
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Subject getSubject1() {
		return subject1;
	}

	public void setSubject1(Subject subject1) {
		this.subject1 = subject1;
	}

	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public int getMajor_id() {
		return major_id;
	}

	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public SubjectService getSubjectService() {
		return subjectService;
	}
	
	
	
}
