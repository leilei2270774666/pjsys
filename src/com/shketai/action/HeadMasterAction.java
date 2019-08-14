package com.shketai.action;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.shketai.entity.Classinfo;
import com.shketai.entity.HeadMaster;
import com.shketai.entity.PageBean;
import com.shketai.service.HeadMasterService;
import com.shketai.service.LogService;

/**
 * @author Administrator
 *	班主任 action层
 *
 */
public class HeadMasterAction extends ActionSupport{
	/**
	 *  构造方法	
	 */
	public HeadMasterAction() {
		super();
	}

	/**
	 *  property
	 */
	private static final long serialVersionUID = 1L;
	
	private int id ;	//id
	
	private String name;	//姓名
	
	private String sex;		//性别
	
	private String card;	//身份证
	
	private String phonenumber;	//电话号码
	
	private String comment;	//备注

	
	private HeadMasterService headMasterService; //班主任 业务层对象
	
	private LogService logService;
	
	private int pageNum;
	private int pageSize;
	
	private PageBean<HeadMaster> pb;
	
	private int flag;
	
	private List<Classinfo> classinfoList;
	
	private int classinfo_id;
	
	private List<HeadMaster> headMasterList;
	
	
	/**
	 *    分页查询 降序
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public String queryPageHeadMaster() {
		
		logService.addLog("班主任管理", "查询");
		
		pb = headMasterService.queryPage(pageNum, pageSize);
		
		return SUCCESS;
	}
	
	
	/**
	 * 添加 
	 * name,  sex,  card,  phonenumber,  comment
	 * @return
	 */
	public String addHeadMaster() {
		
		logService.addLog("班主任管理", "添加");
		
		HeadMaster headMaster = new HeadMaster();
		headMaster.setValueHeadMaster(name,  sex,  card,  phonenumber,  comment);
		
		headMasterService.save(headMaster);
		
		flag =1;
		
		return SUCCESS;
	}
	
	/**
	 * 删除 
	 * 传递参数  id  班主任id
	 */
	public String deleteHeadMaster() {
		
		logService.addLog("班主任管理", "删除");
		
		headMasterService.delete(id);
		
		flag = 1;
		
		return SUCCESS;
	}
	
	
	/**
	 *   修改
	 * @return
	 */
	public String updateHeadMaster() {
		
		logService.addLog("班主任管理", "修改");
		
		HeadMaster headMaster = headMasterService.sqlQueryId(id);
		
		headMaster.setValueHeadMaster( name,  sex,  card,  phonenumber,  comment);
		
		headMasterService.update(headMaster);
		
		flag =1;
		
		return SUCCESS;
	}

	
	/**
	 * @return
	 *  查看未分配班主任的班级
	 *  没有班主任的班级
	 */
	public String classinfoInheadmaster_idIsNull(){
		
		logService.addLog("班主任管理", "查看未分配班主任的班级");
		
		classinfoList = headMasterService.classinfoInheadmaster_idIsNull();
		
		return SUCCESS;
	}
	
	/**
	 * @return
	 *  查看指定班主任的班级
	 *  传递班主任id
	 */
	public String classinfoInheadmaster_idIsTrue(){
		
		logService.addLog("班主任管理", "查看指定班主任的班级");
		
		classinfoList = headMasterService.classinfoInheadmaster_idIsTrue(id);
		
		return SUCCESS;
	}
	
	/**
	  *  清空指定班级 里的 班主任关联
	 * @return
	 *  classinfo_id  班级id
	 * 
	 */
	public String classinfoClearheadmaster_id() {
		
		logService.addLog("班主任管理", "清空指定班级 里的 班主任关联");
		
		headMasterService.classinfoClearheadmaster_id(classinfo_id);
		
		flag =1;
		
		return SUCCESS;
	}
	
	/**
	  *   指定班级添加班主任
	 *  classinfo_id 班级id
	 *   id  班主任id
	 */
	public String classinfoUpdateheadmaster_id() {
		
		logService.addLog("班主任管理", "指定班级添加班主任");
		
		headMasterService.classinfoUpdateheadmaster_id( classinfo_id ,  id);		
		
		flag =1;
		
		return SUCCESS;
	}
	
	/**
	  * 查询所有的 班主任
	 */
	public String findAllHeadMaster() {
		
		logService.addLog("班主任管理", "查询所有的 班主任");
		
		headMasterList = headMasterService.findAllHeadMaster();
		
		return SUCCESS;
	}
	
	

	/**
	 * get set
	 * @return
	 */
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getCard() {
		return card;
	}


	public void setCard(String card) {
		this.card = card;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public HeadMasterService getHeadMasterService() {
		return headMasterService;
	}


	public void setHeadMasterService(HeadMasterService headMasterService) {
		this.headMasterService = headMasterService;
	}


	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public PageBean<HeadMaster> getPb() {
		return pb;
	}


	public void setPb(PageBean<HeadMaster> pb) {
		this.pb = pb;
	}


	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


	public List<Classinfo> getClassinfoList() {
		return classinfoList;
	}


	public void setClassinfoList(List<Classinfo> classinfoList) {
		this.classinfoList = classinfoList;
	}


	public int getClassinfo_id() {
		return classinfo_id;
	}


	public void setClassinfo_id(int classinfo_id) {
		this.classinfo_id = classinfo_id;
	}


	public List<HeadMaster> getHeadMasterList() {
		return headMasterList;
	}


	public void setHeadMasterList(List<HeadMaster> headMasterList) {
		this.headMasterList = headMasterList;
	}


	public LogService getLogService() {
		return logService;
	}


	public void setLogService(LogService logService) {
		this.logService = logService;
	}
		
	
	
}
