<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
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

<!-- <link rel="stylesheet" -->
<!-- 	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"> -->
<title>Product</title>
</head>
<body>
	<!-- 下列敘述設定變數funcName的值為SHO，topMVC.jsp 會用到此變數 -->
	<c:set var="funcName" value="SHO" scope="session" />
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/navigation.jsp" />
	<div class="container p-lg-5 p3 mt-0">
		<section>
			<div>
				<div class="container" style="text-align: center">
					<h1>產品資料</h1>
				</div>
			</div>
		</section>
		<section class="container">
			<div class="row">
				<div class="col-md-6">
					<h3>${product.p_Name}</h3>
					<p>分類: ${product.p_Category}</p>
					<p>單價: ${product.p_Price}</p>
					<p>敘述: ${product.p_Note}</p>
					<p>
						<strong>商品編號: </strong> <span class='label label-warning'>
							${product.p_Id} </span>
					</p>
					<p>
						<a href="<spring:url value='/_03_listProducts/DisplayPageProducts2' />"
							class="btn btn-default"> <span
							class="glyphicon-hand-left glyphicon"></span>返回
						</a> <a href='#' class='btn btn-warning btn-large'> <span
							class='glyphicon-shopping-cart glyphicon'></span>加入購物車
						</a>
					</p>
				</div>
				<div class="col-md-6">
					<img
						src="${pageContext.servletContext.contextPath}/_00_init/getProductImage?id=${product.p_Id}"
						class="card-img-top" alt="..." />
				</div>
			</div>
		</section>
	</div>
	 <!-- ---------------------------分隔線--------------------------------------- -->
	<jsp:include page="/fragment/footer_detail.jsp" />
	<!-- ---------------------------分隔線--------------------------------------- -->
	
	
	
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
	
	<!-- ---------------------------分隔線--------------------------------------- -->
	<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- ---------------------------分隔線--------------------------------------- -->
</body>
</html>
