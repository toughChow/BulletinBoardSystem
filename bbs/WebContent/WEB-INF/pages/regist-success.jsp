<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
</head>
<body>
<div class="banner" style="padding-left:18px;"><a href="typeServlet?method=toMain"><FONT SIZE="7" COLOR="white"><B>BBS论坛</B></FONT></a></div>
	<center>
		<h4>恭喜你 ${requestScope.name },注册成功</h4>
		<h4>点击此处<a href="userServlet?method=jumpToLogin">登录</a></h4>
		<h4>点击此处<a href="typeServlet?method=toMain">返回首页</a></h4>
	</center>
</body>
</html>