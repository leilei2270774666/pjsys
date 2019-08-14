package com.shketai.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.struts2.json.annotations.JSON;

/**
 *	图片库
 */
@Entity
public class PictureLibrary {
	/**
	 *     构造方法
	 */
	public PictureLibrary() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	//图片名称
	private String picturename;
	
	//图片类型
	private String picturetype;
	
	//图片路径
	private String picturepath;
	
	//上传时间
	private Date uploadtime;
	
//	老师id
	private Integer teacher_id;
	
	
	/**
	 *  赋值
	 * @param picturename
	 * @param picturetype
	 * @param picturepath
	 * @param uploadtime
	 */
	public void setValuePictureLibrary(String picturename, String picturetype, String picturepath, Date uploadtime) {
		this.picturename = picturename;
		this.picturetype = picturetype;
		this.picturepath = picturepath;
		this.uploadtime = uploadtime;
	}
	
	

	/**
	 * get set
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPicturename() {
		return picturename;
	}

	public void setPicturename(String picturename) {
		this.picturename = picturename;
	}

	public String getPicturetype() {
		return picturetype;
	}

	public void setPicturetype(String picturetype) {
		this.picturetype = picturetype;
	}

	public String getPicturepath() {
		return picturepath;
	}

	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}

	
	
	public Integer getTeacher_id() {
		return teacher_id;
	}



	public void setTeacher_id(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}



	@JSON(format="yyyy-MM-dd")
	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
	
	
}
