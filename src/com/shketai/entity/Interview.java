package com.shketai.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 面试
 * @author Administrator
 *
 */
@Entity
public class Interview {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String interviewSubject;    //面试科目
	
	private int subject_id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getInterviewSubject() {
		return interviewSubject;
	}

	public void setInterviewSubject(String interviewSubject) {
		this.interviewSubject = interviewSubject;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}


}
