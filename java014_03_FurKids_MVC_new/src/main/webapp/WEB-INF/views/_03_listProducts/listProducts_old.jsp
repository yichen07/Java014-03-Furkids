<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

<title>商品列表</title>
<style>
#content {
	width: 820px;
	margin-left: auto;
	margin-right: auto;
}

/* .collapsing { */
/* 	width: 150; */
/* 	transition-property: width; */
/* 	height: 100%; */
/* } */

</style>

</head>

<script type="text/javascript">

	var currPageNo = '1';
	
	function categoryClick(category) {
		$.ajax({
            method : 'GET',
            url : '<c:url value="/_03_listProducts/DisplayPageProducts2?pageNo=' + currPageNo + '&category=' + category + '"/>',
            async : false,
            success : function(result, textStatus, jqXHR) {
            	document.location.reload();
            },
            error : function(e) {
                console.log(e);
            }
        });
	}

	function changePage(pageNo) {		
		$.ajax({
            method : 'GET',
            url : '<c:url value="/_03_listProducts/DisplayPageProducts2?pageNo=' + pageNo + '"/>',
            async : false,
            success : function(result, textStatus, jqXHR) {
            	document.location.reload();
            },
            error : function(e) {
                console.log(e);
            }
        });
	}

</script>

<body>

	<!-- 下列敘述設定變數funcName的值為SHO，topMVC.jsp 會用到此變數 -->
	<c:set var="funcName" value="SHO" scope="session" />
	<!-- 引入共同的頁首 -->
	<jsp:include page="/fragment/navigation.jsp" />
	
	<!-- 判斷購物車內是否有商品 -->
	<c:choose>
		<c:when test="${ShoppingCart.itemNumber > 0}">
			<!-- 購物車內有一項以上的商品 -->
			<c:set var="cartContent" value="購物車內有${ShoppingCart.itemNumber}項商品" />
		</c:when>
		<c:otherwise>
			<!-- 購物車內沒有商品 -->
			<c:set var="cartContent" value="您尚未購買任何商品" />
		</c:otherwise>
	</c:choose>

	<br>
<!-- 商城標頭+購物車提示 -->
	<div class="p-lg5 p3 mb-0 ml-5 mr-5">
<div class="row">
<div class="col-12">
<TABLE style="background:#FFE4C4; border:1px solid black; width:100%" class="border-primary">
            <TR height='2'>
               <TH width="270">&nbsp;</TH>
               <TH width="280">&nbsp;</TH>
               <TH width="270">&nbsp;</TH>
            </TR>    
            <TR height='10'>
               <TD width="240">&nbsp;</TD>
               <TD width="320" align='center' >
<!--                   <FONT color='#8000FA' size='+2' style="font-weight:900;" > -->
<%--                       ${AppName} --%>
<!--                   </FONT> -->
               </TD>
               <TD width="270" align='right'>
                  <FONT  color='red' size='-1'>
                     ${cartContent}
                  </FONT>
               </TD>
            </TR>
            
            <TR height='2'>
                <TD width="270">
	               <A href="<c:url value='/_04_ShoppingCart/ShowCartContent' />" >
	                                                查看購物車
	               </A>
	            </TD>
                <TD width="280">&nbsp;</TD>
                <TD width="270" align='right'><FONT  color='red' size='-1'>
                                            金額小計(OK):<c:out value="${ShoppingCart.subtotal}" default="0"/> 元</FONT>
               </TD>
            </TR>          
         </TABLE>
</div>
</div>
</div>
<!-- 		分類按鈕 -->
	<div class="p-lg5 p3 m-2 text-center">
		<div class="row">
			<c:if test="${categoryList != null}">
				<c:forEach var='category' items='${categoryList}'>
					<div class="col-lg-2 col-sm-3 col-4 mb-2">
					<a class="btn btn-outline-primary"
						href="<c:url value='/_03_listProducts/DisplayPageProducts2?pageNo=1&category=${category}'/>">${category}</a>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>


		<div class=" p-lg-5 p3 mb-4">
			<div class="row">
				<c:forEach varStatus="stVar" var="product" items="${products}">
				<div class="col-lg-3 col-sm-6 col-12 mb-3">
            <div class="card shadow border-info ">
			<img src="${pageContext.servletContext.contextPath}/_00_init/getProductImage?id=${product.value.p_Id}"
							class="card-img-top" alt="..." />
              <div class="card-body text-info">
                <h5 class="card-title">${product.value.p_Name}</h5>
							<p class="card-text">
								NT$
								<fmt:formatNumber value="${product.value.p_Price}"
									pattern="####" />
								元
							</p>
