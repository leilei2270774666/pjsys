package com.shketai.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Administrator;
import com.shketai.entity.Subject;
import com.shketai.entity.Teacher;

public class AdministratorDao {

private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//
	public int add(Teacher teacher){
		Session session = sessionFactory.getCurrentSession();
		session.save(teacher);
		return 1;
	}
	
	public int update(Teacher teacher){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Teacher where id=:id");
		query.setInteger("id", teacher.getId());
		List<Teacher> list = query.list();
		Teacher oldTeacher = list.get(0);
		teacher.setId(oldTeacher.getId());
		session.clear();
		session.update(teacher);
		return 1;
	}
	public int delete(Teacher teacher){
		Session session = sessionFactory.getCurrentSession();
		session.delete(teacher);
		return 1;
	}
	
	public List<Teacher> findAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Teacher ");
		List<Teacher> list = query.list();
		return list;
	}
}
