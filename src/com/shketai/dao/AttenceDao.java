package com.shketai.dao;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Attence;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Reasons;
import com.shketai.entity.Student;

public class AttenceDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 查看考勤
	 * 
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> findAttenceById(int user_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createSQLQuery("select a.name,c.total from "
				+ "(select b.id as sid,b.stuName as name from student b where b.user_id = ?) a left join (select d.stu_id as sid,count(*) as total from attence d where d.attence_status = 1 group by d.stu_id) c on a.sid = c.sid");
		query1.setLong(0, user_id);
		List<Object[]> list1 = query1.list();

		Query query2 = session.createSQLQuery("select a.name,c.total from "
				+ "(select b.id as sid,b.stuName as name from student b where b.user_id = ?) a left join (select d.stu_id as sid,count(*) as total from attence d where d.attence_status = 2 group by d.stu_id) c on a.sid = c.sid");
		query2.setLong(0, user_id);
		List<Object[]> list2 = query2.list();

		Query query3 = session.createSQLQuery("select a.name,c.total from "
				+ "(select b.id as sid,b.stuName as name from student b where b.user_id = ?) a left join (select d.stu_id as sid,count(*) as total from attence d where d.attence_status = 3 group by d.stu_id) c on a.sid = c.sid");
		query3.setLong(0, user_id);
		List<Object[]> list3 = query3.list();

		Query query4 = session.createSQLQuery("select a.name,c.total from "
				+ "(select b.id as sid,b.stuName as name from student b where b.user_id = ?) a left join (select d.stu_id as sid,count(*) as total from attence d where d.attence_status = 4 group by d.stu_id) c on a.sid = c.sid");
		query4.setLong(0, user_id);
		List<Object[]> list4 = query4.list();

		for (int i = 0; i < list1.size(); i++) {
			Map<String, Object> allAttence = new HashMap<String, Object>();
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

			Integer i1 = (list1.get(i)[1] == null ? 0 : Integer.parseInt(list1.get(i)[1].toString()));
			Integer i2 = (list2.get(i)[1] == null ? 0 : Integer.parseInt(list2.get(i)[1].toString()));
			Integer i3 = (list3.get(i)[1] == null ? 0 : Integer.parseInt(list3.get(i)[1].toString()));
			Integer i4 = (list4.get(i)[1] == null ? 0 : Integer.parseInt(list4.get(i)[1].toString()));

			map.put(1, i1);
			map.put(2, i2);
			map.put(3, i3);
			map.put(4, i4);
			allAttence.put("stuName", list1.get(i)[0].toString());
			allAttence.put("attence", map);
			list.add(allAttence);
		}
		return list;
	}

	public List<Attence> UnAttence(int user_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Student where user_id =:user_id");
		query.setInteger("user_id", user_id);
		List<Student> list = query.list();
		//存放所有学生的id
		List<Integer> ids = new ArrayList<Integer>();
		for (Student student :list) {
			ids.add(student.getId());
		}
		//这一条hql语句相当于  select * from info where stu_id in(1,2,90)，就是查询这些学生的选课信息
		Query query2 = session.createQuery("from Attence where attence_status=2 and student.id in(:ids) ");
		query2.setParameterList("ids",ids);
		List<Attence> list1 = query2.list();
		return list1;
	}
	/**
	 * 考勤
	 * 
	 * @param id
	 */
	public void add(List<Integer> stu_id, int attence_status, int classinfo_id, List<Integer> reason_id_list) {
		Session session = sessionFactory.getCurrentSession();
		Date now = new Date();
		// 遍历集合，得到 每个学生id
		for (int i = 0; i < stu_id.size(); i++) {
			int id = stu_id.get(i);
			int reason_id = reason_id_list.get(i);
			
			Attence attence = new Attence();
			Student student = new Student();
			student.setId(id);
			attence.setStudent(student);
			Classinfo classinfo = new Classinfo();
			classinfo.setId(classinfo_id);
			attence.setAttence_status(attence_status);
			attence.setAttence_time(now);
			attence.setClassinfo(classinfo);
			if(reason_id!=0){
				Reasons reasons = new Reasons();
				reasons.setId(reason_id);
				attence.setReasons(reasons);
			}
			session.save(attence);
		}
	}

	/**
	 * 考勤统计
	 * 
	 * @return
	 */
	public List<Attence> CountAttence() {
		String hql = "from Attence";
		Session session = sessionFactory.getCurrentSession();
		List<Attence> list = session.createQuery(hql).list();
		return list;
	}

	public List<Object[]> Attendancesummary(int page, int classinfo_id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select s.stuName,c.classname,s.personalNum,a.attence_status  , a.reason_id ");
		sql.append(" from  classinfo c , student s , info i, attence a  ");
		sql.append(" where i.classinfo_id = c.id ");
		sql.append(" and i.stu_id = s.id ");
		sql.append(" and c.id = a.classinfo_id  ");
		sql.append(" and s.id = a.stu_id ");
		sql.append(" and a.attence_time>DATE_SUB(CURDATE(), INTERVAL 0 DAY) ");
		sql.append(" and a.classinfo_id = :classinfo_id ");
		sql.append(" GROUP BY s.stuName ");
		sql.append(" limit :startPage , :endPage ");
		
		SQLQuery  sqlQuery = session.createSQLQuery(sql.toString());
		
		sqlQuery.setInteger("classinfo_id", classinfo_id);
		sqlQuery.setInteger("startPage", (page - 1) * 10 );
		sqlQuery.setInteger("endPage", 10);
		
		List<Object[]> list = sqlQuery.list();
		return list;
	}
	
	public int findCount(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select count(*) from ( ");
		sql.append(" select s.stuName,c.classname,s.personalNum,a.attence_status  , a.reason_id ");
		sql.append(" from  classinfo c , student s , info i, attence a  ");
		sql.append(" where i.classinfo_id = c.id ");
		sql.append(" and i.stu_id = s.id ");
		sql.append(" and c.id = a.classinfo_id  ");
		sql.append(" and s.id = a.stu_id ");
		sql.append(" and a.attence_time>DATE_SUB(CURDATE(), INTERVAL 0 day) ");
		sql.append(" and a.classinfo_id = :classinfo_id ");
		sql.append(" GROUP BY s.stuName ");
		sql.append(" ) c ");
		
		SQLQuery  sqlQuery = session.createSQLQuery(sql.toString());
		sqlQuery.setInteger("classinfo_id", classinfo_id);
		BigInteger total = (BigInteger) sqlQuery.uniqueResult();
		return total.intValue();
	}
	public int CountAttence(int classinfo_id,String dateTime) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql=new StringBuffer("select count(distinct stu_id) from attence  where  attence_status=1 and classinfo_id=?");
		if(dateTime!=null&&dateTime!="") {
			sql.append(" and DATE_FORMAT( attence_time,'%Y-%m-%d')=DATE_FORMAT('"+dateTime+"', '%Y-%m-%d') ");
		}else {
			sql.append(" and DATE_FORMAT( attence_time,'%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d')");
		}
		Query query = session.createSQLQuery(sql.toString());
		query.setInteger(0, classinfo_id);
		BigInteger total = (BigInteger) query.uniqueResult();
		return total.intValue();
	}
	
	public int CountUnAttence(int classinfo_id,String dateTime) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql=new StringBuffer("select count(distinct stu_id) from attence  where  attence_status=2 and classinfo_id=?");
		if(dateTime!=null&&dateTime!="") {
			sql.append(" and DATE_FORMAT( attence_time,'%Y-%m-%d')=DATE_FORMAT('"+dateTime+"', '%Y-%m-%d') ");
		}else {
			sql.append(" and DATE_FORMAT( attence_time,'%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d')");
		}
		Query query = session.createSQLQuery(sql.toString());
		query.setInteger(0, classinfo_id);
		BigInteger total = (BigInteger) query.uniqueResult();
		return total.intValue();
	}
	
	public int CountAttence(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(distinct stu_id) from attence  where  attence_status=1 and classinfo_id=? AND DATE_FORMAT( attence_time,'%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d')");
		query.setInteger(0, classinfo_id);
		BigInteger total = (BigInteger) query.uniqueResult();
		return total.intValue();
	}
	
	public int CountUnAttence(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(distinct stu_id) from attence  where  attence_status=2 and classinfo_id=? AND DATE_FORMAT( attence_time,'%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d')");
		query.setInteger(0, classinfo_id);
		BigInteger total = (BigInteger) query.uniqueResult();
		return total.intValue();
	}
	
	public int CountUnAttenceByStarttimeAndEndtime(int classinfo_id , Date startTime , Date endTime) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(stu_id) from attence  ");
		sql.append(" where  attence_status=2 ");
		sql.append(" and classinfo_id = :classinfo_id ");
		sql.append(" AND attence_time >= :startTime ");
		sql.append(" and attence_time <= :endTime ");
		
		Query query = session.createSQLQuery(sql.toString());
		query.setInteger("classinfo_id", classinfo_id);
		query.setDate("startTime", startTime);
		query.setDate("endTime", endTime);
		
		BigInteger total = (BigInteger) query.uniqueResult();
		return total.intValue();
	}
	
	
	public List<Object[]> Attendancesummary1(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select s.stuName,c.classname,s.personalNum,a.attence_status ,r.reason from attence a INNER JOIN classinfo c ON a.classinfo_id=c.id AND  a.attence_time>DATE_SUB(CURDATE(), INTERVAL 0 day)  AND a.classinfo_id=? INNER JOIN student s ON a.stu_id=s.id LEFT JOIN reasons r ON a.reason_id=r.id   ");
		query.setInteger(0, classinfo_id);
		List<Object[]> list = query.list();
		return list;
	}
	
	public void updateFlag(int id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Attence set attence_status=1 where id=:id");
		//引号id
		query.setInteger("id",id);
		System.out.println(id);
		query.executeUpdate();
		Query query1 = session.createQuery("update Attence set reason_id=5 where id=:id");
		query1.setInteger("id", id);
		query1.executeUpdate();
	}
	
	
	public void attenceUpdateStatu(int id , int attence_status , int reason_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Attence set attence_status=:attence_status  where id=:id");
		//引号id
		query.setInteger("id",id);
		query.setInteger("attence_status", attence_status);
		query.executeUpdate();
		
		Query query1 = session.createQuery("update Attence set reason_id=:reason_id  where id=:id");
		query1.setInteger("id", id);
		query1.setInteger("reason_id", reason_id);
		query1.executeUpdate();
	}
	
	/**
	 * 根据姓名查询考勤信息
	 * @param username
	 * @return
	 */
	public List<Attence> QueryByName(String stuName) {
		Session session = sessionFactory.getCurrentSession();
		String hql="select a.id,a.attence_status,"
				+ "s.id as sid,s.stuName,s.sex,s.personalNum, "
				+ "c.id as cid,c.classname,"
				+ "r.id as rid,r.reason,"
				+ "a.attence_time"
				+ " from Attence as a "
				+ "inner join student as s on(s.id=a.stu_id) "
				+ "inner join classinfo as c on(c.id=a.classinfo_id) "
				+ "left join reasons as r on(r.id=a.reason_id) "
				+ "where s.stuName like ? order by a.attence_time desc";
		Query query = session.createSQLQuery(hql);
		query.setString(0,"%"+stuName+"%");
		//query.setInteger(0, classinfo_id);
		List<Object[]> list = query.list();
		List<Attence> ls=new ArrayList<>();
		for (Object[] obj : list) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//考勤信息
			Attence attence=new Attence();
			attence.setId(Integer.parseInt(obj[0].toString()));
			attence.setAttence_status(Integer.parseInt(obj[1].toString()));
			try {
				attence.setAttence_time(sdf.parse(obj[10].toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//学生信息
			Student student=new Student();
			student.setId(Integer.parseInt(obj[2].toString()));
			student.setStuName(obj[3].toString());
			student.setSex(Integer.parseInt(obj[4].toString()));
			student.setPersonalNum(obj[5].toString());
			attence.setStudent(student);
			//班级信息
			Classinfo classinfo=new Classinfo();
			classinfo.setId(Integer.parseInt(obj[6].toString()));
			classinfo.setClassname(obj[7].toString());
			attence.setClassinfo(classinfo);
			//缺勤原因
			Reasons reasons=new Reasons();
			if(obj[8]!=null&&obj[8]!="") {
				reasons.setId(Integer.parseInt(obj[8].toString()));
				reasons.setReason(obj[9].toString());
				attence.setReasons(reasons);
			}
			ls.add(attence);
		}
		if(ls.size() > 0){
			return ls;
		}else{
			return null;
		}
	}
	
	
	/**
	 * 
	 * 传递 班级id 得到缺勤 的考勤记录
	 * @param classinfo_id
	 * @return
	 */
	public List<Attence> querUnAttenceList(int classinfo_id,String dateTime) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from attence  where  attence_status=2 and classinfo_id= :classinfo_id  ");
		if(dateTime!=null&&dateTime!="") {
			sql.append(" and DATE_FORMAT( attence_time,'%Y-%m-%d')=DATE_FORMAT('"+dateTime+"', '%Y-%m-%d') ");
		}else{
			sql.append(" and DATE_FORMAT( attence_time,'%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d')");
		}
		SQLQuery sQLQuery = session.createSQLQuery(sql.toString());
		
		sQLQuery.setInteger("classinfo_id", classinfo_id);

		sQLQuery.addEntity( Attence.class );
		
		return sQLQuery.list();
	}
	
	/**
	 * 
	 * 传递 班级id 得到缺勤 的考勤记录
	 * @param classinfo_id
	 * @return
	 */
	public List<Attence> querUnAttenceList(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from attence  where  attence_status=2 and classinfo_id= :classinfo_id and DATE_FORMAT( attence_time,'%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d') ");
		SQLQuery sQLQuery = session.createSQLQuery(sql.toString());
		
		sQLQuery.setInteger("classinfo_id", classinfo_id);

		sQLQuery.addEntity( Attence.class );
		
		return sQLQuery.list();
	}
	
	/**
	 * 
	 * 传递 班级id 得到缺勤 的考勤记录
	 * classinfo_id
	 * startTime
	 * endTime
	 * 
	 */
	public List<Attence> querUnAttenceListByStartTimeAndEndTime(int classinfo_id , Date startTime , Date endTime) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from attence  ");
		sql.append(" where  attence_status=2 ");
		sql.append(" and classinfo_id = :classinfo_id ");
		sql.append(" AND attence_time >= :startTime ");
		sql.append(" and attence_time <= :endTime ");
		
		SQLQuery sQLQuery = session.createSQLQuery(sql.toString());
		
		sQLQuery.setInteger("classinfo_id", classinfo_id);
		sQLQuery.setDate("startTime", startTime);
		sQLQuery.setDate("endTime", endTime);
		
		
		sQLQuery.addEntity( Attence.class );
		
		return sQLQuery.list();
	}
	
	
	/**
	 * 删除指定 classinfo_id 
	 * @return
	 */
	public int deleteClassinfo_id(int classinfo_id) {
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from attence where  classinfo_id = :classinfo_id ");
		
		
		SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
		
		sqlQuery.setInteger("classinfo_id", classinfo_id);
		
		sqlQuery.executeUpdate();
		
		return 1;
	}
	
}