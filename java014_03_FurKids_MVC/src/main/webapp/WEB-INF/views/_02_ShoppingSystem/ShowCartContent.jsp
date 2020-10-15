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
<html lang="en">
  <head>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>
    <script type="text/javascript">
function confirmDelete(n) {
	if (confirm("確定刪除此項商品 ? ") ) {
		document.forms[0].action="<c:url value='UpdateItem.do?cmd=DEL&ComId=" + n +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	
	}
}
function modifyadd(key, qty, index) {
	var x = "newQty" + index ;
	var newQty = document.getElementById(x).value;
	if  (newQty < 0 ) {
		window.alert ('數量不能小於 0');
		return ; 
	}
	if  (newQty == qty ) {
// 		window.alert ("新、舊數量相同，不必修改");
		return ; 
	}
		document.forms[0].action="<c:url value='UpdateItem.do?cmd=MOD&ComId=" + key + "&newQty=" + newQty +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	
}

function modifysub(key, qty, index) {
    var x = "newQty" + index ;
 	var newQty = document.getElementById(x).value;

	if  (newQty < 0 ) {
		window.alert ('數量不能小於 0');
		return ; 
	}
	if  (newQty == qty ) {
// 		window.alert ("新、舊數量相同，不必修改");
		return ; 
	}
    document.forms[0].action="<c:url value='UpdateItem.do?cmd=MOD&ComId=" + key + "&newQty=" + newQty +"' />" ;
	document.forms[0].method="POST";
	document.forms[0].submit();
	
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
function subCounts() {
          var productCounts = document.getElementById("productCounts");
          var counts = parseInt(productCounts.innerHTML);
          if(counts>=2)
              counts--;
          productCounts.innerHTML = counts;
      }
	  
</script>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <!-- Bootstrap CSS --------------------------------------------------------------------->

    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
      crossorigin="anonymous"
    />

    <!-- animate.style CSS ----------------------------------------------------------------->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
    />

    <!-- Inport CSS End--------------------------------------------------------------------->

    <link
      rel="stylesheet"
      href="<c:url value='/resources/css/_02_ShoppingSystem/shoppingList_1.css' />"
    />

    <!-- Input CSS End---------------------------------------------------------------------->

    <title>FurKids確認訂單</title>
  </head>

  <body>
    <!-- NavBar Srart----------------------------------------------------------------------->

    <jsp:include page="/fragment/navigation.jsp" />

    <!-- NavBar End------------------------------------------------------------------------->

    <!-- Banner ---------------------------------------------------------------------------->

    <div
      class="container-fliid bannerImg d-flex justify-content-center align-items-center"
      id="imageStellar"
      data-stellar-background-ratio="0.5"
    >
      <div class="row">
        <div class="col-md-12 text-center">
          <h1 class="mt-3 bannerTitle animate__animated animate__fadeInDown">
            確認訂單
          </h1>
          <div
            class="align-items-center banner-text animate__animated animate__fadeInUp"
          >
            <h5 class="text-center">
              超多特惠商品，週週下殺便宜精選，讓你愛寵物愛得輕鬆又溫馨
            </h5>
          </div>
        </div>
      </div>
    </div>

    <!-- Banner End------------------------------------------------------------------------->

    <!-- Main Start------------------------------------------------------------------------->

    <div class="container mt-5 mb-3 productList">
      <!-- 免運資格 Start-->
      <div class="row">
        <div
          class="d-flex col-md-12 justify-content-between align-items-center shippingTitle"
        >
          <div><i class="fas fa-truck"></i> 免運資格</div>
          <div class="pr-1">
            開幕慶全館免運費>w<
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
          價格
        </div>
      </div>

	       <c:forEach varStatus="vs" var="anEntry" items="${ShoppingCart.content}">
      <!-- 商品細項欄位 no1 Start-->
      <div class="row p-3 shoppingListItemAll">
      <div
          class="col-lg-1 col-4 d-flex justify-content-center align-items-center colImage shoppingListItem"
        >
          <img
              src="<c:url value='/_00_Init/getBookImage?id=${anEntry.value.comID}' />"
              class="productImg"
          />
        </div>
        <div
          class="col-lg-5 col-8 d-flex justify-content-center align-items-center colProductName shoppingListItem"
        >
          ${anEntry.value.comName}        
		</div>
        <div
          class="d-flex col-lg-2 col-4 justify-content-center align-items-center colTrash shoppingListItem"
        >
          <button class="btn trashButton" onclick="confirmDelete(${anEntry.key})">
            <i class="fas fa-trash-alt"></i>
          </button>
        </div>
        <div
          class="d-flex col-lg-2 col-4 justify-content-center align-items-center colQuantity shoppingListItem"
        >
          <input type="button" class="subs" id="subs${vs.index}" value="-"  />
<!--             <input type="button" id="subs" value="-" /> -->
<!--           <input -->
<!--             type="text" -->
<!--             name="noOfRoom" -->
<!--             class="noOfRoom onlyNumber form-control text-center p-0 " -->
<%--             id="noOfRoom${vs.index}" --%>
<%--             value="<fmt:formatNumber value="${anEntry.value.ordQuantity}" />"          /> --%>
          
          <Input id="newQty${vs.index}" style="width:28px;text-align:center" name="newQty" type="text" value="<fmt:formatNumber value="${anEntry.value.ordQuantity}" />" onkeypress="return isNumberKey(event)"  />
          
          <input type="button" class="adds" id="adds${vs.index}" value="+"  />
<!--           <input type="button" id="adds" value="+" "/> -->

        </div>

        <div
          class="d-flex col-lg-2 col-4 justify-content-end align-items-center colPrice shoppingListItem pr-5"
        >
<%--         ${anEntry.value.ordUnitPrice} ${anEntry.value.ordQuantity} --%>
          <fmt:formatNumber value="${anEntry.value.ordUnitPrice * anEntry.value.ordQuantity}" pattern="#,###,###" />
        </div>
      </div>
<!--         動態JS開始 -->
<script>
  $('#adds${vs.index}').click(function add() {
    var $rooms = $("#newQty${vs.index}");
     var a = $rooms.val(); 
     a++; 
   $("#subs${vs.index}").prop("disabled", !a);
     $rooms.val(a); 
     
    modifyadd(${anEntry.key}, ${anEntry.value.ordQuantity}, ${vs.index})
    $("#newQty${vs.index}").trigger(isNegative());
    
 }); 
        

$("#subs${vs.index}").prop("disabled", !$("#newQty${vs.index}").val());

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
    modifysub(${anEntry.key}, ${anEntry.value.ordQuantity}, ${vs.index})
 }); 

