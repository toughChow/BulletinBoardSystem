package bbs.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.domain.Admin;
import bbs.domain.Post;
import bbs.domain.Reply;
import bbs.domain.Type;
import bbs.domain.User;
import bbs.service.AdminService;
import bbs.service.PostService;
import bbs.service.ReplyService;
import bbs.service.TypeService;
import bbs.service.UserService;
import bbs.web.Page;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService = new AdminService();
	private PostService postService = new PostService();
	private UserService userService = new UserService();
	private ReplyService replyService = new ReplyService();
	private TypeService typeService = new TypeService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String methodStr = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodStr,
					HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void deleteType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String typeIdStr = request.getParameter("typeId");
		int typeId = -1;
		try {
			typeId = Integer.parseInt(typeIdStr);
		} catch (Exception e) {}
		typeService.delete(typeId);
		toAdminTypes(request, response);
	}
	
	protected void toAdminTypes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Type> type = typeService.getTypeList();
		request.setAttribute("type", type);
		request.getRequestDispatcher("/WEB-INF/pages/admin-types.jsp").forward(request, response);
	}	

	
	protected void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userIdStr = request.getParameter("userId");
		int userId = -1;
		try {
			userId = Integer.parseInt(userIdStr);
		} catch (Exception e) {}
		userService.deleteUser(userId);
		toAdminUsers(request, response);
	}
	
	protected void toAdminUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<User> user = userService.getUser();
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/pages/admin-users.jsp").forward(request, response);
	}
	
	protected void toAdminPosts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String postNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		try{
			pageNo = Integer.parseInt(postNoStr);
		}catch(Exception e){}
		
		Page<Post> post = postService.getPage(pageNo);
		List<Type> type = typeService.getTypeList();
		request.setAttribute("type", type);
		request.setAttribute("postpage", post);
		request.getRequestDispatcher("/WEB-INF/pages/admin-posts.jsp").forward(request, response);
	}
	
	
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String postIdStr = request.getParameter("postId");
		String pageNoStr = request.getParameter("pageNo");
		int postId = 0;
		int pageNo = 1;
		try {
			postId = Integer.parseInt(postIdStr);
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		postService.deletePost(postId);
		request.setAttribute("pageNo", pageNo);
		
		toAdminPosts(request, response);
	}
	
	
	protected void getPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postIdStr = request.getParameter("postid");
		String pageNoStr = request.getParameter("pageNo");
		int postId = 1;
		int pageNo =1;
		int userId = 1;
		try {
			postId = Integer.parseInt(postIdStr);
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获取帖子信息
		Post post = postService.getPost(postId);
		userId = post.getUserId();
		//获取帖子用户名信息
		User user = userService.getUser(userId);
		//传入留言板内容
		List<Reply> reply = replyService.getReplyForList(postId);
		
		request.setAttribute("reply", reply);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("user", user);
		request.setAttribute("post", post);
		request.getRequestDispatcher("/WEB-INF/pages/admin-showpost.jsp").forward(request, response);
	}
	
	protected void getPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		try{
			pageNo = Integer.parseInt(postNoStr);
		}catch(Exception e){}
		
		Page<Post> post = postService.getPage(pageNo);
		List<Type> type = typeService.getTypeList();
		request.setAttribute("type", type);
		request.setAttribute("postpage", post);
		request.getRequestDispatcher("/WEB-INF/pages/admin-main.jsp").forward(request, response);
	}

	protected void quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()
				+ "/typeServlet?method=toMain");
	}

	protected void toMain(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String postNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(postNoStr);
		} catch (Exception e) {}
		Page<Post> post = postService.getPage(pageNo);
		List<Type> type = typeService.getTypeList();
		request.setAttribute("type", type);
		request.setAttribute("postpage", post);
		request.getRequestDispatcher("/WEB-INF/pages/admin-main.jsp").forward(
				request, response);
	}


	/**
	 * 管理员登录
	 */
	@SuppressWarnings({ "finally" })
	protected void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		String pwd = null;
		Admin admin = null;
		String name = null;
		try {
			admin = adminService.getUserByName(userName);
			name = admin.getName();
			pwd = admin.getPassword();
			if (!passWord.equals(pwd)) {
				request.setAttribute("message", "用户名或密码错误");
				request.setAttribute("tempname", name);
				request.getRequestDispatcher("/WEB-INF/pages/admin-login.jsp")
						.forward(request, response);
				return;
			}
			HttpSession session = request.getSession();
			session.setAttribute("adminname", name);
			request.getRequestDispatcher(
					"/WEB-INF/pages/admin-login-success.jsp").forward(request,
					response);
		} catch (Exception e) {
		} finally {
			request.setAttribute("message", "用户名或密码错误");
			request.setAttribute("tempname", name);
			request.getRequestDispatcher("/WEB-INF/pages/admin-login.jsp")
					.forward(request, response);
			return;
		}
	}

	protected void toLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/admin-login.jsp").forward(
				request, response);
	}

}
