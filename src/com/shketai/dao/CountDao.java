package com.shketai.dao;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CountDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 报名人数
	 * @return
	 */
	public int countStudent(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Student ");
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}
	
	public int countStudentOfInfo(){
		Session session = sessionFactory.getCurrentSession();
		SQLQuery sqlquery = session.createSQLQuery("  select SUM(number-num)  from classinfo  ");
		BigDecimal total = (BigDecimal ) sqlquery.uniqueResult();
		return total.intValue();
	}
	
	/**
	 * 学科总数
	 * @return
	 */
	public int countSubject(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Subject ");
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}
	
	/**
	 * 班级总数
	 * @return
	 */
	public int countSubjectClassinfo(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Classinfo ");
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}
	
	/**
	 * 各科男女比例
	 */
	
	public String countpercent1(){
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=1 and t.major_id=1 ");
		BigInteger total1 = (BigInteger) query1.uniqueResult();
		Query query2 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=2 and t.major_id=1");
		BigInteger total2 = (BigInteger) query2.uniqueResult();
		String result =  total1.intValue()+":"+total2.intValue();
		return result;
	}
	
	public String countpercent2(){
			Session session = sessionFactory.getCurrentSession();
			Query query1 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=1 and t.major_id=2 ");
			BigInteger total1 = (BigInteger) query1.uniqueResult();
			Query query2 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=2 and t.major_id=2");
			BigInteger total2 = (BigInteger) query2.uniqueResult();
			String result =  total1.intValue()+":"+total2.intValue();
			return result;
		}
		
	
	public String countpercent3(){
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=1 and t.major_id=3 ");
		BigInteger total1 = (BigInteger) query1.uniqueResult();
		Query query2 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=2 and t.major_id=3");
		BigInteger total2 = (BigInteger) query2.uniqueResult();
		String result =  total1.intValue()+":"+total2.intValue();
		return result;
	}
	
	
	public String countpercent4(){
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=1 and t.major_id=4");
		BigInteger total1 = (BigInteger) query1.uniqueResult();
		Query query2 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=2 and t.major_id=4");
		BigInteger total2 = (BigInteger) query2.uniqueResult();
		String result =  total1.intValue()+":"+total2.intValue();
		return result;
	}
	
	
	
	public String countpercent5(){
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=1 and t.major_id=5 ");
		BigInteger total1 = (BigInteger) query1.uniqueResult();
		Query query2 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=2 and t.major_id=5");
		BigInteger total2 = (BigInteger) query2.uniqueResult();
		String result =  total1.intValue()+":"+total2.intValue();
		return result;
	}
	
	
	
	public String countpercent6(){
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=1 and t.major_id=6");
		BigInteger total1 = (BigInteger) query1.uniqueResult();
		Query query2 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=2 and t.major_id=6");
		BigInteger total2 = (BigInteger) query2.uniqueResult();
		String result =  total1.intValue()+":"+total2.intValue();
		return result;
	}
	
	
	
	public String countpercent7(){
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=1 and t.major_id=7 ");
		BigInteger total1 = (BigInteger) query1.uniqueResult();
		Query query2 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=2 and t.major_id=7");
		BigInteger total2 = (BigInteger) query2.uniqueResult();
		String result =  total1.intValue()+":"+total2.intValue();
		return result;
	}
	
	
	
	public String countpercent8(){
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=1 and t.major_id=8 ");
		BigInteger total1 = (BigInteger) query1.uniqueResult();
		Query query2 = session.createSQLQuery("select count(*) from Info i, student s ,classinfo c ,`subject` t ,majors m where s.id=i.stu_id AND i.classinfo_id =c.id AND t.major_id = m.id and c.subject_id =t.id AND s.sex=2 and t.major_id=8");
		BigInteger total2 = (BigInteger) query2.uniqueResult();
		String result =  total1.intValue()+":"+total2.intValue();
		return result;
	}

	public int CountStudentByClassInfoId(int classinfo_id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Info  where classinfo_id=?");
		query.setInteger(0, classinfo_id);
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}
	
}
