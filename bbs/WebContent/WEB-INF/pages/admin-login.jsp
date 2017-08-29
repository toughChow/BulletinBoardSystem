<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bbs论坛-登录页面</title>
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript">
	$(function(){
		$(":text").change(function(){
			if(this.value == ""){
				alert("用户名不能为空");
				return;
			}
		});
	});
</script>
</head>
<body>
	<div class="banner" style="padding-left:18px;"><a href="typeServlet?method=toMain"><FONT SIZE="7" COLOR="white"><B>BBS论坛</B></FONT></a></div>
	<center>
		<c:if test="${requestScope.message != null}">
			<font color="red">${requestScope.message }</font>
		</c:if>
		<form action="adminServlet?method=login" method="post">
			<table class="border" cellspacing="0" cellpadding="10" border="1">
				<tr>
					<td>用户名</td>
					<td><input type="text" name="username" value="${tempname }"/></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="登录"/></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>