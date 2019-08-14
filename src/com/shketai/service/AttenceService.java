package com.shketai.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.shketai.dao.AttenceDao;
import com.shketai.entity.Attence;
import com.shketai.entity.PageBean;
import com.shketai.entity.Reasons;


public class AttenceService {

	private AttenceDao attenceDao;
	
	private ReasonsService reasonsService;

	public void setAttenceDao(AttenceDao attenceDao) {
		this.attenceDao = attenceDao;
	}
	
	
	public ReasonsService getReasonsService() {
		return reasonsService;
	}


	public void setReasonsService(ReasonsService reasonsService) {
		this.reasonsService = reasonsService;
	}


	public AttenceDao getAttenceDao() {
		return attenceDao;
	}


	/**
	 * 查看考勤
	 * @param user_id
	 * @return
	 */
	public List<Map<String,Object>> findAttenceById(int user_id){
		return attenceDao.findAttenceById(user_id);
	}
	
	public void updateFlag(int id){
		attenceDao.updateFlag(id);
	}
	
	
	public void attenceUpdateStatu(int id , int attence_status , int reason_id){
		attenceDao.attenceUpdateStatu( id ,  attence_status ,  reason_id);
	}
	
	
	/**
	 * 考勤
	 * @param stu_id
	 */
	public void add(List<Integer> stu_id,int attence_status,int classinfo_id,List<Integer> reason_id_list) {
		attenceDao.add(stu_id, attence_status, classinfo_id,reason_id_list);
	}
	
	/**
	 * 考勤统计
	 * @return
	 */
	public List<Attence>  CountAttence(){
		return attenceDao.CountAttence();
	}
	
	/**
	 * 学生考勤汇总
	 * @return
	 */
	public PageBean<Object[]>Attendancesummary(int page,int classinfo_id){
		PageBean<Object[]> pb = new PageBean<Object[]>();
		List<Object[]> list = attenceDao.Attendancesummary(page, classinfo_id);
		
		for (int i = 0; i < list.size(); i++) {
			
			Object[] obj = list.get(i);
			
			Object value =  obj[obj.length-1];
			
			if(value != null) {
				Reasons reasons = reasonsService.queryId((Integer) value);
				obj[obj.length-1] = reasons.getReason();
			}
			
		}
		
		pb.setDatas(list);
		int total = attenceDao.findCount(classinfo_id);
		pb.setTotal(total);
		return pb;
		
	}
	
	/**
	 * 学生考勤汇总
	 * @return
	 */
	public List<Object[]>Attendancesummary1(int classinfo_id){
		
		List<Object[]> list = attenceDao.Attendancesummary1( classinfo_id);
		return list;
		
	}
	
	public List<Attence> UnAttence(int user_id) {
		 return attenceDao.UnAttence(user_id);
		
	}
	
	public List<Attence> QueryByName(String stuName) {
		return attenceDao.QueryByName(stuName);
	}
	
	public int CountAttence(int classinfo_id,Date dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime1 = null;
		if(dateTime!=null) {	
			dateTime1=sdf.format(dateTime);
		}
		return attenceDao.CountAttence(classinfo_id,dateTime1);
	}
	
	public int CountUnAttence(int classinfo_id,Date dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime1 = null;
		if(dateTime!=null) {	
			dateTime1 =sdf.format(dateTime);
		}
		return attenceDao.CountUnAttence(classinfo_id,dateTime1);
	}
	
	public int CountAttence(int classinfo_id) {
		return attenceDao.CountAttence(classinfo_id);
	}
	
	public int CountUnAttence(int classinfo_id) {
		return attenceDao.CountUnAttence(classinfo_id);
	}
	
	public int CountUnAttenceByStarttimeAndEndtime(int classinfo_id , Date startTime , Date endTime) {
		return attenceDao.CountUnAttenceByStarttimeAndEndtime(classinfo_id, startTime, endTime);
	}
	
	
	/**
	 * 传递 班级id 得到缺勤 的考勤记录
	 * @param classinfo_id
	 */
	public List<Attence> querUnAttenceList(int classinfo_id,Date dateTime) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateTime1=null;
		if(dateTime!=null) {
			dateTime1=sdf.format(dateTime);
		}
		return attenceDao.querUnAttenceList(classinfo_id,dateTime1);
	}
	
	
	/**
	 * 传递 班级id 得到缺勤 的考勤记录 的消息综合
	 * @param classinfo_id
	 */
	public String  querUnAttenceMessage(int classinfo_id) {
//		消息	
		String message = "";
//		得到缺勤集合
		List<Attence> list = attenceDao.querUnAttenceList(classinfo_id);
		
		for (int i = 0; i < list.size(); i++) {
			
			Attence attence = list.get(i);
			
			String stu_Name = attence.getStudent().getStuName();
			
			String reason = attence.getReasons().getReason();
			
			
			message = message + stu_Name + " : " + reason + "     " ;
		}
		
		return message;
	}
	
	
	/**
	 * 传递 班级id 得到缺勤 的考勤记录 的消息综合
	 * classinfo_id
	 * startTime
	 * endTime
	 */
	public String  querUnAttenceMessageByStartTimeAndEndTime(int classinfo_id , Date startTime , Date endTime) {
//		消息	
		String message = "";
//		得到缺勤集合
		List<Attence> list = attenceDao.querUnAttenceListByStartTimeAndEndTime( classinfo_id ,  startTime ,  endTime);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int i = 0; i < list.size(); i++) {
			
			Attence attence = list.get(i);
			
			String stu_Name = attence.getStudent().getStuName();
			
			String reason = attence.getReasons().getReason();
			
			Date time = attence.getAttence_time();
			
			String timeStr = "";
			if(time != null) {
				timeStr = sdf.format(time) + " ";
			}
			
			message = message + stu_Name + " : " + timeStr + reason + "     " ;
		}
		
		return message;
	}
	
	
	/**
	 * 删除指定 classinfo_id 
	 * @return
	 */
	public int deleteClassinfo_id(int classinfo_id) {
		return attenceDao.deleteClassinfo_id(classinfo_id);
	}
	
}


