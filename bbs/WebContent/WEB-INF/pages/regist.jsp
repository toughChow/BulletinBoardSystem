<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="bbs.web.TokenProcessor" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bbs论坛-注册</title>
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		
		$(":input[name='password2']").change(function(){
			var val1 = $("#pwd1");
			var val2 = $("#pwd2");
			if(val1.val() == val2.val()){
				$("#pwdMsg").text("密码验证通过").css({"color":"green"});
				return;
			}else{
				$("#pwdMsg").text("密码不匹配").css({"color":"red"});
			}
		});
		
		//	<!-- 验证手机 -->
		$(":input[name='phone']").change(function(){
			var phoneVal = $(this).val();
			phoneVal = $.trim(phoneVal);
			var phoneMsg = "";
			
			
			if(phoneVal == ''){
				phoneMsg = "手机号不能为空";
				$("#phoneMsg").html(phoneMsg).css({"color":"red"});
				return;
			}
			/*
			<!--
			var reg = /^((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8}$/;
			else if(!reg.test(phoneVal)){
			phoneMsg = "手机号格式错误";
			$("#phoneMsg").html(phoneMsg);
			return;
			}
			-->
			*/
			
			var args = {"method":"validatePhone","phone":phoneVal,"time":new Date()};
			var url="${pageContext.request.contextPath }/validateRegistServlet";
			
			$.post(url,args,function(data){
				$("#phoneMsg").html(data);
			});
			
		});
		// <!-- 验证用户名是否可用 -->
		$(":input[name='username']").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			
			if(val != ""){
				var url = "${pageContext.request.contextPath }/validateRegistServlet";
				var args = {"method":"validateUserName","username":val,"time":new Date()};
				
				$.post(url,args,function(data){
					$("#message").html(data);
				});
			}
		});
	});
</script>	
</head>
<body>
	<center>
	<br><br>
		<form action = "userServlet?method=regist" method="post">
				<input type="hidden" name="TOKEN_KEY" value="<%= TokenProcessor.getInstance().saveToken(request) %>"/>
			<table>
				<tr>
					<td>用户名</td>
					<td><input type="text" name="username"/></td>
					<td id = "message"></td>
				</tr>
				<tr>
					<td>电话号码</td>
					<td><input type="text" name="phone"/></td>
					<td id = "phoneMsg"></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="password" name="password1" id="pwd1"/></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>确认密码</td>
					<td><input type="password" name="password2" id = "pwd2"/></td>
					<td id="pwdMsg"></td>
				</tr>
				<tr>
					<td><input type="submit" value="注册"/></td>
					<td colspan="2"><input type="reset" value="重置"/></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>