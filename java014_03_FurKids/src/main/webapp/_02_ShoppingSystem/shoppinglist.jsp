<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Furkids 寵物商城</title>
    <!-- Bootstrap CSS -->
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
      crossorigin="anonymous"
    />
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/shoppingCart.css">
  </head>
  <body>
    <!-- Nav-Bar  Start-->
         <!-- <div class="container"></div><nav class="navbar navbar-expand-lg bg-white sticky-top shadow-sm" id="navBar"> -->
	   
	      <!-- <a class="navbar-brand" href="#"><img class="navLogo" src="../img/Logo-05.png" alt="" srcset=""></a> -->
	      <!-- <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> -->
          <!-- <span class="navbar-toggler-icon"></span> -->
	      <!-- </button> -->

	      <!-- <div class="collapse navbar-collapse" id="ftco-nav"> -->
	        <!-- <div class="collapse navbar-collapse justify-content-end mr-5" id="navbarNavDropdown"> -->
            <!-- <ul class="navbar-nav"> -->
              
              <!-- <li class="nav-item dropdown dropdown-color"> -->
                <!-- <a class="nav-link dropdown-toggle px-3" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
                  <!-- 寵物商城 -->
                <!-- </a> -->
                <!-- <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> -->
                  <!-- <a class="dropdown-item" href="#">自己加</a> -->
                <!-- </div> -->
              <!-- </li> -->
              <!-- <li class="nav-item dropdown dropdown-color"> -->
                <!-- <a class="nav-link dropdown-toggle px-3" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
                  <!-- 寵物交流版 -->
                <!-- </a> -->
                <!-- <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> -->
                  <!-- <a class="dropdown-item" href="#">自己加</a> -->
                <!-- </div> -->
              <!-- </li> -->
              <!-- <li class="nav-item dropdown dropdown-color"> -->
                <!-- <a class="nav-link dropdown-toggle px-3" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
                  <!-- 寵物健康管理</a> -->
                <!-- <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> -->
                  <!-- <a class="dropdown-item" href="#">自己加</a> -->
                <!-- </div> -->
              <!-- </li> -->
              <!-- <li class="nav-item dropdown dropdown-color"> -->
                <!-- <a class="nav-link dropdown-toggle px-3" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
                  <!-- 寵物友善系統 -->
                <!-- </a> -->
                <!-- <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> -->
                  <!-- <a class="dropdown-item" href="#">自己加</a> -->
                <!-- </div> -->
              <!-- </li> -->
            <!-- </ul> -->
          <!-- </div> -->
        <!-- </div> -->       
	    <!-- </div>  <!-- shoppingCart --><div class="member px-2"> -->
          <!-- <button class="btn btn-sm btn-cart" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
            <!-- <i class="fas fa-user"></i> -->
        <!-- </button> -->
        <!-- </div> <div class="dropdown mr-5"> -->
          <!-- <button class="btn btn-m btn-cart" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
            <!-- <i class="fas fa-shopping-bag"></i> -->
              <!-- <span class="badge badge-danger"><span class="badge-size">12</span><span> -->
          <!-- </button> -->
       <!-- <div class="dropdown-menu" style="min-width: 300px"> -->
           <!-- <!-- <div class="px-4 py-3"> -->
             <!-- <h6>已選擇商品</h6> -->
            <!-- <table class="table"> -->
              
              <!-- <tbody> -->
                <!-- <tr> -->
                  <!-- <th scope="row">1ㄅ</th> -->
                  <!-- <td>小鮮肉狗狗鮮食餐</td> -->
                  <!-- <td>x1</td> -->
                  <!-- <td>NT$100</td> -->
                  <!-- <td><button class="trash"><i class="far fa-trash-alt"></i></button></i></td> -->
                <!-- </tr> -->
                <!-- <tr> -->
                  <!-- <th scope="row">2</th> -->
                  <!-- <td>貓咪無穀主食罐-雞肉系列</td> -->
                  <!-- <td>x1</td> -->
                  <!-- <td>NT$49</td> -->
                  <!-- <td><button class="trash"><i class="far fa-trash-alt"></i></button></i></td> -->
                <!-- </tr> -->
                <!-- <tr> -->
                  <!-- <th scope="row">3</th> -->
                  <!-- <td>Hyperr 超躍 手作零食</td> -->
                  <!-- <td>x1</td> -->
                  <!-- <td>NT$200</td> -->
                  <!-- <td><button class="trash"><i class="far fa-trash-alt"></i></button></i></td> -->
                <!-- </tr> -->
              <!-- </tbody> -->
            <!-- </table> -->
           <!-- </div> --> -->
         <!-- </div> -->
        <!-- </div> -->
	  <!-- </nav> -->
     <!-- Nav-Bar  End-->
     
 

     <!-- topPicture  Start-->
  
 	<jsp:include page="/fragment/navigation.jsp" />
   
