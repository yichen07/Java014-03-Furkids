
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
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
      href="../resources/css/_02_ShoppingSystem/shoppingIndex_01.css"
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
      style="background-image: url(<c:url value='/resources/images/_02_ShoppingSystem/shoppingIndex_Banner_01.jpg' />)"
    >
      <div class="row">
        <div class="col-md-12 text-center">
          <h1 class="mt-3 bannerTitle animate__animated animate__fadeInDown">
            寵物商城
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
    <div class="wrapper">
      <!-- Sidebar Holder -->
      <nav id="sidebar">
        <ul class="list-unstyled components">
          <!-- 商品欄位1 -->
          <li class="">
            <a
              href="#Submenu_01"
              class="sideBarSubTItleBg"
              data-toggle="collapse"
              aria-expanded="false"
              ><i class="fas fa-cat mt-2 sidebarTextIcon"> </i>
              <span class="sidebarText ml-1"> 貓咪商品</span>
            </a>
            <ul class="collapse list-unstyled" id="Submenu_01">
              <li class="sideBarSubTItle">
                <a href="<c:url value='/_02_ShoppingSystem/DisplayPageProductsSort?pageNo=1&sort=food' />" class="sideBarSubTItleColor">貓食</a>
              </li>
              <li class="sideBarSubTItle">
                <a href="<c:url value='/_02_ShoppingSystem/DisplayPageProductsSort?pageNo=1&sort=item' />" class="sideBarSubTItleColor">貓用品</a>
              </li>
            </ul>
          </li>
          <!-- 商品欄位2 -->
          <li class="">
            <a
              href="#Submenu_02"
              class="sideBarSubTItleBg"
              data-toggle="collapse"
              aria-expanded="false"
              ><i class="fas fa-dog mt-2 sidebarTextIcon"> </i
              ><span class="sidebarText ml-1"> 狗狗商品</span>
            </a>
            <ul class="collapse list-unstyled" id="Submenu_02">
              <li class="sideBarSubTItle">
                <a href="#" class="sideBarSubTItleColor">狗飼料/乾糧</a>
              </li>
              <li class="sideBarSubTItle">
                <a href="#" class="sideBarSubTItleColor">狗飼料/乾糧</a>
              </li>
            </ul>
          </li>
        </ul>
      </nav>

      <div id="content">
        <button
          type="button"
          id="sidebarCollapse"
          class="btn navbar-btn ml-2 my-2 mb-5"
        >
          <span><i class="fas fa-bars"></i></span>
        </button>

        <div class="e-commerce-product">
          <div class="container-fluid">

		   
		   <div class="row d-flex justify-content-start ml-5 mr-4 mb-5">
	  	      <c:forEach varStatus="stVar"  var="entry"  items="${products_DPP}" >
              <div class="   col-lg-3 col-md-4 col-sm-6 col-12 mb-5 ">
                <div class="product">
                  <div class="content">
                    <img
                      src="${pageContext.servletContext.contextPath}/_00_Init/getBookImage?id=${entry.value.comId}"
                      alt="image"
                      class="productImg"
                    />
                    <FORM  action="<c:url value='BuyCommodity.do' />" method="POST">
                    <div class="product-btn">
                      <a type="button">
                      
                      <Input type='submit' value='加入購物車' style="background-color: #ffffff00; border:none; color:white">        
                       <Input type='hidden' name='qty' value='1'>  
                       <Input type='hidden' name='ComId' value='${entry.value.comId}'>
                      </a>
                       
                    </div>
                  </FORM>
                   
                  </div>
                  <div class="product-details">
                    <h4 class="title px-3">
                      ${entry.value.comName}
                    </h4>
                   <!--  <div class="price mb-0">$999<del>原價 $1580</del></div> -->
				   <div class="price mb-0">${entry.value.comPrice}</div>
				    
                  </div>
                </div>
              </div>
         
                 </c:forEach> 
            </div>

            <!-- Page Number Start -->
<!--             <div class="row d-flex justify-content-center mb-5"> -->

<!--               <nav class="pagination-outer" aria-label="Page navigation"> -->
<!--                 <ul class="pagination"> -->
<!--                     <li class="page-item"> -->
<%--                         <a href="<c:url value='DisplayPageProductsSort?pageNo=${pageNo-1}' />" class="page-link" aria-label="Previous"> --%>
<!--                             <span aria-hidden="true">«</span> -->
<!--                         </a> -->
<!--                     </li> -->
<%--                     <c:if test="${pageNo != 1}"> --%>
<%--                           <li class="page-item"><a class="page-link" href="<c:url value='DisplayPageProductsSort?pageNo=1&sort=${param.sort}' />">1</a></li> --%>
<%--                     </c:if> --%>
<%--                     <c:if test="${pageNo-2 > 1}"> --%>
<%--                           <li class="page-item"><a class="page-link" href="<c:url value='DisplayPageProductsSort?pageNo=${pageNo-2}&sort=${sort}' />">${pageNo-2}</a></li> --%>
<%--                     </c:if> --%>
<%--                      <c:if test="${pageNo-1 > 1}"> --%>
<%--                           <li class="page-item"><a class="page-link" href="<c:url value='DisplayPageProductsSort?pageNo=${pageNo-1}' />">${pageNo-1}</a></li> --%>
<%--                     </c:if> --%>
<%--                     <li class="page-item active"><a class="page-link" href="<c:url value='DisplayPageProductsSort?pageNo=${pageNo}' />">${pageNo}</a></li> --%>
<%--                     <c:if test="${pageNo+1 < totalPages}"> --%>
<%--                           <li class="page-item"><a class="page-link" href="<c:url value='DisplayPageProductsSort?pageNo=${pageNo+1}' />">${pageNo+1}</a></li> --%>
<%--                     </c:if> --%>
<%--                     <c:if test="${pageNo+2 < totalPages}"> --%>
<%--                           <li class="page-item"><a class="page-link" href="<c:url value='DisplayPageProductsSort?pageNo=${pageNo+2}' />">${pageNo+2}</a></li> --%>
<%--                     </c:if> --%>
<%--                     <c:if test="${pageNo != totalPages}"> --%>
<%--                           <li class="page-item"><a class="page-link" href="<c:url value='DisplayPageProductsSort?pageNo=${totalPages}' />">${totalPages}</a></li>                     --%>
<%--                     </c:if>  --%>
<!--                     <li class="page-item"> -->
<%--                         <a href="<c:url value='DisplayPageProducts?pageNo=${pageNo+1}' />" class="page-link" aria-label="Next"> --%>
<!--                             <span aria-hidden="true">»</span> -->
<!--                         </a> -->
<!--                     </li> -->
<!--                 </ul> -->
<!--             </nav> -->




<!--             </div> -->
            <!-- Page Number End -->
          </div>
        </div>
      </div>
      <!-- Page Number Start -->
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
    <script src="../js/jquery.stellar.js"></script>
    <script src="../resources/javascript/jquery.stellar.js"></script>
    <!-- JavaScript Plug-in End------------------------------------------------------------->
    <script src="../resources/javascript/shoppingIndex_01.js"></script>
    <!-- navigation bar js ------------------------------------->
		<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->    
  </body>
</html>