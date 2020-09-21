<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Furkids Main Page</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"/>
    
    <style>
        * {
            box-sizing: border-box;
        }
    
        body {
            overflow: hidden;
        }
    
        a {
            text-decoration: none;
        }
    
        button {
            background-color: transparent;
            border: none;
            cursor: pointer;
        }
    
        button:focus {
            outline: none;
        }
    
        .asideMenu {
            width: 300px;
            background-color: #474747;
            position: absolute;
            top: 60px;
            bottom: 0;
            right: 0;
            border-left: 5px solid #ff9a15;
            padding: 20px;
            box-shadow: 0 0 5px rgba(23, 23, 54, 0.6);
            transform: translateX(0);
            transition: all 0.5s;
        }
    
        .asideMenu.active {
            transform: translateX(295px);
        }
    
        .asideMenu .btn {
            position: absolute;
            top: 50%;
            left: -13%;
            padding: 40px 10px;
            background-color: #ff9a15;
            border-radius: 6px 0 0 6px;
            box-shadow: 0 0 5px rgba(23, 23, 54, 0.6);
        }
    
        .asideMenu .btn .fa-chevron-right {
            color: #474747;
            transform: rotate(0);
            transition: all 0.5s;
        }
    
        .asideMenu .btn .fa-chevron-right.rotate {
            transform: rotate(180deg);
        }
    
        .asideMenu .title {
            font-size: 24px;
            color: #ff9a15;
        }
    
        .asideMenu .list {
            display: flex;
        }
    
        .asideMenu .list .optionTitle {
            color: #ff9a15;
        }
    
        .asideMenu .list .optionList {
            color: #fff;
        }
    
        .asideMenu .list .optionTitle,
        .asideMenu .list .optionList {
            display: block;
            padding: 20px;
            line-height: 2;
        }
    
        .asideMenu .list .optionTitle .fas,
        .asideMenu .list .optionList .fas {
            color: #fff;
            outline: none;
            border: none;
        }
    </style>



</head>
<body>

<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/navigation.jsp" />

<div class="asideMenu">
        <button class="btn"><i class="fas fa-chevron-right fa-2x"></i></button>
        <div class="title">Aside Menu</div>
        <div class="list">
          <ul class="optionTitle">
            <li>index</li>
            <li>username</li>
            <li>city</li>
            <li>number</li>
            <li>edit</li>
            <li>delete</li>
          </ul>
          <ul class="optionList">
            <li>1</li>
            <li>Tim</li>
            <li>Taipei</li>
            <li>0912345678</li>
            <li>
              <button><i class="fas fa-edit fa-lg"></i></button>
            </li>
            <li>
              <button><i class="fas fa-trash-alt fa-lg"></i></button>
            </li>
          </ul>
        </div>
      </div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(function () {
            $(".btn").click(function () {
                $(".asideMenu").toggleClass("active");
                $(".fa-chevron-right").toggleClass("rotate");
            });
        });
    </script>


</body>
</html>