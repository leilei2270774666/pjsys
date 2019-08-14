  package com.shketai.service;

import com.shketai.dao.CountDao;

public class CountService {

	private CountDao countDao;

	public void setCountDao(CountDao countDao) {
		this.countDao = countDao;
	}
	
	public int countStudent(){
		int result = countDao.countStudent();
		return result;
	}
	
	public int countStudentOfInfo(){
		int result = countDao.countStudentOfInfo();
		return result;
	}
	public int countSubject(){
		int result = countDao.countSubject();
		return result;
	}
	
	public int countSubjectClassinfo(){
		int result = countDao.countSubjectClassinfo();
		return result;
	}
	
	public String countpercent1(){
		String result = countDao.countpercent1();
		return result;
	}
	public String countpercent2(){
		String result = countDao.countpercent2();
		return result;
	}
	public String countpercent3(){
		String result = countDao.countpercent3();
		return result;
	}
	public String countpercent4(){
		String result = countDao.countpercent4();
		return result;
	}
	
	public String countpercent5(){
		String result = countDao.countpercent5();
		return result;
	}
	public String countpercent6(){
		String result = countDao.countpercent6();
		return result;
	}public String countpercent7(){
		String result = countDao.countpercent7();
		return result;
	}
	public String countpercent8(){
		String result = countDao.countpercent8();
		return result;
	}
	
	
	public int CountStudentByClassInfoId(int classinfo_id){
		int result = countDao.CountStudentByClassInfoId(classinfo_id);
		return result;
	}
}
