package bbs.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.domain.Post;
import bbs.domain.Reply;
import bbs.domain.Type;
import bbs.domain.User;
import bbs.service.PostService;
import bbs.service.ReplyService;
import bbs.service.TypeService;
import bbs.service.UserService;
import bbs.web.Page;

public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 声明service
	 */
	private PostService postService = new PostService();
	private UserService userService = new UserService();
	private ReplyService replyService = new ReplyService();
	private TypeService typeService = new TypeService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodStr = request.getParameter("method");

		try {
			Method method = getClass().getDeclaredMethod(methodStr, HttpServletRequest.class,
					HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void deleteMyPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postIdStr = request.getParameter("postId");
		int postId = 1;
		try {
			postId = Integer.parseInt(postIdStr);
		} catch (Exception e) {
		}

		postService.deletePost(postId);

		getMyPosts(request, response);
	}

	/**
	 * 修改帖子信息成功
	 */
	protected void alterSuccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postIdStr = request.getParameter("postId");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String typeIdStr = request.getParameter("typeId");
		int typeId = 1;
		int postId = 1;
		try {
			postId = Integer.parseInt(postIdStr);
			typeId = Integer.parseInt(typeIdStr);
		} catch (Exception e) {
		}
		String userName = (String) request.getSession().getAttribute("username");
		User user = postService.getUserByName(userName);
		int userId = user.getUserId();

		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(new java.util.Date().getTime()));
		Timestamp createTime = java.sql.Timestamp.valueOf(nowTime);

		Post post = new Post(postId, title, content, createTime, userId, typeId);
		post.setUserId(userId);

		postService.alterPost(post);
		request.setAttribute("user", user);
		request.setAttribute("postId", postId);
		request.setAttribute("post", post);
		showMyPost(request, response);
	}

	protected void showMyPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postIdStr = request.getParameter("postId");
		int postId = 1;
		int userId = 1;
		try {
			postId = Integer.parseInt(postIdStr);
		} catch (Exception e) {
		}
		// 获取帖子信息
		Post post = postService.getPost(postId);
		userId = post.getUserId();
		// 获取帖子用户名信息
		User user = userService.getUser(userId);
		request.setAttribute("user", user);
		request.setAttribute("post", post);
		request.getRequestDispatcher("/WEB-INF/pages/show-mypost.jsp").forward(request, response);
	}

	/**
	 * 修改帖子信息
	 */
	protected void alterMyPosts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postIdStr = request.getParameter("postId");
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		int postId = 0;
		try {
			postId = Integer.parseInt(postIdStr);
		} catch (Exception e) {
		}
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Post post = postService.getPost(postId);
		List<Type> type = typeService.getTypeList();

		request.setAttribute("post", post);
		request.setAttribute("type", type);
		request.setAttribute("pageNo", pageNo);

		request.getRequestDispatcher("/WEB-INF/pages/alter-post.jsp").forward(request, response);
	}

	protected void getMyPosts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNoStr = request.getParameter("pageNo");
		String name = (String) request.getSession().getAttribute("username");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		System.out.println(pageNo);
		int userId = 0;
		User user = userService.getUserByName(name);
		userId = user.getUserId();
		// System.out.println(userId);
		Page<Post> post = postService.getMyPosts(pageNo, userId);
		request.setAttribute("postpage", post);
		request.getRequestDispatcher("/WEB-INF/pages/myposts.jsp").forward(request, response);
	}

	protected void addPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long check = -1;
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String typeIdStr = request.getParameter("typeId");
		int typeId = 1;
		try {
			typeId = Integer.parseInt(typeIdStr);
		} catch (Exception e) {
		}
		String userName = (String) request.getSession().getAttribute("username");
		User user = postService.getUserByName(userName);
		int userId = user.getUserId();

		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(new java.util.Date().getTime()));
		Timestamp createTime = java.sql.Timestamp.valueOf(nowTime);

		Post post = new Post(title, content, createTime, userId, typeId);
		post.setUserId(userId);
		check = postService.addPost(post);
		if (check > 0) {
			Type type = postService.getTypeByTypeId(typeId);
			request.setAttribute("typeId", typeId);
			request.setAttribute("typeName", type.getTypeName());
			getPostsByTypeId(request, response);
		} else {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	// JumpToSendPost
	protected void JumpToSendPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Type> type = typeService.getTypeList();
		request.setAttribute("type", type);
		request.getRequestDispatcher("/WEB-INF/pages/sendPost.jsp").forward(request, response);
	}

	protected void getPostsByTypeId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postNoStr = request.getParameter("pageNo");
		String typeIdStr = request.getParameter("typeId");
		String typeName = request.getParameter("typeName");
		int pageNo = 1;
		int typeId = 1;

		try {
			pageNo = Integer.parseInt(postNoStr);
		} catch (Exception e) {}
		try {
			typeId = Integer.parseInt(typeIdStr);
		} catch (Exception e) {}
		System.out.println("CURRENT TYPEID:"+ typeId);
		long start = System.currentTimeMillis();
		
		Page<Post> post = postService.getPostByTypeId(typeId,pageNo);
		long end = System.currentTimeMillis();
		long cost = end - start;
		System.out.println("所花费时间：" + cost);
		request.setAttribute("typeName", typeName);
		request.setAttribute("postpage", post);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("typeId", typeId);
		request.getRequestDispatcher("/WEB-INF/pages/type-post.jsp").forward(request, response);
	}

	/**
	 * 获取单个帖子详细内容
	 */
	protected void getPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postIdStr = request.getParameter("postid");
		String pageNoStr = request.getParameter("pageNo");
		int postId = 1;
		int pageNo = 1;
		int userId = 1;
		try {
			postId = Integer.parseInt(postIdStr);
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long start = System.currentTimeMillis();
		
		// 获取帖子信息
		Post post = postService.getPost(postId);
		userId = post.getUserId();
		// 获取帖子用户名信息
		User user = userService.getUser(userId);
		// 传入留言板内容
		List<Reply> reply = replyService.getReplyForList(postId);

		long end = System.currentTimeMillis();
		long cost = end - start;
		System.out.println("所花费时间：" + cost);
		request.setAttribute("reply", reply);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("user", user);
		request.setAttribute("post", post);
		request.getRequestDispatcher("/WEB-INF/pages/showpost.jsp").forward(request, response);
	}

	protected void getPosts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(postNoStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long start = System.currentTimeMillis();
		Page<Post> post = postService.getPage(pageNo);
		long end = System.currentTimeMillis();
		long cost = end - start;
		System.out.println("所花费时间：" + cost);
		request.setAttribute("postpage", post);
		request.getRequestDispatcher("/WEB-INF/pages/type-post.jsp").forward(request, response);
	}

}
