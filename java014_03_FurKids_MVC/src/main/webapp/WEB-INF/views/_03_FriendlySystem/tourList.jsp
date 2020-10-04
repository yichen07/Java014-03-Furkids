<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
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

<!-- Inport CSS End--------------------------------------------------------------------->

<link rel="stylesheet"
	href="<c:url value='/resources/css/_03_FriendlySystem/touristIndex.css' />" />

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
		style="background-image: url(<c:url value='/resources/images/_03_FriendlySystem/touristIndex/touristIndex_banner_01.jpg' />)">
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

	<!-- Search Bar------------------------------------------------------------------------->

	<div class="container search-Bar sticky-top">
		<div class="row">
			<div class="col-md-12">
				<ul class="nav justify-content-start display-flex-inline">
					<li class="nav-item">
						<!--search box start-->
						<div class="search-container ml-1">
							<div class="search-icon-btn">
								<i class="fa fa-search"></i>
							</div>
							<div class="search-input">
								<input type="search" class="search-bar" placeholder="請輸入文字" incremental="incremental" onsearch="OnSearch (this)"/>
							</div>
						</div> <!--search box end-->
					</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- Search Bar End--------------------------------------------------------------------->

	<div class="container my-3">
		<div class="row">
		<c:forEach var="Convenience" items="${AllViewConvenience}">
			<!-- cardView No.1  -->
			<div class="col-lg-3 col-md-6 col-sm-12 my-4">
				<figure class="attractions">
					<div class="image">
						<img
							src="<c:url value='/_03_FriendlySystem/getPicture/${Convenience.busChildNo}' />"
							alt="pr-sample23" />
					</div>
					<figcaption>
						<div class="heart">
							<i class="far fa-heart"></i>
						</div>
						<div class="smallPhoto" style="background-image: url(<c:url value='/_03_FriendlySystem/getSmallPicture/${Convenience.busAccount}' />)"></div>
						<h3 class="mb-0">${Convenience.merchantChildBean.busChildName}</h3>
						<br />
						<h6>${Convenience.conItemList}</h6>
						<div class="pb-1">
							<i class="fas fa-map-marker-alt ml-3"><span
								class="iconFontSize">${Convenience.merchantChildBean.busChildAddress}</span> </i> <i
								class="fas fa-phone-square-alt"><span 
								class="iconFontSize">${Convenience.merchantChildBean.busChildTel}</span> </i>
						</div>
						<div class="border-top py-1 camaraBackground"></div>
					</figcaption>
					<a href="<c:url value='/_03_FriendlySystem/ViewReservation/${Convenience.busChildNo}' />"></a>
				</figure>
			</div>
			<!-- cardView No.1 End -->
		</c:forEach>
		</div>
		<!-- pagination =============================== -->
		<div class="pagination justify-content-center">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item"><c:if test="${nowPage != 1}">
							<a class="page-link"
								href="<c:url value='/_03_FriendlySystem/Reservation/${item}/1' />" tabindex="-1"
								aria-disabled="true">Previous</a>
						</c:if></li>
					<c:forEach var="n" begin="1" end="${TotalPages}">
						<li class="page-item"><a class="page-link"
							href="<c:url value='/_03_FriendlySystem/Reservation/${item}/${n}' />">${n}</a></li>
					</c:forEach>
					<li class="page-item"><c:if test="${nowPage != TotalPages}">
							<a class="page-link"
								href="<c:url value='/_03_FriendlySystem/Reservation/${item}/${nowPage + 1}' />">Next</a>
						</c:if></li>
				</ul>
			</nav>
		</div>
	</div>






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
	<script src="<c:url value='/resources/javascript/touristIndex.js' />"></script>
	
	<script>
	 function OnSearch (input) {
		let a = input.value;
		alert(a);
//          alert ("The current value of the search field: " + input.value);
     }

	</script>
</body>
</html>