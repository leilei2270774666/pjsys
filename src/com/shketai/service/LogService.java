package com.shketai.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;

import com.shketai.dao.LogDao;
import com.shketai.entity.DBLogger;
import com.shketai.entity.PageBean;
import com.shketai.entity.Teacher;

public class LogService {
	
	private LogDao logDao;
	
	

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}



	public void writeLog(DBLogger dbLogger) {
		logDao.log(dbLogger);
	}	

	
	public PageBean<DBLogger> findAll(int page){
		PageBean<DBLogger> pb = new PageBean<DBLogger>();
		List<DBLogger> list = logDao.findAll(page);
		 pb.setDatas(list);
		 int total =logDao.findCount();
			pb.setTotal(total);
			return pb;
	}
	
	public List<DBLogger> findAlls(){
		List<DBLogger> list = logDao.findAlls();
			return list;
	}
	
	public List<DBLogger> findDate(Date startDate,Date endDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String startDate1=null;
		String endDate1=null;
		if(startDate!=null) {
			startDate1=sdf.format(startDate);
		}
		if(endDate!=null) {
			endDate1=sdf.format(endDate);
		}
		return logDao.findDate(startDate1,endDate1);
	}
	
	
	/**
	 * 记录日志
	 */
	public void addLog(String mokuai ,String eventType) {
		
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
//					操作哪个模块
					dbLogger.setMokuai(mokuai);
//					操作 类型
					dbLogger.setEventType(eventType);
					writeLog(dbLogger);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			
		}
		
	}
	
	
	
	
}
