package bbs.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.domain.User;
import bbs.service.UserService;
import bbs.web.TokenProcessor;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodStr = request.getParameter("method");
		
		try {
			Method method = getClass().getDeclaredMethod(methodStr, HttpServletRequest.class,HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected void alterPwdValidate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userIdStr = request.getParameter("userId");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String firstPwd = request.getParameter("firstPwd");
		String phone = request.getParameter("phone");
		int userId = -1;
		
		try {
			userId = Integer.parseInt(userIdStr);
		} catch (Exception e) {}
		
		if(userId > 0){
			User user = userService.getUser(userId);
			String pwd = user.getPassword();
			if(!pwd.equals(firstPwd)){
				request.setAttribute("user", user);
				request.setAttribute("message","原始密码错误，请重新输入");
				request.getRequestDispatcher("/WEB-INF/pages/user-alterpwd.jsp").forward(request, response);
				return;
				
			}else{
				User userNew = new User(userId, name, password, phone);
				userService.alterUser(userNew);
				
				request.getSession().invalidate();
				
				request.getRequestDispatcher("/WEB-INF/pages/alter-user-success.jsp").forward(request, response);
				
			}
		}else{
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
		
	
	protected void alterPassWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = (String) request.getSession().getAttribute("username");
		User user = userService.getUserByName(userName);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/pages/user-alterpwd.jsp").forward(request, response);
	}
		
	
	/**
	 * 退出登录
	 */
	protected void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("typeServlet?method=toMain&pageNo="+pageNo).forward(request, response);
	}
	
	/**
	 * 注册新用户
	 */
	protected void jumpToRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/regist.jsp").forward(request, response);
	}
	
	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
//		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password1");
		
		User user = new User(null, name, password, phone, new Date(new java.util.Date().getTime()));
		boolean valid = TokenProcessor.getInstance().isTokenValid(request);
		if(valid){
			TokenProcessor.getInstance().resetToken(request);
		}else{
//			response.sendRedirect(request.getContextPath() + "/error.jsp");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		long id = userService.registUser(user);
		if(id > 0){
			request.setAttribute("name", user.getName());
//			request.getRequestDispatcher("typeServlet?method=toMain").forward(request, response);
			request.getRequestDispatcher("/WEB-INF/pages/regist-success.jsp").forward(request, response);
//			response.sendRedirect("/WEB-INF/pages/regist-success.jsp");
		}
	}
	
	protected void jumpToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}
		
	
	@SuppressWarnings("finally")
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		String name = null;
		String pwd = null;
		
		try{
			User user = userService.getUserByName(userName);
			name = user.getName();
			pwd = user.getPassword();
			if(!passWord.equals(pwd)){
				request.setAttribute("message", "用户名或密码错误");
				request.setAttribute("tempname", name);
				request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
				return;
			}
			HttpSession session = request.getSession();
			session.setAttribute("username", userName);
			request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			request.setAttribute("message", "用户名或密码错误");
			request.setAttribute("tempname", name);
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
			return;
		}
		
//		boolean flag = false;
		
//		if(user == null){
//			request.setAttribute("message", "用户名错误");
//			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
//			return;
//		}
		
	}
		
}
