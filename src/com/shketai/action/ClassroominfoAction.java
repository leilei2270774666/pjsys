package com.shketai.action;

import java.text.ParseException;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Classroominfo;
import com.shketai.service.ClassroominfoService;
import com.shketai.service.LogService;

public class ClassroominfoAction  extends ActionSupport implements ModelDriven<Classroominfo>{
	
	private static final long serialVersionUID = 1L;
	
	private Classinfo classinfo = new Classinfo();
	
	private int result;
	
	private int classroom_id;
	private int classroominfo_id;
	private String results;
	private Classroominfo classroominfo =new Classroominfo();
	
	private ClassroominfoService classroominfoService;
	
	private List<Classroominfo> classroominfos;
	
	private LogService logService;
	
	/**
	 * 查询所有教室时间段
	 * @return
	 */
	public String findAll(){
		classroominfos = classroominfoService.findAll();
		return SUCCESS;
	}
	
	public String findAll1(){
		classroominfos = classroominfoService.findAll1(classroom_id);
		return SUCCESS;
	}
	/**
	 * 新增教室时间段
	 * @return
	 */
	public String add(){
		logService.addLog("教室管理", "新增教室时间段");
		result = classroominfoService.add(classroominfo, classroom_id);
		return SUCCESS;
	}
	
	/**
	 * 教室占用率
	 * @return
	 * @throws ParseException 
	 */
	public String percent() throws ParseException{
		logService.addLog("教室管理", "教室占用率");
		results = classroominfoService.percent(classroom_id);
		return SUCCESS;
	}

	public Classinfo getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(Classinfo classinfo) {
		this.classinfo = classinfo;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	
	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public int getClassroom_id() {
		return classroom_id;
	}

	public void setClassroom_id(int classroom_id) {
		this.classroom_id = classroom_id;
	}

	public Classroominfo getClassroominfo() {
		return classroominfo;
	}

	public void setClassroominfo(Classroominfo classroominfo) {
		this.classroominfo = classroominfo;
	}

	public ClassroominfoService getClassroominfoService() {
		return classroominfoService;
	}

	public void setClassroominfoService(ClassroominfoService classroominfoService) {
		this.classroominfoService = classroominfoService;
	}

	public List<Classroominfo> getClassroominfos() {
		return classroominfos;
	}

	public void setClassroominfos(List<Classroominfo> classroominfos) {
		this.classroominfos = classroominfos;
	}


	public int getClassroominfo_id() {
		return classroominfo_id;
	}

	public void setClassroominfo_id(int classroominfo_id) {
		this.classroominfo_id = classroominfo_id;
	}

	public Classroominfo getModel() {
		return classroominfo;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
}
