package com.shketai.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.Majors;
import com.shketai.service.LogService;
import com.shketai.service.MajorsService;

public class MajorsAction extends ActionSupport implements ModelDriven<Majors>{

private static final long serialVersionUID = 1L;
	
	private int id;
	
	private List<Majors> majors;
	
	private Majors major = new Majors();
	
	private MajorsService majorsService;
	
	private LogService logService;
	
	private int result;

	public String findAll(){
		logService.addLog("学科管理", "查询");
		
		majors = majorsService.findAll();
		return SUCCESS;
	}
	
	public String add(){
		logService.addLog("学科管理", "添加");
		
		result = majorsService.add(major);
		return SUCCESS;
		
	}
	
	public String update(){
		logService.addLog("学科管理", "修改");
		
		result = majorsService.update(major);
		return SUCCESS;
	}
	
	public String delete(){
		logService.addLog("学科管理", "删除");
		
		majorsService.delete(major);
		result = 1;
		return SUCCESS;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public MajorsService getMajorsService() {
		return majorsService;
	}

	public void setMajorsService(MajorsService majorsService) {
		this.majorsService = majorsService;
	}

	public List<Majors> getMajors() {
		return majors;
	}

	public void setMajors(List<Majors> majors) {
		this.majors = majors;
	}

	public Majors getMajor() {
		return major;
	}

	public void setMajor(Majors major) {
		this.major = major;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Majors getModel() {
		return major;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	
	
}
