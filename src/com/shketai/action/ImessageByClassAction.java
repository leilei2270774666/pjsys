package com.shketai.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.ImessageByClass;
import com.shketai.entity.PageBean;
import com.shketai.service.ImessageByClassService;
import com.shketai.service.LogService;

public class ImessageByClassAction extends ActionSupport implements ModelDriven<ImessageByClass>{
	
private static final long serialVersionUID = 1L;
	
	private int result;  
	
	private int teacher_id;
	
	private String classinfo_ids;
	
	private PageBean<ImessageByClass> pb;
	
	private int page;    //页码
	
	private int stu_id;
	
	private ImessageByClass imessageByClass = new ImessageByClass();
	
	private List<ImessageByClass> imessageByClass1;
	
	private ImessageByClassService imessageByClassService;
	
	private LogService logService;

	/**
	 * 按照班级新增站内信息
	 * @return
	 */
	public String add(){
		logService.addLog("站内通知-班级", "发送站内信");
		try {
			
			//Map<String, Object> session = ActionContext.getContext().getSession();
			if(String.valueOf(this.getTeacher_id()) == null){
				result = 0;//未登陆，不能发送站内信
			}else{
				List<Integer> list = new ArrayList<Integer>();
				String[] array = classinfo_ids.split(",");
				List<String> list2 = Arrays.asList(array);
				for (String string : list2) {
					list.add(Integer.parseInt(string));
				}
				imessageByClassService.add(list,imessageByClass.getHead(), imessageByClass.getMessage(),teacher_id);
				System.out.println(imessageByClass.getHead());
				System.out.println(imessageByClass.getMessage());
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
		pb = imessageByClassService.find(stu_id, page);
		return SUCCESS;
	}
	
	/**
	 * 标记已读
	 * @return
	 */
	public String updateFlag(){
		imessageByClassService.updateFlag(imessageByClass.getId());
		result = 1;
		return SUCCESS;
	}
	
	/**
	 * 查看详情
	 */
	@Override
	public String execute() throws Exception {
		imessageByClass = imessageByClassService.findById(imessageByClass.getId());
		return SUCCESS;
	}
	
	/**
	 * 删除消息
	 * @return
	 */
	public String delete(){
		imessageByClassService.delete(imessageByClass);
		result = 1;
		return SUCCESS;
	}
	
	@Override
	public ImessageByClass getModel() {
		return imessageByClass;
	}



	public int getResult() {
		return result;
	}



	public void setResult(int result) {
		this.result = result;
	}



	public int getTeacher_id() {
		return teacher_id;
	}



	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public PageBean<ImessageByClass> getPb() {
		return pb;
	}

	public void setPb(PageBean<ImessageByClass> pb) {
		this.pb = pb;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public ImessageByClass getImessageByClass() {
		return imessageByClass;
	}

	public void setImessageByClass(ImessageByClass imessageByClass) {
		this.imessageByClass = imessageByClass;
	}

	public List<ImessageByClass> getImessageByClass1() {
		return imessageByClass1;
	}

	public void setImessageByClass1(List<ImessageByClass> imessageByClass1) {
		this.imessageByClass1 = imessageByClass1;
	}

	public ImessageByClassService getImessageByClassService() {
		return imessageByClassService;
	}

	public String getClassinfo_ids() {
		return classinfo_ids;
	}

	public void setClassinfo_ids(String classinfo_ids) {
		this.classinfo_ids = classinfo_ids;
	}

	public void setImessageByClassService(
			ImessageByClassService imessageByClassService) {
		this.imessageByClassService = imessageByClassService;
	}

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	
}
