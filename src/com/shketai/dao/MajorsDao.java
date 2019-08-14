package com.shketai.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Majors;

public class MajorsDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 选课时先查询所有专业
	 * @return
	 */
	public List<Majors> findAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Majors ");
		List<Majors> list = query.list();
		return list;
	}
	
	public int add(Majors majors){
		Session session = sessionFactory.getCurrentSession();
		session.save(majors);
		return 1;
	}
	
	public int update(Majors majors){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Majors  where id=:id");
		query.setInteger("id", majors.getId());
		List<Majors> list = query.list();
		Majors oldmajors = list.get(0);
		majors.setId(oldmajors.getId());
		session.clear();
		session.update(majors);
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	public Majors select(int major_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Majors  where id=:id");
		query.setInteger("id", major_id);
		List<Majors> list = query.list();
		 return list.get(0);
	
		
	
	}
	
	public int delete(Majors majors){
		Session session = sessionFactory.getCurrentSession();
		session.delete(majors);
		return 1;
	}
	
	
	/**
	 * @return
	 * 查询指定id
	 */
	public Majors queryId(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Majors  where id=:id");
		query.setInteger("id", id);
		
		return (Majors) query.uniqueResult();
	}
	
	/**
	 * @return Sql
	 * 查询指定id
	 */
	public Majors querySqlId(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(" select * from Majors  where id=:id");
		query.setInteger("id", id);
		
		query.addEntity(Majors.class);
		
		return (Majors) query.uniqueResult();
	}
	
	
}
