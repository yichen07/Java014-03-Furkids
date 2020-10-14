<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
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
<title>訂單列表</title>
<style type="text/css">
#borderA {
	border: 1px solid black;
}
</style>
</head>
<body>
	<c:set var="funcName" value="ORD" scope="session" />

	<!-- 引入共同的頁首 -->
		<jsp:include page="/fragment/navigation.jsp" />
	<p />
	<div class="m-5">
		<div class="m-5">

			<!-- 	<table style="margin-left:auto; margin-right:auto; width:810; background:#F5EBFF; border:2px solid blue; border-style: outset; "> -->
			<table class="table table-striped">

				<tr id='borderA' height='50'>
					<th id='borderA' colspan="5" align="center">${LoginOK.cusName}的訂購紀錄</th>
				</tr>
				<tr id='borderA' height='36' scope="row">
					<th id='borderA'>訂單編號</th>
					<th id='borderA'>訂購日期</th>
					<th id='borderA'>總金額</th>
					<th id='borderA'>送貨地址</th>
					<th id='borderA'>訂單狀態</th>
				</tr>
				<c:forEach var="anOrderBean" varStatus="stat"
					items="${memberOrders}">
					<TR id='borderA' height='30' scope="row">
						<TD id='borderA' width="86" align="center"><a
							href='<c:url value='/_05_orderProcess/orderDetail?m_No=${LoginOK.m_No}&orderNo=${anOrderBean.s_OrderNo}' />'>
								${anOrderBean.s_OrderNo} </a></TD>
						<%-- 			<TD id='borderA' width="100" align="center">${anOrderBean.s_OrderDate}</TD> --%>
						<TD id='borderA' width="100" align="center"><fmt:formatDate
								value='${anOrderBean.s_OrderDate}' type='date' /></TD>
						<%-- 			<TD id='borderA' width="80" align="right">${anOrderBean.s_OrderSum}</TD> --%>
						<TD id='borderA' width="80" align="right"><fmt:formatNumber
								value="${anOrderBean.s_OrderSum}" pattern="#,###,###" />元</TD>
						<TD id='borderA' width="380" align="left">&nbsp;${anOrderBean.s_M_Address}</TD>
						<TD id='borderA' width="80" align="left">&nbsp; <c:if
								test="${anOrderBean.s_Status ==0}">收到訂單</c:if> <c:if
								test="${anOrderBean.s_Status ==1}">撿貨</c:if> <c:if
								test="${anOrderBean.s_Status ==2}">理貨</c:if> <c:if
								test="${anOrderBean.s_Status ==3}">出貨</c:if> <c:if
								test="${anOrderBean.s_Status ==4}">已送達</c:if>
						</TD>

					</TR>
				</c:forEach>
				<tr height='36' id='borderA'>
					<td id='borderA' align="center" colspan="5"><a
						href="<c:url value='/' />">回首頁</a></td>
				</tr>
			</TABLE>
		</div>
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
	<script src="<c:url value='/resources/javascript/bootstrap.bundle.min.js' />"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS End-------------------------------->

	<!-- JavaScript Plug-in ---------------------------------------------------------------->

	<!-- icon -->
	<script src="https://kit.fontawesome.com/8e822d04fb.js"
		crossorigin="anonymous"></script>
	
	
</body>
</html>