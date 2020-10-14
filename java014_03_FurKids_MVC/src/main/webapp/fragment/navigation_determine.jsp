<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%-- 登入時，如有錯誤，重新導回登入畫面 --%>
<c:if test="${!empty LoginError || !empty LoginInputError}">
	<script>
		$('#login').modal('show')
	</script>
</c:if>

<%-- 未登入，執行登入後功能時，重新導回登入畫面 --%>
<c:if test="${!empty errorNotLogin}">
	<script>
		$('#login').modal('show')
	</script>
</c:if>

<%-- 新增(含註冊、分店與寵物新增)、登入、登出成功與使用逾時時，顯示提示視窗 --%>
<c:if test="${!empty InsertOK}">
	<script>
		$('#messages').modal('show');
		setTimeout(function() {
            $('#messages').modal('hide') // 3秒後，modal消失。
        }, 3000);
	</script>
</c:if>

<c:if test="${!empty sessionScope.timeOut}">
	<script>
		$('#messages').modal('show');
		setTimeout(function() {
            $('#messages').modal('hide') // 3秒後，modal消失。
        }, 3000);
	</script>
	<% session.removeAttribute("timeOut"); %>
</c:if>

<c:if test="${!empty FlashMSG_farewell}">
	<script>
		$('#messages').modal('show');
		setTimeout(function() {
            $('#messages').modal('hide') // 3秒後，modal消失。
        }, 3000);
	</script>
</c:if>

<c:if test="${!empty UpdateOK}">
	<script>
		$('#messages').modal('show');
		setTimeout(function() {
            $('#messages').modal('hide') // 3秒後，modal消失。
        }, 3000);
	</script>
</c:if>

<c:if test="${!empty UpdateError}">
	<script>
		$('#messages').modal('show');
		setTimeout(function() {
            $('#messages').modal('hide') // 3秒後，modal消失。
        }, 3000);
	</script>
</c:if>
<%-- 登出時，跳出詢問視窗 --%>
<script language="javascript"> 
	function logout(){ 
	    if (confirm("您確定要登出嗎？")){ 
// 	    	$('.logout').attr('href', '${pageContext.request.contextPath}/logout');
	    	window.location.href='${pageContext.servletContext.contextPath}/logout';
	    } 
	} 
</script>

<%-- 登入與註冊畫面切換 --%>
<script language="javascript"> 
	function changeModal(){

		$("#regis").on("show.bs.modal",function(e){
			$('#login').modal('hide');
		});

		$("#login").on("show.bs.modal",function(e){
			$('#regis').modal('hide');
		});
		
		/*
		if ($('#regis').modal('show')){
			$('#login').modal('hide')
		} 
		if ($('#login').modal('show')) {
			$('#regis').modal('hide')
		}
		*/
	}
</script>

<%-- 關閉Modal隱藏錯誤訊息 --%>
<script type="text/javascript">
	$("#login").on("hidden.bs.modal",function(e){
	  $('.errhide').css('display', 'none');
	});
</script>


<%-- 禁用鍵盤F5功能 --%>
<script type="text/javascript">
// document.onkeydown = function(e){
//     e = window.event || e;
//     var keycode = e.keyCode || e.which;
//     if(keycode == 116){
// 		     if(window.event){
// 		     try{e.keyCode = 0;}catch(e){}
// 		     e.returnValue = false;
// 		 }else{
// 		     e.preventDefault();
// 		 }
//     }
// } 
</script>
