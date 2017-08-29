<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
		$(".flip").click(function(){
		    $(".panel").slideToggle("slow");
		  });
	});	
</script>

<style type="text/css"> 
div.panel,p.flip{margin:0px;padding:5px;text-align:center;background:#B1D3E0;border:solid 1px #c3c3c3;}
div.panel{height:100px;display:none;}
</style>
</head>
<body class="whole" style="padding-left:20px">
	<div class="banner" style="padding-left:18px;"><a href="typeServlet?method=toMain"><FONT SIZE="7" COLOR="white"><B>BBS论坛</B></FONT></a></div>
	<center>
	
	<div class="guide" style="padding-left:20px;border-bottom:8px solid #dbecF4;background:#B1D3E0;text-align:left;color:#004c7d;padding:5px 7px 3px 7px;">
		<c:if test="${sessionScope.username != null}">
		&raquo; 当前用户:&nbsp;<b class="flip">${sessionScope.username }</b>
		&nbsp; | &nbsp;<a>发帖</a>&nbsp; | &nbsp;
		<a href="postServlet?method=getMyPosts&pageNo=${requestScope.pageNo }">返回上一页</a>&nbsp; | &nbsp;<a href="userServlet?method=quit&pageNo=${postpage.pageNo }" id="quit">退出</a>
		</c:if>
		
		<c:if test="${sessionScope.username == null }">
		&raquo;  您尚未　<a href="userServlet?method=jumpToLogin">登录</a> &nbsp; | &nbsp;没有帐号?点击此处<a href="userServlet?method=jumpToRegist"><b>注册</b></a>&nbsp; | &nbsp;<a href="postServlet?method=getPosts&pageNo=${requestScope.pageNo }">返回上一页</a>
		</c:if>
	</div>
	<div class="panel">
	<p><a href="">我的帖子</a></p>
	<p><a href="">更改密码</a></p>
	</div>
	<br><br>
	<form action="">
		<table class="border" cellspacing="0" cellpadding="10" border="1" width="1300" height="200">
			<tr>
				<td>作者</td>
				<td>标题</td>
				<td>编写时间</td>
			</tr>
			<tr>
				<td>${user.name }</td>
				<td>${post.title }</td>
				<td>${post.createTime }</td>
			</tr>
			<tr>
				<td colspan="3"><textarea rows="10" cols="170" readonly="readonly">${post.content }</textarea></td>
			</tr>
		</table>
		</form>
		<br>		
	</center>
</body>
</html>