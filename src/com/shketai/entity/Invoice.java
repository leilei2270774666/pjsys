package com.shketai.entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Invoice {

	private int id;
	
	private int invoiceId;
	
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

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}
