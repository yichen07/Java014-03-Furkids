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
      href="../resources/css/_02_ShoppingSystem/shoppingList_2.css"
    />

    <!-- Input CSS End---------------------------------------------------------------------->

    <title>FurKids結帳資訊</title>
</head>
<body style="background:#FFFFFF;">
<c:set var="funcName" value="CHE" scope="session"/>
<%-- <jsp:include page="/fragment/navigation.jsp" /> --%>
<!-- <div style="text-align:center"> -->
<!-- <h3>請確認下列訊息：</h3> -->
<%-- <FORM style="margin: 0 auto; width:750px;" action="<c:url value='ProcessOrder' />" method="POST" > --%>
  
<!--    <TABLE border='1' style="background:#F5EBFF; border-color:rgb( 100, 100, 255); border-style: outset; width:810;"> -->
   
<!-- <!--    此處判斷是會員還是商家 --> -->
<%--    <c:if test="${LoginOK.CLASSIFY == 0}"> --%>
<!--       <TR > -->
<!--          <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<%--          	會員編號：${LoginOK.cusAccount} --%>
<!--          </TD> -->
<!--          <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<%--          	客戶姓名：${LoginOK.cusName} --%>
<!--          </TD> -->
<!--          <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<%--          	訂單日期：<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/> --%>
<!--          </TD> -->
<!--       </TR> -->
<!--       <TR> -->
<!--          <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<%--          	會員地址：${LoginOK.cusAddress} --%>
<!--          </TD> -->
<!--       </TR> -->
<!--       <TR> -->
<!--          <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<!--                              出貨地址：<Input style="background:#ECFFCD;" size="60" type="text" id='ShippingAddress'  -->
<!--                    name="ShippingAddress" value=""> -->
<%--                    <font color='red'>${errorMsg.ShippingAddress}</font> --%>
<!--          </TD> -->
<!--       </TR> -->
<%--       </c:if> --%>
<%--        <c:if test="${LoginOK.CLASSIFY == 1}"> --%>
<!--       <TR > -->
<!--          <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<%--          	會員編號：${LoginOK.busAccount} --%>
<!--          </TD> -->
<!--          <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<%--          	客戶姓名：${LoginOK.busName} --%>
<!--          </TD> -->
<!--          <TD style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<%--          	訂單日期：<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/> --%>
<!--          </TD> -->
<!--       </TR> -->
<!--       <TR> -->
<!--          <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<%--          	會員地址：${LoginOK.busAddress} --%>
<!--          </TD> -->
<!--       </TR> -->
<!--       <TR> -->
<!--          <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;"> -->
<!--                              出貨地址：<Input style="background:#ECFFCD;" size="60" type="text" id='ShippingAddress'  -->
<!--                    name="ShippingAddress" value=""> -->
<%--                    <font color='red'>${errorMsg.ShippingAddress}</font> --%>
<!--          </TD> -->
<!--       </TR> -->
<%--       </c:if> --%>
<!-- <!--    此處判斷是會員還是商家 --> -->
    
<!--       <TR> -->
<!--          <TD colspan='3'> -->
         
<!--    <TABLE border='1' style="background:#FFE7CD; border-color:rgb( 100, 100, 255); " > -->
      
<!--      <TR><TH style="text-align:center;font-size: 12pt;" width="350">商品名稱</TH> -->
<!-- <!--          <TH style="text-align:center;font-size: 12pt;" width="80">作者</TH> --> -->
<!--          <TH style="text-align:center;font-size: 12pt;" width="80">廠商</TH> -->
<!--          <TH style="text-align:center;font-size: 12pt;" width="80">單價</TH> -->
<!--          <TH style="text-align:center;font-size: 12pt;" width="60">數量</TH> -->
<!--          <TH style="text-align:center;font-size: 12pt;" width="110">小計</TH></TR> -->
     
<%--      <c:forEach varStatus="vs" var="entry" items="${ShoppingCart.content}"> --%>
                                                    
