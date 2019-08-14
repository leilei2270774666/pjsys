package com.shketai.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.Teacher;
import com.shketai.service.TeacherService;

public class TeacherAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private Teacher teacher;
	
	private TeacherService teacherService;
	
	private String username;
	
	private String password;
	
	private int role;
	
	private int result;
	
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public String execute() throws Exception {
		Teacher t = teacherService.login(username,password,role);
		if(t != null){
			//登录成功，把登录成功的用户名和角色存放到session里
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("username", username);
			session.put("id", t.getId());
			session.put("teacher_id", t.getId());
			session.put("role",role);
			teacher = t;
			result  =1;
			
		}
			return SUCCESS;
			
	}
		

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}


}
