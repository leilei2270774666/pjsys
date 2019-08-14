package com.shketai.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shketai.dao.PaymentDao;
import com.shketai.entity.Info;
import com.shketai.entity.PageBean;
import com.shketai.entity.Student;

public class PaymentService {
	private PaymentDao paymentDao;
	
	private static SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");

	public PaymentDao getPaymentDao() {
		return paymentDao;
	}

	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}
	
	public List<Object[]> getPayment(Date dt,Integer flag,Integer classinfo_id){
		String dt1 = null;
		if(dt!=null) {
			dt1=sdf.format(dt);
		}
		return paymentDao.getPayment(dt1,flag,classinfo_id);
		
	}
	

	public List<Object[]> getPayment1(int classinfo_id,Date dt,Integer flag){
		String dt1=null;
		if(dt!=null) {
			dt1=sdf.format(dt);
		}
		return paymentDao.getPayment1(classinfo_id,dt1,flag);
		
	}
	/**
	 * 缴费明细分页
	 * @param page
	 * @return
	 */
	public PageBean<Object[]> find(int page){
		PageBean<Object[]> pb = new PageBean<Object[]>();
		List<Object[]> list = paymentDao.find(page);
		pb.setDatas(list);
		int total = paymentDao.findCount();
		pb.setTotal(total);
		return pb;
	}

	/**
	 * 缴费明细分页
	 * @param page
	 * @return
	 */
	public PageBean<Object[]> find1(int page,int classinfo_id,int flag){
		PageBean<Object[]> pb = new PageBean<Object[]>();
		List<Object[]> list = paymentDao.find1( page, classinfo_id , flag);
		pb.setDatas(list);
		int total = paymentDao.sqlFindCount( classinfo_id,  flag);
		pb.setTotal(total);
		return pb;
	}
	
	/**
	 * 缴费明细分页
	 * @param page
	 * @return
	 */
	public Integer jxtCount(int flag){
		Integer total = paymentDao.jxtCount(flag);
		return total;
	}
}
