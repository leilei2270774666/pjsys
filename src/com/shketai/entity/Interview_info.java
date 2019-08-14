package com.shketai.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class Interview_info {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	//一对一的关系
	@OneToOne
	@JoinColumn(name="stu_id")  //学生id
	private Student student; 
	
	private Date sqDt;    //面试申请时间
	private Date shDt;    //面试审核时间
	
	@ManyToOne
	@JoinColumn(name="interview_timeslot_id")  //面试场次编号
	private Interview_timeslot interview_timeslot;
	
	private int result;         //面试结果
	
//	clear_flag : 1 清空  null 不清空
	private Integer clear_flag ;

	
	public Date getSqDt() {
		return sqDt;
	}

	public void setSqDt(Date sqDt) {
		this.sqDt = sqDt;
	}

	public Date getShDt() {
		return shDt;
	}

	public void setShDt(Date shDt) {
		this.shDt = shDt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Interview_timeslot getInterview_timeslot() {
		return interview_timeslot;
	}

	public void setInterview_timeslot(Interview_timeslot interview_timeslot) {
		this.interview_timeslot = interview_timeslot;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Integer getClear_flag() {
		return clear_flag;
	}

	public void setClear_flag(Integer clear_flag) {
		this.clear_flag = clear_flag;
	}
	
}
