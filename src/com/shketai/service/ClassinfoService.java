package com.shketai.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.shketai.dao.ClassinfoDao;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Info;
import com.shketai.entity.PageBean;

public class ClassinfoService {

	private ClassinfoDao classinfoDao;
	private InfoService infoService;
	private AttenceService attenceService;
	private ImessageByClassService imessageByClassService;

	
	
	
	public void setImessageByClassService(ImessageByClassService imessageByClassService) {
		this.imessageByClassService = imessageByClassService;
	}

	public void setAttenceService(AttenceService attenceService) {
		this.attenceService = attenceService;
	}

	public void setClassinfoDao(ClassinfoDao classinfoDao) {
		this.classinfoDao = classinfoDao;
	}

	public int add(Classinfo classinfo,int subject_id){
		int result = classinfoDao.add(classinfo, subject_id);
		return result;		
	}
	
	public int delete(Classinfo classinfo){
		
		int classinfo_id = classinfo.getId();
		
//		删除关联attence
		attenceService.deleteClassinfo_id(classinfo_id);
		
//		删除关联info
		infoService.deleteClassinfo_id(classinfo_id);
		
//		删除关联imessagebyclass
		imessageByClassService.deleteClassinfo_id(classinfo_id);
		
//		删除班级
		classinfoDao.sqlDeleteId(classinfo_id);
		
		int result = 1;
		return result;	
	}
	
	public int update(Classinfo classinfo){
		int result = classinfoDao.update(classinfo);
		return result;
	}
	
	public void selectClassroom(Classinfo classinfo){
		//根据calssinfo的id，查询要排班的班级
		Classinfo ci = classinfoDao.findById(classinfo.getId());
		//这个班级之前没有安排教室，此处给他安排一个教室时间段
		ci.setClassroominfo(classinfo.getClassroominfo());
		//利用快照机制更新
	}
	
	
	/**
	 * 查询出符合年龄的班级
	 * @param subjectId
	 * @param age
	 * @return
	 * @throws Exception 
	 */
	public PageBean<Classinfo> findBySubjectAndAge(int major_id,int stu_id,int page) throws Exception{
		PageBean<Classinfo> pb = new PageBean<Classinfo>();
		List<Classinfo> classinfoList = classinfoDao.findBySubjectAndAge(major_id, stu_id, page);
		
		List<Info> infoList =  infoService.findById(stu_id);
		 
		for (int i = 0; i < classinfoList.size(); i++) {
			for (int x = 0; x < infoList.size(); x++) {
				
				Classinfo classinfo = classinfoList.get(i);
				Info info = infoList.get(x);
				//判断是否已选
				if(classinfo.getId() == info.getClassinfo().getId()) {
					//已选1
					classinfo.setInfoFlag(1);
					
					break;
				}else {
					//未选0
					classinfo.setInfoFlag(0);
				}
				
			}
		}
		
		
		pb.setDatas(classinfoList);
		int total = classinfoDao.findCount1(major_id, stu_id);
		pb.setTotal(total);
		return pb;
	}
	
	public List<Classinfo> findBySubjectAndAges(int subject_id,int stu_id) throws Exception{
		
		List<Classinfo> classinfoList = classinfoDao.findBySubjectAndAges(subject_id, stu_id);
		
		List<Info> infoList =  infoService.findById(stu_id);
		
		 
		for (int i = 0; i < classinfoList.size(); i++) {
			for (int x = 0; x < infoList.size(); x++) {
				
				Classinfo classinfo = classinfoList.get(i);
				Info info = infoList.get(x);
				//判断是否已选
				if(classinfo.getId() == info.getClassinfo().getId()) {
					//已选1
					classinfo.setInfoFlag(1);
					
					break;
				}else {
					//未选0
					classinfo.setInfoFlag(0);
				}
				
			}
		}
		
		return classinfoList;
	}
	public int selectSubject(int stu_id,int classinfo_id){
		//选课之前，先判断时间段是否有冲突
		boolean flag = classinfoDao.checkPeriod(stu_id,classinfo_id);
		if(flag){
			//冲突了
			return 0;
		}else{
			
			//选课
			 classinfoDao.selectSubject(stu_id, classinfo_id);
			
			 return 1;
		}
	}
    public int getNum(int classinfo_id){
    	return classinfoDao.getNum(classinfo_id);
    }
	public int findflag(int classinfo_id){
		int result = classinfoDao.findflag(classinfo_id);
		return result;
	}
	public int findInterview_info(int stu_id){
		int result = classinfoDao.findInterview_info(stu_id);
		return result;
	}
	
