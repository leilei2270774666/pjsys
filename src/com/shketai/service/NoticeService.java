package com.shketai.service;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.shketai.dao.NoticeDao;
import com.shketai.entity.Notice;

/**
 * 通知业务层
 *
 */
public class NoticeService {
	/**
	 *  构造方法	
	 */
	public NoticeService() {
		super();
	}

	/**
	 *  property
	 */
	private NoticeDao noticeDao;


	/**
	 *  set
	 */
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	/**
	 * add
	 */
	public int add(Notice notice) {
		
		return noticeDao.add(notice);
	}
	
	
	/**
	 *  第一条 
	 */
	public Notice firstNotice() {
		
		return noticeDao.firstNotice();
	}
	
	
}
