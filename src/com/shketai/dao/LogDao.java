package com.shketai.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.DBLogger;

public class LogDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 记录日志
	 */
	public void log(DBLogger dbLogger) {
		Session session = sessionFactory.getCurrentSession();
		session.save(dbLogger);
	}

	/**
	 * 查询日志
	 * @return
	 */
	public List<DBLogger> findAll(int page){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from DBLogger order by id desc");
		query.setFirstResult((page-1)*10);
		query.setMaxResults(10);
		List<DBLogger> list = query.list();
		return list;
	}
	
	/**
	 * 查询日志(全部)
	 * @return
	 */
	public List<DBLogger> findAlls(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from DBLogger order by id desc");
		List<DBLogger> list = query.list();
		return list;
	}
	
	/**
	 * 查询日志(日期)
	 * @return
	 */
	public List<DBLogger> findDate(String startDate,String endDate){
		Session session = sessionFactory.getCurrentSession();
		StringBuffer hql=new StringBuffer("from DBLogger where 1=1");
		if(startDate!=null&&endDate!=null) {
			hql.append(" and DATE_FORMAT(time,'%Y-%m-%d') between '"+startDate+"' and '"+endDate+"'");
		}
		hql.append(" order by id desc");
		Query query = session.createQuery(hql.toString());
		List<DBLogger> list = query.list();
		return list;
	}
	
	public int findCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from DBLogger ");
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}
}
