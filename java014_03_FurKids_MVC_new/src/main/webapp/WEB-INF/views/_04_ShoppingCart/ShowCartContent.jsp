<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
    
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance 
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility 
%>
<!DOCTYPE html>
<html>
<head>
    <!-- Inport CSS End--------------------------------------------------------------------->
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
    
    <link rel="stylesheet"
      href="<c:url value='/resources/css/_02_ShoppingSystem/shoppingList_3.css' />" />

    <!-- Input CSS End---------------------------------------------------------------------->
    
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>



<meta charset="UTF-8">
<title>購物清單</title>
</head>
<body>
<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/navigation.jsp" />

<c:set var="funcName" value="CHE" scope="session"/>


<c:choose>
   <c:when test="${ShoppingCart.subtotal > 0}">
      <c:set var="subtotalMessage" value="金額小計:${ShoppingCart.subtotal} 元"/>
      <c:set var="subtotal" value="${ShoppingCart.subtotal}"/>  
   </c:when>
   <c:otherwise>
      <c:set var="subtotalMessage" value="金額小計:  0 元"/>
      <c:set var="subtotal" value="0"/>                
   </c:otherwise>
</c:choose>
<!-- 新畫面STAR -->
<!-- Main Start------------------------------------------------------------------------->

    <div class="container mt-5 mb-3 productList shadow">
      <!-- 免運資格 Start-->
      <div class="row">
        <div
          class="d-flex col-md-12 justify-content-between align-items-center shippingTitle"
        >
          <div><i class="fas fa-truck"></i> 免運資格</div>
          <div class="pr-1">
           開幕慶 <span class="shippingMoney">免連費</span>
          </div>
        </div>
      </div>
      <!-- 免運資格 End-->
      <!-- 確認訂單 Start-->
      <div class="row">
        <div
          class="d-flex col-md-12 justify-content-center align-items-center shoppingListTitleTop py-3 pt-5"
        >
          <h2>確認訂單</h2>
        </div>
      </div>
      <!-- 欄位名稱 Start-->
      <div class="row px-3 shoppingListName">
        <div
          class="d-flex col-md-1 col-sm-12 justify-content-center align-items-center shoppingListNameText"
        ></div>
        <div
          class="d-flex col-md-5 col-sm-12 justify-content-center align-items-center shoppingListNameText"
        >
          商品
        </div>

        <div
          class="d-flex col-md-2 col-sm-4 justify-content-center align-items-center shoppingListNameText"
        >
          刪除
        </div>
        <div
          class="d-flex col-md-2 col-sm-4 justify-content-center align-items-center shoppingListNameText"
        >
          數量
        </div>
        <div
          class="d-flex col-md-2 col-sm-4 justify-content-end align-items-center shoppingListNameText pr-5"
        >
          小計
        </div>
      </div>
      <!-- 商品細項欄位 no1 Start-->
      <div class="row p-3 shoppingListItemAll">
      <c:forEach varStatus="vs" var="anEntry" items="${ShoppingCart.content}">
        <div
          class="col-lg-1 col-4 d-flex justify-content-center align-items-center colImage shoppingListItem"
        >
          <img
            class="productImg"
            src="<c:url value='/_00_init/getProductImage?id=${anEntry.value.soi_P_Id}' />"
          />
        </div>
        <div
          class="col-lg-5 col-8 d-flex justify-content-center align-items-center colProductName shoppingListItem"
        >
          ${anEntry.value.soiTitle}
        </div>
        <div
          class="d-flex col-lg-2 col-4 justify-content-center align-items-center colTrash shoppingListItem"
        >
        <Input type="button" name="delete" value="🗑️" onclick="confirmDelete(${anEntry.key})" class="btn trashButton">
<!--           <button class="btn trashButton"> -->
<!--             <i class="fas fa-trash-alt"></i> -->
<!--           </button> -->
        </div>
        <div
          class="d-flex col-lg-2 col-4 justify-content-center align-items-center colQuantity shoppingListItem"
        >

          <!-- 第二種寫法   -->
<%--           <input type="button" id="subs${vs.index}" value="-" class="trashButton"  onclick="modify(${anEntry.key}, ${anEntry.value.soiQty}, ${vs.index})"/> --%>
          <input type="button" id="sub10s${vs.index}" value="-10" class="trashButton"/>
          <input type="button" id="subs${vs.index}" value="-" class="trashButton"/>
