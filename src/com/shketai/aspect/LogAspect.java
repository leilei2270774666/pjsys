package com.shketai.aspect;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;

import com.shketai.entity.DBLogger;
import com.shketai.entity.Teacher;
import com.shketai.service.LogService;
import com.shketai.service.TeacherService;

public class LogAspect {

	private LogService logService;
	
	private TeacherService teacherService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	
	
	public TeacherService getTeacherService() {
		return teacherService;
	}



	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}



	public LogService getLogService() {
		return logService;
	}



	/**
	 * 记录日志
	 */
	public void writeLog(JoinPoint jp ) {
		if(jp.getSignature().getName().equals("log")){
			//记录日志的方法，不被拦截
			return;
		}
		DBLogger dbLogger = new DBLogger();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Object id = request.getSession().getAttribute("id");
		Object role = request.getSession().getAttribute("role");
		if(id == null || role == null){
			//没有登录或者不是老师
			return;
		}else{
			
			Object teacher_id = request.getSession().getAttribute("teacher_id");
			
			if(teacher_id != null) {

				try {
					Teacher teacher = new Teacher();
					teacher.setId(Integer.valueOf(teacher_id.toString()));
					dbLogger.setTeacher(teacher);
					dbLogger.setTime(new Date());
					String target = jp.getTarget().getClass().getSimpleName();
					dbLogger.setMokuai(target.substring(0, target.lastIndexOf("Dao")));
					dbLogger.setEventType(jp.getSignature().getName());
					logService.writeLog(dbLogger);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			
				
				
			
			
		}
		
	}
}