	/*public void quitSubject(int id) {
		classinfoDao.quitSubject(id);
	}*/

		public PageBean<Classinfo> findAll(int page) {
			PageBean<Classinfo> pb = new PageBean<Classinfo>();
		    List<Classinfo> list =classinfoDao.findAll(page);
		    pb.setDatas(list);
			int total = classinfoDao.findCount();
			pb.setTotal(total);
			return pb;
	}
	
		public List<Classinfo> findAll1() {
			//PageBean<Classinfo> pb = new PageBean<Classinfo>();
		    List<Classinfo> list =classinfoDao.findAll1();
		   /* pb.setDatas(list);
			int total = classinfoDao.findCount();
			pb.setTotal(total);*/
			return list;
	}
		public int findRQ(int classinfo_id) {
			int result = classinfoDao.findRQ(classinfo_id);
			return result;
		}
	
	public List<Classinfo> queryByUsername(String classname){
		return classinfoDao.queryByUsername(classname);
	}
	
	
	public List<Classinfo> queryById(Integer id){
		return classinfoDao.queryById(id);
	}
	
	public List<Classinfo> findRoom(int classroom_id){
	return classinfoDao.findRoom(classroom_id);
	}


	/**
	 *  合并班级
	 *  
	 *  classinfo_id; // 班级ID
	 *	classinfo_id_clear; //清空的班级id
	 */
	public int mergeClassinfo(int classinfo_id, int classinfo_id_clear) {
		
		//查询出要 替换班级的 info
		List<Info> infoList = infoService.findAll(classinfo_id_clear);
		
		//查询出 添加的班级
		Classinfo classinfo = classinfoDao.findById(classinfo_id);
		
		for (int i = 0; i < infoList.size(); i++) {
			Info info = infoList.get(i);
			info.setClassinfo(classinfo);
			
			infoService.updateInfo(info);
		}
		
		return 1;
	}
	
	
	/**
	 * @return
	 *  查看未分配教室的班级
	 *  没有教室的班级
	 */
	public List<Classinfo> classinfoInclassroominfo_idIsNull() {
		
		return classinfoDao.classinfoInclassroominfo_idIsNull();
	}
	
	
	/**
	 * @return
	 *   查询 所有班级  
	 *   返回 id  班级名称
	 */
	public List<Classinfo> sqlAllClassinfo(){
		
		return  classinfoDao.sqlAllClassinfo();
	}
	
