<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<%-- 適用不同裝置畫面呈現 --%>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<%-- CSS --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" 
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous"/>
	
	<%-- Nav外觀與特效 --%>
<link rel="stylesheet" 
	href="<c:url value='/resources/css/index.css' />"/>
	
<link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>

	<%-- Icon --%>
<link rel="stylesheet" 
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	
	<%-- 導覽列下拉滑動特效 --%>
	<style>
		
 		.dropdown:hover .dropdown-menu { 
 			display: block; 
 			transition: opacity .10s ease-out;
 	    }
 	     
	    /* 下面特效僅限網頁版有用 */
	    @media all and (min-width: 992px) {
			.navbar .nav-item .dropdown-menu{  display:block; opacity: 0;  visibility: hidden; transition:.5s; margin-top:0;  }
			.navbar .nav-item:hover .nav-link{ color: #fff;  }
			.navbar .dropdown-menu.fade-down{ top:80%; transform: rotateX(-75deg); transform-origin: 0% 0%; }
			.navbar .dropdown-menu.fade-up{ top:180%;  }
			.navbar .nav-item:hover .dropdown-menu{ transition: .3s; opacity:1; visibility:visible; top:100%; transform: rotateX(0deg); }
		}
	</style>

<%-- Navbar --%>
<nav class="navbar navbar-light navbar-expand-lg bg-white sticky-top shadow"
	id="navBar" style="z-index:9999">
	<div class="container">
	
	<%-- Logo --%>
		<a class="navbar-brand mb-1" href="<c:url value='/' />"><img
			src="<c:url value='/resources/images/Logo_07.png' />" alt="Logo" height="26" /></a>
			
	<%-- 漢堡選單 --%>		
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		
	<%-- 功能導覽列 --%>
		<div class="collapse navbar-collapse justify-content-between animate__animated animate__fadeInDown"
			id="navbarNavDropdown">
			<%-- 功能導覽列置中 --%>
			<div></div>
			<%-- 功能導覽列內容 --%>
			<ul class="navbar-nav">
				<%-- 商城 --%>
				<li class="nav-item dropdown mx-2">
					<a class="nav-link nav-font" href="<c:url value='#' />" 
						id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						 <i class="fas fa-store-alt" style="color: grey"></i>&nbsp;寵物商城 
					</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="<c:url value='/_02_ShoppingSystem/DisplayPageProducts' />">購物商城</a>
<%-- 						<a class="dropdown-item" href="<c:url value='#' />">自己加</a> --%>
<%-- 						<a class="dropdown-item" href="<c:url value='#' />">自己加</a> --%>
					</div>
				</li>
				<%-- 寵物交流版 --%>
				<li class="nav-item dropdown mx-2">
<%-- 					<a class="nav-link nav-font" href="<c:url value='/BlogIndex' />" --%>
<!-- 						id="navbarDropdownMenuLink" role="button"  aria-haspopup="true" aria-expanded="false"> -->
<!-- 						<i class="fas fa-handshake" style="color: grey"></i>&nbsp;寵物交流版 -->
<!-- 					</a> -->
					<a class="nav-link nav-font" href="<c:url value='#' />"
						id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="fas fa-handshake" style="color: grey"></i>&nbsp;寵物交流版
					</a>
<!-- 					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> -->
<%-- 						<a class="dropdown-item" href="<c:url value='#' />">自己加</a> --%>
<%-- 						<a class="dropdown-item" href="<c:url value='#' />">自己加</a> --%>
<%-- 						<a class="dropdown-item" href="<c:url value='#' />">自己加</a> --%>
<!-- 					</div> -->
				</li>
				<%-- 寵物友善系統 --%>
				<li class="nav-item dropdown mx-2">
					<a class="nav-link nav-font" href="<c:url value='#' />"
						id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="fas fa-hand-holding-heart" style="color: grey"></i>&nbsp;寵物友善系統
					</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="<c:url value='/_03_FriendlySystem/Reservation/景點' />">景點</a>
						<a class="dropdown-item" href="<c:url value='/_03_FriendlySystem/Reservation/餐廳' />">餐廳</a>
						<a class="dropdown-item" href="<c:url value='/_03_FriendlySystem/Reservation/美容' />">寵物美容</a>
						<a class="dropdown-item" href="<c:url value='/_03_FriendlySystem/Reservation/旅館' />">寵物旅館</a>
						
					</div>
				</li>
				<%-- 寵物健康管理 --%>
				<li class="nav-item dropdown ml-2 mr-5">
					<a class="nav-link nav-font" href="<c:url value='#' />"
						id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="fas fa-user-md" style="color: grey"></i>&nbsp;寵物健康管理 
					</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="<c:url value='#' />">熱量管理</a>
						<a class="dropdown-item" href="<c:url value='/BlogIndex' />">寵物健康專欄</a>
<%-- 						<a class="dropdown-item" href="<c:url value='#' />">疫苗資訊</a> --%>
					</div>
				</li>
				
			</ul>
			
			<%-- 登入、購物車、登出功能列 --%>
			<div class="form-inline my-2 my-lg-0">
				<c:choose>
					<%-- 訪客登入 --%>
					<c:when test="${empty LoginOK}">
						<a href="<c:url value='#' />" class="ml-4 m-2" data-toggle="modal" data-target="#login"> 
							<i class="fas fa-user navbar-user fa-lg"></i>
						</a>						
					</c:when>
					<%-- 會員(已登入) --%>
					<c:when test="${LoginOK.CLASSIFY == 0}">
						<div class="dropdown nav-item">
							<a class="nav-link" href="<c:url value='#' />" id="navbarDropdown" role="button"
								data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">  
								<img src="<c:url value='/_00_init/getHeadshot?account=${LoginOK.cusAccount}' />"
									style="width:30px; height:30px; border-radius:50%; border: 1px solid gray; object-fit: cover" />
							</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item"
									href="<c:url value='/PetRegistration' />">寵物新增</a>
								<a class="dropdown-item" 
									href="<c:url value='/MemberManagementCenter' />">會員管理</a> 
								<a class="dropdown-item" 
									href="<c:url value='/_03_FriendlySystem/MemReservationDetail' />">預約管理</a>
							</div>
						</div>
					</c:when>
					<%-- 商家(已登入) --%>
					<c:when test="${LoginOK.CLASSIFY == 1}">
						<div class="dropdown nav-item">
							<a class="nav-link" href="<c:url value='#' />" id="navbarDropdown" role="button" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> 
								<img src="<c:url value='/_00_init/getHeadshot?account=${LoginOK.busAccount}' />"
									style="width:30px; height:30px; border-radius:50%; border: 1px solid gray; object-fit: cover" />
							</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item"
									href="<c:url value='/MerchantChildRegistration' />">分店新增</a>
								<a class="dropdown-item" 
									href="<c:url value='/MerchantManagementCenter' />">商家管理</a> 
								<a class="dropdown-item"
									href="<c:url value='/_03_FriendlySystem/ViewSessionStatus_setComplete' />">商家服務上架</a>
								<a class="dropdown-item"
									href="<c:url value='/_03_FriendlySystem/busReservationDetail' />">預約明細</a>
							</div>
						</div>
					</c:when>
				</c:choose>
				
				<%-- 購物車 --%>
				<a href="<c:url value='/_02_ShoppingSystem/ShowCartContent' />" class="m-2">
					<i class="fas fa-shopping-cart navbar-cart fa-lg"></i>
				</a>
				
				<%-- 登出 --%>
				<c:if test="${ ! empty LoginOK }">
					<a href="<c:url value='#' />" class="m-2 logout" onclick="logout()">
						<i class="fas fa-sign-out-alt fa-lg" style="color: black"></i>
					</a>
					
				</c:if>
					
			</div>
			
		</div>
	</div>
</nav>


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
				<h4 class="modal-title" id="exampleModalCenterTitle" style="color: #0090d3"><b>登入系統</b></h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			
			<div class="modal-body">

				<form:form method="POST" modelAttribute="loginBean" action="${pageContext.request.contextPath}/login" enctype='multipart/form-data'>
					<div class="form-group">
					    <label for="exampleInputAccount">帳號 / 電子信箱</label>
					    <form:input class="form-control" path="userId" id="exampleInputAccount" aria-describedby="emailHelp" placeholder="Account" />
					   	<font class="errhide" color="red"><form:errors  path="userId" cssClass="errors" /></font>
					</div>
					<div class="form-group">
					    <label for="exampleInputPassword">密碼</label>
					    <form:input type="password" class="form-control" path="password" id="exampleInputPassword" placeholder="Password" />
					    <font class="errhide" color="red"><form:errors  path="password" cssClass="errors" /></font>
					</div> 
					<div class="form-group form-check">	
					    <form:checkbox class="form-check-input" path="rememberMe" id="exampleCheck" />
					    <label class="form-check-label" for="exampleCheck">記住我</label>
					</div>
					<div class="text-center">
						<small class="form-text text-muted"><font class="errhide" color="red">${LoginError} ${errorNotLogin}</font></small>
					</div>
					<div class="modal-footer justify-content-center">
						<button type="submit" class="btn btn-outline-primary">登入</button>
					</div>
					<div class="col-md-12">
              			<p class="font-small white-text d-flex justify-content-center">尚未創建帳號
              			<a href="<c:url value='#' />" class="green-text ml-1 font-weight-bold" data-toggle="modal" data-target="#regis" onclick="changeModal()">立即註冊</a></p>
              		</div>
				</form:form>
				
				
				<%-- 

				<form action="<c:url value='/login' />" method="POST" name="loginForm">
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
				
				--%>
				

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
				<h4 class="modal-title" id="exampleModalCenterTitle" style="color: #0090d3"><b>註冊系統</b></h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div class="row justify-content-center">
						<div class="col-6 text-center"><a href="<c:url value='/MemberRegistration' />">會員註冊</a></div>
						<div class="col-6 text-center"><a href="<c:url value='/MerchantRegistration' />">商家註冊</a></div>
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
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="exampleModalLabel" style="color: #0090d3"><b>訊息提示</b></h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
			<div class="text-center">
				<font color="red">${InsertOK} ${FlashMSG_farewell} ${sessionScope.timeOut} ${UpdateOK}</font>
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
        <a href="<c:url value='/' />"><button type="button" class="btn btn-primary">回首頁</button></a>
      </div>
    </div>
  </div>
</div>





<%-- Javascript --%>

<!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"  -->
<!-- 	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"  -->
<!-- 	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script> -->
<!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"  -->
<!-- 	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script> -->
 

	<%-- Icon --%>
<script
    src="https://kit.fontawesome.com/8e822d04fb.js" crossorigin="anonymous"></script>

<%--

	<%-- Nav特效 --%>
<!-- <script  -->
<!-- 	src="../resources/javascript/blogIndex.js"></script> -->

<%-- 登入時，如有錯誤，重新導回登入畫面 --%>
<c:if test="${!empty LoginError || !empty LoginInputError}">
	<script>
// 		$('#login').modal('show')
	</script>
</c:if>

<%-- 未登入，執行登入後功能時，重新導回登入畫面 --%>
<c:if test="${!empty errorNotLogin}">
	<script>
// 		$('#login').modal('show')
	</script>
</c:if>

<%-- 新增(含註冊、分店與寵物新增)、登入、登出成功與使用逾時時，顯示提示視窗 --%>
<c:if test="${!empty InsertOK}">
	<script>
// 		$('#messages').modal('show');
// 		setTimeout(function() {
//             $('#messages').modal('hide') // 3秒後，modal消失。
//         }, 3000);
	</script>
</c:if>

<c:if test="${!empty sessionScope.timeOut}">
	<script>
// 		$('#messages').modal('show');
// 		setTimeout(function() {
//             $('#messages').modal('hide') // 3秒後，modal消失。
//         }, 3000);
	</script>
<%-- 	<% session.removeAttribute("timeOut"); %> --%>
</c:if>

<c:if test="${!empty FlashMSG_farewell}">
	<script>
// 		$('#messages').modal('show');
// 		setTimeout(function() {
//             $('#messages').modal('hide') // 3秒後，modal消失。
//         }, 3000);
	</script>
</c:if>


<%-- 登出時，跳出詢問視窗 --%>
<script language="javascript"> 
// 	function logout(){ 
// 	    if (confirm("您確定要登出嗎？")){ 
// 	    	$('.logout').attr('href', '${pageContext.request.contextPath}/logout')
// 	    } 
// 	} 
</script>

<%-- 登入與註冊畫面切換 --%>
<script language="javascript"> 
// 	function changeModal(){

// 		$("#regis").on("show.bs.modal",function(e){
// 			$('#login').modal('hide');
// 		});

// 		$("#login").on("show.bs.modal",function(e){
// 			$('#regis').modal('hide');
// 		});
		
		/*
		if ($('#regis').modal('show')){
			$('#login').modal('hide')
		} 
		if ($('#login').modal('show')) {
			$('#regis').modal('hide')
		}
		*/
// 	}
</script>

<%-- 關閉Modal隱藏錯誤訊息 --%>
<script type="text/javascript">
// 	$("#login").on("hidden.bs.modal",function(e){
// 	  $('.errhide').css('display', 'none');
// 	});
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

