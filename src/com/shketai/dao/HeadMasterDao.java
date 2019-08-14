package com.shketai.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.HeadMaster;

/**
 * @author Administrator
 *	班主任dao层
 */
public class HeadMasterDao {
	/**
	 *  构造方法	
	 */
	public HeadMasterDao() {
		super();
	}
	
//	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * sql 查询指定id
	 * @return
	 */
	public HeadMaster sqlQueryId(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from headmaster where id = :id ");
		
		SQLQuery sQLQuery = session.createSQLQuery( sql.toString() );
		
		sQLQuery.setInteger("id", id);
		
		sQLQuery.addEntity( HeadMaster.class );
		
		
		return (HeadMaster) sQLQuery.uniqueResult();
	}
	
	/**
	 *  分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<HeadMaster> queryPage(int pageNum , int pageSize){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from headmaster order by id desc ");
		sql.append(" limit ? , ? ");
		
		SQLQuery sQLQuery = session.createSQLQuery( sql.toString() );
		
		sQLQuery.setInteger(0, (pageNum-1) * pageSize );
		sQLQuery.setInteger(1, pageSize);
		
		
		sQLQuery.addEntity(HeadMaster.class);
		
		return sQLQuery.list();
	}
	
	
	/**
	 * sql 查询总条数
	 * @return
	 */
	public int sqlCountSize() {
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from headmaster ");
		
		SQLQuery sQLQuery = session.createSQLQuery( sql.toString() );
		
		BigInteger count =  (BigInteger) sQLQuery.uniqueResult();
		return Integer.valueOf(count.toString());
	}
	
	/**
	 * 	save
	 * @return
	 */
	public int save(HeadMaster headMaster) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.save(headMaster);
		
		return 1;
	}
	
	
	/**
	 *  sql 删除指定id
	 * @return
	 */
	public int sqlDeleteId(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from headmaster where id = :id ");
		
		SQLQuery sQLQuery = session.createSQLQuery( sql.toString() );
		
		sQLQuery.setInteger("id", id);
		
		sQLQuery.executeUpdate();
		
		return 1;
	}
	
	/**
	 * 	update
	 * @param headMaster
	 * @return
	 */
	public int update(HeadMaster headMaster) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.update(headMaster);
		
		return 1;
	}
	
	/**
	  * 查询所有班主任
	 * @return
	 */
	public List<HeadMaster> findAllHeadMaster(){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" from HeadMaster ");
		
		 Query query = session.createQuery( sql.toString() );
		
		return query.list();
	}
	
	/**
	 * sql 查询指定classinfo_id
	 * @return
	 */
	public HeadMaster sqlQueryclassinfo_id(int classinfo_id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.* from headmaster t1 , classinfo t2 where t1.id = t2.headmaster_id and t2.id = :classinfo_id ");
		
		SQLQuery sQLQuery = session.createSQLQuery( sql.toString() );
		
		sQLQuery.setInteger("classinfo_id", classinfo_id);
		
		sQLQuery.addEntity( HeadMaster.class );
		
		
		return (HeadMaster) sQLQuery.uniqueResult();
	}
	
}
