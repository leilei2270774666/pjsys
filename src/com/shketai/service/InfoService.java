package com.shketai.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.shketai.dao.InfoDao;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Info;
import com.shketai.entity.PageBean;
import com.shketai.entity.Student;

public class InfoService {

	private InfoDao infoDao;

	public void setInfoDao(InfoDao infoDao) {
		this.infoDao = infoDao;
	}
	
	public void updateFlag(int id,String flags){
		infoDao.updateFlag(id, flags);
	}
	/**
	 * 批量更新缴费状态
	 * @param stu_id
	 * @param flag
	 */
	public void update(List<Integer> ids,int flag,Integer pay){
		infoDao.update(ids,flag,pay);
	}
	public void quitSubject(int id) {
		infoDao.quitSubject(id);
	}
	
	public int addInfo(int stu_id,int classinfo_id){
		//选课之前，先判断时间段是否有冲突
				/*boolean flag = infoDao.checkPeriod(stu_id,classinfo_id);
				if(flag){
					//冲突了
					return 0;
				}else{*/
		infoDao.addInfo(stu_id, classinfo_id);
		return 1;
				
	}
	 public int getNum(int classinfo_id){
	    return infoDao.getNum(classinfo_id);
	}
	 
	public int checkstuid(int stu_id,int classinfo_id){
		int result = infoDao.checkstuid(stu_id,classinfo_id);
		return result;
	}
	
	public boolean checkPeriod(int stu_id, int classinfo_id) {
		boolean flag = infoDao.checkPeriod(stu_id, classinfo_id);
		return flag;
	}
	public int deleteById(int id){
		int result = infoDao.deleteById(id);
		return result;
	}
	public List<Info> findAllInfo(int user_id){
		return infoDao.findAllInfo(user_id);
	}

	public List<Info> findAllInfo1(int user_id) {
		return infoDao.findAllInfo1(user_id);
	}

	public List<Info> findAllInfo2(int user_id) {
		return infoDao.findAllInfo2(user_id);
	}

	public List<Info> findAllInfo3(int user_id) {
		return infoDao.findAllInfo3(user_id);
	}
	
	 public PageBean<Info> findAllInfo4(int page) {
		 PageBean<Info> pb = new PageBean<Info>();
		 List<Info> list = infoDao.findAllInfo4(page);
		 pb.setDatas(list);
			int total = infoDao.findCount();
			pb.setTotal(total);
			return pb;
	}
	 public PageBean<Info> findAllInfo5(int page) {
		 PageBean<Info> pb = new PageBean<Info>();
		 List<Info> list = infoDao.findAllInfo5(page);
		 pb.setDatas(list);
//		 	申请续费
			int total = infoDao.findCountFlag(8);
			pb.setTotal(total);
			return pb;
	}
	 
	 public PageBean<Info> findAllInfo6(int page) {
		 PageBean<Info> pb = new PageBean<Info>();
		 List<Info> list = infoDao.findAllInfo6(page);
		 pb.setDatas(list);
			int total = infoDao.findCount2();
			pb.setTotal(total);
			return pb;
	}
	public List<Info> findById(int stu_id){
		return infoDao.findById(stu_id);
	}

	public PageBean<Info> findByClassinfoId(int classinfo_id,int page, int flag) {
		PageBean<Info> pb = new PageBean<Info>();
		List<Info> list = infoDao.findByClassinfoId(classinfo_id, page,flag);
		pb.setDatas(list);
		int total = infoDao.findCount(classinfo_id);
		pb.setTotal(total);
		return pb;
	}
	
	public PageBean<Info> findByClassinfoIdDesc(int classinfo_id,int page, int flag) {
		PageBean<Info> pb = new PageBean<Info>();
		List<Info> list = infoDao.findByClassinfoIdDesc(classinfo_id, page,flag);
		pb.setDatas(list);
		int total = infoDao.findCount(classinfo_id);
		pb.setTotal(total);
		return pb;
	}
	public PageBean<Info> findByClassinfoId1(int classinfo_id,int page) {
		PageBean<Info> pb = new PageBean<Info>();
		
		Integer pageSize = 10 ;
		
		int startPage = (page-1) * pageSize;
		int endPage = pageSize ;
		
		List<Info> infoList = infoDao.queryNearlyDayAndNoneAllAttence( classinfo_id ,  startPage ,  endPage);
		
		
		pb.setDatas(infoList);
		
		int total = 0;
		
		total = total + infoDao.queryNearlyDayCountAndAllAttenceCount(classinfo_id);
		
		pb.setTotal(total);
		return pb;
	}
	