	/**
	 *    清空班级下的所有学生
	 * @return
	 */
	public int classinfoClearStudent(int classinfo_id) {
		
//		把指定班级的 额定人数 赋值给 剩余人数
		classinfoDao.classinfoInnumber_num(classinfo_id);
		
		return infoService.deleteClassinfo_id(classinfo_id);
	}
	
	
	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}
	
	
	/**
	  * 班级考勤数据汇总
	 *   pageNum 页码
	 *  pageSize 数量
	 *  classname 班级名称
	 */
	public PageBean<Classinfo> queryClassinfoInAttence(int pageNum,int pageSize,String classname,Date dateTime){
		PageBean<Classinfo> pb = new PageBean<Classinfo>();
		List<Classinfo> classinfoList = classinfoDao.queryClassinfoInAttence(pageNum, pageSize, classname);
		
		for (int i = 0; i < classinfoList.size(); i++) {
			Classinfo classinfo = classinfoList.get(i);
			
			int classinfo_id = classinfo.getId();
			
//			通过传递的 班级id 更新 班级里的剩余人数
			classinfoDao.updateClassinfoInnum( classinfo_id );
			
//			  查看指定班级id 返回 已配置人数 = 额定人数 - 剩余人数
			Integer count = classinfoDao.queryIdReturnCount( classinfo_id );
					
//			已配置人数  并进行赋值
			classinfo.setCount(count);
			
//			实勤人数
			Integer onPerson = attenceService.CountAttence(classinfo_id,dateTime);
			classinfo.setOnPerson(onPerson);
			
//			缺勤人数
			Integer unPerson = attenceService.CountUnAttence(classinfo_id,dateTime);
			classinfo.setUnPerson(unPerson);
			
//			未到人数
			Integer notPerson = count - onPerson - unPerson;
			classinfo.setNotPerson(notPerson);
			
		}
		
		pb.setDatas(classinfoList);
		int total =  classinfoDao.queryClassinfoInclassname(classname);
		pb.setTotal(total);
		
		return pb;
	}
	
	
	/**
	 * 查询指定班级名称  返回 班级对象
	 * @param classname
	 * @return
	 */
	public Classinfo queryClassinfoInclassnameReturnObj(String classname) {
		return classinfoDao.queryClassinfoInclassnameReturnObj(classname);
	}
	
	
	/**
	  * 班级考勤数据汇总 全部
	 */
	public List<Classinfo>  queryClassinfoInAttence(){
		List<Classinfo> classinfoList = classinfoDao.queryClassinfoAll();
		
		for (int i = 0; i < classinfoList.size(); i++) {
			Classinfo classinfo = classinfoList.get(i);
			
			int classinfo_id = classinfo.getId();
			
//			通过传递的 班级id 更新 班级里的剩余人数
			classinfoDao.updateClassinfoInnum( classinfo_id );
			
//			  查看指定班级id 返回 已配置人数 = 额定人数 - 剩余人数
			Integer count = classinfoDao.queryIdReturnCount( classinfo_id );
					
//			已配置人数  并进行赋值
			classinfo.setCount(count);
			
//			实勤人数
			Integer onPerson = attenceService.CountAttence(classinfo_id);
			classinfo.setOnPerson(onPerson);
			
//			缺勤人数
			Integer unPerson = attenceService.CountUnAttence(classinfo_id);
			classinfo.setUnPerson(unPerson);
			
//			未到人数
			Integer notPerson = count - onPerson - unPerson;
			classinfo.setNotPerson(notPerson);
			
		}
		
		return classinfoList;
	}
	
	
	/**
	  * 班级开始时间和结束时间考勤数据汇总 全部
	 */
	public List<Classinfo>  queryClassinfoInAttenceByStartTimeAndEndtime(Date startTime , Date endTime){
		List<Classinfo> classinfoList = classinfoDao.queryClassinfoAll();
		
		for (int i = 0; i < classinfoList.size(); i++) {
			Classinfo classinfo = classinfoList.get(i);
			
			int classinfo_id = classinfo.getId();
			
//			通过传递的 班级id 更新 班级里的剩余人数
			classinfoDao.updateClassinfoInnum( classinfo_id );
			
//			  查看指定班级id 返回 已配置人数 = 额定人数 - 剩余人数
			Integer count = classinfoDao.queryIdReturnCount( classinfo_id );
					
//			已配置人数  并进行赋值
			classinfo.setCount(count);
			
//			实勤人数
			Integer onPerson = attenceService.CountAttence(classinfo_id);
			classinfo.setOnPerson(onPerson);
			
//			缺勤人数
			Integer unPerson = attenceService.CountUnAttenceByStarttimeAndEndtime( classinfo_id ,  startTime ,  endTime);
			classinfo.setUnPerson(unPerson);
			
//			未到人数
			Integer notPerson = count - onPerson - unPerson;
			classinfo.setNotPerson(notPerson);
			
		}
		
		return classinfoList;
	}
	
}
