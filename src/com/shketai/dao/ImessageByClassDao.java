package com.shketai.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Classinfo;
import com.shketai.entity.ImessageByClass;
import com.shketai.entity.Info;
import com.shketai.entity.Teacher;

public class ImessageByClassDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 根据班级发送站内信
	 * @param classinfo_ids
	 * @param head
	 * @param message
	 * @param teacher_id
	 */
	public void add(List<Integer> classinfo_ids,String head,String message,int teacher_id){
		if(classinfo_ids.size()>0){
			Session session = sessionFactory.getCurrentSession();
			Date now = new Date();
			for (Integer classinfo_id : classinfo_ids) {
				ImessageByClass imessageByClass = new ImessageByClass();
				Classinfo c = new Classinfo();
				c.setId(classinfo_id);
				imessageByClass.setClassinfo(c);
				Teacher teacher = new Teacher();
				teacher.setId(teacher_id);
				imessageByClass.setTeacher(teacher);
				imessageByClass.setTime(now);
				imessageByClass.setFlag(0);
				imessageByClass.setHead(head);
				imessageByClass.setMessage(message);
				session.save(imessageByClass);
			}
		}
	}
	
	/**
	 * 学生端查看站内消息
	 * @param classinfo_id
	 * @param page
	 * @return
	 */
	public List<ImessageByClass> find(int stu_id,int page){
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("select classinfo.id from Info where stu_id=?");
		query1.setInteger(0, stu_id);
		
		//存放所有班级的id
		List<Integer> ids = query1.list();
		
		//这一条hql语句相当于  select * from ImessageByClass where classinfo_id in(1,2,90)
		Query query2 = session.createQuery("from ImessageByClass where classinfo.id in(:ids)");
		query2.setParameterList("ids",ids);
		query2.setFirstResult((page-1)*10);
		query2.setMaxResults(10);
		List<ImessageByClass> list1 = query2.list();
		if(list1.size() > 0){
			return list1;
		}else{
			return null;
		}
	}
	
	public int findCount1(int stu_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("select classinfo.id from Info where stu_id=?");
		query1.setInteger(0, stu_id);
		//存放所有班级的id
		List<Integer> ids = query1.list();
		//这一条hql语句相当于  select * from ImessageByClass where classinfo_id in(1,2,90)
		Query query2 = session.createQuery("select count(*) from ImessageByClass where classinfo.id in(:ids) ");
		query2.setParameterList("ids",ids);
		Long total = (Long) query2.uniqueResult();
		return total.intValue();
	}
	
	public void updateFlag(int id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update ImessageByClass set flag=1 where  id =" + id);
		System.out.println("dao:trail:" + id);
		//引号id
		query.executeUpdate();
	}
	
	public ImessageByClass findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ImessageByClass where id=?");
		query.setInteger(0, id);
		List<ImessageByClass> list = query.list();
		ImessageByClass imessageByClass = list.get(0);
		return imessageByClass;
	}
	
	public int delete(ImessageByClass imessageByClass){
		Session session = sessionFactory.getCurrentSession();
		session.delete(imessageByClass);
		return 1;
	}
	
	/**
	 * 删除指定 classinfo_id 
	 * @return
	 */
	public int deleteClassinfo_id(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from ImessageByClass where  classinfo_id = :classinfo_id ");
		
		
		SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
		
		sqlQuery.setInteger("classinfo_id", classinfo_id);
		
		sqlQuery.executeUpdate();
		
		return 1;
	}
	
}
