<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>預約上架</title>
</head>
<body>




	<div class="container p-1g-5 p-3 wow bounceIn">
		<div class="row">
			<c:forEach var="Convenience" items="${AllConvenience}">
				<div class="col-lg-3 col-sm-6 col-2 mb-4">
					<div class="card bg-light mb-3 border-dark con">
						<img
							src="${pageContext.servletContext.contextPath}/_03_/FriendlyService/getBusChildImage?busChildNo=${Convenience.busChildNo}"
							style="width: 100%; height: 140px;" class="card-img-top"
							alt="...">
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
			<div class="col-lg-3 col-sm-6 col-2 mb-4" >
					<div class="card bg-light mb-3 con" style="border:dashed;">
						<div style="height:400px;display: flex; flex-direction: column;justify-content: center;border: 1px solid #ccc;text-align: center;">		
							<span><i style="z-index:10000;font-size:30px;">新增</i></span>
							<div class="dropdown" style="z-index:20;">
		<button class="btn btn-secondary-outline dropdown-toggle fa-4x" type="button"
			id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"></button>
		<div class="dropdown-menu" aria-labelledby="dropdownMenu2">
			<c:forEach var="NoConvenience" items="${NotConvenience}">
				<button name="submit1" value="${NoConvenience.busChildName}"
					class="dropdown-item btn" type="button" data-toggle="modal"
					data-target="#${NoConvenience.busChildName}1">${NoConvenience.busChildName}</button>
			</c:forEach>
		</div>
	</div>
						</div>
					</div>
			</div>
			<!-- Modal -->
			<c:forEach var="Convenience" items="${AllConvenience}">
				<div class="modal fade"
					id="${Convenience.merchantChildBean.busChildName}" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLongTitle"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-body">
								<img
									src="${pageContext.servletContext.contextPath}/_03_/FriendlyService/getBusChildImage?busChildNo=${Convenience.busChildNo}"
									style="width: 100%; height: 200px;" class="card-img-top"
									alt="...">
								<h5 class="card-header text-center">${Convenience.merchantChildBean.busChildName}</h5>
								<ul class="list-group list-group-flush text-center">
									<li class="list-group-item">${Convenience.conItem}</li>
									<li class="list-group-item">${Convenience.conItemList}</li>
									<li class="list-group-item">${Convenience.merchantChildBean.busChildAddress}</li>
									<li class="list-group-item">公休日：${Convenience.conCloseDay}</li>
									<li class="list-group-item">開始營業時間：${Convenience.conOpenTime}</li>
									<li class="list-group-item">結束營業時間：${Convenience.conCloseTime}</li>
									<li class="list-group-item">${Convenience.merchantChildBean.busChildTel}</li>
									<li class="list-group-item">${Convenience.merchantChildBean.merchantbean.busEmail}</li>
									<li class="list-group-item"><p class="card-text">商店介紹：${Convenience.merchantChildBean.busChildDescription}</p></li>
								</ul>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-danger">刪除</button>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>






	<!-- 下拉式按鈕 -->
	<div class="dropdown">
		<button class="btn btn-secondary-outline dropdown-toggle  fa-5x" type="button"
			id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"></button>
		<div class="dropdown-menu" aria-labelledby="dropdownMenu2">
			<c:forEach var="NoConvenience" items="${NotConvenience}">
				<button name="submit1" value="${NoConvenience.busChildName}"
					class="dropdown-item btn" type="button" data-toggle="modal"
					data-target="#${NoConvenience.busChildName}1">${NoConvenience.busChildName}</button>
			</c:forEach>
		</div>
	</div>




	<!-- 	Modal -->
	<c:forEach var="NoConvenience" items="${NotConvenience}">
		<div class="modal fade" id="${NoConvenience.busChildName}1"
			tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">${NoConvenience.busChildName}</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<FORM
						action="<c:url value='/_03_ConvenienceProcess/ConInsert.do' />"
						method="POST">
						<div class="modal-body">
							<input type="text" class="form-control" name="busChildNo"
								value="${NoConvenience.busChildNo}" style="display: none;">
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">服務種類:</label>
								<input type="text" class="form-control" name="convenience">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">服務項目:</label>
								<input type="text" class="form-control" name="conveniencelist">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">公休日:</label>
								<input type="text" class="form-control" name="concloseday">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">開始營業時間:</label>
								<input type="text" class="form-control" name="conopentime">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">結束營業時間:</label>
								<input type="text" class="form-control" name="conclosetime">
							</div>
							<div class="form-group">
								<label for="message-text" class="col-form-label">備註:</label>
								<textarea class="form-control" name="buschilddescription"></textarea>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary">Save
									changes</button>
							</div>
						</div>
					</FORM>
				</div>
			</div>
		</div>
	</c:forEach>






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
	<script src="https://kit.fontawesome.com/8e822d04fb.js" crossorigin="anonymous"></script>
	<script src="../resources/javascript/Convenience.js"></script>
</body>
</html>