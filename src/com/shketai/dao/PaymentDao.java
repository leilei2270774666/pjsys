package com.shketai.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PaymentDao{
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * 获取缴费明细
	 */

	public List<Object[]> getPayment(String dt,Integer flag,Integer classinfo_id){
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select stu.stuName, stu.personalNum, u.mobilephone ,sub.subject,c.classname,c.money,");
		if(flag==3) {
			sql.append("i.s_j_time,i.h_j_time,i.jPay ");
		}else if(flag==6) {
			sql.append("i.s_t_time,i.h_t_time,i.tPay ");
		}else if(flag==9) {
			sql.append("i.s_x_time,i.h_x_time,i.xPay ");
		}
		sql.append(" from Student stu,Subject sub,Classinfo c,Info i , user u ");
		sql.append(" where c.subject_id=sub.id  ");
		sql.append(" and stu.id=i.stu_id  ");
		sql.append(" and stu.user_id = u.id ");
		sql.append(" and c.id=i.classinfo_id ");
		sql.append(" and i.flag="+flag+" ");
		if(dt!=null) {
			if(flag==3) {
				sql.append(" and DATE_FORMAT(i.h_j_time,'%Y-%m-%d')=DATE_FORMAT('"+dt+"','%Y-%m-%d')");
			}else if(flag==6) {
				sql.append(" and DATE_FORMAT(i.h_t_time,'%Y-%m-%d')=DATE_FORMAT('"+dt+"','%Y-%m-%d')");
			}else if(flag==9) {
				sql.append(" and DATE_FORMAT(i.h_x_time,'%Y-%m-%d')=DATE_FORMAT('"+dt+"','%Y-%m-%d')");
			}
		}
		if(classinfo_id!=null) {
			sql.append(" and i.classinfo_id="+classinfo_id);
		}
		sql.append(" order by c.classname");
		 Query query=session.createSQLQuery(sql.toString());
		 List<Object[]> list = query.list();
		 
		return  list; 
		
	}
	
	public List<Object[]> getPayment1(int classinfo_id,String dt,Integer flag){
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select stu.stuName, stu.personalNum, u.mobilephone ,sub.subject,c.classname,c.money,i.s_j_time,i.h_j_time,i.jPay  ");
		sql.append(" from Student stu,Subject sub,Classinfo c,Info i , user u ");
		sql.append(" where c.subject_id=sub.id  ");
		sql.append(" and stu.id=i.stu_id  ");
		sql.append(" and stu.user_id = u.id ");
		sql.append(" and c.id=i.classinfo_id ");
		sql.append(" and i.flag="+flag+" ");
		sql.append(" and c.id = :classinfo_id ");
		if(dt!=null) {
			if(flag==3) {
				sql.append(" and DATE_FORMAT(i.h_j_time,'%Y-%m-%d')=DATE_FORMAT('"+dt+"','%Y-%m-%d')");
			}else if(flag==6) {
				sql.append(" and DATE_FORMAT(i.h_t_time,'%Y-%m-%d')=DATE_FORMAT('"+dt+"','%Y-%m-%d')");
			}else if(flag==9) {
				sql.append(" and DATE_FORMAT(i.h_x_time,'%Y-%m-%d')=DATE_FORMAT('"+dt+"','%Y-%m-%d')");
			}
		}
		
		SQLQuery query=session.createSQLQuery(sql.toString());
		query.setInteger("classinfo_id", classinfo_id);
		 List<Object[]> list = query.list();
		return  list; 
		
	}
	/**
	 *获取缴费信息进行分页
	 * @param id
	 * @return
	 */
	public List<Object[]> find(int page){
		String sql = " select stu.stuName,sub.subject,c.classname,c.money from Student stu,Subject sub,Classinfo c,Info i where c.subject_id=sub.id and stu.id=i.stu_id and c.id=i.classinfo_id";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.setFirstResult((page-1)*10);
		query.setMaxResults(10);
		List<Object[]> list = query.list();
		return list;
	}
	
	public List<Object[]> find1(int page,int classinfo_id ,int flag){
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select stu.stuName,sub.subject,c.classname,c.money  ");
		if(flag==3) {
			sql.append(",i.s_j_time,i.h_j_time,i.jPay");
		}
		if(flag==9) {
			sql.append(",i.s_x_time,i.h_x_time,i.xPay");
		}
		if(flag==6) {
			sql.append(",i.s_t_time,i.h_t_time,i.tPay");
		}
		sql.append(" from Student stu,Subject sub,Classinfo c,Info i  ");
		sql.append(" where c.subject_id=sub.id  ");
		sql.append(" and stu.id=i.stu_id  ");
		sql.append(" and c.id=i.classinfo_id  ");
		sql.append(" and classinfo_id= :classinfo_id ");
		sql.append(" and i.flag = :flag ");

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql.toString());
		
		query.setInteger("classinfo_id", classinfo_id);
		query.setInteger("flag", flag);
		
		query.setFirstResult((page-1)*10);
		query.setMaxResults(10);
		
		List<Object[]> list = query.list();
		return list;
	}
	public int findCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(" select count(*) from Student stu,Subject sub,Classinfo c,Info i where c.subject_id=sub.id and stu.id=i.stu_id and c.id=i.classinfo_id");
		BigInteger total = (BigInteger) query.uniqueResult();
		return total.intValue();
	}

	public int findCount1(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(" select count(*) from Student stu,Subject sub,Classinfo c,Info i where c.subject_id=sub.id and stu.id=i.stu_id and c.id=i.classinfo_id and classinfo_id=?");
		query.setInteger(0, classinfo_id);
		BigInteger total = (BigInteger) query.uniqueResult();
		return total.intValue();
	}
	
	public int sqlFindCount(int classinfo_id, int flag) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from Student stu,Subject sub,Classinfo c,Info i  ");
		sql.append(" where c.subject_id=sub.id  ");
		sql.append(" and stu.id=i.stu_id  ");
		sql.append(" and c.id=i.classinfo_id  ");
		sql.append(" and classinfo_id = :classinfo_id ");
		sql.append(" and i.flag = :flag ");
		
		Query query = session.createSQLQuery(sql.toString());
		
		query.setInteger("classinfo_id", classinfo_id);
		query.setInteger("flag", flag);
		
		BigInteger total = (BigInteger) query.uniqueResult();
		return total.intValue();
	}
	
	public int jxtCount(int flag) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from Student stu,Subject sub,Classinfo c,Info i  ");
		sql.append(" where c.subject_id=sub.id  ");
		sql.append(" and stu.id=i.stu_id  ");
		sql.append(" and c.id=i.classinfo_id  ");
		sql.append(" and i.flag = :flag ");
		
		Query query = session.createSQLQuery(sql.toString());
		query.setInteger("flag", flag);
		
		BigInteger total = (BigInteger) query.uniqueResult();
		return total.intValue();
	}
	
}
