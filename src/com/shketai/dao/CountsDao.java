package com.shketai.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Classinfo;

public class CountsDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public int CountStudentByClassInfoId(int classinfo_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Info  where classinfo.id=?");
		query.setInteger(0, classinfo_id);
		Long total = (Long) query.uniqueResult();
		System.out.println(total.intValue());
		return total.intValue();
	}

	public Classinfo findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Classinfo where id=?");
		query.setInteger(0, id);
		List<Classinfo> list = query.list();
		Classinfo classinfo = list.get(0);
		return null;
	}
}
