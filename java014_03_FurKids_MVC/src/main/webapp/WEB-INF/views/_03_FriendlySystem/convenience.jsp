<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
${Convenience.busChildNo}
<div class="job">
	<!-- 已上架的分店 -->
	<div class="container p-1g-5 p-3 wow bounceIn">
		<div class="row">
			<c:forEach var="Convenience" items="${AllConvenience}">
				<div class="col-lg-3 col-sm-6 col-2 mb-4 insertcon">
					<div class="card bg-light mb-3 border-dark con shadow-lg rounded">
						<img
							src="<c:url value='/_03_FriendlySystem/getPicture/${Convenience.busChildNo}' />"
							style="width: 100%; height: 140px;" class="card-img-top"
							>
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
						<span><i style="z-index: 10000; font-size: 30px;">新增</i></span>
						<!-- 新增按鈕 -->
						<div class="dropdown" style="z-index: 20;">
							<button class="btn btn-secondary-outline dropdown-toggle fa-4x"
								type="button" id="dropdownMenu2" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"></button>
							<div class="dropdown-menu" aria-labelledby="dropdownMenu2">
								<!-- 未上架的分店 -->
								<c:forEach var="NoConvenience" items="${NotConvenience}">
									<button name="submit1" value="${NoConvenience.busChildName}"
										class="dropdown-item btn notInsertCon" type="button"
										data-toggle="modal"
										data-target="#${NoConvenience.busChildName}">${NoConvenience.busChildName}</button>
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

						<FORM
							action="<c:url value='/_03_ConvenienceProcess/ConRevise.do' />"
							method="POST" class="ccc" style="display: none">

							<input type="text" name="reviseno"
								value="${Convenience.busChildNo}" style="display: none">

							<div class="form-group">
								<label for="recipient-name" class="col-form-label">服務種類:</label>
								<input type="text" class="form-control" name="convenience"
									value="${Convenience.conItem}">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">服務項目:</label>
								<input type="text" class="form-control" name="conveniencelist"
									value="${Convenience.conItemList}">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">地址:</label> <input
									type="text" class="form-control" name="busChildAddress"
									value="${Convenience.merchantChildBean.busChildAddress}">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">公休日:</label>
								<input type="text" class="form-control" name="concloseday"
									value="${Convenience.conCloseDay}">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">開始營業時間:</label>
								<input type="text" class="form-control" name="conopentime"
									value="${Convenience.conOpenTime}">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">結束營業時間:</label>
								<input type="text" class="form-control" name="conclosetime"
									value="${Convenience.conCloseTime}">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">分店信箱:</label>
								<input type="text" class="form-control" name="busEmail"
									value="${Convenience.merchantChildBean.merchantbean.busEmail}">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">分店電話:</label>
								<input type="text" class="form-control" name="busChildTel"
									value="${Convenience.merchantChildBean.busChildTel}">
							</div>
							<div class="form-group">
								<label for="message-text" class="col-form-label">備註:</label>
								<textarea class="form-control" name="buschilddescription">${Convenience.merchantChildBean.busChildDescription}</textarea>
							</div>
							<div>
								<button type="submit" class="btn btn-primary">確定修改</button>
							</div>
						</FORM>
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
						<FORM action="<c:url value='/_03_ConvenienceProcess/Delete.do' />"
							method="POST">
							<input type="text" name="deleteNo"
								value="${Convenience.busChildNo}" style="display: none">
							<button type="submit" class="btn btn-danger delete">刪除</button>
						</FORM>
						<button type="button" class="btn btn-secondary revise">修改</button>

					</div>
				</div>
			</div>
		</div>
	</c:forEach>







	<!-- 新增的Modal-->
	<!-- 	Modal -->
	<%-- 	${NoConvenience.busChildName}1 --%>
	<c:forEach var="NoConvenience" items="${NotConvenience}">
		<div class="modal fade ppp" id="${NoConvenience.busChildName}"
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
								<font class="errmsg" color="red" size="-1">${ErrorMsg.ConvenienceError}</font>
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
								<button type="submit" class="btn btn-primary">新增</button>
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
	<script src="https://kit.fontawesome.com/8e822d04fb.js"
		crossorigin="anonymous"></script>
	<script src="../resources/javascript/Convenience.js"></script>
	<c:if test="${!empty ErrorMsg}">

		<button id="pipimou" class="dropdown-item btn insertcon" type="button"
			data-toggle="modal" data-target="#${pilimou}" style="display: none">${test}</button>
		<script type="text/javascript">
			$('#pipimou').trigger('click');
		</script>
	</c:if>
</body>
</html>