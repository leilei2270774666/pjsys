package com.shketai.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Classinfo;
import com.shketai.entity.Info;

public class SubjectbalanceDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * 科目收支明细
	 */
	public List<Object[]> getSubjectbalance(){
		String sql= "SELECT t1.SUBJECT,t2.srje,t2.jfrs,t3.tkje,t3.tkrs FROM"+
				"(SELECT SUBJECT FROM SUBJECT) t1 LEFT JOIN ("+
				"SELECT s. SUBJECT AS SUBJECT,sum(c.money) AS srje,count(i.id) AS jfrs FROM info i,SUBJECT s,classinfo c WHERE i.classinfo_id = c.id AND c.subject_id = s.id AND (i.flag = 3 OR i.flag = 8) GROUP BY SUBJECT) t2 ON t1. SUBJECT = t2. SUBJECT "+
			"LEFT JOIN (SELECT s. SUBJECT AS SUBJECT,sum(c.money) AS tkje,count(i.id) AS tkrs FROM info i,SUBJECT s,classinfo c WHERE i.classinfo_id = c.id AND c.subject_id = s.id AND i.flag = 6 GROUP BY SUBJECT) t3 ON t1. SUBJECT = t3. SUBJECT";
					Session session = sessionFactory.getCurrentSession();
					Query query = session.createSQLQuery(sql);
		 List<Object[]> list = query.list();
		return list;
		
	}
	/**
	 *获取科目收支信息进行分页
	 * @param id
	 * @return
	 */
	public List<Object[]> find(int page,String subject){
		StringBuffer sql=new StringBuffer("SELECT t1.SUBJECT,t2.srje,t2.jfrs,t3.tkje,t3.tkrs FROM"+
	"(SELECT SUBJECT FROM SUBJECT) t1 LEFT JOIN ("+
	"SELECT s. SUBJECT AS SUBJECT,sum(c.money) AS srje,count(i.id) AS jfrs FROM info i,SUBJECT s,classinfo c WHERE i.classinfo_id = c.id AND c.subject_id = s.id AND (i.flag = 3 OR i.flag = 8) GROUP BY SUBJECT) t2 ON t1. SUBJECT = t2. SUBJECT "
	+ "LEFT JOIN (SELECT s. SUBJECT AS SUBJECT,sum(c.money) AS tkje,count(i.id) AS tkrs FROM info i,SUBJECT s,classinfo c WHERE i.classinfo_id = c.id AND c.subject_id = s.id AND i.flag = 6 GROUP BY SUBJECT) t3 ON t1. SUBJECT = t3. SUBJECT");
		if(subject!=null&&subject!="") {
			sql.append(" where t1.SUBJECT like:subject");
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql.toString());
		if(subject!=null&&subject!="") {
			query.setString("subject", "%"+subject+"%");
		}
		query.setFirstResult((page-1)*10);
		query.setMaxResults(10);
		List<Object[]> list = query.list();
		return list;
	}
	public int findCount(String subject) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer hql=new StringBuffer("select count(*)  from subject");
		if(subject!=null&&subject!="") {
			hql.append(" where subject=:subject");
		}
		Query query = session.createSQLQuery(hql.toString());
		if(subject!=null&&subject!="") {
			query.setString("subject", subject);
		}
		BigInteger total = (BigInteger) query.uniqueResult();
		return total.intValue();
	}
}
