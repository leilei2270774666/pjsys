package com.shketai.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.shketai.dao.ImessageByInterDao;
import com.shketai.dao.Interview_infoDao;
import com.shketai.entity.Imessage;
import com.shketai.entity.ImessageByInter;
import com.shketai.entity.PageBean;

public class ImessageByInterService {

	private ImessageByInterDao imessageByInterDao;
	
	private Interview_infoDao interview_infoDao;

	public void setInterview_infoDao(Interview_infoDao interview_infoDao) {
		this.interview_infoDao = interview_infoDao;
	}

	public void setImessageByInterDao(ImessageByInterDao imessageByInterDao) {
		this.imessageByInterDao = imessageByInterDao;
	}
	
	public void add(String head,String message,int teacher_id){
		List<Integer> interview_info_ids = interview_infoDao.findAllIds();
		imessageByInterDao.add(interview_info_ids, head, message, teacher_id);
	}
	
	
	public PageBean<ImessageByInter> find(int stu_id,int page){
		PageBean<ImessageByInter> pb = new PageBean<ImessageByInter>();
		List<ImessageByInter> list = imessageByInterDao.find(stu_id, page);
		pb.setDatas(list);
		int total = imessageByInterDao.findCount1(stu_id);
		pb.setTotal(total);
		return pb;
	}
	
	public void updateFlag(int id){
		imessageByInterDao.updateFlag(id);
	}
	
	public ImessageByInter findById(int id) {
		ImessageByInter imessageByInter = imessageByInterDao.findById(id);
		return imessageByInter;
	}
	
	public int delete(ImessageByInter imessageByInter){
		int result = imessageByInterDao.delete(imessageByInter);
		return result;
	}

	
	
}
