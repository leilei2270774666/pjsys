package com.shketai.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.Reasons;
import com.shketai.service.ReasonsService;

public class ReasonsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private List<Reasons> reason;
	
	private ReasonsService reasonsService;
	
	public String findAll(){
		reason = reasonsService.findAll();
		return SUCCESS;
	}

	public List<Reasons> getReason() {
		return reason;
	}

	public void setReason(List<Reasons> reason) {
		this.reason = reason;
	}

	public void setReasonsService(ReasonsService reasonsService) {
		this.reasonsService = reasonsService;
	}

	
	
}
