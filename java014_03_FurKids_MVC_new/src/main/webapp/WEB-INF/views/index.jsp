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
	href="<c:url value='/resources/css/_00_Home/Home.css' />" />

<link rel="stylesheet"
	href="<c:url value='/resources/css/CardView/CardA.css' />" />
<!-- Input CSS End---------------------------------------------------------------------->


<title>Furkids Main Page</title>
</head>

<body>

	<!-- ***** Main Menu Area Start ***** -->
	<div class="mainMenu d-flex align-items-center justify-content-between">
		<!-- Close Icon -->
		<div class="closeIcon">
			<i class="fas fa-times" aria-hidden="true"></i>
		</div>
		<!-- Logo Area -->
		<div class="logo-area">
			<a href="<c:url value='/' />"><img
				src="<c:url value='/resources/images/Logo-06.png' />"
				style="height: 35px; -webkit-filter: drop-shadow(12px 12px 7px rgba(0, 0, 0, 0.7)); filter: drop-shadow(2px 2px 2px rgb(0, 0, 0));"></a>
		</div>
		<!-- Nav -->
		<div class="sonarNav wow fadeInUp align-items-center"
			data-wow-delay="1s">
			<nav>
				<ul>
<!-- 					<li class="nav-item active"><a class="nav-link" -->
<%-- 						href="<c:url value='/_02_ShoppingSystem/DisplayPageProducts' />">寵物商城</a> --%>
<!-- 					</li> -->
					<li class="nav-item active"><a class="nav-link"
						href="<c:url value='/_03_listProducts/DisplayPageProducts2' />">寵物商城</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='http://127.0.0.1:8081/' />">寵物交流版</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/_03_FriendlySystem/Reservation/景點' />">寵物旅遊</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/_03_FriendlySystem/Reservation/餐廳' />">餐廳</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/_03_FriendlySystem/Reservation/寵物美容' />">寵物美容</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/_03_FriendlySystem/Reservation/寵物旅館' />">寵物旅館</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/_05_HealthControl/CalorieCalculation' />">寵物熱量管理</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/BlogIndex' />">寵物健康專欄</a></li>
				</ul>
			</nav>
		</div>
		<!-- Copwrite Text -->
		<div class="copywrite-text">
			<p>Copyright © 2020 FurKids Inc.</p>
		</div>
	</div>
	<!-- ***** Main Menu Area End ***** -->

	<!-- ***** Header Area Start ***** -->
	<header class="header-area">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12">
					<div class="menu-area d-flex justify-content-between">
						<!-- Logo Area  -->
						<div class="logo-area">
							<a href="<c:url value='/' />"><img
								src="<c:url value='/resources/images/Logo-06.png '/>"
								style="height: 36px; -webkit-filter: drop-shadow(12px 12px 7px rgba(0, 0, 0, 0.7)); filter: drop-shadow(2px 2px 2px rgba(0, 0, 0, 0.829));"></a>
						</div>

						<div class="menu-content-area d-flex align-items-center">
							<!-- Header Social Area -->
							<div class="header-social-area d-flex align-items-center">


								<%-- 登入、購物車、登出功能列 --%>
								<div class="form-inline my-2 my-lg-0">
									<c:choose>
										<%-- 訪客登入 --%>
										<c:when test="${empty LoginOK}">
											<a href="<c:url value='#' />" data-toggle="modal"
												data-target="#login"> <span data-toggle="tooltip"
												data-placement="bottom" title="會員登入"><i
													class="fas fa-user iconShadow" aria-hidden="true"></i></span>
											</a>
										</c:when>
										<%-- 會員(已登入) --%>
										<c:when test="${LoginOK.CLASSIFY == 0}">
											<div class="dropdown nav-item">
												<a class="nav-link" href="<c:url value='#' />"
													id="navbarDropdown" role="button" data-toggle="dropdown"
													aria-haspopup="true" aria-expanded="false"><img
													src="<c:url value='/_00_init/getHeadshot?account=${LoginOK.cusAccount}' />"
													style="width: 30px; height: 30px; border-radius: 50%; border: 1px solid gray; object-fit: cover"
													data-toggle="tooltip" data-placement="bottom" title="會員中心" />
												</a>
												<div class="dropdown-menu" aria-labelledby="navbarDropdown"
													style="background-color: #dededecc">
													<a class="dropdown-item"
														href="<c:url value='/PetRegistration' />"
														style="color: #002255"><b>寵物新增</b></a> <a
														class="dropdown-item"
														href="<c:url value='/MemberManagementCenter' />"
														style="color: #002255"><b>會員管理</b></a> <a
														class="dropdown-item"
														href="<c:url value='/_03_FriendlySystem/MemReservationDetail' />"
														style="color: #002255"><b>預約管理</b></a>
														<a
														class="dropdown-item"
														href="<c:url value='/_05_orderProcess/orderList' />"
														style="color: #002255"><b>訂單查詢</b></a>
												</div>
											</div>
										</c:when>
										<%-- 商家(已登入) --%>
										<c:when test="${LoginOK.CLASSIFY == 1}">
											<div class="dropdown nav-item">
												<a class="nav-link" href="<c:url value='#' />"
													id="navbarDropdown" role="button" data-toggle="dropdown"
													aria-haspopup="true" aria-expanded="false"> <img
													src="<c:url value='/_00_init/getHeadshot?account=${LoginOK.busAccount}' />"
													style="width: 30px; height: 30px; border-radius: 50%; border: 1px solid gray; object-fit: cover"
													data-toggle="tooltip" data-placement="bottom" title="商家中心" />
												</a>
												<div class="dropdown-menu" aria-labelledby="navbarDropdown"
													style="background-color: #dededecc">
													<a class="dropdown-item"
														href="<c:url value='/MerchantChildRegistration' />"
														style="color: #002255"><b>分店新增</b></a> <a
														class="dropdown-item"
														href="<c:url value='/MerchantManagementCenter' />"
														style="color: #002255"><b>商家管理</b></a> <a
														class="dropdown-item"
														href="<c:url value='/_03_FriendlySystem/ViewSessionStatus_setComplete' />"
														style="color: #002255"><b>商家服務上架</b></a> <a
														class="dropdown-item"
														href="<c:url value='/_03_FriendlySystem/busReservationDetail' />"
														style="color: #002255"><b>預約明細</b></a>
												</div>
											</div>
										</c:when>
									</c:choose>

									<%-- 登出 --%>
									<c:if test="${ ! empty LoginOK }">
										<a href="<c:url value='#' />" class="logout"
											onclick="logout()" data-toggle="tooltip"
											data-placement="bottom" title="登出"> <i
											class="fas fa-sign-out-alt fa-lg iconShadow" style="color: white"></i>
										</a>
									</c:if>

									<%-- 購物車 --%>
