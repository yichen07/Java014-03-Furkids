<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon"
	href="<c:url value='/resources/images/logo_08_iP6_6.ico' />"
	type="image/x-icon" />

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

<!-- Inport CSS End--------------------------------------------------------------------->

<link rel="stylesheet"
	href="<c:url value='/resources/css/_05_HealthControl/CalorieCalculation.css' />" />

<!-- Input CSS End---------------------------------------------------------------------->

<title>寵物健康管理_寵物熱量計算</title>
</head>
<body>
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/navigation.jsp" />

	<!-- Banner ---------------------------------------------------------------------------->

	<div
		class="container-fluid bannerImg d-flex justify-content-center align-items-center"
		id="imageStellar" data-stellar-background-ratio="0.5" 
		style="background-image: url(<c:url value='/resources/images/_05_HealthControl/HealthControl_banner.jpg' />)">
		<div class="row">
			<div class="col-md-12 text-center">
				<h1 class="mt-3 bannerTitle animate__animated animate__fadeInDown">
					寵物熱量計算</h1>
				<div
					class="align-items-center banner-text animate__animated animate__fadeInUp">
					<h5 class="text-center">過重會影響寵物的整體健康、福祉及生活品質，擁有健康體重的寵物</h5>
				</div>
			</div>
		</div>
	</div>

	<!-- Banner End------------------------------------------------------------------------->

	<!-- Main Start------------------------------------------------------------------------->
	<section class="overlay">
		<div class="container my-5">
			<div class="row bg-darker wrapper">
				<div class=" col-md-5  leftImg"></div>
				<div class=" col-md-7 col-12 px-5">
					<div class="d-flex justify-content-center">
						<h2 class="formTitle mt-4 mb-4">寵物熱量計算</h2>
					</div>
					<!-- --------------------------- -->

					<!-- 貓 Start -->

					<div
						class="row d-flex justify-content-center align-items-center border-bottom mb-4">
						<div
							class="col-3 d-flex-column justify-content-center align-items-center text-center">
							<div class="mb-0">
								<i class="fas fa-cat leftIcon leftIcon2"></i>
							</div>
							<div class="pb-5">
								<h5 class="leftTitle">貓</h5>
							</div>
						</div>
						<div class="col-9 pr-5">
							<div class="input-group mb-4">
								<input type="text" class="inputClass" id="inputCatWeight"
									onkeyup="value=value.replace(/[^\d.]/g,'')" />
								<label for="">寵物體重 (Kg)</label> <span>寵物體重 (Kg)</span>
							</div>
							<div class="input-group mb-4">
								<input type="text" name="name" class="inputClass"
									id="catEatCount" input
									onkeyup="this.value=this.value.replace(/\D/g,'')"
									onafterpaste="this.value=this.value.replace(/\D/g,'')" />
								<label for="">每日餐數</label> <span>每日餐數</span>
							</div>
							<!-- 貓情況選擇 Start -->
							<select name="petsCat"
								class="d-flex justify-content-center align-items-center mb-4 w-100"
								id="selectCat">
								<option value=" " selected>請選擇寵物狀態</option>
								<option value="1.4">健康成貓:一般(未結紮)</option>
								<option value="1.2">健康成貓:一般(結紮後)</option>
								<option value="1.0">健康成貓:肥胖傾向</option>
								<option value="2.5">健康幼貓</option>
							</select>
							<!-- 貓情況選擇 End -->

							<div class="d-flex justify-content-end mb-3">
								<h2 class="mt-0 mr-2" id="totalCatCal"></h2>
								<h6 class="calUnitePosition" id="catCalUnite"></h6>
							</div>
						</div>
					</div>

					<!-- 貓 End -->

					<!-- 狗 Start -->

					<div
						class="row d-flex justify-content-center align-items-center mb-3 mt-5 border-bottom">
						<div
							class="col-3 d-flex-column justify-content-center align-items-center text-center">
							<div class="mb-0">
								<i class="fas fa-dog leftIcon leftIcon2"></i>
							</div>
							<div class="pb-5">
								<h5 class="leftTitle">狗</h5>
							</div>
						</div>
						<div class="col-9 pr-5">
							<div class="input-group mb-4">
								<input type="text" name="name" class="inputClass"
									id="inputDogWeight" onkeyup="value=value.replace(/[^\d.]/g,'')" />
								<label for="">寵物體重 (Kg)</label> <span>寵物體重 (Kg)</span>
							</div>
							<div class="input-group mb-4">
								<input type="text" name="name" class="inputClass"
									id="DogEatCount" input
									onkeyup="this.value=this.value.replace(/\D/g,'')"
									onafterpaste="this.value=this.value.replace(/\D/g,'')" />
								<label for="">每日餐數</label> <span>每日餐數</span>
							</div>

							<!-- 狗情況選擇 Start -->
							<select name="petsDog "
								class="d-flex justify-content-center align-items-center mb-4 w-100"
								id="selectDog">
								<option value=" " selected>請選擇寵物狀態</option>
								<option value="1.8">健康成犬:一般(未結紮)</option>
								<option value="1.6">健康成犬:一般(結紮後)</option>
								<option value="1.4">健康成犬:肥胖傾向</option>
								<option value="3">健康幼犬:年齡小於4個月</option>
								<option value="2">健康幼犬:年齡大於4個月</option>
							</select>
							<!-- 狗情況選擇 End -->
							<div class="d-flex justify-content-end mb-3">
								<h2 class="mt-0 mr-2" id="totalDogCal"></h2>
								<h6 class="calDogUnitePosition" id="dogCalUnite"></h6>
							</div>
						</div>
					</div>

					<!-- 狗 End -->
					<div class="justify-content-center mb-4 mt-0">
						<h5 class="infoText">資料來源：</h5>
						<ul class="infoSource">
							<li>1.
								WSAVA寵物體態分級：https://petobesityprevention.org/pet-weight-check/</li>
							<li>2. MERCK-Nutritional Requirements and Related Diseases
								of Small Animalsby Sherry Lynn Sanderson, BS, DVM, PhD, DACVIM,
								DACVN, Associate Professor, Department of Physiology and
								Pharmacology, College of Veterinary Medicine, University of
								Georgia</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- background -->
		</div>
	</section>

	<!-- 引入詳細的頁尾 -->
	<jsp:include page="/fragment/footer_detail.jsp" />
	<!-- Footer End------------------------------------------------------------------------->
	
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS ----------------------------------->
    <script
      src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
      integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
      integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
      integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
      crossorigin="anonymous"
    ></script>
    <!-- jQuery first, then Popper.js, then Bootstrap JS End-------------------------------->

    <!-- JavaScript Plug-in ---------------------------------------------------------------->

    <!-- icon -->
    <script
      src="https://kit.fontawesome.com/8e822d04fb.js"
      crossorigin="anonymous"
    ></script>

    <!-- banner effect -->
	<script src="<c:url value='/resources/javascript/jquery.stellar.js' />"></script>
    <!-- JavaScript Plug-in End------------------------------------------------------------->
    <script src="<c:url value='/resources/javascript/CalorieCalculation.js' />"></script>
    
    
    <!-- navigation bar js ------------------------------------->
	<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->
	



</body>
</html>