<%-- <Input type="button" name="update" value="修改" onclick="modify(${anEntry.key}, ${anEntry.value.soiQty}, ${vs.index})"> --%>
<!--           <input -->
<!--             type="text" -->
<%--             name="newQty${vs.index}" --%>
<!--             class="onlyNumber form-control text-center p-0" -->
<!--             id="noOfRoom" -->
<!--             value="1" -->
<!--           /> -->
		<fieldset disabled>
          <Input id="newQty${vs.index}" style="width:28px;text-align:center" name="newQty" type="text" value="<fmt:formatNumber value="${anEntry.value.soiQty}" />" name="qty" onkeypress="return isNumberKey(event)"  />
		</fieldset>
<%--           <input type="button" id="adds${vs.index}" value="+" class="trashButton" onclick="modify(${anEntry.key}, ${anEntry.value.soiQty}, ${vs.index})"/> --%>
          <input type="button" id="adds${vs.index}" value="+" class="trashButton"/>
          <input type="button" id="add10s${vs.index}" value="+10" class="trashButton"/>
        </div>

        <div
          class="d-flex col-lg-2 col-4 justify-content-end align-items-center colPrice shoppingListItem pr-5"
        >
          <fmt:formatNumber value="${anEntry.value.soiPrice  * anEntry.value.soiQty}" pattern="#,###,###" />元
        </div>

		<!--         動態JS開始 -->
        <script>
        $('#adds${vs.index}').click(function add() {
    var $rooms = $("#newQty${vs.index}");
    var a = $rooms.val();

    a++;
    $("#subs${vs.index}").prop("disabled", !a);
    $rooms.val(a);

    modify(${anEntry.key}, ${anEntry.value.soiQty}, ${vs.index})
    $("#newQty${vs.index}").trigger(isNegative());
    
});
        $('#add10s${vs.index}').click(function add() {
    var $rooms = $("#newQty${vs.index}");
    var a = $rooms.val();

    a=(a*1+10);
    $("#sub10s${vs.index}").prop("disabled", !a);
    $rooms.val(a);

    modify(${anEntry.key}, ${anEntry.value.soiQty}, ${vs.index})
    $("#newQty${vs.index}").trigger(isNegative());
    
});

$("#subs${vs.index}").prop("disabled", !$("#newQty${vs.index}").val());
$("#sub10s${vs.index}").prop("disabled", !$("#newQty${vs.index}").val());

$('#subs${vs.index}').click(function subst() {
    var $rooms = $("#newQty${vs.index}");
    var b = $rooms.val();
    if (b >= 2) {
        b--;
        $rooms.val(b);
    }
    else {
        
        $("#subs${vs.index}").prop("disabled", true);
    }
    modify(${anEntry.key}, ${anEntry.value.soiQty}, ${vs.index})
});
$('#sub10s${vs.index}').click(function subst() {
    var $rooms = $("#newQty${vs.index}");
    var b = $rooms.val();
    if (b >= 2) {
        b=(b-10*1);
        $rooms.val(b);
    }
    else {
        
        $("#sub10s${vs.index}").prop("disabled", true);
    }
    modify(${anEntry.key}, ${anEntry.value.soiQty}, ${vs.index})
});
        </script>
<!--         動態JS結束 -->


        </c:forEach>
      </div>
      <!-- 商品細項欄位 no1 End -->
      <!-- 總價格欄位 Start-->
      <div class="row p-3 priceListAll justify-content-end">
        <div class="col-md-3 mx-5">
          <div class="row d-flex justify-content-between text-right">
            <div class="d-flex-inline align-self-end">
              <div class="priceAll mb-2">合計金額</div>
              <div class="shippingPrice mb-2">運費</div>
              <div class="discountPrice mb-2">營業稅</div>
              <div class="totalPrice mb-0">總計金額</div>
            </div>
            <div class="text-right priceAll">
              <div class="priceAll mb-2">$<fmt:formatNumber value="${subtotal}" pattern="#,###,###" />元</div>
              <div class="shippingPrice mb-2">$0</div>
              <div class="discountPrice discountPriceColor mb-2"><c:set var="VAT" value="${subtotal*0.05 + 0.0001}"/>
          <fmt:formatNumber value="${VAT}" pattern="#,###,###" />元</div>
              <div class="totalPrice"><fmt:formatNumber value="${subtotal + VAT }" pattern="#,###,###" />元</div>
            </div>
          </div>
        </div>
      </div>
      <!-- 總價格欄位 End -->
      <!-- 按鈕 Start-->
      <div class="row p-3 shoppingListBtn">
        <div class="d-flex col-md-12 col-sm-12 justify-content-end align-items-center border-top">
