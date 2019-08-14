package com.shketai.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ImessageByInter {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;         //id@Id
	
	private String head;  //消息标题
	
	private String message;  //消息内容
	
	private Date time;      //发布时间
	
	private int flag;      //标记是否已读
	
	@ManyToOne
	@JoinColumn(name="interview_info_id")
	private Interview_info interview_info;
	@ManyToOne
	@JoinColumn(name="teacher_id")  //教师id
	private Teacher teacher;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Interview_info getInterview_info() {
		return interview_info;
	}
	public void setInterview_info(Interview_info interview_info) {
		this.interview_info = interview_info;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	@Override
	public String toString() {
		return "ImessageByInter [id=" + id + ", head=" + head + ", message=" + message + ", time=" + time + ", flag="
				+ flag + ", interview_info=" + interview_info + ", teacher=" + teacher + "]";
	}
	
	
	
	
}
