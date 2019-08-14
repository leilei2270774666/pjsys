package com.shketai.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.ActionContext;
import com.shketai.entity.Teacher;

public class TeacherDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public Teacher login(String username,String password,int role) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Teacher where username=:username and password=:password and role=:role";
		Query query = session.createQuery(hql);
		query.setString("username", username);
		query.setString("password",password);
		query.setInteger("role",role);
		List<Teacher> list = query.list();
		if(list.size()==1){
			//登录成功，把登录成功的用户名和角色存放到session里
			Map<String, Object> s = ActionContext.getContext().getSession();
			s.put("username", username);
			s.put("id", list.get(0).getId());
			s.put("role",role);
			return list.get(0);
		}else{
			return null;
		}
	}

	
	/**
	 *  查询指定id 返回总数
	 */
	public int queryIdReturnCount(int id){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from teacher where id = :id  ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setInteger("id", id);
		
		
		BigInteger BigInteger =   (BigInteger) sqlQuery.uniqueResult();
		
		return  BigInteger.intValue(); 
	}
	
}
