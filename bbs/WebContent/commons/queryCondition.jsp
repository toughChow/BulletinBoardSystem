<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
	
	$(function(){
		$("a").each(function(){
			this.onclick = function(){
				window.location.href = href;			
				return false;
			};
		});
	});	
</script>
	