</script> 
<!--         動態JS結束 -->
</c:forEach>

      <!-- 商品細項欄位 no1 End -->
      <!-- 總價格欄位 Start-->
      <div class="row p-3 priceListAll justify-content-end">
        <div class="col-md-3 mx-5">
          <div class="row d-flex justify-content-between text-right">
            <div class="d-flex-inline align-self-end">
              <div class="priceAll mb-2">小計</div>
              <div class="shippingPrice mb-2">運費</div>
            <!--  <div class="discountPrice mb-2">活動現折</div>  -->
              <div class="totalPrice mb-0">總金額</div>
            </div>
            <div class="text-right priceAll">            
              <div class="priceAll mb-2"><fmt:formatNumber value="${ShoppingCart.subtotal}" pattern="#,###,###" /></div>
              <div class="shippingPrice mb-2">0</div>
              <!-- <div class="discountPrice discountPriceColor mb-2">-$2800</div>  -->
              <div class="totalPrice"><fmt:formatNumber value="${ShoppingCart.subtotal}" pattern="#,###,###" /></div>
            </div>
          </div>
        </div>
      </div>
      <!-- 總價格欄位 End -->
      <!-- 按鈕 Start-->
      <div class="row p-3 shoppingListBtn">
        <div
          class="d-flex col-md-12 col-sm-12 justify-content-end align-items-center border-top"
        >
          <div class="shoppingBtn col-lg-2 col-md-3 col-sm-6 col-xs-6 mt-3">
            <a href="<c:url value='/_02_ShoppingSystem/DisplayPageProducts?pageNo=${param.pageNo}' />" class="btn btnEffect effect01"><span>繼續購物</span></a>
          </div>
          <div class="shoppingBtn col-lg-2 col-md-3 col-sm-6 col-xs-6 mt-3">
            <a href="<c:url value='checkout' />" class="btn btnEffect02 effect02"><span>結帳</span></a>
          </div>
          <div class="shoppingBtn col-lg-2 col-md-3 col-sm-6 col-xs-6 mt-3">
            <a href="<c:url value='abort' />" onClick="return Abort();" class="btn btnEffect02 effect02"><span>放棄購物</span></a>
          </div>
        </div>
      </div>
    </div>
    <!-- 按鈕 End-->
    <div class="container mb-3 shoppingInfo py-4">
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
    <div class="container mb-5 shoppingInfo py-4">
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
    <!-- Main End--------------------------------------------------------------------------->

    <!-- Footer Start----------------------------------------------------------------------->

    <div class="footer-dark">
      <footer>
        <div class="container">
          <div class="row">
            <div class="col-md-3 item">
              <h3>網站導覽</h3>
              <ul>
                <li><a href="#">寵物商城</a></li>
                <li><a href="#">寵物交流區</a></li>
                <li><a href="#">寵物友善系統</a></li>
              </ul>
            </div>
            <div class="col-md-3 item">
              <h3>購物說明</h3>
              <ul>
                <li><a href="#">付款相關問題</a></li>
                <li><a href="#">運送相關問題</a></li>
                <li><a href="#">退換貨說明</a></li>
              </ul>
            </div>
            <div class="col-md-3 item">
              <h3>訂閱我們</h3>
              <ul>
                <li><a href="#">關於FurKids</a></li>
                <li><a href="#">隱私權政策</a></li>
                <li><a href="#">文章</a></li>
              </ul>
            </div>
            <div class="col-md-3 item text">
              <h3>聯絡我們</h3>
              <ul>
                <li>地址：106台北市大安區忠孝東路三段1號</li>
                <li>電話：02 2771 2171</li>
              </ul>
            </div>
            <div class="col item social mt-5">
              <a href="#"><i class="fa fa-facebook"></i></a
              ><a href="#"><i class="fa fa-twitter"></i></a
              ><a href="#"><i class="fa fa-youtube"></i></a
              ><a href="#"><i class="fa fa-instagram"></i></a
              ><a href="#"><i class="fa fa-google"></i></a>
            </div>
          </div>
          <p class="copyright">Copyright © 2020 FurKids Inc.</p>
        </div>
      </footer>
    </div>

    <!-- Footer End------------------------------------------------------------------------->

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS ----------------------------------->
    <script
      src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
      integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
      integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
      integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
      crossorigin="anonymous"
    ></script>
    <!-- jQuery first, then Popper.js, then Bootstrap JS End-------------------------------->

    <!-- JavaScript Plug-in ---------------------------------------------------------------->

    <!-- icon -->
    <script
      src="https://kit.fontawesome.com/8e822d04fb.js"
      crossorigin="anonymous"
    ></script>

    <!-- banner effect -->
    <script src="../resources/javascript/jquery.stellar.js"></script>
    <!-- JavaScript Plug-in End------------------------------------------------------------->
    <script src="../resources/javascript/shoppingList_1.js"></script>
	<!-- navigation bar js ------------------------------------->
		<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->
  </body>
</html>
