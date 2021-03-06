<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- CSS --%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/myTemplate.css">
<link rel="stylesheet" 
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<%-- Body --%>
<div style="height: 73px; max-height: 73px">
	<header class="fixed-top">
		<nav class="navbar navbar-expand-lg navbar-dark container-fluid"
			style="background-color: #4d4d4d00;">
	
			<%-- logo --%>
			<a class="navbar-brand" href="<c:url value='/index.jsp' />"><img
				src="${pageContext.request.contextPath}/resources/images/Logo-02.png"
				width="200px" height="auto" alt="logo" /></a>
	
			<%-- 漢堡選單 --%>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
	
			<%-- 功能列表 --%>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
	
					<li class="nav-item dropdown">
					<a class="nav-link" style="font-size: 24px; padding: 8px;" href="<c:url value='#' />" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 商城 </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="<c:url value='/_02_ShoppingSystem/DisplayPageProducts' />">寵物商城</a>
						</div>
					</li>
	
					<li class="nav-item dropdown">
					<a class="nav-link text_size" style="font-size: 24px; padding: 8px;" href="<c:url value='#' />" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">交流專區</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="<c:url value='#' />">寵物專欄</a> 
							<a class="dropdown-item" href="<c:url value='#' />">寵物失蹤協尋</a> 
							<a class="dropdown-item" href="<c:url value='#' />">二手市集</a> 
							<a class="dropdown-item" href="<c:url value='#' />">活動建立</a> 
							<a class="dropdown-item" href="<c:url value='#' />">寵物交友</a> 
							<a class="dropdown-item" href="<c:url value='#' />">留言板</a>
						</div>
					</li>
	
					<li class="nav-item dropdown">
					<a class="nav-link" style="font-size: 24px; padding: 8px;" href="<c:url value='' />" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 友善專區 </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="<c:url value='#' />">景點</a> 
							<a class="dropdown-item" href="<c:url value='#' />">餐廳</a> 
							<a class="dropdown-item" href="<c:url value='#' />">寵物美容</a> 
							<a class="dropdown-item" href="<c:url value='#' />">旅館</a> 
							<a class="dropdown-item" href="<c:url value='#' />">寵物寄放</a> 
							<a class="dropdown-item" href="<c:url value='#' />">租屋</a>
						</div>
					</li>
	
					<li class="nav-item dropdown">
					<a class="nav-link" style="font-size: 24px; padding: 8px;" href="<c:url value='#' />" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 健康管理 </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="<c:url value='#' />">寵物熱量計算</a> 
							<a class="dropdown-item" href="<c:url value='#' />">寵物健康管理</a> 
							<a class="dropdown-item" href="<c:url value='#' />">寵物疫苗資訊</a> 
							<a class="dropdown-item" href="<c:url value='#' />">寵物食譜</a>
						</div>
					</li>
				</ul>
	
				<ul class="navbar-nav my-2 my-lg-0">
					<li class="nav-item">
						<c:choose>
								<c:when test="${empty LoginOK}">
									<a class="nav-link" href="<c:url value='#' />" data-toggle="modal"
										data-target="#login"> <p1 style="font-size: 24px; padding: 8px;">會員登入</p1> <p2> <img
											src="${pageContext.request.contextPath}/resources/images/member.svg"
											width="40px" height="auto" alt="" /> </p2>
									</a>
								</c:when>
								<c:when test="${LoginOK.CLASSIFY == 0}">
									<div class="dropdown">
										<a class="nav-link nav-item dropdown-toggle" href="<c:url value='#' />" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <p1 style="font-size: 24px; padding: 8px;">會員中心</p1> <p2> <img
												src="${pageContext.request.contextPath}/_00_init/getHeadshot?account=${LoginOK.cusAccount}"
												width="40px" height="auto" alt="" /> </p2>
										</a>
											<div class="dropdown-menu" aria-labelledby="navbarDropdown">
												<a class="dropdown-item" href="<c:url value='/_01_Member/PetRegistration.jsp' />">寵物新增</a> 
												<a class="dropdown-item" href="<c:url value='#' />">會員管理</a> 
												<a class="dropdown-item" href="<c:url value='#' />">會員功能_三</a> 
												<a class="dropdown-item" href="<c:url value='#' />">會員功能_四</a>
											</div>
									</div>
								</c:when>
								<c:when test="${LoginOK.CLASSIFY == 1}">
									<div class="dropdown">
										<a class="nav-link dropdown-toggle" href="<c:url value='#' />" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <p1 style="font-size: 24px; padding: 8px;">商家中心</p1> <p2> <img
												src="${pageContext.request.contextPath}/_00_init/getHeadshot?account=${LoginOK.busAccount}"
												width="40px" height="auto" alt="" /> </p2>
										</a>
											<div class="dropdown-menu" aria-labelledby="navbarDropdown">
													<a class="dropdown-item" href="<c:url value='/_01_Member/MerchantChildRegistration.jsp' />">分店新增</a> 
													<a class="dropdown-item" href="<c:url value='#' />">商家管理</a> 
													<a class="dropdown-item" href="<c:url value='/_03_ConvenienceProcess/Convenience_H.do' />">服務上架</a> 
													<a class="dropdown-item" href="<c:url value='#' />">商家功能_四</a>
											</div>
									</div>
								</c:when>
							</c:choose>
						</li>
	
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='#' />"><p1 style="font-size: 24px; padding: 8px;">購物車</p1>
							<p2>
								<img
									src="${pageContext.request.contextPath}/resources/images/cart.svg"
									width="40px" height="auto" alt="" />
							</p2>
						</a>
					</li>
					
					<li class="nav-item">
						<c:if test="${ ! empty LoginOK }">
							<a class="nav-link" type="button" onclick="logout()"><p1 style="font-size: 24px; padding: 8px;">登出</p1>
								<p2>
									<img
										src="${pageContext.request.contextPath}/resources/images/logout_2.png"
										width="40px" height="auto" alt="" />
								</p2>
							</a>
						</c:if>
					</li>
				</ul>
	
			</div>
		</nav>
	</header>
