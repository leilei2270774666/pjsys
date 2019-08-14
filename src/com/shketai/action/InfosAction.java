package com.shketai.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.Info;
import com.shketai.service.InfoService;
import com.shketai.service.LogService;

public class InfosAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private int stu_id;
	
	private InfoService infoService;
	
	private List<Info> info;
	
	private LogService logService;
	
	public String findById(){
		logService.addLog("学生管理", "选课查询");
		info = infoService.findById(stu_id);
		return SUCCESS;
	}

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public InfoService getInfoService() {
		return infoService;
	}

	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}

	public List<Info> getInfo() {
		return info;
	}

	public void setInfo(List<Info> info) {
		this.info = info;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

}
