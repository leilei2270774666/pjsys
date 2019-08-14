package com.shketai.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Classinfo;
import com.shketai.entity.Info;
import com.shketai.entity.Student;

public class AmountDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 查询所有学生缴费金额
	 * @return 
	 */

	//根据classinfo_id 计算出总收取金额

		public double findAllMoney(String dateTime){
			StringBuffer hql =new StringBuffer("select sum(c.money) FROM classinfo c,info i where (i.flag=3 OR i.flag=9) AND i.classinfo_id=c.id ");
			if(dateTime!=null && dateTime!="") {
				hql.append("and (DATE_FORMAT(i.h_j_time,'%Y-%m-%d')=DATE_FORMAT('"+dateTime+"', '%Y-%m-%d') or DATE_FORMAT(i.h_x_time,'%Y-%m-%d')=DATE_FORMAT('"+dateTime+"', '%Y-%m-%d') or DATE_FORMAT(i.h_t_time,'%Y-%m-%d')=DATE_FORMAT('"+dateTime+"', '%Y-%m-%d'))");
			}
			Session session = sessionFactory.getCurrentSession();
			Query query=session.createSQLQuery(hql.toString());
			//Query query=session.createQuery("select sum(money) from classinfo where id in(select classinfo_id from info where flag=3 or flag=8)");
			
			Double total = (Double) query.uniqueResult();
			if(total == null){
				return 0.0;
			}
			return total.doubleValue();
		
		//	return  Double.parseDouble(query.uniqueResult().toString()) ;
			
		}
		//根据classinfo_id计算出总退费金额
		public double findAllBackmoney(String dateTime){
			Session session = sessionFactory.getCurrentSession();
			StringBuffer hql=new StringBuffer("select sum(c.money) FROM classinfo c,info i where (i.flag=6) AND i.classinfo_id=c.id ");
			if(dateTime!=null&&dateTime!="") {
				hql.append("and (DATE_FORMAT(i.h_j_time,'%Y-%m-%d')=DATE_FORMAT('"+dateTime+"', '%Y-%m-%d') or DATE_FORMAT(i.h_x_time,'%Y-%m-%d')=DATE_FORMAT('"+dateTime+"', '%Y-%m-%d') or DATE_FORMAT(i.h_t_time,'%Y-%m-%d')=DATE_FORMAT('"+dateTime+"', '%Y-%m-%d'))");
			}
			Query query=session.createSQLQuery(hql.toString());
			Double total = (Double) query.uniqueResult();
			if(total == null){
				return 0.0;
			}
			return  Double.parseDouble(query.uniqueResult().toString()) ;
			
		}
		
		//根据classinfo_id 计算出总收取金额

				public double findAllMoney( ){
					String hql = "select sum(c.money) FROM classinfo c,info i where (i.flag=3 OR i.flag=9) AND i.classinfo_id=c.id";
					Session session = sessionFactory.getCurrentSession();
					Query query=session.createSQLQuery(hql);
					//Query query=session.createQuery("select sum(money) from classinfo where id in(select classinfo_id from info where flag=3 or flag=8)");
					
					Double total = (Double) query.uniqueResult();
					if(total == null){
						return 0.0;
					}
					return total.doubleValue();
				
				//	return  Double.parseDouble(query.uniqueResult().toString()) ;
					
				}
				//根据classinfo_id计算出总退费金额
				public double findAllBackmoney(){
					Session session = sessionFactory.getCurrentSession();
					
					Query query=session.createSQLQuery("select sum(c.money) FROM classinfo c,info i where (i.flag=6) AND i.classinfo_id=c.id");
					Double total = (Double) query.uniqueResult();
					if(total == null){
						return 0.0;
					}
					return  Double.parseDouble(query.uniqueResult().toString()) ;
					
				}
	}








