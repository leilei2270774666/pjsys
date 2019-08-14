package com.shketai.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Subject {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String subject;   //科目名称

	private int interview;    //是否需要面试 1：需要 0：不需要
	
	private String introduction;
	
	@Transient
	private Interview_timeslot interview_timeslot;
	
	@Transient
	private int interview_id;
	
	
	//多对一的关系
		@ManyToOne
		@JoinColumn(name="major_id")
		private Majors majors;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Majors getMajors() {
		return majors;
	}

	public void setMajors(Majors majors) {
		this.majors = majors;
	}

	public int getInterview() {
		return interview;
	}

	public void setInterview(int interview) {
		this.interview = interview;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Interview_timeslot getInterview_timeslot() {
		return interview_timeslot;
	}

	public void setInterview_timeslot(Interview_timeslot interview_timeslot) {
		this.interview_timeslot = interview_timeslot;
	}

	public int getInterview_id() {
		return interview_id;
	}

	public void setInterview_id(int interview_id) {
		this.interview_id = interview_id;
	}
	
	
	
}
