package com.shketai.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.Imessage;
import com.shketai.entity.PageBean;
import com.shketai.service.ImessageService;
import com.shketai.service.LogService;

public class ImessageAction extends ActionSupport implements ModelDriven<Imessage>{

	private static final long serialVersionUID = 1L;
	
	private int result;  
	
	private int user_id;
	
	private int teacher_id;
	
	private String user_ids;
	
	private int id;
	
	private String ids;
	
	private PageBean<Imessage> pb;
	
	private int page;    //页码
	
	private Imessage imessage = new Imessage();
	
	private ImessageService imessageService;
	
	private LogService logService;
	
	private List<Imessage> Imessages;


	public int getResult() {
		return result;
	}


	public void setResult(int result) {
		this.result = result;
	}


	public Imessage getImessage() {
		return imessage;
	}


	public void setImessage(Imessage imessage) {
		this.imessage = imessage;
	}


	public void setImessageService(ImessageService imessageService) {
		this.imessageService = imessageService;
	}

	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Imessage> getImessages() {
		return Imessages;
	}


	public void setImessages(List<Imessage> imessages) {
		Imessages = imessages;
	}


	public String getUser_ids() {
		return user_ids;
	}


	public void setUser_ids(String user_ids) {
		this.user_ids = user_ids;
	}


	public PageBean<Imessage> getPb() {
		return pb;
	}


	public void setPb(PageBean<Imessage> pb) {
		this.pb = pb;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getTeacher_id() {
		return teacher_id;
	}


	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}


	/**
	 * 增加一条站内信
	 * @return
	 */
	public String add(){
		
		logService.addLog("站内通知", "新增");
		
		try {

			Map<String, Object> session = ActionContext.getContext().getSession();
			if(session.get("id") == null){
				result = 0;//未登陆，不能发送站内信
			}else{
				int tid = Integer.parseInt(session.get("id").toString());
				imessageService.add(imessage.getHead(), imessage.getMessage(),  tid);;
				result = 1;
			}
			
		} catch (Exception e) {
			result = 0;//未登陆，不能发送站内信
			return SUCCESS;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 学生端查看站内信
	 * @return
	 */
	public String find(){
		pb = imessageService.find(user_id, page);
		return SUCCESS;
	}
	
	
	/**
	 *   学生端查看站内信 前三条
	 */
	public String queryFirstThreeByImessage(){
//		List<Imessage> ImessagesList = imessageService.queryFirstThreeByImessage(user_id);
		
		try {
			Imessages = imessageService.queryFirstThreeByImessage(user_id);
		} catch (Exception e) {
			
		}
		return SUCCESS;
	}
	
	
	/**
	 * 标记已读
	 * @return
	 */
	public String updateFlag(){
		
		logService.addLog("站内通知", "修改");
		
		imessageService.updateFlag(imessage.getId());
		result = 1;
		return SUCCESS;
	}
	
	public String updateFlagBySelect(){
		
		logService.addLog("站内通知", "修改");
		
		 System.out.println("========================================" + ids);
		try{
			List<Integer> list = new ArrayList<Integer>();
			String[] array = ids.split(",");
			List<String> list2 = Arrays.asList(array);
			for (String string : list2) {
				list.add(Integer.parseInt(string));
			}
			imessageService.updateFlagBySelect(list, imessage.getFlag());
			
			 result = 1;
		}catch(Exception e){
			result = 0;
		}
		
		return SUCCESS;
	}
	/**
	 * 查看详情
	 */
	@Override
	public String execute() throws Exception {
		
		logService.addLog("站内通知", " 查看详情");
		
		imessage = imessageService.findById(imessage.getId());
		return SUCCESS;
	}
	
	/**
	 * 删除消息
	 * @return
	 */
	public String delete(){
		
		logService.addLog("站内通知", " 删除消息");
		
		imessageService.delete(imessage);
		result = 1;
		return SUCCESS;
	}
	
	/**
	 * 多选删除站内消息
	 * @return
	 */
	public String deleteAll(){
		
		logService.addLog("站内通知", " 删除消息");
		
		try{
			List<Integer> list = new ArrayList<Integer>();
			String[] array = ids.split(",");
			List<String> list2 = Arrays.asList(array);
		for (String string : list2) {
			list.add(Integer.parseInt(string));
		}
			imessageService.deleteAll(list);
		 	result = 1;               //删除成功
		}catch(Exception e){
			result = 0;               //删除失败
		}
		
		return SUCCESS;
	}
			
	/**
	 * 未读数
	 * @return
	 */
	public String count(){
		
		logService.addLog("站内通知", " 未读数");
		
		try {
			result = imessageService.count(user_id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	@Override
	public Imessage getModel() {
		// TODO Auto-generated method stub
		return imessage;
	}


	public LogService getLogService() {
		return logService;
	}


	public void setLogService(LogService logService) {
		this.logService = logService;
	}


	public ImessageService getImessageService() {
		return imessageService;
	}
	
	
}
