<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8" />
<link rel="icon" href="<c:url value='/resources/images/logo_08_iP6_6.ico' />" type="image/x-icon" />
<title>商家註冊</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<!-- Bootstrap CSS --------------------------------------------------------------------->

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous" />

<!-- animate.style CSS ----------------------------------------------------------------->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />

<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<!-- Inport CSS End--------------------------------------------------------------------->

<link rel="stylesheet"
	href="<c:url value='/resources/css/_01_Member/MerchantRegistration.css' />" />

<!-- Input CSS End---------------------------------------------------------------------->


</head>

<body>

	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/navigation.jsp" />

	<!-- Main Form Start------------------------------------------------------------------------->
	<section class="imgBackground">
		<div class="container-fluid">
			<div class="row wrapperOut">
				<!-- Left Content Start ---------------------->
				<div
					class="col-xl-7 col-lg-8 col-md-10 col-12 mt-5 wrapper my-md-5 my-0 ml-md-5 ml-0 mb-5 pb-5 mt-lg-5 animate__animated animate__fadeInLeft">	
					
					<form:form class="mt-0" method="POST" modelAttribute="merchantBean" enctype='multipart/form-data'>
						<div class="px-1">
							<div class="row">
								<div class="col-12 d-flex justify-content-center">
									<div class="mb-4">
										<h2 class="formTitle">商家註冊
										<span style="font-size: 16px">(切換至<a href="<c:url value='/MemberRegistration' />" >會員註冊</a>)</span>
										</h2>
									</div>
								</div>
							</div>

							<!-- 基本資料 Start -->
							<div
								class="row d-flex justify-content-center align-items-center border-bottom mb-4">
								<div
									class="col-3 d-flex-column justify-content-center align-items-center text-center">
									<div class="mb-1">
										<img src="<c:url value='resources/images/_01_Member/user-circle-solid.svg'/>" class="leftUserIcon" id="show_image" style="height: 50px; width: 50px" />
									</div>
									<div class="pb-3">
										<h5 class="leftTitle">基本資料</h5>
									</div>
								</div>
								<div class="col-9 pr-5">
									<div class="input-group">
										<form:input type="email" path='busAccount' required="" class="w-80"
											id="inputStyle" /> <span class="colTitle">帳號 / 電子信箱</span>
										<form:errors path="busAccount" cssClass="error" style="color: red" />
									</div>
									<div class="input-group">
										<form:input type="text" path="busName" required="" class="w-80"
											id="inputStyle" /> <span class="colTitle">姓名</span>
										<form:errors path="busName" cssClass="error" style="color: red" />
									</div>
									<div class="buybtnP mb-4">
										<form:input type='file' path="merchantMultipartFile" style="display: none" id="image_file"/>
										<button type="button" class="btn-block ripple buybtn" id="upload_image">
											<i class="fas fa-camera-retro mr-2"> <span
												class="btnText align-self-center">上傳照片</span>
											</i>
										</button>
										<form:errors path="merchantMultipartFile"  cssClass="error" style="color: red" />
									</div>
								</div>
							</div>
							<!-- 基本資料 End -->
							<!-- 密碼 Start -->
							<div
								class="row d-flex justify-content-center align-items-center border-bottom mb-4">
								<div
									class="col-3 d-flex-column justify-content-center align-items-center text-center">
									<div class="mb-1">
										<i class="fas fa-key leftIcon"></i>
									</div>
									<div class="pb-3">
										<h5 class="leftTitle">密碼</h5>
									</div>
								</div>
								<div class="col-9 pr-5">
									<div class="input-group">
										<form:input type="password" path='busPassword' required="" id="eyeInput" /> <span
											class="colTitle">密碼</span> <span class="eyePosition"><i
											class="far fa-eye-slash" id="eyeHide" onclick="eyeFunction()"></i></span>
										<span class="eyePosition"><i class="far fa-eye"
											id="eyeShow" onclick="eyeFunction()"></i></span>
										<form:errors path="busPassword" cssClass="error" style="color: red" />
									</div>
									<div class="input-group">
										<form:input type="password" path='confirmPassword' required="" id="eyeInputCheck" />
										<span class="colTitle">密碼確認</span> <span class="eyePosition"><i
											class="far fa-eye-slash" id="eyeHideCheck"
											onclick="eyeFunctionCheck()"></i></span> <span class="eyePosition"><i
											class="far fa-eye" id="eyeShowCheck"
											onclick="eyeFunctionCheck()"></i></span>
										<form:errors path="confirmPassword" cssClass="error" style="color: red" />
									</div>
								</div>
							</div>
							<!-- 密碼 End -->
							<!-- 其他資料 Start -->
							<div
								class="row d-flex justify-content-center align-items-center border-bottom mb-4">
								<div
									class="col-3 d-flex-column justify-content-center align-items-center text-center">
									<div class="mb-1">
										<i class="fas fa-info-circle leftIcon"></i>
									</div>
									<div class="pb-3">
										<h5 class="leftTitle">其他資料</h5>
									</div>
								</div>
								<div class="col-9 pr-5">
									<div class="input-group">
										<form:input type="text" path='busAddress' required="" class="w-80"
											id="inputStyle" /> <span class="colTitle">地址</span>
										<form:errors path="busAddress" cssClass="error" style="color: red" />
									</div>
									<div class="input-group">
										<form:input type="text" path='busTel' required="" class="w-80"
											id="inputStyle" /> <span class="colTitle">電話</span>
										<form:errors path="busTel" cssClass="error" style="color: red" />
									</div>
								</div>
							</div>
							<!-- 其他資料 End -->
							<!-- 商家描述 Start-->
							<div
								class="row d-flex justify-content-center align-items-center border-bottom mb-4">
								<div
									class="col-3 d-flex-column justify-content-center align-items-center text-center">
									<div class="mb-1">
										<i class="fas fa-store fa-fw leftIcon"></i>
									</div>
									<div class="pb-3">
										<h5 class="leftTitle">商家描述</h5>
									</div>
								</div>
								<div class="col-9 pr-5">
									<div class="input-group">
										<form:textarea path="busDescription" class="form-control noteborder" rows="4"
											placeholder="請輸入對您商店的描述"></form:textarea>
										<form:errors path="busDescription"  cssClass="error" style="color: red" />
									</div>

								</div>
							</div>
							<!-- 商家描述 End-->
							<!-- 確認表單 Start-->
							<div class="commitOrder row mt-0">
								<div
									class="checkbox-wrapper col-12  ml-2 d-flex justify-content-center">
									<input type="checkbox" id="checkTerms" hidden /><label
										for="checkTerms" class="checkStyle"></label>
									<div class="checkboxText ml-2 mb-1 pr-4">
										我已閱讀條款並同意FurKids有權於任何時間修改或變更本服務條款內容，修改後將公布本網站上，FurKids不再個別通知會員，建議會員隨時注意該等修改或變更。會員於任何修改或變更後繼續使用本網站服務時，視為會員已瞭解並同意接受該等修改或變更。
									</div>
								</div>
							</div>
							<div class="buybtnP mt-3 mb-0 mr-2">
								<div class="col-12 mr-0 d-flex justify-content-end">
									<input type="submit" name="submit" id="submit" class="ripple buybtn"/>
									<input type="reset" name="cancel" id="cancel" class="ripple buybtnOutline" />
								</div>
							</div>

							<!-- 確認表單 End-->
						</div>
					</form:form>
					
				</div>

				<!-- Left Content End ------------------------>
			</div>
		</div>
	</section>

	<!-- Main Form End--------------------------------------------------------------------------->
	
	

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
	
	<!-- Taiwan Address -->
<!-- 	<script type="text/javascript" -->
<!-- 		src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script> -->
<%--     <script src="<c:url value='/resources/javascript/jquery.twzipcode_mvc.js' />"></script> --%>

	<!-- JavaScript Plug-in End------------------------------------------------------------->
	<script
		src="<c:url value='/resources/javascript/MerchantRegistration.js' />"></script>
		
	<!-- navigation bar js ------------------------------------->
		<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->
	
	<!-- 上傳圖片 js -->
	<script type="text/javascript">
	$("#upload_image").click(function (e) {
        document.getElementById("image_file").click();
      });

      $("#image_file").on("change", function (event) {
        const file = event.target.files[0];
        let readFile = new FileReader();
        readFile.readAsDataURL(file);
        readFile.addEventListener("load", function (e) {
            let image = document.getElementById("show_image");
            image.src = this.result;
          });
      });
      </script>
	
	
</body>
</html>