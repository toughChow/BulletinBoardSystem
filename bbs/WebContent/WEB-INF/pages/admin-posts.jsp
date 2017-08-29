<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BBS论坛 - powered by servlet</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#quit").click(function(){
			var flag = true;
			flag = confirm("确定要退出登录吗?");
			if(flag){
				return true;
			}
			return false;
		});
		
		$("#deleteVal").click(function(){
			var flag = true;
			flag = confirm("确定要退出登录吗?");
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
			var href = "adminServlet?method=getPosts&pageNo=" + pageNo;
			window.location.href = href;
		});
	});	
</script>
</head>
<body class="whole" style="padding-left:20px">
	<div class="banner" style="padding-left:18px;"><a href="adminServlet?method=toMain"><FONT SIZE="7" COLOR="white"><B>BBS论坛</B></FONT></a></div>
	<div class="guide" style="padding-left:20px;border-bottom:8px solid #dbecF4;background:#B1D3E0;text-align:left;color:#004c7d;padding:5px 7px 3px 7px;">
		<c:if test="${sessionScope.adminname != null}">
		&nbsp; | &nbsp;当前管理员:&nbsp;${sessionScope.adminname }&nbsp; | &nbsp; <a id="quit" href="adminServlet?method=quit">退出</a>&nbsp; | &nbsp; 
		</c:if>
	</div>
	<br>
	<div class="t1">
		<table cellspacing="0" cellpadding="10" border="1" bordercolor="#96CDCD" width="1300">
			<tr><td>公告板</td></tr>
			<tr>
				<td>文明水贴，共创优良环境</td>
			</tr>
		</table>
	</div><br>
	<form>
		<table class="border" cellspacing="0" cellpadding="10" border="1" width="1300">
			<tr>
				<td>序号</td>
				<td>标题</td>
				<td>日期</td>
				<td>帖子类型</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${postpage.list }" var="post">
			
				<tr>
					<td>${post.postId }<input type="hidden" value=${post.typeId }/></td>
					<td><a href="adminServlet?method=getPost&postid=${post.postId }&pageNo=${postpage.pageNo }">${post.title }</a></td>
					<td>${post.createTime }</td>
					<td>${post.typeId }</td>
					<td>
					<!-- 
						<select>
							<c:forEach items="${type }" var="type">
								<option id="typeVal" value="2">${type.typeName }</option>
							</c:forEach>
						</select>
					 -->
						&nbsp; | &nbsp;<a href="adminServlet?method=delete&postId=${post.postId }&pageNo=${postpage.pageNo }" id="deleteVal">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		</form>
		<center>
		<br><br>
		共 ${postpage.totalPageNumber } 页
		&nbsp;&nbsp;
		当前第 ${postpage.pageNo } 页		
		&nbsp;&nbsp;
		<c:if test="${postpage.hasPrev }">
			<a href="adminServlet?method=toAdminPosts&pageNo=1">首页</a>
			&nbsp;&nbsp;
			<a href="adminServlet?method=toAdminPosts&pageNo=${postpage.prevPage }">上一页</a>
		</c:if>
		&nbsp;&nbsp;
		<c:if test="${postpage.hasNext }">
			<a href="adminServlet?method=toAdminPosts&pageNo=${postpage.nextPage }">下一页</a>
			&nbsp;&nbsp;
			<a href="adminServlet?method=toAdminPosts&pageNo=${postpage.totalPageNumber }">末页</a>
		</c:if>
		&nbsp;&nbsp;
		转到 <input type="text" size="1" id="pageNo"/> 页
		</center>
</body>
</html>