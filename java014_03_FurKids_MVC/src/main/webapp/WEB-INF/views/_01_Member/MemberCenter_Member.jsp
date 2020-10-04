<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<!-- Website Icon --------------------------------------------------------------------->
<link rel="icon" href="<c:url value='/resources/images/logo_08_iP6_6.ico' />" type="image/x-icon" />

<!-- Bootstrap CSS --------------------------------------------------------------------->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous" />

<!-- animate.style CSS ----------------------------------------------------------------->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />

<!-- Other CSS ----------------------------------------------------------------->
<link rel="stylesheet"
	href="<c:url value='/resources/css/_01_Member/memberManagement.css' />" />

<!--------------------------------------------------------------------- Import CSS End-->


<title>會員管理系統_會員基本資料</title>
</head>
<body>
<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/navigation.jsp" />

<!-- Banner ---------------------------------------------------------------------------->
	<div
		class="container-fluid bannerImg d-flex justify-content-center align-items-center"
		style="background-image: url(<c:url value='/resources/images/_01_Member/member_banner_01.jpg' />)" 
		id="imageStellar" data-stellar-background-ratio="0.5">
		<div class="row">
			<div class="col-md-12 text-center">
				<h1 class="mt-3 bannerTitle animate__animated animate__fadeInDown">
					會員管理</h1>
				<div
					class="align-items-center banner-text animate__animated animate__fadeInUp">
					<h2 class="text-center"><i class="fas fa-address-card"></i>&nbsp會員基本資料</h2>
				</div>		
				<div
					class="align-items-center banner-text animate__animated animate__fadeInUp">
					<h5 class="text-center">專心專注，只為寵物</h5>
				</div>
			</div>
		</div>
	</div>
<!------------------------------------------------------------------------- Banner End -->

<!-- Main Start--------------------------------------------------------------------------->
	<div class="container-fluid mainContent">
		<!-- Slide Bar Start -->
		<div class="sidebar_initial float-left sticky-top">
			<div class="wrapper row">
				<div class="main_body">
					<div class="sidebar_menu">
						<div class="inner__sidebar_menu">
						
					<!-- Part 1. 用戶基本資訊 -->
							<div class="d-flex mb-3 userImg">
							<!-- 用戶大頭照 -->
								<div class="mr-3">
									<img class="userImgConer"
										src="https://randomuser.me/api/portraits/women/60.jpg"
										height="60px" width="60px" />
								</div>
							<!-- 用戶名稱與信箱 -->
								<div>
									<span class="userName mt-3">Adora</span><br /> 
									<small class="mt-0 userMail">
									<i class="far fa-envelope"></i> adora@gmail.com</small>
								</div>
							</div>
							
					<!-- Part 2. 側邊選單內容 -->
							<ul>
								<li><a href="<c:url value='/MemberManagementCenter' />"> <span class="icon"><i 
											class="fas fa-address-card"></i></span> <span class="list">會員基本資料</span>
								</a></li>
								<li><a href="<c:url value='#' />"> <span class="icon"><i 
											class="fas fa-user-edit"></i></span> <span class="list">會員資料修改</span>
								</a></li>
								<li><a href="<c:url value='#' />"> <span class="icon"><i 
											class="fas fa-key"></i></span> <span class="list">修改密碼</span>
								</a></li>
								<li><a href="<c:url value='/PetManagementCenter' />"> <span class="icon"><i 
											class="fas fa-paw"></i></span> <span class="list">會員寵物資料</span>
								</a></li>
							</ul>


					<!-- Part 3. 側邊選單伸縮按鈕 -->
							<div class="hamburger">
								<div class="inner_hamburger">
									<span class="arrow"> 
										<i class="fas fa-long-arrow-alt-left"></i> 
										<i class="fas fa-long-arrow-alt-right"></i>
									</span>
								</div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Slide Bar End-->
		
		<!-- Right Content Start-->
		<!-- Part 4. 內容顯示 -->
		<div class="row">
			<div class="col-lg-12">
				<div class="card rightContent" style="height: auto;">
					<div class="container-fluid">
					
					<!-- 會員基本資料清單 -->
						<div class="row justify-content-center">

								<div class="card m-3 col-lg-6 col-lg-offset-6" style="border-radius: 10px;">

									<form action="" class="m-3">
										<fieldset style="border: 1px solid black; border-radius: 10px;">
											<legend style="text-align:center; border: 1px solid black; border-radius: 10px; width:60%;">會員基本資料</legend>
											
											<div class="form-group has-feedback">
												<label for="username">帳號</label>
												<input>
												<div class="input-group">
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-user"></span></span> <input
														id="username" class="form-control"
														placeholder="請使用電子信箱進行帳號設定" type="text">
												</div>

											</div>

											<div class="form-group has-feedback">
												<label for="password">密碼</label>
												<div class="input-group">
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-lock"></span></span> <input
														id="password" class="form-control" placeholder="請輸入密碼"
														type="password">
												</div>

												
											</div>

											<div class="form-group has-feedback">
												<label for="passwordConfirm">密碼確認</label>
												<div class="input-group">
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-lock"></span></span> <input
														id="passwordConfirm" class="form-control"
														placeholder="請再次確認密碼" type="password">
												</div>

												
											</div>


											<div class="form-group has-feedback">
												<label for="phoneNum">手机号码</label>
												<div class="input-group">
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-phone"></span></span> <input
														id="phoneNum" class="form-control" placeholder="请输入手机号码"
														maxlength="11" type="text">
												</div>
												
											</div>


											<div class="form-group col-xs-6">
												<input class="form-control btn btn-primary" id="submit"
													value="立&nbsp;&nbsp;即&nbsp;&nbsp;注&nbsp;&nbsp;册"
													type="submit">
											</div>

											<div class="form-group col-xs-6">
												<input value="重置" id="reset"
													class="form-control btn btn-danger" type="reset">
											</div>

										</fieldset>
									</form>
								</div>


						</div>
						
					</div>
				</div>
			</div>
		</div>
		<!-- Right Content End-->
	</div>

	<!-- MAIN End--------------------------------------------------------------------------->


	<!-- Footer Start----------------------------------------------------------------------->
<!-- 引入簡易的頁尾 -->
<jsp:include page="/fragment/footer_brief.jsp" />



<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS ----------------------------------->
<!-- 	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" -->
<!-- 		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" -->
<!-- 		crossorigin="anonymous"></script> -->
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS End-------------------------------->

	<!-- JavaScript Plug-in ---------------------------------------------------------------->

	<!-- icon -->
	<script src="https://kit.fontawesome.com/8e822d04fb.js"
		crossorigin="anonymous"></script>

	<!-- banner effect -->
	<script src="<c:url value='/resources/javascript/jquery.stellar.js' />"></script>

	<!-- JavaScript Plug-in End------------------------------------------------------------->
	<script src="<c:url value='/resources/javascript/memberManagement.js' />"></script>


</body>
</html>