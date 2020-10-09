<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<link rel="icon" href="<c:url value='/resources/images/logo_08_iP6_6.ico' />" type="image/x-icon" />
<title>商家管理系統_商家基本資料</title>
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



</head>
<body>
<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/navigation.jsp" />

<!-- Banner ---------------------------------------------------------------------------->
	<div
		class="container-fluid bannerImg d-flex justify-content-center align-items-center"
		style="background-image: url('<c:url value='/resources/images/_01_Member/merchant_banner_01.jpg' />')" 
		id="imageStellar" data-stellar-background-ratio="0.5">
		<div class="row">
			<div class="col-md-12 text-center">
				<h1 class="mt-3 bannerTitle animate__animated animate__fadeInDown">
					商家管理</h1>
				<div
					class="align-items-center banner-text animate__animated animate__fadeInUp">
					<h2 class="text-center"><i class="fas fa-address-card"></i>&nbsp;商家基本資料</h2>
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
										src="<c:url value='/_00_init/getHeadshot?account=${LoginOK.busAccount}' />"
										style="height:60px; width:60px; object-fit: cover" />
								</div>
							<!-- 用戶名稱與信箱 -->
								<div>
									<span class="userName mt-3">${LoginOK.busName}</span><br /> 
									<small class="mt-0 userMail">
									<i class="far fa-envelope"></i>&nbsp;${LoginOK.busAccount}</small>
								</div>
							</div>
							
					<!-- Part 2. 側邊選單內容 -->
							<ul>
								<li><a href="<c:url value='/MerchantManagementCenter' />"> <span class="icon"><i 
											class="fas fa-address-card"></i></span> <span class="list">商家基本資料</span>
								</a></li>
								<li><a href="<c:url value='/MerchantManagementCenter/MerchantUpdate' />"> <span class="icon"><i 
											class="fas fa-user-edit"></i></span> <span class="list">商家資料修改</span>
								</a></li>
								<li><a href="<c:url value='#' />"> <span class="icon"><i 
											class="fas fa-key"></i></span> <span class="list">修改密碼</span>
								</a></li>
								<li><a href="<c:url value='/MerchantChildManagementCenter' />"> <i 
											class="fas fa-store"></i> <span class="list">商家分店資料</span>
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
				<div class="card rightContent outerlayer3" style="height: auto;">
					<div class="container-fluid bgtype_opacity">
					
						<!-- 會員基本資料清單 -->
						<div class="row justify-content-start ml-5 animate__animated animate__fadeInLeft">

								<div class="m-3 col-lg-6 col-lg-offset-6">

									<form:form class="m-3" method="POST" modelAttribute="merchantBean" enctype='multipart/form-data'>
									
										<fieldset class="m-3 innerlayer3">
											<legend style="text-align:center; border: 1px solid black; border-radius: 10px; width:60%; background: gray; color: white"><b>商家基本資料</b></legend>
											
											<%-- FormContent --%>
											
											<div class="form-group has-feedback text-center">
												<img style="width: 200px; height: 200px; border: 1px solid gray; border-radius: 50%; object-fit: cover;" 
												src="<c:url value='/_00_init/getHeadshot?account=${LoginOK.busAccount}' />" id="show_image">
												<form:input  path="merchantMultipartFile" type="file" id="image_file" style="display: none;" disabled="true" /> 
											</div>
											
											<div class="form-group has-feedback border-top">
												<label class="mt-3"><b>帳號 / 電子信箱</b></label>
												<form:input path="busAccount" class="form-control" type="text" disabled="true" />
											</div>

											<div class="form-group has-feedback">
												<label><b>商家名稱</b></label>
												<form:input path="busName" class="form-control" type="text" disabled="true" />
											</div>

											<div class="form-group has-feedback">
												<label><b>電話</b></label>
												<form:input path='busTel' class="form-control" type="text" disabled="true" />
											</div>
											
											<div class="form-group has-feedback">
												<label><b>地址</b></label>
												<form:input path='busAddress' class="form-control" type="text" disabled="true" />
											</div>
											
											<div class="form-group has-feedback">
												<label><b>商家描述</b></label>
												<form:textarea path="busDescription" class="form-control" disabled="true" />
											</div>

										</fieldset>
										
									</form:form>
									
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
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
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

	<!-- navigation bar js ------------------------------------->
		<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->

</body>
</html>