<%--     <c:if test = ${LoginOK.}> --%>
<%--        ${LoginOK.cusName} 您好 --%>
<%--     </c:if> --%>
    
    <c:choose>

        <c:when test="${Session.LoginOK}">   
        </c:when>
   
        <c:otherwise> 
        </c:otherwise>
  
     </c:choose>
     <div class="container-fluid" id="imageStellar">
         <div class="row">
             <div class="col imgBox" style="background-image: url(../img/cat-3266675.jpg);" data-stellar-background-ratio="0.5">   <span class="headtext d-flex justify-content-center align-items-center mt-auto"><h1>寵物商城</h1></span>
         </div>
      
    

     </div>
     
     <!-- Main  Item-->

     <!-- sidebar -->
     
        <div class="container-fluid p-0 mt-5 pl-5 ">
  
            <!-- Bootstrap row -->
            <div class="row" id="body-row">
                <!-- Sidebar -->
                <div id="sidebar-container" class="sidebar-expanded d-none d-md-block borderless"><!-- d-* hiddens the Sidebar in smaller devices. Its itens can be kept on the Navbar 'Menu' -->
                    <!-- Bootstrap List Group -->
                    <ul class="list-group borderless">
                        <!-- Separator with title -->
                        <li class="list-group-item sidebar-separator-title text-muted d-flex align-items-center menu-collapsed borderless">
                            <span class="pr-2 "><i class="fas fa-cat"></i></span><small style="color: rgb(132, 200, 255);"></i>貓咪專區</small>
                        </li>
                        <!-- /END Separator -->
                        <!-- Menu with submenu -->
                        <a href="#submenu1" data-toggle="collapse" aria-expanded="false" class="bg-transparent list-group-item list-group-item-action flex-column align-items-start text-secondary border-none">
                            
                            <div class="d-flex w-100 justify-content-start align-items-center font-weight-bold">
                                <span class=" mr-3"><i class="fas fa-fish"></i></span> 
                                <span class="menu-collapsed">飼料區</span>
                                <span class="submenu-icon ml-auto"></span>
                            </div>
                        </a>
                        <!-- Submenu content -->
                        <div id='submenu1' class="collapse sidebar-submenu border-none">
                            <a href="#" class="list-group-item list-group-item-action bg-transparent text-secondary">
                                <span class="menu-collapsed">罐頭區</span>
                            </a>
                            <a href="#" class="list-group-item list-group-item-action bg-transparentt text-secondary">
                                <span class="menu-collapsed">冷凍生肉</span>
                            </a>
                            <a href="#" class="list-group-item list-group-item-action bg-transparent text-secondary">
                                <span class="menu-collapsed">乾主食</span>
                            </a>
                        </div>
                      
                      <!-- Separator with title -->
                      <li class="list-group-item sidebar-separator-title text-muted d-flex align-items-center menu-collapsed bg-transparent">
                        <span class="pr-2"><i class="fas fa-dog"></i></i></span><small style="color: rgb(132, 200, 255);"></i>狗狗專區</small>
                    </li>
                        <!-- Menu with submenu -->
                      <a href="#submenu2" data-toggle="collapse" aria-expanded="false" class="bg-transparent list-group-item list-group-item-action flex-column align-items-start text-secondary">
                            
                        <div class="d-flex w-100 justify-content-start align-items-center font-weight-bold">
                            <span class=" mr-3"><i class="fas fa-bone"></i></i></span> 
                            <span class="menu-collapsed">飼料區</span>
                            <span class="submenu-icon ml-auto border-none"></span>
                        </div>
                    </a>
                    <!-- Submenu content -->
                    <div id='submenu2' class="collapse sidebar-submenu">
                        <a href="#" class="list-group-item list-group-item-action bg-transparent text-secondary border-none">
                            <span class="menu-collapsed">罐頭區</span>
                        </a>
                        <a href="#" class="list-group-item list-group-item-action bg-transparent text-secondary border-none">
                            <span class="menu-collapsed">冷凍生肉</span>
                        </a>
                        <a href="#" class="list-group-item list-group-item-action bg-transparent text-secondary border-none">
                            <span class="menu-collapsed">乾主食</span>
                        </a>
                    </div>
                       
                        <!-- Separator without title -->
                               
                    



                        
                        <!-- /END Separator -->
                        
                        
                    </ul><!-- List Group END-->
                </div><!-- sidebar-container END -->
            
     <!-- MAIN -->
	 

     <div class="container">
      <div class="row wow bounceInUp">
	  	<c:forEach varStatus="stVar"  var="entry"  items="${products_DPP}" >

        <div class="col-lg-4 col-sm-6 col-12 mb-4">
          <div class="card">
            <img
              src="${pageContext.servletContext.contextPath}/_00_init/getBookImage?id=${entry.value.comId}"
              class="card-img-top"
              alt="..."
            />
           <FORM  action="<c:url value='/_02_ShoppingSystem/BuyCommodity.do' />" method="POST">
            
            <div class="card-body">
              <h5 class="card-title">${entry.value.comName}</h5>
              <p class="card-text">
                ${entry.value.comDescription}
              </p>
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
               <Input type='hidden' name='ComId' value='${entry.value.comId}'>           
              <Input type='submit' value='加入購物車'>        
              <a href="#" class="btn btn-primary">加入購物車</a>
            </div>
            </FORM>
          </div>
        </div>
    </c:forEach> 

      </div>
    </div>
