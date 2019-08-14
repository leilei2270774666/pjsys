package com.shketai.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Interview;
import com.shketai.entity.PictureLibrary;
import com.shketai.entity.Teacher;

/**
 * @author Administrator
 *	图片库 Dao
 */
public class PictureLibraryDao {
	/**
	 *  构造方法	
	 */
	public PictureLibraryDao() {
		super();
	}

	//
	private SessionFactory sessionFactory;
	
	
	/**
	 * @return
	 * 	第一张 图片 
	 */
	public PictureLibrary firstOnePicture() {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select * from PictureLibrary ");
		sql.append(" ORDER BY id desc  LIMIT 0,1 ");
		
		SQLQuery  sQLQuery  = session.createSQLQuery(sql.toString());
		
		sQLQuery.addEntity(PictureLibrary.class);
		
		
		return (PictureLibrary) sQLQuery.uniqueResult();
	}
	
	
	/**
	 *  查询所有图片库
	 */
	public List<PictureLibrary> findAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" from PictureLibrary ");
		List<PictureLibrary> list = query.list();
		return list;
	}
	
	/**
	 *  查询查询指定 teacher_id 的图片库
	 */
	public List<PictureLibrary> findTeacher_id(int teacher_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" from PictureLibrary where teacher_id = :teacher_id ");
		
		query.setInteger("teacher_id", teacher_id);
		
		List<PictureLibrary> list = query.list();
		return list;
	}
	
	/**
	  *  添加图片库
	 * @param pictureLibrary
	 * @param teacher_id
	 */
	public void save(PictureLibrary pictureLibrary , int teacher_id) {
		Session session = sessionFactory.getCurrentSession();
		
		Teacher teacher = new Teacher();
		teacher.setId(teacher_id);
		
		pictureLibrary.setTeacher_id(teacher_id);
		
		session.save( pictureLibrary );
	}
	
	/**
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
