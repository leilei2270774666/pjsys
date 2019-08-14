package com.shketai.service;


import java.util.List;

import com.shketai.dao.ClassroomDao;
import com.shketai.entity.ClassRoom;
import com.shketai.entity.Classroominfo;

public class ClassroomService {

	private ClassroomDao classroomDao;

	public void setClassroomDao(ClassroomDao classroomDao) {
		this.classroomDao = classroomDao;
	}
	/**
	 * 新增教室
	 * @param classroom
	 * @return
	 */
	public int add(ClassRoom classroom){
		int result = classroomDao.add(classroom);
		return result;
	}
	
	/**
	 * 查询所有教室
	 * @return
	 */
	public List<ClassRoom> findAll() {
		List list = classroomDao.findAll();
		return list;
	}
	
}
