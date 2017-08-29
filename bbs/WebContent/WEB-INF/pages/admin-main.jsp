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
	<center>
		<br><br>
		<a href="adminServlet?method=toAdminUsers">用户管理</a>
		<br><br>
		<a href="adminServlet?method=toAdminPosts">帖子管理</a>
		<br><br>
		<a href="adminServlet?method=toAdminTypes">类型管理</a>
		<br><br>
	</center>
</body>
</html>