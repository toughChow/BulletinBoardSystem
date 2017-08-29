package bbs.ajax.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bbs.domain.User;
import bbs.service.UserService;

/**
 * Servlet implementation class ValidateRegistServlet
 * 使用Ajax的post方式
 */
public class ValidateRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private UserService userService = new UserService();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodStr = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodStr, HttpServletRequest.class,HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request,response);
		} catch (Exception e) {}
	}
	
	protected void validatePhone(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String result= null;
		User user = userService.getUserByPhone(phone);
		//var reg = /^((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8}$/;
		String reg = "^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}";
		
//		Pattern pattern = Pattern.compile(reg);
		
		if(user != null){
			result = "<font color='red'>该手机已被使用</font>";
		}else if(!phone.matches(reg)){
			result= "<font color='red'>该手机号码格式错误</font>";
		}else{
			result = "<font color='green'>该手机未被使用</font>";
		}
		
//		else if(phone.length() != 11){
//			result= "<font color='red'>该手机号码格式错误</font>";
//		}
//		Gson gson = new Gson();
//		String jsonStr = gson.toJson(result);
//		response.setContentType("text/javascript");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(result);
		
	}
	
	protected void validateUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String result = null;
		User user = userService.getUserByName(userName);
		
		String reg = "^[A-Za-z][A-Za-z1-9_-]+$";
		
		
		if(userName.length() < 6 || userName.length() > 15){
			result = "<font color='red'>用户名必须由6-15位组成</font>";
		}else if(!userName.matches(reg)){
			result = "<font color='red'>用户名必须以字母开头</font>";
		}else if(user == null){
			result = "<font color='green'>该用户名未被使用</font>";
		} else {
			result = "<font color='red'>该用户名已被使用</font>";
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(result);
		
	}

}
