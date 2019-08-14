package com.shketai.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 班主任
 */
@Entity
public class HeadMaster {
	/**
	 *  构造方法	
	 */
	public HeadMaster() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;	//姓名
	
	private String sex;		//性别
	
	private String card;	//身份证
	
	private String phonenumber;	//电话号码
	
	private String comment;	//备注

	
	
	public void setValueHeadMaster(String name, String sex, String card, String phonenumber, String comment) {
		this.name = name;
		this.sex = sex;
		this.card = card;
		this.phonenumber = phonenumber;
		this.comment = comment;
	}

	/**
	 * get set
	 * 
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
}
