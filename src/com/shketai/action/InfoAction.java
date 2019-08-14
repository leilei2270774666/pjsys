package com.shketai.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Info;
import com.shketai.entity.PageBean;
import com.shketai.entity.Student;
import com.shketai.service.ImessageService;
import com.shketai.service.InfoService;
import com.shketai.service.LogService;

public class InfoAction extends ActionSupport implements ModelDriven<Info>{

	private static final long serialVersionUID = 1L;
	
	private String stu_ids;
	private String user_ids;
	private int teacher_id;
	
	private String classinfo_ids;
	
	private String ids;    
	//private List<Integer>  stu_id;
	
	private String stu_id;//1,2,3
	
	private Student student = new Student();
	private int user_id;
	
	private int classinfo_id;
	
	private Integer my_classinfo_id;
	
	private Integer my_stu_id;
	
	private Integer my_flag;
	
	private Integer info_flag;
		
	
	private int flag;            //缴费状态
	
	private Integer pay;         //缴费/续费/退费支付方式
	
	private int result;
	
	private int id;
	
	private String stuName;
	private PageBean<Info> pb;
	
	private int page;    //页码
	
	private String flags;
	
	private String spell_stu_name;
	
	private List<Info> info;
	
	private Info info1 =new Info();

	private InfoService infoService;
	
	private ImessageService imessageService;
	
	private LogService logService;
	
	private InputStream inputStream;//要下载文件的字节输入流
	private String filename;//要下载文件的名字
	private String contentType;//要下载的文件类型
	
	private String randomDir = UUID.randomUUID().toString();//表示download目录中随机目录，每次下载都不一样
	/**
	 * 更新缴费状态
	 * @param stu_id
	 * @param flag
	 * @return
	 */
	public String pay(){
		 System.out.println("========================================" + ids);
		 System.out.println("========================================" + flag);
		 if(flag==10){
			 logService.addLog("缴费审核", "拒绝缴费");
		 }
		 if(flag==3&&this.getPay()==1){
			 logService.addLog("缴费审核", "微信支付");
		 }
		 if(flag==3&&this.getPay()==2){
			 logService.addLog("缴费审核", "支付宝支付");
		 }
		 if(flag==3&&this.getPay()==3){
			 logService.addLog("缴费审核", "现金支付");
		 }
		 if(flag==3&&this.getPay()==4){
			 logService.addLog("缴费审核", "POS机支付");
		 }
		 if(flag==11){
			 logService.addLog("续费审核", "拒绝续费");
		 }
		 if(flag==9&&this.getPay()==1){
			 logService.addLog("续费审核", "微信支付");
		 }
		 if(flag==9&&this.getPay()==2){
			 logService.addLog("续费审核", "支付宝支付");
		 }
		 if(flag==9&&this.getPay()==3){
			 logService.addLog("续费审核", "现金支付");
		 }
		 if(flag==9&&this.getPay()==4){
			 logService.addLog("续费审核", "POS机支付");
		 }
		 if(flag==12){
			 logService.addLog("退费审核", "拒绝退费");
		 }
		 if(flag==6&&this.getPay()==1){
			 logService.addLog("退费审核", "微信支付");
		 }
		 if(flag==6&&this.getPay()==2){
			 logService.addLog("退费审核", "支付宝支付");
		 }
		 if(flag==6&&this.getPay()==3){
			 logService.addLog("退费审核", "现金支付");
		 }
		 if(flag==6&&this.getPay()==4){
			 logService.addLog("退费审核", "POS机支付");
		 }
		try{
			List<Integer> list = new ArrayList<Integer>();
			String[] array = ids.split(",");
			List<String> list2 = Arrays.asList(array);
			for (String string : list2) {
				list.add(Integer.parseInt(string));
			}
			 infoService.update(list, info1.getFlag(),this.getPay());
			
			 
			 if((flag==3||flag==9)&&(stu_ids != null)) {
				 
				 String[] stu_id_Arr = stu_ids.split(",");
				 String[] user_id_Arr = user_ids.split(",");
				 
				 for (int i = 0; i < stu_id_Arr.length; i++) {
					 imessageService.addOpenNotice("开学通知", Integer.parseInt( stu_id_Arr[i] ), Integer.parseInt( user_id_Arr[i] ), teacher_id);
				 }
				 
			 }
			 
			 
			 result = 1;
		}catch(Exception e){
			result = 0;
		}
		
		return SUCCESS;
	}
	
	
	/**
	  * 批量删除 id
	 */
	public String payDeleteIds(){
		 System.out.println("========================================" + ids);
		 System.out.println("========================================" + flag);
		try{
			List<Integer> list = new ArrayList<Integer>();
			String[] array = ids.split(",");
			List<String> list2 = Arrays.asList(array);
			for (String string : list2) {
				list.add(Integer.parseInt(string));
			}
			 infoService.deleteListId(list);
			 
			 result = 1;
		}catch(Exception e){
			result = 0;
		}
		
		return SUCCESS;
	}
	
