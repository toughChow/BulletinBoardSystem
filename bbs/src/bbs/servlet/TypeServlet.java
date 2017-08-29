package bbs.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.domain.Post;
import bbs.domain.Type;
import bbs.service.TypeService;

public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TypeService typeService = new TypeService();

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
	
	protected void toMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Post post = new Post();
		long count = 0;
		List<Type> type = typeService.getTypeList();
//		List<Type> typeCount = null;
		for(Type t : type){
			count = typeService.getCountByTypeId(t.getTypeId());
			post = typeService.getPostByTypeId(t.getTypeId());
			
			t.setCount(count);
			System.out.println(count);
			t.setPost(post);
		}
//		count = typeService.getCountByTypeId(1);
		
		request.setAttribute("type", type);
//		request.setAttribute("adminname", "");
		
		request.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(request, response);
	}
		

}