<!--         <TR height='16'> -->
<%--           <TD style="text-align:left  ;font-size: 11pt;">${entry.value.comName}</TD> --%>
<!-- <!--           <TD style="text-align:center;font-size: 11pt;"> --> -->
<%-- <%--           	${fn:substring(entry.value.author, 0, 3)} --%> --%>
<!-- <!--           </TD> --> -->
<!--           <TD style="text-align:center;font-size: 11pt;"> -->
<%--           	${entry.value.busName} --%>
<!--           </TD> -->
<!--           <TD style="text-align:right ;font-size: 11pt;"> -->
<%--           	<fmt:formatNumber value="${entry.value.ordUnitPrice}" pattern="#,###" />元 --%>
<!--           </TD> -->
<!--           <TD style="text-align:right ;font-size: 11pt;">  -->
<%--           	${entry.value.ordQuantity} --%>
<!--           </TD> -->
<!--           <TD style="text-align:right ;font-size: 11pt;"> -->
<%--           	<fmt:formatNumber  --%>
<%--           	value="${entry.value.ordUnitPrice * entry.value.ordQuantity}" pattern="#,###,###" />元 --%>
<!--           </TD> -->
<!--         </TR> -->
<%--      </c:forEach> --%>
     
<!--         <TR height='16'> -->
<!--           <TD style="text-align:right;font-size: 11pt;" colspan='5' >合計金額：</TD> -->
<!--           <TD style="text-align:right;font-size: 11pt;" > -->
<%--           <fmt:formatNumber value="${ShoppingCart.subtotal}" pattern="#,###,###" />元</TD> --%>
                  
<!--         </TR> -->
<!-- <!--         <TR> --> -->
<!-- <!--           <TD colspan='5' style="text-align:right;font-size: 11pt;" >營業稅：</TD> --> -->
<%-- <%--           <c:set var="VAT" value="${ShoppingCart.subtotal*0.05 + 0.0001}"/> --%> --%>
<!-- <!--           <TD style="text-align:right;font-size: 11pt;" >  --> -->
<%-- <%--           <fmt:formatNumber value="${VAT}" pattern="#,###,###" />元</TD> --%> --%>
<!-- <!--         </TR> --> -->
<!--         <TR> -->
<!--           <TD colspan='5' style="text-align:right;font-size: 11pt;" >總計金額：</TD> -->
<!--           <TD style="text-align:right;font-size: 11pt;color:#AA0200;" > -->
<%--           <fmt:formatNumber value="${ShoppingCart.subtotal}" pattern="#,###,###" />元</TD> --%>
<!--         </TR> -->
<!--    </TABLE> -->
<!--           </TD> -->
<!--       </TR> -->
 
<!--    </TABLE><P/> -->
<!--    <input type="hidden" name="finalDecision"  value="">    -->
<!--    <input type="button" name="OrderBtn"  value="確定送出" onclick="reconfirmOrder()"> -->
<!--    <input type="button" name="CancelBtn" value="取消訂單" onclick="cancelOrder()"> -->
<!-- </FORM> -->
<!-- </div> -->


<!-- ----------------------------------分隔線------------------------------------------------ -->

    <!-- NavBar Srart----------------------------------------------------------------------->

    	<jsp:include page="/fragment/navigation.jsp" />

    <!-- NavBar End------------------------------------------------------------------------->

    <!-- Banner ---------------------------------------------------------------------------->

    <div
      class="container-fliid bannerImg d-flex justify-content-center align-items-center"
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
              超多特惠商品，週週下殺便宜精選，讓你愛寵物愛得輕鬆又溫馨
            </h5>
          </div>
        </div>
      </div>
    </div>

    <!-- Banner End------------------------------------------------------------------------->

    <!-- Main Start------------------------------------------------------------------------->

    <!-- Product Detail Start -------------------------------------------------------------->
