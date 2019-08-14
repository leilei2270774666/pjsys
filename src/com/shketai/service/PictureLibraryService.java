package com.shketai.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.shketai.dao.PictureLibraryDao;
import com.shketai.entity.PictureLibrary;
import com.shketai.entity.Teacher;

/**
 * @author Administrator
 *	图片库 业务层
 */
public class PictureLibraryService {
	/**
	 *  构造方法	
	 */
	public PictureLibraryService() {
		super();
	}

	//图片库dao 对象
	private PictureLibraryDao pictureLibraryDao;

	
	/**
	 * @return
	 * 	第一张 图片 
	 */
	public PictureLibrary firstOnePicture() {
		return  pictureLibraryDao.firstOnePicture();
	}
	
	/**
	 *  查询所有图片库
	 */
	public List<PictureLibrary> findAll(){
		return pictureLibraryDao.findAll();
	}
	
	
	/**
	 *  查询查询指定 teacher_id 的图片库
	 */
	public List<PictureLibrary> findTeacher_id(int teacher_id){
		return pictureLibraryDao.findTeacher_id(teacher_id);
	}
	
	/**
	  *  添加图片库
	 * @param pictureLibrary
	 * @param teacher_id
	 */
	public void save(PictureLibrary pictureLibrary , int teacher_id) {
		pictureLibraryDao.save(pictureLibrary, teacher_id);
	}

	
	/**
	 * @param pictureLibraryDao
	 */
	public void setPictureLibraryDao(PictureLibraryDao pictureLibraryDao) {
		this.pictureLibraryDao = pictureLibraryDao;
	}
	
	
}
