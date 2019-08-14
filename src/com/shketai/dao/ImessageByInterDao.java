package com.shketai.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.ImessageByInter;
import com.shketai.entity.Interview_info;
import com.shketai.entity.Teacher;

/**
 * 站内信
 */
public class ImessageByInterDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 新增一条站内信
	 * @param imessage
	 * @return
	 */
	public void add(List<Integer> interview_info_ids,String head,String message,int teacher_id){
		if(interview_info_ids.size()>0){
			Session session = sessionFactory.getCurrentSession();
			Date now = new Date();
			for (Integer interview_info_id : interview_info_ids) {
				ImessageByInter imessageByInter = new ImessageByInter();
				Interview_info interview_info = new Interview_info();
				interview_info.setId(interview_info_id);
				imessageByInter.setInterview_info(interview_info);
				Teacher teacher = new Teacher();
				teacher.setId(teacher_id);
				imessageByInter.setTeacher(teacher);
				imessageByInter.setTime(now);
				imessageByInter.setFlag(0);
				imessageByInter.setHead(head);
				imessageByInter.setMessage(message);
				session.save(imessageByInter);
			}
		}
	}
	
	/**
	 * 学生端查看站内消息
	 * @param classinfo_id
	 * @param page
	 * @return
	 */
	public List<ImessageByInter> find(int stu_id,int page){
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("select id from Interview_info where stu_id=?");
		query1.setInteger(0, stu_id);
		//存放所有班级的id
		List<Integer> ids = query1.list();
		//这一条hql语句相当于  select * from ImessageByClass where classinfo_id in(1,2,90)
		Query query2 = session.createQuery(" from ImessageByInter where interview_info.id in(:ids) ");
		query2.setParameterList("ids",ids);
		query2.setFirstResult((page-1)*10);
		query2.setMaxResults(10);
		List<ImessageByInter> list1 = query2.list();
		if(list1.size() > 0){
			return list1;
		}else{
			return null;
		}
	}
	
	public int findCount1(int stu_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("select id from Interview_info where stu_id=?");
		query1.setInteger(0, stu_id);
		//存放所有班级的id
		List<Integer> ids = query1.list();
		//这一条hql语句相当于  select * from ImessageByClass where classinfo_id in(1,2,90)
		Query query2 = session.createQuery("select count(*) from ImessageByInter where interview_info.id in(:ids) ");
		query2.setParameterList("ids",ids);
		Long total = (Long) query2.uniqueResult();
		return total.intValue();
	}
	
	public void updateFlag(int id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update ImessageByInter set flag=1 where  id =" + id);
		System.out.println("dao:trail:" + id);
		//引号id
		query.executeUpdate();
	}
	
	public ImessageByInter findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ImessageByInter where id=?");
		query.setInteger(0, id);
		List<ImessageByInter> list = query.list();
		ImessageByInter imessageByInter = list.get(0);
		return imessageByInter;
	}
	
	public int delete(ImessageByInter imessageByInter){
		Session session = sessionFactory.getCurrentSession();
		session.delete(imessageByInter);
		return 1;
	}
	
	
	
}