	 /**
	  * 退班
	  * @return
	  */
	 public String quitSubject(){
		 System.out.println(info1.getId());
		 infoService.quitSubject(info1.getId());
		 result =1 ;
		 return SUCCESS;
	 }
	 
	public String updateFlag(){
		logService.addLog("缴费/续费/退费审核", "添加/修改备注");
		infoService.updateFlag(info1.getId(), info1.getFlags());
		//System.out.println(info1.getId());
		System.out.println(info1.getFlags());
		result = 1;
		return SUCCESS;
	}
	
	/**
	 *  显示出已选这门课的学生
	 * @return
	 */
	public String find(){
		if(page==0){
			page = 1; //如果没有发送page参数，默认查询第一条
		}
		pb = infoService.findByClassinfoId(classinfo_id, page , 4);
		return SUCCESS;
	}
	
	public String findByDesc(){
		if(page==0){
			page = 1; //如果没有发送page参数，默认查询第一条
		}
		pb = infoService.findByClassinfoIdDesc(classinfo_id, page , 4);
		return SUCCESS;
	}
	public String findAll(){
		info = infoService.findAll(classinfo_id);
		return SUCCESS;
	}
	/**
	 * 删除选课
	 * 
	 * @return
	 */
	public String deleteById(){
		logService.addLog("学生管理-选课", "删除");
		result = infoService.deleteById(info1.getId());
		return SUCCESS;
	}
	public String findAllInfo(){
		info = infoService.findAllInfo(user_id);
		return SUCCESS;
	}
	
	public String findAllInfo1(){
		info = infoService.findAllInfo1(user_id);
		return SUCCESS;
	}
	
	public String findAllInfo2(){
		info = infoService.findAllInfo2(user_id);
		return SUCCESS;
	}
	
	public String findAllInfo3(){
		info = infoService.findAllInfo3(user_id);
		return SUCCESS;
	}
	public String findAllInfo4(){
		
		logService.addLog("缴费审核", "查询");
		
		pb = infoService.findAllInfo4(page);
		return SUCCESS;
	}
	public String findAllInfo5(){
		
		logService.addLog("续费审核", "查询");
		
		pb = infoService.findAllInfo5(page);
		return SUCCESS;
	}
	public String findAllInfo6(){
		
		logService.addLog("退费审核", "查询");
		
		pb = infoService.findAllInfo6(page);
		return SUCCESS;
	}
	
	public String show1()  {
		//username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
		logService.addLog("缴费审核", "按姓名查询");
		info = infoService.queryByUsername1(stuName);
		return SUCCESS;
	}
	
	public String show2()  {
		//username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
		logService.addLog("续费审核", "按姓名查询");
		System.out.println(student.getStuName());
		info = infoService.queryByUsername2(stuName);
		return SUCCESS;
	}
	
	public String show3()  {
		//username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
		logService.addLog("退费审核", "按姓名查询");
		info = infoService.queryByUsername3(stuName);
		return SUCCESS;
	}
	/**
	 * 查出该班级下的学生
	 * @param classinfo_id
	 * @return
	 */
	public String findAllByclassId(){
//		info = infoService.findAllByclassId(classinfo_id);
		return SUCCESS;
	}
	
	public String findByclassId1(){
		logService.addLog("学生考勤管理", "出勤");
		pb = infoService.findByClassinfoId1(classinfo_id, page);
		return SUCCESS;
	}
	