<!-- 以下為控制第一頁、前一頁、下一頁、最末頁 等超連結-->
<table border="1">
  <tr>
    <td width='76'>
        <c:if test="${pageNo > 1}">
           <div id="pfirst">
              <a href="<c:url value='DisplayPageProducts?pageNo=1' />">第一頁</a>
           </div>
        </c:if>
     </td>
     <td width='76'>
        <c:if test="${pageNo > 1}">
           <div id="pprev">
              <a href="<c:url value='DisplayPageProducts?pageNo=${pageNo-1}' />">上一頁</a>
           </div>
        </c:if>  
     </td>
     <td width='76'>
            <c:if test="${pageNo != totalPages}">
                <div id="pnext">
                   <a href="<c:url value='DisplayPageProducts?pageNo=${pageNo+1}' />">下一頁</a>
                </div>
            </c:if>
     </td>  
     <td width='76'>
            <c:if test="${pageNo != totalPages}">
                <div id="plast">
                    <a href="<c:url value='DisplayPageProducts?pageNo=${totalPages}' />">最末頁</a>
                </div>
            </c:if>
     </td>
     <td width='176' align="center">
                      第${pageNo}頁 / 共${totalPages}頁
     </td>  
</tr>
</table>
     


  </div><!-- Main Col END -->
  
</div><!-- body-row END -->

     

    <!-- bootstrap JS  Start-->
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
    <!-- bootstrap JS  End-->
    <link rel="stylesheet" href="../css/discuss.css" />
    <script src="${pageContext.request.contextPath}/resources/javascript/shoppingCart.js"></script>
    <script
    src="https://kit.fontawesome.com/8e822d04fb.js"
    crossorigin="anonymous"
  ></script>
  <script src="${pageContext.request.contextPath}/resources/javascript/jquery.stellar.js"></script>
  </body>
</html>
