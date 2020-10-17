<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
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


    <!-- Inport CSS End--------------------------------------------------------------------->

    <link
      rel="stylesheet"
      href="<c:url value='/resources/css/_02_ShoppingSystem/shoppingList_4.css' />"
    />

<!-- 取得今天的日期，今天的日期應當在最後確認時才取得 -->
<jsp:useBean   id="today"  class="java.util.Date" scope="session"/> 
<title>訂單明細資訊確認</title>
</head>
<body>
<c:set var="funcName" value="CHE" scope="session"/>

<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/navigation.jsp" />

<div style="text-align:center">
<!-- 新頁面_開始 -->
<!-- Banner ---------------------------------------------------------------------------->

    <div
      class="container-fluid bannerImg d-flex justify-content-center align-items-center"
      id="imageStellar"
      data-stellar-background-ratio="0.5"
      style="background-image: url(<c:url value='/resources/images/_02_ShoppingSystem/shoppingList_Banner_01.jpg' />)"
    >
      <div class="row ">
        <div class="col-lg-12 text-center">
          <h1 class="mt-3 bannerTitle animate__animated animate__fadeInDown">
            確認訂單
          </h1>
          <div
            class="align-items-center banner-text animate__animated animate__fadeInUp"
          >
            <h5 class="text-center">
              超多特惠商品，週週下殺便宜精選。
            </h5>
          </div>
        </div>
      </div>
    </div>

    <!-- Banner End------------------------------------------------------------------------->



