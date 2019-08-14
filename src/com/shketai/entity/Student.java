package com.shketai.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;
/**
 * 学生
 * @author Administrator
 *
 */
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;              //ID
	
	//多对一的关系
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private int IdNum;           //学号
	
	private String stuName;		 //学生姓名
	
	private int sex;			 //性别:1男2女
	
	private Date birthday;       //生日
	
	private String personalNum;  //身份证号
	
	private String schoolname;
	
	private String address;      //家庭住址
	
	private String postcode;     //邮编
	
	private String parent; //紧急联系人姓名
	
	private String mobilephone;//紧急联系人电话
	
	private String emergencyContactName; //紧急联系人姓名
	
	private String emergencyContactPhone;//紧急联系人电话
	
	private String workaddress;  //工作单位
	

	@Transient
	private String new_stuName ; //	new_姓名

	@Transient
	private String new_personalNum ; //	new_身份证

	@Transient
	private String new_sex ; //	new_性别
	
	@Transient
	private Integer flag; //	1=关联成功  0=关联失败  -1=不存在
	
	@Transient
	private String flag_info; //	返回信息
	
	@Transient
	private String classname;	//班级名称
	
	@Transient
	private String new_classname;	//new_班级名称
	
	/**
	 *  set Value
	 * @param new_stuName
	 * @param new_personalNum
	 * @param new_sex
	 * @param flag
	 * @param flag_info
	 */
	public void setValueStudent(String new_stuName, String new_personalNum, String new_sex, Integer flag, String flag_info) {
		this.new_stuName = new_stuName;
		this.new_personalNum = new_personalNum;
		this.new_sex = new_sex;
		this.flag = flag;
		this.flag_info = flag_info;
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

	public int getIdNum() {
		return IdNum;
	}

	public void setIdNum(int idNum) {
		IdNum = idNum;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPersonalNum() {
		return personalNum;
	}

	public void setPersonalNum(String personalNum) {
		this.personalNum = personalNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}

	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}

	public String getWorkaddress() {
		return workaddress;
	}

	public void setWorkaddress(String workaddress) {
		this.workaddress = workaddress;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public Student(int id, User user, int idNum, String stuName, int sex,
			Date birthday, String personalNum, String schoolname,
			String address, String postcode, String parent, String mobilephone,
			String emergencyContactName, String emergencyContactPhone,
			String workaddress) {
		super();
		this.id = id;
		this.user = user;
		IdNum = idNum;
		this.stuName = stuName;
		this.sex = sex;
		this.birthday = birthday;
		this.personalNum = personalNum;
		this.schoolname = schoolname;
		this.address = address;
		this.postcode = postcode;
		this.parent = parent;
		this.mobilephone = mobilephone;
		this.emergencyContactName = emergencyContactName;
		this.emergencyContactPhone = emergencyContactPhone;
		this.workaddress = workaddress;
	}

	public Student(){
		
	}

	public String getNew_stuName() {
		return new_stuName;
	}

	public void setNew_stuName(String new_stuName) {
		this.new_stuName = new_stuName;
	}

	public String getNew_personalNum() {
		return new_personalNum;
	}

	public void setNew_personalNum(String new_personalNum) {
		this.new_personalNum = new_personalNum;
	}

	public String getNew_sex() {
		return new_sex;
	}

	public void setNew_sex(String new_sex) {
		this.new_sex = new_sex;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getFlag_info() {
		return flag_info;
	}

	public void setFlag_info(String flag_info) {
		this.flag_info = flag_info;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getNew_classname() {
		return new_classname;
	}

	public void setNew_classname(String new_classname) {
		this.new_classname = new_classname;
	}

	
}
