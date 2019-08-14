package com.shketai.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Classinfo;
import com.shketai.entity.Info;
import com.shketai.entity.Interview;
import com.shketai.entity.Interview_info;
import com.shketai.entity.Interview_timeslot;
import com.shketai.entity.Student;
import com.shketai.entity.User;

public class Interview_timeslotDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public int add(Interview_timeslot interview_timeslot,int interview_id){
		
		Session session = sessionFactory.getCurrentSession();
		Interview interview = new Interview();
		interview.setId(interview_id);
		interview_timeslot.setInterview(interview);
		session.save(interview_timeslot);
		return 1;
	}
	
	public int update( Interview_timeslot interview_timeslot) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Interview_timeslot where id=:id ");
		query.setInteger("id", interview_timeslot.getId());
		List<Interview_timeslot> list = query.list();
		Interview_timeslot oldClassinfo = list.get(0);
		interview_timeslot.setId(oldClassinfo.getId());
		interview_timeslot.setInterview(oldClassinfo.getInterview());;
		session.clear();
		session.update(interview_timeslot);
		return 1;
	}
	
	/**
	 * 选面试科目时，当用户选择了某个科目，再查询相关的面试时间段
	 * @param 
	 * @return 
	 */
	public List<Interview_timeslot> findBySubjectInterview(int interview_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Interview_timeslot where interview_id=?");
		query.setInteger(0,  interview_id);
		List list = query.list();
		return list;
	}
	
	/**
	 * 预约面试
	 * @param 
	 * @param interview_id
	 */
	public void selectInterview(int stu_id,int Interview_timeslot_id){
		Session session = sessionFactory.getCurrentSession();
		Interview_info interview_info=  new Interview_info();
		//学生对象
		Student stu = new Student();
		stu.setId(stu_id);
		interview_info.setStudent(stu);
		
		//面试信息对象
		Interview_timeslot interview_timeslot = new Interview_timeslot();
		interview_timeslot.setId(Interview_timeslot_id);
		interview_info.setInterview_timeslot(interview_timeslot);;
		interview_info.setResult(0);  //0 为等待面试
		interview_info.setSqDt(new Date());
		session.save(interview_info);
	}
	
	public List<Interview_timeslot> findAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Interview_timeslot");
		List list = query.list();
		return list;
	}
	
	
	/**
	 * @param subject_id
	 * @return Interview_timeslot
	 * 
	 * 查询指定subject_id
	 * id desc 第一个
	 * 
	 */
	public Interview_timeslot findSubject_id(int subject_id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer() ;
		sql.append(" select * from  interview_timeslot t1 , interview t2 ");
		sql.append(" where t1.interview_id = t2.id and t2.subject_id = :subject_id ");
		sql.append(" order by t1.id desc limit 0 , 1 ");
		
		SQLQuery SQLQuery = session.createSQLQuery(sql.toString());
		
		SQLQuery.setParameter("subject_id", subject_id);
		
		SQLQuery.addEntity(Interview_timeslot.class);
		
		return (Interview_timeslot) SQLQuery.uniqueResult();
		
	}
	
	/**
	 * @param id
	 *  清空 内容
	 */
	public void clearInterview_timeslot(int interview_id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer() ;
		sql.append(" UPDATE Interview_timeslot ");
		sql.append(" SET  starttime = :starttime , end_time = :end_time , num = :num ");
		sql.append(" WHERE interview_id = :interview_id   ");
		
		SQLQuery SQLQuery = session.createSQLQuery(sql.toString());
		
		SQLQuery.setDate("starttime", null);
		SQLQuery.setDate("end_time", null);
		SQLQuery.setInteger("num", 0);
		
		SQLQuery.setParameter("interview_id", interview_id);
		
		SQLQuery.executeUpdate();
		
	}
	

	/**
	 * @param interview_id
	 * @return
	 *  查询指定interview_id的面试科目记录
	 * 
	 */
	public List<Interview_timeslot> queryInterview_timeslotActionByinterview_id(int interview_id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer() ;
		
		sql.append(" select * from Interview_timeslot ");
		sql.append(" where  interview_id = :interview_id ");
		
		SQLQuery SQLQuery = session.createSQLQuery(sql.toString());
		
		SQLQuery.setInteger("interview_id", interview_id);
		
		SQLQuery.addEntity(Interview_timeslot.class);
		
		return SQLQuery.list();
	}

	
	/**
	 * @param id
	 *   sql update
	 */
	public int sqlUpdate(int id , Date starttime, Date end_time , int num ) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer() ;
		sql.append(" UPDATE Interview_timeslot ");
		sql.append(" SET  starttime = :starttime , end_time = :end_time , num = :num ");
		sql.append(" WHERE id = :id  ");
		
		SQLQuery SQLQuery = session.createSQLQuery(sql.toString());
		
		SQLQuery.setDate("starttime", starttime);
		SQLQuery.setDate("end_time", end_time);
		SQLQuery.setInteger("num", num);
		
		SQLQuery.setParameter("id", id);
		
		return SQLQuery.executeUpdate();
		
	}
	
}
