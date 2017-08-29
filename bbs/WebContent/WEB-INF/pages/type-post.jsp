<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BBS论坛 - ${requestScope.typeName }分区</title>
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
			var href = "postServlet?method=getPosts&pageNo=" + pageNo;
			window.location.href = href;
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
<body>
	<div class="whole" style="padding-left:20px">
	<div class="banner" style="padding-left:20px;"><a href="typeServlet?method=toMain"><FONT SIZE="7" COLOR="white"><B>BBS论坛</B></FONT></a></div>
	
	<div class="guide" style="padding-left:20px;border-bottom:8px solid #dbecF4;background:#B1D3E0;text-align:left;color:#004c7d;padding:5px 7px 3px 7px;">
		
		<c:if test="${sessionScope.username != null}">
		&raquo; 当前用户:&nbsp;<b class="flip">${sessionScope.username }</b>&nbsp; | &nbsp;
		<a href="postServlet?method=JumpToSendPost">发帖</a>&nbsp; | &nbsp;
		<a href="userServlet?method=quit&pageNo=${postpage.pageNo }" id="quit">退出</a>
		</c:if>
		
		<c:if test="${sessionScope.username == null }">
		&raquo;  您尚未　<a href="userServlet?method=jumpToLogin">登录</a>
		&nbsp; | &nbsp;没有帐号?点击此处<a href="userServlet?method=jumpToRegist">
		<b>注册</b></a>
		</c:if>
	</div>
	<div class="panel">
	<p><a href="">我的帖子</a></p>
	<p><a href="">更改密码</a></p>
	</div>
	<br>
	<center>
	<table class="border" cellspacing="0" cellpadding="10" border="1" width="1300">
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
		<a href="postServlet?method=getPostsByTypeId&typeId=${typeId }&pageNo=1">首页</a>
		&nbsp;&nbsp;
		<a href="postServlet?method=getPostsByTypeId&typeId=${typeId }&pageNo=${postpage.prevPage }">上一页</a>
	</c:if>
	&nbsp;&nbsp;
	<c:if test="${postpage.hasNext }">
		<a href="postServlet?method=getPostsByTypeId&typeId=${typeId }&pageNo=${postpage.nextPage }">下一页</a>
		&nbsp;&nbsp;
		<a href="postServlet?method=getPostsByTypeId&typeId=${typeId }&pageNo=${postpage.totalPageNumber }">末页</a>
	</c:if>
	&nbsp;&nbsp;
	转到 <input type="text" size="1" id="pageNo"/> 页	
	</center>
	</div>
</body>
</html>