	/**
	 * spell 7天内没有考情 
	 * 
	 * 传递参数：
	 * 	spell_stu_name
	 * 	page
	 * 
	 * 返回参数：
	 * 	pb
	 * 
	 */
	public String spellFindByClassinfoId() {
		logService.addLog("学生考勤管理", "按照学生姓名查询考勤");
		pb = infoService.spellFindByClassinfoId( spell_stu_name, page);
		return SUCCESS;
	}
	
	/**
	 * 我的课程
	 * @return
	 */
	public String findClassinfo(){
		info = infoService.find(user_id);
		return SUCCESS;
	}
	
	/**
	 * 添加 学生 和 班级 关联
	 * @return
	 * 传递参数 
	 * 	my_flag 缴费状态
	 * 	my_classinfo_id 班级id
	 * 	my_stu_id 学生id
	 */
	public String addInfoInClass_idStu_idFlag() {
		logService.addLog("财务数据汇总-缴费明细", "按姓名添加");
		infoService.my_addInfo( my_stu_id, my_classinfo_id, my_flag);
		
		info_flag = 1;
		
		return SUCCESS;
	}
	
	/**
	 *  更新 info指定 班级id 的 flag状态
	 * 
	 * 传递参数：
	 * 	classinfo_ids 班级id 逗号拼接
	 * 	my_flag 状态
	 *返回参数：
	 *	
	 * info_flag = 1
	 */
	public String infoUpdateClassinfo_idsByflag() {
		logService.addLog("班级管理-老生", "修改");
		if(classinfo_ids != null) {
			
			infoService.updateClassinfo_idsByflag(classinfo_ids, my_flag);
		 }
		
		info_flag = 1 ;
		
		return SUCCESS;
		
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getClassinfo_id() {
		return classinfo_id;
	}

	public void setClassinfo_id(int classinfo_id) {
		this.classinfo_id = classinfo_id;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public List<Info> getInfo() {
		return info;
	}

	public void setInfo(List<Info> info) {
		this.info = info;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getStu_id() {
		return stu_id;
	}

	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
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



	public InputStream getInputStream() {
		return inputStream;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}



	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getRandomDir() {
		return randomDir;
	}

	public Info getInfo1() {
		return info1;
	}

	public void setInfo1(Info info1) {
		this.info1 = info1;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setRandomDir(String randomDir) {
		this.randomDir = randomDir;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Info getModel() {
		// TODO Auto-generated method stub
		return info1;
	}

	public Integer getMy_classinfo_id() {
		return my_classinfo_id;
	}

	public void setMy_classinfo_id(Integer my_classinfo_id) {
		this.my_classinfo_id = my_classinfo_id;
	}

	public Integer getMy_stu_id() {
		return my_stu_id;
	}

	public void setMy_stu_id(Integer my_stu_id) {
		this.my_stu_id = my_stu_id;
	}

	public Integer getInfo_flag() {
		return info_flag;
	}

	public void setInfo_flag(Integer info_flag) {
		this.info_flag = info_flag;
	}

	public Integer getMy_flag() {
		return my_flag;
	}

	public void setMy_flag(Integer my_flag) {
		this.my_flag = my_flag;
	}

	public ImessageService getImessageService() {
		return imessageService;
	}

	public void setImessageService(ImessageService imessageService) {
		this.imessageService = imessageService;
	}

	public InfoService getInfoService() {
		return infoService;
	}

	public String getStu_ids() {
		return stu_ids;
	}

	public void setStu_ids(String stu_ids) {
		this.stu_ids = stu_ids;
	}

	public String getUser_ids() {
		return user_ids;
	}

	public void setUser_ids(String user_ids) {
		this.user_ids = user_ids;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getClassinfo_ids() {
		return classinfo_ids;
	}

	public void setClassinfo_ids(String classinfo_ids) {
		this.classinfo_ids = classinfo_ids;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String getSpell_stu_name() {
		return spell_stu_name;
	}

	public void setSpell_stu_name(String spell_stu_name) {
		this.spell_stu_name = spell_stu_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Integer getPay() {
		return pay;
	}


	public void setPay(Integer pay) {
		this.pay = pay;
	}
	
	
}
