package com.shketai.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.Info;
import com.shketai.entity.PageBean;
import com.shketai.service.SubjectrefundService;

public class SubjectrefundAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private SubjectrefundService subjectrefundService;
	private List<Object[]> result;
	private int page;
	private PageBean<Object[]> pb;
	
	
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
	public SubjectrefundService getSubjectrefundService() {
		return subjectrefundService;
	}
	public void setSubjectrefundService(SubjectrefundService subjectrefundService) {
		this.subjectrefundService = subjectrefundService;
	}
	
	public List<Object[]> getResult() {
		return result;
	}
	public void setResult(List<Object[]> result) {
		this.result = result;
	}
	public String getSubjectrefund(){
		result=subjectrefundService.getSubjectrefund();
		return SUCCESS;
		
	}
	/**
	 *缴费明细分页
	 * @return
	 */
	public String find2(){
		if(page==0){
			page = 1; //如果没有发送page参数，默认查询第一条
		}
		pb = subjectrefundService.find(page);
		return SUCCESS;
	}

}