	public List<Info> findAll(int classinfo_id){
		List list = infoDao.findAll(classinfo_id);
		return list;
	}
	public List<Object[]> findAllByclassId(Integer classinfo_id){
		List<Object[]> list =  infoDao.findAllByclassId(classinfo_id);
		return list;
	}
	
	public List<Object[]> findAllBySubject(){
		List<Object[]> list =  infoDao.findAllBySubject();
		return list;
	}
	public List<Info> find(int user_id){
		List list = infoDao.find(user_id);
		return list;
	}
	
	public List<Info> queryByUsername1(String stuName){
		return infoDao.queryByUsername1(stuName);
	}
	
	public List<Info> queryByUsername2(String stuName){
		return infoDao.queryByUsername2(stuName);
	}
	
	public List<Info> queryByUsername3(String stuName){
		return infoDao.queryByUsername3(stuName);
	}
	
	/**
	 * @param info
	 * 修改info对象
	 */
	public int updateInfo(Info info) {
		return infoDao.updateInfo(info);
	}
	
	/**
	 * 删除指定 classinfo_id 
	 * @return
	 */
	public int deleteClassinfo_id(int classinfo_id) {
		
		return infoDao.deleteClassinfo_id(classinfo_id);
	}
	
	
	/**
	 * 查询指定 学生id 和 班级 id
	 * 
	 * 返回数量
	 */
	public int querystu_idInclassinfo_id(int stu_id , int classinfo_id){
		
		return  infoDao.querystu_idInclassinfo_id(stu_id, classinfo_id);
	}

	/**
	 * 查询指定 学生id 和 班级 id 和  不等flag集合
	 * 
	 * 返回数量
	 */
	public int querystu_idInclassinfo_idInflag(int stu_id , int classinfo_id , List<Integer> flag ){
		
		return  infoDao.querystu_idInclassinfo_idInflag( stu_id ,  classinfo_id ,  flag);
	}

	/**
	 *  传递制定 学生id 和 班级id  更新指定的 flag
	 */
	public int updatestu_idInclassinfo_idInflag(int stu_id , int classinfo_id , int flag ){
		
		return  infoDao.updatestu_idInclassinfo_idInflag( stu_id ,  classinfo_id ,  flag );
	}
	
	/**
	 *  sql  添加info
	 * @param flag
	 * @param classinfo_id
	 * @param stu_id
	 * @return
	 */
	/*public int sqlAddInfo(int flag , int classinfo_id , int stu_id) {
		
		return  infoDao.sqlAddInfo( flag ,  classinfo_id ,  stu_id);
	}*/
	
	/**
	 * 向班级里添加学生 同时判断是否已经添加
	 * @param classinfo_id
	 * @param stu_id
	 */
	public void my_addInfo(int stu_id,int classinfo_id,int flag){
		infoDao.my_addInfo( stu_id, classinfo_id, flag);
	}
	
	
	/**
	 * 批量更新缴费状态
	 * @param classinfo_ids 需要更新的班级id集合
	 * @param flag 缴费状态
	 */
	public void updateClassinfo_idsByflag(String classinfo_ids,int flag){
		
		
		 String[] classinfo_id_Arr = classinfo_ids.split(",");
		 
		 List<Integer> classinfo_idList = new ArrayList<Integer>();
		 
		 for (int i = 0; i < classinfo_id_Arr.length; i++) {
			 classinfo_idList.add( Integer.parseInt( classinfo_id_Arr[i] ) );
		 }
		 
		 
		infoDao.updateClassinfo_idsByflag( classinfo_idList , flag );
	}
	
	/**
	 * 批量删除 id
	 */
	public void deleteListId(List<Integer> ids){
		infoDao.deleteListId(ids);
	}
	
	
	/**
	 *  spell 7天内没有考情 
	 */
	public PageBean<Info> spellFindByClassinfoId(String spell_stu_name,int page) {
		PageBean<Info> pb = new PageBean<Info>();
		
		Integer pageSize = 10 ;
		
		int startPage = (page-1) * pageSize;
		int endPage = pageSize ;
		
		List<Info> list = infoDao.querySpellNearlyDay( spell_stu_name,  startPage ,  endPage);
		pb.setDatas(list);
		
		int total = infoDao.querySpellNearlyDayCount(spell_stu_name);
		pb.setTotal(total);
		
		return pb;
	}
	
}

