package com.shketai.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.Teacher;
import com.shketai.service.AdministratorService;
import com.shketai.service.LogService;

/**
 * @author Administrator
 *
 */
public class AdministratorAction extends ActionSupport implements ModelDriven<Teacher>{

	private static final long serialVersionUID = 1L;
	
	private int result;
	
	private int id;
	
	private String role;
	
	private String username;
	
	private String password;
	
	private AdministratorService administratorService; 
	
	private Teacher teacher = new Teacher(); 
	
	private List<Teacher> teachers;
	
	private LogService logService;
	
	public void setAdministratorService(AdministratorService administratorService) {
		this.administratorService = administratorService;
	}

	/**
	 * 新增账户
	 * @return
	 */
	public String add(){
		logService.addLog("账户管理", "添加");
		administratorService.add(teacher);
		result = 1;
		return SUCCESS;
	}
	
	/**
	 * 更新账户
	 * @return
	 */
	public String update(){
		logService.addLog("账户管理", "修改");
		administratorService.update(teacher);
		result = 1;
		return SUCCESS;
	}
	
	/**
	 * 删除账户
	 * @return
	 */
	public String delete(){
		logService.addLog("账户管理", "删除");
		administratorService.delete(teacher);
		result = 1;
		return SUCCESS;
	}
	/**
	 * 
	 * @return
	 */
	public String findAll(){
		logService.addLog("账户管理", "查询");
		teachers = administratorService.findAll();
		return SUCCESS;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Teacher getModel() {
		// TODO Auto-generated method stub
		return teacher;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	
	
}
