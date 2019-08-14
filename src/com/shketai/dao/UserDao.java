package com.shketai.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shketai.entity.User;

public class UserDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public int register(User user){
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		return 1;
	}

	public int reset(User user){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where mobilephone=:mobilephone");
		query.setString("mobilephone", user.getMobilephone());
		List<User> list = query.list();
		User oldUser = list.get(0);
		user.setId(oldUser.getId());
		user.setMobilephone(oldUser.getMobilephone());
		session.clear();
		session.update(user);
		return 1;
	}
	
	/**
	 * 验证验证码是否正确
	 * @param yzm
	 * @return
	 */
	public User findyzm(String yzm) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class, yzm);
		return user;
	}

	public int findUsername(String mobilephone){
		Session session = sessionFactory.getCurrentSession();
		String hql = "from User where mobilephone=:mobilephone ";
		Query query = session.createQuery(hql);
		query.setString("mobilephone", mobilephone);
		List<User> list = query.list();
		if(list.size()==1){
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User login(String mobilephone,String password){
		Session session = sessionFactory.getCurrentSession();
		String hql = "from User where mobilephone=:mobilephone and password=:password";
		Query query = session.createQuery(hql);
		query.setString("mobilephone", mobilephone);
		query.setString("password", password);
		List<User> list = query.list();
		if(list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
	public void updateResult(int id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update User set flag=:flag where id=:id");
		query.setString("flag", "已确认过报名须知！");
		query.setInteger("id", id);
		query.executeUpdate();
	}
	
	/**
	 * 查询所有家长的id集合
	 * @return
	 */
	public List<Integer> findAllIds(){
		String hql = "select id from User";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Integer> list = query.list();
		return list;
	}
}
