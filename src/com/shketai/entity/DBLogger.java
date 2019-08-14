package com.shketai.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.struts2.json.annotations.JSON;

@Entity
public class DBLogger {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;         //id
	
	private String mokuai;  //操作模块
	
	private String EventType;//类型
	
	private Date time;    //操作时间
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private Teacher teacher;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMokuai() {
		return mokuai;
	}

	public void setMokuai(String mokuai) {
		this.mokuai = mokuai;
	}

	public String getEventType() {
		return EventType;
	}

	public void setEventType(String eventType) {
		EventType = eventType;
	}
//	@JSON(format="yyyy-MM-dd hh:mm:ss")
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(time);
//		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
