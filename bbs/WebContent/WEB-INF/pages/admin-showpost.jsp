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
		
		//<!-- 点击回复提交时 检查是否已经登录 -->
		$(".reply").click(function(){
			var userName = $("#hiddenName").val();
			if(userName == ""){
				alert("请登录后回复帖子!");
				return false;
			}
		});
		
		$("#quit").click(function(){
			var flag = true;
			flag = confirm("确定要退出登录吗?");
			if(flag){
				return true;
			}
			return false;
		});
	});
</script>
</head>
<body class="whole" style="padding-left:20px">
	<div class="banner" style="padding-left:18px;"><a href="typeServlet?method=toMain"><FONT SIZE="7" COLOR="white"><B>BBS论坛</B></FONT></a></div>
	<center>
	<br><br>
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
				<td colspan="3">${post.content }</td>
			</tr>
		</table>
		<br>
		
		<hr>
		
		<br>
		<input type = "hidden" value ="${sessionScope.username }" id = "hiddenName"/>
		<input type = "hidden" value ="${post.postId }" id = "postId"/>
		<input type = "hidden" value ="${post.typeId }" id = "postId"/>
		<h3>留言板管理</h3>
		
		<c:if test="${!empty requestScope.reply }">
			<form action="replyServlet?method=addReplyToMysql&postId=${post.postId }&&userName=${sessionScope.username}&pageNo=${requestScope.pageNo }&typeId=${post.typeId }" method="post">
				<table bordercolor="#96CDCD" cellpadding="10" cellspacing="0" border="1" width="1300" id = "replyTable">
					<tr>
						<td>用户Id</td>
						<td>回复内容</td>
						<td>回复时间</td>
					</tr>
					<c:forEach items="${requestScope.reply }" var="reply" >
						<tr>
							<td>${reply.userId }</td>
							<td>${reply.content }</td>
							<td>${reply.createTime }</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</c:if>
		
	</center>
</body>
</html>