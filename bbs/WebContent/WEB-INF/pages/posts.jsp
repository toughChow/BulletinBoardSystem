<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电子公告牌 - powered by servlet</title>
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript">
	$(function(){
		$("#quit").click(function(){
			var flag = true;
			var username = $("#hiddenName").val();
			flag = confirm("尊敬的"+ username +",确定要退出登录吗?");
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
			var href = "postServlet?method=getPosts&pageNo=" + pageNo;
			window.location.href = href;
		});
	});
</script>

</head>
<body>
	<div class="banner" style="padding-left:5px;"><a href="postServlet?method=getPosts"><FONT SIZE="7" COLOR="white"><B>BBS论坛</B></FONT></a></div>

	<center>
	
	<div class="banner" style="padding-left:18px;"><a href="typeServlet?method=toMain"><FONT SIZE="7" COLOR="white"><B>BBS论坛</B></FONT></a></div>
	<div class="guide" style="padding-left:20px;border-bottom:8px solid #dbecF4;background:#B1D3E0;text-align:left;color:#004c7d;padding:5px 7px 3px 7px;">
		<c:if test="${sessionScope.username != null}">
		&raquo; 当前用户:&nbsp;${sessionScope.username }&nbsp; | &nbsp;<a>发帖</a>&nbsp; | &nbsp;<a href="userServlet?method=quit&pageNo=${postpage.pageNo }" id="quit">退出</a>
		</c:if>
		<c:if test="${sessionScope.username == null }">
		&raquo;  您尚未　<a href="userServlet?method=jumpToLogin">登录</a> &nbsp; | &nbsp;没有帐号?点击此处<a href="userServlet?method=jumpToRegist"><b>注册</b></a>
		</c:if>
	</div>
	<!-- 
	<c:if test="${sessionScope.username != null}">
		欢迎&nbsp;${sessionScope.username }&nbsp;登录&emsp;&emsp;&emsp;
		<a href="userServlet?method=quit&pageNo=${postpage.pageNo }" id="quit">退出</a>
	</c:if>
	<c:if test="${sessionScope.username == null }">
	请<a href="userServlet?method=jumpToLogin">登录</a>&emsp;&emsp;&emsp;
	没有帐号?点击此处<a href="userServlet?method=jumpToRegist">注册</a>
	</c:if>
	 -->
	<input type = "hidden" value ="${sessionScope.username }" id = "hiddenName"/>
	
	<br><br>
		<table cellspacing="10">
				<tr>
					<td>序号</td>
					<td>标题</td>
					<td>日期</td>
				</tr>
			<c:forEach items="${postpage.list }" var="post">
				<tr>
					<td>${post.postId }</td>
					<td><a href="postServlet?method=getPost&postid=${post.postId }&pageNo=${postpage.pageNo }">${post.title }</a></td>
					<td>${post.createTime }</td>
				</tr>
			
			</c:forEach>
		</table>
		
		<br><br>
		共 ${postpage.totalPageNumber } 页
		&nbsp;&nbsp;
		当前第 ${postpage.pageNo } 页		
		&nbsp;&nbsp;
		
		<c:if test="${postpage.hasPrev }">
			<a href="postServlet?method=getPosts&pageNo=1">首页</a>
			&nbsp;&nbsp;
			<a href="postServlet?method=getPosts&pageNo=${postpage.prevPage }">上一页</a>
		</c:if>
		
		&nbsp;&nbsp;
		
		<c:if test="${postpage.hasNext }">
			<a href="postServlet?method=getPosts&pageNo=${postpage.nextPage }">下一页</a>
			&nbsp;&nbsp;
			<a href="postServlet?method=getPosts&pageNo=${postpage.totalPageNumber }">末页</a>
		</c:if>
		
		&nbsp;&nbsp;
		
		转到 <input type="text" size="1" id="pageNo"/> 页	
		
	</center>
</body>
</html>