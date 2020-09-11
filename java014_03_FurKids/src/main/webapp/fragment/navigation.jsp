<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- CSS --%>
<link
	href="${pageContext.request.contextPath}/resources/css/main_template.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<%-- Body --%>
<header class="fixed-top">
	<nav class="navbar navbar-expand-lg navbar-dark container-fluid"
		style="background-color: #4d4d4d00;">

		<%-- logo --%>
		<a class="navbar-brand" href="index.html"><img
			src="${pageContext.request.contextPath}/resources/images/Logo-02.png"
			width="100px" height="auto" alt="logo" /></a>

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

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> 商城 </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<!--<a class="dropdown-item" href="#">預留欄位</a>-->
					</div></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> 交流專區 </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">寵物專欄</a> <a
							class="dropdown-item" href="#">寵物失蹤協尋</a> <a
							class="dropdown-item" href="#">二手市集</a> <a class="dropdown-item"
							href="#">活動建立</a> <a class="dropdown-item" href="#">寵物交友</a> <a
							class="dropdown-item" href="#">留言板</a>
					</div></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> 友善專區 </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">景點</a> <a class="dropdown-item"
							href="#">餐廳</a> <a class="dropdown-item" href="#">寵物美容</a> <a
							class="dropdown-item" href="#">旅館</a> <a class="dropdown-item"
							href="#">寵物寄放</a> <a class="dropdown-item" href="#">租屋</a>
					</div></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> 健康管理 </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">寵物熱量計算</a> <a
							class="dropdown-item" href="#">寵物健康管理</a> <a
							class="dropdown-item" href="#">寵物疫苗資訊</a> <a
							class="dropdown-item" href="#">寵物食譜</a>
					</div></li>
			</ul>

		</div>

		<div>
			<ul class="navbar-nav my-2 my-lg-0">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">管理 </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<c:set value="1" var="check" /> 
					<c:if test="${check == 2}"  >
						<a class="dropdown-item" href="#">會員管理</a>
					</c:if>	
					<c:if test="${check == 1}"  >
						<a class="dropdown-item" href="<c:url value='/_03_ConvenienceProcess/Convenience_H.do' />">商家管理</a>
					</c:if>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="#"
					data-toggle="modal" data-target="#login"><p1>登入</p1> <p2>
						<img
							src="${pageContext.request.contextPath}/resources/images/member.svg"
							width="34px" height="auto" alt="" /></p2></a></li>
				<li class="nav-item"><a class="nav-link" href="#"><p1>購物車</p1>
						<p2> <img
							src="${pageContext.request.contextPath}/resources/images/cart.svg"
							width="34px" height="auto" alt="" /></p2></a></li>
			</ul>
		</div>
	</nav>
</header>

<!-- 登入 -->
<div class="modal fade" id="login" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalCenterTitle">登入</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">







				<form role="form">
					<div class="form-group">

						<span class="input input--juro"> <input
							class="input__field input__field--juro" type="text" id="input-28" />
							<label class="input__label input__label--juro" for="input-28">
								<span class="input__label-content input__label-content--juro">First
									Name</span>
						</label>
						</span>
					</div>
					<!-- /.form-group -->

					<div class="form-group">
						<div class="input-group">
							<input type="password" class="form-control" id="uPassword"
								placeholder="Password"> <label for="uPassword"
								class="input-group-addon glyphicon glyphicon-lock"></label>
						</div>
						<!-- /.input-group -->
					</div>
					<!-- /.form-group -->

					<div class="checkbox">
						<label> <input type="checkbox"> Remember me
						</label>
					</div>
					<!-- /.checkbox -->
				</form>











			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>



<%-- Javascript --%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>