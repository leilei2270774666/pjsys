package com.shketai.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Classinfo;
import com.shketai.entity.Info;
import com.shketai.entity.Student;

import com.shketai.util.DateStrUtil;

public class InfoDao {

	private SessionFactory sessionFactory;
	
	private ClassinfoDao classinfoDao;
	
	private StudentDao studentDao;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public void setClassinfoDao(ClassinfoDao classinfoDao) {
		this.classinfoDao = classinfoDao;
	}


	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}


	/**
	 * 修改学生备注
	 * @param student
	 * @param id
	 * @return
	 */
	public void updateFlag(int id,String flags){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Info set flags=:flags where id=:id");
		//引号id
		query.setInteger("id",id);
		query.setString("flags", flags);
		System.out.println(id);
		System.out.println(flags);
		query.executeUpdate();
	}
	/**
	 * 批量更新缴费状态
	 * @param stu_id 需要更新的学生的id集合
	 * @param flag 缴费状态
	 */
	public void update(List<Integer> ids,int flag,Integer pay){
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql=new StringBuffer("update Info set flag=:flag ");
		if(flag==2) {
			//申请缴费时间
			sql.append(",s_j_time=now()");
		}
		if(flag==8) {
			//申请续费时间
			sql.append(",s_x_time=now()");
		}
		if(flag==5) {
			//申请退费时间
			sql.append(",s_t_time=now()");
		}
		if(flag==10) {
			//拒绝缴费时间
			sql.append(",j_j_time=now()");
		}
		if(flag==11) {
			//拒绝续费时间
			sql.append(",j_x_time=now()");
		}
		if(flag==12) {
			//拒绝退费时间
			sql.append(",j_t_time=now()");
		}
		if(flag==3) {
			//审核缴费时间
			sql.append(",h_j_time=now(),jPay="+pay);
		}
		if(flag==9) {
			//审核续费时间
			sql.append(",h_x_time=now(),xPay="+pay);
		}
		if(flag==6) {
			//审核退费时间
			sql.append(",h_t_time=now(),tPay="+pay);
		}
		sql.append("where id in (:ids)");
		Query query = session.createQuery(sql.toString());
		//根据名字设置参数
		query.setInteger("flag", flag);
		query.setParameterList("ids", ids);
		query.executeUpdate();
		
	}
	
	/**
	 * 批量删除 id
	 */
	public void deleteListId(List<Integer> ids){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" DELETE from  Info   where id in (:ids)");
		//根据名字设置参数
		query.setParameterList("ids", ids);
		query.executeUpdate();
		
	}
	
	/**
	 * 退班
	 * @param id
	 */
	public void quitSubject(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Info set flag = 4 where id =:id");
		query.setInteger("id",id);
		query.executeUpdate();
	}
	
	/**
	 * 向班级里添加学生
	 * @param classinfo_id
	 * @param stu_id
	 */
	public void addInfo(int stu_id,int classinfo_id){
		
//		判断该班级是否已经有 该 学生 , 如果班级下有该学生 不进行添加
		if( querystu_idInclassinfo_id(stu_id, classinfo_id) >0 ) {
			return;
		}
		
		Session session = sessionFactory.getCurrentSession();
		Info info = new Info();
		Classinfo ci = (Classinfo) session.get(Classinfo.class, classinfo_id);
		if(ci.getNum()>0){
		// 该班级可选人数减1
		ci.setNum(ci.getNum() - 1);
		info.setClassinfo(ci);
		Student stu = (Student) session.get(Student.class, stu_id);
		stu.setId(stu_id);
		info.setStudent(stu);
		info.setFlag(3);
		//保存信息
		session.save(info);
			
		}
	}
	public int getNum(int classinfo_id){
		int result;
		Session session = sessionFactory.getCurrentSession();
		Classinfo ci = (Classinfo) session.get(Classinfo.class, classinfo_id);
		if(ci.getNum()<=0){
			 return result = 0;
		}else{
			return result = 1;
		}
	}
	public int checkstuid(int stu_id,int classinfo_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Info where stu_id=? and classinfo_id=?");
		query.setInteger(0, stu_id);
		query.setInteger(1, classinfo_id);
		List<Info> list = query.list();
		if(list.size() > 0){
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * 检测时间段是否有冲突
	 * 
	 * @param stu_id
	 * @param classinfo_id
	 * @return true有冲突 false 无冲突
	 */
	public boolean checkPeriod(int stu_id, int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select classinfo.classroominfo.day,classinfo.classroominfo.start,classinfo.classroominfo.jieshu from"
						+ " Info where student.id=:sid");
		query.setInteger("sid", stu_id);
		List<Object[]> list = query.list();
		Query query1 = session.createQuery(
				"select classroominfo.day,classroominfo.start,classroominfo.jieshu from Classinfo where id=:id");
		query1.setInteger("id", classinfo_id);
		List<Object[]> list1 = query1.list();
		Object[] period = list1.get(0);
		boolean flag = true;
		for (Object[] array : list) {
			if (period[0].equals(array[0])) {
				if (DateStrUtil.compare(period[1].toString(), array[1].toString()) == 0) {
					flag = true;
					break;
				} else if (DateStrUtil.compare(period[1].toString(), array[1].toString()) < 0) {
					if (DateStrUtil.compare(period[2].toString(), array[1].toString()) > 0) {
						flag = true;
						break;
					}
				} else {
					if (DateStrUtil.compare(period[1].toString(), array[2].toString()) < 0) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;

	}
	public Info findByStuId(List<Integer> stu_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Student where id in(:ids)");
		query.setParameterList("ids", stu_id);
		List<Info> list = query.list();
		//存放所有学生的id
		List<Integer> ids = new ArrayList<Integer>();
		for (Info info :list) {
			ids.add(info.getStudent().getId());
		}
		return list.get(0);
	}

	/**
	 * 我的课程
	 * @param user_id
	 * @return
	 */
	public List<Info> find(int user_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Student where user.id = ?");
		query.setLong(0, user_id);
		List<Student> list = query.list();
		//存放所有学生的id
		List<Integer> ids = new ArrayList<Integer>();
		for (Student student :list) {
			ids.add(student.getId());
		}
		//这一条hql语句相当于  select * from info where stu_id in(1,2,90)，就是查询这些学生的选课信息
		Query query2 = session.createQuery("from Info where student.id in(:ids) ");
		query2.setParameterList("ids",ids);
		List<Info> list1 = query2.list();
		return list1;
	}
	
	public List<Info> findById(int stu_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Info where stu_id=?");
		query.setInteger(0, stu_id);
		List<Info> list = query.list();
		return list;
	}
	
	/**
	 * 删除选课
	 * @param id
	 * @return
	 */
	public int deleteById(int id){
		Session session = sessionFactory.getCurrentSession();
		Info info = new Info();
		info.setId(id);
		System.out.println(id);
		session.delete(info);
		return 1;
	}
	

	/**
	 * 显示出已选这门课的学生
	 * @param classinfo_id sdsd
	 * @return
	 */
	public List<Info> findByClassinfoId(int classinfo_id,int page , int flag){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select i.id,i.flag,i.flags,c.id as cid,c.classname,"
				+ "s.id as sid,s.IdNum,s.stuName,s.sex,s.personalNum from Info i "
				+ "inner join classinfo c on(c.id=i.classinfo_id) "
				+ "inner join student s on(s.id=i.stu_id)"
				+ "where i.classinfo_id=:classinfo_id  and i.flag != :flag order by i.id");
		query.setInteger("classinfo_id", classinfo_id);
		query.setInteger("flag", flag);
		//order by convert(s.stuName using gbk)
		query.setFirstResult((page-1)*10);
		query.setMaxResults(10);
		List<Object[]> ls=query.list();
		List<Info> list =new ArrayList<>(); 
		for (Object[] obj : ls) {
			//财务信息
			Info info = new Info();
			info.setId(Integer.parseInt(obj[0].toString()));
			info.setFlag(Integer.parseInt(obj[1].toString()));
			if(obj[2]!=null&&obj[2]!="") {
				info.setFlags(obj[2].toString());
			}
			
			//班级信息
			Classinfo classinfo = new Classinfo();
			classinfo.setId(Integer.parseInt(obj[3].toString()));
			classinfo.setClassname(obj[4].toString());
			info.setClassinfo(classinfo);
			
			//学生信息
			Student student=new Student();
			student.setId(Integer.parseInt(obj[5].toString()));
			if(obj[6]!=null&&obj[6]!="") {
				student.setIdNum(Integer.parseInt(obj[6].toString()));
			}
			student.setStuName(obj[7].toString());
			student.setSex(Integer.parseInt(obj[8].toString()));
			student.setPersonalNum(obj[9].toString());
			info.setStudent(student);
			
			list.add(info);
		}
		if(list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	
	public List<Info> findByClassinfoIdDesc(int classinfo_id,int page , int flag){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select i.id,i.flag,i.flags,c.id as cid,c.classname,"
				+ "s.id as sid,s.IdNum,s.stuName,s.sex,s.personalNum from Info i "
				+ "inner join classinfo c on(c.id=i.classinfo_id) "
				+ "inner join student s on(s.id=i.stu_id)"
				+ "where i.classinfo_id=:classinfo_id  and i.flag != :flag order by s.id desc");
		query.setInteger("classinfo_id", classinfo_id);
		query.setInteger("flag", flag);
		
		query.setFirstResult((page-1)*10);
		query.setMaxResults(10);
		List<Object[]> ls=query.list();
		List<Info> list =new ArrayList<>(); 
		for (Object[] obj : ls) {
			//财务信息
			Info info = new Info();
			info.setId(Integer.parseInt(obj[0].toString()));
			info.setFlag(Integer.parseInt(obj[1].toString()));
			if(obj[2]!=null&&obj[2]!="") {
				info.setFlags(obj[2].toString());
			}
			
			//班级信息
			Classinfo classinfo = new Classinfo();
			classinfo.setId(Integer.parseInt(obj[3].toString()));
			classinfo.setClassname(obj[4].toString());
			info.setClassinfo(classinfo);
			
			//学生信息
			Student student=new Student();
			student.setId(Integer.parseInt(obj[5].toString()));
			if(obj[6]!=null&&obj[6]!="") {
				student.setIdNum(Integer.parseInt(obj[6].toString()));
			}
			student.setStuName(obj[7].toString());
			student.setSex(Integer.parseInt(obj[8].toString()));
			student.setPersonalNum(obj[9].toString());
			info.setStudent(student);
			
			list.add(info);
		}
		if(list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	public List<Info> findByClassinfoId1(int classinfo_id,int page){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(
				"SELECT t5.id,t5.stuName,t5.sex,t5.personalNum,t6.classname ,t4.classinfo_id FROM info t4 INNER JOIN student t5 on t4.stu_id = t5.id INNER JOIN classinfo t6 on t4.classinfo_id = t6.id WHERE t4.stu_id NOT IN ("
		+"SELECT t2.id FROM info t1 INNER JOIN student t2 ON t1.stu_id = t2.id "
		+"INNER JOIN attence t3 ON t3.stu_id = t2.id WHERE t1.classinfo_id = ?) and t4.classinfo_id = ?");
		query.setInteger(0, classinfo_id);
		query.setInteger(1, classinfo_id);
		query.setFirstResult((page-1)*10);
		query.setMaxResults(10);
		
		List<Object[]> list = query.list();
		List<Info> result = new ArrayList<Info>();
		for (Object[] array : list) {
			Info info = new Info();
			Student student = new Student();
			student.setId(Integer.parseInt(array[0].toString()));
			student.setStuName(array[1].toString());
			student.setSex(Integer.parseInt(array[2].toString()));
			student.setPersonalNum(array[3].toString());
			Classinfo classinfo = new Classinfo();
			classinfo.setClassname(array[4].toString());
			classinfo.setId(Integer.parseInt(array[5].toString()));
			info.setStudent(student);
			info.setClassinfo(classinfo);
			result.add(info);
		}
		return result;
	}
	
	public List<Info> findByClassinfoIds(int classinfo_id,int page){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Info where classinfo_id=?");
		query.setInteger(0, classinfo_id);
		query.setFirstResult((page-1)*10);
		query.setMaxResults(10);
		Classinfo classinfo = new Classinfo();
		classinfo.setId(classinfo_id);
		Info info = new Info();
		info.setId(classinfo_id);
		List<Info> list = query.list();
		if(list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	
	public List<Info> findAll(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Info where classinfo_id=?");
		query.setInteger(0, classinfo_id);
		Classinfo classinfo = new Classinfo();
		classinfo.setId(classinfo_id);
		Info info = new Info();
		info.setId(classinfo_id);
		List<Info> list = query.list();
		if(list.size() > 0){
			return list;
		}
		
		return list;
	}
	
	/**
	 * 查出该班级下的学生
	 * @param classinfo_id  sdsd
	 * @return
	 */
	public List<Object[]> findAllByclassId(Integer classinfo_id){  
		Session session = sessionFactory.getCurrentSession();
		StringBuffer hql=new StringBuffer("select t.`subject` ,c.classname ,s.stuName,s.sex,s.personalNum,s.birthday,s.address,s.postcode,s.parent,s.mobilephone,s.emergencyContactName,s.emergencyContactPhone,s.workaddress,s.schoolname,i.flag,c.id from student s, Info i ,`subject` t ,classinfo c where s.id=i.stu_id AND c.id=i.classinfo_id AND t.id=c.subject_id");
		if(classinfo_id!=null) {
			hql.append(" AND classinfo_id=?");
		}
		hql.append(" order by i.id");
		Query query = session.createSQLQuery(hql.toString());
		if(classinfo_id!=null) {
			query.setInteger(0, classinfo_id);
		}
		List<Object[]> list = query.list();
		if(list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	
	public List<Object[]> findAllBySubject(){
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.`subject` ,c.classname ,s.stuName,s.sex,s.personalNum,s.birthday,s.address,s.postcode,s.parent,s.mobilephone,s.emergencyContactName,s.emergencyContactPhone,s.workaddress,s.schoolname , c.id from student s, Info i ,`subject` t ,classinfo c where s.id=i.stu_id AND c.id=i.classinfo_id AND t.id=c.subject_id ORDER BY c.id ");
		
		Query query = session.createSQLQuery(sql.toString());
		List<Object[]> list = query.list();
		if(list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	public int findCount(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Info where classinfo_id=?");
		query.setInteger(0, classinfo_id);
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}
 
	
	public int findCount1(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT count(*) FROM info t4  WHERE t4.stu_id NOT IN ("
		+"SELECT t2.id FROM info t1 "
		+"INNER JOIN student t2 ON t1.stu_id = t2.id "
		+"INNER JOIN attence t3 ON t3.stu_id = t2.id WHERE t1.classinfo_id = ?)and t4.classinfo_id = ?");
		query.setInteger(0, classinfo_id);
		query.setInteger(1, classinfo_id);
		BigInteger total = (BigInteger) query.uniqueResult();
		return total.intValue();
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
	 * 缴费时候查询学生选课信息
	 * @param user_id
	 * @param classinfo_id
	 * @return
	 */
	public List<Info> findAllInfo(int user_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Student where user.id = ?");
		query.setLong(0, user_id);
		List<Student> list = query.list();
		//存放所有学生的id
		List<Integer> ids = new ArrayList<Integer>();
		for (Student student :list) {
			ids.add(student.getId());
		}
		//这一条hql语句相当于  select * from info where stu_id in(1,2,90)，就是查询这些学生的选课信息
		Query query2 = session.createQuery("from Info where student.id in(:ids) and flag=1");
		query2.setParameterList("ids",ids);
		List<Info> infos = query2.list();
		return infos;
	}
	
	/**
	 * 退费时候查询学生选课信息
	 * @param user_id
	 * @param classinfo_id
	 * @return
	 */
	public List<Info> findAllInfo1(int user_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Student where user.id = ?");
		query.setLong(0, user_id);
		List<Student> list = query.list();
		//存放所有学生的id
		List<Integer> ids = new ArrayList<Integer>();
		for (Student student :list) {
			ids.add(student.getId());
		}
		//这一条hql语句相当于  select * from info where stu_id in(1,2,90)，就是查询这些学生的选课信息
		Query query2 = session.createQuery("from Info where student.id in(:ids) and flag=3");
		query2.setParameterList("ids",ids);
		List<Info> infos = query2.list();
		return infos;
	}
	
	/**
	 * 续费时候查询学生选课信息
	 * @param user_id
	 * @param classinfo_id
	 * @return
	 */
	public List<Info> findAllInfo2(int user_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Student where user.id = ?");
		query.setLong(0, user_id);
		List<Student> list = query.list();
		//存放所有学生的id
		List<Integer> ids = new ArrayList<Integer>();
		for (Student student :list) {
			ids.add(student.getId());
		}
		//这一条hql语句相当于  select * from info where stu_id in(1,2,90)，就是查询这些学生的选课信息
		Query query2 = session.createQuery("from Info where student.id in(:ids) and flag=7");
		query2.setParameterList("ids",ids);
		List<Info> infos = query2.list();
		return infos;
	}
	/**
	 * 缴费审核查询学生选课信息
	 * @param user_id
	 * @param classinfo_id
	 * @return
	 */
	public List<Info> findAllInfo4(int page){
		Session session = sessionFactory.getCurrentSession();
		/*Query query = session.createQuery("from Student where id = ?");
		query.setLong(0, id);
		List<Student> list = query.list();
		//存放所有学生的id
		List<Integer> ids = new ArrayList<Integer>();
		for (Student student :list) {
			ids.add(student.getId());
		}*/
		//这一条hql语句相当于  select * from info where stu_id in(1,2,90)，就是查询这些学生的选课信息
		Query query2 = session.createQuery("from Info where flag=2");
		query2.setFirstResult((page-1)*10);
		query2.setMaxResults(10);
		//query2.setParameterList("ids",ids);
		List<Info> infos = query2.list();
		return infos;
	}
	
	public List<Info> queryByUsername1(String stuName){
		Session session = sessionFactory.getCurrentSession();
		List<Info> list = session.createQuery("from Info where flag = 2 and student.stuName like ? ").setString(0,"%"+stuName+"%").list();
		return list;
	}
	
	public List<Info> queryByUsername2(String stuName){
		Session session = sessionFactory.getCurrentSession();
		List<Info> list = session.createQuery("from Info where flag = 8 and student.stuName like ? ").setString(0,"%"+stuName+"%").list();
		return list;
	}
	
	public List<Info> queryByUsername3(String stuName){
		Session session = sessionFactory.getCurrentSession();
		List<Info> list = session.createQuery("from Info where flag = 5 and student.stuName like ? ").setString(0,"%"+stuName+"%").list();
		return list;
	}
	/**
	 * 续费审核查询学生选课信息
	 * @param user_id
	 * @param classinfo_id
	 * @return
	 */
	public List<Info> findAllInfo5(int page){
		Session session = sessionFactory.getCurrentSession();
		/*Query query = session.createQuery("from Student where id = ?");
		query.setLong(0, id);
		List<Student> list = query.list();
		//存放所有学生的id
		List<Integer> ids = new ArrayList<Integer>();
		for (Student student :list) {
			ids.add(student.getId());
		}*/
		//这一条hql语句相当于  select * from info where stu_id in(1,2,90)，就是查询这些学生的选课信息
		Query query2 = session.createQuery("from Info where flag=8");
		query2.setFirstResult((page-1)*10);
		query2.setMaxResults(10);
		List<Info> infos = query2.list();
		return infos;
	}
	
	public List<Info> findAllInfo6(int page){
		Session session = sessionFactory.getCurrentSession();
		/*Query query = session.createQuery("from Student where id = ?");
		query.setLong(0, id);
		List<Student> list = query.list();
		//存放所有学生的id
		List<Integer> ids = new ArrayList<Integer>();
		for (Student student :list) {
			ids.add(student.getId());
		}*/
		//这一条hql语句相当于  select * from info where stu_id in(1,2,90)，就是查询这些学生的选课信息
		Query query2 = session.createQuery("from Info where flag=5");
		query2.setFirstResult((page-1)*10);
		query2.setMaxResults(10);
		List<Info> infos = query2.list();
		return infos;
	}
	/**
	 * 退班时查询学生所选班级信息
	 * @param user_id
	 * @return
	 */
	public List<Info> findAllInfo3(int user_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Student where user.id = ?");
		query.setLong(0, user_id);
		List<Student> list = query.list();
		//存放所有学生的id
		List<Integer> ids = new ArrayList<Integer>();
		for (Student student :list) {
			ids.add(student.getId());
		}
		//这一条hql语句相当于  select * from info where stu_id in(1,2,90)，就是查询这些学生的选课信息
		Query query2 = session.createQuery("from Info where student.id in(:ids) and (flag=1 or flag=3 or flag=2)");
		query2.setParameterList("ids",ids);
		List<Info> infos = query2.list();
		return infos;
	}
	public int findCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Info where flag = 2");
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}
	
	public int findCount1() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Info where flag = 1");
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}
	
	public int findCountFlag(int flag) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" select count(*) from Info where flag = :flag ");
		query.setInteger("flag", flag);
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}
	
	public int findCount2() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Info where flag = 5");
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}
	
	/**
	 * @param info
	 * 修改info对象
	 */
	public int updateInfo(Info info) {
		Session session = sessionFactory.getCurrentSession();
		session.update(info);
		return 1;
	}
	
	
	/**
	 * 删除指定 classinfo_id 
	 * @return
	 */
	public int deleteClassinfo_id(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from info where  classinfo_id = :classinfo_id ");
		
		
		SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
		
		sqlQuery.setInteger("classinfo_id", classinfo_id);
		
		sqlQuery.executeUpdate();
		
		return 1;
	}
	
	/**
	 * 查询指定 学生id 和 班级 id
	 * 
	 * 返回数量
	 */
	public int querystu_idInclassinfo_id(int stu_id , int classinfo_id){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*)  from info where stu_id = :stu_id  and classinfo_id = :classinfo_id ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setInteger("stu_id", stu_id);
		sqlQuery.setInteger("classinfo_id", classinfo_id);
		
		BigInteger count = (BigInteger) sqlQuery.uniqueResult();
		
		return  count.intValue();
	}
	
	/**
	 * 查询指定 学生id 和 班级 id 和  不等flag集合
	 * 
	 * 返回数量
	 */
	public int querystu_idInclassinfo_idInflag(int stu_id , int classinfo_id , List<Integer> flag ){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*)  from info where stu_id = :stu_id  and classinfo_id = :classinfo_id  and  flag not in (:flag)");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setInteger("stu_id", stu_id);
		sqlQuery.setInteger("classinfo_id", classinfo_id);
		sqlQuery.setParameterList("flag", flag);
		
		BigInteger count = (BigInteger) sqlQuery.uniqueResult();
		
		return  count.intValue();
	}
	
	/**
	 *  传递制定 学生id 和 班级id  更新指定的 flag
	 */
	public int updatestu_idInclassinfo_idInflag(int stu_id , int classinfo_id , int flag ){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update info set flag = :flag  where stu_id = :stu_id  and classinfo_id = :classinfo_id ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setInteger("stu_id", stu_id);
		sqlQuery.setInteger("classinfo_id", classinfo_id);
		sqlQuery.setInteger("flag", flag);
		
		sqlQuery.executeUpdate();
		
		return  1;
	}
	
	/**
	 *  sql  添加info
	 * @param flag
	 * @param classinfo_id
	 * @param stu_id
	 * @return
	 */
	/*public int sqlAddInfo(int flag , int classinfo_id , int stu_id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into info (flag , classinfo_id , stu_id) values (:flag , :classinfo_id , :stu_id) ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setInteger("stu_id", stu_id);
		sqlQuery.setInteger("classinfo_id", classinfo_id);
		sqlQuery.setInteger("flag", flag);
		
		sqlQuery.executeUpdate();
		
		return  1;
	}*/
	
	/**
	 * 向班级里添加学生 同时判断是否已经添加
	 * @param classinfo_id
	 * @param stu_id
	 */
	public void my_addInfo(int stu_id,int classinfo_id,int flag){
		
//		判断该班级是否已经有 该 学生 , 如果班级下有该学生 不进行添加
		if( querystu_idInclassinfo_id(stu_id, classinfo_id) >0 ) {
			return;
		}
		
		Session session = sessionFactory.getCurrentSession();
		Info info = new Info();
		Classinfo ci = (Classinfo) session.get(Classinfo.class, classinfo_id);
		if(ci.getNum()>0){
		// 该班级可选人数减1
		ci.setNum(ci.getNum() - 1);
		info.setClassinfo(ci);
		Student stu = (Student) session.get(Student.class, stu_id);
		stu.setId(stu_id);
		info.setStudent(stu);
		info.setFlag(flag);
		//保存信息
		session.save(info);
			
		}
	}
	
	
	/**
	 *  查询近 7天的 info 和 未考勤  sdsd
	 * @return
	 */
	public List<Info> queryNearlyDayAndNoneAllAttence(int classinfo_id , int startPage , int endPage){
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select kq.i_id,kq.stu_id , kq.classinfo_id from ( ");
		sql.append(" select csi.i_id,csi.stu_id, csi.classinfo_id from  ");
		sql.append(" ( ");
		sql.append(" 	select i.id i_id,s.id stu_id,  s.stuName , c.id  classinfo_id, c.classname   ");
		sql.append(" 	from classinfo c , student s , info i  ");
		sql.append(" 			where i.classinfo_id = c.id  ");
		sql.append(" 				and i.stu_id = s.id ");
		sql.append(" 				and c.id = ? ");
		sql.append(" ) csi LEFT JOIN attence a	 ");
		sql.append(" 	on csi.classinfo_id = a.classinfo_id ");
		sql.append(" 	and csi.stu_id = a.stu_id ");
		sql.append(" GROUP BY csi.stuName ");
		sql.append(" HAVING  MAX(a.attence_time) < DATE_SUB(CURDATE(), INTERVAL 0 DAY)  ");
		sql.append(" UNION all ");
		sql.append(" select i.id i_id,s.id stu_id , c.id  classinfo_id  ");
		sql.append(" 		from classinfo c , student s , info i    ");
		sql.append(" 				where i.classinfo_id = c.id  ");
		sql.append(" 					and i.stu_id = s.id  ");
		sql.append(" 					and c.id = ? ");
		sql.append(" 					and s.id not in ( ");
		sql.append(" 									 	select  csi.stu_id  from   ");
		sql.append(" 								    	( ");
		sql.append(" 										  select s.id stu_id,  s.stuName , c.id  classinfo_id, c.classname ");
		sql.append(" 															from classinfo c , student s , info i ");
		sql.append(" 																where i.classinfo_id = c.id  ");
		sql.append(" 																	and i.stu_id = s.id ");
		sql.append(" 																	and c.id = ? ");
		sql.append(" 										) csi  ");
		sql.append(" 												INNER JOIN  ");
		sql.append(" 													attence a ");
		sql.append(" 													on a.stu_id = csi.stu_id ");
		sql.append(" 													and a.classinfo_id = csi.classinfo_id ");
		sql.append(" 													GROUP BY csi.stu_id ");
		sql.append(" 													) ");
		sql.append("  ) kq order by kq.i_id");
		sql.append(" LIMIT :startPage , :endPage ");
		
		SQLQuery query = session.createSQLQuery(sql.toString());
		
		query.setInteger(0, classinfo_id);
		query.setInteger(1, classinfo_id);
		query.setInteger(2, classinfo_id);
		
		query.setInteger("startPage", startPage);
		query.setInteger("endPage", endPage);
		
		List<Object[]> list = query.list();
		List<Info> result = new ArrayList<Info>();
		for (Object[] array : list) {
			
			Integer student_id = Integer.parseInt( array[1].toString() );
			
			Info info = new Info();
			Student student = studentDao.queryId(student_id);
			
			Classinfo classinfo = classinfoDao.findById(classinfo_id);
			
			info.setStudent(student);
			info.setClassinfo(classinfo);
			result.add(info);
		}
		return result;
	}
	
	/**
	 *  查询近 7天的 info 总数
	 * @return
	 */
	public int queryNearlyDayCountAndAllAttenceCount(int classinfo_id){
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select  count(*)  from ( ");
		sql.append(" select csi.stu_id, csi.classinfo_id from  ");
		sql.append(" ( ");
		sql.append(" 	select s.id stu_id,  s.stuName , c.id  classinfo_id, c.classname   ");
		sql.append(" 	from classinfo c , student s , info i  ");
		sql.append(" 			where i.classinfo_id = c.id  ");
		sql.append(" 				and i.stu_id = s.id ");
		sql.append(" 				and c.id = ? ");
		sql.append(" ) csi LEFT JOIN attence a	 ");
		sql.append(" 	on csi.classinfo_id = a.classinfo_id ");
		sql.append(" 	and csi.stu_id = a.stu_id ");
		sql.append(" GROUP BY csi.stuName ");
		sql.append(" HAVING  MAX(a.attence_time) < DATE_SUB(CURDATE(), INTERVAL 0 DAY)  ");
		sql.append(" UNION all ");
		sql.append(" select s.id stu_id , c.id  classinfo_id  ");
		sql.append(" 		from classinfo c , student s , info i    ");
		sql.append(" 				where i.classinfo_id = c.id  ");
		sql.append(" 					and i.stu_id = s.id  ");
		sql.append(" 					and c.id = ? ");
		sql.append(" 					and s.id not in ( ");
		sql.append(" 									 	select  csi.stu_id  from   ");
		sql.append(" 								    	( ");
		sql.append(" 										  select s.id stu_id,  s.stuName , c.id  classinfo_id, c.classname ");
		sql.append(" 															from classinfo c , student s , info i ");
		sql.append(" 																where i.classinfo_id = c.id  ");
		sql.append(" 																	and i.stu_id = s.id ");
		sql.append(" 																	and c.id = ? ");
		sql.append(" 										) csi  ");
		sql.append(" 												INNER JOIN  ");
		sql.append(" 													attence a ");
		sql.append(" 													on a.stu_id = csi.stu_id ");
		sql.append(" 													and a.classinfo_id = csi.classinfo_id ");
		sql.append(" 													GROUP BY csi.stu_id ");
		sql.append(" 													) ");
		sql.append("  ) kq  ");
		
		SQLQuery query = session.createSQLQuery(sql.toString());
		
		query.setInteger(0, classinfo_id);
		query.setInteger(1, classinfo_id);
		query.setInteger(2, classinfo_id);
		
		BigInteger total = (BigInteger) query.uniqueResult();
		
		return total.intValue();
	}
	
	/**
	 *  查询一次都没有考勤的 info
	 * @return
	 */
	public List<Info> queryNoneAllAttence(int classinfo_id){
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select i.stu_id , i.classinfo_id from ( ");
		sql.append(" select distinct i.stu_id , c.id classinfo_id from classinfo c , info i   ");
		sql.append(" where c.id = i.classinfo_id and c.id = ? ORDER BY i.stu_id desc ");
		sql.append(" ) i  ");
		sql.append(" where i.stu_id not in ( ");
		sql.append(" select a.stu_id from attence a where a.classinfo_id = ? GROUP BY a.stu_id ");
		sql.append(" ) ");
		
		SQLQuery query = session.createSQLQuery(sql.toString());
		
		query.setInteger(0, classinfo_id);
		query.setInteger(1, classinfo_id);
		
		List<Object[]> list = query.list();
		List<Info> result = new ArrayList<Info>();
		for (Object[] array : list) {
			
			Integer student_id = Integer.parseInt( array[0].toString() );
			
			Info info = new Info();
			Student student = studentDao.queryId(student_id);
			
			Classinfo classinfo = classinfoDao.findById(classinfo_id);
			
			info.setStudent(student);
			info.setClassinfo(classinfo);
			result.add(info);
		}
		return result;
	}
	
	/**
	 *  查询一次都没有考勤的 info 总数
	 */
	public int queryNoneAllAttenceCount(int classinfo_id){
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from ( ");
		sql.append(" select distinct i.stu_id , c.id classinfo_id from classinfo c , info i   ");
		sql.append(" where c.id = i.classinfo_id and c.id = ? ORDER BY i.stu_id desc ");
		sql.append(" ) i  ");
		sql.append(" where i.stu_id not in ( ");
		sql.append(" select a.stu_id from attence a where a.classinfo_id = ? GROUP BY a.stu_id ");
		sql.append(" ) ");
		
		SQLQuery query = session.createSQLQuery(sql.toString());
		
		query.setInteger(0, classinfo_id);
		query.setInteger(1, classinfo_id);
		
		BigInteger total = (BigInteger) query.uniqueResult();
		
		return total.intValue();
	}
	
	
	/**
	 * 批量更新缴费状态
	 * @param classinfo_ids 需要更新的班级id集合
	 * @param flag 缴费状态
	 */
	public void updateClassinfo_idsByflag(List<Integer> classinfo_ids,int flag){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Info set flag=:flag where classinfo_id in (:classinfo_ids)");
		//根据名字设置参数
		query.setInteger("flag", flag);
		query.setParameterList("classinfo_ids", classinfo_ids);
		query.executeUpdate();
		
	}
	

	/**
	 *  spell 查询近 7天的 info
	 * @return
	 */
	public List<Info> querySpellNearlyDay(String spell_stu_name, int startPage , int endPage){
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select m.stu_id , m.classinfo_id from ( ");
		sql.append(" select a.stu_id , a .classinfo_id from attence a ,(  select s.id stu_id,  s.stuName , c.id  classinfo_id, c.classname  ");
		sql.append(" from classinfo c , student s , info i ,attence a  	 ");
		sql.append(" where i.classinfo_id = c.id ");
		sql.append(" and i.stu_id = s.id ");
		
		if( spell_stu_name!=null && !"".equals(spell_stu_name) ) {
			
			for (int i = 0; i < spell_stu_name.length(); i++) {
				
				int spliceEnd = i+1;
				String spliceChar = spell_stu_name.substring(i, spliceEnd);
				
				String spliceSql = " and UPPER(substring(s.stuName,"+spliceEnd+",1)) in ( select d.content from dictionary d where ucase(d.first_spell) = ucase('"+spliceChar+"') )  ";
				sql.append(spliceSql);
			}	
			
		}
		
		sql.append(" GROUP BY s.id,c.id ) csi ");
		sql.append(" where a.stu_id = csi.stu_id ");
		sql.append(" and a.classinfo_id = csi.classinfo_id ");
		sql.append(" GROUP BY csi.stuName  , csi.classinfo_id ");
		sql.append(" HAVING  MAX(a.attence_time) < DATE_SUB(CURDATE(), INTERVAL 0 DAY)  ");
		sql.append(" union all ");
		sql.append(" select _i.stu_id , _i.classinfo_id from info _i ,student s ");
		sql.append(" where _i.id not in ( ");
		sql.append(" select i.id from info i , ");
		sql.append(" (select s.id stu_id,  s.stuName , c.id  classinfo_id, c.classname ");
		sql.append(" from classinfo c , student s , info i ,attence a  ");
		sql.append(" where i.classinfo_id = c.id  ");
		sql.append(" and i.stu_id = s.id  ");
		sql.append(" and a.classinfo_id = c.id ");
		sql.append(" and a.stu_id = s.id ");
		sql.append(" GROUP BY s.id , c.classname) csia  ");
		sql.append(" where i.stu_id = csia.stu_id ");
		sql.append(" and i.classinfo_id = csia.classinfo_id ");
		sql.append(" ) ");
		sql.append(" and _i.stu_id = s.id ");
		
		if( spell_stu_name!=null && !"".equals(spell_stu_name) ) {
			
			for (int i = 0; i < spell_stu_name.length(); i++) {
				
				int spliceEnd = i+1;
				String spliceChar = spell_stu_name.substring(i, spliceEnd);
				
				String spliceSql = " and UPPER(substring(s.stuName,"+spliceEnd+",1)) in ( select d.content from dictionary d where ucase(d.first_spell) = ucase('"+spliceChar+"') )  ";
				sql.append(spliceSql);
			}	
			
		}
		
		sql.append(" ) m ");
		
		//sql.append(" 		limit :startPage, :endPage ");
		sql.append(" limit "+startPage+","+endPage);
		Query query = session.createSQLQuery(sql.toString().trim());
		
//		query.setInteger("startPage", startPage);
//		query.setInteger("endPage", endPage);
		
		List<Object[]> list = query.list();
		List<Info> result = new ArrayList<Info>();
		for (Object[] array : list) {
			
			Integer student_id = Integer.parseInt( array[0].toString() );
			
			Info info = new Info();
			Student student = studentDao.queryId(student_id);
			
			Classinfo classinfo = classinfoDao.findById(Integer.parseInt( array[1].toString() ));
			
			info.setStudent(student);
			info.setClassinfo(classinfo);
			result.add(info);
		}
		return result;
	}
	
	/**
	 *  查询近 7天的 info 总数
	 * @return
	 */
	public int querySpellNearlyDayCount(String spell_stu_name){
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select COUNT(*) from ( ");
		sql.append(" 	select a.stu_id , a .classinfo_id from attence a ,(  select s.id stu_id,  s.stuName , c.id  classinfo_id, c.classname  ");
		sql.append(" 																from classinfo c , student s , info i ,attence a  	 ");
		sql.append(" 																where i.classinfo_id = c.id ");
		sql.append(" 																and i.stu_id = s.id ");
		
		if( spell_stu_name!=null && !"".equals(spell_stu_name) ) {
			
			for (int i = 0; i < spell_stu_name.length(); i++) {
				
				int spliceEnd = i+1;
				String spliceChar = spell_stu_name.substring(i, spliceEnd);
				
				String spliceSql = " and UPPER(substring(s.stuName,"+spliceEnd+",1)) in ( select d.content from dictionary d where ucase(d.first_spell) = ucase('"+spliceChar+"') )  ";
				sql.append(spliceSql);
			}	
			
		}
		
		sql.append(" 																GROUP BY s.id,c.id ) csi ");
		sql.append(" 	where a.stu_id = csi.stu_id ");
		sql.append(" 		and a.classinfo_id = csi.classinfo_id ");
		sql.append("  GROUP BY csi.stuName  , csi.classinfo_id ");
		sql.append(" HAVING  MAX(a.attence_time) < DATE_SUB(CURDATE(), INTERVAL 0 DAY)  ");
		sql.append(" union all ");
		sql.append(" select _i.stu_id , _i.classinfo_id from info _i ,student s ");
		sql.append(" 	where _i.id not in ( ");
		sql.append(" 											select i.id from info i , ");
		sql.append(" 											(select s.id stu_id,  s.stuName , c.id  classinfo_id, c.classname ");
		sql.append(" 													from classinfo c , student s , info i ,attence a  ");
		sql.append(" 													where i.classinfo_id = c.id  ");
		sql.append(" 													and i.stu_id = s.id  ");
		sql.append(" 													and a.classinfo_id = c.id ");
		sql.append(" 													and a.stu_id = s.id ");
		sql.append(" 													GROUP BY s.id , c.classname) csia  ");
		sql.append(" 												where i.stu_id = csia.stu_id ");
		sql.append(" 													and i.classinfo_id = csia.classinfo_id ");
		sql.append(" 										) ");
		sql.append(" 			and _i.stu_id = s.id ");
		
		if( spell_stu_name!=null && !"".equals(spell_stu_name) ) {
			
			for (int i = 0; i < spell_stu_name.length(); i++) {
				
				int spliceEnd = i+1;
				String spliceChar = spell_stu_name.substring(i, spliceEnd);
				
				String spliceSql = " and UPPER(substring(s.stuName,"+spliceEnd+",1)) in ( select d.content from dictionary d where ucase(d.first_spell) = ucase('"+spliceChar+"') )  ";
				sql.append(spliceSql);
			}	
			
		}
		
		sql.append(" ) m ");
		
		SQLQuery query = session.createSQLQuery(sql.toString());
		
		BigInteger total = (BigInteger) query.uniqueResult();
		
		return total.intValue();
	}

}