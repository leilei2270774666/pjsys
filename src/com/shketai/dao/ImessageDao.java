package com.shketai.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.Attence;
import com.shketai.entity.Classinfo;
import com.shketai.entity.Imessage;
import com.shketai.entity.Reasons;
import com.shketai.entity.Student;
import com.shketai.entity.Teacher;
import com.shketai.entity.User;


public class ImessageDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 新增一条站内信
	 * @param imessage
	 * @return
	 */
	public void add(List<Integer> user_ids,String head,String message,int teacher_id){
		if(user_ids.size()>0){
			Session session = sessionFactory.getCurrentSession();
			Date now = new Date();
			
			for (Integer user_id : user_ids) {
				Imessage imessage = new Imessage();
				User user = new User();
				user.setId(user_id);
				imessage.setUser(user);
				Teacher teacher = new Teacher();
				teacher.setId(teacher_id);
				imessage.setTeacher(teacher);
				imessage.setTime(now);
				imessage.setFlag(0);
				imessage.setHead(head);
				imessage.setMessage(message);
				session.save(imessage);
			}
		}
		
	}
	
	
	public List<Imessage> find(int user_id,int page){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Imessage where user_id=?  order by id desc");
		query.setInteger(0, user_id);
		query.setFirstResult((page-1)*10);
		query.setMaxResults(10);
		List<Imessage> list = query.list();
		if(list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
	
	/**
	 *   学生端查看站内信 前三条
	 */
	public List<Imessage> queryFirstThreeByImessage(int user_id){
		
		Session session = sessionFactory.getCurrentSession();
		
//		Query query = session.createQuery("from Imessage where user_id=?  order by id desc");
		Query query = session.createSQLQuery("select id,head,message,time,teacher_id from Imessage where user_id=:id union all (select id,head,message,time,teacher_id from imessagebyclass where classinfo_id in(\n" + 
				"\n" + 
				"select classinfo_id from info where stu_id in(select id from student where user_id=:id))  GROUP By classinfo_id) union all(select id,head,message,time,teacher_id from imessagebyinter where interview_info_id in(select id from Interview_info where stu_id=:id)) ORDER BY time desc limit 3");
		query.setInteger("id", user_id);
		/*query.setFirstResult(0);
		query.setMaxResults(3);*/
		List<Imessage> list = query.list();
		if(list.size() > 0){
			return list;
		}else{
			return null;
		}
		
	}
	
	public int findCount1(int user_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Imessage where user_id=?");
		query.setInteger(0, user_id);
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}
	public void updateFlag(int id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Imessage set flag=1 where  id =" + id);
		System.out.println("dao:trail:" + id);
		//引号id
		query.executeUpdate();
	}
	
	public void updateFlagBySelect(List<Integer> ids, int flag) {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("update Imessage set flag=:flag where id in (:ids)");
			//根据名字设置参数
			query.setInteger("flag", flag);
			query.setParameterList("ids", ids);
			query.executeUpdate();
			
		}
	public Imessage findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Imessage where id=?");
		query.setInteger(0, id);
		List<Imessage> list = query.list();
		Imessage imessage = list.get(0);
		return imessage;
	}
	
	public int delete(Imessage imessage){
		Session session = sessionFactory.getCurrentSession();
		session.delete(imessage);
		return 1;
	}
	/**
	 * 多选删除
	 * @param ids
	 * @return
	 */
	public int deleteAll(List<Integer> ids){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Imessage  where id in (:ids)");
		//根据名字设置参数
		query.setParameterList("ids", ids);
		query.executeUpdate();
		return 1;
	}
	
	public int findCount(int user_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Imessage where flag=0 and user_id=?");
		query.setInteger(0, user_id);
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}

	/**
	 * 
	 * sql add
	 * @param head
	 * @param message
	 * @param flag
	 * @param user_id
	 * @param teacher_id
	 * @param time
	 * @return
	 */
	public int sqlAdd(String head ,String message,int flag ,int user_id , int teacher_id ,Date time) {
		
		Session session = sessionFactory.getCurrentSession();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" insert into imessage (head ,message, flag ,user_id ,teacher_id , time  )  ");
		sql.append(" values (:head ,:message, :flag ,:user_id ,:teacher_id , :time) ");
		
		SQLQuery query = session.createSQLQuery(sql.toString());
		
		query.setString("head", head);
		query.setString("message", message);
		query.setInteger("flag", flag);
		query.setInteger("user_id", user_id);
		query.setInteger("teacher_id", teacher_id);
		query.setDate("time", time);
		
		return query.executeUpdate();
	}

}
