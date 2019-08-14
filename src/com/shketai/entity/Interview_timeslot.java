package com.shketai.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.struts2.json.annotations.JSON;
@Entity
public class Interview_timeslot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Date starttime;             //面试开始时间
	
	private Date end_time;              //面试结束时间
	
	private int num;                    //可以面试的总人数
	
	@ManyToOne
	@JoinColumn(name="interview_id")  //面试场次编号
	private Interview interview;
	
	@JSON(format="yyyy-MM-dd hh:mm")
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	@JSON(format="yyyy-MM-dd hh:mm")
	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

}
