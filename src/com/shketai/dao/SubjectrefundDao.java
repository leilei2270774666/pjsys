package com.shketai.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Info;

public class SubjectrefundDao {
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * 科目退款明细
	 * @return
	 */
	public List<Object[]> getSubjectrefund(){
		Session session = sessionFactory.getCurrentSession();
		 Query query=session.createSQLQuery("select s.`subject`,c.money from info i,`subject` s,classinfo c where i.classinfo_id=c.id and c.subject_id=s.id and i.flag=6");
		 List<Object[]> list = query.list();
		return list;
		
	}
	/**
	 *获取科目退款进行分页
	 * @param id
	 * @return
	 */
	public List<Object[]> find(int page){
		String sql= "select s.`subject`,c.money from info i,`subject` s,classinfo c where i.classinfo_id=c.id and c.subject_id=s.id and i.flag=6";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.setFirstResult((page-1)*10);
		query.setMaxResults(10);
		List<Object[]> list = query.list();
		return list;
	}
	public int findCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(*)  from info i,`subject` s,classinfo c where i.classinfo_id=c.id and c.subject_id=s.id and i.flag=6");
		BigInteger total = (BigInteger) query.uniqueResult();
		return total.intValue();
	}

}
