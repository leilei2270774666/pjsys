package com.shketai.service;

import java.text.ParseException;
import java.util.List;

import com.shketai.entity.Classinfo;
import com.shketai.entity.Classroominfo;
import com.shketai.dao.ClassroominfoDao;

public class ClassroominfoService {
	
	private ClassroominfoDao ClassroominfoDao;
	
	
	public void setClassroominfoDao(ClassroominfoDao classroominfoDao) {
		ClassroominfoDao = classroominfoDao;
	}

	/**
	 * 新增教室的时间段
	 * @param classroominfo
	 * @return
	 */
	public int add(Classroominfo classroominfo,int classroom_id){
		int result = ClassroominfoDao.add(classroominfo, classroom_id);
		return result;
	}
	
	
	public List<Classroominfo> findAll() {
		List<Classroominfo> list = ClassroominfoDao.findAll();
		return list;
	}
	

	public List<Classroominfo> findAll1(int classroom_id) {
		return ClassroominfoDao.findAll1(classroom_id);
	}
	
	public String percent(int classroom_id) throws ParseException{
		String result = ClassroominfoDao.percent(classroom_id);
		return result;
	}
}
