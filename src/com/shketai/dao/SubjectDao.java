package com.shketai.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Interview;
import com.shketai.entity.Majors;
import com.shketai.entity.Subject;

public class SubjectDao {

private SessionFactory sessionFactory;
private MajorsDao majorsDao;

	
	public void setMajorsDao(MajorsDao majorsDao) {
	this.majorsDao = majorsDao;
}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Subject add(Subject subject,int major_id){
		Session session = sessionFactory.getCurrentSession();
		
		Majors majors = majorsDao.select(major_id);
		subject.setMajors(majors);
		
		session.save(subject);
		
		return subject;
	}
	
	public int update(Subject subject){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Subject where id=:id");
		query.setInteger("id", subject.getId());
		List<Subject> list = query.list();
		Subject oldSubject = list.get(0);
		subject.setId(oldSubject.getId());
		subject.setMajors(oldSubject.getMajors());
		session.clear();
		session.update(subject);
		return 1;
	}
	
	/**
	 * @param subject
	 * @return
	 */
	public int myUpdateSql(Subject subject , int major_id){
		Session session = sessionFactory.getCurrentSession();
		
		Majors majors = majorsDao.queryId(major_id);
		
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE subject ");
		sql.append(" SET subject = :subject , interview = :interview  , introduction = :introduction , major_id = :major_id ");
		sql.append(" WHERE id = :id   ");
		
		SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
		sqlQuery.setString("subject", subject.getSubject());
		sqlQuery.setInteger("interview", subject.getInterview());
		sqlQuery.setString("introduction", subject.getIntroduction());
		sqlQuery.setInteger("major_id", major_id);
		
		sqlQuery.setInteger("id", subject.getId());
		
		sqlQuery.executeUpdate();
		
		return 1;
	}
	
	public int delete(Subject subject){
		Session session = sessionFactory.getCurrentSession();
		session.delete(subject);
		return 1;
	}
	
	/**
	 * 选课时先查询所有课程
	 * @return
	 */
	public List<Subject> findAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Subject ");
		List<Subject> list = query.list();
		return list;
	}
	
	/**
	 * 按照专业选课时先查询所有课程
	 * @return
	 */
	public List<Subject> findAll1(int major_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Subject where major_id=?  ");
		query.setInteger(0, major_id);
		List<Subject> list = query.list();
		if(list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	
	
	/**
	 * id查询
	 * @return
	 */
	public Subject findId(int id){
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("select id , subject  from Subject where id=?  ");
		query.setInteger(0, id);
		
		Subject subject = new Subject();
		
		List<Object[]> list = query.list();
		if(list.size()>0) {
			Object[] obj = list.get(0);
			
			int ids = Integer.parseInt(obj[0].toString());
			String subjectStr = obj[1].toString();
			
			subject.setId(ids);
			subject.setSubject(subjectStr);
		}
		
		return subject;
	}
	
}
