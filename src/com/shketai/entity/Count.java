package com.shketai.entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Count {

	private int id;
	
	private int attence;
	
	private int unattence;
	
	private int late;
	
	private int early;
	
	//一对一的关系
	@OneToOne
	@JoinColumn(name="stu_id")  //学生id
	private Student student;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAttence() {
		return attence;
	}

	public void setAttence(int attence) {
		this.attence = attence;
	}

	public int getUnattence() {
		return unattence;
	}

	public void setUnattence(int unattence) {
		this.unattence = unattence;
	}

	public int getLate() {
		return late;
	}

	public void setLate(int late) {
		this.late = late;
	}

	public int getEarly() {
		return early;
	}

	public void setEarly(int early) {
		this.early = early;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
