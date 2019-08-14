package com.shketai.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.shketai.dao.ClassinfoDao;
import com.shketai.dao.ImessageDao;
import com.shketai.dao.StudentDao;
import com.shketai.dao.UserDao;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Classroominfo;
import com.shketai.entity.Imessage;
import com.shketai.entity.Notice;
import com.shketai.entity.PageBean;
import com.shketai.entity.Student;

public class ImessageService {

	private ImessageDao imessageDao;
	
	private NoticeService noticeService;
	
	private UserDao userDao;
	
	private ClassinfoDao classinfoDao;
	
	private StudentDao studentDao;
	
	

	public void setClassinfoDao(ClassinfoDao classinfoDao) {
		this.classinfoDao = classinfoDao;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public void setImessageDao(ImessageDao imessageDao) {
		this.imessageDao = imessageDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void add(String head,String message,int teacher_id){
		List<Integer> user_ids = userDao.findAllIds();
		imessageDao.add(user_ids, head, message, teacher_id);
	}
	
	public PageBean<Imessage> find(int user_id,int page){
		PageBean<Imessage> pb = new PageBean<Imessage>();
		List<Imessage> list = imessageDao.find(user_id,page);
		pb.setDatas(list);
		int total = imessageDao.findCount1(user_id);
		pb.setTotal(total);
		return pb;
	}
	
	public void updateFlag(int id){
		imessageDao.updateFlag(id);
	}
	
	public void updateFlagBySelect(List<Integer> ids,int flag) {
		imessageDao.updateFlagBySelect(ids, flag);
	}
	public Imessage findById(int id) {
		Imessage imessage = imessageDao.findById(id);
		return imessage;
	}
	
	public int delete(Imessage imessage){
		int result = imessageDao.delete(imessage);
		return result;
	}
	public int deleteAll(List<Integer> ids){
		int result = imessageDao.deleteAll(ids);
		return result;
	}
	public int count(int user_id){
		int result = imessageDao.findCount(user_id);
		return result;
	}
	
	/**
	 *   学生端查看站内信 前三条
	 */
	public List<Imessage> queryFirstThreeByImessage(int user_id){
		return imessageDao.queryFirstThreeByImessage(user_id);
	}
	
	
	/**
	 * 添加开学通知
	 * @param user_id
	 * @param teacher_id
	 * @return
	 */
	public int addOpenNotice(String head,int stu_id, int user_id , int teacher_id ) {
		
		Notice notice =  noticeService.firstNotice();
		
		if(notice == null) {
			notice = new Notice();
		}
		
		String message = notice.getContent();
		
		
		List<Classinfo> classList = classinfoDao.queryStu_idReturnClassinfo(stu_id);
		
		for (int i = 0; i < classList.size(); i++) {
			
			String str = "";
			
			String br = "</br>";
			
			Classinfo classinfo =  classList.get(i);
			
			Student student = studentDao.queryId(stu_id);
			
			str =str  + br;
			
			str =str  + br;
			
			str =str +  "  姓名 : " + student.getStuName() + br;
			
			str =str +  "  科目 : " + classinfo.getSubject().getSubject() + br;
			
			str = str + "  班级 : " + classinfo.getClassname() + br;
			
			String day = "";
			
			Classroominfo  classroominfo = classinfo.getClassroominfo();
			
			if(classroominfo.getDay() == 7) {
				day = "星期日 " + classroominfo.getStart() + "--" + classroominfo.getJieshu();
			}
			if(classroominfo.getDay() == 6) {
				day = "星期六 " + classroominfo.getStart() + "--" + classroominfo.getJieshu();
			}
			if(classroominfo.getDay() == 5) {
				day = "星期五 " + classroominfo.getStart() + "--" + classroominfo.getJieshu();
			}
			if(classroominfo.getDay() == 4) {
				day = "星期四 " + classroominfo.getStart() + "--" + classroominfo.getJieshu();
			}
			if(classroominfo.getDay() == 3) {
				day = "星期三 " + classroominfo.getStart() + "--" + classroominfo.getJieshu();
			}
			if(classroominfo.getDay() == 2) {
				day = "星期二 " + classroominfo.getStart() + "--" + classroominfo.getJieshu();
			}
			if(classroominfo.getDay() == 1) {
				day = "星期一 " + classroominfo.getStart() + "--" + classroominfo.getJieshu();
			}
			
			str = str + "  上课时间 : " + day + br;
			
			str = str + "  教室 : " + classroominfo.getClassRoom().getName() + br;
			
			
			
			message = message + str ;
		}
		
		
		
		int flag = 0 ;
		Date time = new Date();
		
//		添加站内信
		imessageDao.sqlAdd(head, message, flag, user_id, teacher_id, time);
		
		return 1;
	}
	
}