<!-- Product Detail Start -------------------------------------------------------------->
<FORM action="<c:url value='preProcessOrder' />" method="POST" >
<c:set var="VAT" value="${ShoppingCart.subtotal*0.05 + 0.0001}"/>
    <div class="accordion container mt-5 mb-4">
      <div class="contentBx active">
        <!-- 訂單明細標題 -->
        <div class="row label">
          <div
            class="col-lg-12 d-flex justify-content-between align-items-center py-3"
          >
            <div class="ml-5 productListNameHead">訂單明細</div>
            <div class="productListNameHead">
              總計 <span class="pr-3 listTotalPrice"><fmt:formatNumber value="${ShoppingCart.subtotal + VAT }" pattern="#,###,###" />元</span>
            </div>
          </div>
        </div>
        <!-- 訂單明細標題 End-->
        <!-- 欄位名稱 -->
        <div class="content row mb-0">
          <div
            class="col-lg-8 col-8 d-flex justify-content-center align-items-center shoppingListNameText"
          >
            商品
          </div>

          <div
            class="col-lg-2 col-2 d-flex justify-content-center align-items-center shoppingListNameText"
          >
            數量
          </div>
          <div
            class="col-lg-2 col-2 d-flex justify-content-center align-items-center shoppingListNameText"
          >
            價格
          </div>
        </div>
        <!-- 欄位名稱 End-->
        <!-- 商品細項1 -->
        <c:forEach varStatus="vs" var="entry" items="${ShoppingCart.content}">
        <div class="content row">
          <div
            class="col-lg-8 col-8 d-flex justify-content-center align-items-center productListNameText"
          >
            ${entry.value.soiTitle}
          </div>

          <div
            class="col-lg-2 col-2 d-flex justify-content-center align-items-center productListNameText"
          >
            ${entry.value.soiQty}
          </div>
          <div
            class="col-lg-2 col-2 d-flex justify-content-center align-items-center productListNameText"
          >
            $<fmt:formatNumber 
          	value="${entry.value.soiPrice * entry.value.soiQty}" pattern="#,###,###" />元
          </div>
        </div>
        </c:forEach>
        <!-- 商品細項1 -->
        
        <!-- 金額細項 -->
        <div class="content row">
          <div class="col-lg-12 col-12 text-right priceDetail pb-2 pr-5">
            總計：<fmt:formatNumber value="${ShoppingCart.subtotal}" pattern="#,###,###" />+<c:set var="VAT" value="${ShoppingCart.subtotal*0.05 + 0.0001}"/>
            <fmt:formatNumber value="${VAT}" pattern="#,###,###" />(營業稅0.05%) = 
            $<fmt:formatNumber value="${ShoppingCart.subtotal + VAT }" pattern="#,###,###" />元
          </div>
        </div>
        <!-- 金額細項 End-->
      </div>
    </div>
    <!-- Product Detail End ---------------------------------------------------------------->






    <!-- Customer Info Start---------------------------------------------------------------->
    
    
      <div class="container custInfo mb-4">
        <div class="row p-3">
          <!-- 左邊資料 Start-->
          <div class="col-md-6 px-3 custInfoLeft">
            <div
              class="row d-flex justify-content-center align-items-center  mb-4"
            > <h5 class="pb-4 pt-2 custInfoText "><i class="far fa-user custIcon"></i> 購買人資料</h5>
             <div class="col-12 pr-4">
                <div class="input-group mb-4">
                  <label for="">姓名</label>
                  <input
                    type="text"
                    name="name"
                    class="inputClass"
                    id="inputStyle"
                    value="${LoginOK.cusName}"
                    disabled
                  />
                </div>
                <div class="input-group mb-4">
                  <label for="">手機號碼</label>
                  <input
                    type="tel"
                    name="name"
                    class="inputClass"
                    id="inputStyle"
                    value="${LoginOK.cusTel}"
                    disabled
                  />
                </div>
                <div class="input-group mb-4">
                  <label for="">地址</label>
                  <input
                    type="email"
                    name="name"
                    class="inputClass"
                    id="inputStyle"
                    value="${LoginOK.cusAddress}"
                    disabled
                  />
                </div>
                
              </div>
            </div>
          </div>
          <!-- 左邊資料 End -->
         <!-- 右邊資料 Start-->
         <div class="col-md-6 px-3 ">
          <div
            class="row d-flex justify-content-center align-items-center  mb-4"
          > <h5 class="pb-4 pt-2 custInfoText custInfoRightText"><i class="fas fa-user-check custIcon"></i> 收件地址(可修改)</h5>
           <div class="col-12 ml-md-2 ml-0 ">
              <div class="input-group mb-4 ">
                <label for="">發票抬頭</label>
                <Input class="inputClass" size="50" type="text" name="InvoiceTitle" value="" >
            
              </div>
              <div class="input-group mb-4">
                <label for="">統一編號</label>
				<Input class="inputClass" size="10" type="text" name="BNO" value="">
               
              </div>
              <div class="input-group mb-4">
                <label for="">地址</label>
			  <Input class="inputClass" size="60" type="text" id='ShippingAddress' 
                   name="ShippingAddress" value="${LoginOK.cusAddress}" >
                   <font color='red'>${errorMsg.ShippingAddress}</font>
              
              </div>
              
            </div>
          </div>
        </div>
        <!-- 右邊資料 End -->
        </div>
         <div class="row justify-content-center align-items-center">
        <div class="shoppingBtn col-lg-2 col-md-3 col-sm-6 col-xs-6 mt-3">
<!--           <a -->
<!--             href="#" -->
<!--             class="btn btnEffect02 effect02 mb-4 d-flex align-items-center justify-content-center" -->
<!--             ><div class="btnText1">提交訂單</div> -->
<!--             </a> -->
           <input type="hidden" name="finalDecision"  value="">   
   			<input type="button" name="OrderBtn"  value="確定送出" onclick="reconfirmOrder()" class="btn btnEffect02 effect02 mb-4 d-flex align-items-center justify-content-center btnText1">
   			<input type="button" name="CancelBtn" value="取消訂單" onclick="cancelOrder()" class="btn btnEffect02 effect02 mb-4 d-flex align-items-center justify-content-center btnText1">
   			
   			
<!--    			<a href="javascript: if(window.confirm('是否送出訂單?')){window.location.href='ProcessOrder'}"> -->
<!--    			 <input type="button" name="OrderBtn"  value="確定送出"  class="btn btnEffect02 effect02 mb-4 d-flex align-items-center justify-content-center btnText1"> -->
<!--    			</a> -->
<!--    			<a href="javascript: if(window.confirm('是否取消訂單?')){window.location.href='cancelOrder'}"> -->
<!--    			 <input type="button" name="CancelBtn" value="取消訂單"  class="btn btnEffect02 effect02 mb-4 d-flex align-items-center justify-content-center btnText1"> -->
<!--    			</a> -->
        </div>
      </div>
      
