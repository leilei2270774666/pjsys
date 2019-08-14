package com.shketai.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.Notice;
import com.shketai.service.LogService;
import com.shketai.service.NoticeService;

/**
 * 通知 控制层
 *
 */
public class NoticeAction extends ActionSupport{
	/**
	 *  构造方法	
	 */
	public NoticeAction() {
		super();
	}

	/**
	 *  property
	 */
	private static final long serialVersionUID = 1L;
	
	private NoticeService noticeService;

	private String content;
	
	private Integer flag;
	
	private Notice notice;
	
	private LogService logService;
	
	/**
	 * set
	 */
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	
	/**
	 *  添加 通知
	 *  
	 *  传递参数 :
	 *  	content
	 *  
	 *  返回参数:
	 *  	flag =1 成功
	 */
	public String submitNotice() {
		logService.addLog("站内通知", "更新开学通知");
		Notice notice = new Notice();
		
		notice.setContent(content);
		notice.setCreate_time( new Date() );
		
		noticeService.add(notice);
		
		flag = 1;
		
		return SUCCESS;
	}
	
	/**
	 *  第一条 通知
	 *  
	 *  传递参数 :
	 *  	
	 *  返回参数 :
	 *  	
	 *   
	 */
	public String firstNoticeMessage() {
		logService.addLog("站内通知", "开学通知查询");
		notice = noticeService.firstNotice();
		
		return SUCCESS;
	}
	
	
	/**
	 *  get set
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}


	public Notice getNotice() {
		return notice;
	}


	public void setNotice(Notice notice) {
		this.notice = notice;
	}


	public LogService getLogService() {
		return logService;
	}


	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	

	
}