<FORM style="margin: 0 auto; " action="<c:url value='preProcessOrder' />" method="POST" >

    <div class="accordion container mt-5 mb-4">
      <div class="contentBx">
        <!-- 訂單明細標題 -->
        <div class="row label">
          <div
            class="col-lg-12 d-flex justify-content-between align-items-center py-3"
          >
            <div class="ml-5 productListNameHead">訂單明細</div>
            <div class="productListNameHead">
              總計 <span class="pr-3 listTotalPrice">${ShoppingCart.subtotal}</span>
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
            ${entry.value.comName}
          </div>

          <div
            class="col-lg-2 col-2 d-flex justify-content-center align-items-center productListNameText"
          >
            ${entry.value.ordQuantity}
          </div>
          <div
            class="col-lg-2 col-2 d-flex justify-content-center align-items-center productListNameText"
          >
            <fmt:formatNumber 
          	value="${entry.value.ordUnitPrice * entry.value.ordQuantity}" pattern="#,###,###" />
          </div>
        </div>
       
        </c:forEach>
        
        <!-- 商品細項1 -->
       
        <!-- 金額細項 -->
        <div class="content row">
          <div class="col-lg-12 col-12 text-right priceDetail pb-2 pr-5">
            總計：${ShoppingCart.subtotal}
          </div>
        </div>
        <!-- 金額細項 End-->
      </div>
    </div>
    <!-- Product Detail End ---------------------------------------------------------------->

    <!-- Customer Info Start---------------------------------------------------------------->
    <c:if test="${LoginOK.CLASSIFY == 0}">
     <div class="container custInfo mb-4">
      <div class="row  p-3">
        <div class="col-md-6 col-xs-12 custInfoLeft">
          <h5 class="pb-4 pt-2 custInfoText "><i class="far fa-user custIcon"></i> 購買人資料</h5>

          <div class="form-group mt-4 ml-3">
            <input type="text" class="form-control"  value="${LoginOK.cusName}" id="OrdBuyName" name="OrdBuyName" required />
            <label class="form-control-placeholder">姓名</label>
            <font color='red'>${errorMsg.OrdBuyName}</font>
          </div>
          <div class="form-group mt-5 ml-3">
            <input type="tel" class="form-control" value="${LoginOK.cusTel}" id="OrdBuyPhone" name="OrdBuyPhone" required />
            <label class="form-control-placeholder">電話號碼</label>
            <font color='red'>${errorMsg.OrdBuyPhone}</font>
            
          </div>
          <div class="form-group mt-5 ml-3 mb-5">
            <input type="text" class="form-control" value="${LoginOK.cusAccount}" id="OrdBuyEmail" name="OrdBuyEmail" required />
            <label class="form-control-placeholder">Email</label>
            <font color='red'>${errorMsg.OrdBuyEmail}</font>
          
          </div>
        </div>
        <div class="col-md-6 col-xs-12">
          <h5 class="pb-4 pt-2  custInfoText custInfoTextR"><i class="fas fa-user-check custIcon"></i> 收件人資料</h5>
<!--           <form action=""> -->
            <div class="form-group mt-4 ml-4">
              <input type="text" class="form-control" id="OrdReciveName" name="OrdReciveName" required />
              <label class="form-control-placeholder">姓名</label>
              <font color='red'>${errorMsg.OrdReciveName}</font>
            </div>
            <div class="form-group mt-5 ml-4">
              <input type="tel" class="form-control" id="OrdRecivePhone" name="OrdRecivePhone" required />
              <label class="form-control-placeholder">手機號碼</label>
              <font color='red'>${errorMsg.OrdRecivePhone}</font>
            </div>
 
<!--             <div class="d-flex twzipStyle mt-5 ml-4 mb-5" id="twzipcode_ADV"> -->
<!--               <label class="form-control-placeholder twAdress"></label> -->
<!--             </div> -->
            
            <div class="form-group mt-5 ml-3 mb-5">
              <input type="text" class="form-control" id="ShippingAddress" name="ShippingAddress" required />
              <label class="form-control-placeholder">地址</label>
              <font color='red'>${errorMsg.ShippingAddress}</font>
            </div>
 
