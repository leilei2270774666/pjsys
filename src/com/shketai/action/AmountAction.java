package com.shketai.action;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Info;
import com.shketai.service.AmountService;
import com.shketai.service.LogService;

public class AmountAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private Double result;
	private Info info;
	private List<Classinfo> classinfos;
	private AmountService amountService;
	
	private LogService logService;
	
	private int classinfo_id;
	
	private Date dateTime;    //审核时间
	
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public int getClassinfo_id() {
		return classinfo_id;
	}
	public void setClassinfo_id(int classinfo_id) {
		this.classinfo_id = classinfo_id;
	}

	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}
	public AmountService getAmountService() {
		return amountService;
	}
	public void setAmountService(AmountService amountService) {
		this.amountService = amountService;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public List<Classinfo> getClassinfos() {
		return classinfos;
	}
	public void setClassinfos(List<Classinfo> classinfos) {
		this.classinfos = classinfos;
	}
	public String findAllMoney(){
		
		logService.addLog("财务数据汇总-收支汇总", "总收入额查询");
		
		result=amountService.findAllMoney(dateTime);
		
		return SUCCESS;
		
	}
	
	public String findAllBackmoney(){
		logService.addLog("财务数据汇总-收支汇总", "退款金额查询");
		result=amountService.findAllBackmoney(dateTime);
		return SUCCESS;
		
	}
	public LogService getLogService() {
		return logService;
	}
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
		
}


