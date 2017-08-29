<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的帖子</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#pwd2").blur(function(){
			
			var $pwd1Val = $("#pwd1").val();
			var $pwd2Val = $("#pwd2").val();
			var flag = false;
			if($pwd1Val == $pwd2Val){
				
				flag = true;
				pwd2Msg = "密码验证通过";
				$("#pwd2Msg").html(pwd2Msg).css({"color":"green"});
			}
			if(flag == false){
				pwd2Msg = "密码不一致";
				$("#pwd2Msg").html(pwd2Msg).css({"color":"red"});
			}
			return;
		});
		//<!-- 点击回复提交时 检查是否已经登录 -->
		$("#send").click(function(){
			var userVal = $("#user").val();
			var pwd0Val = $("#pwd0").val();
			var pwd1Val = $("#pwd1").val();
			var pwd2Val = $("#pwd2").val();
			var phoneVal = $("#phone").val();
			if(userVal == ''){
				var titleMsg = "用户名不能为空";
				$("#userMsg").html(titleMsg).css({"color":"red"});
				return false;
			}
			if(pwd1Val == ''){
				var titleMsg = "密码不能为空";
				$("#pwd1Msg").html(titleMsg).css({"color":"red"});
				return false;
			}
			if(pwd2Val == ''){
				var contentMsg = "密码不能为空";
				$("#pwd2Msg").html(contentMsg).css({"color":"red"});
				return false;
			}
			if(phoneVal == ''){
				typeIdMsg = "请选择帖子类型";
				$("#phoneMsg").html(typeIdMsg).css({"color":"red"});
				return false;
			}
			if(pwd0Val == ''){
				var contentMsg = "密码不能为空";
				$("#pwd0Msg").html(contentMsg).css({"color":"red"});
				return false;
			}
			
			var $pwd1Val = $("#pwd1").val();
			var $pwd2Val = $("#pwd2").val();
			var flag = false;
			if($pwd1Val == $pwd2Val){
				
				flag = true;
				pwd2Msg = "密码验证通过";
				$("#pwd2Msg").html(pwd2Msg).css({"color":"green"});
			}
			if(flag == false){
				pwd2Msg = "密码不一致";
				$("#pwd2Msg").html(pwd2Msg).css({"color":"red"});
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
	<div class="banner" style="padding-left:20px;">
	<a href="typeServlet?method=toMain"><FONT SIZE="7" COLOR="white"><B>BBS论坛</B></FONT></a>
	</div>
	<div class="guide">
		&raquo; 当前用户:&nbsp;<b class="flip">${sessionScope.username }</b>&nbsp; | &nbsp;
		<a href="postServlet?method=JumpToSendPost">发帖</a>&nbsp; | &nbsp;
		<a href="userServlet?method=quit&pageNo=${postpage.pageNo }" id="quit">退出</a>
	</div><br><br>
	${message }
	<br><br>
	<form action="userServlet?method=alterPwdValidate&userId=${user.userId }" method="post">
			<input type="hidden" name="userId" value="${user.userId }">
		<table  cellspacing="0" cellpadding="10" border="1" bordercolor="#96CDCD" width="1300">
				<tr>
					<td>用户id:</td>
					<td>${user.userId }</td>
				</tr>
				<tr>
					<td>用户名:</td>
					<td><input id="user" type="text" name="name" value="${user.name }"/><font  id="userMsg"></font>
					</td>
				</tr>
				<tr>
					<td>原始密码:</td>
					<td><input id="pwd0" type="password" name="firstPwd"/>
					<font  id="pwd0Msg"></font>
					</td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input id="pwd1" type="password" name="password1"/>
					<font  id="pwd1Msg"></font>
					</td>
				</tr>
				<tr>
					<td>确认密码:</td>
					<td><input id="pwd2" type="password" name="password"/>
					<font id="pwd2Msg"></font>
					</td>
				</tr>
				<tr>
					<td>联系电话:</td>
					<td><input id="phone" type="text" name="phone" value="${user.phone }"/>
					<font  id="phoneMsg"></font>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input id="send" type="submit" value="保存"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>