<!--           </form> -->
        </div>
      </div>
    </div>
      </c:if> 
          <c:if test="${LoginOK.CLASSIFY == 1}">
    <div class="container custInfo mb-4">
      <div class="row  p-3">
        <div class="col-md-6 col-xs-12 custInfoLeft">
          <h5 class="pb-4 pt-2 custInfoText "><i class="far fa-user custIcon"></i> 購買人資料</h5>

          <div class="form-group mt-4 ml-3">
            <input type="text" class="form-control"  value="${LoginOK.busName}" id="OrdBuyName" name="OrdBuyName" required />
            <label class="form-control-placeholder">姓名</label>
            <font color='red'>${errorMsg.OrdBuyName}</font>
          </div>
          <div class="form-group mt-5 ml-3">
            <input type="tel" class="form-control" value="${LoginOK.busTel}" id="OrdBuyPhone" name="OrdBuyPhone" required />
            <label class="form-control-placeholder">電話號碼</label>
            <font color='red'>${errorMsg.OrdBuyPhone}</font>
            
          </div>
          <div class="form-group mt-5 ml-3 mb-5">
            <input type="text" class="form-control" value="${LoginOK.busAccount}" id="OrdBuyEmail" name="OrdBuyEmail" required />
            <label class="form-control-placeholder">Email</label>
            <font color='red'>${errorMsg.OrdBuyEmail}</font>
          
          </div>
        </div>
        <div class="col-md-6 col-xs-12">
          <h5 class="pb-4 pt-2  custInfoText custInfoTextR"><i class="fas fa-user-check custIcon"></i> 收件人資料</h5>
<!--           <form action=""> -->
            <div class="form-group mt-4 ml-4">
              <input type="text" class="form-control" id="OrdReciveName" name="OrdReciveName" required />
              <label class="form-control-placeholder">姓名</label>
              <font color='red'>${errorMsg.OrdReciveName}</font>
            </div>
            <div class="form-group mt-5 ml-4">
              <input type="tel" class="form-control" id="OrdRecivePhone" name="OrdRecivePhone" required />
              <label class="form-control-placeholder">手機號碼</label>
              <font color='red'>${errorMsg.OrdRecivePhone}</font>
            </div>
 
<!--             <div class="d-flex twzipStyle mt-5 ml-4 mb-5" id="twzipcode_ADV"> -->
<!--               <label class="form-control-placeholder twAdress"></label> -->
<!--             </div> -->
            
            <div class="form-group mt-4 ml-4 mb-5">
              <input type="text" class="form-control" id="ShippingAddress" name="ShippingAddress" required />
              <label class="form-control-placeholder">地址</label>
              <font color='red'>${errorMsg.ShippingAddress}</font>
            </div>
 
<!--           </form> -->
        </div>
      </div>
    </div>
      </c:if>

    <!-- Customer Info End ----------------------------------------------------------------->

    <!-- Commit Terms Start ---------------------------------------------------------------->
    <div class="container commitOrder mb-5">
      <div class="row">
        <div
          class="checkbox-wrapper col-12 d-flex justify-content-center align-items-center mt-2"
        >
          <input type="checkbox" id="checkTerms" hidden /><label
            for="checkTerms"
            class="checkStyle mr-2"
          ></label>
          <div class="checkboxText">
            我同意辦理退貨時，由FurKids代為處理發票及銷貨退回證明單，以加速退貨退款作業。
          </div>
        </div>
      </div>
      <div class="row justify-content-center align-items-center">
        <div class="shoppingBtn col-lg-2 col-md-3 col-sm-6 col-xs-6 mt-3">
             <input type="hidden" name="finalDecision"  value="">   
          <a
            href="#"
            class="btn btnEffect02 effect02 mb-4 d-flex align-items-center justify-content-center"
            onClick="return reconfirmOrder()"
            ><div class="btnText1">提交訂單</div></a
          >
<!--              <input type="button" name="OrderBtn"  value="確定送出" onclick="reconfirmOrder()"> -->
<!--    <input type="button" name="CancelBtn" value="取消訂單" onclick="cancelOrder()"> -->
        </div>
      </div>
    </div>
</FORM>
    <!-- Commit Terms Eed ------------------------------------------------------------------>

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

    <!-- Taiwan Address -->
    <script src="../resources/javascript/jquery.twzipcode.js"></script>

    <!-- JavaScript Plug-in End------------------------------------------------------------->
    <script src="../resources/javascript/shoppingList_2.js"></script>
    
     <!-- navigation bar js ------------------------------------->
		<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->    
  </body>


</body>
</html>