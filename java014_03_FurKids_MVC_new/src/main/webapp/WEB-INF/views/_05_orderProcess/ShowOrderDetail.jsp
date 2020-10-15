<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>個人訂單明細</title>
<style type="text/css">
#main {
	position: absolute;
	top: 110px;
	left: 210px;
}

#borderA {
	border: 1px solid black;
}
</style>
</head>
<body>
	<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/navigation.jsp" />
	<p />
	
	<div class="m-5">
	<div class="m-5">
	
<!-- 	<TABLE style="margin-left: 10%; margin-right: 10%; background: #F0E4F4; border: 1px blue solid;"> -->
	<TABLE class="table table-striped">
		<tr id='borderA' height='50'>
			<th id='borderA' align="center" colspan="5"><h3>${LoginOK.cusName}的訂單明細</h3></th>
		</tr>
		<tr id='borderA' height='36'>
			<td colspan="5">
				<table width="100%" class="m-0">
					<tr id='borderA'>
						<td align="Left" width="350px">
							<b>出貨地址：</b>${SaleOrderBean.s_M_Address}
						</td>
						<td align="center" width="300px">
							<b>訂購日期：</b><fmt:formatDate value='${SaleOrderBean.s_OrderDate}' type='date'/>
						</td>
						<td align="center" width="280px">
							<b>訂單編號：</b>${SaleOrderBean.s_OrderNo}
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr id='borderA' height='36'>
			<th id='borderA' width="100px" align="center">商品編號</th>
			<th id='borderA' width="400px" align="center">商品資訊</th>
			<th id='borderA' width="70px" align="center">單價</th>
			<th id='borderA' width="50px" align="center">數量</th>
			<th id='borderA' width="100px" align="center">總價</th>
<!-- 			<th id='borderA' width="80px" align="center">折扣</th> -->
<!-- 			<th id='borderA' width="100px" align="center">售價</th> -->
		</tr>
		<c:set var="subtotal" value="0" />
		<c:forEach var="aBean" varStatus="entry" items="${SaleOrderBean.items}">
			<c:choose>
				<c:when test="${ entry.count % 2 == 0 }">
					<c:set var="aColor" value="#E6FFA0" />
				</c:when>
				<c:otherwise>
					<c:set var="aColor" value="#EBFFEB" />
				</c:otherwise>
			</c:choose>
			<tr id='borderA' bgColor="${aColor}" height='30'>
				<td id='borderA' align="center">${aBean.soi_P_Id}</td>
				<td id='borderA' align="left">${aBean.soiDescription}</td>
				<td id='borderA' align="right">${aBean.soiPrice}&nbsp;</td>
				<td id='borderA' align="right">${aBean.soiQty}&nbsp;</td>
				<td id='borderA' align="right">${aBean.soiPrice*aBean.soiQty}&nbsp;</td>
<%-- 				<td id='borderA' align="center">${aBean.soiDiscount}&nbsp;</td> --%>
<%-- 				<td id='borderA' align="right"><fmt:formatNumber --%>
<%-- 						value="${aBean.soiPrice*aBean.soiDiscount*aBean.soiQty}" --%>
<%-- 						pattern="#,###,###" />元</td> --%>
				<c:set var="subtotal"
					value="${ subtotal + aBean.soiPrice * aBean.soiQty }" />
			</tr>
		</c:forEach>
		<tr height='30' >
			<TD id='borderA' align="center" rowspan="3" colspan="3">&nbsp;</TD>
			<TD id='borderA' width="60px" align="center"><b>合 計</b></TD>
			<TD id='borderA' width="100px" align="right">
			   <fmt:formatNumber value="${subtotal}" pattern="#,###,###" />元</TD>
		</tr>
		<tr height='30'>
			<TD id='borderA' width="60px" align="center"><b>營業稅</b></TD>
			<c:set var="VAT" value="${subtotal*0.05 + 0.0001}" />
			<TD id='borderA' width="100px" align="right">
			   <fmt:formatNumber value="${VAT}" pattern="#,###,###" />元</TD>
		</tr>
		<tr height='30'>
			<TD id='borderA' width="60px" align="center"><b>總金額</b></TD>
			<TD id='borderA' width="100px" align="right">
			    <fmt:formatNumber value="${SaleOrderBean.s_OrderSum}" pattern="#,###,###" />元</TD>
		</tr>
	</TABLE>
	<p />

	<div style="text-align: center">
		<a href="<c:url value='orderList' />">回上一頁</a>&nbsp;&nbsp;
		<a href="<c:url value='/' />">回首頁</a>
	</div>
	
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
	<!-- navigation bar js ------------------------------------->
	<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->
	
	
	
	
</body>
</html>
