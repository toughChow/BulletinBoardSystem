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
		$("#a1").click(function(){
			var flag = true;
			flag = confirm("确定删除吗?");
			if(flag){
				return true;
			}
			return false;
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
	<center>
		<table  class="border" cellspacing="0" cellpadding="10" border="1" width="1300">
			<tr>
				<td>类型Id</td>
				<td>类型名称</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${type }" var="type">
			<tr>
				<td>${type.typeId }</td>
				<td>${type.typeName }</td>
				<td><a id="a1" href="adminServlet?method=deleteType&typeId=${type.typeId }">删除</a></td>
			</tr>
			</c:forEach>			
		</table>
	</center>
</body>
</html>