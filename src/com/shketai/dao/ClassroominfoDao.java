package com.shketai.dao;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.ClassRoom;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Classroominfo;
import com.shketai.entity.Interview;
import com.shketai.entity.Interview_timeslot;

public class ClassroominfoDao {
	
	private SessionFactory sessionFactory;
	
	private ClassroomDao classroomDao;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	public ClassroomDao getClassroomDao() {
		return classroomDao;
	}



	public void setClassroomDao(ClassroomDao classroomDao) {
		this.classroomDao = classroomDao;
	}



	/**
	 * 查询所有教室时间段
	 * @return
	 */
	public List<Classroominfo> findAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Classroominfo");
		/*query.setFirstResult((page-1)*10);
		query.setMaxResults(10);*/
		List<Classroominfo> list = query.list();
		return list;
	}
	
	public List<Classroominfo> findAll1(int classroom_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Classroominfo where classroom_id=?");
		query.setInteger(0, classroom_id);
		List<Classroominfo> list = query.list();
		if(list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	
	/**
	 * 新增教室时间段
	 * @param classroominfo
	 * @return
	 */
	public int add(Classroominfo classroominfo,int classroom_id){
		Session session = sessionFactory.getCurrentSession();
		ClassRoom classroom = new ClassRoom();
		classroom.setId(classroom_id);
		classroominfo.setClassRoom(classroom);
		session.save(classroominfo);
		return 1;
	}

	public double  getHour(String start,String jieshu) throws ParseException{
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		  Date date1 = sdf.parse(start);
		  Date date2 = sdf.parse(jieshu);
		  long time1 = date1.getTime();
		  long time2 = date2.getTime();
		  return (time2-time1) * 1.0 /3600000;
		}
	
	/**
	 * 教室的占用率
	 * @return
	 * @throws ParseException 
	 */
	public String  percent(int classroom_id) throws ParseException {
		double results = 0;
		double sum = 0;
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT c2.`start`,c2.jieshu from Classinfo c , classroominfo c2 where c.classroominfo_id = c2.id AND c2.classroom_id=?";
		Query query = session.createSQLQuery(hql);
		query.setInteger(0, classroom_id);
		List<Object[]> list = query.list();
		List<Classroominfo> cis = new ArrayList<Classroominfo>();
		for (Object[] array : list) {
			String start = (String) array[0];
			String jieshu = (String) array[1];
			Classroominfo ci = new Classroominfo();
			//ci.setId(Integer.parseInt(array[0].toString()));
			ci.setStart(array[0].toString());
			ci.setJieshu(array[1].toString());
			cis.add(ci);
			double result = getHour(start, jieshu);
			sum = sum + result;
		}
		results = sum/16;
		DecimalFormat df = new DecimalFormat("0.00%"); 
		return df.format(results);
	}
			
	
	/**
	 * id 查询
	 * @return
	 */
	public Classroominfo findId(int id){
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(" select id , start , jieshu , day , classroom_id from Classroominfo where id = :id ");
		
		query.setInteger("id", id);
		
		Classroominfo classroominfo = new Classroominfo();
		
		List<Object[]> list = query.list();
		if(list.size()>0) {
			Object[] obj = list.get(0);
			
			int ids = Integer.parseInt(obj[0].toString());
			
			 String start = obj[1].toString();
			
			 String jieshu = obj[2].toString();
			
			 int day = Integer.parseInt(obj[3].toString());			
			 
			 int classroom_id = Integer.parseInt(obj[4].toString());
			 
			 classroominfo.setId(ids);
			 classroominfo.setStart(start);
			 classroominfo.setJieshu(jieshu);
			 classroominfo.setDay(day);
			
			 ClassRoom classRoom = classroomDao.findId(classroom_id);
			 classroominfo.setClassRoom(classRoom);
			 
		}
		
		return classroominfo;
	}
	
	
	
}
