<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<!-- Inport CSS End--------------------------------------------------------------------->

<link rel="stylesheet"
	href="<c:url value='/resources/css/_02_ShoppingSystem/shoppingIndex_01.css' />" />

<!-- Input CSS End---------------------------------------------------------------------->

<title>FurKids確認訂單</title>
</head>
<script type="text/javascript">
	var currPageNo = '1';

	function categoryClick(category) {
		$
				.ajax({
					method : 'GET',
					url : '<c:url value="/_03_listProducts/DisplayPageProducts2?pageNo='
							+ currPageNo + '&category=' + category + '"/>',
					async : false,
					success : function(result, textStatus, jqXHR) {
						document.location.reload();
					},
					error : function(e) {
						console.log(e);
					}
				});
	}

	function changePage(pageNo) {
		$
				.ajax({
					method : 'GET',
					url : '<c:url value="/_03_listProducts/DisplayPageProducts2?pageNo='
							+ pageNo + '"/>',
					async : false,
					success : function(result, textStatus, jqXHR) {
						document.location.reload();
					},
					error : function(e) {
						console.log(e);
					}
				});
	}
</script>
<body>

	<!-- 下列敘述設定變數funcName的值為SHO，topMVC.jsp 會用到此變數 -->
	<c:set var="funcName" value="SHO" scope="session" />
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/navigation.jsp" />

	<!-- 判斷購物車內是否有商品 -->
	<c:choose>
		<c:when test="${ShoppingCart.itemNumber > 0}">
			<!-- 購物車內有一項以上的商品 -->
			<c:set var="cartContent" value="購物車內有${ShoppingCart.itemNumber}項商品" />
		</c:when>
		<c:otherwise>
			<!-- 購物車內沒有商品 -->
			<c:set var="cartContent" value="您尚未購買任何商品" />
		</c:otherwise>
	</c:choose>
	<!-- Banner ---------------------------------------------------------------------------->

	<div
		class="container-fliid bannerImg d-flex justify-content-center align-items-center"
		id="imageStellar" data-stellar-background-ratio="0.5"
		style="background-image: url(<c:url value='/resources/images/_02_ShoppingSystem/shoppingIndex_Banner_01.jpg' />)">
		<div class="row">
			<div class="col-md-12 text-center">
				<h1 class="mt-3 bannerTitle animate__animated animate__fadeInDown">
					寵物商城</h1>
				<div
					class="align-items-center banner-text animate__animated animate__fadeInUp">
					<h5 class="text-center">超多特惠商品，週週下殺便宜精選，讓你愛寵物愛得輕鬆又溫馨</h5>
				</div>
			</div>
		</div>
	</div>

	<!-- Banner End------------------------------------------------------------------------->

	<!-- Main Start------------------------------------------------------------------------->
	<div class="wrapper">
		<!-- Sidebar Holder -->
		<nav id="sidebar">
			<ul class="list-unstyled components">
<<<<<<< HEAD
				<li class="" ><a class="sideBarSubTItleBg2">
						<span class="sidebarText ml-1" ><b>金額小計(OK):<c:out
								value="${ShoppingCart.subtotal}" default="0" /> 元
					</b></span>
				</a></li>
=======
			<li class=""><a class="sideBarSubTItleBg"
						
					>
								<span class="sidebarText ml-1">金額小計(OK):<c:out
							value="${ShoppingCart.subtotal}" default="0" /> 元</span>
						</a></li>
