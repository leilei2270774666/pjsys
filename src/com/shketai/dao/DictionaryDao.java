package com.shketai.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Dictionary;
import com.shketai.entity.Interview_info;

/**
 * @author Administrator
 *  词典
 */
public class DictionaryDao {
	/**
	 *  构造方法	
	 */
	public DictionaryDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	private SessionFactory sessionFactory;
	
	
	/**
	 * 查询字段 first_spell 等于null 或者 空字符串
	 */
	public List<Dictionary> queryListInFirst_spellNotNull(){
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from dictionary where first_spell is null or first_spell = '' ");
		
		SQLQuery  sqlQuery = session.createSQLQuery(sql.toString());
		
		sqlQuery.addEntity(Dictionary.class);
		
		List<Dictionary> list=sqlQuery.list();
		
		return list;
	}
	
	/**
	   *  删除全部
	 */
	public void deleteAll() {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from dictionary ");
		
		SQLQuery  sqlQuery = session.createSQLQuery(sql.toString());
		sqlQuery.executeUpdate();
	}
	
	/**
	   *  删除词典里first_spell等于空或者空字符串
	 */
	public void deleteByfirst_spell_IsNullOrNotStr() {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from dictionary where first_spell ='' or first_spell is null ");
		
		SQLQuery  sqlQuery = session.createSQLQuery(sql.toString());
		sqlQuery.executeUpdate();
	}
	
	/**
	 *  update
	 */
	public void updateObj(Dictionary dictionary) {
		Session session = sessionFactory.getCurrentSession();
		session.update(dictionary);
	}
	
	/**
	 *  save
	 */
	public void saveObj(Dictionary dictionary) {
		Session session = sessionFactory.getCurrentSession();
		session.save(dictionary);
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
