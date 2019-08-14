package com.shketai.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;
/**
 * 班级
 * @author Administrator
 *
 */
@Entity
public class Classinfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String classname;    //班级名
	
	private double unit_price;   //每节课时费
	
	private int keshi;           //总课时
	
	private double money;        //总费用
	
	private Date min_age;         //最晚出生日期
	
	private Date max_age;       //最早出生日期
	
	private int kaifang;        //是否开放  1：开放  0：不开放
	
	@Transient
    private int count;	//已配置人数
	
	@Transient
	private int infoFlag; //已选1 未选0
	
	private int num;	//剩余人数
	
	private int number;	//额定人数
	
	private Integer headmaster_id;	//班主任id
	
//	实勤人数
	@Transient
	private Integer onPerson;
	
//	缺勤人数
	@Transient
	private Integer unPerson;
	
//	未到人数
	@Transient
	private Integer notPerson;
	
	
	//多对一的关系
	@ManyToOne
	@JoinColumn(name="subject_id")
	private Subject subject;
	
	@OneToOne//一对一关系
	@JoinColumn(name="classroominfo_id")
	private Classroominfo classroominfo;
	
	@OneToOne  //一对一关系
	@JoinColumn(name="id")
	private ClassRoom classroom;
	
	
	
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



	

	@JSON(format="yyyy-MM-dd")
	public Date getMin_age() {
		return min_age;
	}



	public void setMin_age(Date min_age) {
		this.min_age = min_age;
	}


	@JSON(format="yyyy-MM-dd")
	public Date getMax_age() {
		return max_age;
	}



	public void setMax_age(Date max_age) {
		this.max_age = max_age;
	}



	public Subject getSubject() {
		return subject;
	}



	public void setSubject(Subject subject) {
		this.subject = subject;
	}




	public Classroominfo getClassroominfo() {
		return classroominfo;
	}



	public void setClassroominfo(Classroominfo classroominfo) {
		this.classroominfo = classroominfo;
	}



	public Classinfo() {
	}

	public int getNum() {
		return num;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public int getKaifang() {
		return kaifang;
	}



	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
	}



	public void setKaifang(int kaifang) {
		this.kaifang = kaifang;
	}



	public int getInfoFlag() {
		return infoFlag;
	}



	public void setInfoFlag(int infoFlag) {
		this.infoFlag = infoFlag;
	}



	public Integer getHeadmaster_id() {
		return headmaster_id;
	}



	public void setHeadmaster_id(Integer headmaster_id) {
		this.headmaster_id = headmaster_id;
	}



	public Integer getOnPerson() {
		return onPerson;
	}



	public void setOnPerson(Integer onPerson) {
		this.onPerson = onPerson;
	}



	public Integer getUnPerson() {
		return unPerson;
	}



	public void setUnPerson(Integer unPerson) {
		this.unPerson = unPerson;
	}



	public Integer getNotPerson() {
		return notPerson;
	}



	public void setNotPerson(Integer notPerson) {
		this.notPerson = notPerson;
	}



	public ClassRoom getClassroom() {
		return classroom;
	}



	public void setClassroom(ClassRoom classroom) {
		this.classroom = classroom;
	}

}