</div>


<!-- 下列敘述設定變數funcName的值為LOG，top.jsp 會用到此變數 -->
	<c:set var="funcName" value="LOG" scope="session"/>
	<c:set var="msg" value="登入" />
	
<!-- 表示使用逾時，重新登入 -->
<%-- 	<c:if test="${ ! empty sessionScope.timeOut }" >  --%>
<%-- 	   <c:set var="msg" value="<font color='red'>${sessionScope.timeOut}</font>" /> --%>
<%-- 	</c:if> --%>


<!-- 登入畫面_Modal -->
<div class="modal fade" id="login" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
		
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalCenterTitle">登入系統</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			
			<div class="modal-body">

				<form action="<c:url value='/_01_Member/Login' />" method="POST" name="loginForm">
					<div class="form-group">
					    <label for="exampleInputAccount">帳號</label>
					    <input type="text" class="form-control" name="userId" id="exampleInputAccount" aria-describedby="emailHelp" placeholder="Account" value="${requestScope.user}${param.userId}">
					    <small class="form-text text-muted"><font class="errhide" color="red">${ErrorMsgKey.AccountEmptyError}</font></small> 
					</div>
					<div class="form-group">
					    <label for="exampleInputPassword1">密碼</label>
					    <input type="password" class="form-control" name="pswd" id="exampleInputPassword1" placeholder="Password" value="${requestScope.password}${param.pswd}">
					    <small class="form-text text-muted"><font class="errhide" color="red">${ErrorMsgKey.PasswordEmptyError}</font></small>	
					</div> 
					<div class="form-group form-check">	
					    <input type="checkbox" class="form-check-input" name="rememberMe" id="exampleCheck1"
						    <c:if test='${requestScope.rememberMe==true}'>
		                  		checked="checked"
		               		</c:if> 
					    value="true">
					    <label class="form-check-label" for="exampleCheck1">記住我</label>
					</div>
					<div class="text-center">
						<small class="form-text text-muted"><font class="errhide" color="red">${ErrorMsgKey.LoginError} ${MsgMap.errorNotLogin}</font></small>
					</div>
					<div class="modal-footer justify-content-center">
						<button type="submit" class="btn btn-outline-primary">登入</button>
					</div>
					<div class="col-md-12">
              			<p class="font-small white-text d-flex justify-content-center">尚未創建帳號
              			<a href="<c:url value='#' />" class="green-text ml-1 font-weight-bold" data-toggle="modal" data-target="#regis" onclick="changeModal()">立即註冊</a></p>
              		</div>
				</form>
            </div>
		</div>
	</div>
