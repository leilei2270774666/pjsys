package com.shketai.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Attence;
import com.shketai.entity.Info;
import com.shketai.entity.Student;
import com.shketai.entity.User;

public class StudentDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	/**
	 * 教师端 查看所有学生信息
	 */
	public List<Student> findAll(){
		String hql = "from Student";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Student> list = query.list();
		return list;
	}
	
	public int checkPersonalNum(String personalNum){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Student where personalNum=?");
		query.setString(0, personalNum);
		List<Student> list = query.list();
		if(list.size() > 0){
			return 0;
		}else{
			return 1;
		}
	}
	/**
	 * 新增学生信息
	 * @param student
	 * @return
	 */
	public int add(Student student,int user_id){
	
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		user.setId(user_id);
		student.setUser(user);
		session.save(student);
		
		return 1;
	}
	public Student findByWorkId(int id) {
		Session session = sessionFactory.getCurrentSession();
		Student student = (Student) session.get(Student.class, id);
		return student;
	}

	/**
	 * 修改学生信息
	 * @param student
	 */
	public void update(Student student,int user_id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Student where id=:id");
		query.setInteger("id", student.getId());
		List<Student> list = query.list();
		Student oldUser = list.get(0);
		student.setId(oldUser.getId());
		User user = new User();
		user.setId(user_id);
		student.setUser(user);
		session.clear();
		session.update(student);
	}
	/**
	 * 修改学生备注
	 * @param student
	 * @param id
	 * @return
	 */
	public void updateFlag(int id,String flag){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Info set flag=:flag where id=:id");
		//引号id
		query.setInteger("id",id);
		query.setString("flag", flag);
		query.executeUpdate();
	}
	
	public int delete(Student student){
//		delete from  attence where stu_id=1800
//		delete from  info where stu_id=1800
//		delete from  interview_info where stu_id=1800
//		delete from  invoice where stu_id=1800
//		delete from  student where id=1800
		Session session = sessionFactory.getCurrentSession();
		session.delete(student);
		return 1;
	}
	/**
	 * 查看个人信息
	 * @param id
	 * @return
	 */
	public List<Student> findById(int user_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Student where user_id=?");
		query.setInteger(0, user_id);
		List<Student> list = query.list();
		if(list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	
	public List<Student> findById1(int id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Student where id=?");
		query.setInteger(0, id);
		List<Student> list = query.list();
		if(list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	
	
	
	/**
	 * 教师端 查看所有学生信息
	 * @param id
	 * @return
	 */
	public List<Student> find(int page){
		String hql = "from Student";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult((page-1)*10);
		query.setMaxResults(10);
		List<Student> list = query.list();
		return list;
	}
	public int findCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Student");
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}
	
	public List<Student> finddesc(int page){
		String hql = "from Student order by id desc";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult((page-1)*10);
		query.setMaxResults(10);
		List<Student> list = query.list();
		return list;
	}
	/**
	 * 根据姓名查询学生信息
	 * @param username
	 * @return
	 */
	public List<Student> queryByUsername(String stuName){
		Session session = sessionFactory.getCurrentSession();
		List<Student> list = session.createQuery("from Student where stuName like ? ").setString(0,"%"+stuName+"%").list();
		return list;
	}
	
	/**
	 * 查询指定 身份证号 的 学生
	 * 
	 * 返回数量
	 */
	public int queryPersonalNum(String personalNum){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*)  from student where personalNum = :personalNum ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setString("personalNum", personalNum);
		
		BigInteger count = (BigInteger) sqlQuery.uniqueResult();
		
		return  count.intValue();
	}
	
	
	/**
	 *  查询指定 身份证号 的 学生
	 *   返回 学生对象
	 */
	public Student queryPersonalNumReturnObj(String personalNum){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select id , stuName , sex , personalNum  from student where personalNum = :personalNum ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setString("personalNum", personalNum);
		
		List<Object[]> list = sqlQuery.list();
		
		Student student = new Student();
		
		if(list.size()>0) {
			
			Object[] obj =  list.get(0);
			
			int id = (int) obj[0];
			String stuName = (String) obj[1];
			int sex = (int) obj[2];
//			String personalNum = (String) obj[3];
			
			student.setId(id);
			student.setStuName(stuName);
			student.setSex(sex);
			student.setPersonalNum(personalNum);
		}
		
		
		return  student;
	}
	
	/**
	 *  查询指定姓名 的 学生 并且和 班级没有关联
	 *   返回 学生集合对象
	 */
	public List<Student> queryStuName(String name){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select id , stuName , sex , personalNum  from student s ");
		sql.append(" where s.stuName like :stuName ");
		sql.append(" and  s.id not in ( select i.stu_id from info i ) ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setString("stuName", "%"+name+"%");
		
		List<Object[]> list = sqlQuery.list();
		
		List<Student> studentList = new ArrayList<Student>();
		
		for (int i = 0; i < list.size(); i++) {
			
			Object[] obj =  list.get(i);
			
//			get
			int id = (int) obj[0];
			String stuName = (String) obj[1];
			int sex = (int) obj[2];
			String personalNum = (String) obj[3];
			
//			new Student
			Student student = new Student();
			
//			set Value
			student.setId(id);
			student.setStuName(stuName);
			student.setSex(sex);
			student.setPersonalNum(personalNum);
			
//			list add
			studentList.add(student);
		}
		
		
		return  studentList;
	}
	
	
	/**
	 *  查询指定 身份证号 的 学生
	 *   返回 学生对象
	 */
	public Student queryId(int id){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select id , stuName , sex , personalNum  from student where id = :id ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setInteger("id", id);
		
		List<Object[]> list = sqlQuery.list();
		
		Student student = new Student();
		
		if(list.size()>0) {
			
			Object[] obj =  list.get(0);
			
			int ids = (int) obj[0];
			String stuName = (String) obj[1];
			int sex = (int) obj[2];
			String personalNum = (String) obj[3];
			
			student.setId(id);
			student.setStuName(stuName);
			student.setSex(sex);
			student.setPersonalNum(personalNum);
		}
		
		
		return  student;
	}
	
}
