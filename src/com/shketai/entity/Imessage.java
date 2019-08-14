package com.shketai.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Imessage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;         //id@Id
	
	private String head;  //消息标题
	
	private String message;  //消息内容
	
	private Date time;      //发布时间
	
	private int flag;      //标记是否已读
	
	@ManyToOne
	@JoinColumn(name="user_id")  //家长id
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name="teacher_id")  //教师id
	private Teacher teacher;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
