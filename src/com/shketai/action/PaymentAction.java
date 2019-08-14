package com.shketai.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.Info;
import com.shketai.entity.PageBean;
import com.shketai.entity.Student;
import com.shketai.service.LogService;
import com.shketai.service.PaymentService;

public class PaymentAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private List<Object[]> result;
	private PaymentService paymentService;
	private int page;
	private PageBean<Object[]> pb;
	
	private int classinfo_id;
	
	private int flag;
	
	private Integer countRen;    //总人数
	
	private LogService logService;
	
	
	public LogService getLogService() {
		return logService;
	}
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public Integer getCountRen() {
		return countRen;
	}
	public void setCountRen(Integer countRen) {
		this.countRen = countRen;
	}
	public PageBean<Object[]> getPb() {
		return pb;
	}
	public void setPb(PageBean<Object[]> pb) {
		this.pb = pb;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<Object[]> getResult() {
		return result;
	}
	public void setResult(List<Object[]> result) {
		this.result = result;
	}
	public PaymentService getPaymentService() {
		return paymentService;
	}
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	public String getPayment(){
		result=paymentService.getPayment(null,null,null);
		return SUCCESS;
		
	}
	/**
	 *缴费明细分页
	 * @return
	 */
	public String find(){
		logService.addLog("财务数据汇总-缴/续/退费明细", "查询");
		if(page==0){
			page = 1; //如果没有发送page参数，默认查询第一条
		}
		pb = paymentService.find(page);
		return SUCCESS;
	}
	
	/**
	 *缴费明细分页
	 * @return
	 */
	public String find1(){
		logService.addLog("财务数据汇总-缴/续/退费明细", "查询");
		if(page==0){
			page = 1; //如果没有发送page参数，默认查询第一条
		}
//        flag = 1 = '已报名未缴费';
//        flag = 2 = '已申请缴费,未缴费';
//        flag = 3 = '已缴费';
//        flag = 4 = '已退班';
//        flag = 5 = '已申请退费';
//        flag = 6 = '已成功退费';
//        flag = 7 = '未申请续费';
//        flag = 8 = '已申请续费';
//        flag = 9 = '已成功续费';
		
		pb = paymentService.find1( page, classinfo_id, flag);
		return SUCCESS;
	}
	
	/**
	 * 查询缴/续/退费总人数
	 * @return
	 */
	public String jxtCount() {
		logService.addLog("财务数据汇总-缴/续/退费明细", "总人数查询");
		this.setCountRen(paymentService.jxtCount(flag));
		return SUCCESS;
	}
	
	public int getClassinfo_id() {
		return classinfo_id;
	}
	public void setClassinfo_id(int classinfo_id) {
		this.classinfo_id = classinfo_id;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
	
}
