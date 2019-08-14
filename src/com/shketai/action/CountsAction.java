package com.shketai.action;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.Classinfo;
import com.shketai.service.CountsService;

public class CountsAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;

	private CountsService countsService;
	
	private int id;
	
	private Classinfo classinfo;

	public CountsService getCountsService() {
		return countsService;
	}

	public void setCountsService(CountsService countsService) {
		this.countsService = countsService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		classinfo = countsService.findById(id);
		return SUCCESS;
	}

	public Classinfo getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(Classinfo classinfo) {
		this.classinfo = classinfo;
	}

}