<!-- 							<a -->
<%-- 								href="<spring:url value='/_03_listProducts/product?id=${product.value.p_Id}' />" --%>
<!-- 								class="btn btn-primary"> <span -->
<!-- 								class="glyphicon-info-sigh glyphicon"></span>詳細資料 -->
<!-- 							</a> -->
							<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal${product.value.p_Id}">
  詳細資料
</button>
              </div>
            </div>
          </div>

<!-- 預先建立modal -->
<!-- Modal -->
<div class="modal fade" id="exampleModal${product.value.p_Id}" tabindex="-1" 
aria-labelledby="exampleModalLabel${product.value.p_Id}" aria-hidden="true" style="z-index: 99991">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel${product.value.p_Id}">商品資料</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
			<div class="row">
				<div class="col-md-6">
					<h3>${product.value.p_Name}</h3>
					<p>分類: ${product.value.p_Category}</p>
					<p>單價: ${product.value.p_Price}</p>
					<p>敘述: ${product.value.p_Note}</p>
					<p>
						<strong>商品編號: </strong> <span class='label label-warning'>
							${product.value.p_Id} </span>
					</p>
					<p>
					<FORM  action="<c:url value='BuyProduct.do' />" method="POST">
                                購買數量:
               <select name='qty'>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
               </select>
               <!-- 這些隱藏欄位都會送到後端 -->
               <Input type='hidden' name='p_Id' value='${product.value.p_Id}'>
               <Input type='hidden' name='pageNo' value='${param.pageNo}'>
               <Input type='hidden' name='p_Category' value='${product.value.p_Category}'>
               <Input type='submit' class='btn btn-warning btn-large' value='加入購物車'>
       </FORM>
					</p>
<!-- 					<p> -->
<%-- 						<a href="<spring:url value='/_03_listProducts/DisplayPageProducts2' />" --%>
<!-- 							class="btn btn-default"> <span -->
<!-- 							class="glyphicon-hand-left glyphicon"></span>返回 -->
<!-- 						</a> <a href='#' class='btn btn-warning btn-large'> <span -->
<!-- 							class='glyphicon-shopping-cart glyphicon'></span>加入購物車 -->
<!-- 						</a> -->
<!-- 					</p> -->
				</div>
				<div class="col-md-6">
					<img
						src="${pageContext.servletContext.contextPath}/_00_init/getProductImage?id=${product.value.p_Id}"
						class="card-img-top" alt="..." />
				</div>
			</div>
      </div>
      <div class="modal-footer">
      <div class="row  ml-auto mr-auto">
      <div class="col-4">
      <img src="<c:url value='/_00_init/p_Pic1?id=${product.value.p_Id}'/>" style="max-width: 300px" />
      </div>
      <div class="col-4">
      <img src="<c:url value='/_00_init/productImage2?id=${product.value.p_Id}'/>" style="max-width: 300px" />
      </div>
      <div class="col-4">
      <img src="<c:url value='/_00_init/productImage3?id=${product.value.p_Id}'/>" style="max-width: 300px" />
      </div>
      </div>
<!--         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!--         <button type="button" class="btn btn-primary">Save changes</button> -->
      </div>
    </div>
  </div>
</div>

<!-- 預先建立modal結束 -->
				</c:forEach>
			</div>
		</div>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center mb-5">
			<li class="page-item disabled"><a class="page-link" href="#"
				tabindex="-1" aria-disabled="true">第${pageNo}頁</a></li>
			<c:if test="${pageNo >= 1}">
				<c:forEach var="x" begin="1" end="${totalPages}">
					<%--           	<c:if test="${x>=pageNo-2 && x<=pageNo+2}"> --%>
					<c:if
						test="${x >= pageNo - 2 && x <= 5 || x <= pageNo + 2 && x > totalPages - 5}">
						<li class="page-item  page-item-weight"><a class="page-link"
							href="<c:url value='/_03_listProducts/DisplayPageProducts2?pageNo=${x}'/>">${x}</a></li>
					</c:if>
				</c:forEach>
			</c:if>
			<li class="page-item disabled"><a class="page-link"
				tabindex="-1" aria-disabled="true"> 共${totalPages}頁</a></li>
		</ul>
	</nav>


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
	<!-- jQuery first, then Popper.js, then Bootstrap JS End-------------------------------->

	<!-- JavaScript Plug-in ---------------------------------------------------------------->

	<!-- icon -->
	<script src="https://kit.fontawesome.com/8e822d04fb.js"
		crossorigin="anonymous"></script>

	<!-- banner effect -->
	<script src="<c:url value='/resources/javascript/jquery.stellar.js' />"></script>
	
	<!-- ---------------------------分隔線--------------------------------------- -->
	<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- ---------------------------分隔線--------------------------------------- -->
	
</body>
</html>
