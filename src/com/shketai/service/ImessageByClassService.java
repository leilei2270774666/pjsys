package com.shketai.service;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.shketai.dao.ImessageByClassDao;
import com.shketai.entity.ImessageByClass;
import com.shketai.entity.PageBean;

public class ImessageByClassService {

	private ImessageByClassDao imessageByClassDao;

	public void setImessageByClassDao(ImessageByClassDao imessageByClassDao) {
		this.imessageByClassDao = imessageByClassDao;
	}
	
	public void add(List<Integer> classinfo_ids,String head,String message,int teacher_id){
		imessageByClassDao.add(classinfo_ids, head, message, teacher_id);
	}
	
	public PageBean<ImessageByClass> find(int stu_id,int page){
		PageBean<ImessageByClass> pb = new PageBean<ImessageByClass>();
		List<ImessageByClass> list = imessageByClassDao.find(stu_id, page);
		pb.setDatas(list);
		int total = imessageByClassDao.findCount1(stu_id);
		pb.setTotal(total);
		return pb;
	}
	
	public void updateFlag(int id){
		imessageByClassDao.updateFlag(id);
	}
	
	public ImessageByClass findById(int id) {
		ImessageByClass imessageByClass = imessageByClassDao.findById(id);
		return imessageByClass;
	}
	
	public int delete(ImessageByClass imessageByClass){
		int result = imessageByClassDao.delete(imessageByClass);
		return result;
	}


	/**
	 * 删除指定 classinfo_id 
	 * @return
	 */
	public int deleteClassinfo_id(int classinfo_id) {
		return imessageByClassDao.deleteClassinfo_id(classinfo_id);
	}
	
}
