package com.shketai.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.shketai.dao.StudentDao;
import com.shketai.entity.Student;
import com.shketai.entity.User;
import com.shketai.entity.PageBean;

public class StudentService {

	private StudentDao studentDao;

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	

	public int add(Student student,int user_id){
		int result = studentDao.add(student,user_id);
		return result;
	}

	public List<Student> findById(int user_id) {
		return studentDao.findById(user_id);
	}
	
	public int checkPersonalNum(String personalNum){
		int result = studentDao.checkPersonalNum(personalNum);
		return result;
	}
	
	
	
	public List<Student> queryByUsername(String stuName){
		return studentDao.queryByUsername(stuName);
	}
	
	public PageBean<Student> find(int page){
		PageBean<Student> pb = new PageBean<Student>();
		List<Student> list = studentDao.find(page);
		pb.setDatas(list);
		int total = studentDao.findCount();
		pb.setTotal(total);
		return pb;
	}
	
	public PageBean<Student> finddesc(int page){
		PageBean<Student> pb = new PageBean<Student>();
		List<Student> list = studentDao.finddesc(page);
		pb.setDatas(list);
		int total = studentDao.findCount();
		pb.setTotal(total);
		return pb;
	}
	public void update(Student student,int user_id) {
		
		studentDao.update(student, user_id);;
	}
	
	public int delete(Student student){
		int result = studentDao.delete(student);
		return result;		
	}

	public List<Student> findById1(int id) {
		return studentDao.findById1(id);
	}


	/**
	 * 查询指定 身份证号 的 学生
	 * 
	 * 返回数量
	 */
	public int queryPersonalNum(String personalNum){
		return  studentDao.queryPersonalNum(personalNum);
	}
	
	/**
	 *  查询指定 身份证号 的 学生
	 *   返回 学生对象
	 */
	public Student queryPersonalNumReturnObj(String personalNum){
		return  studentDao.queryPersonalNumReturnObj(personalNum);
	}

	/**
	 *  查询指定姓名 的 学生 并且和 班级没有关联
	 *   返回 学生集合对象
	 */
	public List<Student> queryStuName(String name){
		
		return  studentDao.queryStuName(name);
	}
	
}