<!--       有動畫的按鈕 -->
<!-- 		<div class="row justify-content-center align-items-center"> -->
<!--         <div class="shoppingBtn col-lg-2 col-md-3 col-sm-6 col-xs-6 mt-3"> -->
<!--           <a -->
<!--             href="#" -->
<!--             class="btn btnEffect02 effect02 mb-4 d-flex align-items-center justify-content-center" -->
<!--              onclick="reconfirmOrder()"><div class="btnText1">提交訂單</div></a> -->
<!--         </div> -->
<!--         <div class="shoppingBtn col-lg-2 col-md-3 col-sm-6 col-xs-6 mt-3"> -->
<!--           <a -->
<!--             href="#" -->
<!--             class="btn btnEffect02 effect02 mb-4 d-flex align-items-center justify-content-center" -->
<!--              onclick="cancelOrder()"><div class="btnText1">取消訂單</div></a> -->
<!--         </div> -->
<!--       </div> -->
      </div>
    
     </FORM>
    <!-- Customer Info End ----------------------------------------------------------------->

<!-- 新頁面_結束 -->
<!-- <h3>請確認下列訊息：</h3> -->
<%-- <FORM style="margin: 0 auto; width:750px;" action="<c:url value='preProcessOrder' />" method="POST" > --%>
<!--    <TABLE border='1' style="background:#F5EBFF; border-color:rgb( 100, 100, 255); border-style: outset; width:810;"> -->
<!--       <TR > -->
<!--          <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<%--          	會員編號：${LoginOK.m_No} --%>
<!--          </TD> -->
<!--          <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<%--          	客戶姓名：${LoginOK.m_Name} --%>
<!--          </TD> -->
<!--          <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<%--          	訂單日期：<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/> --%>
<!--          </TD> -->
<!--       </TR> -->
<!--       <TR> -->
<!--          <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<%--          	會員地址：${LoginOK.m_Add} --%>
<!--          </TD> -->
<!--       </TR> -->
<!--       <TR> -->
<!--          <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<!--                              出貨地址：<Input style="background:#ECFFCD;" size="60" type="text" id='ShippingAddress'  -->
<%--                    name="ShippingAddress" value="${LoginOK.m_Add}"> --%>
<%--                    <font color='red'>${errorMsg.ShippingAddress}</font> --%>
<!--          </TD> -->
<!--       </TR> -->
<!--       <TR> -->
<!--          <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<!--                                    統一編號：<Input style="background:#ECFFCD;" size="10" type="text"  -->
<!--                       name="BNO" value=""> -->
<!--          </TD> -->
<!--       </TR> -->
<!--       <TR> -->
<!--          <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<!--                                    發票抬頭：<Input style="background:#ECFFCD;" size="50" type="text"  -->
<%--                       name="InvoiceTitle" value="${LoginOK.m_Name}" > --%>
<!--          </TD> -->
<!--       </TR> -->
    
<!--       <TR> -->
<!--          <TD colspan='3'> -->
         
<!--    <TABLE border='1' style="background:#FFE7CD; border-color:rgb( 100, 100, 255); " > -->
      
<!--      <TR><TH style="text-align:center;font-size: 12pt;" >商品名稱</TH> -->
<!--          <TH style="text-align:center;font-size: 12pt;" >商品類別</TH> -->
<!--          <TH style="text-align:center;font-size: 12pt;" >簡述</TH> -->
<!--          <TH style="text-align:center;font-size: 12pt;" >單價</TH> -->
<!--          <TH style="text-align:center;font-size: 12pt;" >數量</TH> -->
<!--          <TH style="text-align:center;font-size: 12pt;" >小計</TH></TR> -->
     
<%--      <c:forEach varStatus="vs" var="entry" items="${ShoppingCart.content}"> --%>
                                                    
