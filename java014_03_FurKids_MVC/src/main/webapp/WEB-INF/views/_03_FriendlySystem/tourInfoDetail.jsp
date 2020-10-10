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
	href="<c:url value='/resources/css/_03_FriendlySystem/touristInfoDetail_01.css' />" />

<!-- Input CSS End---------------------------------------------------------------------->

<title>FurKids寵物旅遊</title>
</head>
<body>
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/navigation.jsp" />

	<section>
		<div class="container">
			<div class="p-5">
				<h3>預約資料</h3>
				<table class="table table-striped table-responsive">
					
					<c:forEach var="ResInfo" varStatus="status" items="${ResInfo}">
					<thead class="text-center">
						<tr>
							<th width="30">#</th>
							<th width="100">商家名稱</th>
							<th width="100">商家E-mail</th>
							<th>商家電話</th>
							<th width="150">商家地址</th>
							<th width="70">姓名</th>
							<th>預約日期</th>
							<th width="100">備註</th>
							<c:if test="${!empty ResInfo.resQuantity}">
								<th width="100">預約人數</th>
							</c:if>

						</tr>
					</thead>
						<tbody class="text-center">
							<tr>
								<th scope="row">${status.index +1}</th>
								<td>${ResInfo.busChildName}</td>
								<td>${ResInfo.busChildEmail}</td>
								<td>${ResInfo.busChildTel}</td>
								<td>${ResInfo.busChildAddress}</td>
								<td>${ResInfo.cusName}</td>
								<td>${ResInfo.resDate} ${ResInfo.resTime}</td>
								<td>${ResInfo.resNote}</td>
								<c:if test="${!empty ResInfo.resQuantity}">
									<td>${ResInfo.resQuantity}</td>
								</c:if>
							</tr>
						<thead class="text-center">
							<tr>
								<th width="50">#</th>
								<th width="100">種類</th>
								<th width="">品種</th>
								<th width="100">名字</th>
								<th><a
									href="<c:url value='/_03_FriendlySystem/reservationDelete/${ResInfo.resID}' />"><button
											type="button" class="btn btn-secondary">刪除預約</button></a></th>
							</tr>
						</thead>
						<c:forEach var="ResChildInfo" varStatus="status"
							items="${ResInfo.reservationChildBean}">
							<thead class="text-center">
								<tr>
									<td scope="row"></td>
									<td>${ResChildInfo.resSpecies}</td>
									<td>${ResChildInfo.resVariety}</td>
									<td>${ResChildInfo.resName}</td>
								</tr>
							</thead>
						</c:forEach>
						</tbody>
					</c:forEach>
				</table>
				<tbody></tbody>
				<tfoot></tfoot>

			</div>
		</div>
	</section>


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


<script>
	
</script>

	<!-- navigation bar js ------------------------------------->
	<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->
</body>
</html>