<!--           <div class="shoppingBtn col-lg-2 col-md-3 col-sm-6 col-xs-6 mt-3"> -->
<%--             <a href="<c:url value='/_03_listProducts/DisplayPageProducts2' />" class="btn btnEffect02 effect02"><span>繼續購物</span></a> --%>
<!--           </div> -->
          <div class="shoppingBtn col-lg-2 col-md-3 col-sm-6 col-xs-6 mt-3">
            <a href="<c:url value='checkout' />" onClick="return Checkout(${subtotal});" class="btn btnEffect02 effect02"><span>結帳</span></a>
          </div>
          <div class="shoppingBtn col-lg-2 col-md-3 col-sm-6 col-xs-6 mt-3">
            <a href="<c:url value='abort' />" onClick="return Abort();" class="btn btnEffect02 effect02"><span>放棄購物---</span></a>
          </div>
        </div>
      </div>
    </div>
    <!-- 按鈕 End-->
    <div class="container mb-3 shoppingInfo py-4 shadow">
      <div class="row">
        <div class="col-lg-12 d-flex align-items-center justify-content-center">
          <div class="">
            【預購型商品
            須知!】當訂單內包含預購商品時，整筆訂單將依照「預購商品的實際出貨日」合併出貨!
            (恕無法拆單出貨)
          </div>
        </div>
      </div>
    </div>
    <div class="container mb-5 shoppingInfo py-4 shadow">
      <div class="row">
        <div class="col-lg-12 d-flex align-items-center justify-content-center">
          <div class="">
            <i class="fas fa-shipping-fast fa-fw"></i>
            購物享7天鑑賞期，24小時快速出貨!
            <i class="fas fa-heart fa-fw"></i
            >【贈品】依【購物車】顯示資訊為準，訂單會依購物車內品項出貨!
          </div>
        </div>
      </div>
    </div>


<!-- 新畫面END -->

<div style='text-align:center;'>
<c:if test='${not empty OrderErrorMessage}'>
		<font color='red'>${OrderErrorMessage}</font>
		<c:remove var="OrderErrorMessage"/>	
</c:if>
</div>
    
<form>
   <input type="hidden" name="a"/>
</form>

	<!-- ---------------------------分隔線--------------------------------------- -->
		<jsp:include page="/fragment/footer_detail.jsp" />
	<!-- ---------------------------分隔線--------------------------------------- -->
	
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS ----------------------------------->
	
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

	
    <!-- JavaScript Plug-in End------------------------------------------------------------->
    <script src="<c:url value='/resources/javascript/shoppingList_3.js' />"></script>
	
	<script type="text/javascript">
function confirmDelete(n) {
	if (confirm("確定刪除此項商品 ? ") ) {
		document.forms[0].action="<c:url value='UpdateItem.do?cmd=DEL&p_Id=" + n +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	
	}
}
function modify(key, qty, index) {
	var x = "newQty" + index;
	var newQty = document.getElementById(x).value;
	if  (newQty < 0 ) {
		window.alert ('數量不能小於 0');
		document.getElementById(x).value = qty;
		return ; 
	}
	if  (newQty == 0 ) {
		window.alert ("請執行刪除功能來刪除此項商品");
		document.getElementById(x).value = qty;
		return ; 
	}
	if  (newQty == qty ) {
// 		window.alert ("新、舊數量相同，不必修改");
		return ; 
	}
// 	if (confirm("確定將此商品的數量由" + qty + " 改為 " + newQty + " ? ") ) {
		document.forms[0].action="<c:url value='UpdateItem.do?cmd=MOD&p_Id=" + key + "&newQty=" + newQty +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
// 	} else {
// 		document.getElementById(x).value = qty;
// 	}
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57)){
      return false;
   }
   return true;
}
function Checkout(qty) {
	if (qty == 0)  {
		alert("無購買任何商品，不需結帳");
		return false;
	}
	if (confirm("再次確認訂單內容 ? ") ) {
		return true;
	} else {
		return false;
	}
}
function Abort() {
	if (confirm("確定放棄購物 ? ") ) {
		return true;
	} else {
		return false;
	}
}
</script>

	
	
	
	
	<!-- ---------------------------分隔線--------------------------------------- -->
	<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- ---------------------------分隔線--------------------------------------- -->
    
     
</body>
</html>