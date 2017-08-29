package bbs.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jmx.snmp.Timestamp;

import bbs.domain.Reply;
import bbs.domain.User;
import bbs.service.ReplyService;
import bbs.service.UserService;

public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();
	private ReplyService replyService = new ReplyService();

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

	protected void addReplyToMysql(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String postIdStr = request.getParameter("postId");
		// String replyIdStr = request.getParameter("replyId");
		String content = request.getParameter("replyContent");
		String pageNoStr = request.getParameter("pageNo");
		String typeIdStr = request.getParameter("typeId");
		int pageNo = 1;
		int postId = 1;
		int typeId = 1;
	
		try {
			postId = Integer.parseInt(postIdStr);
			pageNo = Integer.parseInt(pageNoStr);
			typeId = Integer.parseInt(typeIdStr);
		} catch (Exception e) {
		}

		User user = userService.getUserByName(userName);
		int userId = user.getUserId();
		System.out.println(userName);
		System.out.println(postId);
		System.out.println(userId);
		System.out.println(content);
		System.out.println(typeId);
		Date createTime = new Date(new java.util.Date().getTime());
		if (content != null) {
			String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(new java.util.Date().getTime()));
			//new Date(new java.util.Date().getTime())
			Reply reply = new Reply(content, java.sql.Timestamp.valueOf(nowTime ), postId, userId,typeId);
			replyService.addReply(reply);
			
			request.getRequestDispatcher("postServlet?method=getPost&postid="+postId+"&pageNo="+pageNo).forward(request, response);
			return;
//			String result = "<tr><td>"+userId+"</td><td>"+content+"content</td><td>"+createTime+"</td></tr>";
//			// <tr><td>"+userId+"</td><td>"+content+"content</td><td>"+createTime+"</td></tr>
//			response.setContentType("text/html");
//			response.getWriter().print(result);
//			return;
		}
	}

}
