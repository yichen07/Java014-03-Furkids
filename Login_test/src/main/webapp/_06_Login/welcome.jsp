<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FurKids_welcome</title>
    <link href="css/login.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<header class="fixed-top">
    <nav class="navbar navbar-expand-lg navbar-dark container" style="background-color: rgba(77,77,77,0.0);">
        <a class="navbar-brand" href="index.html"><img src="images/Logo-02.png" width="100px" height="auto" alt="logo"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        商城
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <!--<a class="dropdown-item" href="#">預留欄位</a>-->                                      
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        交流專區
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">寵物專欄</a>    
                        <a class="dropdown-item" href="#">寵物失蹤協尋</a>    
                        <a class="dropdown-item" href="#">二手市集</a> 
                        <a class="dropdown-item" href="#">活動建立</a>  
                        <a class="dropdown-item" href="#">寵物交友</a>
                        <a class="dropdown-item" href="#">留言板</a>                                     
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        友善專區
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">景點</a>    
                        <a class="dropdown-item" href="#">餐廳</a>    
                        <a class="dropdown-item" href="#">寵物美容</a> 
                        <a class="dropdown-item" href="#">旅館</a>  
                        <a class="dropdown-item" href="#">寵物寄放</a>
                        <a class="dropdown-item" href="#">租屋</a>                                           
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        健康管理
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">寵物熱量計算</a>    
                        <a class="dropdown-item" href="#">寵物健康管理</a>    
                        <a class="dropdown-item" href="#">寵物疫苗資訊</a>  
                        <a class="dropdown-item" href="#">寵物食譜</a>                                         
                    </div>
                </li>
            </ul>
            <form class="navbar-nav my-2 my-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="login.html"><p1>會員登入/註冊</p1><p2><img src="images/member.svg" width="34px" height="auto" alt=""/></p2></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><p1>購物車</p1><p2><img src="images/cart.svg" width="34px" height="auto" alt=""/></p2></a>
                </li> 
            </form>
        </div>
    </nav>
</header>
<main>
    <h3>歡迎[${requestScope.user.userName }]登入</h3>
</main>
<footer class="fixed-bottom">
    <h4>© 2020 FurKids 著作權所有</h4>
</footer>
</body>
</html>