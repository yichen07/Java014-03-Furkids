<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script type="text/javascript">
function cancelOrder() {
	if (confirm("確定取消此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.forms[1].finalDecision.value = "CANCEL";
		document.forms[1].action="<c:url value='/_02_ShoppingSystem/ProcessOrder' />";
		document.forms[1].method="POST";
		document.forms[1].submit();
		return;
	} else {
		return;
	}
}
function reconfirmOrder() {
	var sa = document.getElementById('ShippingAddress').value;
	if  (sa === "") {
		window.alert ('出貨地址不能是空白');
		return ; 
	}
	if (confirm("確定送出此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.forms[1].finalDecision.value = "ORDER";
		document.forms[1].action="<c:url value='/_02_ShoppingSystem/ProcessOrder' />";
		document.forms[1].method="POST";
		document.forms[1].submit();
		return;
	} else {
		return;
	}
}
</script>
<meta charset="UTF-8">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 取得今天的日期，今天的日期應當在最後確認時才取得 -->
<jsp:useBean   id="today"  class="java.util.Date" scope="session"/> 
<title>訂單明細資訊確認</title>
</head>
<body style="background:#FFFFFF;">
<c:set var="funcName" value="CHE" scope="session"/>
<jsp:include page="/fragment/navigation.jsp" />
<div style="text-align:center">
<h3>請確認下列訊息：</h3>
<FORM style="margin: 0 auto; width:750px;" action="<c:url value='ProcessOrder' />" method="POST" >
  
   <TABLE border='1' style="background:#F5EBFF; border-color:rgb( 100, 100, 255); border-style: outset; width:810;">
   
<!--    此處判斷是會員還是商家 -->
   <c:if test="${LoginOK.CLASSIFY == 0}">
      <TR >
         <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;">
         	會員編號：${LoginOK.cusAccount}
         </TD>
         <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;">
         	客戶姓名：${LoginOK.cusName}
         </TD>
         <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;">
         	訂單日期：<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>
         </TD>
      </TR>
      <TR>
         <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;">
         	會員地址：${LoginOK.cusAddress}
         </TD>
      </TR>
      <TR>
         <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;">
                             出貨地址：<Input style="background:#ECFFCD;" size="60" type="text" id='ShippingAddress' 
                   name="ShippingAddress" value="">
                   <font color='red'>${errorMsg.ShippingAddress}</font>
         </TD>
      </TR>
      </c:if>
       <c:if test="${LoginOK.CLASSIFY == 1}">
      <TR >
         <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;">
         	會員編號：${LoginOK.busAccount}
         </TD>
         <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;">
         	客戶姓名：${LoginOK.busName}
         </TD>
         <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;">
         	訂單日期：<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>
         </TD>
      </TR>
      <TR>
         <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;">
         	會員地址：${LoginOK.busAddress}
         </TD>
      </TR>
      <TR>
         <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;">
                             出貨地址：<Input style="background:#ECFFCD;" size="60" type="text" id='ShippingAddress' 
                   name="ShippingAddress" value="">
                   <font color='red'>${errorMsg.ShippingAddress}</font>
         </TD>
      </TR>
      </c:if>
<!--    此處判斷是會員還是商家 -->
    
      <TR>
         <TD colspan='3'>
         
   <TABLE border='1' style="background:#FFE7CD; border-color:rgb( 100, 100, 255); " >
      
     <TR><TH style="text-align:center;font-size: 12pt;" width="350">商品名稱</TH>
<!--          <TH style="text-align:center;font-size: 12pt;" width="80">作者</TH> -->
         <TH style="text-align:center;font-size: 12pt;" width="80">廠商</TH>
         <TH style="text-align:center;font-size: 12pt;" width="80">單價</TH>
         <TH style="text-align:center;font-size: 12pt;" width="60">數量</TH>
         <TH style="text-align:center;font-size: 12pt;" width="110">小計</TH></TR>
     
     <c:forEach varStatus="vs" var="entry" items="${ShoppingCart.content}">
                                                    
        <TR height='16'>
          <TD style="text-align:left  ;font-size: 11pt;">${entry.value.comName}</TD>
<!--           <TD style="text-align:center;font-size: 11pt;"> -->
<%--           	${fn:substring(entry.value.author, 0, 3)} --%>
<!--           </TD> -->
          <TD style="text-align:center;font-size: 11pt;">
          	${entry.value.busName}
          </TD>
          <TD style="text-align:right ;font-size: 11pt;">
          	<fmt:formatNumber value="${entry.value.ordUnitPrice }" pattern="#,###" />元
          </TD>
          <TD style="text-align:right ;font-size: 11pt;"> 
          	${entry.value.ordQuantity}
          </TD>
          <TD style="text-align:right ;font-size: 11pt;">
          	<fmt:formatNumber 
          	value="${entry.value.ordUnitPrice * entry.value.ordQuantity}" pattern="#,###,###" />元
          </TD>
        </TR>
     </c:forEach>
     
        <TR height='16'>
          <TD style="text-align:right;font-size: 11pt;" colspan='5' >合計金額：</TD>
          <TD style="text-align:right;font-size: 11pt;" >
          <fmt:formatNumber value="${ShoppingCart.subtotal}" pattern="#,###,###" />元</TD>
                  
        </TR>
<!--         <TR> -->
<!--           <TD colspan='5' style="text-align:right;font-size: 11pt;" >營業稅：</TD> -->
<%--           <c:set var="VAT" value="${ShoppingCart.subtotal*0.05 + 0.0001}"/> --%>
<!--           <TD style="text-align:right;font-size: 11pt;" >  -->
<%--           <fmt:formatNumber value="${VAT}" pattern="#,###,###" />元</TD> --%>
<!--         </TR> -->
        <TR>
          <TD colspan='5' style="text-align:right;font-size: 11pt;" >總計金額：</TD>
          <TD style="text-align:right;font-size: 11pt;color:#AA0200;" >
          <fmt:formatNumber value="${ShoppingCart.subtotal + VAT }" pattern="#,###,###" />元</TD>
        </TR>
   </TABLE>
          </TD>
      </TR>
 
   </TABLE><P/>
   <input type="hidden" name="finalDecision"  value="">   
   <input type="button" name="OrderBtn"  value="確定送出" onclick="reconfirmOrder()">
   <input type="button" name="CancelBtn" value="取消訂單" onclick="cancelOrder()">
</FORM>
</div>

</body>
</html>