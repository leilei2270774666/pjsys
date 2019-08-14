package com.shketai.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.ImessageByInter;
import com.shketai.entity.PageBean;
import com.shketai.service.ImessageByInterService;
import com.shketai.service.LogService;

public class ImessageByInterAction  extends ActionSupport implements ModelDriven<ImessageByInter>{
	
	private static final long serialVersionUID = 1L;
	
	private int result;  
	
	private int user_id;
	
	private int teacher_id;
	
	private String interview_info_ids;
	
	private int id;
	
	private int stu_id;
	
	private PageBean<ImessageByInter> pb;
	
	private int page;    //页码
	
	private ImessageByInterService imessageByInterService;
	
	private ImessageByInter imessageByInter = new ImessageByInter();
	
	private LogService logService;
	
	/**
	 * 按照面试增加一条站内信
	 * @return
	 */
	public String add(){
		logService.addLog("站内通知-面试", "发送站内信");
		try {

			Map<String, Object> session = ActionContext.getContext().getSession();
			if(session.get("id") == null){
				result = 0;//未登陆，不能发送站内信
			}else{
				int tid = Integer.parseInt(session.get("id").toString());
				imessageByInterService.add(imessageByInter.getHead(), imessageByInter.getMessage(),  tid);;
				result = 1;
			}
			
		} catch (Exception e) {
			result = 0;//未登陆，不能发送站内信
			return SUCCESS;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 学生的站内信息
	 * @return
	 */
	public String find(){
		pb = imessageByInterService.find(stu_id, page);
		return SUCCESS;
	}
	
	/**
	 * 标记已读
	 * @return
	 */
	public String updateFlag(){
		imessageByInterService.updateFlag(imessageByInter.getId());
		result = 1;
		return SUCCESS;
	}
	
	/**
	 * 查看详情
	 */
	@Override
	public String execute() throws Exception {
		imessageByInter = imessageByInterService.findById(imessageByInter.getId());
		return SUCCESS;
	}
	
	/**
	 * 删除消息
	 * @return
	 */
	public String delete(){
		imessageByInterService.delete(imessageByInter);
		result = 1;
		return SUCCESS;
	}
	
	
	
	/**
	 * get set
	 */
	@Override
	public ImessageByInter getModel() {
		return imessageByInter;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getInterview_info_ids() {
		return interview_info_ids;
	}

	public void setInterview_info_ids(String interview_info_ids) {
		this.interview_info_ids = interview_info_ids;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PageBean<ImessageByInter> getPb() {
		return pb;
	}

	public void setPb(PageBean<ImessageByInter> pb) {
		this.pb = pb;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setImessageByInterService(
			ImessageByInterService imessageByInterService) {
		this.imessageByInterService = imessageByInterService;
	}

	public void setImessageByInter(ImessageByInter imessageByInter) {
		this.imessageByInter = imessageByInter;
	}

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public ImessageByInter getImessageByInter() {
		return imessageByInter;
	}

	public ImessageByInterService getImessageByInterService() {
		return imessageByInterService;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	

	
}