<!--         <TR height='16'> -->
<%--           <TD style="text-align:left  ;font-size: 11pt;">${entry.value.soiTitle}</TD> --%>
<!--           <TD style="text-align:center;font-size: 11pt;"> -->
<%--           	${entry.value.soiCategory} --%>
<!--           </TD> -->
<!--           <TD style="text-align:center;font-size: 11pt;"> -->
<%--           	${fn:substring(entry.value.soiDescription, 0, 20)} --%>
<!--           </TD> -->
<!--           <TD style="text-align:right ;font-size: 11pt;"> -->
<%--           	<fmt:formatNumber value="${entry.value.soiPrice }" pattern="#,###" />元 --%>
<!--           </TD> -->
<!--           <TD style="text-align:right ;font-size: 11pt;">  -->
<%--           	${entry.value.soiQty} --%>
<!--           </TD> -->
<!--           <TD style="text-align:right ;font-size: 11pt;"> -->
<%--           	<fmt:formatNumber  --%>
<%--           	value="${entry.value.soiPrice * entry.value.soiQty}" pattern="#,###,###" />元 --%>
<!--           </TD> -->
<!--         </TR> -->
<%--      </c:forEach> --%>
     
<!--         <TR height='16'> -->
<!--           <TD style="text-align:right;font-size: 11pt;" colspan='5' >合計金額：</TD> -->
<!--           <TD style="text-align:right;font-size: 11pt;" > -->
<%--           <fmt:formatNumber value="${ShoppingCart.subtotal}" pattern="#,###,###" />元</TD> --%>
                  
<!--         </TR> -->
<!--         <TR> -->
<!--           <TD colspan='5' style="text-align:right;font-size: 11pt;" >營業稅：</TD> -->
<%--           <c:set var="VAT" value="${ShoppingCart.subtotal*0.05 + 0.0001}"/> --%>
<!--           <TD style="text-align:right;font-size: 11pt;" >  -->
<%--           <fmt:formatNumber value="${VAT}" pattern="#,###,###" />元</TD> --%>
<!--         </TR> -->
<!--         <TR> -->
<!--           <TD colspan='5' style="text-align:right;font-size: 11pt;" >總計金額：</TD> -->
<!--           <TD style="text-align:right;font-size: 11pt;color:#AA0200;" > -->
<%--           <fmt:formatNumber value="${ShoppingCart.subtotal + VAT }" pattern="#,###,###" />元</TD> --%>
<!--         </TR> -->
<!--    </TABLE> -->
<!--           </TD> -->
<!--       </TR> -->
 
<!--    </TABLE><P/> -->
<!--    <input type="hidden" name="finalDecision"  value="">    -->
<!--    <input type="button" name="OrderBtn"  value="確定送出" onclick="reconfirmOrder()"> -->
<!--    <input type="button" name="CancelBtn" value="取消訂單" onclick="cancelOrder()"> -->
<!-- </FORM> -->
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
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/javascript/bootstrap.bundle.min.js"></script> --%>
	<!-- jQuery first, then Popper.js, then Bootstrap JS End-------------------------------->

	<!-- JavaScript Plug-in ---------------------------------------------------------------->

	<!-- icon -->
	<script src="https://kit.fontawesome.com/8e822d04fb.js"
		crossorigin="anonymous"></script>

<!-- 	<!-- banner effect --> -->
<%-- 	<script src="<c:url value='/resources/javascript/jquery.stellar.js' />"></script> --%>
	
	<!-- JavaScript Plug-in End------------------------------------------------------------->
    <script src="<c:url value='/resources/javascript/shoppingList_4.js' />"></script>	
	
	<script type="text/javascript">
window.onload()
function cancelOrder() {
	if (confirm("確定取消此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.forms[1].finalDecision.value = "CANCEL";
		document.forms[1].action="<c:url value='cancelOrder' />";
		document.forms[1].method="GET";
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
		document.forms[1].action="<c:url value='ProcessOrder' />";
		document.forms[1].method="POST";
		document.forms[1].submit();
		return;
	} else {
		return;
	}
}
</script>
	
	
	
	
	<!-- ---------------------------分隔線--------------------------------------- -->
	<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- ---------------------------分隔線--------------------------------------- -->
	
	
	
	

   
</body>
</html>