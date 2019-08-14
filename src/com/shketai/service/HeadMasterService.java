package com.shketai.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.shketai.dao.ClassinfoDao;
import com.shketai.dao.HeadMasterDao;
import com.shketai.entity.Classinfo;
import com.shketai.entity.HeadMaster;
import com.shketai.entity.PageBean;

/**
 * @author Administrator
 *	班主任 service层
 *	
 */
public class HeadMasterService {
	/**
	 *  构造方法	
	 */
	public HeadMasterService() {
		super();
	}

//	班主任 dao层 对象
	private HeadMasterDao headMasterDao;
	
//	班级 dao层 对象
	private ClassinfoDao classinfoDao;

	public void setHeadMasterDao(HeadMasterDao headMasterDao) {
		this.headMasterDao = headMasterDao;
	}
	
	public void setClassinfoDao(ClassinfoDao classinfoDao) {
		this.classinfoDao = classinfoDao;
	}


	/**
	 * sql 查询指定id
	 * @return
	 */
	public HeadMaster sqlQueryId(int id) {
		
		return headMasterDao.sqlQueryId(id);
	}
	
	/**
	 *  分页查询 降序
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageBean<HeadMaster> queryPage(int pageNum , int pageSize){
		List<HeadMaster> list = headMasterDao.queryPage(pageNum, pageSize);
		
		int total = headMasterDao.sqlCountSize();
		
		PageBean<HeadMaster> pb = new PageBean<HeadMaster>();
		
		pb.setDatas(list);
		pb.setTotal(total);
		
		
		return pb;
	}
	
	
	/**
	 * 	save
	 * @return
	 */
	public int save(HeadMaster headMaster) {
		
		return headMasterDao.save(headMaster);
	}
	
	
	/**
	 *  sql 删除指定id
	 * @return
	 */
	public int sqlDeleteId(int id) {
		
		return headMasterDao.sqlDeleteId(id);
	}
	
	/**
	 * 	update
	 * @param headMaster
	 * @return
	 */
	public int update(HeadMaster headMaster) {
		
		return headMasterDao.update(headMaster);
	}
	
	/**
	 * sql 查询总条数
	 * @return
	 */
	public int sqlCountSize() {
		
		return headMasterDao.sqlCountSize();
	}
	
	/**
	 * @return
	 *  查看未分配班主任的班级
	 *  没有班主任的班级
	 */
	public List<Classinfo> classinfoInheadmaster_idIsNull(){
		
		return classinfoDao.classinfoInheadmaster_idIsNull();
	}
	
	/**
	 * @return
	 *  查看指定班主任的班级
	 *  
	 */
	public List<Classinfo> classinfoInheadmaster_idIsTrue(int headmaster_id){
		
		return  classinfoDao.classinfoInheadmaster_idIsTrue(headmaster_id);
		
	}
	
	
	/**
	  *  清空指定班级 里的 班主任关联
	 * @return
	 *  classinfo_id  班级id
	 * 
	 */
	public int classinfoClearheadmaster_id(int classinfo_id) {
		return classinfoDao.classinfoClearheadmaster_id(classinfo_id);
	}
	
	
	/**
	  *   指定班级添加班主任
	 *  classinfo_id 班级id
	 *   id  班主任id
	 */
	public int classinfoUpdateheadmaster_id(int classinfo_id , int id) {
		return classinfoDao.classinfoUpdateheadmaster_id(classinfo_id, id);
	}
	
	/**
	  * 查询所有班主任
	 * @return
	 */
	public List<HeadMaster> findAllHeadMaster(){
		
		return headMasterDao.findAllHeadMaster();
	}
	
	public int delete(int id) {
		
//		删除班主任
		headMasterDao.sqlDeleteId(id);
		
//		关联的班级一起清空
		classinfoDao.clearheadmaster_id(id);
		
		 return 1;
	}
	
	/**
	 * sql 查询指定classinfo_id
	 * @return
	 */
	public HeadMaster sqlQueryclassinfo_id(int classinfo_id) {
		
		return  headMasterDao.sqlQueryclassinfo_id(classinfo_id);
	}
	
}
