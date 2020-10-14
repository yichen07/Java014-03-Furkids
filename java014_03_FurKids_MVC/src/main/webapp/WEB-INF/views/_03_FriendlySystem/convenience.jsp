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

<link rel="stylesheet"
	href="<c:url value='/resources/css/_03_FriendlySystem/clockpicker.css' />" />

<link rel="stylesheet"
	href="<c:url value='/resources/css/_03_FriendlySystem/standalone.css' />" />

<!-- Input CSS End---------------------------------------------------------------------->
<title>預約上架</title>
</head>
<body style="background-color: #F5F5F5;">
	<jsp:include page="/fragment/navigation.jsp" />

	<div class="job">
		<!-- 已上架的分店 -->
		<div class="container p-1g-5 p-3 wow bounceIn">
			<div class="row">
				<c:forEach var="Convenience" items="${AllConvenience}">
					<div class="col-lg-3 col-sm-6 col-2 mb-4 insertcon">
						<div class="card bg-light mb-3 border-dark con shadow-lg rounded">
							<img
								src="<c:url value='/_03_FriendlySystem/getPicture/${Convenience.busChildNo}' />"
								style="width: 100%; height: 140px;" class="card-img-top">
							<h5 class="card-header text-center">${Convenience.merchantChildBean.busChildName}</h5>
							<ul class="list-group list-group-flush text-center">
								<li class="list-group-item">${Convenience.conItem}</li>
								<li class="list-group-item">${Convenience.conItemList}</li>
								<li class="list-group-item">${Convenience.shortAddress}</li>
							</ul>
							<div class="card-body text-center">
								<a href="#" class="detail" data-toggle="modal"
									data-target="#${Convenience.merchantChildBean.busChildName}">詳細內容</a>
							</div>
						</div>
					</div>
				</c:forEach>
				<!-- 新增框 -->
				<div id="insert" class="col-lg-3 col-sm-6 col-2 mb-4"
					style="display: none">
					<div class="card  mb-3 con"
						style="border: dashed; border-color: #AFA9A9; background-color: #F5F5F5;">
						<div
							style="height: 400px; display: flex; flex-direction: column; justify-content: center; text-align: center;">
							<!-- 新增按鈕 -->
							<div class="dropdown" style="z-index: 20;">
								<button class="btn btn-secondary-outline dropdown-toggle fa-2x"
									type="button" id="dropdownMenu2" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">
									上架<br> <i class="fas fa-caret-down"></i>
								</button>
								<div class="dropdown-menu" aria-labelledby="dropdownMenu2">
									<!-- 未上架的分店 -->
									<c:forEach var="NoConvenience" items="${NotConvenience}">
										<a
											href="<c:url value='/_03_FriendlySystem/insert/${NoConvenience.busChildNo}' />"><button
												name="submit1" class="dropdown-item btn notInsertCon"
												type="button">${NoConvenience.busChildName}</button></a>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- pagination =============================== -->
		<div class="pagination justify-content-center">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item"><c:if test="${nowPage != 1}">
							<a class="page-link"
								href="<c:url value='/_03_FriendlySystem/convenience/1' />"
								tabindex="-1" aria-disabled="true">Previous</a>
						</c:if></li>
					<c:forEach var="n" begin="1" end="${TotalPages}">
						<li class="page-item"><a class="page-link"
							href="<c:url value='/_03_FriendlySystem/convenience/${n}' />">${n}</a></li>
					</c:forEach>
					<li class="page-item"><c:if test="${nowPage != TotalPages}">
							<a class="page-link"
								href="<c:url value='/_03_FriendlySystem/convenience/${nowPage + 1}' />">Next</a>
						</c:if></li>
				</ul>
			</nav>
		</div>
	</div>




	<!-- 詳細內容Modal -->
	<!-- Modal -->
	<c:forEach var="Convenience" items="${AllConvenience}">
		<div class="modal fade mmm"
			id="${Convenience.merchantChildBean.busChildName}" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLongTitle"
			aria-hidden="true" style="z-index: 99991">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-body">
						<img
							src="<c:url value='/_03_FriendlySystem/getPicture/${Convenience.busChildNo}' />"
							style="width: 100%; height: 250px;" class="card-img-top">
						<h5 class="card-header text-center">${Convenience.merchantChildBean.busChildName}</h5>

						<ul class="list-group list-group-flush text-center">
							<li class="list-group-item ddd">${Convenience.conItem}</li>
							<li class="list-group-item ddd">${Convenience.conItemList}</li>
							<li class="list-group-item ddd">${Convenience.merchantChildBean.busChildAddress}</li>
							<li class="list-group-item ddd">公休日：${Convenience.conCloseDay}</li>
							<li class="list-group-item ddd">開始營業時間：${Convenience.conOpenTime}</li>
							<li class="list-group-item ddd">結束營業時間：${Convenience.conCloseTime}</li>
							<li class="list-group-item ddd">${Convenience.merchantChildBean.busChildEmail}</li>
							<li class="list-group-item ddd">${Convenience.merchantChildBean.busChildTel}</li>
							<li class="list-group-item ddd"><p class="card-text">商店介紹：${Convenience.merchantChildBean.busChildDescription}</p></li>
						</ul>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger delete"
							data-toggle="modal"
							data-target="#delete${Convenience.busChildNo}">下架</button>
						<a
							href="<c:url value='/_03_FriendlySystem/alter/${Convenience.busChildNo}' />"><button
								type="button" class="btn btn-secondary">修改</button></a>
					</div>
				</div>
			</div>
		</div>
		<!-- 刪除 -->
		<!-- Modal -->
		<div class="modal fade" id="delete${Convenience.busChildNo}"
			tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			aria-hidden="true" style="z-index: 99992">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">確定要下架</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<h5>${Convenience.merchantChildBean.busChildName}?</h5>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<a
							href="<c:url value='/_03_FriendlySystem/deleteConvenience/${Convenience.busChildNo}' />"><button
								type="button" class="btn btn-danger">確定下架</button></a>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

	<!--  	修改  -->
	<!--  	Modal  -->
	<div class="modal fade" id="alertCb" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLongTitle" aria-hidden="true"
		data-backdrop="static" style="z-index: 99991">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<c:if test="${!empty emptyCb.busChildNo}">
						<img
							src="<c:url value='/_03_FriendlySystem/getPicture/${emptyCb.busChildNo}' />"
							style="width: 100%; height: 250px;" class="card-img-top">
					</c:if>
					<h5 class="card-header text-center">${emptyCb.merchantChildBean.busChildName}</h5>

					<form:form method="POST" modelAttribute="emptyCb" action="alter"
						class="ccc">
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">服務種類:</label>
							<form:select class="form-control" path="conItem">
								<form:option value="${emptyCb.conItem}"
									item="${emptyCb.conItem}" />
								<form:options items="${cvsAlterOption}" />
							</form:select>
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">服務項目:</label>
							<form:input type="text" class="form-control" path="conItemList" />
							<form:errors path="conItemList" class="text-danger" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">公休日:</label>
							<form:input type="text" class="form-control" path="conCloseDay" />
							<form:errors path="conCloseDay" class="text-danger" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">開始營業時間:</label>
							<form:input type="time" class="form-control" path="conOpenTime" />
							<form:errors path="conOpenTime" class="text-danger" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">結束營業時間:</label>
							<form:input type="time" class="form-control" path="conCloseTime" />
							<form:errors path="conCloseTime" class="text-danger" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">分店信箱:</label>
							<form:input type="text" class="form-control"
								path="merchantChildBean.busChildEmail" />
							<form:errors path="merchantChildBean.busChildEmail"
								class="text-danger" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">分店電話:</label>
							<form:input type="text" class="form-control"
								path="merchantChildBean.busChildTel" />
							<form:errors path="merchantChildBean.busChildTel"
								class="text-danger" />
						</div>
						<div class="form-group">
							<label for="message-text" class="col-form-label">備註:</label>
							<form:textarea class="form-control"
								path="merchantChildBean.busChildDescription" />
							<form:errors path="merchantChildBean.busChildDescription"
								class="text-danger" />
						</div>
						<div class="modal-footer">
							<div>
								<button type="submit" class="btn btn-primary">確定修改</button>
							</div>

							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>




	<!-- 新增的Modal-->
	<!-- 	Modal -->
	<div class="modal fade" id="insertCb" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLongTitle" aria-hidden="true"
		data-backdrop="static" style="z-index: 999991">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<c:if test="${!empty emptyMcb.busChildNo}">
						<img
							src="<c:url value='/_03_FriendlySystem/getPicture/${emptyMcb.busChildNo}' />"
							style="width: 100%; height: 250px;" class="card-img-top">

						<h5 class="card-header text-center">${emptyMcb.busChildName}</h5>

						<form:form method="POST" modelAttribute="emptyMcb" action="insert">
							<div class="modal-body">
								<form:input type="text" id="time" class="form-control"
									path="busChildNo" style="display: none;" />
								<div class="form-group">
									<label for="recipient-name" class="col-form-label">服務種類:</label>
									<form:select class="form-control"
										path="convenienceBean_H.conItem">
										<form:options items="${cvsOption}" />
									</form:select>
									<form:errors path="convenienceBean_H.conItem"
										class="text-danger" />
								</div>
								<div class="form-group">
									<label for="recipient-name" class="col-form-label">服務項目:</label>
									<form:input type="text" class="form-control"
										path="convenienceBean_H.conItemList" />
									<form:errors path="convenienceBean_H.conItemList"
										class="text-danger" />
								</div>
								<div class="form-group">
									<label for="recipient-name" class="col-form-label">公休日:</label>
									<form:input type="text" class="form-control"
										path="convenienceBean_H.conCloseDay" />
									<form:errors path="convenienceBean_H.conCloseDay"
										class="text-danger" />
								</div>
								<div class="form-group">
									<label for="recipient-name" class="col-form-label">開始營業時間:</label>
									<form:input type="time" class="form-control"
										path="convenienceBean_H.conOpenTime" />
									<form:errors path="convenienceBean_H.conOpenTime"
										class="text-danger" />
								</div>
								<div class="form-group">
									<label for="recipient-name" class="col-form-label">結束營業時間:</label>
									<form:input type="time" class="form-control"
										path="convenienceBean_H.conCloseTime" />
									<form:errors path="convenienceBean_H.conCloseTime"
										class="text-danger" />
								</div>
								<div class="form-group">
									<label for="message-text" class="col-form-label">商店介紹／備註:</label>
									<form:textarea class="form-control" path="busChildDescription" />
									<form:errors path="busChildDescription" class="text-danger" />
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary">上架</button>
								</div>
							</div>
						</form:form>
					</c:if>
				</div>
			</div>
		</div>
	</div>









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

	<script src="<c:url value='/resources/javascript/clockpicker.js' />"></script>
	<script type="text/javascript"
		src="<c:url value='/resources/javascript/Convenience.js' />">
		
	</script>
	<script type="text/javascript">
		$('.clockpicker').clockpicker();
	</script>
	<c:if test="${!empty aaalert}">
		<button id="alterCb" class="dropdown-item btn" type="button"
			data-toggle="modal" data-target="#alertCb" style="display: none"></button>
		<script type="text/javascript">
			$('#alterCb').trigger('click');
		</script>
	</c:if>

	<c:if test="${!empty iiinsert}">
		<button id="insCb" class="dropdown-item btn" type="button"
			data-toggle="modal" data-target="#insertCb" style="display: none"></button>
		<script type="text/javascript">
			$('#insCb').trigger('click');
			$('.clockpicker').clockpicker();
		</script>
	</c:if>
	<c:if test="${!empty inputError}">
		<button id="inputError" class="dropdown-item btn" type="button"
			data-toggle="modal" data-target="#insertCb" style="display: none"></button>
		<script type="text/javascript">
			$('#inputError').trigger('click');
		</script>
	</c:if>
	<c:if test="${!empty alterError}">
		<button id="alterError" class="dropdown-item btn" type="button"
			data-toggle="modal" data-target="#alertCb" style="display: none"></button>
		<script type="text/javascript">
			$('#alterError').trigger('click');
		</script>
	</c:if>
	<script type="text/javascript">
		//如果已上架服務<8並且還有未上架分店 就顯示新增框	
		if ($('.insertcon').length < 8 && $('.notInsertCon').length != 0) {
			$('#insert').css('display', 'block');
		}
	</script>

	<!-- navigation bar js ------------------------------------->
	<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->
</body>
</html>