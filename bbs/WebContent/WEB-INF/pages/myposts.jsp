<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的帖子</title>
<link rel="stylesheet" href="css/style1.css" type="text/css" /> 
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#delete").click(function(){
			var flag = true;
			flag = confirm("确定要删除吗?");
			if(flag){
				return true;
			}
			return false;
		});
		$("#pageNo").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			//1. 校验 val 是否为数字 1, 2, 而不是 a12, b
			var flag = false;
			var reg = /^\d+$/g;
			var pageNo = 0;
			if(reg.test(val)){
				//2. 校验 val 在一个合法的范围内： 1-totalPageNumber
				pageNo = parseInt(val);
				if(pageNo >= 1 && pageNo <= parseInt("${postpage.totalPageNumber }")){
					flag = true;
				}
			}
			if(!flag){
				alert("输入的不是合法的页码.");
				$(this).val("");
				return;
			}
			//3. 页面跳转
			var href = "postServlet?method=getMyPosts&pageNo=" + pageNo;
			window.location.href = href;
		});
	});
</script>
</head>
<body class="whole" style="padding-left:20px">
	<div class="banner" style="padding-left:20px;">
	<a href="typeServlet?method=toMain"><FONT SIZE="7" COLOR="white"><B>BBS论坛</B></FONT></a>
	</div>
	<div class="guide">
		&raquo; 当前用户:&nbsp;<b class="flip">${sessionScope.username }</b>&nbsp; | &nbsp;
		<a href="postServlet?method=JumpToSendPost">发帖</a>&nbsp; | &nbsp;
		<a href="userServlet?method=quit&pageNo=${postpage.pageNo }" id="quit">退出</a>
	</div><br><br>
	<table class="border" cellspacing="0" cellpadding="10" border="1" width="1300">
		<tr>
			<td>标号</td>
			<td>标题</td>
			<td>创建时间</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${postpage.list }" var="post">
			<tr>
			<td>${post.postId }</td>
			<td><a href="postServlet?method=showMyPost&postId=${post.postId }">${post.title }</a></td>
			<td>${post.createTime }</td>
			<td>
			<a href = "postServlet?method=alterMyPosts&postId=${post.postId }">修改</a>
			&nbsp; | &nbsp;
			<a id = "delete" href="postServlet?method=deleteMyPost&postId=${post.postId }">删除</a>
			</td>
			</tr>
		</c:forEach>
	</table>
	<center>
		<br><br>
		共 ${postpage.totalPageNumber } 页
		&nbsp;&nbsp;
		当前第 ${postpage.pageNo } 页		
		&nbsp;&nbsp;
		
	<c:if test="${postpage.hasPrev }">
		<a href="postServlet?method=getMyPosts&pageNo=1">首页</a>
		&nbsp;&nbsp;
		<a href="postServlet?method=getMyPosts&pageNo=${postpage.prevPage }">上一页</a>
	</c:if>
	&nbsp;&nbsp;
	<c:if test="${postpage.hasNext }">
		<a href="postServlet?method=getMyPosts&pageNo=${postpage.nextPage }">下一页</a>
		&nbsp;&nbsp;
		<a href="postServlet?method=getMyPosts&pageNo=${postpage.totalPageNumber }">末页</a>
	</c:if>
	&nbsp;&nbsp;
	转到 <input type="text" size="1" id="pageNo"/> 页
	</center>
</body>
</html>