package com.shketai.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.ClassRoom;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Classroominfo;
import com.shketai.entity.Subject;
import com.shketai.service.ClassroomService;
import com.shketai.service.LogService;

public class ClassroomAction  extends ActionSupport implements ModelDriven<ClassRoom>{

	private static final long serialVersionUID = 1L;
	
	private int result;
	
	private List<ClassRoom> classRoom;
	
	private ClassRoom classroom = new ClassRoom();
	
	private ClassroomService classroomService;
	
	private LogService logService;
	
	/**
	 * 新增教室
	 * @return
	 */
	public String add(){
		
		logService.addLog("教室管理", "新增");
		
		result = classroomService.add(classroom);
		return SUCCESS;
	}
	
	/**
	 * 查询所有教室
	 * @return
	 */
	public String findAll(){
		
		logService.addLog("教室管理", "查询");
		
		classRoom = classroomService.findAll();
		return SUCCESS;
	}
	
	
	public ClassRoom getModel() {
		return classroom;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<ClassRoom> getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(List<ClassRoom> classRoom) {
		this.classRoom = classRoom;
	}
	public ClassRoom getClassroom() {
		return classroom;
	}
	public void setClassroom(ClassRoom classroom) {
		this.classroom = classroom;
	}
	public void setClassroomService(ClassroomService classroomService) {
		this.classroomService = classroomService;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public ClassroomService getClassroomService() {
		return classroomService;
	}

	

	
}