</div>


<!-- 註冊前導畫面_Modal -->
<div class="modal fade" id="regis" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog modal-xl"
		role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalCenterTitle">註冊系統</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div class="row justify-content-center">
<%-- 						<a href="${pageContext.request.contextPath}/_01_Member/MemberRegistration.jsp"><div class="col-6 text-center">會員註冊</div></a> --%>
<%-- 						<a href="${pageContext.request.contextPath}/_01_Member/MerchantRegistration.jsp"><div class="col-6 text-center">商家註冊</div></a> --%>
						<div class="col-6 text-center"><a href="<c:url value='/_01_Member/MemberRegistration.jsp' />">會員註冊</a></div>
						<div class="col-6 text-center"><a href="<c:url value='/_01_Member/MerchantRegistration.jsp' />">商家註冊</a></div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="col-md-12">
					<p class="font-small white-text d-flex justify-content-center">
						已完成註冊 <a href="<c:url value='#' />" class="green-text ml-1 font-weight-bold"
							data-toggle="modal" data-target="#login" onclick="changeModal()">立即登入</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- 訊息畫面_Modal -->
<div class="modal fade" id="messages" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">訊息提示</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
			<div class="text-center">
				<font color="red">${MsgOK.InsertOK} ${sessionScope.timeOut}</font>
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
        <a href="<c:url value='/index.jsp' />"><button type="button" class="btn btn-primary">回首頁</button></a>
      </div>
    </div>
  </div>
</div>





<%-- Javascript --%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<%-- 登入時，如有錯誤，重新導回登入畫面 --%>
<c:if test="${!empty ErrorMsgKey}">
	<script>
		$('#login').modal('show')
	</script>
</c:if>

<%-- 未登入，執行登入後功能時，重新導回登入畫面 --%>
<c:if test="${!empty MsgMap.errorNotLogin}">
	<script>
		$('#login').modal('show')
	</script>
	<% session.removeAttribute("MsgMap"); %>
</c:if>

<%-- 新增(含註冊、分店與寵物新增)成功與使用逾時時，顯示提示視窗 --%>
<c:if test="${!empty MsgOK.InsertOK}">
	<script>
		$('#messages').modal('show');
		setTimeout(function() {
            $('#messages').modal('hide') // 3秒後，modal消失。
        }, 3000);
	</script>
	<% session.removeAttribute("MsgOK"); %>
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


<%-- 登出時，跳出詢問視窗 --%>
<script language="javascript"> 
	function logout(){ 
	    if (confirm("您確定要登出嗎？")){ 
	    	window.location.href="/java014_03_FurKids/_01_Member/logout.jsp"
	    } 
	} 
</script>

<%-- 登入與註冊畫面切換 --%>
<script language="javascript"> 
	function changeModal(){
		if ($('#regis').modal('show')){
			$('#login').modal('hide')
		}
		if ($('#login').modal('show')) {
			$('#regis').modal('hide')
		}
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
