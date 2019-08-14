package com.shketai.action;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Info;
import com.shketai.entity.PageBean;
import com.shketai.entity.Student;
import com.shketai.service.ClassinfoService;
import com.shketai.service.LogService;

public class ClassinfoAction extends ActionSupport implements ModelDriven<Classinfo> {

	private static final long serialVersionUID = 1L;

	private int result;

	private int id;
	
	private Integer bid;

	private String classname; // 班级名

	private double unit_price; // 每节课时费

	private int keshi; // 总课时

	private double money; // 总费用

	private int min_age; // 年龄段：最小年龄

	// private int max_age; //年龄段：最大年龄

	private int stu_id; // 学生ID

	private int classinfo_id; // 班级ID
	
	private int classinfo_id_clear; //清空的班级id
	
	private int classinfo_flag;	 // 返回 1 成功 0 失败
	
	private int classroom_id;

	private int classroominfo_id;

	private int subject_id; // 科目ID

	private int major_id;

	private int page;
	
	private int pageNum;
	private int pageSize;
	
	private String my_classname;

	private ClassinfoService classinfoService;
	
	private LogService logService;

	private List<Classinfo> classinfo1;

	private Classinfo classinfo = new Classinfo();

	private Info info = new Info();
	private Student student = new Student();

	private PageBean<Classinfo> pb;
	
	private Date dateTime;  //时间

	
	
	public String findAll() {
		
		logService.addLog("班级管理", "查询");
		
		int count = classinfoService.findRQ(classinfo.getId());
		classinfo.setCount(count);
		pb = classinfoService.findAll(page);
		return SUCCESS;
	}

	public String findAll1() {
		/*
		 * int count = classinfoService.findRQ(classinfo.getId());
		 * classinfo.setCount(count);
		 */
		
		//logService.addLog("班级管理", "老生查询");
		
		classinfo1 = classinfoService.sqlAllClassinfo();
		return SUCCESS;
	}

	/**
	 * 新增班级
	 * 
	 * @return
	 */
	public String add() {
		
		logService.addLog("班级管理", "新增");
		
		result = classinfoService.add(classinfo, subject_id);
		return SUCCESS;
	}

	/**
	 * 删除班级
	 * 
	 * @return
	 */
	public String delete() {
		
		logService.addLog("班级管理", "删除");
		
		classinfoService.delete(classinfo);
		result = 1;
		return SUCCESS;
	}

	/**
	 * 更新班级
	 * 
	 * @return
	 */

	public String update() {
		
		logService.addLog("班级管理", "修改");
		
		result = classinfoService.update(classinfo);
		return SUCCESS;
	}

	/**
	 * 查询符合年龄的班级
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findBySubjectAndAge() throws Exception {
		
		logService.addLog("班级管理", " 查询符合年龄的班级");
		
		if (page == 0) {
			page = 1; // 如果没有发送page参数，默认查询第一条
		}
		pb = classinfoService.findBySubjectAndAge(major_id, stu_id, page);
		return SUCCESS;
	}

	/**
	 * 查询符合年龄的班级
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findBySubjectAndAges() throws Exception {
		
		logService.addLog("班级管理", " 查询符合年龄的班级");
		
		classinfo1 = classinfoService.findBySubjectAndAges(subject_id, stu_id);
		return SUCCESS;
	}

	/**
	 * 选课
	 * 		result = 3; // 该班级名额已满，请选报其他班级!
	 * 		flag == 1  需要面试
	 * 		result = -1; // 没有面试，需要预约面试
	 * 		result = 0    // 冲突了
	 * 		result = 1	// 面试通过，选课成功
	 * 		result = 2; // 面试不通过的，不能选课
	 * 	
	 * @return
	 */
	public String selectSubject() {
		
		logService.addLog("班级管理", " 选课");
		
		int a = classinfoService.getNum(classinfo_id);
		if (a <= 0) {
			result = 3; // 该班级名额已满，请选报其他班级!
		} else {
			int flag = classinfoService.findflag(classinfo_id);
			if (flag == 1) {
				// 需要面试
				int results = classinfoService.findInterview_info(stu_id);
				if (results == -1) {
					result = -1; // 没有面试，需要预约面试
				}
				if (results == 1) {
					result = classinfoService.selectSubject(stu_id, classinfo_id); // 面试通过的
																					// ，选课成功
				} else if (results == 2) {
					result = 2; // 面试不通过的，不能选课
				}
			} else if (flag == 0) {
				result = classinfoService.selectSubject(stu_id, classinfo_id); // 选课成功
			}
		}
		return SUCCESS;
	}

	/* *//**
			 * 退班
			 * 
			 * @return
			 *//*
			 * public String quitSubject(){ System.out.println(info.getId());
			 * classinfoService.quitSubject(info.getId()); result =1 ; return
			 * SUCCESS; }
			 */

