package com.shketai.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Interview;

public class InterviewDao {

private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	/**
	 * 新增面试学科
	 * @param subject_id
	 * @param interviewSubject
	 * @return
	 */
	public int add(int subject_id , String interviewSubject){
		Session session = sessionFactory.getCurrentSession();
		
		Interview interview = new Interview();
		
		interview.setSubject_id( subject_id );
		interview.setInterviewSubject( interviewSubject );
		
		session.save(interview);
		
		return 1;
	}
	
	/**
	 * 新增面试学科
	 * @param interview
	 * @return
	 */
	public int add(Interview interview){
		Session session = sessionFactory.getCurrentSession();
		
		session.save(interview);
		
		return 1;
	}

	public int update(Interview interview){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Interview where id=:id");
		query.setInteger("id", interview.getId());
		List<Interview> list = query.list();
		Interview oldInterview = list.get(0);
		interview.setId(oldInterview.getId());
		//interview.setInterviewSubject(oldInterview.getInterviewSubject());
		session.clear();
		session.update(interview);
		return 1;
	}
	
	/**
	 * @param interview
	 * @return
	 * sql update
	 */
	public int sqlUpdate(Interview interview){
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" UPDATE Interview ");
		sql.append(" SET interviewSubject = :interviewSubject , subject_id = :subject_id ");
		sql.append(" WHERE id = :id ");
		
		SQLQuery sQLQuery = session.createSQLQuery(sql.toString());
		
		sQLQuery.setString("interviewSubject", interview.getInterviewSubject());
		sQLQuery.setInteger("subject_id", interview.getSubject_id());
		sQLQuery.setInteger("id", interview.getId());
		
		sQLQuery.executeUpdate();
		
		return 1;
	}
	
	/**
	 * 查询不需要面试科目
	 * //是否需要面试 1：需要 0：不需要
	 * @return
	 */
	public List<Interview>  findAllInterview(){
		Session session = sessionFactory.getCurrentSession();
		SQLQuery  sQLQuery  = session.createSQLQuery(" select t1.* from Interview t1 , Subject t2 where t1.subject_id = t2.id  and  t2.interview = 1 ");
		
		sQLQuery.addEntity(Interview.class);
		
		List<Interview> list = sQLQuery.list();
		return list;
	}
	
	/**
	 * @param subject_id
	 * @return
	  *  查询指定subject_id 的
	 */
	public Interview  findSubject_id(int subject_id){
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.* from Interview t1 , Subject t2 ");
		sql.append(" where t1.subject_id = t2.id and t2.id = :subject_id ");
		sql.append(" ORDER BY t1.id desc  LIMIT 0,1 ");
		
		SQLQuery  sQLQuery  = session.createSQLQuery(sql.toString());
		sQLQuery.setInteger("subject_id", subject_id);
		
		sQLQuery.addEntity(Interview.class);
		
		return (Interview) sQLQuery.uniqueResult();
	}
	
}
