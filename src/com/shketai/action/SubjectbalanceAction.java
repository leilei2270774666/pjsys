package com.shketai.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Info;
import com.shketai.entity.PageBean;
import com.shketai.service.LogService;
import com.shketai.service.SubjectbalanceService;

public class SubjectbalanceAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private SubjectbalanceService subjectbalanceService;
	private List<Object[]> result;
	private int page;
	private PageBean<Object[]> pb;
	private String subject;   //科目
	private LogService logService;
	
	
	
	public LogService getLogService() {
		return logService;
	}
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public PageBean<Object[]> getPb() {
		return pb;
	}
	public void setPb(PageBean<Object[]> pb) {
		this.pb = pb;
	}
	public SubjectbalanceService getSubjectbalanceService() {
		return subjectbalanceService;
	}
	public void setSubjectbalanceService(SubjectbalanceService subjectbalanceService) {
		this.subjectbalanceService = subjectbalanceService;
	}
	
	public List<Object[]> getResult() {
		return result;
	}
	public void setResult(List<Object[]> result) {
		this.result = result;
	}
	public String getSubjectbalance(){
		result=subjectbalanceService.getSubjectbalance();
		return SUCCESS;
		
	}
	/**
	 *缴费明细分页
	 * @return
	 */
	public String find1(){
		logService.addLog("财务数据汇总-科目收支明细", "查询");
		if(page==0){
			page = 1; //如果没有发送page参数，默认查询第一条
		}
		pb = subjectbalanceService.find(page,subject);
		return SUCCESS;
	}
}
