package com.shketai.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.shketai.dao.AmountDao;
import com.shketai.entity.Classinfo;

public class AmountService {
	private AmountDao amountDao;
	
	public AmountDao getAmountDao() {
		return amountDao;
	}
	public void setAmountDao(AmountDao amountDao) {
		this.amountDao = amountDao;
	}
	//计算总收入金额
	public double findAllMoney(Date dateTime){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateTime1=null;
		if(dateTime!=null) {
			dateTime1=sdf.format(dateTime);
		}
		return amountDao.findAllMoney(dateTime1);
	}
	
	//计算总退款金额
		public double findAllBackmoney(Date dateTime){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String dateTime1=null;
			if(dateTime!=null) {
				dateTime1=sdf.format(dateTime);
			}
			return amountDao.findAllBackmoney(dateTime1);
			
		}
	
	//计算总收入金额
		public double findAllMoney(){
			return amountDao.findAllMoney();
		}
	//计算总退款金额
	public double findAllBackmoney(){
		return amountDao.findAllBackmoney();
		
	}
	
}