	/**
	 * 教室排班
	 * 
	 * @return
	 */
	public String selectClassroom() {
		
		logService.addLog("班级管理", " 排班");
		
		classinfoService.selectClassroom(classinfo);
		result = 1;
		return SUCCESS;
	}

	/**
	 * 根据姓名查找班级
	 * 
	 * @return
	 */
	public String show() {
		
		logService.addLog("班级管理", " 根据姓名查找班级");
		
		// username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
		classinfo1 = classinfoService.queryByUsername(classinfo.getClassname());
		return SUCCESS;
	}
	
	/**
	 * 根据id查找班级
	 * 
	 * @return
	 */
	public String showbj() {
		
		logService.addLog("班级管理", " 根据id查找班级");
		
		// username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
		classinfo1 = classinfoService.queryById(bid);
		return SUCCESS;
	}

	public String findRoom(){
		
		logService.addLog("班级管理", " 查询指定班级的教室");
		
		classinfo1 = classinfoService.findRoom(classroom_id);
		return SUCCESS;
	}
	
	/**
	 *  合并班级 
	 *  
	 *  classinfo_id; // 班级ID
	 *	classinfo_id_clear; //清空的班级id
	 *  classinfo_flag // 返回 1 成功 0 失败
	 */
	public String mergeClassinfo() {
		
		logService.addLog("班级管理", " 合并");
		
		// 返回 1 成功 0 失败
		classinfo_flag = classinfoService.mergeClassinfo(classinfo_id, classinfo_id_clear);
		
		return SUCCESS;
	}
	
	/**
	 * @return
	 *  查看未分配教室的班级
	 *  没有教室的班级
	 */
	public String classinfoInclassroominfo_idIsNull() {
		
		logService.addLog("班级管理", " 查看未分配教室的班级");
		
		classinfo1 = classinfoService.classinfoInclassroominfo_idIsNull();
		
		return SUCCESS;
	}
	
	/**
	 *    清空班级下的所有学生
	 *    把班级里的剩余人数初始化
	 *    
	 *  传递参数 classinfo_id 班级 id
	 * 
	 * 返回参数 classinfo_flag
	 */
	public String classinfoClearStudent() {
		
		logService.addLog("班级管理", "清空班级下的所有学生");
		
		classinfoService.classinfoClearStudent(classinfo_id);
		
		classinfo_flag = 1;
		
		return SUCCESS;
	}
	
	/**
	  * 班级考勤数据汇总
	 *   pageNum 页码
	 *  pageSize 数量
	 *  my_classname 班级名称
	 */
	public String queryClassinfoInAttence() {
		
		logService.addLog("班级管理", "班级考勤数据汇总");
		
		pb = classinfoService.queryClassinfoInAttence( pageNum, pageSize, my_classname,dateTime);
		
		return SUCCESS;
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

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public int getKeshi() {
		return keshi;
	}

	public void setKeshi(int keshi) {
		this.keshi = keshi;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getMin_age() {
		return min_age;
	}

	public void setMin_age(int min_age) {
		this.min_age = min_age;
	}

	public Classinfo getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(Classinfo classinfo) {
		this.classinfo = classinfo;
	}

	public void setClassinfoService(ClassinfoService classinfoService) {
		this.classinfoService = classinfoService;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public List<Classinfo> getClassinfo1() {
		return classinfo1;
	}

	public void setClassinfo1(List<Classinfo> classinfo1) {
		this.classinfo1 = classinfo1;
	}

	public int getClassroominfo_id() {
		return classroominfo_id;
	}

	public void setClassroominfo_id(int classroominfo_id) {
		this.classroominfo_id = classroominfo_id;
	}

	public Classinfo getModel() {
		return classinfo;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean<Classinfo> getPb() {
		return pb;
	}

	public void setPb(PageBean<Classinfo> pb) {
		this.pb = pb;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public int getMajor_id() {
		return major_id;
	}

	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}

	public int getClassroom_id() {
		return classroom_id;
	}

	public void setClassroom_id(int classroom_id) {
		this.classroom_id = classroom_id;
	}

	public int getClassinfo_id_clear() {
		return classinfo_id_clear;
	}

	public void setClassinfo_id_clear(int classinfo_id_clear) {
		this.classinfo_id_clear = classinfo_id_clear;
	}

	public int getClassinfo_flag() {
		return classinfo_flag;
	}

	public void setClassinfo_flag(int classinfo_flag) {
		this.classinfo_flag = classinfo_flag;
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

	public String getMy_classname() {
		return my_classname;
	}

	public void setMy_classname(String my_classname) {
		this.my_classname = my_classname;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public ClassinfoService getClassinfoService() {
		return classinfoService;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

}
