package com.shketai.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 考勤
 * @author Administrator
 *
 */
@Entity
public class Attence {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int attence_status;   //出勤状态  1：正常 2：缺勤 3：迟到 4：早退

	private Date attence_time;//考勤时间
	
	//一对一的关系
	@OneToOne
	@JoinColumn(name="stu_id")  //学生id
	private Student student; 
	
	
	@ManyToOne
	@JoinColumn(name="classinfo_id")
	private Classinfo classinfo;//该考勤是哪个班的
	
	@ManyToOne
	@JoinColumn(name="reason_id")  //学生id
	private Reasons reasons;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAttence_status() {
		return attence_status;
	}

	public void setAttence_status(int attence_status) {
		this.attence_status = attence_status;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getAttence_time() {
		return attence_time;
	}


	public void setAttence_time(Date attence_time) {
		this.attence_time = attence_time;
	}

	public Student getStudent() {
		return student;
	}

	public Classinfo getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(Classinfo classinfo) {
		this.classinfo = classinfo;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Reasons getReasons() {
		return reasons;
	}

	public void setReasons(Reasons reasons) {
		this.reasons = reasons;
	}

	
}
