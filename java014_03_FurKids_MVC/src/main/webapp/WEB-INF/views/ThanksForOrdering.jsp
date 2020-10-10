<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
      href="resources/css/_02_ShoppingSystem/shoppingListSuccess.css"
    />

    <!-- Input CSS End---------------------------------------------------------------------->

    <title>FurKids結帳資訊</title>
  </head>

  <body>
    <!-- NavBar Srart----------------------------------------------------------------------->
    <jsp:include page="/fragment/navigation.jsp" />

    
    <!-- NavBar End------------------------------------------------------------------------->

    <!-- Main Start------------------------------------------------------------------------->

    <div
      class="container-fliid bannerImg d-flex justify-content-center align-items-center"
      id="imageStellar"
      data-stellar-background-ratio="0.5"
      style="background-image: url(<c:url value='/resources/images/_02_ShoppingSystem/shoppingIndex_Banner_01.jpg' />)"
    >
      <div class="row">
        <div class="col-lg-12 text-center boaderOrder">
         <!-- 勾勾動畫 Start -->
          <div class="dummy-positioning">
            <div class="success-icon">
              <div class="success-icon__tip"></div>
              <div class="success-icon__long"></div>
            </div>
          </div>
         <!-- 勾勾動畫 End -->
          <h1 class="mt-3 bannerTitle animate__animated animate__fadeInDown">
            交易成功
          </h1>
          <div
            class="align-items-center banner-text animate__animated animate__fadeInUp"
          >
            <p class="text-center pb-3 successDetail">
              我們收到你的訂單囉！<br />
              訂單編號：135731 <br />
              店長會趕快為你準備出貨，訂單出貨訂單出貨需約 1 ~ 2 個工作天，<br />
              出貨時會寄發簡訊到你的手機， 如有相關問題請洽詢店長。
            </p>
          </div>
          <div class=".backHomeBtn">
            <a href="#" class="btn btnHome"><span class="btnHomeText">回到首頁</span> </a>
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
    <script src="resources/javascript/jquery.stellar.js"></script>

    <!-- Taiwan Address -->
    <script src="resources/javascript/jquery.twzipcode.js"></script>

    <!-- JavaScript Plug-in End------------------------------------------------------------->
    <script src="resources/javascript/shoppingList_2.js"></script>
  </body>
</html>
