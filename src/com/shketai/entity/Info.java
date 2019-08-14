package com.shketai.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Info {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;              //ID
	
	//一对一的关系
	@OneToOne
	@JoinColumn(name="stu_id")  //学生id
	private Student student; 
	
	//一对一的关系
	@OneToOne
	@JoinColumn(name="classinfo_id") //班级id
	private Classinfo classinfo;
	
	private int flag;            //缴费状态
	
	private String flags;
	
	private Date s_j_time;     //申请缴费时间 （2018/10/25添加：ning）
	private Date s_x_time;     //申请续费时间（2018/10/25添加：ning）
	private Date s_t_time;     //申请退费时间（2018/10/25添加：ning）
	private Date j_j_time;     //拒绝缴费时间（2018/10/25添加：ning）
	private Date j_x_time;     //拒绝续费时间（2018/10/25添加：ning）
	private Date j_t_time;     //拒绝退费时间（2018/10/25添加：ning）
	private Date h_j_time;     //审核缴费时间（2018/10/25添加：ning）
	private Date h_x_time;     //审核续费时间（2018/10/25添加：ning）
	private Date h_t_time;     //审核退费时间（2018/10/25添加：ning）
	
	private Integer jPay;      //缴费支付方式
	private Integer xPay;      //续费支付方式
	private Integer tPay;      //退费支付方式
	
	private String  results;   //报名须知

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Classinfo getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(Classinfo classinfo) {
		this.classinfo = classinfo;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public Date getS_j_time() {
		return s_j_time;
	}

	public void setS_j_time(Date s_j_time) {
		this.s_j_time = s_j_time;
	}

	public Date getS_x_time() {
		return s_x_time;
	}

	public void setS_x_time(Date s_x_time) {
		this.s_x_time = s_x_time;
	}

	public Date getS_t_time() {
		return s_t_time;
	}

	public void setS_t_time(Date s_t_time) {
		this.s_t_time = s_t_time;
	}

	public Date getJ_j_time() {
		return j_j_time;
	}

	public void setJ_j_time(Date j_j_time) {
		this.j_j_time = j_j_time;
	}

	public Date getJ_x_time() {
		return j_x_time;
	}

	public void setJ_x_time(Date j_x_time) {
		this.j_x_time = j_x_time;
	}

	public Date getJ_t_time() {
		return j_t_time;
	}

	public void setJ_t_time(Date j_t_time) {
		this.j_t_time = j_t_time;
	}

	public Date getH_j_time() {
		return h_j_time;
	}

	public void setH_j_time(Date h_j_time) {
		this.h_j_time = h_j_time;
	}

	public Date getH_x_time() {
		return h_x_time;
	}

	public void setH_x_time(Date h_x_time) {
		this.h_x_time = h_x_time;
	}

	public Date getH_t_time() {
		return h_t_time;
	}

	public void setH_t_time(Date h_t_time) {
		this.h_t_time = h_t_time;
	}

	public Integer getjPay() {
		return jPay;
	}

	public void setjPay(Integer jPay) {
		this.jPay = jPay;
	}

	public Integer getxPay() {
		return xPay;
	}

	public void setxPay(Integer xPay) {
		this.xPay = xPay;
	}

	public Integer gettPay() {
		return tPay;
	}

	public void settPay(Integer tPay) {
		this.tPay = tPay;
	}

	
}