>>>>>>> bd287b3641d2e14c90fa906c1a9ed0e7c47741ef
				<c:if test="${categoryList != null}">
					<c:forEach var='category' items='${categoryList}'>
						<!-- 商品欄位1 -->
						<li class=""><a class="sideBarSubTItleBg"
							href="<c:url value='/_03_listProducts/DisplayPageProducts2?pageNo=1&category=${category}'/>">
								<span class="sidebarText ml-1">${category}</span>
						</a></li>
					</c:forEach>
				</c:if>

			</ul>
		</nav>

		<div id="content">
			<button type="button" id="sidebarCollapse"
				class="btn navbar-btn ml-2 my-2 mb-5">
				<span><i class="fas fa-bars"></i></span>
			</button>
			<div></div>
			<div class="e-commerce-product">
				<div class="container-fluid">
					<div class="row d-flex justify-content-start ml-5 mr-4 mb-5">
						<!-- 第1個產品 -->
						<c:forEach varStatus="stVar" var="product" items="${products}">
							<div class="   col-lg-3 col-md-4 col-sm-6 col-12 mb-5 ">
								<div class="product">
									<div class="content">
										<img
											src="${pageContext.servletContext.contextPath}/_00_init/getProductImage?id=${product.value.p_Id}"
											alt="image" class="productImg" />
										<div class="product-btn">
											<a data-toggle="modal"
												data-target="#exampleModal${product.value.p_Id}"><span style="color: white;">詳細內容</span> </a>
										</div>
									</div>
									<div class="product-details">
										<h4 class="title px-3">${product.value.p_Name}</h4>
										<div class="price mb-0">NT ${product.value.p_Price}</div>
									</div>
								</div>
							</div>
							<!-- 預先建立modal -->
							<!-- Modal -->
							<div class="modal fade" id="exampleModal${product.value.p_Id}"
								tabindex="-1"
								aria-labelledby="exampleModalLabel${product.value.p_Id}"
								aria-hidden="true" style="z-index: 99991">
								<div class="modal-dialog modal-xl modal-dialog-centered">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title"
												id="exampleModalLabel${product.value.p_Id}">商品資料</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-6">
													<h3>${product.value.p_Name}</h3>
													<p>分類: ${product.value.p_Category}</p>
													<p>單價: ${product.value.p_Price}</p>
													<p>敘述: ${product.value.p_Note}</p>
													<p>
														<strong>商品編號: </strong> <span class='label label-warning'>
															${product.value.p_Id} </span>
													</p>
													<p>
													<FORM action="<c:url value='BuyProduct.do' />"
														method="POST">
														購買數量: <select name='qty'>
															<option value="1">1</option>
															<option value="2">2</option>
															<option value="3">3</option>
															<option value="4">4</option>
															<option value="5">5</option>
															<option value="6">6</option>
															<option value="7">7</option>
															<option value="8">8</option>
															<option value="9">9</option>
															<option value="10">10</option>
														</select>
														<!-- 這些隱藏欄位都會送到後端 -->
														<Input type='hidden' name='p_Id'
															value='${product.value.p_Id}'> <Input
															type='hidden' name='pageNo' value='${param.pageNo}'>
														<Input type='hidden' name='p_Category'
															value='${product.value.p_Category}'> <Input
															type='submit' class='btn btn-warning btn-large'
															value='加入購物車'>
													</FORM>
													</p>
													<!-- 					<p> -->
													<%-- 						<a href="<spring:url value='/_03_listProducts/DisplayPageProducts2' />" --%>
													<!-- 							class="btn btn-default"> <span -->
													<!-- 							class="glyphicon-hand-left glyphicon"></span>返回 -->
													<!-- 						</a> <a href='#' class='btn btn-warning btn-large'> <span -->
													<!-- 							class='glyphicon-shopping-cart glyphicon'></span>加入購物車 -->
													<!-- 						</a> -->
													<!-- 					</p> -->
												</div>
												<div class="col-md-6">
													<img
														src="${pageContext.servletContext.contextPath}/_00_init/getProductImage?id=${product.value.p_Id}"
														class="card-img-top" alt="..." />
												</div>
											</div>
										</div>
										<!--       <div class="modal-footer"> -->
										<!--       <div class="row  ml-auto mr-auto"> -->
										<!--       <div class="col-4"> -->
										<%--       <img src="<c:url value='/_00_init/p_Pic1?id=${product.value.p_Id}'/>" style="max-width: 300px" /> --%>
										<!--       </div> -->
										<!--       <div class="col-4"> -->
										<%--       <img src="<c:url value='/_00_init/productImage2?id=${product.value.p_Id}'/>" style="max-width: 300px" /> --%>
										<!--       </div> -->
										<!--       <div class="col-4"> -->
										<%--       <img src="<c:url value='/_00_init/productImage3?id=${product.value.p_Id}'/>" style="max-width: 300px" /> --%>
										<!--       </div> -->
										<!--       </div> -->
										<!--         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
										<!--         <button type="button" class="btn btn-primary">Save changes</button> -->
										<!--       </div> -->
									</div>
								</div>
							</div>

							<!-- 預先建立modal結束 -->


						</c:forEach>

					</div>

					<!-- Page Number Start -->
					<div class="row d-flex justify-content-center mb-5">

						<nav class="pagination-outer" aria-label="Page navigation">
							<ul class="pagination">
							
								
							<button class="" tabindex="-1" aria-disabled="true" style="border:0px; background-color: #fdfdfd00" disabled><b>第${pageNo}頁</b></button>&nbsp;&nbsp;&nbsp;
								<c:forEach var="x" begin="1" end="${totalPages}">
									<c:if
										test="${x >= pageNo - 2 && x <= 5 || x <= pageNo + 2 && x > totalPages - 5}">
										<li class="page-item"><a class="page-link"
											href="<c:url value='/_03_listProducts/DisplayPageProducts2?pageNo=${x}'/>">${x}</a></li>
									</c:if>
								</c:forEach>
								
								<button class="" tabindex="-1" aria-disabled="true" style="border:0px; background-color: #fdfdfd00" disabled> <b>共${totalPages}頁</b></button>

							</ul>
						</nav>




					</div>
					<!-- Page Number End -->
				</div>
			</div>
		</div>
		<!-- Page Number Start -->
	</div>

	<!-- Main End--------------------------------------------------------------------------->


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
	<script
		src="<c:url value='/resources/javascript/shoppingIndex_01.js' />"></script>

	<!-- navigation bar js ------------------------------------->
	<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->
</body>
</html>
