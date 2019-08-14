package com.shketai.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.DBLogger;
import com.shketai.entity.PageBean;
import com.shketai.service.LogService;

public class LogAction  extends ActionSupport{

	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	private List<DBLogger> DBLogger;
	
	private PageBean<DBLogger> pb;

	private int page;
	
	
	public List<DBLogger> getDBLogger() {
		return DBLogger;
	}

	public void setDBLogger(List<DBLogger> dBLogger) {
		DBLogger = dBLogger;
	}
	
	
	public PageBean<DBLogger> getPb() {
		return pb;
	}

	public void setPb(PageBean<DBLogger> pb) {
		this.pb = pb;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * 查询所有日志
	 * @return
	 */
	public String findAll(){
		//logService.addLog("操作日志", "查询");
		pb = logService.findAll(page);
		return SUCCESS;
	}

	
}