<%-- 									<a href="<c:url value='/_02_ShoppingSystem/ShowCartContent' />" --%>
									<a href="<c:url value='/_04_ShoppingCart/ShowCartContent' />"
										data-toggle="tooltip" data-placement="bottom" title="購物車 ">
										<span style="font-size: 14px"><i
											class="fas fa-shopping-cart navbar-cart fa-lg iconShadow"></i></span>
									</a> <a href="#" data-toggle="tooltip" data-placement="bottom"
										title="Linkedin "><i class="fa fa-linkedin iconShadow"
										aria-hidden="true"></i> </a> <a href="#" data-toggle="tooltip"
										data-placement="bottom" title="Instagram"><i
										class="fa fa-instagram iconShadow" aria-hidden="true"></i> </a> <a
										href="#" data-toggle="tooltip" data-placement="bottom"
										title="Facebook"><i class="fa fa-facebook iconShadow"
										aria-hidden="true"></i> </a> <a href="#" data-toggle="tooltip"
										data-placement="bottom" title="Twitter"><i
										class="fa fa-twitter iconShadow" aria-hidden="true"></i></a>
								</div>
								<!-- Menu Icon -->
								<span class="navbar-toggler-icon iconShadow ml-4" id="menuIcon"></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- ***** Header Area End ***** -->

	<!-- 首頁內容 -->
	<!-- ***** Hero Area Start ***** -->
	<section class="hero-area">
		<div class="hero-slides owl-carousel">
			<!-- Single Hero Slide -->
			<div class="single-hero-slide bg-img slide-background-overlay"
				style="background-image: url(<c:url value='/resources/images/_00_Home/Home_001.jpg' />)">
				<a href="<c:url value='/_03_listProducts/DisplayPageProducts2' />">
					<div class="container h-100">
						<div class="row h-100 align-items-end">
							<div class="col-12">
								<div class="hero-slides-content">
									<div class="line"></div>
									<h2>寵物商城</h2>
									<p>
										一個關心寵物生活為出發點的的寵物用品購物資訊商城。有許多品牌的狗飼料，狗用品，貓飼料，貓砂、貓用品等商品，滿足毛孩更多的渴望。
									</p>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>
			<!-- Single Hero Slide -->
			<div class="single-hero-slide bg-img slide-background-overlay"
				style="background-image: url(<c:url value='/resources/images/_00_Home/Home_002.jpg' />)">
				<a href="<c:url value='http://127.0.0.1:8081/' />">
					<div class="container h-100">
						<div class="row h-100 align-items-end">
							<div class="col-12">
								<div class="hero-slides-content">
									<div class="line"></div>
									<h2>寵物交流版</h2>
									<p>寵物板無論是貓狗、毛小孩或任何養其他寵物的經驗都可以在此討論，另外像是寵物協尋或動物醫院的分享也歡迎發文！</p>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>
			<!-- Single Hero Slide -->
			<div class="single-hero-slide bg-img slide-background-overlay"
				style="background-image: url(<c:url value='/resources/images/_00_Home/Home_003.jpg' />)">
				<a href="<c:url value='/_03_FriendlySystem/Reservation/景點' />">
					<div class="container h-100">
						<div class="row h-100 align-items-end">
							<div class="col-12">
								<div class="hero-slides-content">
									<div class="line"></div>
									<h2>寵物旅遊</h2>
									<p>
										提供全台寵物友善景點、寵物遊樂場，嚴選毛孩完善的寵物友善空間，完整收錄寵物友善程度、環境設施、適合寵物犬型、寵物游泳池、
										...</p>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>
			<!-- Single Hero Slide -->
			<div class="single-hero-slide bg-img slide-background-overlay"
				style="background-image: url(<c:url value='/resources/images/_00_Home/Home_004.jpg' />)">
				<a href="<c:url value='/_03_FriendlySystem/Reservation/餐廳' />">
					<div class="container h-100">
						<div class="row h-100 align-items-end">
							<div class="col-12">
								<div class="hero-slides-content">
									<div class="line"></div>
									<h2>餐廳</h2>
									<p>
										家有毛孩嗎？假日想出去走走的時候，毛孩要待在家還是一起出門？但最麻煩的是，寵物友善餐廳總是得先爬文先找找，在這裡你不但能找到合適的餐廳，還能夠使用線上預約的服務。
									</p>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>
			<!-- Single Hero Slide -->
			<div class="single-hero-slide bg-img slide-background-overlay"
				style="background-image: url(<c:url value='/resources/images/_00_Home/Home_005.jpg' />)">
				<a href="<c:url value='/_03_FriendlySystem/Reservation/寵物美容' />">
					<div class="container h-100">
						<div class="row h-100 align-items-end">
							<div class="col-12">
								<div class="hero-slides-content">
									<div class="line"></div>
									<h2>寵物美容</h2>
									<p>
										需要寵物美容服務嗎？在這裡我們提供各式美容店訊息與預約服務，為您的毛小孩提供專屬的理容服務，包括深層清潔、造型設計，及毛髮保養等。
									</p>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>
			<!-- Single Hero Slide -->
			<div class="single-hero-slide bg-img slide-background-overlay"
				style="background-image: url(<c:url value='/resources/images/_00_Home/Home_006.jpg' />)">
				<a href="<c:url value='/_03_FriendlySystem/Reservation/寵物旅館' />">
					<div class="container h-100">
						<div class="row h-100 align-items-end">
							<div class="col-12">
								<div class="hero-slides-content">
									<div class="line"></div>
									<h2>寵物旅館</h2>
									<p>
										提供您可攜帶寵物入住的寵物飯店、寵物旅館及寵物民宿，更幫您整理出可寵物同房的飯店訂房推薦，以及寵物不同房另有場地安置的推薦
										...</p>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>
			<!-- Single Hero Slide -->
			<div class="single-hero-slide bg-img slide-background-overlay"
				style="background-image: url(<c:url value='/resources/images/_00_Home/Home_007.jpg' />)">
				<a href="<c:url value='/_05_HealthControl/CalorieCalculation' />">
					<div class="container h-100">
						<div class="row h-100 align-items-end">
							<div class="col-12">
								<div class="hero-slides-content">
									<div class="line"></div>
									<h2>寵物熱量管理</h2>
									<p>
										管理動物的生活狀態同時也是檢視自身習慣，若能在幫助毛小孩減重的過程中重新建立親密互動，與牠們的共同生活也會變得更加美好！</p>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>
			<!-- Single Hero Slide -->
			<div class="single-hero-slide bg-img slide-background-overlay"
				style="background-image: url(<c:url value='/resources/images/_00_Home/Home_008.jpg' />)">
				<a href="<c:url value='/BlogIndex' />">
					<div class="container h-100">
						<div class="row h-100 align-items-end">
							<div class="col-12">
								<div class="hero-slides-content">
									<div class="line"></div>
									<h2>寵物健康專欄</h2>
									<p>提供寵物有關的常見日常健康知識、生活智慧等資訊，您我從每天了解更多寵物相關健康資訊開始，一起打造美好健康生活！</p>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>

		</div>
	</section>
	<!-- ***** Hero Area End ***** -->



	<!-- 登入畫面_Modal -->
	<div class="modal fade" id="login" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">

				<div class="modal-header">
					<h4 class="modal-title" id="exampleModalCenterTitle"
						style="color: #0090d3">
						<b>登入系統</b>
					</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">

					<form:form method="POST" modelAttribute="loginBean"
						action="${pageContext.request.contextPath}/login"
						enctype='multipart/form-data'>
						<div class="form-group">
							<label for="exampleInputAccount">帳號 / 電子信箱</label>
							<form:input class="form-control" path="userId"
								id="exampleInputAccount" aria-describedby="emailHelp"
								placeholder="Account" />
							<font class="errhide" color="red"><form:errors
									path="userId" cssClass="errors" /></font>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword">密碼</label>
							<form:input type="password" class="form-control" path="password"
								id="exampleInputPassword" placeholder="Password" />
							<font class="errhide" color="red"><form:errors
									path="password" cssClass="errors" /></font>
						</div>
						<div class="form-group form-check">
							<form:checkbox class="form-check-input" path="rememberMe"
								id="exampleCheck" />
							<label class="form-check-label" for="exampleCheck">記住我</label>
						</div>
						<div class="text-center">
							<small class="form-text text-muted"><font class="errhide"
								color="red">${LoginError} ${errorNotLogin}</font></small>
						</div>
						<div class="modal-footer justify-content-center">
							<button type="submit" class="btn btn-outline-primary">登入</button>
						</div>
						<div class="col-md-12">
							<p class="font-small white-text d-flex justify-content-center">
								尚未創建帳號 <a href="<c:url value='#' />"
									class="green-text ml-1 font-weight-bold" data-toggle="modal"
									data-target="#regis" onclick="changeModal()">立即註冊</a>
							</p>
						</div>
					</form:form>
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
					<h4 class="modal-title" id="exampleModalCenterTitle"
						style="color: #0090d3">
						<b>註冊系統</b>
					</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<div class="backgroundTest ">
						<div class="container">
							<div class="row">
								<!-- 會員註冊 Start -->
								<div class="col-6">
									<div class="memberRegistered memberCard mt-3 mb-3"
										style="background: url(${pageContext.request.contextPath}/resources/images/CardTest/memberCard_001.jpg); background-position: center center; background-size: cover; border-radius: 15px;">
										<div class="memberCardWrapper">
											<div class="memberCardHeader">
												<div class="memberCardDiscount">
													<span class="memberCardDiscountTitle"
														style="font-size: 18px">加入享受優惠</span>
												</div>
												<ul class="memberCardStar">
													<li><i class="fas fa-star"></i></li>
													<li><i class="fas fa-star"></i></li>
													<li><i class="fas fa-star"></i></li>
													<li><i class="fas fa-star"></i></li>
													<li><i class="fas fa-star"></i></li>
												</ul>
											</div>
											<div class="memberCardData">
												<div class="memberCardContent">
													<h1
														class="memberCardTitle d-flex justify-content-center mb-4 mt-0">
														<a href="<c:url value='/MemberRegistration' />"><span
															style="font-size: 30px">會員註冊</span></a>
													</h1>
													<p class="memberCardText" style="color: white">
														<br>加入FurKids，系統才可以做到以下的事：1. 查詢訂單、2. 計算優惠、3. 送購物金、4.
														滿額免運、5. 生日折扣及專屬優惠 6. 寵物服務預約 。
													</p>
													<a href="<c:url value='/MemberRegistration' />"
														class="memberCardButton pt-1"><span
														style="font-size: 18px">立刻加入</span></a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- 會員註冊 End -->
								<!-- 商家註冊 Start -->
								<div class="col-6">
									<div class="memberRegistered memberCard mt-3 mb-3"
										style="background: url(${pageContext.request.contextPath}/resources/images/CardTest/memberCard_002.jpg); background-position: center center; background-size: cover; border-radius: 15px;">
										<div class="memberCardWrapper">
											<div class="memberCardHeader">
												<div class="memberCardDiscount">
													<span class="memberCardDiscountTitle"
														style="font-size: 18px">加入享受優惠</span>
												</div>
												<ul class="memberCardStar">
													<li><i class="fas fa-star"></i></li>
													<li><i class="fas fa-star"></i></li>
													<li><i class="fas fa-star"></i></li>
													<li><i class="fas fa-star"></i></li>
													<li><i class="fas fa-star"></i></li>
												</ul>
											</div>
											<div class="memberCardData">
												<div class="memberCardContent">
													<h1
														class="memberCardTitle d-flex justify-content-center mb-4 mt-0">
														<a href="<c:url value='/MerchantRegistration' />"><span
															style="font-size: 30px">商家註冊</span></a>
													</h1>
													<p class="memberCardText" style="color: white">
														<br>FurKids
														讓店家使用最低的人力一鍵開店快速打造網路店面，同步管理金物流及顧客訂單，立即了解。自動預約問答降低人力成本。
													</p>
													<a href="<c:url value='/MerchantRegistration' />"
														class="memberCardButton"><span style="font-size: 18px">立刻加入</span></a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- 商家註冊 End -->
							</div>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<div class="col-md-12">
						<p class="font-small white-text d-flex justify-content-center">
							已完成註冊 <a href="<c:url value='#' />"
								class="green-text ml-1 font-weight-bold" data-toggle="modal"
								data-target="#login" onclick="changeModal()">立即登入</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 訊息畫面_Modal -->
	<div class="modal fade" id="messages" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="exampleModalLabel"
						style="color: #0090d3">
						<b>訊息提示</b>
					</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="text-center">
						<font color="red">${InsertOK} ${FlashMSG_farewell}
							${sessionScope.timeOut} ${UpdateOK}</font>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">關閉</button>
					<a href="<c:url value='/' />"><button type="button"
							class="btn btn-primary">回首頁</button></a>
				</div>
			</div>
		</div>
	</div>




	<!-- jQuery first, then Popper.js, then Bootstrap JS ----------------------------------->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>

	<script
		src="<c:url value='/resources/javascript/jquery-2.2.4.min.js' />"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS End-------------------------------->

	<!-- icon -->
	<script src="https://kit.fontawesome.com/8e822d04fb.js"
		crossorigin="anonymous"></script>

	<!-- Slider effect -->
	<script src="<c:url value='/resources/javascript/plugins.js' />"></script>

	<!-- JavaScript Plug-in End------------------------------------------------------------->
	<script src="<c:url value='/resources/javascript/home.js' />"></script>

	<!-- navigation bar js ------------------------------------->
	<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->
</body>
</html>