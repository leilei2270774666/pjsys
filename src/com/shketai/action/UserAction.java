package com.shketai.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shketai.entity.User;
import com.shketai.service.TeacherService;
import com.shketai.service.UserService;
import com.shketai.util.CCPSMSUtils;
import com.shketai.util.Md5;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;
	//既用了模型驱动接收参数，也用了属性驱动接收参数，模型驱动优先，所以参数在user对象里
	private User user = new User(); //一定要手动实例化
	
//	private String username;
	
	private String password;  
	
	private String yzm;
	
	private int result;
	
	private UserService userService ;
	
	
	
	/**
	 * 发送验证码
	 */
	public String sendMsg(){
		//获取随机验证码
		String number= CCPSMSUtils.getNumber();
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("yzm", number);
		//发送短信
		CCPSMSUtils.sendSms(user.getMobilephone(),number);
		result = 1;
		return SUCCESS;
	}
	
	/**
	 * 注册
	 * @return
	 */
	public String register(){
		int results = userService.findUsername(user.getMobilephone());
		System.out.println(results);
			if(results ==1){
				Map<String, Object>  session = ActionContext.getContext().getSession();
				//把用户输入的验证码和session里的验证码比较
				if(user.getYzm().equals(session.get("yzm").toString())){
					userService.register(user);
					result = 1; //1,注册成功
				}else{
					result = 2;//2,验证码不正确
				}	
			}else{
				result = 3;//3,用户名重复
			}
	
		return SUCCESS;
	}
	
	/**
	 * 重置密码
	 * @return
	 */
	public String reset(){
		Map<String, Object>  session = ActionContext.getContext().getSession();
		//把用户输入的验证码和session里的验证码比较
		
		try {
			if(user.getYzm().equals(session.get("yzm").toString())){
				userService.reset(user);
				result = 1; //1,重置成功
			}else{
				result = 2;//2,验证码不正确
			}	
		} catch (Exception e) {
			result = 3;//3,不存在手机号码
		}
		return SUCCESS;
	}
	
	
	/**
	 * 登录
	 */

	public String execute() throws Exception {
		User u = userService.login(user.getMobilephone(),user.getPassword());
		if(u!=null){
			result = 1;
			Map<String,Object> session =ActionContext.getContext().getSession();
			session.put("mobilephone", user.getMobilephone());
			session.put("id", u.getId());
			
			
			session.put("teacher_id", null);
			
			
			result = u.getId();
		}
		return SUCCESS;
	}
	/**
	 * 忘记密码
	 * @return
	 */

	public String getPassword() {
		return password;
	}

	public String updateResult(){
		userService.updateResult(user.getId());
		result = 1;
		return SUCCESS;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getYzm() {
		return yzm;
	}

	public void setYzm(String yzm) {
		this.yzm = yzm;
	}

	
	public User getModel(){
		return user;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
