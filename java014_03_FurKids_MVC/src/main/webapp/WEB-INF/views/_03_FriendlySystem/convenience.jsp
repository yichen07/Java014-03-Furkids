<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">

<link
	href="${pageContext.request.contextPath}/resources/css/convenience.css"
	rel="stylesheet" type="text/css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
								<li class="list-group-item">${Convenience.merchantChildBean.busChildAddress}</li>
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
									新增<br> <i class="fas fa-caret-down"></i>
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
								href="<c:url value='Convenience_H.do?pageNo=1' />" tabindex="-1"
								aria-disabled="true">Previous</a>
						</c:if></li>
					<c:forEach var="n" begin="1" end="${TotalPages}">
						<li class="page-item"><a class="page-link"
							href="<c:url value='Convenience_H.do?pageNo=${n}' />">${n}</a></li>
					</c:forEach>
					<li class="page-item"><c:if test="${nowPage != TotalPages}">
							<a class="page-link"
								href="<c:url value='Convenience_H.do?pageNo=${nowPage + 1}' />">Next</a>
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
			aria-hidden="true">
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
							<li class="list-group-item ddd">${Convenience.merchantChildBean.merchantbean.busEmail}</li>
							<li class="list-group-item ddd">${Convenience.merchantChildBean.busChildTel}</li>
							<li class="list-group-item ddd"><p class="card-text">商店介紹：${Convenience.merchantChildBean.busChildDescription}</p></li>
						</ul>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger delete"
							data-toggle="modal"
							data-target="#delete${Convenience.busChildNo}">刪除</button>
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
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">...</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<a
							href="<c:url value='/_03_FriendlySystem/deleteConvenience/${Convenience.busChildNo}' />"><button
								type="button" class="btn btn-danger">確定刪除</button></a>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

	<!--  	修改  -->
	<!--  	Modal  -->
	<div class="modal fade" id="alertCb" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<img
						src="<c:url value='/_03_FriendlySystem/getPicture/${emptyCb.busChildNo}' />"
						style="width: 100%; height: 250px;" class="card-img-top">
					<h5 class="card-header text-center">${emptyCb.merchantChildBean.busChildName}</h5>

					<form:form method="POST" modelAttribute="emptyCb"
						action="<c:url value='/_03_FriendlySystem/convenience/alter' />"
						class="ccc">
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">服務種類:</label>
							<form:input type="text" class="form-control" path="conItem" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">服務項目:</label>
							<form:input type="text" class="form-control" path="conItemList" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">地址:</label>
							<form:input type="text" class="form-control"
								path="merchantChildBean.busChildAddress" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">公休日:</label>
							<form:input type="text" class="form-control" path="conCloseDay" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">開始營業時間:</label>
							<form:input type="text" class="form-control" path="conOpenTime" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">結束營業時間:</label>
							<form:input type="text" class="form-control" path="conCloseTime" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">分店信箱:</label>
							<form:input type="text" class="form-control"
								path="merchantChildBean.merchantbean.busEmail" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">分店電話:</label>
							<form:input type="text" class="form-control"
								path="merchantChildBean.busChildTel" />
						</div>
						<div class="form-group">
							<label for="message-text" class="col-form-label">備註:</label>
							<form:textarea class="form-control"
								path="merchantChildBean.busChildDescription" />
						</div>
						<div>
							<button type="submit" class="btn btn-primary">確定修改</button>
						</div>
					</form:form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary revise">修改</button>
				</div>
			</div>
		</div>
	</div>




	<!-- 新增的Modal-->
	<!-- 	Modal -->
	<div class="modal fade" id="insertCb" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<img
						src="<c:url value='/_03_FriendlySystem/getPicture/${emptyMcb.busChildNo}' />"
						style="width: 100%; height: 250px;" class="card-img-top">
					<h5 class="card-header text-center">${emptyMcb.busChildName}</h5>

					<form:form method="POST" modelAttribute="emptyMcb" 
						>
						<div class="modal-body">
							<form:input type="text" class="form-control" path="busChildNo"
								style="display: none;" />
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">服務種類:</label>
								<form:input type="text" class="form-control"
									path="convenienceBean_H.conItem" />
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">服務項目:</label>
								<form:input type="text" class="form-control"
									path="convenienceBean_H.conItemList" />
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">公休日:</label>
								<form:input type="text" class="form-control"
									path="convenienceBean_H.conCloseDay" />
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">開始營業時間:</label>
								<form:input type="text" class="form-control"
									path="convenienceBean_H.conOpenTime" />
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">結束營業時間:</label>
								<form:input type="text" class="form-control"
									path="convenienceBean_H.conCloseTime" />
							</div>
							<div class="form-group">
								<label for="message-text" class="col-form-label">備註:</label>
								<form:textarea class="form-control" path="busChildDescription" />
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary">新增</button>
							</div>
						</div>
					</form:form>
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
	<script src="https://kit.fontawesome.com/8e822d04fb.js"
		crossorigin="anonymous"></script>

	<c:if test="${!empty emptyCb.busChildNo}">
		<button id="aCb" class="dropdown-item btn insertcon" type="button"
			data-toggle="modal" data-target="#alertCb" style="display: none"></button>
		<script type="text/javascript">
			$('#aCb').trigger('click');
		</script>
	</c:if>

	<c:if test="${!empty emptyMcb.busChildNo}">
		<button id="iCb" class="dropdown-item btn insertcon" type="button"
			data-toggle="modal" data-target="#insertCb" style="display: none"></button>
		<script type="text/javascript">
			$('#iCb').trigger('click');
		</script>
	</c:if>

	<script type="text/javascript">
		//如果已上架服務<8並且還有未上架分店 就顯示新增框	
		if ($('.insertcon').length < 8 && $('.notInsertCon').length != 0) {
			$('#insert').css('display', 'block');
		}
	</script>

</body>
</html>