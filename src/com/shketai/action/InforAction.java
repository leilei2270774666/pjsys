package com.shketai.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.Info;
import com.shketai.entity.PageBean;
import com.shketai.service.InfoService;

public class InforAction extends ActionSupport implements ModelDriven<Info>{
	
	private static final long serialVersionUID = 1L;
	
	private int id;    
	
	private int stu_id;//1,2,3
	
	private int stu_ids;
	
	private int classinfo_id;
	
	private int flag;            //缴费状态
	
	private int result;
	
	private PageBean<Info> pb;
	
	private int page;    //页码
	
	private List<Info> info;

	private InfoService infoService;
	
	private Info infos = new Info();
	
	/**
	 * 向班级里添加学生
	 * @return
	 */
	public String addInfo() {
		int a = infoService.getNum(classinfo_id);
		if (a <= 0) {
			result = 3; // 该班级名额已满，请选报其他班级!
		} else {
		int i = infoService.checkstuid(stu_id, classinfo_id);
		if(i==1){
		infoService.addInfo(stu_id, classinfo_id);
		result = 1;
		}else{
		result = 0;
		}
		}
		return SUCCESS;
}
	public InfoService getInfoService() {
		return infoService;
	}

	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}

	public List<Info> getInfo() {
		return info;
	}

	public void setInfo(List<Info> info) {
		this.info = info;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public int getClassinfo_id() {
		return classinfo_id;
	}


	public void setClassinfo_id(int classinfo_id) {
		this.classinfo_id = classinfo_id;
	}


	public PageBean<Info> getPb() {
		return pb;
	}


	public void setPb(PageBean<Info> pb) {
		this.pb = pb;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}

	public Info getInfos() {
		return infos;
	}

	public void setInfos(Info infos) {
		this.infos = infos;
	}

	@Override
	public Info getModel() {
		// TODO Auto-generated method stub
		return infos;
	}

	public int getStu_ids() {
		return stu_ids;
	}

	public void setStu_ids(int stu_ids) {
		this.stu_ids = stu_ids;
	}
	
	
}

