package com.shketai.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Reasons;

public class ReasonsDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Reasons> findAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Reasons");
		List<Reasons> list = query.list();
		return list;
	}

	
	public Reasons queryId(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Reasons where id = :id");
		query.setInteger("id", id);
		
		return (Reasons) query.uniqueResult();
	}
	
}
