package com.shketai.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.ClassRoom;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Classroominfo;
import com.shketai.entity.Subject;

public class ClassroomDao {

private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 新增教室
	 * @param classroom
	 * @return
	 */
	public int add(ClassRoom classroom){
		Session session = sessionFactory.getCurrentSession();
		session.save(classroom);
		return 1;
	}
	
	/**
	 * 查询所有教室
	 * @return
	 */
	public List<Classroominfo> findAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ClassRoom order by name");
		List<Classroominfo> list = query.list();
		return list;
	}

	
	/**
	 * id 查询
	 * @return
	 */
	public ClassRoom findId(int id){
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(" select id , name from ClassRoom where id = :id ");
		
		query.setInteger("id", id);
		
		ClassRoom classRoom = new ClassRoom();
		
		List<Object[]> list = query.list();
		if(list.size()>0) {
			Object[] obj = list.get(0);
			
			int ids = Integer.parseInt(obj[0].toString());
			
			String name = obj[1].toString();
			
			classRoom.setId(ids);
			classRoom.setName(name);
		}
		
		return  classRoom;
	}
	
}
