package com.shketai.dao;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mchange.v2.codegen.bean.ClassInfo;
import com.shketai.entity.ClassRoom;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Classroominfo;
import com.shketai.entity.Info;
import com.shketai.entity.Student;
import com.shketai.entity.Subject;
import com.shketai.util.DateStrUtil;

public class ClassinfoDao {

	private SessionFactory sessionFactory;
	
	private SubjectDao subjectDao;
	
	private ClassroominfoDao classroominfoDao;
	
	private ClassroomDao classroomDao;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

	public SubjectDao getSubjectDao() {
		return subjectDao;
	}



	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}



	public ClassroominfoDao getClassroominfoDao() {
		return classroominfoDao;
	}



	public void setClassroominfoDao(ClassroominfoDao classroominfoDao) {
		this.classroominfoDao = classroominfoDao;
	}



	public ClassroomDao getClassroomDao() {
		return classroomDao;
	}



	public void setClassroomDao(ClassroomDao classroomDao) {
		this.classroomDao = classroomDao;
	}



	/**
	 * 新增班级
	 * 
	 * @param classinfo
	 * @param classroominfo_id
	 * @param subject_id
	 * @return
	 */
	public int add(Classinfo classinfo, int subject_id) {
		Session session = sessionFactory.getCurrentSession();
		Subject subject = new Subject();
		subject.setId(subject_id);
		classinfo.setSubject(subject);
		classinfo.setNum(classinfo.getNumber());
		session.save(classinfo);
		return 1;
	}

	public int delete(Classinfo classinfo) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(classinfo);
		return 1;
	}

	/**
	 * 教室排班
	 * 
	 * @param id
	 */
	public Classinfo findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Classinfo ci = (Classinfo) session.get(Classinfo.class, id);
		return ci;
	}

	public int update(Classinfo classinfo) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Classinfo where id=:id ");
		query.setInteger("id", classinfo.getId());
		List<Classinfo> list = query.list();
		Classinfo oldClassinfo = list.get(0);
		classinfo.setId(oldClassinfo.getId());
		classinfo.setClassroominfo(oldClassinfo.getClassroominfo());
		classinfo.setSubject(oldClassinfo.getSubject());
		classinfo.setHeadmaster_id( oldClassinfo.getHeadmaster_id() );
		session.clear();
		session.update(classinfo);
		return 1;
	}

	/**
	 * 选课时，当用户选择了某个科目，再查询符合年龄的班级
	 * 
	 * @param subjectId
	 *            课程编号
	 * @return age 用户的年龄
	 * @throws Exception
	 */
	public List<Classinfo> findBySubjectAndAge(int major_id, int stu_id,int page) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"from Classinfo where subject.majors.id = ? and min_age >= (select birthday from Student where id = ?)");
		query.setInteger(0, major_id);
		query.setInteger(1, stu_id);
		query.setFirstResult((page-1)*10);
		query.setMaxResults(10);
		List<Classinfo> list = query.list();
		return list;
	}
	
	public int findCount1(int major_id, int stu_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Classinfo where subject.majors.id = ? and min_age >= (select birthday from Student where id = ?)");
		query.setInteger(0, major_id);
		query.setInteger(1, stu_id);
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}


	/**
	 * 选课(报名)
	 * 
	 * @param uid
	 *    学生id
	 * @param classid
	 *     班级编号
	 */
	public void selectSubject(int stu_id, int classinfo_id) { 
		Session session = sessionFactory.getCurrentSession();

		Info info = new Info();

		// 学生对象
		Student stu = new Student();
		stu.setId(stu_id);
		info.setStudent(stu);

		// 查询班级对象
		Classinfo ci = (Classinfo) session.get(Classinfo.class, classinfo_id);
		
		if(ci.getNum()>0){
		// 该班级可选人数减1
		ci.setNum(ci.getNum() - 1);

		info.setClassinfo(ci);
		info.setFlag(1);
		info.setResults("已确认过报名须知！");
		session.save(info);
		}
	}
	
	/**
	 *  判断是否重复选课 TODO
	 */
	public int doYouRepeatTheCourse(int stu_id, int classinfo_id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		return 0 ;
	}

	public int getNum(int classinfo_id){
		int result;
		Session session = sessionFactory.getCurrentSession();
		Classinfo ci = (Classinfo) session.get(Classinfo.class, classinfo_id);
		/*int count = CountPeople(classinfo_id);
		ci.setNum(ci.getNumber()-count);*/
		if(ci.getNum()<=0){
			 return result = 0;
		}else{
			return result = 1;
		}
	}

	/**
	 * 查是否要面试
	 * @param classinfo_id
	 * @return
	 */
	public int findflag(int classinfo_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select interview from subject ,classinfo  where subject.id = classinfo.subject_id and classinfo.id=?");
		query.setInteger(0, classinfo_id);
		int result =  (int) query.uniqueResult();
		return result;
	}
	/**
	 * 查询面试结果
	 * -1：没有面试，需要预约面试
	 * 1：面试不通过
	 * 2：通过
	 * @param stu_id
	 * @return
	 */
	public int findInterview_info(int stu_id){
		int result = -1;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select result from Interview_info where student.id =?");
		query.setInteger(0, stu_id);
		//出现异常，首先看异常信息：158行空指针异常，也就是语句没有查询到结果，还要转成int型，就报空指针异常
		//43号学生没有面试，所以查不到，正常，所以你需要先判断，再转换
		Object object = query.uniqueResult();
		if(object != null){
			result =  (int) query.uniqueResult();
		}
		return result;
	
	}
	
	public int CountPeople(int classinfo_id){
		Session session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("SELECT count(*) FROM info i,classinfo c WHERE  i.classinfo_id=? AND c.id=i.classinfo_id  ");
		Long total = (Long) sqlQuery.uniqueResult();
		return total.intValue();
	}
	
	public List<Classinfo> findAll(int page) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select t1.id,t1.classname,t1.keshi,t1.money,t1.min_age,t1.subject_id,t1.classroominfo_id, t1.unit_price , t1.num , t1.max_age ,t1.kaifang,t1.number ");
		
		sql.append(" ,t3.subject,t4.`day`,t4.`start`,t4.jieshu,IFNULL(t2.count,0) as count,cr.id as cid,cr.name from classinfo t1 LEFT JOIN (select classinfo_id,count(*) as count from info group by classinfo_id) t2 on t1.id = t2.classinfo_id LEFT JOIN subject t3 on t1.subject_id = t3.id LEFT JOIN classroominfo t4 ON t4.id = t1.classroominfo_id left join classroom as cr on(cr.id=t4.classroom_id) group by t1.id  ");
		
		SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
		sqlQuery.setFirstResult((page-1)*10);
		sqlQuery.setMaxResults(10);
		List<Object[]> list = sqlQuery.list();
		List<Classinfo> cis = new ArrayList<Classinfo>();
		for (Object[] array : list) {
			Classinfo ci = new Classinfo();
			ci.setId(Integer.parseInt(array[0].toString()));
			ci.setClassname(array[1].toString());
			ci.setKeshi(Integer.parseInt(array[2].toString()));
			ci.setMoney(Double.parseDouble(array[3].toString()));
			ci.setKaifang(Integer.parseInt(array[10].toString()));
			try {
				ci.setMin_age(sdf.parse(array[4].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				ci.setMax_age(sdf.parse(array[9].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ci.setUnit_price(Double.parseDouble(array[7].toString()));
//			ci.setNum(Integer.parseInt(array[8].toString()));	//剩余人数
			
//			通过传递的 班级id 更新 班级里的剩余人数
			updateClassinfoInnum( Integer.parseInt(array[0].toString()) );
//			查看指定班级 id 返回 剩余人数
			Integer num = queryIdReturnNum( Integer.parseInt(array[0].toString()) );
			
//			剩余人数
			ci.setNum( num );	
			
			Subject subject = new Subject();
			subject.setSubject(array[12].toString());
			ci.setSubject(subject);
			ci.setNumber(Integer.parseInt(array[11].toString())); //额定人数
			
//			已配置人数 = 额定人数 - 剩余人数
			ci.setCount(  Integer.parseInt(array[11].toString()) - num );	//已配置人数
			
			Classroominfo classroominfo = new Classroominfo();
			if(array[13]!=null) {
				classroominfo.setDay(Integer.parseInt(array[13].toString()));
			}
			if(array[14]!=null) {
				classroominfo.setStart(array[14].toString());
			}
			if(array[15]!=null) {
				classroominfo.setJieshu(array[15].toString());
			}
			ci.setClassroominfo(classroominfo);
			
			
//			教师信息
			ClassRoom classroom=new ClassRoom();
			if(array[17]!=null) {
				classroom.setId(Integer.parseInt(array[17].toString()));
			}
			if(array[18]!=null) {
				classroom.setName(array[18].toString());	
			}
			ci.setClassroom(classroom);
			
			cis.add(ci);
		}
//		Query query = session.createQuery("from Classinfo");

//		query.setFirstResult((page - 1) * 10);
//		query.setMaxResults(10);
//		List<Classinfo> list = query.list();
		return cis;
	}

	
	public List<Classinfo> findAll1() {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Session session = sessionFactory.getCurrentSession();
		//SQLQuery sqlQuery = session.createSQLQuery(
			//	"SELECT t1.*,t3.subject,count(*) as count from classinfo t1 LEFT JOIN info t2 on t1.id = t2.classinfo_id LEFT JOIN subject t3 on t1.subject_id = t3.id group by t1.id");
		//sqlQuery.setFirstResult((page - 1) * 10);
		//sqlQuery.setMaxResults(10);
		//List<Object[]> list = sqlQuery.list();
		//List<Classinfo> cis = new ArrayList<Classinfo>();
		/*for (Object[] array : list) {
			Classinfo ci = new Classinfo();
			ci.setId(Integer.parseInt(array[0].toString()));
			ci.setClassname(array[1].toString());
			ci.setKeshi(Integer.parseInt(array[2].toString()));
			ci.setMoney(Double.parseDouble(array[3].toString()));
			try {
				ci.setMin_age(sdf.parse(array[4].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ci.setUnit_price(Double.parseDouble(array[7].toString()));
			Subject subject = new Subject();
			subject.setSubject(array[9].toString());
			ci.setSubject(subject);
			ci.setCount(Integer.parseInt(array[10].toString()));
			cis.add(ci);
		}
		return cis;*/
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" from Classinfo ");
		List<Classinfo> list = query.list();
		return list;
		
	}
	
	public int findRQ(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Info where classinfo_id=:classinfo_id");
		query.setInteger("classinfo_id", classinfo_id);
		Long total = (Long) query.uniqueResult();
		return total.intValue();
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
		boolean flag = false;
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

	/**
	 * 根据姓名查询班级
	 * 
	 * @param username
	 * @return
	 */ 
	public List<Classinfo> queryByUsername(String classname) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.id,t1.classname,t1.keshi,t1.money,t1.min_age,t1.subject_id,t1.classroominfo_id, t1.unit_price , t1.num , t1.max_age ,t1.kaifang,t1.number ");
		sql.append(" ,t3.subject,t4.`day`,t4.`start`,t4.jieshu,IFNULL(t2.count,0) as count,cr.id as cid,cr.name from classinfo t1 LEFT JOIN (select classinfo_id,count(*) as count from info group by classinfo_id) t2 on t1.id = t2.classinfo_id LEFT JOIN subject t3 on t1.subject_id = t3.id LEFT JOIN classroominfo t4 ON t4.id = t1.classroominfo_id "
				+ "left join classroom as cr on(cr.id=t4.classroom_id) WHERE classname like ?  group by t1.id  desc");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		sqlQuery.setString(0, "%" + classname + "%").list();
		List<Object[]> list = sqlQuery.list();
		List<Classinfo> cis = new ArrayList<Classinfo>();
		for (Object[] array : list) {
			Classinfo ci = new Classinfo();
			try {
				ci.setId(Integer.parseInt(array[0].toString()));
			} catch (Exception e9) {
				// TODO Auto-generated catch block
				
			}
			try {
				ci.setClassname(array[1].toString());
			} catch (Exception e8) {
				
			}
			try {
				ci.setKeshi(Integer.parseInt(array[2].toString()));
			} catch (Exception e7) {
				// TODO Auto-generated catch block
				
			}
			try {
				ci.setMoney(Double.parseDouble(array[3].toString()));
			} catch (Exception e6) {
				// TODO Auto-generated catch block
		
			}
			//---2019.6.12,gwf
			try {
				ci.setKaifang(Integer.parseInt(array[10].toString()));
			} catch (Exception e) {
	
			}
			//---end

			try {
				ci.setMin_age(sdf.parse(array[4].toString()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				ci.setMax_age(sdf.parse(array[9].toString()));
			} catch (Exception e) {
	
			}
			try {
				ci.setUnit_price(Double.parseDouble(array[7].toString()));
			} catch (Exception e5) {
				// TODO Auto-generated catch block
		
			}
			
//			通过传递的 班级id 更新 班级里的剩余人数
			updateClassinfoInnum( Integer.parseInt(array[0].toString()) );
//			查看指定班级 id 返回 剩余人数
			Integer num = queryIdReturnNum( Integer.parseInt(array[0].toString()) );
						
			ci.setNum(num);	//剩余人数
			
			Subject subject = new Subject();
			if(array[12]!=null) {
				subject.setSubject(array[12].toString());
			}	
			ci.setSubject(subject);
			if(array[11]!=null) {
				ci.setNumber(Integer.parseInt(array[11].toString()));
			}
			
			
//			已配置人数 = 额定人数 - 剩余人数
			if(array[11]!=null) {
				ci.setCount(  Integer.parseInt(array[11].toString()) - num );	//已配置人数
			}
			
			
			Classroominfo classroominfo = new Classroominfo();
			if(array[13]!=null) {
				classroominfo.setDay(Integer.parseInt(array[13].toString()));
			}
			if(array[14]!=null) {
				classroominfo.setStart(array[14].toString());
			}
			if(array[15]!=null) {
				classroominfo.setJieshu(array[15].toString());		
			}
			ci.setClassroominfo(classroominfo);
			
//			教师信息
			ClassRoom classroom=new ClassRoom();
			if(array[17]!=null) {
				classroom.setId(Integer.parseInt(array[17].toString()));
			}
			if(array[18]!=null) {
				classroom.setName(array[18].toString());
			}else {
				classroom.setName(null);
			}
			ci.setClassroom(classroom);
			
			cis.add(ci);
		}
		return cis;
	}

	/**
	 * 根据id查询班级
	 * 
	 * @param username
	 * @return
	 */ 
	public List<Classinfo> queryById(Integer id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.id,t1.classname,t1.keshi,t1.money,t1.min_age,t1.subject_id,t1.classroominfo_id, t1.unit_price , t1.num , t1.max_age ,t1.kaifang,t1.number ");
		sql.append(" ,t3.subject,t4.`day`,t4.`start`,t4.jieshu,IFNULL(t2.count,0) as count,cr.id as cid,cr.name from classinfo t1 LEFT JOIN (select classinfo_id,count(*) as count from info group by classinfo_id) t2 on t1.id = t2.classinfo_id LEFT JOIN subject t3 on t1.subject_id = t3.id LEFT JOIN classroominfo t4 ON t4.id = t1.classroominfo_id "
				+ "left join classroom as cr on(cr.id=t4.classroom_id) WHERE t1.id=?  group by t1.id  desc");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		sqlQuery.setInteger(0,id);
		List<Object[]> list = sqlQuery.list();
		List<Classinfo> cis = new ArrayList<Classinfo>();
		for (Object[] array : list) {
			Classinfo ci = new Classinfo();
			ci.setId(Integer.parseInt(array[0].toString()));
			ci.setClassname(array[1].toString());
			ci.setKeshi(Integer.parseInt(array[2].toString()));
			ci.setMoney(Double.parseDouble(array[3].toString()));
			ci.setKaifang(Integer.parseInt(array[10].toString()));	//---2019.6.12,gwf
			try {
				ci.setMin_age(sdf.parse(array[4].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				ci.setMax_age(sdf.parse(array[9].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ci.setUnit_price(Double.parseDouble(array[7].toString()));
			
//			通过传递的 班级id 更新 班级里的剩余人数
			updateClassinfoInnum( Integer.parseInt(array[0].toString()) );
//			查看指定班级 id 返回 剩余人数
			Integer num = queryIdReturnNum( Integer.parseInt(array[0].toString()) );
						
			ci.setNum(num);	//剩余人数
			
			Subject subject = new Subject();
			subject.setSubject(array[12].toString());			
			ci.setSubject(subject);
			
			ci.setNumber(Integer.parseInt(array[11].toString()));
			
			
//			已配置人数 = 额定人数 - 剩余人数
			ci.setCount(  Integer.parseInt(array[11].toString()) - num );	//已配置人数
			
			
			Classroominfo classroominfo = new Classroominfo();
			
			try {
				classroominfo.setDay(Integer.parseInt(array[13].toString()));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				classroominfo.setStart(array[14].toString());				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				classroominfo.setJieshu(array[15].toString());				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			ci.setClassroominfo(classroominfo);
			
//			教师信息
			ClassRoom classroom=new ClassRoom();
			classroom.setId(Integer.parseInt(array[17].toString()));
			classroom.setName(array[18].toString());
			ci.setClassroom(classroom);
			
			cis.add(ci);
		}
		return cis;
	}

	
	public int findCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Classinfo ");
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}

	public List<Classinfo> findBySubjectAndAges(int subject_id, int stu_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"from Classinfo where subject.id = ? and min_age <= (select birthday from Student where id = ?)");
		query.setInteger(0, subject_id);
		query.setInteger(1, stu_id);
		List<Classinfo> list = query.list();
		return list;
	}
	
	public List<Classinfo> findRoom(int classroom_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT c.classname,c2.`day`,c2.`start`,c2.jieshu from Classinfo c , classroominfo c2 where c.classroominfo_id = c2.id AND c2.classroom_id=?");
		query.setInteger(0, classroom_id);
		List<Object[]> list = query.list();
		List<Classinfo> cis = new ArrayList<Classinfo>();
		for (Object[] array : list) {
			Classinfo ci = new Classinfo();  
			ci.setClassname(array[0].toString());
			Classroominfo classroominfo = new Classroominfo();
			classroominfo.setDay(Integer.parseInt(array[1].toString()));
			classroominfo.setStart(array[2].toString());
			classroominfo.setJieshu(array[3].toString());
			ci.setClassroominfo(classroominfo);
			cis.add(ci);
		}
			return cis;
	}


	
	/**
	 * @return
	 *  查看未分配教室的班级
	 *  没有教室的班级
	 */
	public List<Classinfo> classinfoInclassroominfo_idIsNull() {
		
		Session session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(" select  id, classname  from Classinfo where classroominfo_id is null ");
		
		//SQLQuery对象list()方法返回一个集合数组对象
		List<Object[]> list = sqlQuery.list();
		
		List<Classinfo> classinfoList = new ArrayList<Classinfo>();
		
		for (Object obj[] : list) {
			
			int id =  (int) obj[0];
			String classname = (String) obj[1];
			
			Classinfo classinfo = new Classinfo();
			
			classinfo.setId(id);
			classinfo.setClassname(classname);
			
			classinfoList.add(classinfo);
		}
		
		
		return  classinfoList;
	}
	
	/**
	 * @return
	 *  查看未分配班主任的班级
	 *  没有班主任的班级
	 */
	public List<Classinfo> classinfoInheadmaster_idIsNull(){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer(" select  id, classname  from Classinfo where headmaster_id is null ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		//SQLQuery对象list()方法返回一个集合数组对象
		List<Object[]> list = sqlQuery.list();
		
		List<Classinfo> classinfoList = new ArrayList<Classinfo>();
		
		for (Object obj[] : list) {
			
			int id =  (int) obj[0];
			String classname = (String) obj[1];
			
			Classinfo classinfo = new Classinfo();
			
			classinfo.setId(id);
			classinfo.setClassname(classname);
			
			classinfoList.add(classinfo);
		}
		
		
		return  classinfoList;
	}
	
	
	/**
	 * @return
	 *  查看指定班主任的班级
	 */
	public List<Classinfo> classinfoInheadmaster_idIsTrue(int headmaster_id){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select  id, classname  from Classinfo where headmaster_id = :headmaster_id ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setInteger("headmaster_id", headmaster_id);
		
		//SQLQuery对象list()方法返回一个集合数组对象
		List<Object[]> list = sqlQuery.list();
		
		List<Classinfo> classinfoList = new ArrayList<Classinfo>();
		
		for (Object obj[] : list) {
			
			int id =  (int) obj[0];
			String classname = (String) obj[1];
			
			Classinfo classinfo = new Classinfo();
			
			classinfo.setId(id);
			classinfo.setClassname(classname);
			
			classinfoList.add(classinfo);
		}
		
		
		return  classinfoList;
	}
	
	/**
	 * 	清空指定班级 里的 班主任关联
	 * @return
	 *  classinfo_id  班级id
	 */
	public int classinfoClearheadmaster_id(int classinfo_id){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update classinfo set headmaster_id = null where id = :id ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setInteger("id", classinfo_id);
		
		sqlQuery.executeUpdate();
		
		return  1;
	}
	
	
	/**
	  *   指定班级添加班主任
	 *  classinfo_id 班级id
	 *   id  班主任id
	 */
	public int classinfoUpdateheadmaster_id(int classinfo_id , int headmaster_id){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update classinfo set headmaster_id = :headmaster_id  where id = :id ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setInteger("headmaster_id", headmaster_id);
		sqlQuery.setInteger("id", classinfo_id);
		
		sqlQuery.executeUpdate();
		
		return  1;
	}
	
	
	/**
	 * @return
	 *   查询 所有班级  
	 *   返回 id  班级名称
	 */
	public List<Classinfo> sqlAllClassinfo(){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select  id, classname  from Classinfo ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		//SQLQuery对象list()方法返回一个集合数组对象
		List<Object[]> list = sqlQuery.list();
		
		List<Classinfo> classinfoList = new ArrayList<Classinfo>();
		
		for (Object obj[] : list) {
			
			int id =  (int) obj[0];
			String classname = (String) obj[1];
			
			Classinfo classinfo = new Classinfo();
			
			classinfo.setId(id);
			classinfo.setClassname(classname);
			
			classinfoList.add(classinfo);
		}
		
		return  classinfoList;
	}
	
	
	
	/**
	 *  清空 班级里的 班主任关联
	 * @param headmaster_id
	 * @return
	 */
	public int clearheadmaster_id(int headmaster_id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update classinfo set headmaster_id = null  where headmaster_id = :headmaster_id ");
		
		SQLQuery sQLQuery = session.createSQLQuery( sql.toString() );
		
		sQLQuery.setInteger("headmaster_id", headmaster_id);
		
		sQLQuery.executeUpdate();
		
		return 1;
	}
	
	/**
	 *  把指定班级的 额定人数 赋值给 剩余人数
	 */
	public int classinfoInnumber_num(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update classinfo set num = number where id = :id ");
		
		SQLQuery sQLQuery = session.createSQLQuery( sql.toString() );
		
		sQLQuery.setInteger("id", id);
		
		sQLQuery.executeUpdate();
		
		return 1;
	}
	
	/**
	 *  班级考勤数据汇总
	 *   pageNum 页码
	 *  pageSize 数量
	 *  classname 班级名称
	 */
	public List<Classinfo> queryClassinfoInAttence( int pageNum,int pageSize,String classname ) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" from Classinfo where classname like :classname ");
		
		Query query = session.createQuery( sql.toString() );
		
		if(classname == null) {
			classname = "";
		}
		
		query.setString("classname", "%" + classname + "%");
		
//		指定从那个对象开始查询，参数的索引位置是从0开始的，
		query.setFirstResult((pageNum-1)*pageSize);
//		分页时，一次最多产寻的对象数
		query.setMaxResults(pageSize);
		
		List<Classinfo> list = query.list();
		return list;
	}
	
	
	/**
	 * 查询指定班级名称  返回总条数
	 * @param classname
	 * @return
	 */
	public int queryClassinfoInclassname(String classname) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from Classinfo where classname like :classname ");
		
		Query query = session.createQuery( sql.toString() );
		
		if(classname == null) {
			classname = "";
		}
		
		query.setString("classname", "%" + classname + "%");
		
		Long count = (Long) query.uniqueResult();
		
		return count.intValue();
	}
	
	/**
	 * 查询指定班级名称  返回 班级对象
	 * @param classname
	 * @return
	 */
	public Classinfo queryClassinfoInclassnameReturnObj(String classname) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" from Classinfo where classname = :classname ");
		
		Query query = session.createQuery( sql.toString() );
		
		query.setString("classname",  classname);
		
		
		return (Classinfo) query.uniqueResult();
	}
	
	
	/**
	 *   通过传递的 班级id 更新 班级里的剩余人数
	 */
	public int updateClassinfoInnum(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update classinfo set num = number - (select count(*) from info where classinfo_id = :classinfo_id )  where id = :id ");
		
		SQLQuery sQLQuery = session.createSQLQuery( sql.toString() );
		
		sQLQuery.setInteger("classinfo_id", classinfo_id);
		sQLQuery.setInteger("id", classinfo_id);
		
		sQLQuery.executeUpdate();
		
		return 1;
	}
	
	/**
	 * @return
	 *  查看指定班级id 返回 剩余人数
	 */
	public Integer queryIdReturnNum(int id){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select  num  from Classinfo where id = :id ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setInteger("id", id);
		
		
		return  (Integer) sqlQuery.uniqueResult();
	}
	
	/**
	 * @return
	 *  查看指定班级id 
	 *  返回 已配置人数 = 额定人数 - 剩余人数
	 */
	public Integer queryIdReturnCount(int id){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select (number - num)   from Classinfo where id = :id ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setInteger("id", id);
		
		BigInteger count = (BigInteger) sqlQuery.uniqueResult();
		return  count.intValue(); 
	}
	
	
	/**
	 *   查询所有班级
	 */
	public List<Classinfo> queryClassinfoAll() {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" from Classinfo ");
		
		Query query = session.createQuery( sql.toString() );
		
		List<Classinfo> list = query.list();
		return list;
	}
	
	
	/**
	 * @return
	 *  查看指定学生id 
	 *  返回班级 集合对象
	 */
	public List<Classinfo> queryStu_idReturnClassinfo(int stu_id){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select c.id , c.classname , c.subject_id ,c.classroominfo_id  from Info i , Classinfo c  ");
		sql.append(" where i.classinfo_id = c.id  ");
		sql.append(" and i.stu_id = :stu_id ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setInteger("stu_id", stu_id);
		
		List<Object[]> list = sqlQuery.list();
		
		List<Classinfo> classinfoList = new ArrayList<Classinfo>();
		
		
		for (int i = 0; i < list.size(); i++) {
			
			Object[] obj = list.get(i);
			
			int id =  Integer.parseInt( obj[0].toString() );
			String classname  = obj[1].toString();
			int subject_id  =  Integer.parseInt( obj[2].toString() );
			int classroominfo_id =  Integer.parseInt( obj[3].toString() );
			
			Classinfo classinfo = new Classinfo();
			classinfo.setId(id);
			classinfo.setClassname(classname);
			
			
			Subject subject = subjectDao.findId(subject_id);
			classinfo.setSubject(subject);
			
			Classroominfo classroominfo = classroominfoDao.findId(classroominfo_id);
			classinfo.setClassroominfo(classroominfo);
			
			
			classinfoList.add(classinfo);
		}
		
		
		return  classinfoList; 
	}
	
	
	/**
	  *   删除
	 *  classinfo_id 班级id
	 */
	public int sqlDeleteId(int id ){
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from  classinfo  where id = :id ");
		
		SQLQuery sqlQuery = session.createSQLQuery( sql.toString() );
		
		sqlQuery.setInteger("id", id);
		
		sqlQuery.executeUpdate();
		
		return  1;
	}
	
}
