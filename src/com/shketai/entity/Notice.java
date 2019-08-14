package com.shketai.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *	
 *	通知
 *
 */
@Entity
public class Notice {
	/**
	 *  构造方法	
	 */
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 *	property 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	//id
	
	private Date create_time;	//创建时间
	
	private String content;	//上下文
	
	
	
	
	/**
	 * get set
	 */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
