<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#titleVal").blur(function(){
			var titleVal = $(this).val();
			titleVal = $.trim(titleVal);
			if(titleVal == ''){
				
				var titleMsg = "标题不能为空";
				$("#titleMsg").html(titleMsg).css({"color":"red"});
				return;
			}else{
				var titleMsg = "";
				$("#titleMsg").html(titleMsg);
			}
		});
		$("#contentVal").blur(function(){
			var contentVal = $(this).val();
			contentVal = $.trim(contentVal);
			if(contentVal == ''){
				contentMsg = "内容不能为空";
				//$("#contentVal").val("内容不能为空").css({"color":"red"});
				$("#contentMsg").html(contentMsg).css({"color":"red"});
				return;
			}else{
				contentMsg = "";
				$("#contentMsg").html(contentMsg);
			}
		});
		$("#typeIdVal").blur(function(){
			var typeIdVal = $(this).val();
			if(typeIdVal == 0){
				typeIdMsg = "请选择帖子类型";
				$("#typeIdMsg").html(typeIdMsg).css({"color":"red"});
				return;
			}else{
				var typeIdMsg = "";
				$("#typeIdMsg").html(typeIdMsg);
			}
		});
		//<!-- 点击回复提交时 检查是否已经登录 -->
		$("#send").click(function(){
			var userName = $("#hiddenName").val();
			var title = $("#titleVal").val();
			var content = $("#contentVal").val();
			var typeId = $("#typeIdVal").val();
			if(userName == ""){
				alert("请登录后回复帖子!");
				return false;
			}
			if(title == ''){
				var titleMsg = "标题不能为空";
				$("#titleMsg").html(titleMsg).css({"color":"red"});
				return false;
			}
			if(content == ''){
				var contentMsg = "内容不能为空";
				$("#contentMsg").html(contentMsg).css({"color":"red"});
				return false;
			}
			if(typeId == 0){
				typeIdMsg = "请选择帖子类型";
				$("#typeIdMsg").html(typeIdMsg).css({"color":"red"});
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
		<form action="postServlet?method=addPost" method="post">
		<input type = "hidden" value ="${sessionScope.username }" id = "hiddenName"/>
			<table  cellspacing="0" cellpadding="10" border="1" bordercolor="#96CDCD" width="1300">
				<tr>
					<td>标题:</td>
					<td><input id="titleVal" type="text" name="title"/><font  id="titleMsg"></font>
					</td>
				</tr>
				<tr>
					<td>内容:</td>
					<td><textarea id="contentVal"  name="content"  rows="10" cols="170"></textarea>
					<font id="contentMsg"></font>
					</td>
				</tr>
				<tr>
					<td>类型:</td>
					<td>
					<select id="typeIdVal" name="typeId">
						<option value="0">请选择...
					<c:forEach items="${type }" var="type">
						<option value="${type.typeId }">${type.typeName }</option>
					</c:forEach></option>
					</select><font id="typeIdMsg"></font>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input id="send" type="submit" value="提交"/><input type="reset" value="重置"/></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>