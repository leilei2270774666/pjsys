package com.shketai.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.PageBean;
import com.shketai.entity.Student;
import com.shketai.service.LogService;
import com.shketai.service.StudentService;

public class StudentAction extends ActionSupport implements ModelDriven<Student>{

	private static final long serialVersionUID = 1L;
	
	private int result;
	
	private StudentService studentService;
	
	private LogService logService;
	
	private Student student = new Student();
	
	private List<Student> student1;
	
	private PageBean<Student> pb;
	
	private int page;    //页码
	
	private String flag;

	private int user_id;
	private int id;

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	

	 /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     * @return 生日(yyyyMMdd)
     */
    public static String getBirthByIdCard(String personalNum) {
        return personalNum.substring(6, 14);
    }
	/**
	 * 新增学生信息
	 * @param student
	 * @return
	 * @throws ParseException 
	 */
	public String add() throws ParseException{
		
		logService.addLog("学生管理", "新增");
		
		int i = studentService.checkPersonalNum(student.getPersonalNum());
		if(i==1){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			Date birthday = simpleDateFormat.parse(getBirthByIdCard(student.getPersonalNum()));
			student.setBirthday(birthday);
			result = studentService.add(student,user_id);
		}else{
			result = 0;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新信息
	 * @return
	 * @throws ParseException 
	 */
	public String update() throws ParseException{
		
		logService.addLog("学生管理", "修改");
		
		//result = studentService.update(student, student.getId());
		//int i = studentService.checkPersonalNum(student.getPersonalNum());
		//if(i==0){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			Date birthday = simpleDateFormat.parse(getBirthByIdCard(student.getPersonalNum()));
			student.setBirthday(birthday);
		 studentService.update(student, user_id);;
		result = 1;
		return SUCCESS;
	}
	
	
	
	/**
	 * 删除学生
	 * @return
	 */
	public String delete(){
		
		logService.addLog("学生管理", "删除");
		
		studentService.delete(student);
		result = 1;
		return SUCCESS;
	}
	/**
	 * 查看个人信息
	 * @return
	 */
	public String findById(){
		
		logService.addLog("学生管理", "查看个人信息");
		
		student1 = studentService.findById(user_id);
		return SUCCESS;
	}
	
	public String findById1(){
		logService.addLog("学生管理", "查询指定学生");
		
		student1 = studentService.findById1(student.getId());
		return SUCCESS;
	}
	
	/**
	 * 教师端查看所有学生信息
	 * @return
	 */
	public String find(){
		
		logService.addLog("学生管理", "查看");
		
		if(page==0){
			page = 1; //如果没有发送page参数，默认查询第一条
		}
		pb = studentService.find(page);
		return SUCCESS;
	}
	
	public String finddesc(){
		
		logService.addLog("学生管理", "倒序查看");
		
		if(page==0){
			page = 1; //如果没有发送page参数，默认查询第一条
		}
		pb = studentService.finddesc(page);
		return SUCCESS;
	}
	/**
	 * 根据姓名查询学生信息
	 * @return
	 * 传递参数  stuName 学生姓名
	 */
	public String show()  {
		
		logService.addLog("学生管理", "根据姓名查询学生信息");
		
		//username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
		student1 = studentService.queryByUsername(student.getStuName());
		return SUCCESS;
	}
	
	/**
	 *  查询指定姓名 的 学生 并且和 班级没有关联
	 *   返回 学生集合对象
	 *   传递参数  stuName 学生姓名
	 */
	public String queryStuNameNotInfo() {
		
		logService.addLog("学生管理", "查询指定姓名 的 学生 并且和 班级没有关联");
		
		student1 = studentService.queryStuName(student.getStuName());
		
		return SUCCESS;
	}
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public List<Student> getStudent1() {
		return student1;
	}
	public void setStudent1(List<Student> student1) {
		this.student1 = student1;
	}
	
	public int getPage() {
		return page;
	}

	public PageBean<Student> getPb() {
		return pb;
	}

	public void setPb(PageBean<Student> pb) {
		this.pb = pb;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}


	public Student getModel() {
		return student;
	
	}


	public LogService getLogService() {
		return logService;
	}


	public void setLogService(LogService logService) {
		this.logService = logService;
	}


	public StudentService getStudentService() {
		return studentService;
	}
	
	
}
