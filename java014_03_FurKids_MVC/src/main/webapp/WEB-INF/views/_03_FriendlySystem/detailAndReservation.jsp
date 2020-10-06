<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8" />
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
	href="<c:url value='/resources/css/_03_FriendlySystem/touristInfo_01.css' />" />

<!-- Input CSS End---------------------------------------------------------------------->

<title>FurKids寵物旅遊</title>
</head>
<body>
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/navigation.jsp" />


	<!-- Banner ---------------------------------------------------------------------------->

	<div
		class="container-fliid bannerImg d-flex justify-content-center align-items-center"
		id="imageStellar" data-stellar-background-ratio="0.5"
		style="background-image: url(<c:url value='/resources/images/_03_FriendlySystem/touristInfo/touristInfo_banner_01.jpg' />)">
		<div class="row">
			<div class="col-md-12 text-center">
				<h1 class="mt-3 bannerTitle animate__animated animate__fadeInDown">
					寵物旅遊</h1>
				<div
					class="align-items-center banner-text animate__animated animate__fadeInUp">
					<h5 class="text-center">一起出遊、一起拓展毛孩生命的寬度吧！</h5>
				</div>
			</div>
		</div>
	</div>

	<!-- Banner End------------------------------------------------------------------------->

	<!-- Main Start------------------------------------------------------------------------->
	<section>
		<div class="container">
			<div class="row">
				<!-- Left Content Start ----------------------->
				<div class="col-lg-8 mt-3 mb-0 pl-4 pr-4" id="slider">
					<div class="row">
						<div class="col-lg-8">
							<h2 class="d-flex align-items-end titleH2">
								${Reservation.busChildName}<span class="star ml-3 mb-2"><i
									class="fas fa-star"></i><i class="fas fa-star"></i><i
									class="fas fa-star"></i><i class="fas fa-star"></i><i
									class="fas fa-star"></i></span>
							</h2>
							<div class="mt-0 mb-0 align-items-center">
								<i class="fas fa-map-marker-alt fa-fw mr-1"> </i><span
									class="titleText mr-4">${Reservation.busChildAddress}</span><br />
								<i class="fas fa-phone-square-alt fa-fw mr-1 mb-1"> </i><span
									class="titleText">${Reservation.busChildTel}</span>
							</div>
						</div>
						<div class="col-lg-4 d-flex justify-content-end align-items-end">
							<h3 class="hotelPrice">
								<small class="hotelTWD"></small>${Reservation.convenienceBean_H.conItemList}
							</h3>
						</div>
					</div>
					<div></div>
					<div class="titleText mb-2"></div>
					<!-- main slider carousel items -->

					<div id="myCarousel" class="carousel slide shadow "
						data-ride="carousel">
						<div class="carousel-inner">
							<div class="active carousel-item" data-slide-number="0">
								<img
									src="<c:url value='/_03_FriendlySystem/getPicture/${Reservation.busChildNo}' />"
									class="hotelImage" />
							</div>
							<!--                 <div class="carousel-item" data-slide-number="1"> -->
							<!--                   <img -->
							<!--                     src="../resources/images/_03_FriendlySystem/touristInfo/touristInfo_hotel_01_02.jpg" -->
							<!--                     class="hotelImage" -->
							<!--                   /> -->
							<!--                 </div> -->

							<!--                 <a -->
							<!--                   class="carousel-control-prev" -->
							<!--                   href="#myCarousel" -->
							<!--                   role="button" -->
							<!--                   data-slide="prev" -->
							<!--                 > -->
							<!--                   <span -->
							<!--                     class="carousel-control-prev-icon" -->
							<!--                     aria-hidden="true" -->
							<!--                   ></span> -->
							<!--                   <span class="sr-only">Previous</span> -->
							<!--                 </a> -->
							<!--                 <a -->
							<!--                   class="carousel-control-next" -->
							<!--                   href="#myCarousel" -->
							<!--                   role="button" -->
							<!--                   data-slide="next" -->
							<!--                 > -->
							<!--                   <span -->
							<!--                     class="carousel-control-next-icon" -->
							<!--                     aria-hidden="true" -->
							<!--                   ></span> -->
							<!--                   <span class="sr-only">Next</span> -->
							<!--                 </a> -->
						</div>
						<!-- main slider carousel nav controls -->

						<!--               <ul -->
						<!--                 class="carousel-indicators list-inline justify-content-start px-2 w-100" -->
						<!--               > -->
						<!--                 <li class="list-inline-item active"> -->
						<!--                   <a -->
						<!--                     id="carousel-selector-0" -->
						<!--                     class="selected" -->
						<!--                     data-slide-to="0" -->
						<!--                     data-target="#myCarousel" -->
						<!--                   > -->
						<!--                     <img -->
						<!--                       src="../resources/images/_03_FriendlySystem/touristInfo/touristInfo_hotel_01_01.jpg" -->
						<!--                       class="hotelImageIcon" -->
						<!--                     /> -->
						<!--                   </a> -->
						<!--                 </li> -->
						<!--                 <li class="list-inline-item"> -->
						<!--                   <a -->
						<!--                     id="carousel-selector-1" -->
						<!--                     data-slide-to="1" -->
						<!--                     data-target="#myCarousel" -->
						<!--                   > -->
						<!--                     <img -->
						<!--                       src="../resources/images/_03_FriendlySystem/touristInfo/touristInfo_hotel_01_02.jpg" -->
						<!--                       class="hotelImageIcon" -->
						<!--                     /> -->
						<!--                   </a> -->
						<!--                 </li> -->
						<!--                 <li class="list-inline-item"> -->
						<!--                   <a -->
						<!--                     id="carousel-selector-2" -->
						<!--                     data-slide-to="2" -->
						<!--                     data-target="#myCarousel" -->
						<!--                   > -->
						<!--                     <img -->
						<!--                       src="../resources/images/_03_FriendlySystem/touristInfo/touristInfo_hotel_01_03.jpg" -->
						<!--                       class="hotelImageIcon" -->
						<!--                     /> -->
						<!--                   </a> -->
						<!--                 </li> -->
						<!--                 <li class="list-inline-item"> -->
						<!--                   <a -->
						<!--                     id="carousel-selector-3" -->
						<!--                     data-slide-to="3" -->
						<!--                     data-target="#myCarousel" -->
						<!--                   > -->
						<!--                     <img -->
						<!--                       src="../resources/images/_03_FriendlySystem/touristInfo/touristInfo_hotel_01_04.jpg" -->
						<!--                       class="hotelImageIcon" -->
						<!--                     /> -->
						<!--                   </a> -->
						<!--                 </li> -->
						<!--                 <li class="list-inline-item"> -->
						<!--                   <a -->
						<!--                     id="carousel-selector-4" -->
						<!--                     data-slide-to="4" -->
						<!--                     data-target="#myCarousel" -->
						<!--                   > -->
						<!--                     <img -->
						<!--                       src="../resources/images/_03_FriendlySystem/touristInfo/touristInfo_hotel_01_05.jpg" -->
						<!--                       class="hotelImageIcon" -->
						<!--                     /> -->
						<!--                   </a> -->
						<!--                 </li> -->
						<!--               </ul> -->
					</div>

					<div class="mt-4 mb-4 border-bottom">
						<div class="pb-0 mb-0 pt-3 IntroductionTitle border-top">
							<i class="far fa-file-alt"></i> 簡介
						</div>
						<article class="articleColor">
							<p class="mt-3">${Reservation.convenienceBean_H.conItemList}</p>
							<p>公休日： ${Reservation.convenienceBean_H.conCloseDay}</p>

							<p>營業時間 ：${Reservation.convenienceBean_H.conOpenTime} -
								${Reservation.convenienceBean_H.conCloseTime}</p>

							<p>電話：${Reservation.busChildTel}</p>

							<p>地址：${Reservation.busChildAddress}</p>

							<p>信箱：${Reservation.busChildEmail}</p>
						</article>
					</div>
					<div class="pb-0 mb-3 IntroductionTitle">
						<i class="far fa-clipboard"></i> 備註
					</div>
					<div class="remarks p-4 mb-5">
						<article>
							<p class="remarkPColor">${Reservation.busChildDescription}</p>
						</article>
					</div>
				</div>
				<!-- Left Content End ------------------------->
				<!-- Right Content Start ---------------------->
				<div class="col-lg-4 col-sm-12 mt-4 wrapper mb-5">
						<div class="px-1">
					<form class="mt-0">
							<!-- 基本資料 Start-->

							<div class="d-flex align-items-center mt-0">
								<i class="fas fa-user-alt rightTitleIcon fa-fw"></i>
								<div class="rightTitle pl-1">基本資料</div>
							</div>
							<div class="input-data mt-4">
								<input class="form-control" type="text" value="${Member.cusName}"/>
								<div class="underline"></div>
								<label class="labelText">姓名</label>
							</div>
							<div class="input-data mt-4">
								<input class="form-control" type="tel" value="${Member.cusTel}"/>
								<div class="underline"></div>
								<label class="labelText">電話</label>
							</div>
							<div class="input-data mt-4 mb-2">
								<input class="form-control" type="tel" value="${Member.cusAccount}"/>
								<div class="underline"></div>
								<label class="labelText">E-mail</label>
							</div>
						</form>
							<!-- 基本資料 End -->
							
							<!-- 入住資料 Start-->

							<div class="d-flex align-items-center mt-4 pt-3">
								<i class="fas fa-hotel rightTitleIcon fa-fw"></i>
								<div class="rightTitle pl-1">入住資料</div>
							</div>
						<form:form method="POST" modelAttribute="ResBean">
							<div class="d-flex mt-3">
								<div class="input-data mt-4">
									<form:input path="resDate" class="dateRange form-control" type="text"  />
									<div class="underlineDate"></div>
									<label>入住時間</label>
								</div>
							</div>
							<!-- 入住資料 End-->
							
							<!-- 寵物訊息 Start-->
							<div
								class="d-flex align-items-center justify-content-between mt-4 pt-2">
								<div>
									<i class="fas fa-paw rightTitleIcon"></i> <span
										class="rightTitle pl-1">寵物訊息</span>
								</div>
							</div>
							<br>
							<div>
 								<c:forEach items="${ResBean.reservationChildBean}" varStatus="status" var="function">
 									
								<form:checkbox path="reservationChildBean[${status.index}].resName" 
								value="${function.resName}" /> 
								<td>${function.resName}</td>
								</c:forEach>
							</div>
							<!-- 寵物訊息 End-->
							<div class="mt-4 px-3 col-12">
								<h6 class="note">預約備註</h6>
								<form:textarea path="resNote" class="form-control noteborder" rows="4"
									placeholder="" />
							</div>
							
							<!-- 確認表單 Start-->
							<div class="commitOrder row">
								<div class="checkbox-wrapper col-12 mt-3 d-flex">
									<input type="checkbox" id="checkTerms" hidden /><label
										for="checkTerms" class="checkStyle"></label>
									<div class="checkboxText ml-2">我已詳細閱讀並同意旅客入住須知規範並願意遵守規定</div>
								</div>
							</div>
							<div class="row buybtnP mt-3 mb-0">
								<div class="col-12 mr-0 d-flex justify-content-end mr-0 ml-3">
									<button class="ripple buybtn">確認預定</button>
								</div>
							</div>
						</form:form>
							<!-- 確認表單 End-->
						</div>
					
				</div>

				<!-- Right Content End ------------------------>
			</div>
		</div>
	</section>

	<!-- Main End--------------------------------------------------------------------------->



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

	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>

	<!-- JavaScript Plug-in End------------------------------------------------------------->
	<script src="<c:url value='/resources/javascript/touristInfo_01.js' />"></script>


</body>
</html>