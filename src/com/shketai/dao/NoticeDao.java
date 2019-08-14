package com.shketai.dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Notice;

/**
 * 通知 dao层
 *
 */
public class NoticeDao {
	/**
	 *  构造方法	
	 */
	public NoticeDao() {
		super();
	}

	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	
	/**
	 * add
	 */
	public int add(Notice notice) {
		Session session = sessionFactory.getCurrentSession();
		
		session.save(notice);
		
		return 1;
	}
	
	
	/**
	 *  第一条 
	 */
	public Notice firstNotice() {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from notice ORDER BY id desc LIMIT 0,1  " );
		
		SQLQuery query = session.createSQLQuery(sql.toString());
		
		query.addEntity(Notice.class);
		
		return (Notice) query.uniqueResult();
	}
	
}
