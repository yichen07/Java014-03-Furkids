<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<link rel="icon"
	href="<c:url value='/resources/images/logo_08_iP6_6.ico' />"
	type="image/x-icon" />
<title>商家管理系統_商家基本資料</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<!-- Website Icon --------------------------------------------------------------------->
<link rel="icon"
	href="<c:url value='/resources/images/logo_08_iP6_6.ico' />"
	type="image/x-icon" />

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
					<h2 class="text-center">
						<i class="fas fa-address-card"></i>&nbsp;商家基本資料
					</h2>
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
										style="height: 60px; width: 60px; object-fit: cover" />
								</div>
								<!-- 用戶名稱與信箱 -->
								<div>
									<span class="userName mt-3">${LoginOK.busName}</span><br /> <small
										class="mt-0 userMail"> <i class="far fa-envelope"></i>&nbsp;${LoginOK.busAccount}
									</small>
								</div>
							</div>

							<!-- Part 2. 側邊選單內容 -->
							<ul>
								<li><a href="<c:url value='/MerchantManagementCenter' />">
										<span class="icon"><i class="fas fa-address-card"></i></span>
										<span class="list">商家基本資料</span>
								</a></li>
								<li><a
									href="<c:url value='/MerchantManagementCenter/MerchantUpdate' />">
										<span class="icon"><i class="fas fa-user-edit"></i></span> <span
										class="list">商家資料修改</span>
								</a></li>
								<li><a href="<c:url value='#' />" data-toggle="modal"
									data-target="#passwordUpdate"> <span class="icon"><i
											class="fas fa-key"></i></span> <span class="list">修改密碼</span>
								</a></li>
								<li><a
									href="<c:url value='/MerchantChildManagementCenter' />"> <i
										class="fas fa-store"></i> <span class="list">商家分店資料</span>
								</a></li>
							</ul>


							<!-- Part 3. 側邊選單伸縮按鈕 -->
							<div class="hamburger">
								<div class="inner_hamburger">
									<span class="arrow"> <i
										class="fas fa-long-arrow-alt-left"></i> <i
										class="fas fa-long-arrow-alt-right"></i>
									</span>
								</div>
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
				<div class="card rightContent sideBarBgType" style="height: auto;">
					<div class="container-fluid bgtype_opacity">

						<!-- 商家分店清單 -->

						<!-- Card View 清單 -->
						<div class="row justify-content-center">
							<c:forEach var="merchantChild" items="${merchantChildList}">
								<div class="card m-3 col-xm-2 shadow"
									style="width: 18rem; border-radius: 10px">
									<div class="card-body text-center">
										<img
											style="width: 200px; height: 200px; border: 1px solid gray; border-radius: 50%; object-fit: cover;"
											src="<c:url value='/_03_FriendlySystem/getPicture/${merchantChild.busChildNo}' />">
										<h5 class="card-title mt-2">${merchantChild.busChildName}</h5>

									</div>
									<ul class="list-group list-group-flush">
										<li class="list-group-item">分店信箱：${merchantChild.busChildEmail}</li>
										<li class="list-group-item">分店電話：${merchantChild.busChildTel}</li>
									</ul>
									<div class="card-body text-center">
										<a href="<c:url value='/MerchantChildManagementCenter/${merchantChild.busChildNo}' />"
											class="card-link"><button class="btn btn-primary">修改</button></a>
									</div>
								</div>
							</c:forEach>
						</div>


						<!--  	修改  -->
						<!--  	Modal  -->


						<div class="modal fade" id="alertCb" tabindex="-1" role="dialog"
							aria-labelledby="exampleModalCenterTitle" aria-hidden="true"
							style="z-index: 99991">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLongTitle"
											style="color: #0090d3">
											<b>分店資料修改</b>
										</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<form:form method="POST" modelAttribute="merchantChildBean"
										enctype='multipart/form-data'>
										<div class="modal-body">

											<div class="form-group text-center">
												<c:if test="${!empty merchantChildBean.busChildNo}">
													<img
														style="width: 200px; height: 200px; border: 1px solid gray; border-radius: 50%; object-fit: cover;"
														src="<c:url value='/_03_FriendlySystem/getPicture/${merchantChildBean.busChildNo}' />"
														id="show_image">
													<form:input path="merchantChildMultipartFile" type="file"
														id="image_file" style="display: none;" />
												</c:if>
												<div
													style="position: relative; z-index: 1; top: -30px; left: 90px; font-size: 25px; color: darkblue">
													<a type="button" id="upload_image"><i
														class="fas fa-plus-square"></i></a>
												</div>
											</div>
											<hr>

											<div class="form-group">
												<label class="col-form-label"><b>分店名稱:</b></label>
												<form:input type="text" class="form-control" path="busChildName" />
											</div>
											<div class="form-group">
												<label class="col-form-label"><b>分店信箱:</b></label>
												<form:input type="text" class="form-control" path="busChildEmail" />
											</div>
											<div class="form-group">
												<label class="col-form-label"><b>分店電話:</b></label>
												<form:input type="text" class="form-control" path="busChildTel" />
											</div>
											<div class="form-group">
												<label class="col-form-label"><b>分店地址:</b></label>
												<form:input type="text" class="form-control" path="busChildAddress" />
											</div>
											<div class="form-group">
												<label class="col-form-label"><b>分店描述:</b></label>
												<form:textarea class="form-control" path="busChildDescription" />
											</div>

										</div>
										<div class="modal-footer justify-content-center">
											<button type="submit" class="btn btn-primary">確定修改</button>
										</div>
									</form:form>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<!-- Right Content End-->

		<!-- MAIN End--------------------------------------------------------------------------->

		<!-- 修改密碼Modal_Start -->

		<!-- Button trigger modal -->
		<!-- 	<button type="button" class="btn btn-primary" data-toggle="modal" -->
		<!-- 		data-target="#exampleModalCenter">Launch demo modal</button> -->

		<!-- Modal -->
		<div class="modal fade" id="passwordUpdate" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true" data-backdrop="static" data-keyboard="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="exampleModalCenterTitle"
							style="color: #0090d3">
							<b>修改密碼</b>
						</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<!-- 密碼修改表單_Start -->
					<form:form class="m-3" method="POST" modelAttribute="merchantBean"
						action="${pageContext.request.contextPath}/MerchantManagementCenter/PasswordUpdate">
						<div class="modal-body">

							<div class="form-group">
								<label for="exampleInputPassword"><b>舊密碼</b></label> <input
									type="password" class="form-control" name="oldPassword"
									id="oldPassword" placeholder="請輸入舊密碼"> <font
									class="errhide" color="red">${OldPasswordError}</font>
							</div>

							<div class="form-group">
								<label for="exampleInputPassword"><b>新密碼</b></label>
								<form:input type="password" class="form-control"
									path="busPassword" id="busPassword" placeholder="請輸入新密碼" />
								<font class="errhide" color="red"><form:errors
										path="busPassword" cssClass="errors" /></font>
							</div>

							<div class="form-group">
								<label for="exampleInputPassword"><b>新密碼確認</b></label>
								<form:input type="password" class="form-control"
									path="confirmPassword" id="confirmPassword" placeholder="新密碼確認" />
								<font class="errhide" color="red"><form:errors
										path="confirmPassword" cssClass="errors" /></font>
							</div>

						</div>
						<div class="modal-footer justify-content-center">
							<button type="submit" class="btn btn-primary">提交</button>
						</div>
					</form:form>
					<button type="reset" class="btn btn-outline-primary"
						id="passwordClear" hidden>重設</button>

					<!-- 密碼修改表單_End -->

				</div>
			</div>
		</div>

		<!-- 修改密碼Modal_End -->

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
		<script
			src="<c:url value='/resources/javascript/jquery.stellar.js' />"></script>

		<!-- JavaScript Plug-in End------------------------------------------------------------->
		<script
			src="<c:url value='/resources/javascript/memberManagement.js' />"></script>

		<!-- navigation bar js ------------------------------------->
		<jsp:include page="/fragment/navigation_determine.jsp" />
		<!-- navigation bar js End------------------------------------->

		<!-- 上傳圖片 js -->
		<script type="text/javascript">
			$("#upload_image").click(function(e) {
				document.getElementById("image_file").click();
			});
	
			$("#image_file").on("change", function(event) {
				const file = event.target.files[0];
				let readFile = new FileReader();
				readFile.readAsDataURL(file);
				readFile.addEventListener("load", function(e) {
					let image = document.getElementById("show_image");
					image.src = this.result;
				});
			});
		</script>


	<!-- 修改密碼呈現Javascript -->
		<script type="text/javascript">
			$('#passwordClear').click(function() {
				document.getElementById("oldPassword").value = '';
				document.getElementById("busPassword").value = '';
				document.getElementById("confirmPassword").value = '';
			});

			$('#passwordUpdate').on('show.bs.modal', function() {
				document.getElementById("passwordClear").click();
			})
		</script>

		<%-- 修改密碼時，如有錯誤，重新導回基本資料畫面 --%>
		<c:if test="${!empty PasswordUpdateError || !empty OldPasswordError}">
			<script>
				$('#passwordUpdate').modal('show')
			</script>
		</c:if>
		<%-- 關閉Modal隱藏錯誤訊息 --%>
		<script type="text/javascript">
			$("#passwordUpdate").on("hidden.bs.modal", function(e) {
				$('.errhide').css('display', 'none');
			});
		</script>

		<!-- 修改密碼呈現Javascript_End -->

		<%-- 修改分店基本資料_Start --%>
		<c:if test="${!empty aaalert}">
			<button id="alterCb" class="dropdown-item btn" type="button"
				data-toggle="modal" data-target="#alertCb" style="display: none"></button>
			<script type="text/javascript">
				$('#alterCb').trigger('click');
			</script>
		</c:if>
		<%-- 修改分店基本資料_End --%>
</body>
</html>