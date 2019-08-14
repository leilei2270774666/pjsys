package com.shketai.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Info;
import com.shketai.entity.Interview_info;
import com.shketai.entity.Interview_timeslot;
import com.shketai.entity.Student;

public class Interview_infoDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 面试通过
	 * @param id
	 */
	public void pass(int stu_id,int interview_timeslot_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Interview_info set result = 1,shDt=now() where stu_id =? and  interview_timeslot_id=?");
		query.setInteger(0, stu_id);
		query.setInteger(1, interview_timeslot_id);
		System.out.println(stu_id);
		System.out.println(interview_timeslot_id);
		query.executeUpdate();
	}
	
	/**
	 * 面试不通过
	 * @param id
	 */
	public void unpass(int stu_id,int interview_timeslot_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Interview_info set result = 2,shDt=now() where stu_id = ? and  interview_timeslot_id=?");
		query.setInteger(0, stu_id);
		query.setInteger(1, interview_timeslot_id);
		query.executeUpdate();
	}
	
	public List<Interview_info> findAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Interview_info");
		List<Interview_info> list = query.list();
		return list;
	}
	
	public List<Interview_info> find(int user_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Student where user.id = ?");
		query.setLong(0, user_id);
		List<Student> list = query.list();
		//存放所有学生的id
		List<Integer> ids = new ArrayList<Integer>();
		for (Student student :list) {
			ids.add(student.getId());
		}
		//这一条hql语句相当于  select * from info where stu_id in(1,2,90)，就是查询这些学生的选课信息
		Query query2 = session.createQuery("from Interview_info where student.id in(:ids) ");
		query2.setParameterList("ids",ids);
		List<Interview_info> list1 = query2.list();
		return list1;
	}
	
	public List<Interview_info> findByInterview(int interview_id,int page){
		Session session = sessionFactory.getCurrentSession();
		
		int startPage = (page-1)*10;
		int endPage =   10;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select i_if.*   from Interview_info  i_if  , interview_timeslot i_ts , interview i_ ");
		sql.append(" where  i_if.clear_flag is null ");
		sql.append(" and i_if.interview_timeslot_id = i_ts.id ");
		sql.append(" and i_ts.interview_id = i_.id ");
		sql.append(" and i_ts.interview_id = :interview_id ");
		sql.append(" GROUP BY i_.id , i_if.stu_id ");
		sql.append(" LIMIT :startPage , :endPage ");
		
		SQLQuery  sqlQuery = session.createSQLQuery(sql.toString());
		
		sqlQuery.setInteger("interview_id", interview_id);
		sqlQuery.setInteger("startPage", startPage);
		sqlQuery.setInteger("endPage", endPage);
		
		sqlQuery.addEntity(Interview_info.class);
		
		List<Interview_info> list=sqlQuery.list();
		
		return list;
	}
	public int findCounts(int interview_id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from ( ");
		sql.append(" select i_if.*   from Interview_info  i_if  , interview_timeslot i_ts , interview i_ ");
		sql.append(" where  i_if.clear_flag is null ");
		sql.append(" and i_if.interview_timeslot_id = i_ts.id ");
		sql.append(" and i_ts.interview_id = i_.id ");
		sql.append(" and i_ts.interview_id = :interview_id ");
		sql.append(" GROUP BY i_.id , i_if.stu_id ");
		sql.append(" ) c ");
		
		SQLQuery  sqlQuery = session.createSQLQuery(sql.toString());
		sqlQuery.setInteger("interview_id", interview_id);
		
		BigInteger  total = (BigInteger ) sqlQuery.uniqueResult();
		return total.intValue();
	}
	/**
	 * 根据姓名查询学生信息
	 * @param username
	 * @return
	 */
	public List<Interview_info> queryByUsername(String stuName){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Interview_info where result = 0 and student.stuName like ? ");
		query.setString(0,"%"+stuName+"%").list();
		//query.setInteger(0, classinfo_id);
		Student student = new Student();
		student.setStuName(stuName);
		Interview_info interview_info = new Interview_info();
		interview_info.setStudent(student);
		List<Interview_info> list = query.list();
		if(list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	
	public List<Interview_info> findInterview(int stu_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Interview_info where stu_id=?");
		query.setInteger(0, stu_id);
		List<Interview_info> list = query.list();
		return list;
	}
	
	/**
	 * 查询所有面试审核通过的学生
	 * @return
	 */
	public List<Interview_info> findResult(int page){
		Session session = sessionFactory.getCurrentSession();
		
		int startPage = (page-1)*10;
		int endPage =   10;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select i_if.*   from Interview_info  i_if  , interview_timeslot i_ts , interview i_ ");
		sql.append(" where  i_if.clear_flag is null ");
		sql.append(" and i_if.interview_timeslot_id = i_ts.id ");
		sql.append(" and i_ts.interview_id = i_.id ");
		sql.append(" GROUP BY i_.id , i_if.stu_id ");
		sql.append(" LIMIT :startPage , :endPage ");
		
		SQLQuery  sqlQuery = session.createSQLQuery(sql.toString());
		
		sqlQuery.setInteger("startPage", startPage);
		sqlQuery.setInteger("endPage", endPage);
		
		sqlQuery.addEntity(Interview_info.class);
		
		List<Interview_info> list=sqlQuery.list();
		
		return list;
	}
	public int findCount() {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from ( ");
		sql.append(" select i_if.*   from Interview_info  i_if  , interview_timeslot i_ts , interview i_ ");
		sql.append(" where  i_if.clear_flag is null ");
		sql.append(" and i_if.interview_timeslot_id = i_ts.id ");
		sql.append(" and i_ts.interview_id = i_.id ");
		sql.append(" GROUP BY i_.id , i_if.stu_id ");
		sql.append(" ) c ");
		
		SQLQuery  sqlQuery = session.createSQLQuery(sql.toString());
		BigInteger  total = (BigInteger ) sqlQuery.uniqueResult();
		return total.intValue();
	}
	/**
	 * 查询所有学生的id集合
	 * @return
	 */
	public List<Integer> findAllIds(){
		String hql = "select id from Interview_info";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Integer> list = query.list();
		return list;
	}
	
	/**
	 * 把所有interview_info 里的  clear_flag 赋值1 
	 * @return
	 */
	public Integer interview_infoInclear_flag() {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update interview_info set clear_flag = 1 ");
		
		SQLQuery sQLQuery = session.createSQLQuery( sql.toString() );
		
		sQLQuery.executeUpdate();
		
		return 1;
	}
	
}
