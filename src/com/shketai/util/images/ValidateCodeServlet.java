package com.shketai.util.images;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest reqeust,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		//禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		HttpSession session = reqeust.getSession();
		
		ValidateCode vCode = new ValidateCode(120,40,5,100);
		//生成验证码，存放到session里
		session.setAttribute("code", vCode.getCode());
		vCode.write(response.getOutputStream());
	}
/**
 * web.xml 添加servlet
	<servlet>
		<servlet-name>validateCodeServlet</servlet-name>
		<servlet-class>cn.dsna.util.images.ValidateCodeServlet</servlet-class>
	</servlet>	
	<servlet-mapping>
		<servlet-name>validateCodeServlet</servlet-name>
		<url-pattern>*.images</url-pattern>
	</servlet-mapping>

在地址栏输入XXX/dsna.images 测试
 */

}